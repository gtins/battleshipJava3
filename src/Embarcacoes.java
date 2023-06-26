public class Embarcacoes {
    protected int tamanho;
    protected int linha;
    protected int coluna;
    protected boolean horizontal;

    protected Tabuleiro tabuleiro;

    void Coordenada(String tipo, int linha, int coluna, char letra, char direcao){
        this.tamanho = tamanho;
        this.linha = linha;
        this.coluna = coluna;
        this.horizontal = horizontal;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }
}