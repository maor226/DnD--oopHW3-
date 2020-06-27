package DnD.Tiles.Units.Players;

import DnD.Tiles.Board;
import DnD.Tiles.Point;
import DnD.Tiles.Units.Enemies.Enemy;
import DnD.Tiles.Units.HeroicUnit;

import java.util.List;

public class Warrior extends Player implements HeroicUnit
{
    private Integer abilityCooldown;
    private Integer remainingCooldown;

    public Warrior(Point position, String name, Integer healthPool, Integer attackPoints, Integer defencePoints, Integer abilitycooldown) {
        super(position, name, healthPool, attackPoints, defencePoints);
        abilityCooldown = abilitycooldown;
        remainingCooldown = 0;
    }

    public void LevelUp()
    {
        super.LevelUp();
        remainingCooldown = 0;
        health.AddPool(5*level);
        attackPoints = attackPoints + (2*level);
        defencePoints = defencePoints + level;
    }

    @Override
    public void GameTick() {
        remainingCooldown--;
    }

    @Override
    public String GetInfo() {
        return super.GetInfo()+" can Cast Ability in: "+remainingCooldown+" torn";
    }

    //------------------getters--------------------------
    public Integer getAbilityCooldown() {
        return abilityCooldown;
    }
    public Integer getRemainingCooldown() {
        return remainingCooldown;
    }

    @Override
    public void CastAbility(Board board) {
        if(remainingCooldown>0)
            NotifyObserver("Can not Cast an ability at the moment\nyou can cast in:\t"+remainingCooldown);
        else{
            remainingCooldown=abilityCooldown.intValue();
            health.ChangeAmount(10*rollDefence());
            List<Enemy> enemies =board.getEnemies(position,3);
            if(enemies.size()>0){
                enemies.get(Math.toIntExact(Math.round(Math.random() * (enemies.size()-1)))).Hit(health.getPool()/10);
            }
            NotifyObserver(name + " casted his ability");
        }
    }



}