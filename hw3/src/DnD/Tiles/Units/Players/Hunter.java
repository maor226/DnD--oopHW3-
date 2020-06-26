package DnD.Tiles.Units.Players;

import DnD.Tiles.Board;
import DnD.Tiles.Point;
import DnD.Tiles.Units.Enemies.Enemy;
import DnD.Tiles.Units.HeroicUnit;

import java.util.List;

public class Hunter extends Player implements HeroicUnit {

    private Integer range;
    private Integer arrowsCount;
    private Integer ticksCount;
    private boolean IsAttackkPointsAttack=false;

    public Hunter(Point position, String name, Integer healthPool, Integer attackPoints, Integer defencePoints, Integer range) {
        super(position, name, healthPool, attackPoints, defencePoints);
        this.range = range;
        ticksCount=0;
        arrowsCount=10;
    }

    public void LevelUp(){
        super.LevelUp();
        arrowsCount+=10*level;
        attackPoints+=2*level;
        defencePoints+=level;
    }

    @Override
    public void GameTick() {
        if(ticksCount == 10)
        {
            arrowsCount += level;
            ticksCount=0;
        }
        else
        {
            ticksCount++;
        }
    }

    @Override
    public String GetInfo() {
        return super.GetInfo()+" Range: "+range+" Arrows: "+arrowsCount;
    }

    @Override
    public void CastAbility(Board board) {
       if(arrowsCount==0) NotifyObserver("Can not Cast an ability at the moment\nyou have 0 arrows\r you can cast in:\t"+(10-ticksCount));
       else{
           List<Enemy> enemies =board.getEnemies(position,range);
           if(enemies.size()==0)
               NotifyObserver("Can not Cast an ability at the moment\nyou have no enemies in range "+(range));
           else{
               Enemy closest=enemies.get(0);
               for (Enemy e:enemies) {
                   if(position.Range(e.getPosition())<position.Range(closest.getPosition()))
                       closest=e;
               }
               IsAttackkPointsAttack=true;
               Attack(closest);
               IsAttackkPointsAttack=false;
               NotifyObserver(name + " casted his ability");
           }
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
