package Tiles.Units.Players;

import Tiles.Point;

public class Hunter extends Player {

    private Integer range;
    private Integer arrowsCount;
    private Integer ticksCount;

    public Hunter(Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experience, Integer level, Integer range) {
        super(position, name, healthPool, healthAmount, attackPoints, defencePoints, experience, level);
        this.range = range;
        ticksCount=0;
    }

    public void LevelUp()
    {
        super.LevelUp();

    }

    @Override
    public void GameTick() {
        if(ticksCount == 10)
        {
            arrowsCount += level;
            ticksCount=0;
        }
        else
        {
            ticksCount++;
        }

    }

    //------------------getters-----------------------
}
