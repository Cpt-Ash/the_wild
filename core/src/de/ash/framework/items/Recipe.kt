package de.ash.framework.items

import java.util.*

class Recipe(reqItems: List<ItemStack>, prodItems: List<ItemStack>)
{
    val requiredItems: List<ItemStack> = Collections.unmodifiableList(reqItems)
    val producedItems: List<ItemStack> = Collections.unmodifiableList(prodItems)
}