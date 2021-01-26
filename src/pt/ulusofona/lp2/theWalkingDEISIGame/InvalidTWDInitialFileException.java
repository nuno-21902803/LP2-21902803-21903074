package pt.ulusofona.lp2.theWalkingDEISIGame;



public class InvalidTWDInitialFileException extends Exception {
    private int numCriaturas;
    private int dadoscriaturaLenght;
    private String linhaError = "";



    public InvalidTWDInitialFileException(int numCriaturas,int dadoscriaturaLenght) {
        this.numCriaturas = numCriaturas;
        this.dadoscriaturaLenght = dadoscriaturaLenght;
    }

    public InvalidTWDInitialFileException() {

    }

    public void setLinhaError(String linhaError) {
        this.linhaError = linhaError;
    }


    //funcao que valida se tem +2 criaturas
    public boolean validNrOfCreatures(){
        return numCriaturas > 1;
    }

    //funcao que valida a estrutura da criatura
    public boolean validCreatureDefinition(){
        return dadoscriaturaLenght == 5;
    }

    //retorna a 1st linha que tem erro
    public String getErroneousLine(){
        return this.linhaError;
    }


}
