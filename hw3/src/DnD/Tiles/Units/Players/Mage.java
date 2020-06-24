package DnD.Tiles.Units.Players;

import DnD.Tiles.Board;
import DnD.Tiles.Point;
import DnD.Tiles.Units.Enemies.Enemy;
import DnD.Tiles.Units.HeroicUnit;
import DnD.Tiles.Units.PoolInteger;

import java.util.List;

public class Mage extends Player implements HeroicUnit {

    PoolInteger mana;
    Integer manaCost;
    Integer spellPower;
    Integer hitsCount;
    Integer abilityRange;
    boolean IsSpellAttack=false;

    public Mage(Point position, String name, Integer healthPool, Integer attackPoints, Integer defencePoints
            ,Integer manaPool, Integer manaCost, Integer spellPower, Integer hitsCount, Integer abilityRange) {
        super(position, name, healthPool, attackPoints, defencePoints);
        mana=new PoolInteger(manaPool,manaPool/4);
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
    public void GameTick() {
        mana.ChangeAmount(level);
    }

    @Override
    public boolean CastAbility(Board board) {
        if(mana.getAmount()<manaCost)
            NotifyObserver("Can not Cast an ability at the moment" +
                    "\n you need more "+(manaCost-mana.getAmount())+" mana");
        else{
            mana.ChangeAmount(-manaCost);
            List<Enemy> enemies =board.getEnemies(position,abilityRange);
            IsSpellAttack=true;
            if(enemies.size()>0){
                for (int hit = 0; hit < hitsCount; hit++) {
                    Attack(enemies.get(Math.toIntExact(Math.round(Math.random() * enemies.size()))));
                }
            }
            IsSpellAttack=false;
            return true;
        }
        return false;
    }

    @Override
    public void Attack(Enemy e) {
        e.Hit((IsSpellAttack? spellPower:rollAttack())-e.rollDefence());
    }

    //------------------getters-----------------------

}
