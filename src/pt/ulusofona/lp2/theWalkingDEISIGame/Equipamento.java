package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Equipamento {
    int id;
    int idTipo;
    int x,y;

    public Equipamento(int id, int idTipo, int x, int y) {
        this.id = id;
        this.idTipo = idTipo;
        this.x = x;
        this.y = y;
    }

    public int getId(){
        return this.id;
    }

    public String getImagePNG(){
        return "equipment.png";
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

    @Override
    public String toString() {
        return "Equipamento{" + "id=" + id + ", idTipo=" + idTipo + ", x=" + x + ", y=" + y + '}';
    }
}

