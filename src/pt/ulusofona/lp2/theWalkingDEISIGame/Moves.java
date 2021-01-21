package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.Map;

public class Moves {
    int x, y;
    int x0, y0;
    int idCriaturaAtual;
    TWDGameManager gameManager = new TWDGameManager();

    public Moves(int x, int y, int x0, int y0, int idCriaturaAtual) {
        this.x = x;
        this.y = y;
        this.x0 = x0;
        this.y0 = y0;
        this.idCriaturaAtual = idCriaturaAtual;
    }


    public boolean checkCriaturasNoCaminho(int xAnterior, int yAnterior,int maxMoves, int direcao){

    boolean check = false;
        switch (direcao){
                //Cima
            case 0:
                if (maxMoves == 1){
                    return gameManager.getElementId(xAnterior, yAnterior + 1) == 0;
                }
                if (maxMoves == 2){
                    check = gameManager.getElementId(xAnterior, yAnterior + 1) == 0;
                    return check && gameManager.getElementId(xAnterior, yAnterior + 2) == 0;
                }
                //baixo
            case 1:
                if (maxMoves == 1){
                    return gameManager.getElementId(xAnterior, yAnterior - 1) == 0;
                }
                if (maxMoves == 2){

                    check = gameManager.getElementId(xAnterior, yAnterior - 1) == 0;
                    return check && gameManager.getElementId(xAnterior, yAnterior - 2) == 0;
                }


                //ladoESQ
            case 2:
                if (maxMoves == 1){
                    return gameManager.getElementId(xAnterior - 1, yAnterior) == 0;
                }
                if (maxMoves == 2){
                    check = gameManager.getElementId(xAnterior - 1, yAnterior) == 0;
                    return check && gameManager.getElementId(xAnterior - 2, yAnterior ) == 0;
                }


                //ladoDIR
            case 3:
                if (maxMoves == 1){
                    return gameManager.getElementId(xAnterior + 1, yAnterior) == 0;
                }
                if (maxMoves == 2){
                    check = gameManager.getElementId(xAnterior + 1, yAnterior) == 0;
                    return check &&  gameManager.getElementId(xAnterior + 2, yAnterior ) == 0;
                }


                //diagonal direitaCIMA
            case 4:
                if (maxMoves == 1){
                    return gameManager.getElementId(xAnterior + 1, yAnterior - 1) == 0;
                }
                if (maxMoves == 2){
                    check = gameManager.getElementId(xAnterior + 1, yAnterior - 1) == 0;
                    return check && gameManager.getElementId(xAnterior + 2, yAnterior - 2 ) == 0;
                }
                //diagonal direitaBAIXO
            case 5:
                if (maxMoves == 1){
                    return gameManager.getElementId(xAnterior + 1, yAnterior + 1) == 0;
                }
                if (maxMoves == 2){
                    check = gameManager.getElementId(xAnterior + 1, yAnterior + 1) == 0;
                    return check && gameManager.getElementId(xAnterior + 2, yAnterior + 2 ) == 0;
                }
                //diagonal esqCIMA
            case 6:
                if (maxMoves == 1){
                    return gameManager.getElementId(xAnterior - 1, yAnterior - 1) == 0;
                }
                if (maxMoves == 2){
                    check = gameManager.getElementId(xAnterior - 1, yAnterior - 1) == 0;
                    return check && gameManager.getElementId(xAnterior - 2, yAnterior - 2 ) == 0;
                }
                //diagonal esqBAIXO
            case 7:
                if (maxMoves == 1){
                    return gameManager.getElementId(xAnterior - 1, yAnterior + 1) == 0;
                }
                if (maxMoves == 2){
                    check = gameManager.getElementId(xAnterior - 1, yAnterior + 1) == 0;
                    return check && gameManager.getElementId(xAnterior - 2, yAnterior + 2 ) == 0;
                }
        }

        return false;
    }


    //ver se ha criaturas no caminho pretendido

    public boolean validarMove(int xPretendido, int yPretendido, int xAnterior, int yAnterior, int tYPEidCriaturaAtual,
                               boolean day) {

        if (xAnterior <0 || yPretendido <0 || xPretendido <0 || yAnterior <0){
            //coordenadas negativas nao valem
            return false;
        }


        boolean check = xPretendido == xAnterior - 1 && yPretendido == yAnterior ||
                xPretendido == xAnterior + 1 && yPretendido == yAnterior ||
                xPretendido == xAnterior && yPretendido == yAnterior - 1 ||
                xPretendido == xAnterior && yPretendido == yAnterior + 1;

        int moveMAX= 0;
        int direcao = 0;


        switch (tYPEidCriaturaAtual) {
            //crianca
            case 0:
            case 5:
                //idoso zombie
            case 3:
                moveMAX = 1;
                direcao = direcaoGetter(xPretendido,yPretendido,xAnterior,yAnterior);

                if (!checkCriaturasNoCaminho(xAnterior,yAnterior,moveMAX,direcao)){
                    return false;
                }

                return check;
            //adulto
            case 1:
            case 6:
                moveMAX = 2;
                direcao = direcaoGetter(xPretendido,yPretendido,xAnterior,yAnterior);
                System.out.println(direcao +" check" + checkCriaturasNoCaminho(xAnterior,yAnterior,moveMAX,direcao));
                if (!checkCriaturasNoCaminho(xAnterior,yAnterior,moveMAX,direcao)){

                    return false;
                }
                return (xAnterior - xPretendido> -3 && yAnterior - yPretendido < 3) &&
                        (yAnterior - yPretendido > -3 && xAnterior - xPretendido < 3);

            //militar
            case 2:
            case 7:
                /*
                moveMAX = 3;
                direcao = direcaoGetter(xPretendido,yPretendido,xAnterior,yAnterior);
                if (checkCriaturasNoCaminho(xPretendido,yPretendido,xAnterior,yAnterior,moveMAX,direcao)) {
                    //return false;
                }*/

                return (xAnterior - xPretendido> -4 && yAnterior - yPretendido < 4) &&
                        (yAnterior - yPretendido > -4 && xAnterior - xPretendido < 4);

            //idoso humano
            case 8:
                moveMAX = 1;
                direcao = direcaoGetter(xPretendido,yPretendido,xAnterior,yAnterior);

                if (day){
                    if (!checkCriaturasNoCaminho(xAnterior,yAnterior,moveMAX,direcao)){
                        return false;
                    }

                    return check;
                }else {
                    return false;
                }
                //zombie vampiro
            case 4:

                if (!day){


                    return (xAnterior -xPretendido > -3 && xAnterior-xPretendido <3) &&
                            (yAnterior-yPretendido> -3 && yAnterior-yPretendido <3);
                }else {
                    return false;
                }
                //cao
            case 9:

                if (xPretendido == xAnterior + 1 || xPretendido == xAnterior - 1) {
                    if (xPretendido + 1 > TWDGameManager.numeroColunas ||
                            yPretendido + 1 > TWDGameManager.numeroLinhas) {
                        return false;
                    } else if (xPretendido - 1 > TWDGameManager.numeroColunas ||
                            yPretendido - 1 > TWDGameManager.numeroLinhas){
                        return false;
                    }
                }

                if (xPretendido == xAnterior +2) {
                    if (xPretendido + 2 > TWDGameManager.numeroColunas ||
                            yPretendido + 2 > TWDGameManager.numeroLinhas) {
                        return false;
                    } else if (xPretendido - 2 > TWDGameManager.numeroColunas ||
                            yPretendido - 2 > TWDGameManager.numeroLinhas) {
                        return false;
                    }
                }

                if (xPretendido == xAnterior - 1 ){
                    return yPretendido == yAnterior + 1 || yPretendido == yAnterior - 1;
                } else if (xPretendido == xAnterior + 1 ){
                    return yPretendido == yAnterior + 1 || yPretendido == yAnterior - 1;
                }

                if (xPretendido == xAnterior - 2 ){
                    return yPretendido == yAnterior + 2 || yPretendido == yAnterior - 2;
                } else if (xPretendido == xAnterior + 2 ){
                    return yPretendido == yAnterior + 2 || yPretendido == yAnterior - 2;
                }
                return false;
                //zombieFILME
            case 10:

                //anda em L mas para esquerda so pode andar em L para cima
                //ser for para a direita é em L para baixo

                if (xPretendido == xAnterior +1) {
                    if (xPretendido + 1 > TWDGameManager.numeroColunas ||
                            yPretendido + 3 > TWDGameManager.numeroLinhas) {
                        return false;
                    }
                }

                if (xPretendido == xAnterior - 1) {
                    if (xPretendido - 1 > TWDGameManager.numeroColunas ||
                            yPretendido - 3 > TWDGameManager.numeroLinhas) {
                        return false;
                    }
                }

                if (xPretendido == xAnterior - 1 ){
                    return yPretendido == yAnterior - 3;

                } else if (xPretendido == xAnterior + 1 ){

                    return yPretendido == yAnterior + 3;

                }
                return false;
            default:
                return xPretendido <= TWDGameManager.numeroLinhas - 1 ||
                        yPretendido <= TWDGameManager.numeroColunas - 1;
        }

    }

    int direcaoGetter(int xPretendido,int yPretendido,int xAnterior,int yAnterior){
        //devolve a direcao para crianca idoso e adulto

        //para cima
        if (xPretendido == xAnterior && yPretendido == yAnterior - 1 ||
                xPretendido == xAnterior && yPretendido == yAnterior - 2 ){
            return 0;
        }
        // para baixo
        if (xPretendido == xAnterior && yPretendido == yAnterior + 1 ||
                xPretendido == xAnterior && yPretendido == yAnterior + 2 ){
            return 1;
        }
        //ladoESQ
        if (xPretendido == xAnterior-1 && yPretendido == yAnterior ||
                xPretendido == xAnterior-2 && yPretendido == yAnterior){
            return 2;
        }
        //ladoDIR
        if (xPretendido == xAnterior+1 && yPretendido == yAnterior ||
                xPretendido == xAnterior+2 && yPretendido == yAnterior){
            return 3;
        }
        //diagonal direitaCIMA
        if (xPretendido == xAnterior + 1  && yPretendido == yAnterior - 1 ||
                xPretendido == xAnterior + 2  && yPretendido == yAnterior - 2){
            return 4;
        }
        //diagonal direitaBAIXO
        if (xPretendido == xAnterior + 1 && yPretendido == yAnterior + 1 ||
                xPretendido == xAnterior + 2 && yPretendido == yAnterior + 2){
            return 5;
        }
        //diagonal esqCIMA
        if (xPretendido == xAnterior - 1 && yPretendido == yAnterior - 1 ||
                xPretendido == xAnterior - 2 && yPretendido == yAnterior - 2 ){
            return 6;
        }
        //diagonal esqBAIXO
        if (xPretendido == xAnterior - 1  && yPretendido == yAnterior + 1||
                xPretendido == xAnterior - 2  && yPretendido == yAnterior + 2){
            return 7;
        }
        return 800;
    }
}
