package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Equipamento {
    private int id;
    private int idTipo;
    private String nome;
    private int x,y;
    private int strikesLEFT;
    private float litroLEFT;
    private ArrayList<Creature> apanhadoPorCreatures;

    private boolean apanhado=false;
    private boolean destruido=false;

    public Equipamento() {
    }

    public Equipamento(int id, int idTipo, String nome, int x, int y) {
        this.id = id;
        this.idTipo = idTipo;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public ArrayList<Creature> getApanhadoPorCreatures() {
        return apanhadoPorCreatures;
    }

    public void setApanhadoPorCreatures(ArrayList<Creature> apanhadoPorCreatures) {
        this.apanhadoPorCreatures = apanhadoPorCreatures;
    }

    public void setStrikesLEFT(int strikesLEFT) {
        this.strikesLEFT = strikesLEFT;
    }

    public boolean getIsApanhado() {
        return apanhado;
    }

    public void setApanhado(boolean apanhado) {
        this.apanhado = apanhado;
    }

    public boolean getIsDestruido() {
        return destruido;
    }

    public void setDestruido(boolean destruido) {
        this.destruido = destruido;
    }

    public int getStrikesLEFT(){
        return strikesLEFT;
    }

    public void setLitroLEFT(float litroLEFT) {
        this.litroLEFT = litroLEFT;
    }

    public float getLitroLEFT() {
        return litroLEFT;
    }

    public int getId(){
        return this.id;
    }

    public int getTypeID(){
        return this.idTipo;
    }

    public String getNome() {
        return nome;
    }

    public String getImagePNG(){
        switch (idTipo) {
            case 0:
                return "equipment_0.png";
            case 1:
                return "equipment.png";
            case 2:
                return "gun.png";
            case 3:
                return "tactical_shield.png";
            case 4:
                return "rolled_magazine.png";
            case 5:
                return "garlic.png";
            case 6:
                return "steak.png";
            case 7:
                return "bleach.png";
            case 8:
                return "poison.png";
            case 9:
                return "antidote.png";
            case 10:
                return "beskar_helmet.png";
            default:
                throw new IllegalArgumentException("Equipment Not Found");
        }
    }

    public int cordenadaX(){
        return this.x;
    }

    public int cordenadaY(){
        return this.y;
    }

    @Override
    public String toString() {
        return "Equipamento{" +
                "id=" + id +
                ", idTipo=" + idTipo +
                ", nome='" + nome + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", strikesLEFT=" + strikesLEFT +
                ", litroLEFT=" + litroLEFT +
                ", apanhado=" + apanhado +
                ", destruido=" + destruido +
                '}';
    }
}

