package Tiles.Units.Players;

import Tiles.Board;
import Tiles.Point;
import Tiles.Units.HeroicUnit;
import Tiles.Units.PoolInteger;

import javax.swing.text.Position;

public class Mage extends Player implements HeroicUnit {

    PoolInteger mana;
    Integer manaCost;
    Integer spellPower;
    Integer hitsCount;
    Integer abilityRange;

    public Mage(Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experience, Integer level,Integer manapool, Integer manaCost, Integer spellPower, Integer hitsCount, Integer abilityRange) {
        super(position, name, healthPool, healthAmount, attackPoints, defencePoints, experience, level);
        mana=new PoolInteger(manapool,manapool/4);
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }

    public void LevelUp() {
        super.LevelUp();
        mana.AddPool(25*level);
        mana.AddAmount(mana.getPool()/4);
        spellPower = spellPower + (10*level);
    }

    @Override
    public void GameTick(Player p) {
        mana.AddAmount(level);
    }

    @Override
    public void CastAbility(Board board) {
        //TODO
    }

    //------------------getters-----------------------

}
