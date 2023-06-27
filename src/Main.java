import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tabuleiro Tabuleiro = new Tabuleiro(10);

        for(int i = 0; i < 5; i++){
            System.out.printf("Digite o tamanho do seu Navio: ");
            int tamanho = Integer.parseInt(scanner.nextLine());
            System.out.printf("Digite a linha inicial: ");
            int linha = Integer.parseInt(scanner.nextLine());
            System.out.printf("Digite a coluna inicial: ");
            int coluna = Integer.parseInt(scanner.nextLine());
            System.out.printf("Horizontal? (true ou false): ");
            boolean horizontal = Boolean.parseBoolean(scanner.nextLine());
            Tabuleiro.posicionar(tamanho, linha, coluna, horizontal);
        }
        Tabuleiro.imprimir();

//        Tabuleiro.posicionar(5,0,0,true);
//        Tabuleiro.posicionar(5,2,2,false);
//        Tabuleiro.imprimir();
    }
}