package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Zombie extends Creature {

    boolean isDead;
    boolean tranformado;

    //zombie
    public Zombie(int id, int idTipo, String tipoCriatura, String nomeEquipa, String nome, int x, int y,
                  ArrayList<Equipamento> equipamentos) {
        super(id, idTipo, tipoCriatura, nomeEquipa, nome, x, y, equipamentos);
    }

    public boolean getIsDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean getIsTranformado() {
        return tranformado;
    }

    public void setTranformado(boolean tranformado) {
        this.tranformado = tranformado;
    }
}
