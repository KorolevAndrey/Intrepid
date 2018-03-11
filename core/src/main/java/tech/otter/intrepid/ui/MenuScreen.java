package tech.otter.intrepid.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.parser.action.ActorConsumer;
import com.github.czyzby.lml.util.Lml;
import tech.otter.intrepid.Controller;

/**
 * @author John Lynn <john@otter.tech>
 * @date 3/4/18
 */
public class MenuScreen extends BaseScreen {
    public MenuScreen(Controller controller) {
        super(controller);
    }

    @Override
    public void createUI() {
        LmlParser parser = Lml.parser(skin)
                // Play action to move to the play scene
                .action("playClicked", (ActorConsumer<Void, TextButton>) actor -> {
                    stage.addAction(Actions.sequence(
                            Actions.fadeOut(0.5f),
                            new Action() {
                                @Override
                                public boolean act(float delta) {
                                    controller.changeScreen(new PlayScreen(controller));
                                    return true;
                                }
                            }
                    ));
                    return null;
                })
                // Quit action to exit the game.
                .action("quitClicked", (ActorConsumer<Void, TextButton>) actor -> {
                    stage.addAction(Actions.sequence(
                            Actions.fadeOut(0.5f),
                            new Action() {
                                @Override
                                public boolean act(float delta) {
                                    controller.exit();
                                    return true;
                                }
                            })
                    );
                    return null;
                })
                // Adding showing action for the window:
                .action("fadeIn", (ActorConsumer<Action, Window>) actor -> Actions.fadeIn(1f))
                .build();

        // Parsing actors defined in main.lml template and adding them to stage:
        parser.fillStage(stage, Gdx.files.internal("ui/templates/MainMenu.lml"));
    }
}
