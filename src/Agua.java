public class Agua extends Elemento {

    Agua(int tamanho, int linha, int coluna, boolean horizontal){
        this.tamanho = tamanho;
        this.linha = linha;
        this.coluna = coluna;
        this.horizontal = horizontal;
        this.acertos = 0;
        caractere = '*';
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