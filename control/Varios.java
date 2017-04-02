package control;

import javax.swing.JButton;

import modelo.Coordenada;

public class Varios {

	public Coordenada obtenerCoordenada(JButton boton){
		return new Coordenada(Integer.valueOf(Character.toString(boton.getName().charAt(0))),Integer.valueOf(Character.toString(boton.getName().charAt(1))));
	}
}
