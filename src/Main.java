import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro(10);
        Embarcacoes[] barcos = new Embarcacoes[5];
        int i = 0;

        for(i = 0; i < 5; i++){ //esse 5 não muda, afinal são no máximo 5 barcos no jogo
            System.out.printf("Digite o tamanho do seu Navio: ");
            int tamanho = Integer.parseInt(scanner.nextLine());
            System.out.printf("Digite a linha inicial: ");
            int linha = Integer.parseInt(scanner.nextLine());
            System.out.printf("Digite a coluna inicial: ");
            int coluna = Integer.parseInt(scanner.nextLine());
            System.out.printf("Horizontal? (true ou false): ");
            boolean horizontal = Boolean.parseBoolean(scanner.nextLine());
            tabuleiro.posicionar(tamanho, linha, coluna, horizontal);
            tabuleiro.imprimir();
        }
//        while (!tabuleiro.jogoAcabou()){
//            System.out.print("Digite seu palpite: (linha, coluna): ");
//            int lin = scanner.nextInt();
//            int col = scanner.nextInt();
//
//            if(tabuleiro.acertou(lin, col)) {
//                tabuleiro.atualizaTabuleiro(lin, col, true);
//                System.out.println("Acertou!");
//            } else {
//                tabuleiro.atualizaTabuleiro(lin, col, false);
//                System.out.println("Errou!");
//            }
//            tabuleiro.imprimir();
//        }
    }
}