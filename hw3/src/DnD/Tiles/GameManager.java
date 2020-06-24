package DnD.Tiles;

import DnD.Tiles.Board;

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
        {
            level++;
            if (level<levels.length) {
                board = levels[level];
                board.getPlayer().NotifyObserver("congratulation! you are continuing to the next level!");
            }
        }
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

    public boolean isWon()
    {
        return level>=levels.length;
    }

    public Board getBoard()
    {
        return board;
    }


}
