package DnD.Tiles;

import DnD.Tiles.Units.Unit;

public class Empty extends Tile {
    public static char EmptyTile='.';

    public Empty( Point position) {
        super(EmptyTile, position);
    }

    public boolean accept(Unit u)
    {
        return true;
    }

}
