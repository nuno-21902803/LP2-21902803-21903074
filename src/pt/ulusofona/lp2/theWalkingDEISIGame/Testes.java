package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        int resultEsperado = 6;
        int resultReal= teste.getCreatures().size();

        assertEquals("Devia dar igual",resultEsperado,resultReal);
    }

    @Test
    public void testGetElementID_1(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        int resultEsperado = 1;
        int resultReal= teste.getElementId(3,3);

        assertEquals("Devia dar 1, Militar Freddie",resultEsperado,resultReal);
    }

    @Test
    public void testGetElementID_2(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        int resultEsperado = 2;
        int resultReal= teste.getElementId(3,4);

        assertEquals("Devia dar 2, zombie crianca jackie",resultEsperado,resultReal);
    }

    @Test
    public void testGetElementID_3(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        int resultEsperado = 3;
        int resultReal= teste.getElementId(5,5);

        assertEquals("Devia dar 3, zombie crianca jackie",resultEsperado,resultReal);
    }

    @Test
    public void testGetElementID_4(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        int resultEsperado = 4;
        int resultReal= teste.getElementId(2,6);

        assertEquals("Devia dar 4, militar Ash",resultEsperado,resultReal);
    }

    @Test
    public void testGetElementID_5(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        int resultEsperado = 5;
        int resultReal= teste.getElementId(1,1);

        assertEquals("Devia dar 5, cao sam",resultEsperado,resultReal);
    }

    @Test
    public void testGetElementID_6(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        int resultEsperado = 6;
        int resultReal= teste.getElementId(4,4);

        assertEquals("Devia dar 6, zombie crianca jackie",resultEsperado,resultReal);
    }
}

