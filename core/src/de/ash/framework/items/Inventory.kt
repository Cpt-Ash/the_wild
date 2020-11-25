package de.ash.framework.items

class Inventory
{
    private val items: MutableMap<Int, ItemStack> = hashMapOf()

    fun add(item: Item, amount: Int)
    {
        val stack: ItemStack? = items[item.id]
        if (stack == null) items[item.id] = ItemStack(item, amount)
        else stack.amount += amount
    }

    fun add(stack: ItemStack)
    {
        add(stack.item, stack.amount)
    }

    fun add(itemList: List<ItemStack>)
    {
        itemList.forEach { add(it) }
    }

    fun remove(item: Item, amount: Int)
    {
        val stack: ItemStack = items[item.id] ?: return
        stack.amount -= amount
    }

    fun remove(stack: ItemStack)
    {
        remove(stack.item, stack.amount)
    }

    fun remove(itemList: List<ItemStack>)
    {
        itemList.forEach { remove(it) }
    }

    fun contains(item: Item, amountNeeded: Int): Boolean
    {
        val stack: ItemStack = items[item.id] ?: return false
        return stack.amount >= amountNeeded
    }

    fun contains(required: ItemStack): Boolean
    {
        return contains(required.item, required.amount)
    }

    fun contains(required: List<ItemStack>): Boolean
    {
        return required.all { contains(it) }
    }

    override fun toString(): String
    {
        val builder = StringBuilder()

        items.values.forEach{
            builder.append(it.item.name)
            builder.append(" = ")
            builder.append(it.amount)
            builder.append("\n")
        }

        return builder.toString()
    }
}
