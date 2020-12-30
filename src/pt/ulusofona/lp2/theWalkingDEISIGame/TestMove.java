package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class TestMove {


    static TWDGameManager teste = new TWDGameManager();

    @Test
    public void testMove_1() {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean resultEsperado = true;
        boolean resultReal = teste.move(5, 1, 6, 0);

        assertEquals("Devia dar igual, movimento cao", true, resultReal);
    }


    @Test
    public void testMove_2() {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);
        //System.out.println(teste.day);
        teste.move(2, 6, 0, 6);
        //System.out.println(teste.day);
        teste.move(1,2,4,2);
        //System.out.println(teste.day);
        teste.move(3,3,3,2);
        //System.out.println(teste.day);

        boolean resultReal = teste.move(5, 5, 5, 3);
       // System.out.println(teste.day);
        boolean resultEsperado = true;


        assertEquals("Devia dar true pq vampiro n move de dia", resultEsperado, resultReal);
    }

    @Test
    public void testMove_3() {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean resultEsperado = true;
        boolean resultReal = teste.move(5, 6, 6, 6);

        assertEquals("Devia dar true, idoso para save Haven de dia", resultEsperado, resultReal);
    }

    @Test
    public void testMove_4() {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean resultEsperado =false;
        boolean resultReal = teste.move(5, 5, 6, 5);

        assertEquals("Devia dar false, nao e o turno de zombie", resultEsperado, resultReal);
    }

    @Test
    public void testMove_5() {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);
        teste.move(2, 6, 1, 6);
        teste.move(1,2,4,2);

        boolean resultEsperado = false;
        boolean resultReal = teste.move(5, 6, 6, 6);

        assertEquals("Devia dar false, idoso nao se move de noite", resultEsperado, resultReal);
    }

    @Test
    public void testMove_6() {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);


        boolean resultEsperado = true;
        boolean resultReal = teste.move(3, 3, 2, 3);

        assertEquals("Devia dar true, militar apanha eq", resultEsperado, resultReal);
    }

    @Test
    public void testMove_7() {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);


        boolean resultEsperado = false;
        boolean resultReal = teste.move(3, 0, 3, -1);

        assertEquals("Devia dar false, coordenadas invalidas", resultEsperado, resultReal);
    }



    @Test
    public void testMove_outOfBounds1() {
        File file = new File("test-files/testesFile.txt");
        teste.loadGame(file);

        boolean obtained = teste.move(0,0,-1,0);
        assertFalse(obtained);
    }

    @Test
    public void testMove_outOfBounds2() {
        File file = new File("test-files/testesFile.txt");
        teste.loadGame(file);

        boolean obtained = teste.move(0,0,0,-1);
        assertFalse(obtained);
    }

    @Test
    public void testMove_outOfBounds3() {
        File file = new File("test-files/testesFile.txt");
        teste.loadGame(file);

        boolean obtained = teste.move(6,6,7,6);
        assertFalse(obtained);
    }

    @Test
    public void testMove_outOfBounds4() {
        File file = new File("test-files/testesFile.txt");
        teste.loadGame(file);

        boolean obtained = teste.move(6,6,6,7);
        assertFalse(obtained);
    }

    @Test
    public void testMove_oneRight(){
        File file = new File("test-files/testesFile.txt");
        teste.loadGame(file);

        boolean obtained = teste.move(3,3,4,3);
        assertTrue(obtained);
    }
    @Test
    public void testMove_oneLeft() {
        File file = new File("test-files/testesFile.txt");
        teste.loadGame(file);

        boolean obtained = teste.move(3,3,2,3);
        assertTrue(obtained);
    }
    @Test
    public void testMove_oneUp() {
        File file = new File("test-files/testesFile.txt");
        teste.loadGame(file);

        boolean obtained = teste.move(3,3,3,2);
        assertTrue(obtained);
    }
    @Test
    public void testMove_oneDown() {
        File file = new File("test-files/testesFile.txt");
        teste.loadGame(file);

        boolean obtained = teste.move(3,3,3,4);
        assertEquals("devia dar false, militar nao tem eq defensivo e move para zombie",false,obtained);
    }
    @Test
    public void testMove_oneUpRightDiagonal() {
        File file = new File("test-files/testesFile.txt");
        teste.loadGame(file);

        boolean obtained = teste.move(3,3,4,2);
        assertTrue(obtained);
    }
    @Test
    public void testMove_oneLeftUpDiagonal() {
        File file = new File("test-files/testesFile.txt");
        teste.loadGame(file);

        boolean obtained = teste.move(3,3,2,2);
        assertTrue(obtained);
    }

    @Test
    public void testMove_oneLeftDownDiagonal() {
        File file = new File("test-files/testesFile.txt");
        teste.loadGame(file);

        boolean obtained = teste.move(3, 3, 2, 4);
        assertTrue(obtained);
    }

    //--testes file modelo
    //
    @Test
    public void testMove_Modelo_1() {
        File file = new File("test-files/modelo.txt");
        teste.loadGame(file);

        boolean obtained = teste.move(0, 2, 0, 4);
        assertEquals(false,obtained);
    }
}
