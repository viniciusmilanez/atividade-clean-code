public class Partida {
    private final Time timeA;
    private final Time timeB;
    private final int golsA;
    private final int golsB;

    public Partida(Time timeA, Time timeB, int golsA, int golsB) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.golsA = golsA;
        this.golsB = golsB;
    }

    public void computarResultado() {
        if (golsA > golsB) {
            timeA.adicionarPontos(3);
        } else if (golsB > golsA) {
            timeB.adicionarPontos(3);
        } else {
            timeA.adicionarPontos(1);
            timeB.adicionarPontos(1);
        }
    }

    public String getResultado() {
        return timeA.getNome() + " " + golsA + " x " + golsB + " " + timeB.getNome();
    }
}
