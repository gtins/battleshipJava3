import jogo.Agua;
import jogo.Cordenada;
import jogo.Elemento;
import jogo.Embarcacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static LinkedList<Embarcacao> embarcacoes;// objeto linkedlist do tipo embarcacao chamado de embarcacoes
    private static LinkedList<Cordenada> jogadas = new LinkedList<>();//linkedlist do tipo cordenada e de nome jogadas, nova linkedlist

    public static void main(String[] args) {
        String csvName = pedirNomeArquivo();
        embarcacoes = criar( csvName);//ta embaixo
        mostrarNavios();//ta embaixo
        jogadas();//ta embaixo
        jogar();//ta embaixo
        mostrarTabuleiro();
        System.out.println("Afundados" + getTotalAfundados());
    }

    private static int getTotalAfundados() {
        int contadorAfundados = 0;
        for (Embarcacao elemento: embarcacoes) {
            if(elemento.afundou()) {
                contadorAfundados++;
            }
        }
        return  contadorAfundados;
    }

    public static String getElemento(int x, int y) {
        String posicao = "*";
        for (Embarcacao elemento: embarcacoes) {
            if( elemento.temPosicao(x,y) ) {
                Cordenada c = elemento.getCordenada(x,y);
                return c.getTipo();
            }
        }
        return posicao;
    }

    private static void mostrarTabuleiro() {
        for(int x = 0; x < 10; x++) {
            for( int y = 0; y < 10; y++) {
                System.out.print(getElemento(x,y) + " ");
            }
            System.out.println();
        }
    }

    private static String pedirNomeArquivo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do arquivo: ");
        String csvFile = scanner.nextLine();
        scanner.close();
        String csvName = "src/" + csvFile + ".csv";
        return csvName;
    }

    private static void jogar() {
        for( Cordenada cordenada : jogadas) { //vai varrer o jogadas
            atirarBarco(cordenada);//cada jogada é um tiro
        }
    }

    private static void jogadas() {//adiciona na linkedlist novos objetos do tipo cordenada, que por serem do construtor padrão tem x e y.
        jogadas.add( new Cordenada(5,3));
        jogadas.add( new Cordenada( 9, 8));
        jogadas.add( new Cordenada(9,9));
    }

    private static boolean atirarBarco(Cordenada c ) {//metodo atirar recebe uma variavel do tipo cordenada c
        boolean acertou = false;
        int indice = 0;
        int forca = 0;
        do {
            Embarcacao elemento = embarcacoes.get(indice);//indice é tipo o varrer matriz em funcao de i em um for
            if( elemento.acertou( c.getX(), c.getY()) ) { //usa o metodo acertou do embarcacoes, e se retorna true...
                forca = elemento.atirar(c.getX(), c.getY());//metodo atirar de embarcacoes e nao do main
                //faz a logica fo atirar, que vai diminuir a força (vida) do barco
                acertou = true; //muda valor da variavel
            }
            indice++; //incrementa indice
        } while( acertou != true && indice < embarcacoes.size()); //determina que o while vai rodar até ele retornar um acerto e for menor que o tamanho
        //de chaves(intenS) dentro de embarcaçao
        return acertou;//retorna variavel
    }

    private static void mostrarNavios() { //imprimir do codigo antigo basicamente só que em "lego"
        for (Embarcacao elemento: embarcacoes
        ) {
            System.out.println(elemento + " Forca " + elemento.getForca() + elemento.getPosicoes());
        }
    }

    /**
     * Le o arquivo
     * @return as embarcacoes do meu jogo
     */
    private static LinkedList<Embarcacao> criar(String csvFile ) {
        LinkedList<Embarcacao> embarcacoes = new LinkedList<>(); // objeto do tipo linked list de nome embarcacoes, nova linked list
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] items = line.split(csvSplitBy);

                int linha = Integer.parseInt(items[0]);
                int coluna = Integer.parseInt(items[1]);
                int tamanho = Integer.parseInt(items[2]);
                String tipo = items[3];
                boolean orientacao = Boolean.parseBoolean(items[4]);

                embarcacoes.add( new Embarcacao(linha,coluna, tamanho, tipo,orientacao) ); // adiciona na lista criada um novo objeto embarcaçao // DAQUI VAI PRA CLASSE EMBARCACAO PRA ENTENDER
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return embarcacoes;//retorna a lista ATUALIZADA de embarcacoes com todos os seus objetos ja criados
    }

}
