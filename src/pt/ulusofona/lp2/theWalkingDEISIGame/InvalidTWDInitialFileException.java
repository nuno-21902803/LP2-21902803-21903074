package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;


public class InvalidTWDInitialFileException extends Exception {

    ArrayList<String> criaturasERROR = new ArrayList<>();

    public InvalidTWDInitialFileException() {

    }

    //funcao que valida se tem +2 criaturas
    public boolean validNrOfCreatures(){
        return TWDGameManager.numCriaturas > 1;
    }

    //funcao que valida a estrutura da criatura
    public boolean validCreatureDefinition(){
        return false;
    }

    //retorna a 1st linha que tem erro
    public String getErroneousLine(){
        return "";
    }
}
