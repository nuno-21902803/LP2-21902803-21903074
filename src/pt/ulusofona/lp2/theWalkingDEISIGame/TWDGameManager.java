package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.io.*;
import java.util.*;



public class TWDGameManager {
    //game
    //vars static para aceder a outras classes

    //--havens
    ArrayList<Integer> safeCreaturesID= new ArrayList<>();
    ArrayList<SafeHaven> safeHavens = new ArrayList<>();

    //--HashsCreatures
    static HashMap<Integer, Creature> criaturas = new HashMap<>();

    //--equi
    HashMap<Integer, Equipamento> equipamentoHashMap = new HashMap<>();
    HashMap<String, List<String>> listMap = new HashMap<String, List<String>>();


    static int numeroLinhas;
    static int numeroColunas;
    int idEquipaAtual = 0;
    int idEquipaInicial = 0;
    int nrTurnos = 0;
    int nrTurnosTotal = 0; //ver se detetou alguma infecao no jogo

    //vars exceptions
    static int numCriaturas;
    static String linhaErro = "";
    static String[] dadosCriaturaStatic;


    boolean day = true;


    int xMorto = -1;
    int yMorto = -1;



    public TWDGameManager() {
    }

    public void startGame (File ficheiroInicial) throws InvalidTWDInitialFileException, FileNotFoundException {


            safeCreaturesID.clear();
            safeHavens.clear();
            //--HashsCreatures
            criaturas.clear();
            //--equi
            equipamentoHashMap.clear();
            numeroLinhas = 0;
            numeroColunas = 0;
            nrTurnosTotal = 0;
            idEquipaAtual = 0;
            idEquipaInicial = 0;
            nrTurnos = 0;
            day = true;
            xMorto = -1;
            yMorto = -1;
            linhaErro="";
            //clear das estruturas e vars



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

                int nrCriaturas = 0;
                nrCriaturas = Integer.parseInt(leitorFicheiro.nextLine());
                numCriaturas = nrCriaturas;

                if (!new InvalidTWDInitialFileException().validNrOfCreatures()) {

                    throw new InvalidTWDInitialFileException();
                }


                for (int x = 0; x < nrCriaturas; x++) {
                    if (leitorFicheiro.hasNextLine()) {
                        String[] dadosCriatura = leitorFicheiro.nextLine().split(" : ");


                        dadosCriaturaStatic = dadosCriatura;
                        int count = 0;
                        if (!new InvalidTWDInitialFileException().validCreatureDefinition()){

                            for (String s : dadosCriaturaStatic) {
                                linhaErro += s;
                                if (count<dadosCriaturaStatic.length-1) {
                                    linhaErro += " : ";
                                    count++;
                                }

                            }

                            throw new InvalidTWDInitialFileException();
                        }
                        //fazer o split da linha com os " : "
                        //e guardar os dados nos objetos e add na lista respetiva

                        if (Integer.parseInt(dadosCriatura[1]) >= 0 &&
                                Integer.parseInt(dadosCriatura[1]) <= 4 &&
                                dadosCriatura.length == 5) {
                            //é zombie

                            int idZ = Integer.parseInt(dadosCriatura[0]);
                            int idTipoZ = Integer.parseInt(dadosCriatura[1]);
                            String nomeZ = dadosCriatura[2];
                            int xZ = Integer.parseInt(dadosCriatura[3]);
                            int yZ = Integer.parseInt(dadosCriatura[4]);
                            Equipamento equipamentos = new Equipamento();


                            Zombie zombieAtual =
                                    new Zombie(idZ, idTipoZ, creatureTYPE_ID(idTipoZ), "Os Outros",
                                            nomeZ, xZ, yZ, equipamentos, 0, false,false,0);

                            criaturas.putIfAbsent(idZ, zombieAtual);


                        } else if (Integer.parseInt(dadosCriatura[1]) >= 5 &&
                                Integer.parseInt(dadosCriatura[1]) <= 9 &&
                                dadosCriatura.length == 5) {

                            //é humano

                            int idH = Integer.parseInt(dadosCriatura[0]);
                            int idTipoH = Integer.parseInt(dadosCriatura[1]);
                            String nomeH = dadosCriatura[2];
                            int xH = Integer.parseInt(dadosCriatura[3]);
                            int yH = Integer.parseInt(dadosCriatura[4]);
                            Equipamento equipamentos = new Equipamento();

                            Vivo vivoAtual =
                                    new Vivo(idH, idTipoH, creatureTYPE_ID(idTipoH)
                                            , "Os Vivos", nomeH, xH, yH, equipamentos,
                                            0, false, false,-1);

                            criaturas.putIfAbsent(idH, vivoAtual);
                        } else if (Integer.parseInt(dadosCriatura[1]) == 10) {
                            int idZ = Integer.parseInt(dadosCriatura[0]);
                            int idTipoZ = Integer.parseInt(dadosCriatura[1]);
                            String nomeZ = dadosCriatura[2];
                            int xZ = Integer.parseInt(dadosCriatura[3]);
                            int yZ = Integer.parseInt(dadosCriatura[4]);
                            Equipamento equipamentos = new Equipamento();


                            Zombie zombieAtual =
                                    new Zombie(idZ, idTipoZ, creatureTYPE_ID(idTipoZ), "Os Outros",
                                            nomeZ, xZ, yZ, equipamentos, 0, false,false,0);

                            criaturas.putIfAbsent(idZ, zombieAtual);
                        }
                    }
                }

                int nrEquipamentos = 0;
                nrEquipamentos = Integer.parseInt(leitorFicheiro.nextLine());

                //adicionar os equipamentos nas estruturas
                for (int k = 0; k < nrEquipamentos; k++) {
                    if (leitorFicheiro.hasNextLine()) {
                        String[] dadosEquipamento = leitorFicheiro.nextLine().split(" : ");


                        int idE = Integer.parseInt(dadosEquipamento[0]);
                        int idTipoE = Integer.parseInt(dadosEquipamento[1]);
                        int xE = Integer.parseInt(dadosEquipamento[2]);
                        int yE = Integer.parseInt(dadosEquipamento[3]);
                        ArrayList<Integer> c = new ArrayList<>();
                        Equipamento equipamento = new Equipamento(idE, idTipoE, equipmentTYPE_ID(idTipoE), xE, yE, c
                        ,0);

                        equipamentoHashMap.putIfAbsent(idE, equipamento);
                        equipmentSTRIKESsetter(idTipoE, idE);
                    }
                }


                int nrSafeHavens = 0;
                if (leitorFicheiro.hasNextLine()) {
                    nrSafeHavens = Integer.parseInt(leitorFicheiro.nextLine());
                }

                //adicionar as safeHavens
                for (int k = 0; k < nrSafeHavens; k++) {
                    if (leitorFicheiro.hasNextLine()) {
                        String[] dadosSafeHaven = leitorFicheiro.nextLine().split(" : ");

                        int shX = Integer.parseInt(dadosSafeHaven[0]);
                        int shY = Integer.parseInt(dadosSafeHaven[1]);

                        SafeHaven safeHaven = new SafeHaven(shX, shY);
                        safeHavens.add(safeHaven);
                    }

                }
            }

            leitorFicheiro.close();

        } catch (FileNotFoundException exception) {
            throw new FileNotFoundException();

        }
    }

    public int[] getWorldSize() {
        return new int[]{numeroLinhas, numeroColunas};
    }

    public int getInitialTeam() {
        return idEquipaInicial;
    }

    public List<Creature> getCreatures(){

        ArrayList<Creature> creatures = new ArrayList<>(criaturas.values());

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

    public boolean move(int xO, int yO, int xD, int yD) {
        //getGameStatistics();

        int idCriatura = 0;
        boolean isHumano = false;
        boolean isZombie = false;
        //fica true se for o zombie a atacar
        boolean zombieAttack = false;

        //adquirir o id da criatura naquela posicao
        for (Creature zombie1 : criaturas.values()) {
            if (zombie1 instanceof Zombie) {
                if (zombie1.cordenadaX() == xO && zombie1.cordenadaY() == yO) {
                    idCriatura = zombie1.getId();
                    isZombie = true;
                    break;
                }
            }
        }

        if (!isZombie) {
            //se nao for zombie ele entra aqui para verificar se é humano
            for (Creature humano1 : criaturas.values()) {
                if (humano1 instanceof Vivo) {
                    if (humano1.cordenadaX() == xO && humano1.cordenadaY() == yO) {
                        idCriatura = humano1.getId();
                        isHumano = true;
                        break;
                    }
                }
            }
        }
        //---

        Moves moves = new Moves(xO, yO, xD, yD,idCriatura);

        //validar o movimento
        if (isHumano) {
            Vivo vivo = (Vivo) criaturas.get(idCriatura);
            if (!moves.validarMove(xD, yD, xO, yO, criaturas.get(idCriatura).getIdTipo(),day) ||
                    vivo.getIsSafe()) {
                return false;
            }
        } else if (isZombie){
            if (!moves.validarMove(xD, yD, xO, yO, criaturas.get(idCriatura).getIdTipo(),day)) {
                return false;
            }
        } else {
            return false;
        }


        if(isDoorToSafeHaven(xD,yD)){
            if (!isZombie) {
                Vivo vivo = (Vivo) criaturas.get(idCriatura);
                vivo.setSafe(true);
                vivo.colocarCoordenada(xMorto-100, yMorto-100);
                safeCreaturesID.add(idCriatura);

                nrTurnosTotal++;
                nrTurnos++;
                if (nrTurnosTotal % 2 == 0) {
                    //se forem multiplos de 2 muda o dia
                    day = !day;
                }
                idEquipaAtual = 20;
                return true;
            }
            return false;
        }

        //aumentar o nrTuros envenenado

        //entra aqui para verificar se existe la um humano
        //ya pt sou o zombie a atacar
        for (Creature vivo1 : criaturas.values()) {
            if (vivo1 instanceof Vivo) {
                if (vivo1.cordenadaX() == xD && vivo1.cordenadaY() == yD) {
                    if (isHumano) {
                        return false;
                    } else {
                        //Se a creatura nao for humana ele entra aqui
                        for (Creature zombie1 : criaturas.values()) {
                            if (zombie1 instanceof Zombie) {
                                if (zombie1.cordenadaX() == xO && zombie1.cordenadaY() == yO) {
                                    zombieAttack = true;
                                    //FUNCAO ATTACK/DEFENSE
                                    if (vivo1.getIdTipo() == 9) {
                                        //Nenhum zombie pode-se mover para cima de um cao
                                        return false;
                                    }
                                    if (vivo1.getEquipamento().getTypeID() != -23) {
                                        int type = -1;
                                        System.out.println(vivo1);
                                        type = vivo1.getEquipamento().getTypeID();
                                        if (type != -1) {
                                            switch (type) {
                                                case 0:
                                                case 3:
                                                case 4:
                                                case 5:
                                                case 7:
                                                case 8:
                                                case 9:
                                                case 10:
                                                    if (!defense((Vivo) vivo1, (Zombie) zombie1, true)) {
                                                        //nao sucedeu na defesa
                                                        //foi para zombie
                                                        //mudar id para idzombie respetivo
                                                        vivo1.setIdTipo(vivo1.getIdTipo() - 5);
                                                        //zombie tmp para colocar no hash
                                                        //System.out.println("FOI AQUI");
                                                        int num = zombie1.getTransformacoes();
                                                        zombie1.setTransformacoes(num+1);
                                                        Zombie tmp = new Zombie(vivo1.getId(), vivo1.getIdTipo(),
                                                                creatureTYPE_ID(vivo1.getIdTipo()),
                                                                "Os Outros", vivo1.getNome(),
                                                                vivo1.cordenadaX(), vivo1.cordenadaY(),
                                                                new Equipamento(), 0, false,false,0);
                                                        criaturas.put(vivo1.getId(), tmp);
                                                        //remove dos humanos
                                                        idEquipaAtual = 10;

                                                        //houve uma infeção logo nrTurnos volta para 0.
                                                        nrTurnosTotal++;
                                                        nrTurnos++;
                                                        if (nrTurnosTotal % 2 == 0) {
                                                            day = !day;
                                                        }

                                                        nrTurnos = 0;
                                                        return true;
                                                    }

                                                    nrTurnosTotal++;
                                                    nrTurnos++;
                                                    if (nrTurnosTotal % 2 == 0) {
                                                        //se forem multiplos de 2 muda o dia
                                                        day = !day;
                                                    }
                                                    idEquipaAtual = 10;
                                                    return true;

                                                case 1:
                                                case 2:
                                                    if (zombie1.getIdTipo() == 4) {
                                                        //pistolas nao podem ser usadas contra zombies vampiros
                                                        return false;
                                                    }

                                                case 6:
                                                    if (!attack((Vivo) vivo1, (Zombie) zombie1, true)) {
                                                        //nao sucedeu no ataque
                                                        //foi para zombie
                                                        //mudar id para idzombie respetivo
                                                        vivo1.setIdTipo(vivo1.getIdTipo() - 5);
                                                        //zombie tmp para colocar no hash
                                                        //System.out.println("FOI AQUI 2");
                                                        int num = zombie1.getTransformacoes();
                                                        ( zombie1).setTransformacoes(num+1);
                                                        Zombie tmp = new Zombie(vivo1.getId(), vivo1.getIdTipo(),
                                                                creatureTYPE_ID(vivo1.getIdTipo()),
                                                                "Os Outros", vivo1.getNome(),
                                                                vivo1.cordenadaX(), vivo1.cordenadaY(),
                                                                new Equipamento(), 0, false,false,0);

                                                        criaturas.put(vivo1.getId(), tmp);

                                                        idEquipaAtual = 10;

                                                        //houve uma infeção logo nrTurnos volta para 0.
                                                        nrTurnosTotal++;
                                                        nrTurnos++;
                                                        if (nrTurnosTotal % 2 == 0) {
                                                            day = !day;
                                                        }
                                                        nrTurnos = 0;
                                                        return true;
                                                    }

                                                    nrTurnosTotal++;
                                                    nrTurnos++;
                                                    if (nrTurnosTotal % 2 == 0) {
                                                        //se forem multiplos de 2 muda o dia
                                                        day = !day;
                                                    }
                                                    idEquipaAtual = 10;
                                                    return true;
                                            }
                                        }
                                    } else {

                                        //nao tinha equipamentos entao vai para zombie
                                        //foi para zombie
                                        //mudar id para idzombie respetivo
                                        vivo1.setIdTipo(vivo1.getIdTipo() - 5);
                                        //zombie tmp para colocar no hash
                                        //System.out.println("FOI AQUI 3");
                                        int num =  zombie1.getTransformacoes();
                                         zombie1.setTransformacoes(num+1);
                                        Zombie tmp = new Zombie(vivo1.getId(), vivo1.getIdTipo(),
                                                creatureTYPE_ID(vivo1.getIdTipo()),
                                                "Os Outros", vivo1.getNome(),
                                                vivo1.cordenadaX(), vivo1.cordenadaY(),
                                                new Equipamento(), 0, false,false,0);

                                        criaturas.put(vivo1.getId(), tmp);

                                        nrTurnosTotal++;
                                        nrTurnos++;
                                        if (nrTurnosTotal % 2 == 0) {
                                            //se forem multiplos de 2 muda o dia
                                            day = !day;
                                        }
                                        nrTurnos = 0;
                                        idEquipaAtual = 10;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //verificar se existem criaturas na posicao pretendida
        //ya pt sou o humano a atacar
        for (Creature zombie1 : criaturas.values()) {
            if (zombie1 instanceof Zombie) {
                if (zombie1.cordenadaX() == xD && zombie1.cordenadaY() == yD) {

                    if (isZombie) {
                        return false;
                    } else {

                        //Se a creatura nao for zombie ele entra aqui
                        for (Creature humano1 : criaturas.values()) {
                            if (humano1 instanceof Vivo) {
                                if (humano1.cordenadaX() == xO && humano1.cordenadaY() == yO) {
                                    //se nao tiver equipamentos nenhuns nao entra
                                    if (humano1.getEquipamento().getTypeID() != -23) {

                                        //serva para ver se o eq do humano tem strikesLeft
                                        if (humano1.getEquipamento().getStrikesLEFT() == 0){
                                            return false;
                                        }
                                        int type = -1;
                                        type = humano1.getEquipamento().getTypeID();

                                        if (type != -1) {
                                            switch (type) {
                                                case 0:
                                                case 3:
                                                case 4:
                                                case 5:
                                                case 7:
                                                case 8:
                                                case 9:
                                                    //nao tinha equipamentos de ataque
                                                    return false;
                                                case 10:
                                                case 1:
                                                    if (humano1.getIdTipo() == 5 && zombie1.getIdTipo() != 0) {
                                                        //criança humana nao pode lutar com nao criancas zombies
                                                        return false;
                                                    }
                                                case 2:
                                                    if (zombie1.getIdTipo() == 4) {
                                                        //pistolas nao podem ser usadas contra zombies vampiros
                                                        return false;
                                                    }
                                                case 6:
                                                    if (!attack((Vivo) criaturas.get(idCriatura), (Zombie) zombie1,
                                                            zombieAttack)) {
                                                        //nao sucedeu no ataque
                                                        //foi para zombie
                                                        //mudar id para idzombie respetivo
                                                        //System.out.println("FOI AQUI 4");
                                                        humano1.setIdTipo(humano1.getIdTipo() - 5);
                                                        int num =  zombie1.getTransformacoes();
                                                        zombie1.setTransformacoes(num+1);
                                                        //zombie tmp para colocar no hash
                                                        Zombie tmp = new Zombie(humano1.getId(), humano1.getIdTipo(),
                                                                creatureTYPE_ID(humano1.getIdTipo()),
                                                                "Os Outros", humano1.getNome(),
                                                                humano1.cordenadaX(), humano1.cordenadaY(),
                                                                new Equipamento(), 0, false,false,0);

                                                        criaturas.put(humano1.getId(), tmp);

                                                        idEquipaAtual = 20;

                                                        //houve uma infeção logo nrTurnos volta para 0.
                                                        nrTurnosTotal++;
                                                        nrTurnos++;
                                                        if (nrTurnosTotal % 2 == 0) {
                                                            day = !day;
                                                        }

                                                        nrTurnos = 0;
                                                        return true;

                                                    }

                                                    nrTurnosTotal++;
                                                    nrTurnos++;
                                                    if (nrTurnosTotal % 2 == 0) {
                                                        //se forem multiplos de 2 muda o dia
                                                        day = !day;
                                                    }
                                                    idEquipaAtual = 20;
                                                    return true;
                                            }
                                        }
                                    } else {
                                        //humano nao tinha defesa
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //ver se é humano ou zombie e atualizar a coordenada
        if (isHumano && idEquipaAtual == 10) {

            //ve se algum humano morre envenenado
            for (Creature vivo2 : criaturas.values()){
                if (vivo2 instanceof Vivo) {
                    Vivo vivo1 = (Vivo) vivo2;
                    if (vivo1.getTomouVeneno()) {
                        if (vivo1.getNrTurnosEnvenenados() > 2) {
                            vivo1.setDeadVeneno(true);
                            //faz a criatura desaparecer do jogo
                            criaturas.get(vivo1.getId()).colocarCoordenada(xMorto,yMorto);
                            xMorto--;
                            yMorto--;
                        }
                        int nr = vivo1.getNrTurnosEnvenenados();
                        vivo1.setNrTurnosEnvenenados(nr + 1);
                        break;
                    }
                }
            }

            Vivo vivo = (Vivo) criaturas.get(idCriatura);

            vivo.colocarCoordenada(xD, yD);

            int xH = vivo.cordenadaX();
            int yH = vivo.cordenadaY();
            boolean idoso = false;


            //Caso especifico para o idosoVIVO
            //se o idoso for para uma casa com equipamento
            if (vivo.getIdTipo() == 8 && existEquipment(xH,yH) != null){
                idoso = true;
                Equipamento equipamento = existEquipment(xH,yH);


                if (!vivo.getTomouVeneno() && equipamento.getTypeID() == 9){
                    vivo.colocarCoordenada(xO,yO);
                    //nao estava envenenado
                    return false;
                }

                //se ele tiver 1 equipamento com ele
                if (vivo.getEquipamento() != null){
                    Equipamento eqAnt = vivo.getEquipamento();
                    eqAnt.setX(xO);
                    eqAnt.setY(yO);
                }


                vivo.setEquipamento(equipamento);
                equipamentoHashMap.get(equipamento.getId()).setX(xMorto);
                equipamentoHashMap.get(equipamento.getId()).setY(yMorto);
                xMorto--;
                yMorto--;

                int num = vivo.getEquipamentos();
                vivo.setNumEqApanhadosDestruidos(num+1);

                //ver se foi veneno e começar a contar
                if (equipamento.getTypeID() == 8){
                    if (!vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 1){
                        vivo.setTomouVeneno(true);
                        vivo.setNrTurnosEnvenenados(1);
                        equipamento.setStrikesLEFT(0);
                    } else if (vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 1){
                        int nr = vivo.getNrTurnosEnvenenados();
                        vivo.setNrTurnosEnvenenados(nr +1 );
                        equipamento.setStrikesLEFT(0);
                    }
                } else if (equipamento.getTypeID() == 9){
                    if (vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 1){
                        //atualiza os usos left e mete o vivo curado
                        equipamento.setStrikesLEFT(0);
                        vivo.setTomouVeneno(false);

                    } else if (vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 0){

                    }
                }

                vivo.getEquipamento().getApanhadoPorCreaturesID().add(vivo.getId());
            } else if (vivo.getIdTipo() == 8 && existEquipment(xH,yH) == null){
                idoso = true;
                //se o idoso tiver um equipamento nele e a pos para onde vai nao tiver nenhum
                if (vivo.getEquipamento() != null){
                    Equipamento equipamento = vivo.getEquipamento();
                    //add na lista global

                    if (!vivo.getTomouVeneno() && equipamento.getTypeID() == 9){
                        vivo.colocarCoordenada(xO,yO);
                        //nao estava envenenado
                        return false;
                    }

                    //equipamentoHashMap.put(equipamento.getId(),equipamento);

                    //se ele tiver um equipamento tirar o mesmo
                    vivo.setEquipamento(new Equipamento());
                    equipamento =equipamentoHashMap.get(equipamento.getId());

                    //colocar as coordenadas da pos anterior do idoso
                    equipamento.setX(xO);
                    equipamento.setY(yO);

                    //ver se foi veneno e começar a contar
                    if (equipamento.getTypeID() == 8 ){
                        if (!vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 1){
                            vivo.setTomouVeneno(true);
                            vivo.setNrTurnosEnvenenados(1);
                            equipamento.setStrikesLEFT(0);
                        } else if (vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 1){
                            int nr = vivo.getNrTurnosEnvenenados();
                            vivo.setNrTurnosEnvenenados(nr +1 );
                            equipamento.setStrikesLEFT(0);
                        }
                    } else if (equipamento.getTypeID() == 9){
                        if (vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 1){
                            //atualiza os usos left e mete o vivo curado
                            equipamento.setStrikesLEFT(0);
                            vivo.setTomouVeneno(false);

                        } else if (vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 0){
                            return true;
                        } else if (!vivo.getTomouVeneno()){
                            //nao estava envenenado
                            vivo.colocarCoordenada(xO,yO);
                            return false;
                        }
                    }
                }
            }
            //-------------

            //se existir equipamento naquelas coordenadas ele adiciona e remove da lista
            if (existEquipment(xH, yH) != null && vivo.getEquipamento().getTypeID() == -23 && !idoso) {
                Equipamento equipamento = existEquipment(xH,yH);

                if (!vivo.getTomouVeneno() && equipamento.getTypeID() == 9){
                    vivo.colocarCoordenada(xO,yO);
                    //nao estava envenenado
                    return false;
                }

                //adiciona o equipamento no humano
                vivo.setEquipamento(equipamento);
                //remove o equipamento da lista global
                equipamentoHashMap.get(equipamento.getId()).setX(xMorto);
                equipamentoHashMap.get(equipamento.getId()).setY(yMorto);
                xMorto--;
                yMorto--;

                int num = vivo.getEquipamentos();
                vivo.setNumEqApanhadosDestruidos(num+1);

                //verificar se foi apanhado por mais que um militar
                //se for escudo de madeira
                if (vivo.getEquipamento().getTypeID() == 0 && vivo.getIdTipo() == 7){
                    for (int k : vivo.getEquipamento().getApanhadoPorCreaturesID()){
                        //se for militar
                        if (criaturas.get(k).getIdTipo() == 7) {
                            //mas se nao for o mesmo militar e se foi usado por outro
                            if (k != vivo.getId() && vivo.getEquipamento().getIsUsadoPorMilitar()) {
                                //tem 1 strike left
                                vivo.getEquipamento().setStrikesLEFT(1);
                            } else if (!vivo.getEquipamento().getIsUsadoPorMilitar()){
                                //se nao foi usado pelo outro
                                vivo.getEquipamento().setStrikesLEFT(2);
                            }

                            //se for o mesmo militar tem 1 strike left
                            if (k == vivo.getId()){
                                vivo.getEquipamento().setStrikesLEFT(1);
                            }
                        }
                    }
                }

                //ver se foi veneno e começar a contar
                if (equipamento.getTypeID() == 8){
                    if (!vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 1) {
                        vivo.setTomouVeneno(true);
                        vivo.setNrTurnosEnvenenados(1);
                        equipamento.setStrikesLEFT(0);
                    }  else if (vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 1){
                        int nr = vivo.getNrTurnosEnvenenados();
                        vivo.setNrTurnosEnvenenados(nr + 1 );
                        equipamento.setStrikesLEFT(0);
                    }
                } else if (equipamento.getTypeID() == 9){
                    if (vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 1){
                        //atualiza os usos left e mete o vivo curado
                        equipamento.setStrikesLEFT(0);
                        vivo.setTomouVeneno(false);

                    } else if (vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 0){
                        return true;
                    }
                }

                //vai buscar o equipamento e coloca o idTIPO do humano na lista de usados do eq
                vivo.getEquipamento().getApanhadoPorCreaturesID().add(vivo.getId());

                //caso contrario se ele ja tiver um equipamento ele adiciona o da casa seguinte e dropa o antigo-
            } else if (existEquipment(xH, yH) != null && vivo.getEquipamento().getTypeID() != -23 && !idoso) {
                //vai buscar o eq do humano anterior
                Equipamento equipamentoAnterior = vivo.getEquipamento();
                Equipamento equipamento = new Equipamento();


                if (!vivo.getTomouVeneno() && equipamento.getTypeID() == 9){
                    vivo.colocarCoordenada(xO,yO);
                    //nao estava envenenado
                    return false;
                }
                //guarda-o na lista global
                //equipamentoHashMap.put(equipamentoAnterior.getId(),equipamentoAnterior);

                //coloca o eq anterior nas cordenadas anteriores
                equipamentoAnterior.setX(xO);
                equipamentoAnterior.setY(yO);

                //adiciona o eq pretendido
                vivo.setEquipamento(existEquipment(xH, yH));

                //aumenta na lista de equipamentos usados
                int num = vivo.getEquipamentos();
                vivo.setNumEqApanhadosDestruidos(num+1);

                //vai buscar o eq atual e remove da lista global
                equipamento = vivo.getEquipamento();

                //remove o que tinha mas adiciona na lista de equipamentos no bairro
                equipamentoHashMap.get(equipamento.getId()).setX(xMorto);
                equipamentoHashMap.get(equipamento.getId()).setY(yMorto);
                xMorto--;
                yMorto--;

                //verificar se foi apanhado por mais que um militar
                //se for escudo de madeira
                if (vivo.getEquipamento().getTypeID() == 0 && vivo.getIdTipo() == 7){
                    for (int k : vivo.getEquipamento().getApanhadoPorCreaturesID()){
                        //se for militar
                        if (criaturas.get(k).getIdTipo() == 7) {
                            //mas se nao for o mesmo militar e se foi usado por outro
                            if (k != vivo.getId() && vivo.getEquipamento().getIsUsadoPorMilitar()) {
                                //tem 1 strike left
                                vivo.getEquipamento().setStrikesLEFT(1);
                            } else if (!vivo.getEquipamento().getIsUsadoPorMilitar()){
                                //se nao foi usado pelo outro
                                vivo.getEquipamento().setStrikesLEFT(2);
                            }

                            //se for o mesmo militar tem 1 strike left
                            if (k == vivo.getId()){
                                vivo.getEquipamento().setStrikesLEFT(1);
                            }
                        }
                    }
                }

                //ver se foi veneno e começar a contar
                if (equipamento.getTypeID() == 8 ){
                    if (!vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 1){
                        vivo.setTomouVeneno(true);
                        vivo.setNrTurnosEnvenenados(1);
                        equipamento.setStrikesLEFT(0);
                    }  else if (vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 1){
                        int nr = vivo.getNrTurnosEnvenenados();
                        vivo.setNrTurnosEnvenenados(nr +1 );
                        equipamento.setStrikesLEFT(0);
                    }
                } else if (equipamento.getTypeID() == 9){
                    if (vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 1){
                        //atualiza os usos left e mete o vivo curado
                        equipamento.setStrikesLEFT(0);
                        vivo.setTomouVeneno(false);

                    } else if (vivo.getTomouVeneno() && equipamento.getStrikesLEFT() == 0){
                        return true;
                    } else if (!vivo.getTomouVeneno()){
                        //nao estava envenenado
                        return false;
                    }
                }

                //se o escudo foi dropado por um militar e se for um escudo
                if (vivo.getEquipamento().isDropadoPorMilitar() && vivo.getEquipamento().getTypeID() == 0){
                    //fica com capacidade de receber 1 hit
                    vivo.getEquipamento().setStrikesLEFT(1);
                }

                //adiciona na lista o humano portador
                equipamento.getApanhadoPorCreaturesID().add(vivo.getId());

            }


            nrTurnosTotal++;
            nrTurnos++;
            if (nrTurnosTotal % 2 == 0) {
                //se forem multiplos de 2 muda o dia
                day = !day;
            }
            //muda o id da equipa atual
            idEquipaAtual = 20;

            return true;
        }

        //ver se é zombie e atualiza a coordenada
        if (isZombie && idEquipaAtual == 20) {

            for (Creature vivo2 : criaturas.values()){
                if (vivo2 instanceof Vivo) {
                    Vivo vivo1 = (Vivo) vivo2;
                    if (vivo1.getTomouVeneno()) {
                        if (vivo1.getNrTurnosEnvenenados() > 2) {
                            vivo1.setDeadVeneno(true);
                            criaturas.get(vivo1.getId()).colocarCoordenada(xMorto,yMorto);
                            xMorto--;
                            yMorto--;
                        }
                        int nr = vivo1.getNrTurnosEnvenenados();
                        vivo1.setNrTurnosEnvenenados(nr + 1);
                        break;
                    }
                }
            }


            Zombie zombie = (Zombie) criaturas.get(idCriatura);
            zombie.colocarCoordenada(xD, yD);


            int xZ = zombie.cordenadaX();
            int yZ = zombie.cordenadaY();



            //se existir equipamento naquelas coordenadas ele destroi
            if (existEquipment(xZ, yZ) != null) {

                Equipamento equipamento = existEquipment(xZ,yZ);

                //se for veneno da logo false
                if (equipamento.getTypeID() == 8){
                    zombie.colocarCoordenada(xO,yO);
                    return false;
                }
                //se o zombie for vampiro e o eqq for uma cabeça de alho
                if (zombie.getIdTipo() == 4 && equipamento.getTypeID() == 5) {
                    //vampiro nao se pode mexer p cabeça de alho
                    //fica onde estava
                    zombie.colocarCoordenada(xO,yO);
                    return false;
                } else {
                    //remove da lista
                    equipamentoHashMap.get(equipamento.getId()).setX(xMorto);
                    equipamentoHashMap.get(equipamento.getId()).setY(yMorto);
                    xMorto--;
                    yMorto--;

                    //aumenta o nrDestruidos
                    int num = zombie.getEquipamentos();
                    zombie.setNumEqApanhadosDestruidos(num + 1);

                }
            }

            nrTurnosTotal++;
            nrTurnos++;
            if (nrTurnosTotal % 2 == 0) {
                //se forem multiplos de 2 muda o dia
                day = !day;
            }
            //muda o id da equipa
            idEquipaAtual = 10;
            return true;
        }

        return false;
    }

    public boolean attack(Vivo vivo, Zombie zombie, boolean zombieAttack){
        //equipamento atual do humano que ataca o zombie
        Equipamento equipAttack = vivo.getEquipamento();
        int killed = vivo.getZombieKILLED();
        int numDEFESA = equipAttack.getNumDEFESAS();

        switch (equipAttack.getTypeID()){
                //qd e uma espada
            case 1:
                if (vivo.getIdTipo() == 5 && zombie.getIdTipo() ==0){
                    //qd e uma crianca vivo vs crianca zombie
                    //crianca mata o zombie
                    //zombieHashMap.remove(zombie.getId());

                    criaturas.get(zombie.getId()).setDead(true);
                    vivo.setZombieKILLED(killed + 1);
                    equipAttack.setNumDEFESAS(numDEFESA+1);

                    if (!zombieAttack) {
                        vivo.colocarCoordenada(zombie.cordenadaX(), zombie.cordenadaY());
                    }
                    //faz a criatura desaparecer do jogo
                    criaturas.get(zombie.getId()).colocarCoordenada(xMorto,yMorto);
                    xMorto--;
                    yMorto--;
                    return true;

                }else if (vivo.getIdTipo() == 5 && zombieAttack && zombie.getIdTipo() != 0){
                    //zombie criança nao consegue atacar outros zombies nao crianças
                    return false;
                }else{

                    //zombieHashMap.remove(zombie.getId());
                    criaturas.get(zombie.getId()).setDead(true);
                    vivo.setZombieKILLED(killed + 1);
                    equipAttack.setNumDEFESAS(numDEFESA+1);

                    if (!zombieAttack) {
                        vivo.colocarCoordenada(zombie.cordenadaX(), zombie.cordenadaY());
                    }

                    //faz a criatura desaparecer do jogo
                    criaturas.get(zombie.getId()).colocarCoordenada(xMorto,yMorto);
                    xMorto--;
                    yMorto--;

                    return true;
                }

                //qd é uma pistola
            case 2:
                if (equipAttack.getStrikesLEFT()>0){
                    //zombieHashMap.remove(zombie.getId());

                    criaturas.get(zombie.getId()).setDead(true);
                    vivo.setZombieKILLED(killed + 1);
                    equipAttack.setNumDEFESAS(numDEFESA+1);

                    //dar update nos strikes
                    int numStrikes = equipAttack.getStrikesLEFT();
                    equipAttack.setStrikesLEFT(numStrikes-1);

                    if (!zombieAttack) {
                        vivo.colocarCoordenada(zombie.cordenadaX(), zombie.cordenadaY());
                    }

                    //faz a criatura desaparecer do jogo
                    criaturas.get(zombie.getId()).colocarCoordenada(xMorto,yMorto);
                    xMorto--;
                    yMorto--;

                } else {
                    //nao tem balas
                    return false;
                }
                return true;

                //qd e uma estaca de madeira
            case 6:
                //qd e um beskar
            case 10:

               // zombieHashMap.remove(zombie.getId());
                criaturas.get(zombie.getId()).setDead(true);
                vivo.setZombieKILLED(killed + 1);
                equipAttack.setNumDEFESAS(numDEFESA+1);

                if (!zombieAttack) {
                    vivo.colocarCoordenada(zombie.cordenadaX(), zombie.cordenadaY());
                }

                //faz a criatura desaparecer do jogo
                criaturas.get(zombie.getId()).colocarCoordenada(xMorto,yMorto);
                xMorto--;
                yMorto--;
                return true;

        }
        //sucedeu no ataque
        return true;
    }

    public boolean defense(Vivo vivo, Zombie zombie, boolean zombieAttack){
        Equipamento equipDefense = vivo.getEquipamento();
        int numDEFESA = equipDefense.getNumDEFESAS();


        switch (equipDefense.getTypeID()){
            //qd e um escudo madeira
            case 0:
                if (equipDefense.getStrikesLEFT()>0){

                    //dar update nos strikes
                    int numStrikes = equipDefense.getStrikesLEFT();
                    equipDefense.setStrikesLEFT(numStrikes-1);

                } else {
                    //nao tem strickes
                    return false;
                }
                equipDefense.setNumDEFESAS(numDEFESA+1);
                return true;
            //qd é um escudo tatico
            case 3:

            case 10:
                //defende o ataque
                equipDefense.setNumDEFESAS(numDEFESA+1);
                return true;
                //qd e uma revista maria
            case 4:
                if (zombie.getIdTipo() == 3){
                    equipDefense.setNumDEFESAS(numDEFESA+1);
                    return true;
                } else {
                    return false;
                }

                //qd e cabeca de alho
            case 5:

                if (!day){
                    equipDefense.setNumDEFESAS(numDEFESA+1);
                    return true;
                } else {
                    return false;
                }

            //qd e garrafa de lixivia
            case 7:
                if (equipDefense.getLitroLEFTfloat() >= 0.3){

                    //dar update nos strikes
                    float numStrikes = equipDefense.getLitroLEFTfloat();
                    equipDefense.setLitroLEFT((float) (numStrikes-0.3));

                } else {
                    //nao tem strickes
                    return false;
                }

                equipDefense.setNumDEFESAS(numDEFESA+1);
                return true;

                //qd e veneno
            case 8:
                //ou seja nao esta vazio
                //aumenta o nrTurno por defesa
                if (vivo.getTomouVeneno() && vivo.getNrTurnosEnvenenados() < 3 && vivo.getNrTurnosEnvenenados() > 0){
                    int nr = vivo.getNrTurnosEnvenenados();
                    vivo.setNrTurnosEnvenenados(nr +1);

                    equipDefense.setNumDEFESAS(numDEFESA+1);
                    return true;
                }

            //qd e antidoto
            case 9:
                //ou seja tem um uso
                return false;
        }
        //sucedeu na defesa
        return true;
    }

    public Equipamento existEquipment(int x0, int y0) {

        for (Equipamento equipamento : equipamentoHashMap.values()) {
            if (equipamento.cordenadaX() == x0 && equipamento.cordenadaY() == y0) {
                return equipamento;
            }
        }

        return null;
    }

    public boolean gameIsOver() {
        //se nao houve infecoes
        if (nrTurnos == 12){
            return true;
        }

        //se para cada vivo ele nao estiver morto nem a salvo
        for (Creature vivo : criaturas.values()){
            if (vivo instanceof Vivo){
                if (!((Vivo) vivo).getIsSafe() && !vivo.getIsDead() && !((Vivo) vivo).isDeadVeneno()){
                    return false;
                }
            }
        }

        return true;
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
        //adquirir o id da criatura naquela posicao

        for (Creature creature : criaturas.values()){

            if (creature.cordenadaX() == x && creature.cordenadaY() == y){
                return creature.getId();
            }
        }
        for (Equipamento equipamento : equipamentoHashMap.values()) {
            if (equipamento.cordenadaX() == x && equipamento.cordenadaY() == y) {
                return equipamento.getId();

            }
        }

        for (SafeHaven safeHaven : safeHavens){
            if (safeHaven.getX() == x && safeHaven.getY() == y) {
                return 0;
            }
        }

        //---

        return 0;
    }

    public List<String> getGameResults() {
        ArrayList<String> survivors = new ArrayList<>();
        ArrayList<Creature> creatures = (ArrayList<Creature>) getCreatures();

        //fazer a string
        survivors.add("Nr. de turnos terminados:");
        survivors.add(nrTurnosTotal +"");
        survivors.add("");
        survivors.add("Ainda pelo bairo:");
        survivors.add("");
        survivors.add("OS VIVOS");

        for (Creature humano : criaturas.values()) {
            if (humano instanceof Vivo && !((Vivo) humano).getIsSafe() && !humano.getIsDead() &&
                    !((Vivo) humano).isDeadVeneno()) {
                survivors.add(humano.getId() + " " + humano.getNome());
            }
        }

        survivors.add("");
        survivors.add("OS OUTROS");

        for (Creature zombie : criaturas.values()) {
            if (zombie instanceof Zombie && !zombie.getIsDead()) {
                survivors.add(zombie.getId() + " (antigamente conhecido como " + zombie.getNome() + ")");
            }
        }

        survivors.add("");
        survivors.add("Num safe haven:");
        survivors.add("");
        survivors.add("OS VIVOS");

        for (Creature c : criaturas.values()){
            if (c instanceof Vivo) {
                if (((Vivo) c).getIsSafe()) {
                    survivors.add(c.getId() + " " + c.getNome());
                }
            }
        }

        survivors.add("");
        survivors.add("Envenenados / Destruidos");
        survivors.add("");
        survivors.add("OS VIVOS");

//fazer a cena dos envenenados com for
        for (Creature c : criaturas.values()){
            if (c instanceof Vivo) {
                if (((Vivo) c).isDeadVeneno()) {
                    survivors.add(c.getId() + " " + c.getNome());
                }
            }
        }

        survivors.add("");
        survivors.add("OS OUTROS");

        for (Creature zombie : criaturas.values()){
            if (zombie instanceof Zombie) {
                if (zombie.getIsDead()) {
                    survivors.add(zombie.getId() + " (antigamente conhecido como " + zombie.getNome() + ")");

                }
            }
        }

        return survivors;
    }

    public boolean isDay() {
        return day;
    }

    public int getEquipmentTypeId(int equipmentId){

        Equipamento equipamento = equipamentoHashMap.get(equipmentId);


        //se for null percorre a lista de humanos para verificar se existe la um
       if (equipamento == null){
            for (Creature humano1 : criaturas.values()) {
                if (humano1 instanceof Vivo) {
                    if (humano1.getEquipamento().getId() == equipmentId) {
                        //encontrou
                        return humano1.getEquipamento().getTypeID();
                    }
                }
            }
        } else {
            //se nao for null da return diretamente da lista global
            return equipamento.getTypeID();
        }

        return -23;
    }

    public int getEquipmentId(int creatureId) {

        //entra aqui para verificar se o humano contém eq
        for (Creature humano1 : criaturas.values()) {
            if (humano1.getId() == creatureId) {
                return humano1.getEquipamento().getId();
            }
        }

        //a creature nao tinha equipamentos
        return 0;
    }

    public String getEquipmentInfo(int equipmentId) {
        Equipamento equipamento = equipamentoHashMap.get(equipmentId);
        String info = "";
        Creature vivo = new Creature();

        if (equipamento == null){
            for (Creature humano1 : criaturas.values()) {
                if (humano1 instanceof Vivo) {
                    if (humano1.getEquipamento().getId() == equipmentId) {
                        //encontrou
                        equipamento = humano1.getEquipamento();
                        vivo = humano1;
                    }
                }
            }
        }

        switch (Objects.requireNonNull(equipamento).getTypeID()){
            case 0:
                if (vivo.getIdTipo() != 7) {
                    info = equipamento.getNome() + " | " + equipamento.getStrikesLEFT();
                    return info;
                } else {
                    return "";
                }
            case 2:
            case 8:
            case 9:
                info= equipamento.getNome() +" | "+ equipamento.getStrikesLEFT();
                return info;
            case 7:
                info= equipamento.getNome() +" | "+ equipamento.getLitroLEFTint();
                return info;
            default:
                info= equipamento.getNome()+"";
                return info;
        }
    }

    public String[] popCultureExtravaganza(){
        String[] answers = new String[14];

        answers[0] = "Resident Evil";
        answers[1] = "Evil Dead";
        answers[2] = "I Am Legend";
        answers[3] = "I Am Legend";
        answers[4] = "Dragon Ball";
        answers[5] = "World War Z";
        answers[6] = "Mandalorians";
        answers[7] = "1972";
        answers[8] = "Kill Bill";
        answers[9] = "1978";
        answers[10] = "Bond. James Bond.";//certo ate aqui
        answers[11] = "Lost";
        answers[12] = "Chocho";
        answers[13] = "Farrokh Bulsara";

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
                //personagem the walking dead
                return "Beta (Zombie)";
            default:
                throw new IllegalArgumentException("CreatureTYPEid Not Found");
        }
    }

    public String equipmentTYPE_ID(int type){
        switch (type){
            case 0:
                return "Escudo de Madeira";
            case 1:
                return "Espada Hattori Hanzo";
            case 2:
                return "Pistola Walther PPK";
            case 3:
                return "Escudo Táctico";
            case 4:
                return "Revista Maria";
            case 5:
                return "Cabeça de Alho";
            case 6:
                return "Estaca de Madeira";
            case 7:
                return "Garrafa de Lixívia (1 litro)";
            case 8:
                return "Veneno";
            case 9:
                return "Antídoto";
            case 10:
                return "Beskar Helmet";
            default:
                throw new IllegalArgumentException("EquipmentTYPEid Not Found");
        }
    }

    public void equipmentSTRIKESsetter(int type, int equipmentID){

        switch (type){
            case 0:
                //1 protecao
                //escudo madeira
                equipamentoHashMap.get(equipmentID).setStrikesLEFT(1);
                return;
            case 2:
                //pistola
                equipamentoHashMap.get(equipmentID).setStrikesLEFT(3);
                return;
            case 7:
                //"Garrafa de Lixívia (1litro)"
                equipamentoHashMap.get(equipmentID).setLitroLEFT((float) 1.0);
                return;

            case 8:
                //da 1 para o veneno (ou seja tem veneno la)
                equipamentoHashMap.get(equipmentID).setStrikesLEFT(1);
            case 9:
                //da 1uso por frasco ao antidoto
                equipamentoHashMap.get(equipmentID).setStrikesLEFT(1);
            default:
        }
    }

    public boolean saveGame(File fich){
        try {

            for (Creature c : criaturas.values()){
                if (c.getEquipamento().getTypeID() != -23){
                    //se tiver equipamento ele mete as memas coordenadas da criatura
                    equipamentoHashMap.put(c.getId(),c.getEquipamento());
                    c.getEquipamento().setX(c.cordenadaX());
                    c.getEquipamento().setY(c.cordenadaY());

                }
            }

            File file = new File(fich.getAbsolutePath() + ".txt");

            //funcao de escrever p ficheiro
            BufferedWriter writer = new BufferedWriter(new FileWriter(fich.getName(), true));

            writer.write(getWorldSize()[0] + " " + getWorldSize()[1] + "\n"+idEquipaAtual + "\n"
                    +criaturas.size() + "\n");



            for (Creature c : criaturas.values()) {
                writer.write(c.getId() + " : "
                        + c.getIdTipo() + " : " + c.getNome() + " : "
                        + c.cordenadaX() + " : " + c.cordenadaY() + " : " + c.getEquipamentos()+ "\n");
            }

            writer.write(equipamentoHashMap.size() + "\n");

            for (Equipamento e : equipamentoHashMap.values()) {
                writer.write(e.getId() + " : " + e.getTypeID()
                        + " : " + e.cordenadaX() + " : "
                        + e.cordenadaY() + " : "+ e.getNumDEFESAS()  +" : "+ e.getStrikesLEFT() +"\n");
            }

            writer.write(safeHavens.size() + "\n");

            for (SafeHaven sh : safeHavens) {
                writer.write(sh.getX() + " : "
                        + sh.getY() + "\n");
            }

            writer.close();

            return file.createNewFile();
        } catch (IOException e) {
            //erro
            e.printStackTrace();
        }
        return false;
    }

    public boolean loadGame(File fich){

        safeCreaturesID.clear();
        safeHavens.clear();
        //--HashsCreatures
        criaturas.clear();
        //--equi
        equipamentoHashMap.clear();
        numeroLinhas = 0;
        numeroColunas = 0;
        nrTurnosTotal = 0;
        idEquipaAtual = 0;
        idEquipaInicial = 0;
        nrTurnos = 0;
        day = true;
        xMorto = -1;
        yMorto = -1;
        //clear das estruturas e vars

        try {

            Scanner leitorFicheiro = new Scanner(new FileInputStream(fich));
            while (leitorFicheiro.hasNextLine()) {

                String linha = leitorFicheiro.nextLine();
                String[] dados = linha.split(" ");

                if (dados.length == 2) {
                    numeroLinhas = Integer.parseInt(dados[0].trim());
                    numeroColunas = Integer.parseInt(dados[1].trim());
                }


                idEquipaInicial = Integer.parseInt(leitorFicheiro.nextLine());

                idEquipaAtual = idEquipaInicial;

                int nrCriaturas = 0;
                nrCriaturas = Integer.parseInt(leitorFicheiro.nextLine());


                for (int x = 0; x < nrCriaturas; x++) {
                    if (leitorFicheiro.hasNextLine()) {
                        String[] dadosCriatura = leitorFicheiro.nextLine().split(" : ");
                        //fazer o split da linha com os " : "
                        //e guardar os dados nos objetos e add na lista respetiva

                        if (    Integer.parseInt(dadosCriatura[1]) >= 0 &&
                                Integer.parseInt(dadosCriatura[1]) <= 4 &&
                                dadosCriatura.length == 6) {
                            //é zombie

                            int idZ = Integer.parseInt(dadosCriatura[0]);
                            int idTipoZ = Integer.parseInt(dadosCriatura[1]);
                            String nomeZ = dadosCriatura[2];
                            int xZ = Integer.parseInt(dadosCriatura[3]);
                            int yZ = Integer.parseInt(dadosCriatura[4]);
                            int numEQ = Integer.parseInt(dadosCriatura[5]);
                            Equipamento equipamentos = new Equipamento();


                            Zombie zombieAtual =
                                    new Zombie(idZ, idTipoZ, creatureTYPE_ID(idTipoZ), "Os Outros",
                                            nomeZ, xZ, yZ, equipamentos,numEQ,false,false,0);

                            criaturas.putIfAbsent(idZ, zombieAtual);


                        } else if (Integer.parseInt(dadosCriatura[1]) >= 5 &&
                                Integer.parseInt(dadosCriatura[1]) <= 9 &&
                                dadosCriatura.length == 6 ) {

                            //é humano

                            int idH = Integer.parseInt(dadosCriatura[0]);
                            int idTipoH = Integer.parseInt(dadosCriatura[1]);
                            String nomeH = dadosCriatura[2];
                            int xH = Integer.parseInt(dadosCriatura[3]);
                            int yH = Integer.parseInt(dadosCriatura[4]);
                            int numEQ = Integer.parseInt(dadosCriatura[5]);
                            Equipamento equipamentos = new Equipamento();

                            Vivo vivoAtual =
                                    new Vivo(idH, idTipoH, creatureTYPE_ID(idTipoH)
                                            , "Os Vivos", nomeH, xH, yH, equipamentos,
                                            numEQ,false, false,-1);

                            //se os X e Y do vivo forem negativos é porque foram salvos ou mortos
                            //se for safe ele vai para coordenada -100-xmorto, -100-ymorto
                            if (xH < -99 && yH < -99 ){
                                safeCreaturesID.add(vivoAtual.getId());
                                vivoAtual.setSafe(true);
                            }

                            if ((xH < 0 && xH > -100) && (yH < 0 && yH > -100) ){
                                vivoAtual.setDead(true);
                            }

                            criaturas.putIfAbsent(idH, vivoAtual);
                        } else if (Integer.parseInt(dadosCriatura[1]) == 10){
                            int idZ = Integer.parseInt(dadosCriatura[0]);
                            int idTipoZ = Integer.parseInt(dadosCriatura[1]);
                            String nomeZ = dadosCriatura[2];
                            int xZ = Integer.parseInt(dadosCriatura[3]);
                            int yZ = Integer.parseInt(dadosCriatura[4]);
                            Equipamento equipamentos = new Equipamento();


                            Zombie zombieAtual =
                                    new Zombie(idZ, idTipoZ, creatureTYPE_ID(idTipoZ), "Os Outros",
                                            nomeZ, xZ, yZ, equipamentos,0,false,false,0);

                            criaturas.putIfAbsent(idZ, zombieAtual);
                        }
                    }
                }

                int nrEquipamentos = 0;
                nrEquipamentos = Integer.parseInt(leitorFicheiro.nextLine());

                //adicionar os equipamentos nas estruturas
                for (int k = 0; k < nrEquipamentos; k++) {
                    if (leitorFicheiro.hasNextLine()) {
                        String[] dadosEquipamento = leitorFicheiro.nextLine().split(" : ");


                        int idE = Integer.parseInt(dadosEquipamento[0]);
                        int idTipoE = Integer.parseInt(dadosEquipamento[1]);
                        int xE = Integer.parseInt(dadosEquipamento[2]);
                        int yE = Integer.parseInt(dadosEquipamento[3]);
                        int numDef = Integer.parseInt(dadosEquipamento[4]);
                        int strikerLeft = Integer.parseInt(dadosEquipamento[5]);
                        ArrayList<Integer> c = new ArrayList<>();
                        Equipamento equipamento = new Equipamento(idE, idTipoE, equipmentTYPE_ID(idTipoE), xE, yE,c,
                                numDef);
                        equipamento.setStrikesLEFT(strikerLeft);
                        equipamentoHashMap.putIfAbsent(idE,equipamento);
                        equipmentSTRIKESsetter(idTipoE, idE);
                    }
                }

 
                int nrSafeHavens = 0;
                if (leitorFicheiro.hasNextLine()) {
                    nrSafeHavens = Integer.parseInt(leitorFicheiro.nextLine());
                }

                //adicionar as safeHavens
                for (int k = 0; k < nrSafeHavens; k++){
                    if (leitorFicheiro.hasNextLine()) {
                        String[] dadosSafeHaven = leitorFicheiro.nextLine().split(" : ");

                        int shX = Integer.parseInt(dadosSafeHaven[0]);
                        int shY = Integer.parseInt(dadosSafeHaven[1]);

                        SafeHaven safeHaven = new SafeHaven(shX, shY);
                        safeHavens.add(safeHaven);
                    }

                }

            }

            //coloca o respetivo eq na criatura
            for (Equipamento e : equipamentoHashMap.values()){
                for (Creature c : criaturas.values()){
                  if ((e.cordenadaX() == c.cordenadaX()) && (e.cordenadaY() == c.cordenadaY())){
                     c.setEquipamento(e);
                  }
              }
            }

            leitorFicheiro.close();
            return true;

        } catch (FileNotFoundException exception) {
            return false;
        }

    }

    public Map<String, List<String>> getGameStatistics(){
    //lista de controlo
    ArrayList<Creature> creatures = new ArrayList<>(criaturas.values());
    ArrayList<Equipamento> equipamento = new ArrayList<>(equipamentoHashMap.values());


    List<String> os3ZombiesMaisTramados = new ArrayList<>();
    List<String> os3VivosMaisDuros = new ArrayList<>();
    List<String> tiposDeEquipamentoMaisUteis = new ArrayList<>();
    List<String> tiposDeZombieESeusEquipDestruidos= new ArrayList<>();
    List<String> criaturasMaisEquipadas = new ArrayList<>();



        String zombieType0 ="Criança:" +tiposDeZombieESeusEquipDestruidoNUMtipo(0) + ":"
                +tiposDeZombieESeusEquipDestruidoNUMeq(0);

        String zombieType1 =  "Adulto:" +tiposDeZombieESeusEquipDestruidoNUMtipo(1) + ":"
                +tiposDeZombieESeusEquipDestruidoNUMeq(1);

        String zombieType2 = "Militar:" +tiposDeZombieESeusEquipDestruidoNUMtipo(2) + ":"
                +tiposDeZombieESeusEquipDestruidoNUMeq(2);

        String zombieType3 = "Idoso:" +tiposDeZombieESeusEquipDestruidoNUMtipo(3) + ":"
                +tiposDeZombieESeusEquipDestruidoNUMeq(3);

        String zombieType4 = "Vampiro:" +tiposDeZombieESeusEquipDestruidoNUMtipo(4) + ":"
                +tiposDeZombieESeusEquipDestruidoNUMeq(4);


    //criaturasMaisEquipadas
        if (criaturas.size() < 5){
            creatures.stream()
                    .filter(Creature -> !Creature.getIsDead())
                    .filter(Creature -> !Creature.getIsSafe())
                    .sorted((Creature c1, Creature c2) -> c2.getEquipamentos() - c1.getEquipamentos())//decrescente
                    .forEach(Creature -> criaturasMaisEquipadas.add(Creature.getId()+":"
                            +Creature.getNome()+":"+Creature.getEquipamentos()));
        } else {

            creatures.stream()
                    .filter(Creature -> !Creature.getIsDead())
                    .filter(Creature -> !Creature.getIsSafe())
                    .sorted((Creature c1, Creature c2) -> c2.getEquipamentos() - c1.getEquipamentos()) //decrescente
                    .limit(5)
                    .forEach(Creature -> criaturasMaisEquipadas.add(Creature.getId()+":"
                            +Creature.getNome()+":"+Creature.getEquipamentos()));


        }
        //System.out.println(criaturasMaisEquipadas);

        //tiposDeZombieESeusEquipDestruidos
        if (tiposDeZombieESeusEquipDestruidoNUMtipo(1) != -1) {
            tiposDeZombieESeusEquipDestruidos.add(zombieType1);
        }
        if (tiposDeZombieESeusEquipDestruidoNUMtipo(0) != -1){
        tiposDeZombieESeusEquipDestruidos.add(zombieType0);
        }

        if (tiposDeZombieESeusEquipDestruidoNUMtipo(2) != -1) {
            tiposDeZombieESeusEquipDestruidos.add(zombieType2);
        }
        if (tiposDeZombieESeusEquipDestruidoNUMtipo(3) != -1){
            tiposDeZombieESeusEquipDestruidos.add(zombieType3);
        }
        if (tiposDeZombieESeusEquipDestruidoNUMtipo(4) != -1){
            tiposDeZombieESeusEquipDestruidos.add(zombieType4);
        }



        //System.out.println(tiposDeZombieESeusEquipDestruidos);

        //os3ZombiesMaisTramados
        int count = (int) creatures.stream()
                .filter(Creature -> Creature.getTransformacoes() >0)
                .count();

        if (count > 2){
            creatures.stream()
                    .filter(Creature -> Creature.getIdTipo() < 5)
                    .sorted((Creature c1, Creature c2) -> c2.getTransformacoes() - c1.getTransformacoes())
                    .limit(3)
                    .forEach(Creature -> os3ZombiesMaisTramados.add(Creature.getId()+":"
                    +Creature.getNome()+":"+Creature.getTransformacoes()));
        } else {

            creatures.stream()
                    .filter(Creature -> Creature.getIdTipo() < 5)
                    .filter(Creature -> Creature.getTransformacoes() > 0)
                    .sorted((Creature c1, Creature c2) -> c2.getTransformacoes() - c1.getTransformacoes())
                    .forEach(Creature -> os3ZombiesMaisTramados.add(Creature.getId()+":"
                            +Creature.getNome()+":"+Creature.getTransformacoes()));

        }
        //System.out.println(os3ZombiesMaisTramados);


    //os3VivosMaisDuros
        int vivosDestroy = (int) creatures.stream()
                .filter(Creature -> Creature.getZombieKILLED() >0)
                .count();

        if (vivosDestroy > 2){
            creatures.stream()
                    .filter(Creature -> Creature.getIdTipo() > 4)
                    .sorted((Creature c1, Creature c2) -> c2.getZombieKILLED() - c1.getZombieKILLED())
                    .limit(3)
                    .forEach(Creature -> os3VivosMaisDuros.add(Creature.getId()+":"
                            +Creature.getNome()+":"+Creature.getZombieKILLED()));
        } else {

            creatures.stream()
                    .filter(Creature -> Creature.getIdTipo() > 4)
                    .filter(Creature -> Creature.getZombieKILLED() > 0)
                    .sorted((Creature c1, Creature c2) -> c2.getZombieKILLED() - c1.getZombieKILLED())
                    .forEach(Creature -> os3VivosMaisDuros.add(Creature.getId()+":"
                            +Creature.getNome()+":"+Creature.getZombieKILLED()));

        }
        //System.out.println(os3VivosMaisDuros);

        //tiposDeEquipamento
        //MaisUteis
        System.out.println(equipamento);
        equipamento.stream()
                .filter(Equipamento -> Equipamento.getNumDEFESAS() > 0)
                .sorted(Comparator.comparingInt(Equipamento::getNumDEFESAS))
                .forEach(Equipamento -> tiposDeEquipamentoMaisUteis.add(Equipamento.getTypeID()+" "
                        +Equipamento.getNumDEFESAS()));
        System.out.println(tiposDeEquipamentoMaisUteis);


    listMap.put("os3ZombiesMaisTramados",os3ZombiesMaisTramados);
    listMap.put("os3VivosMaisDuros",os3VivosMaisDuros);
    listMap.put("tiposDeEquipamentoMaisUteis",tiposDeEquipamentoMaisUteis);
    listMap.put("tiposDeZombieESeusEquipamentosDestruidos",tiposDeZombieESeusEquipDestruidos);
    listMap.put("criaturasMaisEquipadas",criaturasMaisEquipadas);

    return listMap;
    }

    public int tiposDeZombieESeusEquipDestruidoNUMeq(int type){

        ArrayList<Creature> creatures = new ArrayList<>(criaturas.values());

        //tiposDeZombieESeusEquipDestruidos
        switch (type) {
            case 0: return creatures.stream()
                    .filter(Creature -> Creature.getIdTipo() == 0) //apenas zombies
                    .map(Creature::getEquipamentos)
                    .mapToInt(i -> i).sum();

            case 1: return creatures.stream()
                    .filter(Creature -> Creature.getIdTipo() == 1) //apenas zombies
                    .map(Creature::getEquipamentos)
                    .mapToInt(i -> i).sum();
            case 2: return creatures.stream()
                    .filter(Creature -> Creature.getIdTipo() == 2) //apenas zombies
                    .map(Creature::getEquipamentos)
                    .mapToInt(i -> i).sum();
            case 3: return creatures.stream()
                    .filter(Creature -> Creature.getIdTipo() == 3) //apenas zombies
                    .map(Creature::getEquipamentos)
                    .mapToInt(i -> i).sum();

            case 4: return creatures.stream()
                    .filter(Creature -> Creature.getIdTipo() == 4) //apenas zombies
                    .map(Creature::getEquipamentos)
                    .mapToInt(i -> i).sum();
        }
        return -1;
    }

    public int tiposDeZombieESeusEquipDestruidoNUMtipo(int type){
        ArrayList<Creature> creatures = new ArrayList<>(criaturas.values());
        int numTIPO = 0;
        //tiposDeZombieESeusEquipDestruidos
        switch (type) {
            case 0: numTIPO = (int) creatures.stream()
                    .filter(Creature -> Creature.getIdTipo() == 0) //apenas zombies
                    .count();

                    if (numTIPO == 0){
                        return -1;
                    }
                    return numTIPO;

            case 1: numTIPO = (int)creatures.stream()
                    .filter(Creature -> Creature.getIdTipo() == 1) //apenas zombies
                    .count();

                if (numTIPO == 0){
                    return -1;
                }
                return numTIPO;


            case 2: numTIPO = (int)creatures.stream()
                    .filter(Creature -> Creature.getIdTipo() == 2) //apenas zombies
                    .count();

                if (numTIPO == 0){
                    return -1;
                }
                return numTIPO;

            case 3: numTIPO = (int)creatures.stream()
                    .filter(Creature -> Creature.getIdTipo() == 3) //apenas zombies
                    .count();

                if (numTIPO == 0){
                    return -1;
                }
                return numTIPO;

            case 4: numTIPO = (int)creatures.stream()
                    .filter(Creature -> Creature.getIdTipo() == 4) //apenas zombies
                    .count();

                if (numTIPO == 0){
                    return -1;
                }
                return numTIPO;

        }
        return -1;
    }

}