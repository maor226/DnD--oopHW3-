package DnD.Tiles;

import DnD.Tiles.Units.Enemies.Enemy;
import DnD.Tiles.Units.Players.Player;
import DnD.Tiles.Units.Unit;

public abstract class Tile {
    protected Character tile;
    protected Point position;

    public Tile(Character tile, Point position) {
        this.tile = tile;
        this.position = position;
    }

   public void Print(){
       System.out.print(getTile());
   }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position){this.position=position;}

    public void setTile(Character tile)
    {
        this.tile=tile;
    }

    public abstract boolean accept(Unit u);
    public boolean accept(Player p){return false;};
    public boolean accept(Enemy e)
    {
        return false;
    }

    public Character getTile() {
        return tile;
    }
}
