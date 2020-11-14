package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Moves {
    int x,y;
    int x0,y0;

    public Moves(int x, int y, int x0, int y0) {
        this.x = x;
        this.y = y;
        this.x0 = x0;
        this.y0 = y0;
    }

    public boolean validarMove(int xPretendido, int yPretendido, int xAnterior, int yAnterior){
       //falta as condicoes verdadeiras
        if(     xPretendido == xAnterior - 1 && yPretendido == yAnterior ||
                xPretendido == xAnterior +1 && yPretendido == yAnterior ||
                xPretendido == xAnterior && yPretendido == yAnterior -1 ||
                xPretendido == xAnterior && yPretendido == yAnterior +1)
        {
            return  xPretendido <= TWDGameManager.numeroLinhas-1 &&  xPretendido >= 0 ||
                    yPretendido <= TWDGameManager.numeroColunas-1 &&  yPretendido >= 0;
        }


         else return  false;
    }
}
