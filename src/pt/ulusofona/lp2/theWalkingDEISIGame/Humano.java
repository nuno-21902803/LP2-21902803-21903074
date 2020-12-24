package pt.ulusofona.lp2.theWalkingDEISIGame;


import java.util.ArrayList;


public class Humano extends Creature{
    boolean isDead;
    boolean isSafe;
    boolean tomouVeneno;
    boolean deadVeneno;

    public Humano(int id, int idTipo, String tipoCriatura, String nomeEquipa, String nome, int x, int y,
                  ArrayList<Equipamento> equipamentos, boolean isDead, boolean isSafe) {
        super(id, idTipo, tipoCriatura, nomeEquipa, nome, x, y, equipamentos);
        this.isDead = isDead;
        this.isSafe = isSafe;
    }


    public boolean isDeadVeneno() {
        return deadVeneno;
    }

    public void setDeadVeneno(boolean deadVeneno) {
        this.deadVeneno = deadVeneno;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    public boolean getTomouVeneno() {
        return tomouVeneno;
    }

    public void setTomouVeneno(boolean tomouVeneno) {
        this.tomouVeneno = tomouVeneno;
    }

    public boolean getIsDead() {
        return this.isDead;
    }

    public boolean getIsSafe() {
        return this.isSafe;
    }


}
