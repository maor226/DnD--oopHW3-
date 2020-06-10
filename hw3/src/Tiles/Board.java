package Tiles;

import Tiles.Units.Enemies.Enemy;
import Tiles.Units.Players.Player;

import java.util.List;

public class Board {
    private Tile[][] tiles;

    public Board(Tile[][] tiles)
    {
        this.tiles=tiles;
    }

    public void Switch(Tile t1,Tile t2)
    {
        tiles[t1.getPosition().getX()][t1.getPosition().getY()]=t2;
        tiles[t2.getPosition().getX()][t2.getPosition().getY()]=t1;
    }

}
