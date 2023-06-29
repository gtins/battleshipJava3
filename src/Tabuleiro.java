public class Tabuleiro {
    private Elemento[][] tela;
    private Elemento[][] mar;
    private Embarcacoes[] barcos; //teste para armazenar barcos
    private int tamanho;
    public Tabuleiro(int tamanho, int linha, int coluna, boolean horizontal){ //descobri isso sem querer
        this.tamanho = tamanho;
        //constroi o tabuleiro vazio
        mar = new Elemento[tamanho][tamanho];
        Elemento oceano = new Agua(tamanho, linha, coluna, horizontal);
        //Agua oceano = new Agua()

        //loop para o tabuleiro
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                mar[i][j] = oceano; //matriz vazia
            }
        }
//sera necessario criar a classe agua, "encher o tabuleiro de agua", criando uma classe agua que construir
//uma embarcacao falsa, caracter = * e tudo mais sendo IGUAL a navio.
    }
    public void posicionar(int tamanho, int linha, int coluna, boolean horizontal){
        this.tela = new Embarcacoes[tamanho][tamanho];
        barcos = new Embarcacoes[5];
        Embarcacoes navio = new Embarcacoes(tamanho, linha, coluna, horizontal);

        for (int i = 0; i < navio.getTamanho(); i++){ //5 provisorio, preciso ver um jeito de ficar melhor
            if (horizontal){
                tela[linha][coluna + i] = navio; //mudar para o caractere
            } else {
                tela[linha + i][coluna] = navio;
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
            for (int j = 0; j < tamanho ; j++) {
                String letra = "K";
                if (tela[i][j] == null) {
                    letra = tela[i][j].toString();
                }
                System.out.print( letra + " ");
                //System.out.print(tela[i][j].toString()+ " ");
            }
            System.out.println();
        }
    }

    public boolean acertou(int lin, int col){
        if (tela[lin][col] != null){
            return true;
        } else {
            return false;
        }
    }
    public void atualizaTabuleiro(int lin, int col, boolean acertou){
        if (acertou){
            tela[lin][col].toString();
        } else {
            tela [lin][col].toString();
        }
    }
    public boolean jogoAcabou() {
        for (Embarcacoes navio : barcos) {
            if (navio != null && !navio.afundou()) {
                return false;
            }
        }
        return true;
    }

}
