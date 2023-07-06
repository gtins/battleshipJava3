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
    public String servidor = "10.199.74.53"; // parte de redes 
    public static final int PORTA_RECEBER = 5000; // parte de redes 
    public static final int PORTA_ENVIAR = 6000; // parte de redes 

    private  LinkedList<Embarcacao> embarcacoes; // objeto linkedlist do tipo embarcacao chamado de embarcacoes
    private  LinkedList<Cordenada> jogadasParaEnviar = new LinkedList<>(); //linkedlist do tipo cordenada, jogadas a serem enviadas
    private  LinkedList<Cordenada> jogadasRecebidas = new LinkedList<>(); //linkedlist do tipo cordenada, jogadas sendo recebidas 
    private Scanner scanner = new Scanner(System.in); //novo objeto scanner

    public static void main(String[] args) {
        Batalha batalha = new Batalha(); //novo objeto batalha
        batalha.run(); //metodo run (indicado mais adiante)
    }

    public void init() {  //redes 
        iniciarTabuleiro(); //redes 
        ReceberJogadas receberJogadas = new ReceberJogadas(PORTA_RECEBER , jogadasRecebidas ); //redes
        receberJogadas.start(); //redes 
    }

    private void iniciarTabuleiro() { //método que irá iniciar o tabuleiro
        String csvName = pedirNomeArquivo(); // onde será pedido o nome do arquivo que conterá as embarcações 
        embarcacoes = criar( csvName); //método de criação das embarcações que recebe como parametro o arquivo indicado acima
        mostrarNavios(); //método para mostrar as embarcações de forma gráfica
        jogadas(); //método para realizar as jogadas
        mostrarTabuleiro(); //atualiza o tabuleiro 
        System.out.println("Afundados: " + getTotalAfundados()); //"placar" de navios afundados
    }

    public void run() {
        init(); //redes
        jogadas(); 
    }

    private  int getTotalAfundados() { //método para retorno do número de afundados
        int contadorAfundados = 0; //variavel vazia para servir de base 
        for (Embarcacao elemento: embarcacoes) { //for each embarcação...
            if(elemento.afundou()) { //se satisfazer a condição de elemento.afundou...
                contadorAfundados++; //incremento na variável
            }
        }
        return  contadorAfundados; //retorno de afundados
    }

    public  String getElemento(int x, int y) {
        String posicao = "□";
        for (Embarcacao elemento: embarcacoes) { // for each elemento em embarcações...
            if( elemento.temPosicao(x,y) ) { //se satisfazer a condição de ter sido posicionado...
                Cordenada c = elemento.getCordenada(x,y); //buscar a cordenada da posição
                return c.getTipo(); //tipos diferentes terão letras diferentes 
            }
        }
        return posicao;
    }

    private  void mostrarTabuleiro() { //método para "imprimir" o tabuleiro
        for(int x = 0; x < 10; x++) { //contador i
            for( int y = 0; y < 10; y++) { //contador j
                System.out.print(getElemento(x,y) + " "); //caractere retornado pela função anterior
            }
            System.out.println(); //imprimir
        }
    }

    private  String pedirNomeArquivo() {//método para solicitar o nome do arquivo

        Scanner scanner = new Scanner(System.in);//scanner da proxima linha
        System.out.print("Digite o nome do arquivo: "); // ...
        String csvFile = scanner.nextLine();//scanner da proxima linha 
        System.out.print("Digite o endereco do outro jogador (IP): \n");// ...
        servidor = "10.199.74.53"; //ip a ser usado em redes, scanner ou hardcoded
        String csvName = "src/" + csvFile + ".csv"; //variável que concatena texto e outra variável para criar uma "URL"
        return csvName; //retorna a URL
    }

    private  void jogar() { //método par registrar as jogadas
        for( Cordenada cordenada : jogadasParaEnviar) { //varredura da lista que contém as jogadas 
            atirarBarco(cordenada);//cada jogada é um tiro
        }
    }

    private  void jogadas() {//adiciona na linkedlist novos objetos do tipo cordenada, que por serem do construtor padrão tem x e y.
        Scanner localScanner = new Scanner(System.in); //scanner local
        System.out.printf("Hora do ataque, onde você quer lançar o torpedo?"); //...
        System.out.printf("\nDigite coordenada X: "); //...
        int x = Integer.parseInt(this.scanner.nextLine()); //scanner da proxima linha
        System.out.printf("Digite coordenada Y: "); //...
        int y = Integer.parseInt(this.scanner.nextLine()); //scanner da proxima linha
        jogadasParaEnviar.add( new Cordenada(x,y));//lista que armazena as jogadas recebe um novo objeto que por sua vez recebe as variáveis x e y recem scaneadas
        EnviarJogadaServidor enviarJogadaServidor = new EnviarJogadaServidor(servidor, PORTA_ENVIAR); //redes
        for( Cordenada c : jogadasParaEnviar) { //redes
            enviarJogadaServidor.executar(c.getX(), c.getY()); //redes
        }
        localScanner.close();//fechamos o scanner
    }

    private  boolean atirarBarco(Cordenada c ) {//metodo atirar recebe uma variavel do tipo cordenada c

        boolean acertou = false; //iniciando varíavel base
        int indice = 0; //iniciando varíavel base
        int forca = 0; //iniciando varíavel base
        do {
            Embarcacao elemento = embarcacoes.get(indice);//indice é como varrer a matriz em funcao de i em um for
            if( elemento.acertou( c.getX(), c.getY()) ) { //usa o metodo acertou do embarcacoes, e se retorna true...
                forca = elemento.atirar(c.getX(), c.getY());//metodo atirar de embarcacoes e nao do main
                //faz a logica fo atirar, que vai diminuir a força (vida) do barco
                acertou = true; //muda valor da variavel
            }
            indice++; //incrementa indice
        } while( acertou != true && indice < embarcacoes.size()); //determina que o while vai rodar até ele retornar um acerto e for menor que o tamanho
        //de chaves(itens) dentro de embarcaçao
        return acertou;//retorna variavel
    }

    private  void mostrarNavios() { //imprimir embarcações (cordenadas)
        for (Embarcacao elemento: embarcacoes
        ) {
            System.out.println(elemento + " Forca " + elemento.getForca() + elemento.getPosicoes());
        }
    }
    
    private  LinkedList<Embarcacao> criar(String csvFile ) { //criação da lista que contém as embarcações, recebendo como parametro o arquivo csv criado
        LinkedList<Embarcacao> embarcacoes = new LinkedList<>(); // objeto do tipo linked list de nome embarcacoes, nova linked list
        String line; //iniciando varíavel base
        String csvSplitBy = ","; //iniciando varíavel base

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) { //leitura do arquivo csv (objeto bufferreader que recebe um leitor de arquivo)
            while ((line = br.readLine()) != null) { //enquanto as linhas não são nulas...
                String[] items = line.split(csvSplitBy); //divide o arquivo por linha
                int linha = Integer.parseInt(items[0]); //linhas no indice 0
                int coluna = Integer.parseInt(items[1]); //colunas no indice 1
                int tamanho = Integer.parseInt(items[2]); //tamanho no indice 2
                String tipo = items[3]; //tipo no indice 3
                boolean orientacao = Boolean.parseBoolean(items[4]); //orientação no indice 4
                embarcacoes.add( new Embarcacao(linha,coluna, tamanho, tipo,orientacao) ); // adiciona na lista criada um novo objeto embarcaçao a partir dos dados do arquivo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return embarcacoes;//retorna a lista ATUALIZADA de embarcacoes com todos os seus objetos ja criados
    }

}
