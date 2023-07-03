import jogo.Cordenada;
import jogo.Embarcacao;
import network.EnviarJogadaServidor;
import network.ReceberJogadas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Batalha {
    public String servidor = "10.199.74.53"; // Replace with the server IP address
    public static final int PORTA_RECEBER = 6000; // Replace with the server port number
    public static final int PORTA_ENVIAR = 5000; // Replace with the server port number

    private  LinkedList<Embarcacao> embarcacoes;// objeto linkedlist do tipo embarcacao chamado de embarcacoes
    private  LinkedList<Cordenada> jogadasParaEnviar = new LinkedList<>();//linkedlist do tipo cordenada e de nome jogadas, nova linkedlist

    private  LinkedList<Cordenada> jogadasRecebidas = new LinkedList<>();
    private Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Batalha batalha = new Batalha();
        batalha.run();
    }

    public void init() {
        iniciarTabuleiro();
        ReceberJogadas receberJogadas = new ReceberJogadas(PORTA_RECEBER , jogadasRecebidas );
        receberJogadas.start();
    }

    private void iniciarTabuleiro() {
        String csvName = pedirNomeArquivo();
        embarcacoes = criar( csvName);//ta embaixo
        mostrarNavios();//ta embaixo
        jogadas();//ta embaixo

        mostrarTabuleiro();
        System.out.println("Afundados" + getTotalAfundados());
    }

    public void run() {
        init();
        jogadas();//ta embaixo
    }

    private  int getTotalAfundados() {
        int contadorAfundados = 0;
        for (Embarcacao elemento: embarcacoes) {
            if(elemento.afundou()) {
                contadorAfundados++;
            }
        }
        return  contadorAfundados;
    }

    public  String getElemento(int x, int y) {
        String posicao = "*";
        for (Embarcacao elemento: embarcacoes) {
            if( elemento.temPosicao(x,y) ) {
                Cordenada c = elemento.getCordenada(x,y);
                return c.getTipo();
            }
        }
        return posicao;
    }

    private  void mostrarTabuleiro() {
        for(int x = 0; x < 10; x++) {
            for( int y = 0; y < 10; y++) {
                System.out.print(getElemento(x,y) + " ");
            }
            System.out.println();
        }
    }

    private  String pedirNomeArquivo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do arquivo: ");
        String csvFile = scanner.nextLine();
        System.out.print("Digite o endereco do outro jogador (servidor): ");
        servidor = scanner.nextLine();

        String csvName = "src/" + csvFile + ".csv";
        return csvName;
    }

    private  void jogar() {
        for( Cordenada cordenada : jogadasParaEnviar) { //vai varrer o jogadas
            atirarBarco(cordenada);//cada jogada é um tiro
        }
    }

    private  void jogadas() {//adiciona na linkedlist novos objetos do tipo cordenada, que por serem do construtor padrão tem x e y.
        // depois pode ser um system out e um scanner TODO
        Scanner localScanner = new Scanner(System.in);
        System.out.printf("Digite coordenada X: ");
        int x = Integer.parseInt(this.scanner.nextLine());
        System.out.printf("Digite coordenada Y: ");
        int y = Integer.parseInt(this.scanner.nextLine());
        jogadasParaEnviar.add( new Cordenada(x,y));
        EnviarJogadaServidor enviarJogadaServidor = new EnviarJogadaServidor(servidor, PORTA_ENVIAR);
        for( Cordenada c : jogadasParaEnviar) {
            enviarJogadaServidor.executar(c.getX(), c.getY());
        }
        localScanner.close();
    }

    private  boolean atirarBarco(Cordenada c ) {//metodo atirar recebe uma variavel do tipo cordenada c
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

    private  void mostrarNavios() { //imprimir do codigo antigo basicamente só que em "lego"
        for (Embarcacao elemento: embarcacoes
        ) {
            System.out.println(elemento + " Forca " + elemento.getForca() + elemento.getPosicoes());
        }
    }

    /**
     * Le o arquivo
     * @return as embarcacoes do meu jogo
     */
    private  LinkedList<Embarcacao> criar(String csvFile ) {
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
