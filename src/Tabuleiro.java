public class Tabuleiro {
    private char[][] tela;
    private Embarcacoes[] barcos; //teste para armazenar barcos
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
        barcos = new Embarcacoes[5];
        Embarcacoes navio = new Embarcacoes(tamanho, linha, coluna, horizontal);

        for (int i = 0; i < Embarcacoes.getTamanho(); i++){ //5 provisorio, preciso ver um jeito de ficar melhor
            if (horizontal){
                tela[linha][coluna + i] = 'E'; //mudar para o caractere
            } else {
                tela[linha + i][coluna] = 'E';
            }
            barcos[i] = navio; //cada navio criado é armazenado no array
            //System.out.println(barcos[i].getTamanho());
        }
//        for (int i = 0; i < barcos.length; i++)
//        System.out.println(barcos[i].getTamanho());
        //imprimir();
    }
    public void imprimir(){
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                System.out.print(tela[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public boolean acertou(int lin, int col){
        if (tela[lin][col] == 'E'){
            return true;
        } else {
            return false;
        }
    }
    public void atualizaTabuleiro(int lin, int col, boolean acertou){
        if (acertou){
            tela[lin][col] = 'X';
        } else {
            tela [lin][col] = 'O';
        }
    }

}
