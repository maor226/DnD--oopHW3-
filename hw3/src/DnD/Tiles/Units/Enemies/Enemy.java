package DnD.Tiles.Units.Enemies;

import DnD.Tiles.Board;
import DnD.Tiles.Direction;
import DnD.Tiles.Point;
import DnD.Tiles.Tile;
import DnD.Tiles.Units.Players.Player;
import DnD.Tiles.Units.Unit;

public abstract class Enemy extends Unit {

    protected Integer experienceValue;

    public Enemy(Character tile, Point position, String name, Integer healthPool, Integer attackPoints, Integer defencePoints, Integer experienceValue) {
        super(tile, position, name, healthPool, attackPoints, defencePoints);
        this.experienceValue = experienceValue;
    }
    public abstract void GameTick(Player p, Board board);
    public boolean accept(Unit u)
    {
        return u.accept(this);
    }

    //getters and seters
    public Integer getExperienceValue() {
        return experienceValue;
    }


    public abstract int getVisionRange();

    public void move(Board board, String direction) {
            boolean moveable;
            if (direction.equals("right")) {
                if (board.possibleMove(position.getX() , position.getY()+1)) { /**not out of bounds**/
                    Tile move = board.getTiles()[position.getX() ][position.getY()+1]; /**gets the tile to interact to**/
                    moveable = Interact(move); /**interaction brings if you can move there or not(empty,wall,player,another unit and so...)**/
                    if (moveable) {/**if you can move there**/
                        board.Switch(this, move);/**switch their places in the board**/
                        this.getPosition().Move(Direction.Right); /**move their position object**/
                        move.getPosition().Move(Direction.Left);
                    }
                }
            } else if (direction.equals("left")) {
                if (board.possibleMove(position.getX(), position.getY()-1)) {
                    Tile move = board.getTiles()[position.getX()][position.getY()-1];
                    moveable = Interact(move);
                    if (moveable) {
                        board.Switch(this, move);
                        this.getPosition().Move(Direction.Left); /**move their position object**/
                        move.getPosition().Move(Direction.Right);
                    }
                }
            } else if (direction.equals("up"))
            {
                if (board.possibleMove(position.getX()-1, position.getY())) {
                    Tile move = board.getTiles()[position.getX()-1][position.getY()];
                    moveable = Interact(move);
                    if (moveable) {
                        board.Switch(this, move);
                        this.getPosition().Move(Direction.Up); /**move their position object**/
                        move.getPosition().Move(Direction.Down);
                    }
                }
            }
            else
            {
                if (board.possibleMove(position.getX()+1, position.getY())) {
                    Tile move = board.getTiles()[position.getX() + 1][position.getY()];
                    moveable = Interact(move);
                    if (moveable) {
                        board.Switch(this, move);
                        this.getPosition().Move(Direction.Down); /**move their position object**/
                        move.getPosition().Move(Direction.Up);
                    }
                }
            }
    }

    public void Hit(int i) {
        health.ChangeAmount(-i);
    }
}
