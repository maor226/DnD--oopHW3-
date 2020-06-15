import Tiles.Board;
import Tiles.Units.Enemies.Enemy;
import Tiles.Units.Players.Player;

import java.util.List;

public class GameManager {
    private Player player;
    private List<Enemy> enemies;
    private Board board;

    public GameManager(List<Enemy> enemies, Player player, Board board)
    {
        this.player = player;
        this.enemies = enemies;
        this.board = board;
    }

    public void GameTick(Character c)
    {
        boolean movable=false;
        switch(c)
        {
            case 'w': movable = board.possibleMove(player.getPosition().getX(),player.getPosition().getY()+1);
                break;
            case 'a': movable = board.possibleMove(player.getPosition().getX()-1,player.getPosition().getY());
                break;
            case 's': movable = board.possibleMove(player.getPosition().getX(),player.getPosition().getY()-1);
                break;
            case 'd': movable = board.possibleMove(player.getPosition().getX()+1,player.getPosition().getY());
                break;
            case 'q': movable = false;
        }
        if (movable) {
            player.Interact(board.GetTileDirection(player.getPosition(), c));
        }
        for (Enemy e :enemies)
            if(e.getHealth().isZero()) {
                enemies.remove(e);
                board.DeadUnit(e.getPosition());
            }
            else if (e.getPosition().Range(player.getPosition())<e.getVisionRange())
                e.GameTick(player,board);
            else
                e.GameTick(null,board);
            if(player.getHealth().isZero())
                board.DeadPlayer(player.getPosition());
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
