package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Equipamento {
    int id;
    int idTipo;
    int x,y;
    boolean apanhado=false;
    boolean destruido=false;

    public Equipamento() {
    }

    public Equipamento(int id, int idTipo, int x, int y) {
        this.id = id;
        this.idTipo = idTipo;
        this.x = x;
        this.y = y;
    }

    public int getId(){
        return this.id;
    }

    public int getTypeID(){
        return this.idTipo;
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
        return "Equipamento{" + "id=" + id + ", idTipo=" + idTipo + ", x=" + x + ", y=" + y + '}';
    }
}

