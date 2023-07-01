package network;

import jogo.Cordenada;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ReceberJogadas extends Thread {
    private int portaServidor;
    private LinkedList<Cordenada> cordenadas;

    public ReceberJogadas(int portaServidor, LinkedList<Cordenada> cordenadas) {
        this.portaServidor = portaServidor;
        this.cordenadas = cordenadas;
    }
    public void run() {
        while( true )
           ficarRecebendo();
    }

    private void ficarRecebendo() {
        try {
            // Criar o socket do servidor
            ServerSocket servidorSocket = new ServerSocket(portaServidor);

            System.out.println("Servidor aguardando conexões...");

            // Aguardar por conexões de clientes
            Socket clienteSocket = servidorSocket.accept();

            System.out.println("Cliente conectado!");

            // Obter o fluxo de entrada do socket
            InputStream inputStream = clienteSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Ler o comando recebido do cliente
            String comando = reader.readLine();
            System.out.println("Comando recebido: " + comando);

            // Processar o comando (extrair linha e coluna)
            String[] partes = comando.split(",");
            if (partes.length == 2) {
                int linha = Integer.parseInt(partes[0]);
                int coluna = Integer.parseInt(partes[1]);

                // Faça algo com os valores da linha e coluna recebidos
                System.out.println("Linha: " + linha);
                System.out.println("Coluna: " + coluna);

                cordenadas.add(new Cordenada(linha,coluna));
            }

            // Fechar a conexão
            clienteSocket.close();
            servidorSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
