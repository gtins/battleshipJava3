import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Tabuleiro Tabuleiro = new Tabuleiro(10);
        Tabuleiro.posicionar(5,0,0,true);
        Tabuleiro.posicionar(5,2,2,false);
        Tabuleiro.imprimir();
    }
}