package Tiles;

import Tiles.Units.Enemies.Monster;
import Tiles.Units.Unit;

public class Empty extends Tile {
    public Empty( Point position) {
        super('.', position);
    }

    public boolean accept(Unit u)
    {
        return true;
    }

}
