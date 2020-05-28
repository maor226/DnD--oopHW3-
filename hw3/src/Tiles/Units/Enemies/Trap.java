package Tiles.Units.Enemies;

import Tiles.Point;

public class Trap extends Enemy {
    private Integer visibilityTime;
    private Integer invisibilityTime;
    private Integer ticksCount;
    private boolean visible;

    public Trap(Integer visibilitytime, Integer invisibilitytime, Character tile, Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experienceValue)
    {
        super(tile,position,name,healthPool,healthAmount,attackPoints,defencePoints,experienceValue);
        visibilityTime=visibilitytime;
        invisibilityTime=invisibilitytime;
        ticksCount=0;
        visible=true;
    }

    @Override
    public void GameTick() {
        visible = (ticksCount<visibilityTime);
        if(ticksCount==(visibilityTime+invisibilityTime))
            ticksCount=0;
        else
            ticksCount++;
        /**add attacking mechanism
         * check range then attack **/


    }
}
