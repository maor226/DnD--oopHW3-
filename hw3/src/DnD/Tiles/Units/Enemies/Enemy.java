package DnD.Tiles.Units.Enemies;

import DnD.Tiles.Board;
import DnD.Tiles.Point;
import DnD.Tiles.Tile;
import DnD.Tiles.Units.Players.Player;
import DnD.Tiles.Units.Unit;

public abstract class Enemy extends Unit {

    protected Integer experienceValue;

    public Enemy(Character tile, Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experienceValue) {
        super(tile, position, name, healthPool, attackPoints, defencePoints);
        this.experienceValue = experienceValue;
    }
    public abstract void GameTick(Player p, Board board);

    @Override
    public void Print() {
        System.out.print("\033[0;31m"+tile+"\033[0m");
    }

    //getters and seters
    public Integer getExperienceValue() {
        return experienceValue;
    }


    public abstract int getVisionRange();

    public void move(Board board, String direction) {
            boolean moveable;
            if (direction.equals("right")) {
                if (board.possibleMove(position.getX() + 1, position.getY())) { /**not out of bounds**/
                    Tile move = board.getTiles()[position.getX() + 1][position.getY()]; /**gets the tile to interact to**/
                    moveable = Interact(move); /**interaction brings if you can move there or not(empty,wall,player,another unit and so...)**/
                    if (moveable) {/**if you can move there**/
                        this.getPosition().MoveRight(1); /**move their position object**/
                        move.getPosition().MoveLeft(1);
                        board.Switch(this, move);/**switch their places in the board**/
                    }
                }
            } else if (direction.equals("left")) {
                if (board.possibleMove(position.getX() - 1, position.getY())) {
                    Tile move = board.getTiles()[position.getX() - 1][position.getY()];
                    moveable = Interact(move);
                    if (moveable) {
                        this.getPosition().MoveLeft(1); /**move their position object**/
                        move.getPosition().MoveRight(1);
                        board.Switch(this, move);
                    }
                }
            } else if (direction.equals("up"))
            {
                if (board.possibleMove(position.getX(), position.getY() + 1)) {
                    Tile move = board.getTiles()[position.getX()][position.getY() + 1];
                    moveable = Interact(move);
                    if (moveable) {
                        this.getPosition().MoveUp(1); /**move their position object**/
                        move.getPosition().MoveDown(1);
                        board.Switch(this, move);
                    }
                }
            }
            else
            {
                if (board.possibleMove(position.getX(), position.getY() - 1)) {
                    Tile move = board.getTiles()[position.getX() + 1][position.getY() - 1];
                    moveable = Interact(move);
                    if (moveable) {
                        this.getPosition().MoveDown(1); /**move their position object**/
                        move.getPosition().MoveUp(1);
                        board.Switch(this, move);
                    }
                }
            }
    }

    public void Hit(int i) {
        health.ChangeAmount(-i);
    }
}
