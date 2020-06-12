package Tiles.Units.Players;

import Tiles.Board;
import Tiles.Point;
import Tiles.Units.HeroicUnit;
import Tiles.Units.PoolInteger;

public class Mage extends Player implements HeroicUnit {

    PoolInteger mana;
    Integer manaCost;
    Integer spellPower;
    Integer hitsCount;
    Integer abilityRange;

    public Mage(Point position, String name, Integer healthPool, Integer attackPoints, Integer defencePoints,Integer manapool, Integer manaCost, Integer spellPower, Integer hitsCount, Integer abilityRange) {
        super(position, name, healthPool, attackPoints, defencePoints);
        mana=new PoolInteger(manapool,manapool/4);
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }

    public void LevelUp() {
        super.LevelUp();
        mana.AddPool(25*level);
        mana.ChangeAmount(mana.getPool()/4);
        spellPower = spellPower + (10*level);
    }

    @Override
    public void GameTick(Player p,Board board) {
        mana.ChangeAmount(level);
    }

    @Override
    public void CastAbility(Board board) {
        //TODO
    }

    //------------------getters-----------------------

}
