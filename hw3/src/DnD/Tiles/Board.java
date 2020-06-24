package DnD.Tiles;

import DnD.Tiles.Units.Enemies.Boss;
import DnD.Tiles.Units.Enemies.Enemy;
import DnD.Tiles.Units.Enemies.Monster;
import DnD.Tiles.Units.Enemies.Trap;
import DnD.Tiles.Units.Observer;
import DnD.Tiles.Units.Players.Player;

import java.util.ArrayList;
import java.util.List;

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
        public Board(char[][] tiles,Point playerPosition,Observer o) {
            this.enemies = new ArrayList<Enemy>() ;
            this.playerPos = playerPosition;
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
            p.setPosition(playerPos);
            player=p;
            tiles[p.getPosition().getX()][p.getPosition().getY()]=player;
        }

        public void PlayerTick(Character c)
        {
            boolean movable=false;
            switch( getDirection(c))
            {
                case Right: movable = possibleMove(getPlayer().getPosition().getX(),getPlayer().getPosition().getY()+1);
                    break;
                case Down: movable = possibleMove(getPlayer().getPosition().getX()-1,getPlayer().getPosition().getY());
                    break;
                case Left: movable = possibleMove(getPlayer().getPosition().getX(),getPlayer().getPosition().getY()-1);
                    break;
                case Up: movable = possibleMove(getPlayer().getPosition().getX()+1,getPlayer().getPosition().getY());
                    break;
                case None: movable = false;
                    break;
                case Cast: getPlayer().CastAbility(this);
                    break;
            }
            if (movable) {
                getPlayer().Interact(GetTileDirection(getPlayer().getPosition(), c));
            }
            getPlayer().GameTick();
        }
        public void EnemyTick()
        {
            for (Enemy e : getEnemies())
                if(e.getHealth().isZero()) {
                    player.NotifyObserver( "" + player.getName() +" gained "+ e.getExperienceValue()+" experience points");
                    player.gainExprerience(e.getExperienceValue());
                    getEnemies().remove(e);
                    DeadUnit(e.getPosition());
                }
                else if (e.getPosition().Range(getPlayer().getPosition())<e.getVisionRange())
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
            return null;
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
                for(int j=0;j<tiles[0].length;i++)
                {
                    sb.append(tiles[i][j].getTile());
                }
            }
            return sb.toString();
        }
    }
