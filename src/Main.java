import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tabuleiro Tabuleiro = new Tabuleiro(10);
        Embarcacoes[] barcos = new Embarcacoes[5];

        for(int i = 0; i < 5; i++){ //esse 5 não muda, afinal são no máximo 5 barcos no jogo
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
        for (int i = 0; i < barcos.length; i++) {
            System.out.println(barcos[i].getTamanho());
        }

//        Tabuleiro.posicionar(5,0,0,true);
//        Tabuleiro.posicionar(5,2,2,false);
//        Tabuleiro.imprimir();
    }
}