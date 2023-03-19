package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.GameFrame;
import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.service.GameMaster;

public class Player extends DynamicObject {
    private  int state;
    public int getState() {
        return state;
    }
    private int coinCount;

    private static final int stepSize = 1;

    public Player(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.PLAYER_SPRITE);
        coinCount =0;
        this.state =0;
    }

    public void move(Direction direction) {
        int tmpXPosition = getXPosition();
        int tmpYPosition = getYPosition();

        switch (direction) {
            case UP -> tmpYPosition -= stepSize;
            case DOWN -> tmpYPosition += stepSize;
            case LEFT -> tmpXPosition -= stepSize;
            case RIGHT -> tmpXPosition += stepSize;
        }

        if (isAllowedSurface(tmpXPosition, tmpYPosition) && isAllowedExit(tmpXPosition,tmpYPosition) ) {
            xPosition = tmpXPosition;
            yPosition = tmpYPosition;
        }
        if(CheckCoin(this.xPosition,this.yPosition)){
            coinCount++;

        }
    }

    @Override
    public String toString() {
        return "Player{[" + xPosition + ":" + yPosition + "]}" + "Coin: "+ coinCount+ "/9";
    }
    private boolean CheckCoin (int x, int y){
        for(GameObject o: GameMaster.getInstance().getCoinObject()){
            if(o instanceof Coin){
                if((x==o.getXPosition()) && (y == o.getYPosition())){
                    ((Coin) o).setState(true);
                    return true;

                }
            }
        }
        return false;
    }

    private boolean isAllowedExit (int x, int y) {
        if (GameMaster.getInstance().getMap().getMap()[y][x] == Configuration.EXIT_CHARACTER) {
            if (coinCount < 9) {
                return false;
            }
                this.state=1;
                return true;
        }
            return true;
    }
}
