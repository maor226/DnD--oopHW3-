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
    public String GetInfo() {
        return super.GetInfo()+"Mana: "+mana+" spellPower: "+spellPower;
    }

    @Override
    public void CastAbility(Board board) {
        if(mana.getAmount()<manaCost) {
            NotifyObserver("Can not Cast an ability at the moment" +
                    "\n you need more "+(manaCost-mana.getAmount())+" mana");
        } else{
            mana.ChangeAmount(-manaCost);
            List<Enemy> enemies =board.getEnemies(position,abilityRange);
            IsSpellAttack=true;
            int hit = 0;
            if(enemies.size()>0){
                for (; hit < hitsCount&&enemies.size()>0; hit++) {
                    Enemy e=enemies.get(Math.toIntExact(Math.round(Math.random() * (enemies.size()-1))));
                    enemies.remove(e);
                    Attack(e);
                }
            }
            IsSpellAttack=false;
            NotifyObserver(name + " casted his ability and hit "+hit);
        }
    }

    @Override
    public void Attack(Enemy e) {
        int attack=(IsSpellAttack? spellPower:rollAttack()),defence=e.rollDefence();
        e.Hit(attack-defence);
        NotifyObserver(getName()  + " attacked " +e.getName() + " you rolled " + attack + " attack points and " + e.getName() + "rolled " + defence +" defence, so you hit for "+ (attack-defence));
    }

    //------------------getters-----------------------

}
