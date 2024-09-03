package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyDemoyt;

public class GameOverState extends State {
    private Texture gameOverTexture;
    private BitmapFont font;
    private int score;
    private Texture backgroundTexture; // Added for background image

    public GameOverState(GameStateManager gsm, int score) {
        super(gsm);
        this.score = score;
        gameOverTexture = new Texture("gameover.png"); // Path to your game over sprite
        backgroundTexture = new Texture("bg.png"); // Path to your background image

        font = new BitmapFont(); // Use default font or load your own
        cam.setToOrtho(false, FlappyDemoyt.WIDTH / 2, FlappyDemoyt.HEIGHT / 2);
        // Save high score if the current score is higher
        int highScore = ScoreManager.loadScore();
        if (score > highScore) {
            ScoreManager.saveScore(score);
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm)); // Restart the game
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(backgroundTexture, cam.position.x - (float) backgroundTexture.getWidth() / 2, cam.position.y - (float) backgroundTexture.getHeight() / 2);

        sb.draw(gameOverTexture, cam.position.x - (float) gameOverTexture.getWidth() / 2, cam.position.y);
        String scoreText = "Score: " + score;
        float textWidth = font.getRegion().getRegionWidth() * scoreText.length(); // Approximate width of the text
        font.draw(sb, scoreText, cam.position.x - textWidth / 2, cam.position.y - gameOverTexture.getHeight() / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        gameOverTexture.dispose();
        font.dispose();
    }
}
