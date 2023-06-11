package sunadventure.modelos;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import sunadventure.songs.EfeitosSonoros;

public class Tiro {

	private Image Imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel; // pra quando o tiro sair da tela ou tocar no inimigo, sumir

	private static final int LARGURA = 938;
	private static int VELOCIDADE = 3;

	public Tiro(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
		somTiroSimples();

	}

	public void load() {
		ImageIcon referencia = new ImageIcon("res\\TiroSimples.png");
		Imagem = referencia.getImage();

		this.altura = Imagem.getHeight(null);
		this.largura = Imagem.getWidth(null);

	}

	public void update() {
		this.x += VELOCIDADE;
		if (this.x > LARGURA) {
			isVisivel = false; // Tiro some quando passa da largura da tela
		}

	}

	public void somTiroSimples() {
		EfeitosSonoros a = new EfeitosSonoros();
		a.tocarTiro();
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return Imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

}
