package de.ash.thewild.buildings

import de.ash.framework.items.Recipe
import de.ash.thewild.Recipes

class PrototypeBuilding private constructor(val name: String, val type: BuildingType, val maxLevel: Int,
                                            val activeRecipes: List<Recipe>, val passiveRecipes: List<Recipe>)
{
    companion object
    {
        val lumberjack: PrototypeBuilding = PrototypeBuilding("lumberjack", BuildingType.LUMBERJACK, 10,
                listOf<Recipe>(), listOf(Recipes.basicLumberjackRecipe))
    }

    /*
    -name -> name of the building
    -max level -> maximum achievable level of the building; increases production; unlocks new recipes
    -active recipes -> all available active recipes
    -passive recipes -> all available recipes

    -Passive production: -> resources, e.g. wheat, iron, wood, etc.
        .is checked every tick
        .if enough time passed -> consumes resources it needs and produces something
        .if not enough resources available -> production is stopped
    -Active production: -> military, e.g. troops
        .player decides how much should be produced
        .works like passive production until limit is reached
    -> both consume specific resources and produce others
     */
}