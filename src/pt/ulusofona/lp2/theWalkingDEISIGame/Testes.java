package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Testes {

    @Test
    public void testFileErrado(){
        TWDGameManager teste = new TWDGameManager();
        File file = new File("fileErrado.txt");
        boolean resultReal =teste.startGame(file);
        boolean resultEsperado = false;

        assertEquals("Devia dar false",resultEsperado,resultReal);
    }

    @Test
    public void testGetWorldSize(){
        TWDGameManager teste = new TWDGameManager();

        int[] resultReal = teste.getWorldSize();
        int[] resultEsperado = new int[]{5,5};

        assertEquals("Devia dar [5,5]", Arrays.toString(resultEsperado), Arrays.toString(resultReal));
    }

    @Test
    public void testGetTeamInicial(){
        TWDGameManager teste = new TWDGameManager();
        File file = new File("testesFile.txt");
        teste.startGame(file);

        int resultEsperado = 0;
        int resultReal= teste.getInitialTeam();

        assertEquals("Devia dar igual",resultEsperado,resultReal);
    }


}

