package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws InvalidTWDInitialFileException, FileNotFoundException {
        //main
        File nd = new File("test-files/testesFile.txt");
        TWDGameManager game = new TWDGameManager();
        game.startGame(nd);
        game.saveGame(new File("Oláá"));
        game.loadGame(new File("Oláá"));
        game.move(3,3,2,3);
        game.move(1,2,1,3);
        System.out.println(TWDGameManager.criaturas);
        System.out.println(game.move(2,3,1,3));
        game.move(3,4,3,3);
        System.out.println(game.move(1,3,3,3));
        game.move(4,4,4,3);
        System.out.println(game.move(3,3,4,3));
        System.out.println(TWDGameManager.criaturas);
        game.move(5,5,5,3);
        System.out.println(game.move(4,3,5,3));
        System.out.println(TWDGameManager.criaturas);


    }
}
