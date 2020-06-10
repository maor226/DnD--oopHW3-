package Tiles.Units.Enemies;

import Tiles.Point;
import Tiles.Units.Unit;

import javax.swing.text.Position;

public abstract class Enemy extends Unit {
    protected Integer experienceValue;


    public Enemy(Character tile, Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experienceValue) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defencePoints);
        this.experienceValue = experienceValue;
    }

    @Override
    public void Print() {
        System.out.print("\033[0;31m"+tile+"\033[0m");
    }

    //getters and seters
    public Integer getExperienceValue() {
        return experienceValue;
    }


    public abstract int getVisionRange();
}
