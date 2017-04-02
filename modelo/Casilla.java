package modelo;

public class Casilla {
	private boolean velada=true;
	private boolean marcada=false;
	
	public boolean isVelada() {
		return velada;
	}
	private void setVelada(boolean velada) {
		this.velada = velada;
	}
	public boolean isMarcada() {
		return marcada;
	}
	public void setMarcada(boolean marcada) {
		this.marcada = marcada;
	}
	
}
