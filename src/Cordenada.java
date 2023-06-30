public class Cordenada {
    private int x;
    private int y;
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cordenada(int x, int y) { //construtor padrao da cordenada, recebe X e Y e define que o objeto cordenada tem esses dois valores respectivamente
        //volta para embarcacoes pra entender
        this.x = x;
        this.y = y;
    }

    public Cordenada(int x, int y, String tipo) { //construtor padrao da cordenada, recebe X e Y e define que o objeto cordenada tem esses dois valores respectivamente
        //volta para embarcacoes pra entender
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cordenada cordenada)) return false;

        if (getX() != cordenada.getX()) return false;
        return getY() == cordenada.getY();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }

    @Override
    public String toString() {
        return "Cordenada{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void acertou() {
        this.tipo = "X";
    }
}