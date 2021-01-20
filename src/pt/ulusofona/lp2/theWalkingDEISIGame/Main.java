package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws InvalidTWDInitialFileException, FileNotFoundException {
        //main
        File nd = new File("testesFile.txt");
        TWDGameManager game = new TWDGameManager();
        game.startGame(nd);

    }
}
