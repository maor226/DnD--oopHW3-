package DnD.Tiles;

import DnD.Tiles.Units.Unit;

public class Wall extends Tile {

    public Wall(Point position) {
        super('#', position);
    }

    public boolean accept(Unit u)
    {
        return false;
    }

}
