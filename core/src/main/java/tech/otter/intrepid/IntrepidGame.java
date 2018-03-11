package tech.otter.intrepid;

import com.artemis.World;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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
import tech.otter.intrepid.ui.BaseScreen;
import tech.otter.intrepid.ui.MenuScreen;

public class IntrepidGame implements ApplicationListener, Controller {
    private BaseScreen screen;
    private World model;

    @Override
    public void create() {
        model = new World();

        this.changeScreen(new MenuScreen(this));
    }

    @Override
    public void resize(int width, int height) {
        if(screen != null) screen.resize(width, height);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(screen != null) screen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void pause() {
        if(screen != null) screen.pause();
    }

    @Override
    public void resume() {
        if(screen != null) screen.resume();
    }

    @Override
    public void dispose() {
        if(screen != null) screen.dispose();
    }

    @Override
    public void exit() {
        this.dispose();
        System.exit(0);
    }

    @Override
    public void changeScreen(BaseScreen nextScreen) {
        if(this.screen != null) this.screen.dispose();

        this.screen = nextScreen;
        this.screen.show();
        this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public World getModel() {
        return this.model;
    }
}