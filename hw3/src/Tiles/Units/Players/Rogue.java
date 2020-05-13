package Tiles.Units.Players;

import Tiles.Point;

public class Rogue extends Player {

    private Integer cost;
    private Integer currentEnergy;

    public Rogue(Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experience, Integer level, Integer cost) {
        super(position, name, healthPool, healthAmount, attackPoints, defencePoints, experience, level);
        this.cost = cost;
        currentEnergy = 100;
    }

    public void LevelUp()
    {
        super.LevelUp();
        currentEnergy =100;
        attackPoints = attackPoints + (3*level);
    }

    @Override
    public void GameTick() {
        currentEnergy = Math.min(currentEnergy+10,100);
    }

    //------------------getters-----------------------
}