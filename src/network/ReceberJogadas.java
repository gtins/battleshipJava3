package network;

import jogo.Cordenada;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ReceberJogadas extends Thread {
    private int portaServidor;
    private LinkedList<Cordenada> cordenadas; //monta a lista do tipo cordenada

    public ReceberJogadas(int portaServidor, LinkedList<Cordenada> cordenadas) {
        this.portaServidor = portaServidor; // cria a portaServidor
        this.cordenadas = cordenadas; // cria a cordenada
    }
    public void run() {
        while( true )
           ficarRecebendo();
    }

    private void ficarRecebendo() {
        try {
            // Criar o socket do servidor
            ServerSocket servidorSocket = new ServerSocket(portaServidor); //cria a porta do servidor

            System.out.println("Servidor aguardando conexões..."); //messagem de espera pelo outra conexao

            // Aguardar por conexões de clientes
            Socket clienteSocket = servidorSocket.accept(); //isso que faz ele aguardar a conexão

            System.out.println("Cliente conectado!");

            // Obter o fluxo de entrada do socket
            InputStream inputStream = clienteSocket.getInputStream(); //configuro os imputs de stream para serem as portas
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Ler o comando recebido do cliente
            String comando = reader.readLine();
            System.out.println("Comando recebido: " + comando);

            // Processar o comando (extrair linha e coluna)
            String[] partes = comando.split(",");
            if (partes.length == 2) {
                int linha = Integer.parseInt(partes[0]);
                int coluna = Integer.parseInt(partes[1]);

                System.out.println("Linha: " + linha); //mostra o valor que foi adicionado
                System.out.println("Coluna: " + coluna);//mostra o valor que foi adicionado

                cordenadas.add(new Cordenada(linha, coluna));//adiciona cordenadas
            }
            // Fechar a conexão
            clienteSocket.close();
            servidorSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
