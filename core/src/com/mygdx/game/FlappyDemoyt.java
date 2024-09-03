package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;


public class FlappyDemoyt extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE =  "Flappy Bird";
	private GameStateManager gsm;
	private SpriteBatch batch;
	public static BitmapFont font; // Font for drawing text


	private Music music;


	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("1.02 Yoshi Story.mp3"));
		music.setLooping(true);
		music.setVolume(0.9f);
		music.play();
		font = new BitmapFont(); // Initialize the font
		Gdx.gl.glClearColor(1,0,0,1);

		// Initialize the custom font
		font = new BitmapFont(Gdx.files.internal("font/font.fnt"), Gdx.files.internal("font/font_0.png"), false);

		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

}
