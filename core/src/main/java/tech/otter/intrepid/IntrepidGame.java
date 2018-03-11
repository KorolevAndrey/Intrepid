package tech.otter.intrepid

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.Window
import com.badlogic.gdx.utils.viewport.FitViewport
import com.github.czyzby.kiwi.util.gdx.AbstractApplicationListener
import com.github.czyzby.kiwi.util.gdx.asset.Disposables
import com.github.czyzby.lml.parser.LmlParser
import com.github.czyzby.lml.parser.action.ActorConsumer
import com.github.czyzby.lml.util.Lml

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class IntrepidGame : AbstractApplicationListener() {

    private var stage: Stage? = null
    private var skin: Skin? = null

    override fun create() {
        stage = Stage(FitViewport(WIDTH.toFloat(), HEIGHT.toFloat()))
        skin = Skin(Gdx.files.internal("ui/skin.json"))

        val parser = Lml.parser(skin)
                // Adding action for the button listener:
                .action("setClicked", { actor ->
                    actor.setText("Clicked.")
                    null
                } as ActorConsumer<Void, TextButton>)
                // Adding showing action for the window:
                .action("fadeIn", { actor -> Actions.fadeIn(1f) } as ActorConsumer<Action, Window>).build()

        // Parsing actors defined in main.lml template and adding them to stage:
        parser.fillStage(stage, Gdx.files.internal("ui/templates/main.lml"))
        // Note: there are less verbose and more powerful ways of using LML. See other LML project templates.

        Gdx.input.inputProcessor = stage
    }

    override fun resize(width: Int, height: Int) {
        stage!!.viewport.update(width, height)
    }

    public override fun render(deltaTime: Float) {
        // AbstractApplicationListener automatically clears the screen with black color.
        stage!!.act(deltaTime)
        stage!!.draw()
    }

    override fun dispose() {
        // Null-safe disposing utility method:
        Disposables.disposeOf(stage, skin)
    }

    companion object {
        /** Default application size.  */
        val WIDTH = 640
        val HEIGHT = 480
    }
}