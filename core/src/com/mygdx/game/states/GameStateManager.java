package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> States;
    public GameStateManager(){
        States = new Stack<State>();
    }

    public void push(State state) {
        States.push(state);
    }

    public void pop(){
        States.pop();
    }
    public void set(State state) {
        States.pop();
        States.push(state);

    }
    public void update(float dt) {
        States.peek().update(dt);

    }

    public void render(SpriteBatch sb) {
        States.peek().render(sb);
    }
}
