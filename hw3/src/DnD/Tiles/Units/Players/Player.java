package DnD.Tiles.Units.Players;

import DnD.Tiles.Point;
import DnD.Tiles.Units.Enemies.Enemy;
import DnD.Tiles.Units.HeroicUnit;
import DnD.Tiles.Units.Unit;

public abstract class Player extends Unit implements HeroicUnit {
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
        System.out.print("\033[0;32m"+tile+"\033[0m");
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

    public boolean accept(Enemy e){
        Attack(e);
        return false;
    }

    public void Attack(Enemy e)
    {
        int attack = rollAttack();
        int defence = e.rollDefence();
        e.Hit(attack-defence);
        NotifyObserver(getName() +" attacked " + e.getName() +" rolled attack: + "+attack+" and enemy rolled defence " + defence + " and you hit the enemy for " + (attack-defence));
    }

     public abstract void GameTick();

    public void Hit(Integer attack) {
        int hit=rollDefence()-attack;
        if(hit<0)health.ChangeAmount(hit);
    }

    public void gainExprerience(int exp)
    {
        if(experience + exp >= 50*level)
        {
            exp = exp - (50*level-experience);
            LevelUp();
            gainExprerience(exp);
        }
        experience+=exp;
    }

}