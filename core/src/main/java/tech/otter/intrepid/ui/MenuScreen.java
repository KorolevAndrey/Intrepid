package tech.otter.intrepid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.parser.action.ActorConsumer;
import com.github.czyzby.lml.util.Lml;

/**
 * @author John Lynn <john@otter.tech>
 * @date 3/4/18
 */
public class MenuScreen extends GameScreen {
    @Override
    public void show() {
        stage = new Stage(new FitViewport(WIDTH, HEIGHT));
        skin = new Skin(Gdx.files.internal("ui/skin.json"));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void createUI() {
        LmlParser parser = Lml.parser(skin)
                // Play action to move to the play scene
                .action("playClicked", (ActorConsumer<Void, TextButton>) actor -> {
                    actor.setText("Clicked."); return null;
                })
                // Quit action to exit the game.
                .action("quitClicked", (ActorConsumer<Void, TextButton>) actor -> {
                    System.exit(0); return null;
                })
                // Adding showing action for the window:
                .action("fadeIn", (ActorConsumer<Action, Window>) actor -> Actions.fadeIn(1f)).build();

        // Parsing actors defined in main.lml template and adding them to stage:
        parser.fillStage(stage, Gdx.files.internal("ui/templates/main.lml"));
    }

    @Override
    public void render(float delta) {

    }
}
