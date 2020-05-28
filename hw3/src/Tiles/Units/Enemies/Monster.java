package Tiles.Units.Enemies;

import Tiles.Point;

public class Monster extends Enemy {
    Integer visionRange;

    public Monster(Integer visionrange, Character tile, Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experienceValue)
    {
        super(tile,position,name,healthPool,healthAmount,attackPoints,defencePoints,experienceValue);
        visionRange=visionrange;
    }

    @Override
    public void GameTick() {
        //todo
    }
}
