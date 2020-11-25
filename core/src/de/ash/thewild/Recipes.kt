package de.ash.thewild

import de.ash.framework.items.ItemStack
import de.ash.framework.items.Recipe

class Recipes
{
    companion object
    {
        val basicLumberjackRecipe: Recipe = Recipe(listOf(), listOf<ItemStack>(ItemStack(Items.wood, 50)))
    }
}