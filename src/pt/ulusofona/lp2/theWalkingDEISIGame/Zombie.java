package pt.ulusofona.lp2.theWalkingDEISIGame;


import java.util.List;

public class Zombie {

    int id;
    int idTipo = 0;
    String nomeEquipa;
    String nome;
    int x,y;
    List<Equipamento> equipamentosDestruidos;

    //zombie
    public Zombie(int id, int idTipo, String nome, int x, int y) {
        this.id = id;
        this.idTipo = idTipo;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public Zombie(int id, int idTipo, String nomeEquipa, String nome, int x, int y, List<Equipamento> equipamentosDestruidos) {
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
        return this.id +" | "+ this.idTipo +" | "+ this.nomeEquipa +" | "+ this.nome+ " "+ this.equipamentosDestruidos.size() +"@ (<x>, <y>)";
    }
}
