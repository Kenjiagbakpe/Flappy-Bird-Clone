package com.mygdx .game;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.FlappyDemoyt;





// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(FlappyDemoyt.WIDTH, FlappyDemoyt.HEIGHT);
		config.setTitle(FlappyDemoyt.TITLE);
		config.setForegroundFPS(60);
		config.setForegroundFPS(60);
		config.setTitle("Flappy Bird");
		new Lwjgl3Application(new FlappyDemoyt(), config);
	}}

