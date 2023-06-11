package sunadventure.modelos;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import sunadventure.songs.EfeitosSonoros;

public class Player extends Nave implements ActionListener {

	private int x, y; // Servem como vetor, pra mover quando apertar o botão
	private int dx, dy;
	private Image imagem;
	private List<Tiro> tiros;
	private List<Missel> misseis;
	private int altura, largura;
	private Timer timer;
	private boolean turbo, isVisivel;
	private int vida, turboLiberar, misselLiberar;
	private double tempo = 0;

	// Construtor
	public Player() {
		this.x = 100; // Coordenadas que o player vai aparecer
		this.y = 100;

		vida = 6;
		turboLiberar = 3;
		misselLiberar = 10;
		timer = new Timer(7000, this);
		timer.start();

		tiros = new ArrayList<Tiro>();
		misseis = new ArrayList<Missel>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (turbo == true) {
			ImageIcon referencia = new ImageIcon("res\\spaceship2.png");
			imagem = referencia.getImage();
			turbo = false;

		}
	}

	// Imagem da nave
	public void load() {
		ImageIcon referencia = new ImageIcon("res\\spaceship2.png");
		imagem = referencia.getImage();

		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);

	}

	// Movimento da nave
	public void update() {

		x += dx;
		y += dy;

		// os if são para a nave não se perder fora da tela de jogo
		if (this.x < 6) {
			x = 6;
		}

		if (this.x > 938) {
			x = 938;
		}

		if (this.y < 65) {
			y = 65;
		}
		if (this.y > 694) {
			y = 694;
		}

		setImagemvida();

	}

	// Tiros
	public void tirosSimples() {
		this.tiros.add(new Tiro(x + largura, y + altura / 2));

	}

	public void Misseis() {
		if (misselLiberar > 0) {
			this.misseis.add(new Missel(x + largura, y + altura / 2));
		}

	}

	public void turbo() {

	}

	// Imagem dano
	public void setImagemvida() {
		if (vida == 1) {
			ImageIcon referencia = new ImageIcon("res\\spaceship2Hitmed.png");
			imagem = referencia.getImage();

		}

		if (vida == 0) {
			ImageIcon referencia = new ImageIcon("res\\spaceship2Hit.png");
			imagem = referencia.getImage();
		}

	}

	// Getters e Setters

	public boolean isTurbo() {
		return turbo;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getMisselLiberar() {
		return misselLiberar;
	}

	public void setMisselLiberar(int misselLiberar) {
		this.misselLiberar = misselLiberar;
	}

	public int getTurboLiberar() {
		return turboLiberar;
	}

	public void setTurboLiberar(int turboLiberar) {
		this.turboLiberar = turboLiberar;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public int getX() {
		return x;
	}

	public List<Missel> getMisseis() {
		return misseis;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	// Colisao

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	// Teclado
	public void keyPressed(KeyEvent tecla) {

		int codigo = tecla.getKeyCode(); // aqui to dizendo que quando apertar alguma tecla, o sistema vai reconhecer e
											// fazer alguma ação

		if (codigo == KeyEvent.VK_SPACE) {

			turbo();

		}

		if (codigo == KeyEvent.VK_A) {

			tirosSimples();

		}

		if (codigo == KeyEvent.VK_S) {

			if (misselLiberar > 0) {
				Misseis();
				misselLiberar--;
			}

		}

		// Se os eventos do teclado for, cima, baixo, direita ou esquerda, a nave se
		// move 3 posições no vetor
		if (codigo == KeyEvent.VK_UP) {
			dy = -3;
		}

		if (codigo == KeyEvent.VK_DOWN) {
			dy = 3;
		}

		if (codigo == KeyEvent.VK_LEFT) {
			dx = -3;
		}

		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 3;
		}

	}

	public void keyReleased(KeyEvent tecla) {
		// Quando parar de pressionar a tecla, o x e y volta a ser 0 dnv
		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_DOWN) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

	}

}
