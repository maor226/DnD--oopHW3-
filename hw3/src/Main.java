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
        System.out.println("choose character from the following list, enter the number of the character");
        System.out.println(GameManager.Description());
//        Scanner scanner = new Scanner(System.in);
//        int x = scanner.nextInt();
//        Player p;
//        switch (x)
//        {
//            case 1: p=new Warrior();
//                    break;
//            case 2: p=new Warrior();
//                    break;
//            case 3: p=new Mage();
//                break;
//            case 4: p=new Mage();
//                break;
//            case 5: p=new Rogue();
//                break;
//            case 6: p=new Rogue();
//                break;
//            case 7: p=new Hunter();
//                break;
//        }
//        try {
//            List<String> levels = Files.list(Paths.get(args[0])).sorted().map(Path::toString).collect(Collectors.toList());
//            for (String levelPath : levels)
//            {
//                List<String> levelData = Files.readAllLines(Paths.get(levelPath));
//                for (String s : levelData)
//                {
//                   /** add board getting the data**/
//                }
//                System.out.println();
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
