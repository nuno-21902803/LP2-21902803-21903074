package pt.ulusofona.lp2.theWalkingDEISIGame;


public class Creature {

    private int id;
    private int idTipo;
    private String tipoCriatura;
    private String nomeEquipa;
    private String nome;
    private int x;
    private int y;
    private Equipamento equipamento = new Equipamento();
    private int numEqApanhadosDestruidos = 0;
    private boolean isDead;
    private boolean isSafe;
    private int transformacoes = 0;
    private int zombieKILLED = 0;

    public Creature() {
    }

    public Creature(int id, int idTipo, String tipoCriatura, String nomeEquipa,
                    String nome, int x, int y, Equipamento equipamento, int numEqApanhadosDestruidos, boolean isDead
    ,boolean isSafe, int transformacoes, int zombieKILLED) {
        this.id = id;
        this.idTipo = idTipo;
        this.tipoCriatura = tipoCriatura;
        this.nomeEquipa = nomeEquipa;
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.equipamento = equipamento;
        this.numEqApanhadosDestruidos = numEqApanhadosDestruidos;
        this.isDead = isDead;
        this.isSafe = isSafe;
        this.transformacoes = transformacoes;
        this.zombieKILLED =  zombieKILLED;
    }



    public void colocarCoordenada(int x1, int y1) {
        this.x = x1;
        this.y = y1;
    }

    public int getId() {
        return id;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getImagePNG() {
        switch (idTipo) {
            case 0:
                return "zombiekid.png";
            case 1:
                return "zombieA.png";
            case 2:
                return "zombie.png";
            case 3:
                return "zombie_Idoso.png";
            case 4:
                return "zombie_vamp.png";
            case 5:
                return "human_K.png";
            case 6:
                return "human.png";
            case 7:
                return "human_M.png";
            case 8:
                return "human_I.png";
            case 9:
                return "dog.png";
            case 10:
                return "betaZombie.png";
            default:
                throw new IllegalArgumentException("Equipment Not Found");
        }
    }

    public String getNome() {
        return nome;
    }

    public int cordenadaX() {
        return x;
    }

    public int cordenadaY() {
        return y;
    }

    public int getEquipamentos(){
        return this.numEqApanhadosDestruidos;
    }

    public void setNumEqApanhadosDestruidos(int numEqApanhadosDestruidos) {
        this.numEqApanhadosDestruidos = numEqApanhadosDestruidos;
    }

    public void setTipoCriatura(String tipoCriatura) {
        this.tipoCriatura = tipoCriatura;
    }

    public void setNomeEquipa(String nomeEquipa) {
        this.nomeEquipa = nomeEquipa;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    public boolean getIsSafe() {
        return this.isSafe;
    }

    public int getTransformacoes() {
        return transformacoes;
    }

    public void setTransformacoes(int transformacoes) {
        this.transformacoes = transformacoes;
    }


    public int getZombieKILLED() {
        return zombieKILLED;
    }

    public void setZombieKILLED(int zombieKILLED) {
        this.zombieKILLED = zombieKILLED;
    }

    @Override
    public String toString(){
        Creature creature = TWDGameManager.criaturas.get(id);

        if (creature!=null) {
            if (creature instanceof Vivo) {
                if (((Vivo) creature).getIsSafe()) {
                    return id + " | " + tipoCriatura + " | " + nomeEquipa + " | " + nome + " " +
                            getEquipamentos() + " @ A salvo";
                }
            }

            if (creature.getIsDead()) {
                return id + " | " + tipoCriatura + " | " + nomeEquipa + " | " + nome + " " +
                        getEquipamentos() + " @ RIP";
            }

        }

        return id + " | " + tipoCriatura + " | " + nomeEquipa + " | " + nome + " " +
                getEquipamentos() + " @ (" + cordenadaX() + ", " + cordenadaY() + ")";

    }


}