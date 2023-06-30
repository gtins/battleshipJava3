package jogo;

public class Agua extends Elemento {

    public Agua(int x, int y, int tamanho , String tipo){
        this.tamanho = 1;
        this.linha = linha;
        this.coluna = coluna;
        this.horizontal = false;
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