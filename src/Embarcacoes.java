public class Embarcacoes {
    protected static int tamanho;
    protected int linha;
    protected int coluna;
    protected boolean horizontal;
    //protected String caractere;

    Embarcacoes(int tamanho, int linha, int coluna, boolean horizontal){
        this.tamanho = tamanho;
        this.linha = linha;
        this.coluna = coluna;
        this.horizontal = horizontal;
    }

    public static int getTamanho() {
        return tamanho;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

//    public String getCaractere() {
//        return caractere;
//    }

}