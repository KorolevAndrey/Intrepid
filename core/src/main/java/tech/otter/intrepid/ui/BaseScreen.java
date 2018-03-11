package tech.otter.intrepid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.czyzby.kiwi.util.gdx.asset.Disposables;

/**
 * @author John Lynn <john@otter.tech>
 * @date 3/4/18
 */
public abstract class GameScreen implements Screen {
    public static final int WIDTH = 640, HEIGHT = 480;

    protected Stage stage;
    protected Skin skin;

    @Override
    public void show() {
        stage = new Stage(new FitViewport(WIDTH, HEIGHT));
        skin = new Skin(Gdx.files.internal("ui/skin.json"));

        createUI();

        Gdx.input.setInputProcessor(stage);
    }

    public abstract void createUI();

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {
        // Currently no change on pause.
    }

    @Override
    public void resume() {
        // Currently no change on resume.
    }

    @Override
    public void hide() {
        // Currently no change on hide.
    }

    @Override
    public void dispose() {
        Disposables.disposeOf(stage, skin);
    }
}
