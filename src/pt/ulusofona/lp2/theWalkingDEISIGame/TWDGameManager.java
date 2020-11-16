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
    ArrayList<String> authors = new ArrayList<>();
    ArrayList<Zombie> zombies = new ArrayList<>();
    ArrayList<Humano> humanos = new ArrayList<>();
    ArrayList<Equipamento> equipamentos = new ArrayList<>();

    HashMap<Integer, Integer> criaturas = new HashMap<>();
    HashMap<Integer, Zombie> zombieHashMap = new HashMap<>();
    HashMap<Integer, Humano> humanoHashMap = new HashMap<>();
    HashMap<Integer, Equipamento> equipamentoHashMap = new HashMap<>();


    static int numeroLinhas;
    static int numeroColunas;
    int idEquipaInicial;
    int idEquipaAtual;
    int nrTurnos = 0;
    int nrCriaturas = 0;
    int nrEquipamentos = 0;
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

                    if (Integer.parseInt(dadosCriatura[1]) == 0) {
                        //é zombie

                        int idZ = Integer.parseInt(dadosCriatura[0]);
                        int idTipoZ = Integer.parseInt(dadosCriatura[1]);
                        String nomeZ = dadosCriatura[2];
                        int xZ = Integer.parseInt(dadosCriatura[3]);
                        int yZ = Integer.parseInt(dadosCriatura[4]);

                        Zombie zombieAtual = new Zombie(idZ, idTipoZ, nomeZ, xZ, yZ);

                        if (criaturas.get(idZ) == null) {

                            criaturas.put(idZ, idTipoZ);
                            zombieHashMap.put(idZ, zombieAtual);
                            zombies.add(zombieAtual);

                        }


                    } else if (Integer.parseInt(dadosCriatura[1]) == 1) {

                        //é humano

                        int idH = Integer.parseInt(dadosCriatura[0]);
                        int idTipoH = Integer.parseInt(dadosCriatura[1]);
                        String nomeH = dadosCriatura[2];
                        int xH = Integer.parseInt(dadosCriatura[3]);
                        int yH = Integer.parseInt(dadosCriatura[4]);

                        Humano humanoAtual = new Humano(idH, idTipoH, nomeH, xH, yH);

                        if (criaturas.get(idH) == null) {

                            criaturas.put(idH, idTipoH);
                            humanoHashMap.put(idH, humanoAtual);

                            humanos.add(humanoAtual);

                        }
                    }

                }

                nrEquipamentos = Integer.parseInt(leitorFicheiro.nextLine());

                for (int k = 0; k < nrEquipamentos; k++) {

                    String[] dadosEquipamento = leitorFicheiro.nextLine().split(" : ");

                    int idE = Integer.parseInt(dadosEquipamento[0]);
                    int idTipoE = Integer.parseInt(dadosEquipamento[1]);
                    int xE = Integer.parseInt(dadosEquipamento[2]);
                    int yE = Integer.parseInt(dadosEquipamento[3]);

                    Equipamento equipamento = new Equipamento(idE, idTipoE, xE, yE);

                    if (equipamentoHashMap.get(idE) == null) {

                        equipamentoHashMap.put(idE, equipamento);
                        equipamentos.add(equipamento);

                    }

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

    public List<Humano> getHumans() {
        return humanos;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public boolean move(int xO, int yO, int xD, int yD) {
        Moves moves = new Moves(xO, yO, xD, yD);
        int idCriatura = 0;
        boolean isHumano = false;
        boolean isZombie = false;


        //adquirir o id da criatura naquela posicao
        for (Zombie zombie1 : zombies) {
            if (zombie1.cordenadaX() == xO && zombie1.cordenadaY() == yO) {
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

        //validar o movimento (se sai do grid, ou se não é horizontal ou vertical)
        if (!moves.validarMove(xD, yD, xO, yO)) {
            return false;
        }

        //verificar se existem criaturas na posicao pretendida
        for (Zombie zombie1 : zombies) {
            if (zombie1.cordenadaX() == xD && zombie1.cordenadaY() == yD) {
                return false;
            }
        }


        //se nao for zombie ele entra aqui para verificar se existe la um humano
        for (Humano humano1 : humanos) {
            if (humano1.cordenadaX() == xD && humano1.cordenadaY() == yD) {
                return false;
            }
        }

        //---------------

        //ver se é humano ou zombie e atualizar a coordenada
        if (isHumano && idEquipaAtual == 0) {
            humanoHashMap.get(idCriatura).colocarCoordenada(xD, yD);

            int xH = humanoHashMap.get(idCriatura).cordenadaX();
            int yH = humanoHashMap.get(idCriatura).cordenadaY();

            //se existir equipamento naquelas coordenadas ele adiciona e remove da lista
            if (existEquipment(xH,yH) != null && humanoHashMap.get(idCriatura).equipamentosApanhados.isEmpty()){
                humanoHashMap.get(idCriatura).equipamentosApanhados.add(existEquipment(xH,yH));
                existEquipment(xH,yH).apanhado = true;

                equipamentos.removeIf(equipamento -> equipamento.apanhado);

                //caso contrario se ele ja tiver um equipamento ele adiciona o da casa seguinte e dropa o antigo-
            } else if (existEquipment(xH,yH) != null && !humanoHashMap.get(idCriatura).equipamentosApanhados.isEmpty()){
                Equipamento equipamentoAnterior = new Equipamento();

                for (Equipamento equipamento : humanoHashMap.get(idCriatura).equipamentosApanhados){
                    if (equipamento.cordenadaX() == xO && equipamento.cordenadaY() == yO){
                        equipamentoAnterior = equipamento;
                    }
                }


                humanoHashMap.get(idCriatura).equipamentosApanhados.add(existEquipment(xH,yH));
                existEquipment(xH,yH).apanhado = true;
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
            idEquipaAtual = 1;
            return true;
        }

        //ver se é zombie e atualiza a coordenada
        if (isZombie && idEquipaAtual == 1) {
            zombieHashMap.get(idCriatura).colocarCoordenada(xD, yD);

            int xZ = zombieHashMap.get(idCriatura).cordenadaX();
            int yZ = zombieHashMap.get(idCriatura).cordenadaY();

            //se existir equipamento naquelas coordenadas ele adiciona
            if (existEquipment(xZ,yZ) != null){
                zombieHashMap.get(idCriatura).equipamentosDestruidos.add(existEquipment(xZ,yZ));
                existEquipment(xZ,yZ).destruido= true;

                equipamentos.removeIf(equipamento -> equipamento.destruido);


            }

            nrTurnos++;
            if (nrTurnos % 2 == 0) {
                //se forem multiplos de 2 muda o dia
                day = !day;
            }
            //muda o id da equipa
            idEquipaAtual = 0;
            return true;
        }

        return false;
    }

    public Equipamento existEquipment(int x0, int y0){

        for (Equipamento equipamento : equipamentos){
            if (equipamento.cordenadaX() == x0 && equipamento.cordenadaY() == y0 ){
                if (!equipamento.apanhado && !equipamento.destruido) {
                    return equipamento;
                }
            }
        }

        return null;
    }

    public boolean gameIsOver() {
        return nrTurnos >= 12;
    }

    public List<String> getAuthors() {
        this.authors.add("Nuno Capela");
        this.authors.add("Francisco Lucas");

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
        for (Zombie zombie1 : zombies) {
            if (zombie1.cordenadaX() == x && zombie1.cordenadaY() == y) {
                idCriatura = zombie1.getId();
                isZombie = true;
                break;
            }
        }

        if (!isZombie) {
            //se nao for zombie ele entra aqui para verificar se é humano
            for (Humano humano1 : humanos) {
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
                    //break;
                }
            }
        }
        //---

        return idCriatura;
    }

    public List<String> getSurvivors() {
        ArrayList<String> survivors = new ArrayList<>();
        //fazer a string
        survivors.add("Nr. de turnos terminados:\n");
        survivors.add(nrTurnos + "\n\n");
        survivors.add("OS VIVOS\n");

        for (Humano humano : humanos) {
            survivors.add(humano.id + " " + humano.nome + "\n");
        }

        survivors.add("\n");
        survivors.add("OS OUTROS\n");

        for (Zombie zombie : zombies) {
            survivors.add(zombie.id + "  (antigamente conhecido como " + zombie.nome + ")");
        }

        return survivors;
    }

    public boolean isDay() {
        return day;
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId) {

        //entra aqui para verificar se o humano contém
        for (Humano humano1 : humanos) {
            if (humano1.getId() == creatureId) {
                for (Equipamento equipamento : humano1.getEquipamentosApanhados()) {
                    if (equipamento.getTypeID() == equipmentTypeId) {
                        return true;
                    }
                }
            }
        }


        //nao havia criaturas com id do equipamentoTYPE
        return false;
    }

}
