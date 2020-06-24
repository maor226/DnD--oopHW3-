import Tiles.Board;
import Tiles.Units.Enemies.Enemy;

import java.util.logging.Level;

public class GameManager {
    private Board board;
    private Board[] levels;
    protected int level=0;

    public GameManager(Board[] levels)
    {
        this.board = levels[level];
        this.levels=levels;
    }

    public void GameTick(Character c){
        boolean movable=false;
        switch( getDirection(c))
        {
            case Right: movable = board.possibleMove(board.getPlayer().getPosition().getX(),board.getPlayer().getPosition().getY()+1);
                break;
            case Down: movable = board.possibleMove(board.getPlayer().getPosition().getX()-1,board.getPlayer().getPosition().getY());
                break;
            case Left: movable = board.possibleMove(board.getPlayer().getPosition().getX(),board.getPlayer().getPosition().getY()-1);
                break;
            case Up: movable = board.possibleMove(board.getPlayer().getPosition().getX()+1,board.getPlayer().getPosition().getY());
                break;
            case None: movable = false;
            break;
            case Cast: board.getPlayer().CastAbility(board);
            break;
        }
        if (movable) {
            board.getPlayer().Interact(board.GetTileDirection(board.getPlayer().getPosition(), c));
        }
        board.getPlayer().GameTick();
        for (Enemy e :board.getEnemies())
            if(e.getHealth().isZero()) {
                board.getEnemies().remove(e);
                board.DeadUnit(e.getPosition());
            }
            else if (e.getPosition().Range(board.getPlayer().getPosition())<e.getVisionRange())
                e.GameTick(board.getPlayer(),board);
            else
                e.GameTick(null,board);
            if(board.getPlayer().getHealth().isZero())
                board.DeadPlayer(board.getPlayer().getPosition());
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
    public void NextLevel(){
        if(HasNextLevel())
        board=levels[++level];
    }
    public boolean HasNextLevel(){
        return levels.length>1+level;
    }

    public static String Description()
    {
        return     "-------------------------------------------------------------------------------------------\n" +
                   "|  Name       |Health |Attack |Defence |CoolDown |                                        |\n"+
                   "-------------------------------------------------------------------------------------------\n" +
                   "|Jon Snow     |  300  |  30   |  4     |  3      |                                        |\n" +
                   "|The Hound    |  400  |  20   |  6     |  5      |                                        |\n" +
                   "-------------------------------------------------------------------------------------------\n" +
                   "|Name         |Health |Attack |Defense |Mana Pool|Mana Cost|Spell Power|Hit Count|Range   |\n"+
                   "-------------------------------------------------------------------------------------------\n" +
                   "|Melisandre   |  100  |  5    |  1     |  300    | 30      | 15        | 5       | 6      |\n" +
                   "|Thoros of Myr|  250  |  25   |  4     |  150    | 20      | 20        | 3       | 4      |\n" +
                   "-------------------------------------------------------------------------------------------\n" +
                   "|Name         |Health |Attack |Defense |Cost     |                                        |\n"+
                   "-------------------------------------------------------------------------------------------\n" +
                   "|Arya Stark   |  150  |  40   |  2     |  20     |                                        |\n" +
                   "|Bronn        |  250  |  35   |  3     |  50     |                                        |\n" +
                   "-------------------------------------------------------------------------------------------\n" +
                   "|Name         |Health |Attack |Defense |Range    |                                        |\n"+
                   "-------------------------------------------------------------------------------------------\n" +
                   "|Ygritte      |  220  |  30   |  2     |  6      |                                        |\n" +
                   "-------------------------------------------------------------------------------------------\n";
    }



}
