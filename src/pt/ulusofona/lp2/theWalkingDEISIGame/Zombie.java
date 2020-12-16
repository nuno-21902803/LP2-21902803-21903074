package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Zombie extends Creature {

    //zombie
    public Zombie(int id, int idTipo, String tipoCriatura, String nomeEquipa, String nome, int x, int y,
                  ArrayList<Equipamento> equipamentos) {
        super(id, idTipo, tipoCriatura, nomeEquipa, nome, x, y, equipamentos);
    }

}
