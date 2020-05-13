package Tiles.Units.Enemies;

import Tiles.Point;

public class Monster extends Enemy {

    protected Integer visionRange;

    public Monster(Character tile, Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experienceValue, Integer visionRange) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defencePoints, experienceValue);
        this.visionRange = visionRange;
    }

    @Override
    public void GameTick() {
        //TODO
    }
}
