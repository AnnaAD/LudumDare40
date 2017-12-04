package com.ludumdare40;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class PauseMenu extends BasicGameState {
    private long delta;
    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        delta = 0;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.setBackground(new Color(0x092d05));
        g.setColor(Color.white);
        String text = "Press 'p' to resume, q to quit";
        g.drawString(text, gc.getWidth() / 2 - gc.getDefaultFont().getWidth(text) / 2, gc.getHeight() / 2 -gc.getDefaultFont().getHeight(text)/2);
    }


    @Override
    public void update(GameContainer gc, StateBasedGame stateBasedGame, int i) throws SlickException {
        delta += i;
        if(delta > 500) {
            if(gc.getInput().isKeyPressed(Input.KEY_P)){
                stateBasedGame.enterState(0);
            }
            if (gc.getInput().isKeyDown(Input.KEY_Q)) {
                gc.exit();
                System.exit(-1);
            }
        }
    }
}
