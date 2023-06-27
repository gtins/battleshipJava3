import java.util.ArrayList;

public class Tabuleiro {
    private char[][] tela;
    private Embarcacoes [] barcos;
    private int tamanho;
    public Tabuleiro(int tamanho){ //descobri isso sem querer
        this.tamanho = tamanho;
        this.tela = new char[tamanho][tamanho];  //constroi o tabuleiro vazio
        this.barcos = new Embarcacoes[5];

        //loop para o tabuleiro
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                tela[i][j] = '-'; //matriz vazia
            }
        }

    }

    public void posicionar(Embarcacoes barcos){

    }
    public void Imprimir(){

    }

}
