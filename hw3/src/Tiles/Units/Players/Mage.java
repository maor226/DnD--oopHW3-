package Tiles.Units.Players;

import Tiles.Point;
import java.awt.*;

public class Mage extends Player  {

    Integer manaPool;
    Integer currentMana;
    Integer manaCost;
    Integer spellPower;
    Integer hitsCount;
    Integer abilityRange;

    public Mage(Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experience, Integer level, Integer manapool, Integer spellpower) {
        super(position, name, healthPool, healthAmount, attackPoints, defencePoints, experience, level);
        manaPool = manapool;
        currentMana = (manapool/4);
        spellPower = spellpower;
    }

    public void LevelUp()
    {
        super.LevelUp();
        manaPool = manaPool+(25*level);
        currentMana = Math.min(currentMana+(manaPool/4),manaPool);
        spellPower = spellPower + (10*level);
    }

    @Override
    public void GameTick() {
        currentMana = Math.min(currentMana + level,manaPool);
    }

    //------------------getters-----------------------

}
