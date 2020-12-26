package pt.ulusofona.lp2.theWalkingDEISIGame;


import java.util.ArrayList;
import java.util.Objects;

public class Creature {

    private int id;
    private int idTipo;
    private String tipoCriatura;
    private String nomeEquipa;
    private String nome;
    private int x, y;
    private Equipamento equipamento = new Equipamento();
    int numEqApanhadosDestruidos = 0;


    public Creature() {
    }

    public Creature(int id, int idTipo, String tipoCriatura, String nomeEquipa,
                    String nome, int x, int y, Equipamento equipamento, int numEqApanhadosDestruidos) {
        this.id = id;
        this.idTipo = idTipo;
        this.tipoCriatura = tipoCriatura;
        this.nomeEquipa = nomeEquipa;
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.equipamento = equipamento;
        this.numEqApanhadosDestruidos = numEqApanhadosDestruidos;
    }


    public void colocarCoordenada(int x1, int y1) {
        this.x = x1;
        this.y = y1;
    }

    public int getId() {
        return id;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getImagePNG() {
        switch (idTipo) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                return "zombie.png";
            case 5:
            case 6:
            case 7:
            case 8:
                return "human.png";
            case 9:
                return "dog.png";
            case 10:
                return "unknown-piece.png";
            default:
                throw new IllegalArgumentException("Equipment Not Found");
        }
    }

    public String getNome() {
        return nome;
    }

    public int cordenadaX() {
        return this.x;
    }

    public int cordenadaY() {
        return this.y;
    }

    public int getEquipamentos(){
        return this.numEqApanhadosDestruidos;
    }

    public void setNumEqApanhadosDestruidos(int numEqApanhadosDestruidos) {
        this.numEqApanhadosDestruidos = numEqApanhadosDestruidos;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    @Override
    public String toString(){
        Creature creature = TWDGameManager.criaturas.get(id);

        if (creature!=null) {
            if (creature instanceof Humano) {
                if (((Humano) creature).getIsSafe()) {
                    return id + " | " + tipoCriatura + " | " + nomeEquipa + " | " + nome + " " +
                            getEquipamentos() + " @ A Salvo";
                }

                if (((Humano) creature).getIsDead()) {
                    return id + " | " + tipoCriatura + " | " + nomeEquipa + " | " + nome + " " +
                            getEquipamentos() + " @ RIP";
                }
            } else if (creature instanceof  Zombie){
                if (((Zombie) creature).getIsDead()) {
                    return id + " | " + tipoCriatura + " | " + nomeEquipa + " | " + nome + " " +
                            getEquipamentos() + " @ RIP";
                }
            }

        }
        return id + " | " + tipoCriatura + " | " + nomeEquipa + " | " + nome + " " +
                getEquipamentos() + " @ (" + x + ", " + y + ")";

    }
}
