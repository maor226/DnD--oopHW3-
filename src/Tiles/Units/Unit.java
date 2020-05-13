package Tiles.Units;

import Tiles.Point;
import Tiles.Tile;

public abstract class Unit extends Tile {
    protected String name;
    protected Integer healthPool;
    protected Integer healthAmount;
    protected Integer attackPoints;
    protected Integer defencePoints;

    public Unit(Character tile, Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints) {
        super(tile, position);
        this.name = name;
        this.healthPool = healthPool;
        this.healthAmount = healthAmount;
        this.attackPoints = attackPoints;
        this.defencePoints = defencePoints;
    }

    public abstract void GameTick();

    //geters and seters

    public String getName() {
        return name;
    }

    public Integer getHealthPool() {
        return healthPool;
    }

    public Integer getHealthAmount() {
        return healthAmount;
    }

    public Integer getAttackPoints() {
        return attackPoints;
    }

    public Integer getDefencePoints() {
        return defencePoints;
    }
}
