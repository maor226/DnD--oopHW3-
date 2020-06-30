package DnD.Tiles;

import DnD.Tiles.Units.Enemies.Boss;
import DnD.Tiles.Units.Enemies.Enemy;
import DnD.Tiles.Units.Enemies.Monster;
import DnD.Tiles.Units.Enemies.Trap;
import DnD.Tiles.Units.Observer;
import DnD.Tiles.Units.Players.Player;

import java.util.*;

public class Board {
        private Tile[][] tiles;
        private List<Enemy> enemies;
        private Player player;
        private Point playerPos;

        public Board(char[][] tiles, Player player,Observer o)
        {
            this.enemies = new ArrayList<Enemy>() ;
            this.player = player;
            this.tiles = new Tile[tiles.length][tiles[0].length];
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    this.tiles[i][j]=ConvertCharToTiles(tiles[i][j],new Point(i,j),o);
                }
            }
        }
        public Board(char[][] tiles,Observer o) {
            this.enemies = new ArrayList<Enemy>() ;
            this.tiles = new Tile[tiles.length][tiles[0].length];
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    this.tiles[i][j]=ConvertCharToTiles(tiles[i][j],new Point(i,j),o);
                }
            }
        }

        private Tile ConvertCharToTiles(char c,Point position,Observer observer) {
            Enemy e=null;
            switch(c){
                case '@':
                    if(player!=null) {
                        player.position = position;
                        player.setObserver(observer);
                    }
                    else
                        this.playerPos = position;
                    return player;
                case '#':
                    return new Wall(position);
                case '.':
                    return new Empty(position);
                //new Enemy so need to be Added to the enemy list
                case 's':e=new Monster(c,position,"Lannister Solider",80, 8,3,25,3);
                    break;
                case 'k':e=new Monster(c,position,"Lannister Knight",200, 14,8,50,4);
                    break;
                case 'q':e=new Monster(c,position,"Queen’s Guard",400, 20,15,100,5);
                    break;
                case 'z':e=new Monster(c,position,"Wright",600, 30,15,100,3);
                    break;
                case 'b':e=new Monster(c,position,"Bear-Wright",1000, 75,30,250,4);
                    break;
                case 'g':e=new Monster(c,position,"Giant-Wright",1500, 100,45,500,5);
                    break;
                case 'w':e=new Monster(c,position,"White Walker",2000, 150,50,1000,6);
                    break;
                case 'M':e=new Boss(c,position,"The Mountain",1000, 60,25,500,6,5);
                    break;
                case 'C':e=new Boss(c,position,"Queen Cersei",100, 10,10,1000,1,5);
                    break;
                case 'K':e=new Boss(c,position,"Night’s King",5000, 300,150,5000,8,5);
                    break;
                case 'B':e=new Trap(1,5,c,position,"Bonus Trap",1, 1,1,250);
                    break;
                case 'Q':e=new Trap(3,7,c,position,"Queen's Trap",250, 50,10,100);
                    break;
                case 'D':e=new Trap(1,10,c,position,"Death Trap",500, 100,20,250);
                    break;
            }
            if(e!=null){
                enemies.add(e);
                e.setObserver(observer);
            }
            return e;
        }

        public Tile GetTileDirection(Point p, Character c) {
            switch(c)
            {
                case 'd': return tiles[p.getX()][p.getY()+1];
                case 's': return tiles[p.getX()+1][p.getY()];
                case 'a': return tiles[p.getX()][p.getY()-1];
                case 'w': return tiles[p.getX()-1][p.getY()];
                case 'q': return null;
            }
            return null;
        }

        public void Switch(Tile t1,Tile t2) /**switches between to tiles**/
        {
            Tile tmp = tiles[t1.getPosition().getX()][t1.getPosition().getY()];
            tiles[t1.getPosition().getX()][t1.getPosition().getY()] =tiles[t2.getPosition().getX()][t2.getPosition().getY()];
            tiles[t2.getPosition().getX()][t2.getPosition().getY()]=tmp;
        }

        public Tile[][] getTiles() { return tiles;}

        public boolean possibleMove(int x,int y) /**checking you're not trying to move outside bounds**/
        {
            if(x>=tiles.length || x<0)
                return false;
            else if (y>=tiles[0].length || y<0)
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
            p.setPosition(playerPos);
            player=p;
            tiles[p.getPosition().getX()][p.getPosition().getY()]=player;
        }

        public void PlayerTick(Character c)
        {
            boolean movable=false;
            Direction direction=getDirection(c),reDirection=Direction.None;
            switch(direction)
            {
                case Right: movable = possibleMove(getPlayer().getPosition().getX(),getPlayer().getPosition().getY()+1);
               reDirection=Direction.Left;
                    break;
                case Down: movable = possibleMove(getPlayer().getPosition().getX()+1,getPlayer().getPosition().getY());
                reDirection=Direction.Up;
                    break;
                case Left: movable = possibleMove(getPlayer().getPosition().getX(),getPlayer().getPosition().getY()-1);
                reDirection=Direction.Right;
                    break;
                case Up: movable = possibleMove(getPlayer().getPosition().getX()-1,getPlayer().getPosition().getY());
                reDirection=Direction.Down;
                    break;
                case None: movable = false;
                    break;
                case Cast: getPlayer().CastAbility(this);
                    break;
            }
            if (movable) {
                Tile t=GetTileDirection(getPlayer().getPosition(), c);
                if(getPlayer().Interact(t)){
                    Switch(player,t);
                    player.position.Move(direction);
                    t.position.Move(reDirection);
                }
            }
            getPlayer().GameTick();
        }
        public void EnemyTick()
        {
            for (int i=0;i<enemies.size();i++) {
                Enemy e = enemies.get(i);
                if (e.getHealth().isZero()) {
                    player.NotifyObserver("" + player.getName() + " gained " + e.getExperienceValue() + " experience points");
                    player.gainExperience(e.getExperienceValue());
                    enemies.remove(e);
                    DeadUnit(e.position);
                    i--;
                }
            }
            for (Enemy e : getEnemies())
                if (e.getPosition().Range(getPlayer().getPosition())<e.getVisionRange())
                    e.GameTick(getPlayer(),this);
                else
                    e.GameTick(null,this);
            if(getPlayer().getHealth().isZero())
                DeadPlayer(getPlayer().getPosition());
        }

        private static Direction getDirection(char c){
            switch (c) {
                case 's':
                    return Direction.Down;
                case 'a':
                    return Direction.Left;
                case 'w':
                    return Direction.Up;
                case 'd':
                    return Direction.Right;
                case 'e':
                    return Direction.Cast;
                case 'q':
                    return Direction.None;
            }
            return Direction.None;
        }

        public boolean isLevelFinished()
        {
            return (enemies.size()==0);
        }
        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<tiles.length;i++)
            {
                for(int j=0;j<tiles[i].length;j++)
                {

                    sb.append(tiles[i][j]!=null?tiles[i][j].getTile():"%");
                }
                sb.append("\n");
            }
            sb.append("\n");
           sb.append(player.GetInfo());
            return sb.toString();


        }

         /**constructor for testing**/
        public Board(Player p,Tile[][] tiles,Enemy e)
        {
            player = p;
            this.tiles =tiles;
            enemies = new ArrayList<>();
            enemies.add(e);
        }
    }
