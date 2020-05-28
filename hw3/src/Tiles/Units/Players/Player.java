package Tiles.Units.Players;

import Tiles.Board;
import Tiles.Point;
import Tiles.Units.Unit;

public abstract class Player extends Unit {
    protected Integer experience=0;
    protected Integer level=1;

    public Player(Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experience, Integer level) {
        super('@', position, name, healthPool, healthAmount, attackPoints, defencePoints);
        this.experience = experience;
        this.level = level;
    }

    public void LevelUp(){
        experience-=50*level;
        level++;
        health.AddPool(10*level);
        health.setAmount(health.getPool());
        attackPoints+=4*level;
        defencePoints+=level;
    }
    public enum Direction{
        left('a'),
        right('d'),
        up('w'),
        down('s'),
        NONE('q');

        Direction(char a) {
        }
    }
    public void MovePlayer(Board board,Direction d){
        //todo
    }

    @Override
    public void Print(){
        System.out.print("\033[0;32m"+tile+"\033[0m");;
    }

    //geters and seters
    public Integer getExperience() {
        return experience;
    }

    public Integer getLevel() {
        return level;
    }

    public abstract void GameTick();
}
