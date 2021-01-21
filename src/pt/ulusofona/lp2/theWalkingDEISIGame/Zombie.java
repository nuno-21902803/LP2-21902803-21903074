package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Zombie extends Creature {

    //zombie


    public Zombie(int id, int idTipo, String tipoCriatura, String nomeEquipa, String nome, int x, int y,
                  Equipamento equipamento, int numEqApanhadosDestruidos, boolean isDead, boolean isSafe,
                  int transformacoes) {

        super(id, idTipo, tipoCriatura, nomeEquipa, nome, x, y, equipamento, numEqApanhadosDestruidos, isDead, isSafe,
                transformacoes,0);
    }

}
