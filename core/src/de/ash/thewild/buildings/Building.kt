package de.ash.thewild.buildings

import de.ash.framework.items.Inventory
import de.ash.framework.items.Recipe

class Building(val prototype: PrototypeBuilding, initLevel: Int = 0)
{
    var level: Int = initLevel
        private set

    private var roundUpgradeStarted: Int = 0
    private var isUpgrading: Boolean = false

    private val activeProduction: ArrayList<Production> = ArrayList()

    init
    {
    }

    fun update(delta: Float, inv: Inventory)
    {
        if (isUpgrading) // building is in progress of being upgraded -> no production
        {
            // TODO add upgrading routine
            return
        }

        // Normal production
        prototype.passiveRecipes.forEach {
            if (inv.contains(it.requiredItems))
            {
                inv.remove(it.requiredItems)
                inv.add(it.producedItems)
            } else
            {
                // TODO add some kind of alert that the required resources is not available
            }
        }

        activeProduction.forEach {
            if (inv.contains(it.recipe.requiredItems))
            {
                inv.remove(it.recipe.requiredItems)
                inv.add(it.recipe.producedItems)
                it.amount--
            } else
            {
                // TODO add some kind of alert that the required resources is not available
            }
        }

        // remove all production that are done
        activeProduction.removeIf { it.amount == 0 }
    }

    /**
     * Starts the upgrading process for the building and saves the current round.
     */
    fun upgrade(currentRound: Int)
    {
        isUpgrading = true;
        roundUpgradeStarted = currentRound
    }

    fun startProduction(recipe: Recipe, amount: Int)
    {
        // Check if production was already started and just add to the current limit
        for (production in activeProduction)
        {
            if (production.recipe == recipe)
            {
                production.amount += amount
                return
            }
        }

        // Add new production to the active productions if the building is capable of producing it
        if (prototype.activeRecipes.contains(recipe))
        {
            activeProduction.add(Production(recipe, amount))
        }
    }

    fun getBuildingType() = prototype.type
}