package de.ash.framework.items

class ItemStack(val item: Item, initialAmount: Int = 0)
{
    var amount: Int = initialAmount
        set(value)
        {
            field = if (value < 0) 0 else value
        }
}