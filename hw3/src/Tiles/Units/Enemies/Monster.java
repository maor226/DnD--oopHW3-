package Tiles.Units.Enemies;

import Tiles.Point;
import Tiles.Units.Players.Player;

import javax.swing.text.Position;

public class Monster extends Enemy {
    protected Integer visionRange;

    public Monster(Character tile, Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experienceValue, Integer visionRange) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defencePoints, experienceValue);
        this.visionRange = visionRange;
    }

    @Override
    public void GameTick(Player p) {
            if (p!=null)
            {
                int dx = position.getX() - p.getPosition().getX();
                int dy = position.getY() - p.getPosition().getY();
                if(Math.abs(dx)>Math.abs(dy))
                    if (dx>0)
                        position.MoveLeft(1);
                    else
                        position.MoveRight(1);
                else
                    if(dx>0)
                        position.MoveUp(1);
                    else
                        position.MoveDown(1);
            }
            else
            {
                position.moveRandom();
            }
    }

    @Override
    public int getVisionRange() {
        return visionRange;
    }
}
