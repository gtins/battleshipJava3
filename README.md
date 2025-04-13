# Batalha Naval em Java — Versão Final

Esta é a versão final e mais completa do projeto **Batalha Naval** em Java, agora com **suporte a rede local** para múltiplos jogadores.

---

## Objetivo

Desenvolver uma versão do clássico jogo Batalha Naval com:

- Suporte a dois jogadores em rede local (servidor e cliente)
- Tabuleiro 10x10
- Frota completa com diferentes tipos de embarcação
- Mecânica de turnos, tiros, acertos e afundamentos

---

## Mudanças

- Implementação de **servidor de rede local** para conectar jogadores
- Código refeito e organizado do zero com foco em clareza e manutenção
- Estrutura de jogo mais próxima de um produto final jogável

---

## Tipos de Embarcação

- Porta-aviões (P) – 5 posições
- Navios-tanque (N) – 4 posições
- Contra-torpedeiros (C) – 3 posições
- Submarinos (S) – 2 posições

---

# A Batalha Naval

<p>Os jogadores se alternam, atirando uma vez por turno.</p>

<p>Cada jogador informa as coordenadas do tiro (linha e coluna) para tentar acertar uma embarcação do oponente.
Se um tiro acerta uma posição ocupada por uma embarcação, o jogador deve ser informado e pode realizar outro tiro na mesma rodada.
Se um tiro atinge todas as posições ocupadas por uma embarcação, o jogador deve ser informado que a embarcação foi afundada.
O jogo continua até que todas as embarcações de um dos jogadores sejam afundadas.</p>
