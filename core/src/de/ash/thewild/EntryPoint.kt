package de.ash.thewild

import com.badlogic.gdx.Application.LOG_DEBUG
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.ScreenViewport
import de.ash.thewild.buildings.Building
import de.ash.thewild.buildings.City
import de.ash.thewild.buildings.PrototypeBuilding

/*

TODO implement upgrading of buildings
TODO add functionality to the level of a building
TODO implement proper loading of items, buildings and recipes from a YAML file

 */

class EntryPoint : ApplicationAdapter()
{
    lateinit var batch: SpriteBatch
    lateinit var stage: Stage

    lateinit var skin: Skin
    lateinit var img: Texture

    lateinit var city: City

    override fun create()
    {
        Gdx.app.logLevel = LOG_DEBUG

        batch = SpriteBatch()
        stage = Stage(ScreenViewport(), batch)
        Gdx.input.inputProcessor = stage


        // --------------------

        skin = Skin()

        val generator = FreeTypeFontGenerator(Gdx.files.internal("fonts/MontserratAlternates-Bold.ttf"))

        val parameterBigFont = FreeTypeFontGenerator.FreeTypeFontParameter()
        val parameterSmallFont = FreeTypeFontGenerator.FreeTypeFontParameter()

        parameterSmallFont.size = 18
        parameterBigFont.size = 36

        val fontBig = generator.generateFont(parameterBigFont)
        val fontSmall = generator.generateFont(parameterSmallFont)

        generator.dispose()

        val pixmap = Pixmap(1, 1, Pixmap.Format.RGBA8888)
        pixmap.setColor(Color.WHITE)
        pixmap.fill()

        skin.add("white", Texture(pixmap))
        skin.add("big", fontBig)
        skin.add("small", fontSmall)

        val labelStyleBig = Label.LabelStyle()
        labelStyleBig.font = skin.getFont("big")
        skin.add("big", labelStyleBig)

        val labelStyleSmall = Label.LabelStyle()
        labelStyleSmall.font = skin.getFont("small")
        skin.add("default", labelStyleSmall, Label.LabelStyle::class.java)

        // --------------------

        img = Texture("badlogic.jpg")

        val initBuildings = setOf(Building(PrototypeBuilding.lumberjack, 1))
        city = City(initBuildings)

        city.setRenderCity(stage, skin)
    }

    override fun render()
    {
        // UPDATE

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
        {
            Gdx.app.debug("DEBUG", "Next Round")
            city.update(Gdx.graphics.deltaTime)
        }

        // RENDER

        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()

        batch.end()

        stage.draw()
    }

    override fun dispose()
    {
        batch.dispose()
        img.dispose()
    }
}
