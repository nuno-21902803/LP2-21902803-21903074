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

    //--creatures
    ArrayList<Creature> zombies = new ArrayList<>();
    ArrayList<Creature> humanos = new ArrayList<>();
    ArrayList<Equipamento> equipamentos = new ArrayList<>();
    //--havens
    ArrayList<Integer> safeCreaturesID= new ArrayList<>();
    ArrayList<SafeHaven> safeHavens = new ArrayList<>();

    //--Hashs
    HashMap<Integer, Creature> criaturas = new HashMap<>();
    HashMap<Integer, Creature> zombieHashMap = new HashMap<>();

    //todo ver as cenas de is alive
    static HashMap<Integer, Humano> humanoHashMap = new HashMap<>();

    HashMap<Integer, Equipamento> equipamentoHashMap = new HashMap<>();


    static int numeroLinhas;
    static int numeroColunas;
    int idEquipaInicial;
    int idEquipaAtual;
    int nrTurnos = 0;
    int nrCriaturas = 0;
    int nrEquipamentos = 0;
    int nrSafeHavens = 0;
    boolean day = true;

    public TWDGameManager() {
    }

    public boolean startGame(File ficheiroInicial) {

        try {
            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiroInicial));
            while (leitorFicheiro.hasNextLine()) {

                String linha = leitorFicheiro.nextLine();
                String[] dados = linha.split(" ");

                if (dados.length == 2) {
                    numeroLinhas = Integer.parseInt(dados[0].trim());
                    numeroColunas = Integer.parseInt(dados[1].trim());
                }

                idEquipaInicial = Integer.parseInt(leitorFicheiro.nextLine());
                idEquipaAtual = idEquipaInicial;
                nrCriaturas = Integer.parseInt(leitorFicheiro.nextLine());


                for (int x = 0; x < nrCriaturas; x++) {

                    String[] dadosCriatura = leitorFicheiro.nextLine().split(" : ");
                    //fazer o split da linha com os " : "
                    //e guardar os dados nos objetos e add na lista respetiva

                    if (Integer.parseInt(dadosCriatura[1]) >=0 && Integer.parseInt(dadosCriatura[1]) <= 4) {
                        //é zombie

                        int idZ = Integer.parseInt(dadosCriatura[0]);
                        int idTipoZ = Integer.parseInt(dadosCriatura[1]);
                        String nomeZ = dadosCriatura[2];
                        int xZ = Integer.parseInt(dadosCriatura[3]);
                        int yZ = Integer.parseInt(dadosCriatura[4]);
                        ArrayList<Equipamento> equipamentos = new ArrayList<>();
                       // System.out.println(idZ +"   -   "+ idTipoZ+ " ->" +creatureTYPE_ID(idTipoZ));

                        Creature zombieAtual =
                                new Zombie(idZ, idTipoZ,creatureTYPE_ID(idTipoZ),"Os Outros",
                                        nomeZ, xZ, yZ,equipamentos);

                        if (criaturas.get(idZ) == null) {

                            criaturas.put(idZ, zombieAtual);
                            zombieHashMap.put(idZ, zombieAtual);
                            zombies.add(zombieAtual);

                        }


                    } else if (Integer.parseInt(dadosCriatura[1]) >=5 && Integer.parseInt(dadosCriatura[1]) <= 10) {

                        //é humano

                        int idH = Integer.parseInt(dadosCriatura[0]);
                        int idTipoH = Integer.parseInt(dadosCriatura[1]);
                        String nomeH = dadosCriatura[2];
                        int xH = Integer.parseInt(dadosCriatura[3]);
                        int yH = Integer.parseInt(dadosCriatura[4]);
                        ArrayList<Equipamento> equipamentos = new ArrayList<>();

                        //System.out.println(idH +"   -   "+ idTipoH+ " ->" +creatureTYPE_ID(idTipoH));
                        Creature humanoAtual =
                                new Humano(idH, idTipoH, creatureTYPE_ID(idTipoH)
                                        ,"Os Vivos",nomeH, xH, yH,equipamentos,false,false);

                        if (criaturas.get(idH) == null) {

                            criaturas.put(idH, humanoAtual);
                            humanoHashMap.put(idH, (Humano) humanoAtual);

                            humanos.add(humanoAtual);

                        }
                    }

                }

                nrEquipamentos = Integer.parseInt(leitorFicheiro.nextLine());

                //adicionar os equipamentos nas estruturas
                for (int k = 0; k < nrEquipamentos; k++) {

                    String[] dadosEquipamento = leitorFicheiro.nextLine().split(" : ");


                    int idE = Integer.parseInt(dadosEquipamento[0]);
                    int idTipoE = Integer.parseInt(dadosEquipamento[1]);
                    int xE = Integer.parseInt(dadosEquipamento[2]);
                    int yE = Integer.parseInt(dadosEquipamento[3]);

                    Equipamento equipamento = new Equipamento(idE, idTipoE, xE, yE);
                    //System.out.println(equipamento);
                    if (equipamentoHashMap.get(idE) == null) {

                        equipamentoHashMap.put(idE, equipamento);
                        equipamentos.add(equipamento);

                    }

                }

                nrSafeHavens = Integer.parseInt(leitorFicheiro.nextLine());

                //adicionar as safeHavens
                for (int k = 0; k < nrSafeHavens; k++){
                    String[] dadosSafeHaven = leitorFicheiro.nextLine().split(" : ");

                    int shX = Integer.parseInt(dadosSafeHaven[0]);
                    int shY = Integer.parseInt(dadosSafeHaven[1]);

                    SafeHaven safeHaven = new SafeHaven(shX,shY);
                    safeHavens.add(safeHaven);
                }

            }
            leitorFicheiro.close();
            return true;

        } catch (FileNotFoundException exception) {
            return false;
        }
    }

    public int[] getWorldSize() {
        return new int[]{numeroLinhas, numeroColunas};
    }

    public int getInitialTeam() {
        return idEquipaInicial;
    }

    public List<Creature> getCreatures(){

        ArrayList<Creature> creatures = new ArrayList<>(zombies);
        creatures.addAll(humanos);

        return creatures;
    }

    public boolean isDoorToSafeHaven(int x, int y){
        for (SafeHaven sh : safeHavens){
            if (sh.getX() == x && sh.getY() == y){
                return true;
            }
        }

        return false;
    }

    public List<Integer> getIdsInSafeHaven(){
        return safeCreaturesID;
    }

    //TODO fazer as restricoes NA CLASSE MOVE para cada criatura etc...
    public boolean move(int xO, int yO, int xD, int yD) {

        int idCriatura = 0;
        boolean isHumano = false;
        boolean isZombie = false;


        //adquirir o id da criatura naquela posicao
        for (Creature zombie1 : zombies) {
            if (zombie1.cordenadaX() == xO && zombie1.cordenadaY() == yO) {
                idCriatura = zombie1.getId();
                isZombie = true;
                break;
            }
        }

        if (!isZombie) {
            //se nao for zombie ele entra aqui para verificar se é humano
            for (Creature humano1 : humanos) {
                if (humano1.cordenadaX() == xO && humano1.cordenadaY() == yO) {
                    idCriatura = humano1.getId();
                    isHumano = true;
                    break;
                }
            }
        }
        //---

        Moves moves = new Moves(xO, yO, xD, yD,idCriatura);
        //validar o movimento (se sai do grid, ou se não é horizontal ou vertical)
        if (!moves.validarMove(xD, yD, xO, yO,idCriatura)) {
            return false;
        }

        //verificar se existem criaturas na posicao pretendida
        for (Creature zombie1 : zombies) {
            if (zombie1.cordenadaX() == xD && zombie1.cordenadaY() == yD) {
                return false;
            }
        }


        //se nao for zombie ele entra aqui para verificar se existe la um humano
        for (Creature humano1 : humanos) {
            if (humano1.cordenadaX() == xD && humano1.cordenadaY() == yD) {
                return false;
            }
        }

        if(isDoorToSafeHaven(xD,yD)){
            humanoHashMap.get(idCriatura).setSafe(true);
            humanoHashMap.get(idCriatura).colocarCoordenada(xD, yD);
            safeCreaturesID.add(idCriatura);
            idEquipaAtual = 20;
            return true;
        }


        //---------------


        //ver se é humano ou zombie e atualizar a coordenada
        if (isHumano && idEquipaAtual == 10) {
            humanoHashMap.get(idCriatura).colocarCoordenada(xD, yD);

            int xH = humanoHashMap.get(idCriatura).cordenadaX();
            int yH = humanoHashMap.get(idCriatura).cordenadaY();

            //se existir equipamento naquelas coordenadas ele adiciona e remove da lista
            if (existEquipment(xH, yH) != null && humanoHashMap.get(idCriatura).getEquipamentosList().isEmpty()) {
                humanoHashMap.get(idCriatura).getEquipamentosList().add(existEquipment(xH, yH));
                existEquipment(xH, yH).apanhado = true;

                equipamentos.removeIf(equipamento -> equipamento.apanhado);

                //caso contrario se ele ja tiver um equipamento ele adiciona o da casa seguinte e dropa o antigo-
            } else if (existEquipment(xH, yH) != null && !humanoHashMap.get(idCriatura).getEquipamentosList().isEmpty()) {
                Equipamento equipamentoAnterior = new Equipamento();

                for (Equipamento equipamento : humanoHashMap.get(idCriatura).getEquipamentosList()) {
                    if (equipamento.cordenadaX() == xO && equipamento.cordenadaY() == yO) {
                        equipamentoAnterior = equipamento;
                    }
                }


                humanoHashMap.get(idCriatura).getEquipamentosList().add(existEquipment(xH, yH));
                existEquipment(xH, yH).apanhado = true;
                equipamentos.removeIf(equipamento -> equipamento.apanhado);
                equipamentos.add(equipamentoAnterior);
                equipamentoAnterior.apanhado = false;


            }

            nrTurnos++;
            if (nrTurnos % 2 == 0) {
                //se forem multiplos de 2 muda o dia
                day = !day;
            }
            //muda o id da equipa atual
            idEquipaAtual = 20;
            return true;
        }

        //ver se é zombie e atualiza a coordenada
        if (isZombie && idEquipaAtual == 20) {
            zombieHashMap.get(idCriatura).colocarCoordenada(xD, yD);

            int xZ = zombieHashMap.get(idCriatura).cordenadaX();
            int yZ = zombieHashMap.get(idCriatura).cordenadaY();

            //se existir equipamento naquelas coordenadas ele adiciona
            if (existEquipment(xZ, yZ) != null) {
                zombieHashMap.get(idCriatura).getEquipamentosList().add(existEquipment(xZ, yZ));
                existEquipment(xZ, yZ).destruido = true;

                equipamentos.removeIf(equipamento -> equipamento.destruido);


            }

            nrTurnos++;
            if (nrTurnos % 2 == 0) {
                //se forem multiplos de 2 muda o dia
                day = !day;
            }
            //muda o id da equipa
            idEquipaAtual = 10;
            return true;
        }

        return false;
    }
    //----


    //TODO funcao de ataques
    public void attack(Creature humano, Creature zombie){

    }

    public Equipamento existEquipment(int x0, int y0) {

        for (Equipamento equipamento : equipamentos) {
            if (equipamento.cordenadaX() == x0 && equipamento.cordenadaY() == y0) {
                if (!equipamento.apanhado && !equipamento.destruido) {
                    return equipamento;
                }
            }
        }

        return null;
    }

    //TODO alterar as restricoes
    public boolean gameIsOver() {
        return nrTurnos >= 12;
    }

    public List<String> getAuthors() {
        ArrayList<String> authors = new ArrayList<>();

        authors.add("Nuno Capela");
        authors.add("Francisco Lucas");

        return authors;
    }

    public int getCurrentTeamId() {
        return idEquipaAtual;
    }

    public int getElementId(int x, int y) {
        int idCriatura = 0;
        boolean isZombie = false;
        boolean isHuman = false;

        //adquirir o id da criatura naquela posicao
        for (Creature zombie1 : zombies) {
            if (zombie1.cordenadaX() == x && zombie1.cordenadaY() == y) {
                idCriatura = zombie1.getId();
                isZombie = true;
                break;
            }
        }

        if (!isZombie) {
            //se nao for zombie ele entra aqui para verificar se é humano
            for (Creature humano1 : humanos) {
                if (humano1.cordenadaX() == x && humano1.cordenadaY() == y) {

                    idCriatura = humano1.getId();
                    isHuman = true;
                    break;
                }
            }
        }


        if (!isHuman) {

            for (Equipamento equipamento : equipamentos) {
                if (equipamento.cordenadaX() == x && equipamento.cordenadaY() == y) {
                    idCriatura = equipamento.getId();
                    break;
                }
            }
        }

        for (SafeHaven safeHaven : safeHavens){
            if (safeHaven.getX() == x && safeHaven.getY() == y) {
                idCriatura = 0;
                break;
            }
        }
        //---

        return idCriatura;
    }

    //TODO
    public List<String> getGameResults() {
        ArrayList<String> survivors = new ArrayList<>();
        //fazer a string
        survivors.add("Nr. de turnos terminados:\n");
        survivors.add(nrTurnos + "\n\n");
        survivors.add("OS VIVOS\n");

        for (Creature humano : humanos) {
            survivors.add(humano.getId() + " " + humano.getNome() + "\n");
        }

        survivors.add("\n");
        survivors.add("OS OUTROS\n");

        for (Creature zombie : zombies) {
            survivors.add(zombie.getId() + "  (antigamente conhecido como " + zombie.getNome()+ ")");
        }

        return survivors;
    }

    public boolean isDay() {
        return day;
    }

    public int getEquipmentTypeId(int equipmentId){
        return equipamentoHashMap.get(equipmentId).getTypeID();
    }

    public int getEquipmentId(int creatureId) {

        //entra aqui para verificar se o humano contém eq
        for (Creature humano1 : humanos) {
            if (humano1.getId() == creatureId) {
                for (Equipamento equipamento : humano1.getEquipamentosList()) {
                   return equipamento.getId();
                }
            }
        }


        //a creature nao tinha equipamentos
        return 0;
    }

    //TODO isto
    public String getEquipmentInfo(int equipmentId){
        Equipamento equipamento = equipamentoHashMap.get(equipmentId);
        String info = "";

        if (equipamento.getTypeID() == 0){ //é um escudo de madeira
            //tem protecao contra 1 strike de zombie
            info= equipamento.getId() +"aqui é os strikes left";
        } else if (equipamento.getTypeID() == 2){//é uma pistola PPK
            //tem 3 strikes contra zombies (3kills)
            info= equipamento.getId() +"aqui é os strikes left";
        } else if (equipamento.getTypeID() == 7){ //é lixivia 1L
            //para cada protecao contra zombies 0.3L
            info= equipamento.getId() +"aqui é os strikes left";
        } else {
            info= equipamento.getId()+"";
        }

        return info;
    }

    public String[] popCultureExtravaganza(){
        String[] answers = new String[13];
        answers[0] = "0";
        answers[1] = "1";
        answers[2] = "2";
        answers[3] = "3";
        answers[4] = "4";
        answers[5] = "5";
        answers[6] = "6";
        answers[7] = "7";
        answers[8] = "8";
        answers[9] = "9";
        answers[10] = "10";
        answers[11] = "11";
        answers[12] = "12";
        answers[13] = "13";

        return answers;
    }

    public String creatureTYPE_ID(int type){
        switch (type){
            case 0:
                return "Criança (Zombie)";
            case 1:
                return "Adulto (Zombie)";
            case 2:
                return "Militar (Zombie)";
            case 3:
                return "Idoso (Zombie)";
            case 4:
                return "Zombie Vampiro";
            case 5:
                return "Criança (Vivo)";
            case 6:
                return "Adulto (Vivo)";
            case 7:
                return "Militar (Vivo)";
            case 8:
                return "Idoso (Vivo)";
            case 9:
                return "Cão";
            case 10:
                return "Beta";
            default:
                throw new IllegalArgumentException("CreatureTYPEid Not Found");
        }
    }

    //TODO
    public boolean saveGame(File fich){
        return true;
    }

    //TODO
    public boolean loadGame(File fich){
        return true;
    }
}
