public class Embarcacoes {
    protected String tipo;
    protected int tamanho;
    protected int linha;
    protected int coluna;
    protected boolean horizontal;
    protected char caractere;
    protected int acertos;

    Embarcacoes(int tamanho, int linha, int coluna, boolean horizontal){
        this.tamanho = tamanho;
        this.linha = linha;
        this.coluna = coluna;
        this.horizontal = horizontal;
        this.acertos = 0;
        caractere = 'E';
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
    public boolean afundou(){
        return tamanho == acertos;
    }

    public void atiraEm() {
        // tabuleiro
        // linha
        // coluna


    }

    public char getCaractere() {
        return caractere;
    }

    @Override
    public String toString() {
        return caractere + "";
    }
}