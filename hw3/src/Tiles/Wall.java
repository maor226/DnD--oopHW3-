package Tiles;

import Tiles.Units.Enemies.Monster;
import Tiles.Units.Unit;

public class Wall extends Tile {

    public Wall(Point position) {
        super('#', position);
    }

    public boolean accept(Unit u)
    {
        return false;
    }

}
