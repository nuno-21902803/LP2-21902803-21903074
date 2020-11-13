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

    HashMap<Integer,Integer> criaturas = new HashMap<>();
    HashMap<Integer,Zombie> zombieHashMap= new HashMap<>();
    HashMap<Integer,Humano> humanoHashMap = new HashMap<>();
    HashMap<Integer,Equipamento> equipamentoHashMap = new HashMap<>();


    static  int numeroLinhas;
    static  int numeroColunas;
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



                        if (criaturas.get(idZ) == null) {
                            Zombie zombie = new Zombie(idZ,idTipoZ,nomeZ,xZ,yZ);

                            criaturas.put(idZ,idZ);
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

                            criaturas.put(idH,idH);
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
        Moves moves = new Moves(xO,yO);
        int idCriatura =0;
        boolean isHumano = false;
        boolean isZombie = false;


        //adquirir o id da criatura naquela posicao
        for (Zombie zombie1 : zombies){
            if (zombie1.cordenadaX() == xO && zombie1.cordenadaY() == yO){
                idCriatura = zombie1.getId();
                isZombie = true;
                break;
            }
        }

        if (!isZombie) {
            //se nao for zombie ele entra aqui para verificar se é humano
            for (Humano humano1 : humanos) {
                if (humano1.cordenadaX() == xO && humano1.cordenadaY() == yO) {
                    idCriatura = humano1.getId();
                    isHumano = true;
                    break;
                }
            }
        }
        //---

        //validar o movimento
        if (!moves.validarMove(xD, yD)){
            return false;
        }

        //ver se é humano ou zombie e atualizar a coordenada
        if(isHumano){
            humanoHashMap.get(idCriatura).colocarCoordenada(xD,yD);
            return true;
        }

        if (isZombie){
            zombies.get(idCriatura).colocarCoordenada(xD,yD);
            return true;
        }
        
        return false;
    }

    public boolean gameIsOver(){
        return nrDias == 3 || nrNoites == 3;
    }

    public List<String> getAuthors(){
        this.authors.add("Nuno Capela");
        this.authors.add("Francisco Lucas");

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
