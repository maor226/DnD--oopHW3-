package Tiles.Units.Players;

import Tiles.Board;
import Tiles.Point;
import Tiles.Units.HeroicUnit;

import javax.swing.text.Position;

public class Rogue extends Player implements HeroicUnit {

    private Integer cost;
    private Integer currentEnergy;

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
    public void GameTick(Player p,Board board) {
        currentEnergy = Math.min(currentEnergy+10,100);
    }

    @Override
    public void CastAbility(Board board) {
        //TODO
    }

    //------------------getters-----------------------
}
