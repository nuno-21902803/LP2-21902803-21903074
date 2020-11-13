package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        //main
        File nd = new File("testesFile.txt");
        TWDGameManager game = new TWDGameManager();
        game.startGame(nd);

    }
}
