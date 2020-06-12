package Tiles.Units.Players;

import Tiles.Board;
import Tiles.Point;
import Tiles.Units.HeroicUnit;

import javax.swing.text.Position;

public class Warrior extends Player implements HeroicUnit
{
    private Integer AbilityCooldown;
    private Integer RemainingCooldown;

    public Warrior(Point position, String name, Integer healthPool, Integer attackPoints, Integer defencePoints, Integer abilitycooldown) {
        super(position, name, healthPool, attackPoints, defencePoints);
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
    public void GameTick(Player p,Board board) {
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
    public void CastAbility(Board board) {
        //TODO
    }
}