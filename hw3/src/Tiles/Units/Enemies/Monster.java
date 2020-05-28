package Tiles.Units.Enemies;

import Tiles.Point;

public class Monster extends Enemy {
<<<<<<< HEAD
    Integer visionRange;

    public Monster(Integer visionrange, Character tile, Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experienceValue)
    {
        super(tile,position,name,healthPool,healthAmount,attackPoints,defencePoints,experienceValue);
        visionRange=visionrange;
=======

    protected Integer visionRange;

    public Monster(Character tile, Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experienceValue, Integer visionRange) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defencePoints, experienceValue);
        this.visionRange = visionRange;
>>>>>>> 271c39df42e3669a2c7d93e3e28cf59a39101637
    }

    @Override
    public void GameTick() {
<<<<<<< HEAD
        //todo
=======
        //TODO
>>>>>>> 271c39df42e3669a2c7d93e3e28cf59a39101637
    }
}
