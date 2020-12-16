package pt.ulusofona.lp2.theWalkingDEISIGame;


import java.util.ArrayList;

public class Creature {

    private int id;
    private int idTipo;
    private String tipoCriatura;
    private String nomeEquipa;
    private String nome;
    private int x, y;
    private ArrayList<Equipamento> equipamentos = new ArrayList<>();

    public Creature() {
    }

    public Creature(int id, int idTipo, String tipoCriatura, String nomeEquipa,
                    String nome, int x, int y, ArrayList<Equipamento> equipamentos) {
        this.id = id;
        this.idTipo = idTipo;
        this.tipoCriatura = tipoCriatura;
        this.nomeEquipa = nomeEquipa;
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.equipamentos = equipamentos;
    }

    public void colocarCoordenada(int x1, int y1) {
        this.x = x1;
        this.y = y1;
    }

    public int getId() {
        return id;
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
        return this.equipamentos.size();
    }

    public ArrayList<Equipamento> getEquipamentosList(){
        return this.equipamentos;
    }

    @Override
    public String toString(){
        if (TWDGameManager.humanISsafe.get(id) !=null && TWDGameManager.humanISdead.get(id) != null) {
            if (TWDGameManager.humanISsafe.get(id)) {
                return id + " | " + tipoCriatura + " | " + nomeEquipa + " | " + nome + " " +
                        getEquipamentos() + " @ A Salvo";
            } else if (TWDGameManager.humanISdead.get(id)) {
                return id + " | " + tipoCriatura + " | " + nomeEquipa + " | " + nome + " " +
                        getEquipamentos() + " @ RIP";
            }
        }
        return id + " | " + tipoCriatura + " | " + nomeEquipa + " | " + nome + " " +
                getEquipamentos() + " @ (" + x + ", " + y + ")";

    }
}
