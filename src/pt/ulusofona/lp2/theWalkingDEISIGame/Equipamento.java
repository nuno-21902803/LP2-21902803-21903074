package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Equipamento {
    private int id;
    //um idTipo nao existente
    private int idTipo = -23;
    private String nome;
    private int x,y;
    private int strikesLEFT = -1;
    private float litroLEFT = -1;
    private ArrayList<Integer> apanhadoPorCreaturesID = new ArrayList<>();
    private int numDEFESAS = 0;

    private boolean usado=false;
    private boolean dropado = false;
    private boolean destruido=false;

    public Equipamento() {
    }

    public Equipamento(int id, int idTipo, String nome, int x, int y, ArrayList<Integer> apanhadoPorCreatures,
                       int numDEFESAS) {
        this.id = id;
        this.idTipo = idTipo;
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.apanhadoPorCreaturesID = apanhadoPorCreatures;
        this.numDEFESAS = numDEFESAS;
    }

    public ArrayList<Integer> getApanhadoPorCreaturesID() {
        return apanhadoPorCreaturesID;
    }

    public void setApanhadoPorCreaturesID(ArrayList<Integer> apanhadoPorCreaturesID) {
        this.apanhadoPorCreaturesID = apanhadoPorCreaturesID;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setStrikesLEFT(int strikesLEFT) {
        this.strikesLEFT = strikesLEFT;
    }


    public int getNumDEFESAS() {
        return numDEFESAS;
    }

    public void setNumDEFESAS(int numDEFESAS) {
        this.numDEFESAS = numDEFESAS;
    }

    public boolean getIsUsadoPorMilitar() {
        return usado;
    }

    public void setUsadoPorMilitar(boolean apanhado) {
        this.usado = apanhado;
    }

    public boolean isDropadoPorMilitar() {
        return dropado;
    }

    public void setDropadoPorMilitar(boolean dropado) {
        this.dropado = dropado;
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

    public float getLitroLEFTfloat() {
        return litroLEFT;
    }

    public int getLitroLEFTint() {

        if (litroLEFT == 1.0){
            return 3;
        }else if (litroLEFT >= 0.6 && litroLEFT <= 0.9){
            return 2;
        }else if (litroLEFT >= 0.3 && litroLEFT <= 0.5){
            return 1;
        }else if (litroLEFT < 0.3){
            return 0;
        }
        return 0;
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
        return x;
    }

    public int cordenadaY(){
        return y;
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
                ", apanhadoPorCreatures=" + apanhadoPorCreaturesID +
                ", apanhado=" + usado +
                ", destruido=" + destruido +
                '}';
    }
}

