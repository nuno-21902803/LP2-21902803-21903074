package pt.ulusofona.lp2.theWalkingDEISIGame;


import java.util.ArrayList;


public class Humano extends Creature{
    boolean isDead;
    boolean isSafe;


    public Humano(int id, int idTipo, String tipoCriatura, String nomeEquipa, String nome, int x, int y,
                  ArrayList<Equipamento> equipamentos, boolean isDead, boolean isSafe) {
        super(id, idTipo, tipoCriatura, nomeEquipa, nome, x, y, equipamentos);
        this.isDead = isDead;
        this.isSafe = isSafe;
    }


    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    public boolean getIsDead() {
        return this.isDead;
    }

    public boolean getIsSafe() {
        return this.isSafe;
    }
}
