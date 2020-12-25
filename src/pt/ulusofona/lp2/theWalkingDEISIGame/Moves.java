package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.Map;

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

//TODO passarem por cima
    public boolean checkCriaturasNoCaminho(int xPretendido, int yPretendido, int xAnterior, int yAnterior){
        //um id nao existente
        int checkID = -1000;
        int loopMaxX = -1;
        int loopMaxY = -30;
        int loopTOTALmax = -100;

        TWDGameManager guia = new TWDGameManager();

        loopMaxX = Math.max(xPretendido,xAnterior) +1;
        loopMaxY = Math.max(yPretendido,yAnterior) +1;

        loopTOTALmax = Math.max(loopMaxX,loopMaxY);


        int x = 0;
        int y = 0;


        return checkID != -1000;
        //tem element no meio return true else false
    }


    //ver se ha criaturas no caminho pretendido

    public boolean validarMove(int xPretendido, int yPretendido, int xAnterior, int yAnterior, int tYPEidCriaturaAtual,
                               boolean day) {

        boolean check = ((xAnterior - xPretendido == 0 || yAnterior - yPretendido == 0) &&
                xAnterior - xPretendido + yAnterior - yPretendido <= 1 &&
                xAnterior - xPretendido + yAnterior - yPretendido >= -1);

        boolean checkCreatures = checkCriaturasNoCaminho(xPretendido,yPretendido,xAnterior,yAnterior);


        switch (tYPEidCriaturaAtual) {
            //crianca
            case 0:
            case 5:
                if (checkCreatures){
                //    return false;
                }

                return xPretendido == xAnterior - 1 && yPretendido == yAnterior ||
                        xPretendido == xAnterior + 1 && yPretendido == yAnterior ||
                        xPretendido == xAnterior && yPretendido == yAnterior - 1 ||
                        xPretendido == xAnterior && yPretendido == yAnterior + 1;
            //adulto
            case 1:
            case 6:
                if (checkCreatures){
                   // return false;
                }
                return (xAnterior - xPretendido> -3 && yAnterior - yPretendido < 3) &&
                        (yAnterior - yPretendido > -3 && xAnterior - xPretendido < 3);

            //militar
            case 2:
            case 7:

                if (checkCreatures) {
                    //return false;
                }

                return (xAnterior - xPretendido> -4 && yAnterior - yPretendido < 4) &&
                        (yAnterior - yPretendido > -4 && xAnterior - xPretendido < 4);
                //idoso zombie
            case 3:
                if (checkCreatures){
                  //  return false;
                }
                return check;

                //idoso humano
            case 8:

                if (day){
                    if (checkCreatures){
                    //    return false;
                    }

                    return check;
                }else {
                    return false;
                }
                //zombie vampiro
            case 4:

                if (!day){

                    if (checkCreatures){
                      //  return false;
                    }

                    return !(xAnterior -xPretendido > -2 && xAnterior-xPretendido <2) ||
                            !(yAnterior-yPretendido> -2 && yAnterior-yPretendido <2);
                }else {
                    return false;
                }
                //cao
            case 9:
                if (checkCreatures){
                   // return false;
                }

               return !check || xAnterior >1 || xPretendido >1 || xAnterior<-1 || xPretendido < -1;
                //zombieFILME
            case 10:
                if (checkCreatures){
                  //  return false;
                }
                //TODO zombie filme
            default:
                return xPretendido <= TWDGameManager.numeroLinhas - 1 && xPretendido >= 0 ||
                        yPretendido <= TWDGameManager.numeroColunas - 1 && yPretendido >= 0;
        }

    }
}
