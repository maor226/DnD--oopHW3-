package Tiles.Units.Players;

import Tiles.Board;
import Tiles.Point;
import Tiles.Units.Enemies.Enemy;
import Tiles.Units.HeroicUnit;
import org.w3c.dom.ranges.Range;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;

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

//------------------getters--------------------------
    public Integer getAbilityCooldown() {
        return abilityCooldown;
    }
    public Integer getRemainingCooldown() {
        return remainingCooldown;
    }

    @Override
    public boolean CastAbility(Board board) {
        if(remainingCooldown>0)
            NotifyObserver("Can not Cast an ability at the moment\nyou can cast in:\t"+remainingCooldown);
        else{
            remainingCooldown=abilityCooldown.intValue();
            health.ChangeAmount(10*rollDefence());
            List<Enemy> enemies =board.getEnemies(position,3);
            if(enemies.size()>0){
                enemies.get(Math.toIntExact(Math.round(Math.random() * enemies.size()))).Hit(health.getPool()/10);
                return true;
            }
        }
        return false;
    }


}