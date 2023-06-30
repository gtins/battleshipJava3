import jogo.Cordenada;
import jogo.Embarcacao;

import java.util.LinkedList;

public class Main {

    private static LinkedList<Embarcacao> embarcacoes;// objeto linkedlist do tipo embarcacao chamado de embarcacoes
    private static LinkedList<Cordenada> jogadas = new LinkedList<>();//linkedlist do tipo cordenada e de nome jogadas, nova linkedlist

    public static void main(String[] args) {
        embarcacoes = criar();//ta embaixo
        mostrarNavios();//ta embaixo
        jogadas();//ta embaixo
        jogar();//ta embaixo


    }

    private static void jogar() {
        for( Cordenada cordenada : jogadas) { //vai varrer o jogadas
            atirarBarco(cordenada);//cada jogada é um tiro
            mostrarNavios();//imprime os navios
        }
    }

    private static void jogadas() {//adiciona na linkedlist novos objetos do tipo cordenada, que por serem do construtor padrão tem x e y.
        jogadas.add( new Cordenada(1,1));
        jogadas.add( new Cordenada( 2, 1));
        jogadas.add( new Cordenada(6,3));
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

    private static LinkedList<Embarcacao> criar() {
        LinkedList<Embarcacao> embarcacoes = new LinkedList<>(); // objeto do tipo linked list de nome embarcacoes, nova linked list
        embarcacoes.add( new Embarcacao(1,1,5, "N",true) ); // adiciona na lista criada um novo objeto embarcaçao // DAQUI VAI PRA CLASSE EMBARCACAO PRA ENTENDER
        embarcacoes.add( new Embarcacao(2,1,5, "N",false) );
        embarcacoes.add( new Embarcacao(3,3,2, "s", true) );
        return embarcacoes;//retorna a lista ATUALIZADA de embarcacoes com todos os seus objetos ja criados
    }
}