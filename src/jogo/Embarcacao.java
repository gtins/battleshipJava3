package jogo;
import java.net.CookieHandler;
import java.util.HashMap;
import java.util.LinkedList;

public class Embarcacao extends Elemento {
    private String tipo; //parametro
    private HashMap<Integer, Cordenada> posicoes = new HashMap<>(); //criaçao de um objeto do tipo hashmap de nome posicoes, novo hashmap
    private int forca; //parametro

    public Embarcacao(int x, int y, int tamanho , String tipo, boolean horizontal) { // construtor de objetos embarcaçao
        //recebe os parametros de cordanadam, tamanho, tipo e orientaçao
        this.forca = tamanho; //decisao inteligente, o tipo é igual ao tamanho afinal é o numero de celulas
        this.tipo = tipo; //tipo recebido vira o tipo
        posicionar(x, y, tamanho, horizontal); //metodo abaixo
    }

    private void posicionar(int x, int y, int tamanho, boolean horizontal) { //precisa de x, y tamanho e orientaçao
        if(! horizontal) {
            for (int i = 0; i < tamanho; i++) {
                Cordenada cordenada = new Cordenada(x + i, y, tipo); //objeto do tipo cordenada de nome cordenada usa do construtor da cordenada
                //IMPORTANTE, incremento no X afinal horizontal = eixo X
                posicoes.put(cordenada.hashCode(), cordenada); //coloca dentro do objeto hashmap posicoes o hashcode do objeto cordenada recem criado como chave e a cordenada como valor.
                //importante, o hashcode é a chave e o X e Y são os valores. ou seja um valor aponta pra um par ordenado
            }
        } else {
            for (int i = 0; i < tamanho; i++) {
                Cordenada cordenada = new Cordenada(x, y + i,tipo); //mesma coisa só que com a logica inversa para a orientação inversa
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
        string.append(" ("); //identação
        for (Cordenada elemento: posicoes.values() //tudo daqui pra baixo é um foreach
        ) { //para cada valor em posicoes...
            string.append(" x: ");// excreva x:
            string.append(elemento.getX());//pegue o x
            string.append(" y:");//escreva y:
            string.append(elemento.getY());//pegue o y
        }
        string.append(" )");//identação 
        return string.toString();//tudo que estava ali em cima estava dando append no novo objeto string builder criado
        //então agora ele possui tudo aquilo
    }

    public int atirar(int x, int y) {//metodo atirar recebe um valor x e y
        boolean acertou =  acertou(x, y); //vai pro metodo acertar
        if( acertou ) {//se acertou foi true
            diminuirForca(); //metodo de diminuir a vida (logo abaixo)
        }
        return forca;//retorna força atualizada
    }

    private void diminuirForca() {//diinui a vida do navio
        forca--;//decremento na força
    }

    public boolean acertou(int x, int y) {//método acertar recebe valores x e y
        Cordenada tiro = new Cordenada(x, y);//objeto do tipo cordenada de nome tipo, nova cordenada (com x e y do parametro acertou)
        int chave = tiro.hashCode(); //chave recebe o hashcode de tiro
        boolean acertou = posicoes.containsKey(chave); //acertou verifica se em posicoes existe a chave, boleano true ou false 
        if( acertou ) {//se acertou for true
            Cordenada elemento = posicoes.get(chave);//elemento busca a chave
            elemento.acertou();//executa o método acertou
        }
        return acertou;
    }

    public boolean temPosicao(int x, int y) {//método acertar recebe valores x e y
        Cordenada tiro = new Cordenada(x, y);//objeto do tipo cordenada de nome tipo, nova cordenada (com x e y do parametro acertou)
        int chave = tiro.hashCode(); //chave recebe o hashcode de tiro
        boolean tem = posicoes.containsKey(chave); //tem verifica se em posicoes existe a chave, boleano true ou false
        return tem;
    }

    public Cordenada getCordenada(int x, int y) {//método acertar recebe valores x e y
        Cordenada pos = new Cordenada(x, y);//objeto do tipo cordenada de nome tipo, nova cordenada (com x e y do parametro acertou)
        int chave = pos.hashCode(); //chave recebe o hashcode de pos
        return  posicoes.get(chave); //busca a chave de pos e retorna
    }

    public int getForca() {//getter da força
        return this.forca;
    }

    public boolean afundou() { //boleano que indica se afundou e zera a força
        return forca == 0;
    }
}

