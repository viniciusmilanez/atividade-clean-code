import java.util.*;

public class ResultadoTorneio {
    private final List<Time> times;
    private final List<Partida> partidas;

    public ResultadoTorneio(List<Time> times, List<Partida> partidas) {
        this.times = times;
        this.partidas = partidas;
    }

    public void imprimirClassificacao() {
        System.out.println("\nClassificação Final:");
        times.sort((a, b) -> Integer.compare(b.getPontos(), a.getPontos()));
        int pos = 1;
        for (Time time : times) {
            System.out.println(pos++ + ". " + time.getNome() + " (" + time.getPontos() + " pontos)");
        }
    }

    public void imprimirResultados() {
        System.out.println("\nResultados:");
        for (Partida partida : partidas) {
            System.out.println(partida.getResultado());
        }
    }
}
