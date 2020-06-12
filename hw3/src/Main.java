import Tiles.Board;
import Tiles.Units.Observer;
import Tiles.Units.Players.*;
import com.sun.deploy.security.SelectableSecurityManager;
import javafx.application.Application;
import javafx.stage.Stage;

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
        Observer o = message -> System.out.println(message);
        int[][] tiles = new int[10][10];
        Player p=null;
        switch (x)
        {
            case 1: p=new Warrior(null,"Jon Snow",300,30,4,3);
                    break;
            case 2: p=new Warrior(null,"The hound", 400,20,6,5);
                    break;
            case 3: p=new Mage(null,"Melisandre",100,5,1,300,30,15,5,6);
                break;
            case 4: p=new Mage(null,"Thoros of Myr",250,25,4,150,20,20,3,4);
                break;
            case 5: p=new Rogue(null, "Arya Stark",150,40,2,20);
                break;
            case 6: p=new Rogue(null, "Bronn",250,35,3,50);
                break;
            case 7: p=new Hunter(null,"Ygritte",220,30,2,6);
                break;
        }
        p.setObserver(o);

        /**getting the board data and setting up position for enemies and the player**/
        try {
            List<String> levels = Files.list(Paths.get(args[0])).sorted().map(Path::toString).collect(Collectors.toList());
            for (String levelPath : levels)
            {
                List<String> levelData = Files.readAllLines(Paths.get(levelPath));
                for (String s : levelData)
                {
                   /** add board getting the data**/
                }
                System.out.println();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
