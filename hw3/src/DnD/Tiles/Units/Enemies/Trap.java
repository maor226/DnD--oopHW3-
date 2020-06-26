package DnD.Tiles.Units.Enemies;

import DnD.Tiles.Board;
import DnD.Tiles.Empty;
import DnD.Tiles.Point;
import DnD.Tiles.Units.Players.Player;
import DnD.Tiles.Units.Unit;

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

        if (p!=null && position.Range(p.getPosition())<2)
        {
           p.Hit(rollAttack(),getName());
        }

    }

    @Override
    public int getVisionRange() {
        return 2;
    }


    @Override
    public boolean accept(Player p) {
        p.Attack(this);
        return getHealth().isZero();
    }

    @Override
    public Character getTile(){
        if(visible)return super.getTile();
        else return Empty.EmptyTile;
    }

}
