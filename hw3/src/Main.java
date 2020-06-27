import DnD.Tiles.Board;
import DnD.Tiles.GameManager;
import DnD.Tiles.Units.Observer;
import DnD.Tiles.Units.Players.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main
{


    public static void main(String[] args) {
        /**choosing character**/
        System.out.println("choose character from the following list, enter the number of the character");
        System.out.println(GameManager.Description());
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        /**setting up the observer the tiles and the player**/
        Observer o = message -> System.out.println("\033[0;32m"+message+"\033[0m");
        Player p = null;
        switch (x) {
            case 1:
                p = new Warrior(null, "Jon Snow", 300, 30, 4, 3);
                break;
            case 2:
                p = new Warrior(null, "The hound", 400, 20, 6, 5);
                break;
            case 3:
                p = new Mage(null, "Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
                break;
            case 4:
                p = new Mage(null, "Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4);
                break;
            case 5:
                p = new Rogue(null, "Arya Stark", 150, 40, 2, 20);
                break;
            case 6:
                p = new Rogue(null, "Bronn", 250, 35, 3, 50);
                break;
            case 7:
                p = new Hunter(null, "Ygritte", 220, 30, 2, 6);
                break;
        }
        p.setObserver(o);
        int i = 0, j = 0;
        char[][][] boards=null;
        /**getting the board data and setting up position for enemies and the player**/
        try {
          //  List<String> levels = Files.list(Paths.get(args[0])).sorted().map(Path::toString).collect(Collectors.toList());
            List<String> levels = Files.list(Paths.get("C:\\Users\\ntur4\\OneDrive\\Desktop\\semester bet\\OPP\\OOP-hw3\\hw3\\src\\Levels")).sorted().map(Path::toString).collect(Collectors.toList());
            boards = new char[levels.size()][][];
            for (String levelPath : levels) {
                List<String> levelData = Files.readAllLines(Paths.get(levelPath));
                boards[i] = new char[levelData.size()][levelData.get(0).length()];
                for (String s : levelData) {
                    for (int k = 0; k < s.length(); k++) {
                        boards[i][j][k] = levelData.get(j).charAt(k);
                    }
                    j++;
                }
                System.out.println();
                i++;
                j = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Board[] levels=null;
        if (boards != null) {
           levels=new Board[boards.length];
            for (i = 0; i < boards.length; i++)
                levels[i]=i==0?new Board(boards[i], p,o):new Board(boards[i],o);
        }
        GameManager GM=new GameManager(levels);

        boolean isWon=false;
        while(!isWon && !p.getHealth().isZero())
        {
            System.out.println(GM.getBoard().toString());
            System.out.println("enter a move:");
            char c = scanner.next().charAt(0);
            GM.GameTick(c);
            if(GM.isWon())
                isWon=true;
        }

        if (p.getHealth().isZero())
        {
            System.out.println(GM.getBoard().toString());
            System.out.println("sorry you unfortunately died, but you can play again!");
        }
        else
        {

            System.out.println("\n\n\n"+
                    "----------------------------------------"+
                    "|                                      |"+
                    "|      CONGRATULATION YOU WON !!!!     |"+
                    "|                                      |"+
                    "----------------------------------------");
        }
    }
}
