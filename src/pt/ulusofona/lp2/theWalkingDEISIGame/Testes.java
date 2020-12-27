package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;


import static org.junit.Assert.assertEquals;

public class Testes {


    static TWDGameManager teste = new TWDGameManager();


    @Test
    public void testFileErrado(){
        File f = new File("test-files/testesERRADO.txt");

        boolean resultReal = teste.startGame(f);
        boolean resultEsperado = false;

        assertEquals("Devia dar false",resultEsperado,resultReal);
    }

    @Test
    public void testGetWorldSize(){
        File f = new File("test-files/testesFile.txt");
        teste.startGame(f);
        int[] resultReal = teste.getWorldSize();
        int[] resultEsperado = new int[]{7,7};

        assertEquals("Devia dar [7,7]", Arrays.toString(resultEsperado), Arrays.toString(resultReal));
    }

    @Test
    public void testGetTeamInicial(){
        File f = new File("test-files/testesFile.txt");
        teste.startGame(f);

        int resultEsperado = 10;
        int resultReal= teste.getInitialTeam();

        assertEquals("Devia dar igual",resultEsperado,resultReal);
    }


    @Test
    public void testGETcreatures(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        int resultEsperado = 8;
        int resultReal= teste.getCreatures().size();

        assertEquals("Devia dar igual",resultEsperado,resultReal);
    }

    @Test
    public void testGETinicialTeam(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);
        teste.startGame(new File("test-files/teste1.txt"));

        int resultEsperado = 20;
        int resultReal= teste.getCurrentTeamId();

        assertEquals("Devia dar igual",resultEsperado,resultReal);
    }

}

