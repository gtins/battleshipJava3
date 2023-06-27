import java.util.ArrayList;

public class Tabuleiro {
    private char[][] tela;
    //private Embarcacoes [] barcos;
    private int tamanho;
    public Tabuleiro(int tamanho){ //descobri isso sem querer
        this.tamanho = tamanho;
        this.tela = new char[tamanho][tamanho];  //constroi o tabuleiro vazio

        //loop para o tabuleiro
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                tela[i][j] = '-'; //matriz vazia
            }
        }

    }

    public void posicionar(int tamanho, int linha, int coluna, boolean horizontal){
        Embarcacoes navio = new Embarcacoes(tamanho, linha, coluna, horizontal);

        for (int i = 0; i < Embarcacoes.getTamanho(); i++){
            if (horizontal == true){
                tela[linha][coluna + i] = 'E'; //mudar para o caractere
            } else {
                tela[linha + i][coluna] = 'E';
            }
        }
        imprimir();
    }
    public void imprimir(){
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                System.out.print(tela[i][j]+ " ");
            }
            System.out.println();
        }
    }

}
