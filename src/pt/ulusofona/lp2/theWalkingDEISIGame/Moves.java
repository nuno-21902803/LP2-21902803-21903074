package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Moves {
    int x,y;
    int x0,y0;
    int idCriaturaAtual;


    public Moves(int x, int y, int x0, int y0, int idCriaturaAtual) {
        this.x = x;
        this.y = y;
        this.x0 = x0;
        this.y0 = y0;
        this.idCriaturaAtual = idCriaturaAtual;
    }


    //TODO a ideia é ir buscar a criatura ao hash de criaturas e ver o seu IDtipo e dps consoante isso fazer restricoes

    public boolean validarMove(int xPretendido, int yPretendido, int xAnterior, int yAnterior, int idCriaturaAtual){
        if(     xPretendido == xAnterior - 1 && yPretendido == yAnterior ||
                xPretendido == xAnterior +1 && yPretendido == yAnterior ||
                xPretendido == xAnterior && yPretendido == yAnterior -1 ||
                xPretendido == xAnterior && yPretendido == yAnterior +1)
        {

            return  xPretendido <= TWDGameManager.numeroLinhas-1 &&  xPretendido >= 0 ||
                    yPretendido <= TWDGameManager.numeroColunas-1 &&  yPretendido >= 0;
        }
        
        return  false;
    }
}
