package Tiles.Units.Players;

import Tiles.Point;
import Tiles.Units.HeroicUnit;

public class Warrior extends Player implements HeroicUnit
{
    private Integer AbilityCooldown;
    private Integer RemainingCooldown;

    public Warrior(Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experience, Integer level, Integer abilitycooldown) {
        super(position, name, healthPool, healthAmount, attackPoints, defencePoints, experience, level);
        AbilityCooldown = abilitycooldown;
        RemainingCooldown = 0;
    }

    public void LevelUp()
    {
        super.LevelUp();
        RemainingCooldown = 0;
        health.AddPool(5*level);
        attackPoints = attackPoints + (2*level);
        defencePoints = defencePoints + level;
    }

    @Override
    public void GameTick() {
        RemainingCooldown--;
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

    @Override
    public void CastAbility() {
        //TODO
    }
}