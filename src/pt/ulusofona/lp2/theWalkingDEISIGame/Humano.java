package pt.ulusofona.lp2.theWalkingDEISIGame;


import java.util.ArrayList;


public class Humano {
    //humano
    int id;
    int idTipo;
    String nomeEquipa = "Os Vivos";
    String nome = "";
    int x,y;
    ArrayList<Equipamento> equipamentosApanhados = new ArrayList<>();




    public Humano(int id, int idTipo, String nome, int x, int y) {
        this.id = id;
        this.idTipo = idTipo;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public Humano(int id, int idTipo, String nomeEquipa, String nome, int x, int y,
                  ArrayList<Equipamento> equipamentosApanhados) {

        this.id = id;
        this.idTipo = idTipo;
        this.nomeEquipa = nomeEquipa;
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.equipamentosApanhados = equipamentosApanhados;
    }

    public int getId(){
        return this.id;
    }

    public String getImagePNG(){
        return "human.png";
    }

    public int getEquipamentos(){
        return this.equipamentosApanhados.size();
    }

    public void colocarCoordenada(int x1, int y1){
        this.x = x1;
        this.y = y1;

    }

    public int cordenadaX(){
        return this.x;
    }

    public int cordenadaY(){
        return this.y;
    }

    public ArrayList<Equipamento> getEquipamentosApanhados(){
        return this.equipamentosApanhados;
    }

    @Override
    public String toString(){
        return id +" | "+ "Humano" +" | "+ nomeEquipa +" | "+ nome+ " "
                + equipamentosApanhados.size() +" @ ("+x+", "+y+")";
    }
}
