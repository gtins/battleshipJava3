import java.net.CookieHandler;
import java.util.HashMap;
import java.util.LinkedList;

public class Embarcacao {
    private String tipo; //parametro
    private HashMap<Integer,Cordenada> posicoes = new HashMap<>(); //criaçao de um objeto do tipo hashmap de nome posicoes, novo hashmap
    private int forca; //parametro

    public Embarcacao(int x, int y, int tamanho , String tipo, boolean horizontal) { // construtor de ibjetos embarcaçao
        //recebe os parametros de cordanadam, tamanho, tipo e orientaçao
        this.forca = tamanho; //decisao inteligente, o tipo é igual ao tamanho ainfal é o numero de celulas
        this.tipo = tipo; //tipo recebido vira o tipo
        posicionar(x, y, tamanho, horizontal);
    }

    private void posicionar(int x, int y, int tamanho, boolean horizontal) { //precisa de x, y tamanho e orientaçao
        if(horizontal) {
            for (int i = 0; i < tamanho; i++) {
                Cordenada cordenada = new Cordenada(x + i, y, tipo); //objeto do tipo cordenada de nome cordenada usa do construtor da cordenada IR PARA CORDENADA PARA ENTENDER
                //IMPORTANTE, incremento no X afinal horizontal = eixo X
                posicoes.put(cordenada.hashCode(), cordenada); //coloca dentro do objeto hashmap posicoes o hashcode do objeto cordenada recem criado como chave e a cordenada como valor.
                //importante, o hashcode é a chave e o X e Y são os valores. ou seja um valor aponta pra um par ordenado
            }
        } else {
            for (int i = 0; i < tamanho; i++) {
                Cordenada cordenada = new Cordenada(x, y + i); //mesma coisa só que com a logica inversa para a orientação inversa
                //incremento no y afinal vertical = eixo y
                posicoes.put(cordenada.hashCode(),cordenada);
            }
        }
    }

    @Override
    public String toString() { //retorna o tipo declarado no metodo construtor como uma string que LITERALMENTE escreve o tipo
        return tipo;
    }

    public String getPosicoes() {
        StringBuilder string = new StringBuilder();//novo objeto string builder
        string.append(" ("); //coisa pra printar bonitinho
        for (Cordenada elemento: posicoes.values() //tudo daqui pra baixo é um foreach
        ) { //para cada valor em posicoes...
            string.append(" x: ");// excreva x:
            string.append(elemento.getX());//pegue o x
            string.append(" y:");//escreva y:
            string.append(elemento.getY());//pegue o y
        }
        string.append(" )");//coisa pra printar bonitinho
        return string.toString();//tudo que tava ali em cima tava dando append no novo objeto string builder criado
        //então agora ele tem tudo aquilo
    }

    public int atirar(int x, int y) {//metodo atirar recebe um valor x e y
        boolean acertou =  acertou(x, y); //vai pro metodo acertar
        if( acertou ) {//se acertou foi true
            diminuirForca(); //metodo de diminuir a vida logo abaixo, bem simples
        }
        return forca;
        //VOLTA PRO MAIN
    }

    /**
     * retorna a força pós processamento, ou seja, reduzida
     */
    private void diminuirForca() {
        forca--;
    }

    /**
     * Retorna TRUE se dentro do objeto hashmap posicoes existe a CHAVE igual ao hashcode de TIRO
     * tiro é um objeto cordenada, que recebe o x e y do metodo que o invoca (acertou) e passa pelo construtor padrao de cordenada
     * a logica de como a chave é adicionada em posicoes da em posicionar.
     * basicamente compara, se forem os mesmos x e y isso aqui da true, se não não da
     * retorna false se não tiver
     * VOLTA PARA ATIRAR
     * @param x como cordenada do array (linha)
     * @param y como cordenada do array (coluna)
     * @return true quando acertou
     */
    public boolean acertou(int x, int y) {//método acertar recebe valores x e y
        Cordenada tiro = new Cordenada(x, y);//objeto do tipo cordenada de nome tipo, nova cordenada (com x e y do parametro acertou)
        int chave = tiro.hashCode();
        boolean acertou = posicoes.containsKey(chave);
        if( acertou ) {
            Cordenada elemento = posicoes.get(chave);
            elemento.acertou();
        }
        return acertou;
    }

    public int getForca() {//getter da força
        return this.forca;
    }
}