package Tiles;

import Tiles.Units.Enemies.Boss;
import Tiles.Units.Enemies.Enemy;
import Tiles.Units.Enemies.Monster;
import Tiles.Units.Enemies.Trap;
import Tiles.Units.Players.Player;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Tile[][] tiles;
    private List<Enemy> enemies;
    private Player player;
    private  Point playerPos;

    public Board(char[][] tiles, Player player) {
        this.enemies = new ArrayList<Enemy>() ;
        this.player = player;
        this.tiles = new Tile[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                this.tiles[i][j]=ConvertCharToTiles(tiles[i][j],new Point(i,j));
            }
        }
    }
    public Board(char[][] tiles,Point playerPosition) {
        this.enemies = new ArrayList<Enemy>() ;
        this.playerPos = playerPosition;
        this.tiles = new Tile[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                this.tiles[i][j]=ConvertCharToTiles(tiles[i][j],new Point(i,j));
            }
        }
    }

    private Tile ConvertCharToTiles(char c,Point position) {
        Enemy e=null;
        switch(c){
            case '@':
                player.position=position;
                return player;
            case '#':
                return new Wall(position);
            case '.':return new Empty(position);
            case 's':e=new Monster(c,position,"Lannister Solider",80,80,8,3,25,3);
            break;
            case 'k':e=new Monster(c,position,"Lannister Knight",200,200,14,8,50,4);
                break;
            case 'q':e=new Monster(c,position,"Queen’s Guard",400,400,20,15,100,5);
                break;
            case 'z':e=new Monster(c,position,"Wright",600,600,30,15,100,3);
                break;
            case 'b':e=new Monster(c,position,"Bear-Wright",1000,1000,75,30,250,4);
                break;
            case 'g':e=new Monster(c,position,"Giant-Wright",1500,1500,100,45,500,5);
                break;
            case 'w':e=new Monster(c,position,"White Walker",2000,2000,150,50,1000,6);
                break;
            case 'M':e=new Boss(c,position,"The Mountain ’",1000,1000,60,25,500,6);
                break;
            case 'C':e=new Boss(c,position,"Queen Cersei",100,100,10,10,1000,1);
                break;
            case 'K':e=new Boss(c,position,"Night’s King",5000,5000,300,150,5000,8);
                break;
            case 'B':e=new Trap(1,5,c,position,"Bonus Trap",1,1,1,1,250);
                break;
            case 'Q':e=new Trap(3,7,c,position,"Queen's Trap",250,250,250,20,100);
                break;
            case 'D':e=new Trap(1,10,c,position,"Death Trap",500,500,100,20,250);
                break;
        }
        if(e!=null)
            enemies.add(e);
        return e;
    }

    public Tile GetTileDirection(Point p, Character c) {
        switch(c)
        {
            case 'd': return tiles[p.getX()][p.getY()+1];
            case 's': return tiles[p.getX()-1][p.getY()];
            case 'a': return tiles[p.getX()][p.getY()-1];
            case 'w': return tiles[p.getX()+1][p.getY()];
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

    public List<Enemy> getEnemies(Point p,int range) {
        List<Enemy> l=new ArrayList<Enemy>();
        for (Enemy e:enemies)
            if(p.Range(e.getPosition())<range)
                l.add(e);
        return  l;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void ReplacePlayer(Player p){
        p.position=playerPos;
        player=p;
        tiles[p.position.getX()][p.position.getY()]=player;
    }
}
