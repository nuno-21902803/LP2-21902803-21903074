package pt.ulusofona.lp2.theWalkingDEISIGame;


public class Vivo extends Creature{
    private boolean isSafe;
    private boolean tomouVeneno;
    private boolean deadVeneno;
    private int nrTurnosEnvenenados;


    public Vivo(int id, int idTipo, String tipoCriatura, String nomeEquipa, String nome, int x, int y,
                Equipamento equipamento, int n, boolean isDead, boolean isSafe) {
        super(id, idTipo, tipoCriatura, nomeEquipa, nome, x, y, equipamento,n, isDead);
        this.isSafe = isSafe;
    }

    public int getNrTurnosEnvenenados() {
        return nrTurnosEnvenenados;
    }

    public void setNrTurnosEnvenenados(int nrTurnosEnvenenados) {
        this.nrTurnosEnvenenados = nrTurnosEnvenenados;
    }

    public boolean isDeadVeneno() {
        return deadVeneno;
    }

    public void setDeadVeneno(boolean deadVeneno) {
        this.deadVeneno = deadVeneno;
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

    public boolean getIsSafe() {
        return this.isSafe;
    }


}
