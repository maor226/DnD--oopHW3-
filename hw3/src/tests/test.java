package tests;

import DnD.Tiles.Board;
import DnD.Tiles.Empty;
import DnD.Tiles.Point;
import DnD.Tiles.Tile;
import DnD.Tiles.Units.Enemies.Enemy;
import DnD.Tiles.Units.Enemies.Monster;
import DnD.Tiles.Units.Players.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class test {

    Board b;
    Player p;
    Enemy e;
    Tile t1;
    Tile t2;
    @Before
    public void StartUp()
    {
         p =new Player(new Point(1,0), "testChar", 100, 100, 100) {
            @Override
            public void GameTick() { }
            @Override
            public void CastAbility(Board board) { }
        };
        t1 = new Empty(new Point(0,0));
        t2 = new Empty(new Point(1,1));
        e = new Monster('q',new Point(0,1),"testMon",100, 100,100,100,100);
        Tile[][] tiles = new Tile[][]{{t1,e},{p,t2}};
        b = new Board(p,tiles,e);
    }

    @Test
    public void SwitchTest()
    {
        b.Switch(p,e);
        Assert.assertSame(p, b.getTiles()[0][1]);
        Assert.assertSame(e, b.getTiles()[1][0]);
    }

    @Test
    public void GetTileDirectionTest()
    {
        Tile t = b.GetTileDirection(p.getPosition(),'d');
        Assert.assertSame(t, t2);
        t = b.GetTileDirection(p.getPosition(),'w');
        Assert.assertSame(t, t1);
        t = b.GetTileDirection(t.getPosition(),'d');
        Assert.assertSame(t, e);
    }

    @Test
    public void PossibleMoveTest()
    {
        Assert.assertFalse(b.possibleMove(3,3));
        Assert.assertFalse(b.possibleMove(3,1));
        Assert.assertFalse(b.possibleMove(1,3));

        Assert.assertTrue(b.possibleMove(1,1));
        Assert.assertTrue(b.possibleMove(1,0));
    }
}
