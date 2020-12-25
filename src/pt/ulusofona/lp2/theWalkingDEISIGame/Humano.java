package pt.ulusofona.lp2.theWalkingDEISIGame;


public class Humano extends Creature{
    private boolean isDead;
    private boolean isSafe;
    private boolean tomouVeneno;
    private boolean deadVeneno;


    public Humano(int id, int idTipo, String tipoCriatura, String nomeEquipa, String nome, int x, int y,
                  Equipamento equipamento,int n, boolean isDead, boolean isSafe) {
        super(id, idTipo, tipoCriatura, nomeEquipa, nome, x, y, equipamento,n);
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
