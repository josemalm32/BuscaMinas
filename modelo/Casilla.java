package modelo;

public class Casilla extends Coordenada {
	private boolean velada=false;
	private boolean marcada=false;
	private boolean tieneMina = false;
	private int numero = 0;
 	public Casilla(int x, int y) {
 		super(x, y);
	}
 		
	public boolean isTieneMina() {
		return tieneMina;
	}
	public void setTieneMina(boolean tieneMina) {
		this.tieneMina = tieneMina;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public boolean isVelada() {
		return velada;
	}
	public void setVelada(boolean velada) {
		this.velada = velada;
	}
	public boolean isMarcada() {
		return marcada;
	}
	public void setMarcada(boolean marcada) {
		this.marcada = marcada;
	}
	
}
