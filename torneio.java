import java.util.*;
import java.util.logging.*;

public class Torneio {
    private final Map<String, Time> times = new HashMap<>();
    private final List<Partida> partidas = new ArrayList<>();
    private final Logger logger;

    public Torneio() {
        logger = Logger.getLogger("TorneioLogger");
        try {
            FileHandler fileHandler = new FileHandler("log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            System.out.println("Erro ao iniciar o logger.");
        }
    }

    public void adicionarTime(String nome) {
        try {
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome inválido");
            }
            if (times.containsKey(nome)) {
                System.out.println(" Time \"" + nome + "\" já registrado.");
                return;
            }
            Time time = new Time(nome);
            times.put(nome, time);
            System.out.println("Time \"" + nome + "\" adicionado com sucesso!");
        } catch (Exception e) {
            logger.warning("Erro ao adicionar time: " + e.getMessage());
            System.out.println(" Erro: " + e.getMessage());
        }
    }

    public void criarPartida(String nomeA, String nomeB, int golsA, int golsB) {
        try {
            if (!times.containsKey(nomeA) || !times.containsKey(nomeB)) {
                throw new IllegalArgumentException("Time não existe");
            }
            if (golsA < 0 || golsB < 0) {
                throw new IllegalArgumentException("Número inválido de gols");
            }

            Time timeA = times.get(nomeA);
            Time timeB = times.get(nomeB);
            Partida partida = new Partida(timeA, timeB, golsA, golsB);
            partidas.add(partida);
            System.out.println(" Partida entre \"" + nomeA + "\" e \"" + nomeB + "\" criada com sucesso!");
        } catch (Exception e) {
            logger.warning("Erro ao criar partida: " + e.getMessage());
            System.out.println(" Erro: " + e.getMessage());
        }
    }

    public ResultadoTorneio jogar() {
        for (Partida p : partidas) {
            p.computarResultado();
        }
        return new ResultadoTorneio(new ArrayList<>(times.values()), partidas);
    }
}
