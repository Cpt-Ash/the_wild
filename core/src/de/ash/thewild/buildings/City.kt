package de.ash.thewild.buildings

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import de.ash.framework.items.Inventory
import java.util.*

class City()
{
    private val buildings: MutableMap<BuildingType, Building> = EnumMap(BuildingType::class.java)
    private val storage: Inventory = Inventory()

    private lateinit var buildingLabel: Label
    private lateinit var storageLabel: Label

    constructor(initBuildings: Set<Building>) : this()
    {
        initBuildings.forEach { buildings[it.getBuildingType()] = it }
    }

    fun update(delta: Float)
    {
        buildings.values.forEach { it.update(delta, storage) }

        updateGuiValues()
    }

    fun setRenderCity(stage: Stage, skin: Skin)
    {
        stage.clear()

        // Create table to manage positioning of widgets on the screen
        val table: Table = Table()
        table.setFillParent(true)
        stage.addActor(table)

        // Label with all available buildings
        buildingLabel = Label("", skin)
        table.add(buildingLabel).top().left().pad(20f)

        // Label with all items in the storage
        storageLabel = Label("", skin)
        table.add(storageLabel).top().right().pad(20f)

        // Set all stage actor texts
        updateGuiValues()
    }

    private fun updateGuiValues()
    {
        val buildingLabelText = StringBuilder()
        buildingLabelText.append("Buildings:\n")
        buildings.values.forEach {
            buildingLabelText.append(it.prototype.name)
            buildingLabelText.append(" = ")
            buildingLabelText.append(it.level)
            buildingLabelText.append("\n")
        }
        buildingLabel.setText(buildingLabelText.toString())

        val storageLabelText = StringBuilder()
        storageLabelText.append("Storage:\n")
        storageLabelText.append(storage.toString())
        storageLabel.setText(storageLabelText.toString())
    }
}