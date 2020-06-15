package Tiles.Units.Players;

import Tiles.Board;
import Tiles.Point;
import Tiles.Units.Enemies.Enemy;
import Tiles.Units.Unit;

import javax.swing.text.Position;

public abstract class Player extends Unit {
    protected Integer experience=0;
    protected Integer level=1;

    public Player(Point position, String name, Integer healthPool, Integer attackPoints, Integer defencePoints) {
        super('@', position, name, healthPool, attackPoints, defencePoints);
    }

    public void LevelUp(){
        experience-=50*level;
        level++;
        health.AddPool(10*level);
        health.setAmount(health.getPool());
        attackPoints+=4*level;
        defencePoints+=level;
        NotifyObserver(getName() + " Leveled up! up to level "+ level +" now! congratulation!");
    }

    @Override
    public void Print(){
        System.out.print("\033[0;32m"+tile+"\033[0m");;
    }

    /**----------------------getters and setters-----------------------------**/
    public Integer getExperience() {
        return experience;
    }

    public Integer getLevel() {
        return level;
    }

    public boolean accept(Unit u)
    {
        return u.accept(this);
    }

    public boolean accept(Enemy e)
    {
        Attack(e);
        return false;
    }

    public void Attack(Enemy e)
    {
        //todo
    }

}
