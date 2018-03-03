package tech.otter.intrepid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.czyzby.kiwi.util.gdx.AbstractApplicationListener;
import com.github.czyzby.kiwi.util.gdx.asset.Disposables;
import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.parser.action.ActorConsumer;
import com.github.czyzby.lml.util.Lml;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class IntrepidGame extends AbstractApplicationListener {
    /** Default application size. */
    public static final int WIDTH = 640, HEIGHT = 480;

    private Stage stage;
    private Skin skin;

    @Override
    public void create() {
        stage = new Stage(new FitViewport(WIDTH, HEIGHT));
        skin = new Skin(Gdx.files.internal("ui/skin.json"));

        LmlParser parser = Lml.parser(skin)
            // Adding action for the button listener:
            .action("setClicked", new ActorConsumer<Void, TextButton>() {
                @Override public Void consume(TextButton actor) {
                    actor.setText("Clicked."); return null;
                }
            })
            // Adding showing action for the window:
            .action("fadeIn", new ActorConsumer<Action, Window>() {
                @Override public Action consume(Window actor) {
                    return Actions.fadeIn(1f);
                }
            }).build();

        // Parsing actors defined in main.lml template and adding them to stage:
        parser.fillStage(stage, Gdx.files.internal("ui/templates/main.lml"));
        // Note: there are less verbose and more powerful ways of using LML. See other LML project templates.

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void render(float deltaTime) {
        // AbstractApplicationListener automatically clears the screen with black color.
        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    public void dispose() {
        // Null-safe disposing utility method:
        Disposables.disposeOf(stage, skin);
    }
}