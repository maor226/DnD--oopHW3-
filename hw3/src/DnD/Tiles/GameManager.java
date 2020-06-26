package DnD.Tiles;

import DnD.Tiles.Board;
import DnD.Tiles.Units.Players.Player;

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
        board.PlayerTick(c);
        board.EnemyTick();
        if (board.isLevelFinished())
           NextLevel();
    }

    public void NextLevel(){
        if(HasNextLevel()) {
            Player p= board.getPlayer();
            level++;
            board=levels[level];
            board.ReplacePlayer(p);
            board.getPlayer().NotifyObserver("congratulation! you are continuing to the next level!");
        }
    }
    public boolean HasNextLevel(){
        return levels.length>1+level;
    }

    public static String Description()
    {
        return     "---------------------------------------------------------------------------------------------------\n" +
                   "|number |  Name       |Health |Attack |Defence |CoolDown |                                        |\n"+
                   "---------------------------------------------------------------------------------------------------\n" +
                   "|   1   |Jon Snow     |  300  |  30   |  4     |  3      |                                        |\n" +
                   "|   2   |The Hound    |  400  |  20   |  6     |  5      |                                        |\n" +
                   "---------------------------------------------------------------------------------------------------\n" +
                   "|number |Name         |Health |Attack |Defense |Mana Pool|Mana Cost|Spell Power|Hit Count|Range   |\n"+
                   "---------------------------------------------------------------------------------------------------\n" +
                   "|   3   |Melisandre   |  100  |  5    |  1     |  300    | 30      | 15        | 5       | 6      |\n" +
                   "|   4   |Thoros of Myr|  250  |  25   |  4     |  150    | 20      | 20        | 3       | 4      |\n" +
                   "---------------------------------------------------------------------------------------------------\n" +
                   "|number |Name         |Health |Attack |Defense |Cost     |                                        |\n"+
                   "---------------------------------------------------------------------------------------------------\n" +
                   "|   5   |Arya Stark   |  150  |  40   |  2     |  20     |                                        |\n" +
                   "|   6   |Bronn        |  250  |  35   |  3     |  50     |                                        |\n" +
                   "---------------------------------------------------------------------------------------------------\n" +
                   "|number |Name         |Health |Attack |Defense |Range    |                                        |\n"+
                   "---------------------------------------------------------------------------------------------------\n" +
                   "|   7   |Ygritte      |  220  |  30   |  2     |  6      |                                        |\n" +
                   "---------------------------------------------------------------------------------------------------\n";
    }

    public boolean isWon()
    {
        return board.getEnemies().size()==0&&!HasNextLevel();
    }

    public Board getBoard()
    {
        return board;
    }


}
