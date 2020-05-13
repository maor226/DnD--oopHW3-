package Tiles.Units.Players;

import Tiles.Point;

import java.awt.*;

public class Warrior extends Player
{
    private Integer AbilityCooldown;
    private Integer RemainingCooldown;

    public Warrior(Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experience, Integer level, Integer abilitycooldown) {
        super(position, name, healthPool, healthAmount, attackPoints, defencePoints, experience, level);
        AbilityCooldown = abilitycooldown;
    }

    public void LevelUp()
    {
        super.LevelUp();
        RemainingCooldown = 0;
        healthPool = healthPool + (5*level);
        attackPoints = attackPoints + (2*level);
        defencePoints = defencePoints + level;
    }



//------------------getters--------------------------
    public Integer getAbilityCooldown() {
        return AbilityCooldown;
    }
    public Integer getRemainingCooldown() {
        return RemainingCooldown;
    }

    @Override
    public void GameTick() {
        RemainingCooldown--;
    }
}