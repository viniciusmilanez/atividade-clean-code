public class Main {
    public static void main(String[] args) {
        Torneio torneio = new Torneio();

        // Adicionando times
        torneio.adicionarTime("Brasil");
        torneio.adicionarTime(""); //  Erro: Nome inválido
        torneio.adicionarTime("Canadá");
        torneio.adicionarTime("Argentina");
        torneio.adicionarTime("Angola");

        // Criando partidas
        torneio.criarPartida("Brasil", "Canadá", 1, 0);
        torneio.criarPartida("Argentina", "Angola", 0, 1);
        torneio.criarPartida("Brasil", "Argentina", -10, -2); //  Erro
        torneio.criarPartida("Brasil", "Argentina", 0, 2);
        torneio.criarPartida("Angola", "Canadá", 1, 1);
        torneio.criarPartida("Brasil", "Angola", 3, 2);
        torneio.criarPartida("Argentina", "Nigéria", 3, 3); //  Erro
        torneio.criarPartida("Argentina", "Canadá", 2, 4);

        // Resultado final
        ResultadoTorneio resultados = torneio.jogar();
        resultados.imprimirClassificacao();
        resultados.imprimirResultados();
    }
}
