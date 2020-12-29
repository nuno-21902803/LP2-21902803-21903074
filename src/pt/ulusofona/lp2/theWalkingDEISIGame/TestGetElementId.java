package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class TestGetElementId {

    static TWDGameManager teste = new TWDGameManager();

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
        int resultReal= teste.getElementId(5,1);

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

    @Test
    public void testGetElementID_7(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        int resultEsperado = 0;
        int resultReal= teste.getElementId(6,6);

        assertEquals("Devia dar 0, porta",resultEsperado,resultReal);
    }

    @Test
    public void testGETElementID_8_saveLOAD(){
        File file = new File("test-files/teste1.txt");
        File file1 = new File("testeSAVE");
        teste.startGame(file);
        teste.move(3,3,2,3);
        teste.saveGame(file1);
        teste.loadGame(file1);

        int resultEsperado = 1;
        int resultReal= teste.getElementId(2,3);

        assertEquals("Devia dar igual",resultEsperado,resultReal);
    }
}
