package network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class EnviarJogadaServidor {
    private String enderecoServidor;
    private int portaServidor;

    public EnviarJogadaServidor(String enderecoServidor, int portaServidor ) {
        this.enderecoServidor = enderecoServidor;
        this.portaServidor = portaServidor;
    }
    public void executar(int linha, int coluna) {
        try {
            // Criar o socket e se conectar ao servidor
            Socket clienteSocket = new Socket(enderecoServidor, portaServidor);

            // Obter o fluxo de saída do socket
            OutputStream outputStream = clienteSocket.getOutputStream();

            // Formatar o comando com linha e coluna como string
            String comando = linha + "," + coluna;

            // Enviar o comando ao servidor
            outputStream.write(comando.getBytes());
            outputStream.flush();

            // Fechar a conexão
            clienteSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
