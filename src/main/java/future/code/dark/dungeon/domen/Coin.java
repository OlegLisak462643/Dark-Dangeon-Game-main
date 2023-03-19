package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

public class Coin extends GameObject{

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    private boolean state;

    public Coin(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.COIN_SPRITE);
        this.state = false;
    }

}
