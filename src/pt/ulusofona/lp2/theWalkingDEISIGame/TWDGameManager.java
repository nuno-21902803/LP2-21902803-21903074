package pt.ulusofona.lp2.theWalkingDEISIGame;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class TWDGameManager {
    //game
    List<String> authors;
    List<Zombie> zombies;
    List<Humano> humanos;
    List<Equipamento> equipamentos;
    int numeroLinhas;
    int numeroColunas;
    int idEquipaInicial;
    int idEquipaAtual;
    boolean day = true;
    int x=0;
    int y=0;
    int nrTurnos=0;
    int nrCriaturas =0;
    int nrEquipamentos =0;
    int nrDias =0;
    int nrNoites =0;

    public TWDGameManager() {
    }

    public boolean startGame(File ficheiroInicial){

        //estas 2linhas pedem o input no main sq
        // Scanner in = new Scanner(System.in);
        //String line = in.nextLine();


        try {
            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiroInicial));
            while(leitorFicheiro.hasNextLine()) {

                String linha = leitorFicheiro.nextLine();
                String[] dados = linha.split(" ");

                if (dados.length==2) {
                    numeroLinhas = Integer.parseInt(dados[0].trim());
                    numeroColunas = Integer.parseInt(dados[1].trim());
                }

                idEquipaInicial = Integer.parseInt(leitorFicheiro.nextLine());
                nrCriaturas = Integer.parseInt(leitorFicheiro.nextLine());


                for (int k=0; k< nrCriaturas; k++){

                    String[] dadosCriatura = leitorFicheiro.nextLine().split(" : ");
                    //fazer o split da linha com os " : "
                    //e guardar os dados nos objetos e add na lista respetiva

                    if (Integer.parseInt(dadosCriatura[1])== 0){
                        //é zombie

                        int id = Integer.parseInt(dadosCriatura[0]);
                        int idTipo = 0;
                        String nome =dadosCriatura[2];
                        int x = Integer.parseInt(dadosCriatura[3]);
                        int y = Integer.parseInt(dadosCriatura[4]);

                        Zombie zombie = new Zombie(id,idTipo,nome,x,y);
                        zombies.add(zombie);


                    } else if (Integer.parseInt(dadosCriatura[1])== 1){
                        //é humano

                        int id = Integer.parseInt(dadosCriatura[0]);
                        int idTipo = 0;
                        String nome =dadosCriatura[2];
                        int x = Integer.parseInt(dadosCriatura[3]);
                        int y = Integer.parseInt(dadosCriatura[4]);

                        Humano humano = new Humano(id,idTipo,nome,x,y);
                        humanos.add(humano);

                    }

                }

                nrEquipamentos = Integer.parseInt(leitorFicheiro.nextLine());

                for (int k=0; k< nrEquipamentos; k++){

                    String[] dadosEquipamento = leitorFicheiro.nextLine().split(" : ");

                    int id = Integer.parseInt(dadosEquipamento[0]);
                    int idTipo = 1;
                    int x = Integer.parseInt(dadosEquipamento[3]);
                    int y = Integer.parseInt(dadosEquipamento[4]);

                    Equipamento equipamento = new Equipamento(id,idTipo,x,y);

                    equipamentos.add(equipamento);
                }
            }
            leitorFicheiro.close();
            return true;

        }
        catch(FileNotFoundException exception) {
            return false;
        }
    }



    public int[] getWorldSize(){
        return new int[]{numeroLinhas, numeroColunas};
    }

    public int getInitialTeam(){
        return idEquipaInicial;
    }

    public List<Humano> getHumans(){
        return this.humanos;
    }

    public List<Zombie> getZombies(){
        return this.zombies;
    }

    public boolean move(int xO, int yO, int xD, int yD){
        this.x = xO;
        this.y = yO;

        if ((x + xD <= numeroLinhas && x + xD >= 0) && (y + yD <= numeroColunas && y + yD >= 0)){
            this.x = x + xD;
            this.y = y + yD;


            return true;
        }
        return false;
    }

    public boolean gameIsOver(){
        return nrDias == 3 || nrNoites == 3;
    }

    public List<String> getAuthors(){
        String string1 = "Nuno Capela";
        String string2 = "Francisco Lucas";

        this.authors.add(string1);
        this.authors.add(string2);

        return this.authors;
    }

    public int getCurrentTeamId(){
        return idEquipaAtual;
    }



    public int getElementId(int x, int y){
        return 1; //n ta feita
    }

    public List<String> getSurvivors(){
        List<String> survivors = null;
        //fazer
        //        Nr. de turnos terminados:
        //        <n>

        //        OS VIVOS
        //      <ID da Criatura> <Nome da criatura>

        //       OS OUTROS
        //    <ID da Criatura> (antigamente conhecido como <Nome da criatura>)
        return survivors;
    }

    public boolean isDay(){
        return this.day;
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId){
        return true;
        //n ta feita
    }

}
