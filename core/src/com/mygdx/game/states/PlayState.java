package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyDemoyt;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture bg;
    private Array<Tube> tubes;
    private int score;
    private ScoreManager scoreManager; // ScoreManager instance

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        cam.setToOrtho(false, FlappyDemoyt.WIDTH / 2, FlappyDemoyt.HEIGHT / 2);
        bg = new Texture("bg.png");

        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
        score = 0;
        scoreManager = new ScoreManager(); // Initialize ScoreManager
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            gsm.set(new MenuState(gsm));
        }

        }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for (Tube tube : tubes) {
            if ((cam.position.x - cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosBotTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                score++;
                scoreManager.saveScore(score); // Update score in ScoreManager
            }
            if (tube.collides(bird.getBounds())) {
                gsm.set(new PlayState(gsm));
                gsm.set(new com.mygdx.game.states.GameOverState(gsm, score));
                return;

            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        FlappyDemoyt.font.draw(sb, " " + score, cam.position.x - cam.viewportWidth / 2 + 10, cam.viewportHeight - 10);

        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
    }
}
