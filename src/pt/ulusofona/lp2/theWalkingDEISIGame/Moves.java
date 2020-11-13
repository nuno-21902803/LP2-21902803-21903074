package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Moves {
    int x,y;

    public Moves(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean validarMove(int x1, int y1){
        return  x1 <= TWDGameManager.numeroLinhas &&  x1 >= 0 ||
                 y1 <= TWDGameManager.numeroColunas &&  y1 >= 0;
    }
}
