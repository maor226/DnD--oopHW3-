package Tiles.Units.Enemies;

import Tiles.Board;
import Tiles.Point;
import Tiles.Units.Players.Player;
import Tiles.Units.Unit;

public class Monster extends Enemy {
    protected Integer visionRange;

    public Monster(Character tile, Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experienceValue, Integer visionRange) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defencePoints, experienceValue);
        this.visionRange = visionRange;
    }

    @Override
    public void GameTick(Player p, Board board) {
        String direction = null;
            if (p!=null)
            {
                int dx = position.getX() - p.getPosition().getX();
                int dy = position.getY() - p.getPosition().getY();
                if(Math.abs(dx)>Math.abs(dy))
                    if (dx>0)
                        direction="left";
                    else
                        direction="right";
                else
                    if(dx>0)
                        direction="up";
                    else
                        direction="down";
            }
            else
            {
                int x = Math.toIntExact(Math.round(Math.random()*4));
                switch (x)
                {
                    case 0: direction = "down";
                        break;
                    case 1: direction = "up";
                        break;
                    case 2: direction = "left";
                        break;
                    case 3: direction = "right";
                        break;
                }
            }
        move(board,direction);
    }

    public void AttackPlayer(Player p){
        p.Hit(rollAttack());
    }

    @Override
    public int getVisionRange() {
        return visionRange;
    }

     public boolean accept(Unit u)
     {
         return u.accept(this);
     }
     public boolean accept(Player p){
         AttackPlayer(p);
         return false;
     }
     public boolean accept(Enemy e)
     {
         return false;
     }



}
