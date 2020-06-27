package DnD.Tiles.Units.Players;

import DnD.Tiles.Board;
import DnD.Tiles.Point;
import DnD.Tiles.Units.Enemies.Enemy;
import DnD.Tiles.Units.HeroicUnit;

import java.util.List;

public class Rogue extends Player implements HeroicUnit {

    private Integer cost;
    private Integer currentEnergy;
    private boolean IsAttackkPointsAttack=false;

    public Rogue(Point position, String name, Integer healthPool, Integer attackPoints, Integer defencePoints, Integer cost) {
        super(position, name, healthPool, attackPoints, defencePoints);
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

    @Override
    public String GetInfo() {
        return super.GetInfo()+" energy: "+currentEnergy;
    }

    @Override
    public void CastAbility(Board board) {
        if(currentEnergy<cost)
            NotifyObserver("Can not Cast an ability at the moment\nyou need more "+(currentEnergy-cost)+" energy");
        else{
            currentEnergy-=cost;
            List<Enemy> enemies =board.getEnemies(position,2);
            IsAttackkPointsAttack=true;
            if(enemies.size()>0){
                for (Enemy e:enemies) {
                    Attack(e);
                }
            }
            IsAttackkPointsAttack=false;
            NotifyObserver(name + " casted his ability and hit "+enemies.size()+" enemies around him");
        }
    }

    @Override
    public void Attack(Enemy e) {
        int attack=(IsAttackkPointsAttack? attackPoints:rollAttack()),defence=e.rollDefence();
        e.Hit(attack-defence);
        NotifyObserver(getName()  + " attacked " +e.getName() + " you rolled " + attack + " attack points and " + e.getName() + "rolled " + defence +" defence, so you hit for "+ (attack-defence));
    }

    //------------------getters-----------------------
}
