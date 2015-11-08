package interfaz_Analizador;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Clase PanelFondo. En esta clase se permite definir una imagen de fondo a la
 * ventana de clientes.
 * 
 * @author Paulo Cesar Alvis.
 * @author Miguel Angel Osorio.
 * @version 3.8 17/12/2013
 */
public class PanelFondo extends javax.swing.JPanel {

	/**
	 * Constante que le asigna eclipse a la clase para su identificación
	 */
	private static final long serialVersionUID = 1L;

	/** Constructor de la clase */
	public PanelFondo() {
		this.setSize(400, 280);
	}

	/**
	 * En este metodo, modificamos el fondo de la ventana para poder asignarle
	 * una imagen de fondo.
	 */
	@Override
	public void paintComponent(Graphics g) {

		Dimension tamanio = getSize();
		Image imagenFondo = new ImageIcon("\\workspace\\AnalizadorLexico\\src\\imagenes\\ruby2.jpg").getImage();
		g.drawImage(imagenFondo, 0, 0, tamanio.width,
				tamanio.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}