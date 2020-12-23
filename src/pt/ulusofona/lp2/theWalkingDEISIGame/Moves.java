package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Moves {
    int x, y;
    int x0, y0;
    int idCriaturaAtual;


    public Moves(int x, int y, int x0, int y0, int idCriaturaAtual) {
        this.x = x;
        this.y = y;
        this.x0 = x0;
        this.y0 = y0;
        this.idCriaturaAtual = idCriaturaAtual;
    }


    //TODO a ideia é ir buscar a criatura ao hash de criaturas e ver o seu IDtipo e dps consoante isso fazer restricoes

    public boolean validarMove(int xPretendido, int yPretendido, int xAnterior, int yAnterior, int tYPEidCriaturaAtual,
                               boolean day) {

        boolean check = ((xAnterior - xPretendido == 0 || yAnterior - yPretendido == 0) &&
                xAnterior - xPretendido + yAnterior - yPretendido <= 1 &&
                xAnterior - xPretendido + yAnterior - yPretendido >= -1);

        switch (tYPEidCriaturaAtual) {
            //crianca
            case 0:
            case 5:
                return xPretendido == xAnterior - 1 && yPretendido == yAnterior ||
                        xPretendido == xAnterior + 1 && yPretendido == yAnterior ||
                        xPretendido == xAnterior && yPretendido == yAnterior - 1 ||
                        xPretendido == xAnterior && yPretendido == yAnterior + 1;
            //adulto
            case 1:
            case 6:
                return (xAnterior - xPretendido> -3 && yAnterior - yPretendido < 3) &&
                        (yAnterior - yPretendido > -3 && xAnterior - xPretendido < 3);

            //militar
            case 2:
            case 7:
                return (xAnterior - xPretendido> -4 && yAnterior - yPretendido < 4) &&
                        (yAnterior - yPretendido > -4 && xAnterior - xPretendido < 4);
                //idoso zombie
            case 3:
                return check;

                //idoso humano
            case 8:
                if (day){

                    return check;
                }else {
                    return false;
                }
                //zombie vampiro
            case 4:

                if (!day){

                    return !(xAnterior -xPretendido > -2 && xAnterior-xPretendido <2) ||
                            !(yAnterior-yPretendido> -2 && yAnterior-yPretendido <2);
                }else {
                    return false;
                }
                //cao
            case 9:
               return !check || xAnterior >1 || xPretendido >1 || xAnterior<-1 || xPretendido < -1;
                //zombieFILME
            case 10:

                //TODO zombie filme
            default:
                return xPretendido <= TWDGameManager.numeroLinhas - 1 && xPretendido >= 0 ||
                        yPretendido <= TWDGameManager.numeroColunas - 1 && yPretendido >= 0;
        }

    }
}
