package DnD.Tiles.Units.Enemies;

import DnD.Tiles.Board;
import DnD.Tiles.Point;
import DnD.Tiles.Units.Players.Player;

public class Monster extends Enemy {
    protected Integer visionRange;

    public Monster(Character tile, Point position, String name, Integer healthPool, Integer attackPoints, Integer defencePoints, Integer experienceValue, Integer visionRange) {
        super(tile, position, name, healthPool, attackPoints, defencePoints, experienceValue);
        this.visionRange = visionRange;
    }

    @Override
    public void GameTick(Player p, Board board) {
        String direction;
            if (p!=null)
            {
                int dx = position.getX() - p.getPosition().getX();
                int dy = position.getY() - p.getPosition().getY();
                if(Math.abs(dx)>Math.abs(dy))
                    if (dx>0)
                        direction="up";
                    else
                        direction="down";
                else
                    if(dy>0)
                        direction="left";
                    else
                        direction="right";
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
                    default: direction = "right";
                        break;
                }
            }
        move(board,direction);
    }

    public void AttackPlayer(Player p){
        p.Hit(rollAttack(),getName());
    }

    @Override
    public int getVisionRange() {
        return visionRange;
    }

    @Override
    public boolean accept(Player p){
        AttackPlayer(p);
        return false;
    }
    @Override
    public boolean accept(Enemy e)
    {
        return false;
    }



}
