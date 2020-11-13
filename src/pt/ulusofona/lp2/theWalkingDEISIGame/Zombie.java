package pt.ulusofona.lp2.theWalkingDEISIGame;


import java.util.ArrayList;


public class Zombie {

    int id;
    int idTipo = 0;
    String nomeEquipa = "Os Outros";
    String nome ="";
    int x,y;
    ArrayList<Equipamento> equipamentosDestruidos = new ArrayList<>();

    //zombie


    public Zombie(int id, int idTipo, String nome, int x, int y) {
        this.id = id;
        this.idTipo = idTipo;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public Zombie(int id, int idTipo, String nomeEquipa, String nome, int x, int y, ArrayList<Equipamento> equipamentosDestruidos) {
        this.id = id;
        this.idTipo = idTipo;
        this.nomeEquipa = nomeEquipa;
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.equipamentosDestruidos = equipamentosDestruidos;
    }

    public int getId(){
        return this.id;
    }


    public String getImagePNG(){
        return null;
    }

    public int getEquipamentos(){
        return this.equipamentosDestruidos.size();
    }

    public String toString(){
        return id +" | "+ idTipo +" | "+ nomeEquipa +" | "+ nome+ " "+ equipamentosDestruidos.size() +"@ ("+x+", "+y+")";
    }
}
