package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class TestMove {


    static TWDGameManager teste = new TWDGameManager();

    @Test
    public void testMove_1(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean resultEsperado = true;
        boolean resultReal= teste.move(5,0,3,0);

        assertEquals("Devia dar igual",resultEsperado,resultReal);
    }

    @Test
    public void testMove_2(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean resultEsperado = true;
        boolean resultReal= teste.move(0,0,0,1);

        assertEquals("Devia dar igual",resultEsperado,resultReal);
    }

    @Test
    public void testMove_3(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean resultEsperado = true;
        boolean resultReal= teste.move(0,0,1,0);

        assertEquals("Devia dar igual",resultEsperado,resultReal);
    }

    @Test
    public void testMove_4(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean resultEsperado = true;
        boolean resultReal= teste.move(5,0,3,0);

        assertEquals("Devia dar igual",resultEsperado,resultReal);
    }

    @Test
    public void testMove_5(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean resultEsperado = true;
        boolean resultReal= teste.move(5,0,3,0);

        assertEquals("Devia dar igual",resultEsperado,resultReal);
    }

    @Test
    public void testMove_6(){
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);


        boolean resultEsperado = true;
        boolean resultReal= teste.move(5,0,3,0);

        assertEquals("Devia dar igual",resultEsperado,resultReal);
    }
}
