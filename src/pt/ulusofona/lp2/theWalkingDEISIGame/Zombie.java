package pt.ulusofona.lp2.theWalkingDEISIGame;


import java.util.ArrayList;


public class Zombie {

    int id;
    int idTipo;
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

    public Zombie(int id, int idTipo, String nomeEquipa, String nome, int x, int y,
                  ArrayList<Equipamento> equipamentosDestruidos) {

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
        return "zombie.png";
    }

    public int getEquipamentos(){
        return this.equipamentosDestruidos.size();
    }

    public void colocarCoordenada(int x1, int y1){
       // System.out.println("x = "+x+ "y =" +y);
        //System.out.println("x = "+x1+ "y =" +y1);
        this.x = x1;
        this.y = y1;

    }

    public int cordenadaX(){
        return this.x;
    }

    public int cordenadaY(){
        return this.y;
    }

    public ArrayList<Equipamento> getEquipamentosDestruidos(){
        return this.equipamentosDestruidos;
    }


    @Override
    public String toString(){
        return id +" | "+ "Zombie" +" | "+ nomeEquipa +" | "+ nome+ " "
                + equipamentosDestruidos.size() +" @ ("+x+", "+y+")";
    }
}
