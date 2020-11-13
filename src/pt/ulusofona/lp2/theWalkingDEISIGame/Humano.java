package pt.ulusofona.lp2.theWalkingDEISIGame;


import java.util.List;

public class Humano {
    //humano
    int id;
    int idTipo =1;
    String nomeEquipa;
    String nome;
    int x,y;
    List<Equipamento> equipamentosApanhados;


    public Humano(int id, int idTipo, String nome, int x, int y) {
        this.id = id;
        this.idTipo = idTipo;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public Humano(int id, int idTipo, String nomeEquipa, String nome, int x, int y, List<Equipamento> equipamentosApanhados) {
        this.id = id;
        this.idTipo = idTipo;
        this.nomeEquipa = nomeEquipa;
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.equipamentosApanhados = equipamentosApanhados;
    }

    public Humano(int id, int idTipo, String nomeEquipa, String nome, int x, int y) {
        this.id = id;
        this.idTipo = idTipo;
        this.nomeEquipa = nomeEquipa;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public int getId(){
        return this.id;
    }

    public String getImagePNG(){
        return null;
    }

    public int getEquipamentos(){
        return this.equipamentosApanhados.size();
    }

    public String toString(){
        return this.id +" | "+ this.idTipo +" | "+ this.nomeEquipa +" | "+ this.nome+ " "+ this.equipamentosApanhados.size() +"@ (<x>, <y>)";
    }
}
