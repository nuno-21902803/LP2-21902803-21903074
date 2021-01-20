package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class TestMove {


    static TWDGameManager teste = new TWDGameManager();

    @Test
    public void testMove_1() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean resultEsperado = true;
        boolean resultReal = teste.move(5, 1, 6, 0);

        assertTrue("Devia dar igual, movimento cao", resultReal);
    }


    @Test
    public void testMove_2() throws InvalidTWDInitialFileException, FileNotFoundException {
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
    public void testMove_3() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean resultEsperado = true;
        boolean resultReal = teste.move(5, 6, 6, 6);

        assertEquals("Devia dar true, idoso para save Haven de dia", resultEsperado, resultReal);
    }

    @Test
    public void testMove_4() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean resultEsperado =false;
        boolean resultReal = teste.move(5, 5, 6, 5);

        assertEquals("Devia dar false, nao e o turno de zombie", resultEsperado, resultReal);
    }

    @Test
    public void testMove_5() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);
        teste.move(2, 6, 1, 6);
        teste.move(1,2,4,2);

        boolean resultEsperado = false;
        boolean resultReal = teste.move(5, 6, 6, 6);

        assertEquals("Devia dar false, idoso nao se move de noite", resultEsperado, resultReal);
    }

    @Test
    public void testMove_6() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);


        boolean resultEsperado = true;
        boolean resultReal = teste.move(3, 3, 2, 3);

        assertEquals("Devia dar true, militar apanha eq", resultEsperado, resultReal);
    }

    @Test
    public void testMove_7() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);


        boolean resultEsperado = false;
        boolean resultReal = teste.move(3, 0, 3, -1);

        assertEquals("Devia dar false, coordenadas invalidas", resultEsperado, resultReal);
    }



    @Test
    public void testMove_outOfBounds1() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean obtained = teste.move(0,0,-1,0);
        assertFalse(obtained);
    }

    @Test
    public void testMove_outOfBounds2() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean obtained = teste.move(0,0,0,-1);
        assertFalse(obtained);
    }

    @Test
    public void testMove_outOfBounds3() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean obtained = teste.move(6,6,7,6);
        assertFalse(obtained);
    }

    @Test
    public void testMove_outOfBounds4() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean obtained = teste.move(6,6,6,7);
        assertFalse(obtained);
    }

    @Test
    public void testMove_oneRight() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean obtained = teste.move(3,3,4,3);
        assertTrue(obtained);
    }
    @Test
    public void testMove_oneLeft() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);;

        boolean obtained = teste.move(3,3,2,3);
        assertTrue(obtained);
    }
    @Test
    public void testMove_oneUp() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean obtained = teste.move(3,3,3,2);
        assertTrue(obtained);
    }
    @Test
    public void testMove_oneDown() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean obtained = teste.move(3,3,3,4);
        assertFalse("devia dar false, militar nao tem eq defensivo e move para zombie", obtained);
    }
    @Test
    public void testMove_oneUpRightDiagonal() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean obtained = teste.move(3,3,4,2);
        assertTrue(obtained);
    }
    @Test
    public void testMove_oneLeftUpDiagonal() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean obtained = teste.move(3,3,2,2);
        assertTrue(obtained);
    }

    @Test
    public void testMove_oneLeftDownDiagonal() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/testesFile.txt");
        teste.startGame(file);

        boolean obtained = teste.move(3, 3, 2, 4);
        assertTrue(obtained);
    }

    //--testes file modelo
    //

    @Test
    public void testMove_ModeloBeta_1() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/modelo.txt");
        teste.startGame(file);
        teste.move(1, 1, 2, 0);

        boolean obtained = teste.move(5, 5, 5, 6);
        assertFalse("Nao devia deixar, nao faz o move certo", obtained);
    }

    @Test
    public void testMove_ModeloBeta_2() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/modelo.txt");
        teste.startGame(file);
        teste.move(1, 1, 2, 0);

        boolean obtained = teste.move(5, 5, 4, 2);
        assertTrue("Deveria dar true, Beta consegue-se mover ", obtained);
    }

    @Test
    public void testMove_ModeloBeta_3() throws InvalidTWDInitialFileException, FileNotFoundException {
        File file = new File("test-files/modelo.txt");
        teste.startGame(file);
        teste.move(1, 1, 2, 0);

        boolean obtained = teste.move(4, 1, 5, 4);
        assertTrue("Deveria dar true, Beta consegue-se mover ", obtained);
    }
}
