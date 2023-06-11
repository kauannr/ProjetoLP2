package sunadventure.container;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;

import sunadventure.modelos.FaseP1;

public class Container_SunAdventure extends JFrame {

	private static ServerSocket serverSocket;

	public Container_SunAdventure() throws IOException {

		// Nova fase para o player
		serverSocket = new ServerSocket(12345);
		System.out.println("Espaço preparado, aguardando a nave se conectar ao servidor...");

		new Thread(() -> {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("Nave e player conectados!\n");

				add(new FaseP1());
				setTitle("Space War"); // Nome do jogo, fica no canto superior esquerdo.
				setSize(1024, 768); // Dimensões de largura e altura
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fechar quando clicar no X
				setLocationRelativeTo(null);
				this.setResizable(false);
				setVisible(true);
				/*
				 * Aqui é só p n aparecer a opçao de tela cheia, pq pode dar problema
				 * dependendo
				 * do tamanho da tela
				 */

			} catch (IOException e) {
				System.out.println("Erro ao tentar conexão, tente novamente");
			}
		}).start();

	}

	public static void main(String[] args) throws IOException {

		new Container_SunAdventure(); // Chamando a tela do jogo

		Socket socket = new Socket("localhost", 12345);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

}