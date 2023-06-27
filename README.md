# A Batalha Naval
<h2>Atividade para desenvolver em java, com servidor de rede de host local.<\h2>

<p>As embarcações são posicionadas em um tabuleiro de dimensões 10x10.<\p>
Cada jogador possui uma frota de embarcações, composta pelos seguintes tipos:

   - Porta-aviões (P): ocupa 5 posições do tabuleiro.
   - Navios-tanque (N): cada um ocupa 4 posições do tabuleiro.
   - Contra-torpedeiros (C): cada um ocupa 3 posições do tabuleiro.
   - Submarinos (S): cada um ocupa 2 posições do tabuleiro.

As posições das embarcações devem ser conectadas em uma linha reta, na vertical ou horizontal.
Os jogadores se alternam, atirando uma vez por turno.

Cada jogador informa as coordenadas do tiro (linha e coluna) para tentar acertar uma embarcação do oponente.
Se um tiro acerta uma posição ocupada por uma embarcação, o jogador deve ser informado e pode realizar outro tiro na mesma rodada.
Se um tiro atinge todas as posições ocupadas por uma embarcação, o jogador deve ser informado que a embarcação foi afundada.
O jogo continua até que todas as embarcações de um dos jogadores sejam afundadas.
