package pt.ulusofona.lp2.theWalkingDEISIGame;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TWDGameManager {
    //game
    ArrayList<String> authors = new ArrayList<>() ;
    ArrayList<Zombie> zombies= new ArrayList<>();
    ArrayList<Humano> humanos= new ArrayList<>();
    ArrayList<Equipamento> equipamentos= new ArrayList<>();
    HashMap<Integer,Zombie> zombieHashMap= new HashMap<>();
    HashMap<Integer,Humano> humanoHashMap = new HashMap<>();
    HashMap<Integer,Equipamento> equipamentoHashMap = new HashMap<>();


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

                        int idZ = Integer.parseInt(dadosCriatura[0]);
                        int idTipoZ = 0;
                        String nomeZ =dadosCriatura[2];
                        int xZ = Integer.parseInt(dadosCriatura[3]);
                        int yZ = Integer.parseInt(dadosCriatura[4]);



                        if (zombieHashMap.get(idZ) == null) {
                            Zombie zombie = new Zombie(idZ,idTipoZ,nomeZ,xZ,yZ);

                            zombieHashMap.put(idZ,zombie);
                            zombies.add(zombie);
                        }


                    } else if (Integer.parseInt(dadosCriatura[1])== 1){
                        //é humano

                        int idH = Integer.parseInt(dadosCriatura[0]);
                        int idTipoH = 0;
                        String nomeH =dadosCriatura[2];
                        int xH = Integer.parseInt(dadosCriatura[3]);
                        int yH = Integer.parseInt(dadosCriatura[4]);


                        if (humanoHashMap.get(idH) == null) {
                            Humano humano = new Humano(idH,idTipoH,nomeH,xH,yH);

                            humanoHashMap.put(idH,humano);
                            humanos.add(humano);
                        }
                    }

                }

                nrEquipamentos = Integer.parseInt(leitorFicheiro.nextLine());

                for (int k=0; k< nrEquipamentos; k++){

                    String[] dadosEquipamento = leitorFicheiro.nextLine().split(" : ");

                    int idE = Integer.parseInt(dadosEquipamento[0]);
                    int idTipoE = 1;
                    int xE = Integer.parseInt(dadosEquipamento[2]);
                    int yE = Integer.parseInt(dadosEquipamento[3]);


                    if (equipamentoHashMap.get(idE) == null) {
                        Equipamento equipamento = new Equipamento(idE,idTipoE,xE,yE);

                        equipamentoHashMap.put(idE,equipamento);
                        equipamentos.add(equipamento);
                    }



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
        return humanos;
    }

    public List<Zombie> getZombies(){
        return zombies;
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

        return authors;
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
        return day;
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId){
        return true;
        //n ta feita
    }

}
