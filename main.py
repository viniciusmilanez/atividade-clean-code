import logging

class Time:
    def __init__(self, nome):
        self.nome = nome
        self.pontos = 0

    def get_nome(self):
        return self.nome

    def get_pontos(self):
        return self.pontos

    def adicionar_pontos(self, pontos):
        self.pontos += pontos


class Partida:
    def __init__(self, time_a, time_b, gols_a, gols_b):
        self.time_a = time_a
        self.time_b = time_b
        self.gols_a = gols_a
        self.gols_b = gols_b

    def computar_resultado(self):
        if self.gols_a > self.gols_b:
            self.time_a.adicionar_pontos(3)
        elif self.gols_b > self.gols_a:
            self.time_b.adicionar_pontos(3)
        else:
            self.time_a.adicionar_pontos(1)
            self.time_b.adicionar_pontos(1)

    def get_resultado(self):
        return f"{self.time_a.get_nome()} {self.gols_a} x {self.gols_b} {self.time_b.get_nome()}"


class ResultadoTorneio:
    def __init__(self, times, partidas):
        self.times = times
        self.partidas = partidas

    def imprimir_classificacao(self):
        print("\nClassificação Final:")
        self.times.sort(key=lambda x: x.get_pontos(), reverse=True)
        for pos, time in enumerate(self.times, 1):
            print(f"{pos}. {time.get_nome()} ({time.get_pontos()} pontos)")

    def imprimir_resultados(self):
        print("\nResultados:")
        for partida in self.partidas:
            print(partida.get_resultado())


class Torneio:
    def __init__(self):
        self.times = {}
        self.partidas = []
        self.logger = self.configurar_logger()

    def configurar_logger(self):
        logger = logging.getLogger("TorneioLogger")
        handler = logging.FileHandler("log.txt", mode='a')
        handler.setFormatter(logging.Formatter('%(asctime)s - %(message)s'))
        logger.addHandler(handler)
        logger.setLevel(logging.WARNING)
        return logger

    def adicionar_time(self, nome):
        try:
            if not nome or nome.strip() == "":
                raise ValueError("Nome inválido")
            if nome in self.times:
                print(f" Time \"{nome}\" já registrado.")
                return
            time = Time(nome)
            self.times[nome] = time
            print(f"Time \"{nome}\" adicionado com sucesso!")
        except Exception as e:
            self.logger.warning(f"Erro ao adicionar time: {e}")
            print(f" Erro: {e}")

    def criar_partida(self, nome_a, nome_b, gols_a, gols_b):
        try:
            if nome_a not in self.times or nome_b not in self.times:
                raise ValueError("Time não existe")
            if gols_a < 0 or gols_b < 0:
                raise ValueError("Número inválido de gols")
            if nome_a == nome_b:
                raise ValueError("Um time não pode jogar contra si mesmo.")

            partida = Partida(self.times[nome_a], self.times[nome_b], gols_a, gols_b)
            self.partidas.append(partida)
            print(f" Partida entre \"{nome_a}\" e \"{nome_b}\" criada com sucesso!")
        except Exception as e:
            self.logger.warning(f"Erro ao criar partida: {e}")
            print(f" Erro: {e}")

    def jogar(self):
        for partida in self.partidas:
            partida.computar_resultado()
        return ResultadoTorneio(list(self.times.values()), self.partidas)


# Exemplo de uso
if __name__ == "__main__":
    torneio = Torneio()

    # Adicionando times
    torneio.adicionar_time("Brasil")
    torneio.adicionar_time("")  # Erro: Nome inválido
    torneio.adicionar_time("Canadá")
    torneio.adicionar_time("Argentina")
    torneio.adicionar_time("Angola")

    # Criando partidas
    torneio.criar_partida("Brasil", "Canadá", 1, 0)
    torneio.criar_partida("Argentina", "Angola", 0, 1)
    torneio.criar_partida("Brasil", "Argentina", -10, -2)  # Erro
    torneio.criar_partida("Brasil", "Argentina", 0, 2)
    torneio.criar_partida("Angola", "Canadá", 1, 1)
    torneio.criar_partida("Brasil", "Angola", 3, 2)
    torneio.criar_partida("Argentina", "Nigéria", 3, 3)  # Erro
    torneio.criar_partida("Argentina", "Canadá", 2, 4)

    # Resultado final
    resultados = torneio.jogar()
    resultados.imprimir_classificacao()
    resultados.imprimir_resultados()
