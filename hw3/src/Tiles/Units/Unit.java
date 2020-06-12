package Tiles.Units;
import Tiles.Board;
import Tiles.Point;
import Tiles.Tile;
import Tiles.Units.Players.Player;

import javax.swing.text.Position;

public abstract class Unit extends Tile implements Observable {
    protected String name;
    protected PoolInteger health;
    protected Integer attackPoints;
    protected Integer defencePoints;
    protected Observer observer;

    public Unit(Character tile, Point position, String name, Integer healthPool, Integer attackPoints, Integer defencePoints) {
        super(tile, position);
        this.name = name;
        health=new PoolInteger(healthPool,healthPool);
        this.attackPoints = attackPoints;
        this.defencePoints = defencePoints;
    }
    public Integer rollAttack()
    {
        return (int)(Math.random()*attackPoints);
    }

    public Integer rollDefence() { return (int)(Math.random()*defencePoints);}

    public abstract void GameTick(Player p, Board board);

    //geters and seters
    public String getName() {
        return name;
    }
  
    public PoolInteger getHealth() {
        return health;
    }

    public Integer getAttackPoints() {
        return attackPoints;
    }

    public Integer getDefencePoints() {
        return defencePoints;
    }

    public boolean Interact(Tile t)
    {
        //todo
        return false;
    }

    /**---------------------observer pattern section------------------------------------**/
    public void NotifyObserver(String s)
    {
        observer.printMessage(s);
    }

    public void setObserver(Observer o)
    {
        observer = o;
    }


}
