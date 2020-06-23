package Tiles.Units.Enemies;

import Tiles.Board;
import Tiles.Empty;
import Tiles.Point;
import Tiles.Units.Players.Player;
import Tiles.Units.Unit;

public class Trap extends Enemy {
    private Integer visibilityTime;
    private Integer invisibilityTime;
    private Integer ticksCount;
    private boolean visible;

    public Trap(Integer visibilitytime, Integer invisibilitytime, Character tile, Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experienceValue) {
        super(tile,position,name,healthPool,healthAmount,attackPoints,defencePoints,experienceValue);
        visibilityTime=visibilitytime;
        invisibilityTime=invisibilitytime;
        ticksCount=0;
        visible=true;
    }

    @Override
    public void GameTick(Player p, Board board) {
        visible = (ticksCount<visibilityTime);
        if(ticksCount==(visibilityTime+invisibilityTime))
            ticksCount=0;
        else
            ticksCount++;

        if (position.Range(p.getPosition())<2)
        {
            int defence = p.rollDefence();
            int attack = rollAttack();
            String s;
            if(defence-attack<0)
            {
                p.getHealth().ChangeAmount(-(defence-attack));
                s = "" + getName() + " attacked " +p.getName()+", "+p.getName()+" rolled: "+defence +" defence, and "+getName() + " rolled: "
                            + attack + " attack. " +p.getName() + " lost " + (defence-attack) + " life points";
            }
            else
            {
                s = "" + getName() + " attacked " +p.getName()+", "+p.getName()+" rolled: "+defence +" defence, and "+getName() + " rolled: "
                        + attack + " attack. " +p.getName() + " lost 0 life points";
            }
            NotifyObserver(s);
        }

    }

    @Override
    public int getVisionRange() {
        return 0;
    }

    public boolean accept(Unit u)
    {
        return false;
    }

    @Override
    public Character getTile(){
        if(visible)return super.getTile();
        else return Empty.EmptyTile;
    }
}
