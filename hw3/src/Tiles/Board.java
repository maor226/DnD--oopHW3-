package Tiles;

import Tiles.Units.Enemies.Enemy;
import Tiles.Units.Players.Player;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.util.List;

public class Board {
    private Tile[][] tiles;

    public Board(Tile[][] tiles)
    {
        this.tiles=tiles;
    }

    public Tile GetTileDirection(Point p, Character c) {
        switch(c)
        {
            case 'w': return tiles[p.getX()][p.getY()+1];
            case 'a': return tiles[p.getX()-1][p.getY()];
            case 's': return tiles[p.getX()][p.getY()-1];
            case 'd': return tiles[p.getX()+1][p.getY()];
            case 'q': return null;
        }
        return null;
    }

    public void Switch(Tile t1,Tile t2) /**switches between to tiles**/
    {
        tiles[t1.getPosition().getX()][t1.getPosition().getY()]=t2;
        tiles[t2.getPosition().getX()][t2.getPosition().getY()]=t1;
    }

    public Tile[][] getTiles() { return tiles;}

    public boolean possibleMove(int x,int y) /**checking you're not trying to move outside bounds**/
    {
        if(x>tiles[0].length || x<0)
            return false;
        else if (y>tiles[1].length || y<0)
            return false;
        return true;
    }

    public void DeadUnit(Point p)
    {
        tiles[p.getX()][p.getY()] = new Empty(new Point(p.getX(),p.getY()));
    }

    public void DeadPlayer(Point p) {
        tiles[p.getX()][p.getY()].setTile('X');
    }

    public enum Direction{
        left('a'),
        right('d'),
        up('w'),
        down('s'),
        NONE('q');

        Direction(char a) {
        }
    }
}
