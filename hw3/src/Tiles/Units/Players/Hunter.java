package Tiles.Units.Players;

import Tiles.Board;
import Tiles.Point;
import Tiles.Units.HeroicUnit;

import javax.swing.text.Position;

public class Hunter extends Player implements HeroicUnit {

    private Integer range;
    private Integer arrowsCount;
    private Integer ticksCount;

    public Hunter(Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experience, Integer level, Integer range) {
        super(position, name, healthPool, healthAmount, attackPoints, defencePoints, experience, level);
        this.range = range;
        ticksCount=0;
    }

    public void LevelUp(){
        super.LevelUp();
        arrowsCount+=10*level;
        attackPoints+=2*level;
        defencePoints+=level;
    }

    @Override
    public void GameTick(Player p) {
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
    public void CastAbility(Board board) {
        //TODO
    }


    //------------------getters-----------------------
}
