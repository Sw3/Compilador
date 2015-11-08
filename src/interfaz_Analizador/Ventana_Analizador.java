package interfaz_Analizador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;

import mundo.AnalizadorLexico;
import mundo.AnalizadorSintactico;
import mundo.Token;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class Ventana_Analizador extends javax.swing.JFrame implements
		ActionListener {
	public AnalizadorLexico analizadorLexico;
	private JTabbedPane jTabbedPane1;
	private JTextArea jTCodigoFuente;
	private JLabel jLBAtoma;
	private JComboBox jCBAtomata;
	private JLabel jLImagen;
	private JLabel jLbr;
	private JLabel jLcentro;
	private JPanel jPanelVentPrincipal;
	private JTable jTableListaErrores;
	private JScrollPane jSPListaErrores;
	private JTable jTableListaToken;
	private JScrollPane jScrollPaneListaTokens;
	private JLabel jLAutomata;
	private JButton jBAnalizar;
	private JLabel jLListaErrores;
	private JLabel jLListaToken;
	private JPanel jPPrincipal;
	private JScrollPane jSPCodigoFuente;
	private JLabel jLCodigoFuente;
	private JPanel jPAnalizadorLexico;
	private PanelFondo fondoPrincipal;
	private PanelFondo fondoLexico;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Ventana_Analizador inst = new Ventana_Analizador();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});

	}

	public Ventana_Analizador() {
		super("Analizador Léxico RUBY");

		// instancia el analizador

		analizadorLexico = new AnalizadorLexico();

		fondoLexico = new PanelFondo();
		{
			jSPListaErrores = new JScrollPane();
			fondoLexico.add(jSPListaErrores);
			jSPListaErrores.setBounds(618, 76, 243, 275);
			{
				TableModel jTableListaErroresModel = new DefaultTableModel(
						new String[][] {}, new String[] { "Lexema", "Tipo" });
				jTableListaErrores = new JTable();
				jSPListaErrores.setViewportView(jTableListaErrores);
				jTableListaErrores.setModel(jTableListaErroresModel);
			}
		}
		{
			jScrollPaneListaTokens = new JScrollPane();
			fondoLexico.add(jScrollPaneListaTokens);
			jScrollPaneListaTokens.setBounds(319, 76, 242, 275);
			{
				TableModel jTableListaTokenModel = new DefaultTableModel(
						new String[][] {}, new String[] { "Lexema", "Tipo" });
				jTableListaToken = new JTable();
				jScrollPaneListaTokens.setViewportView(jTableListaToken);
				jTableListaToken.setModel(jTableListaTokenModel);
			}
		}
		{
			jBAnalizar = new JButton();
			fondoLexico.add(jBAnalizar);
			jBAnalizar.setText("Analizar");
			jBAnalizar.setBounds(358, 392, 112, 38);
			jBAnalizar.addActionListener(this);
		}
		{
			jLListaErrores = new JLabel();
			fondoLexico.add(jLListaErrores);
			jLListaErrores.setText("Lista Errores");
			jLListaErrores.setBounds(618, 40, 165, 16);
			jLListaErrores.setForeground(new java.awt.Color(255, 255, 255));
			jLListaErrores.setFont(new java.awt.Font("Segoe UI", 1, 12));
		}
		{
			jLListaToken = new JLabel();
			fondoLexico.add(jLListaToken);
			jLListaToken.setText("Lista Token");
			jLListaToken.setBounds(324, 32, 163, 16);
			jLListaToken.setForeground(new java.awt.Color(255, 255, 255));
			jLListaToken.setFont(new java.awt.Font("Segoe UI", 1, 12));
		}
		{
			jSPCodigoFuente = new JScrollPane();
			fondoLexico.add(jSPCodigoFuente);
			jSPCodigoFuente.setBounds(30, 86, 234, 253);
			jSPCodigoFuente.setVisible(false);
		}
		{
			jLCodigoFuente = new JLabel();
			fondoLexico.add(jLCodigoFuente);
			jLCodigoFuente.setText("Código Fuente");
			jLCodigoFuente.setBounds(34, 31, 200, 19);
			jLCodigoFuente.setFont(new java.awt.Font("Segoe UI", 1, 12));
			jLCodigoFuente.setForeground(new java.awt.Color(255, 255, 255));
		}
		{
			jTCodigoFuente = new JTextArea();
			fondoLexico.add(jTCodigoFuente);
			jTCodigoFuente.setBounds(30, 70, 234, 281);
		}
		{
			jLbr = new JLabel();
			fondoLexico.add(jLbr);
			jLbr.setBounds(10, 379, 336, 114);
			jLbr.setIcon(new ImageIcon(getClass().getClassLoader().getResource(
					"imagenes/ruby1.png")));
		}

		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setIconImage(new ImageIcon(getClass().getClassLoader()
					.getResource("imagenes/Ruby4.png")).getImage());
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1);
				jTabbedPane1.setBounds(0, 0, 849, 463);
				jTabbedPane1.setSize(900, 562);
				{
					jPPrincipal = new JPanel();
					{
						fondoPrincipal = new PanelFondo();
						jPPrincipal.add(fondoPrincipal);
					}
					{
						jLAutomata = new JLabel();
						jPPrincipal.add(jLAutomata);
						jLAutomata.setText("Autómata");
						jLAutomata.setBounds(609, 146, 94, 16);
						jLAutomata.setForeground(new java.awt.Color(255, 255,
								255));
						jLAutomata
								.setFont(new java.awt.Font("Segoe UI", 1, 12));
					}
					fondoPrincipal.setBounds(0, 0, 900, 562);
					fondoPrincipal.setLayout(null);
					{
						ComboBoxModel jCBAtomataModel = new DefaultComboBoxModel(
								new String[] { " ", "Cierre de Bloque",
										"Comentario de linea",
										"Estructura de control case",
										"Estructura de control each",
										"Estructura de control else",
										"Estructura de control elsif",
										"Estructura de control for",
										"Estructura de control if",
										"Estructura de control in",
										"Estructura de control then",
										"Estructura de control unless",
										"Estructura de control when",
										"Estructura de control while",
										"Identificador de metodo",
										"Intanciador de objetos",
										"Metodo constructor",
										"Metodo imprimir",
										"Metodos de impresion",
										"Operador de asignación",
										"Operador logico AND",
										"Operador logico NOT",
										"Operador logico OR",
										"Palabra reservada break",
										"Palabra reservada class", "String",
										"Valor de nulidad",
										"Variable de instancia",
										"Variable global", "variable local",
										"visibilidad" });
						jCBAtomata = new JComboBox();
						fondoPrincipal.add(jCBAtomata);
						jCBAtomata.setModel(jCBAtomataModel);
						jCBAtomata.setBounds(18, 103, 241, 23);
						jCBAtomata.addActionListener(this);
					}
					{
						jLBAtoma = new JLabel();
						fondoPrincipal.add(jLBAtoma);
						jLBAtoma.setText("Autómata");
						jLBAtoma.setBounds(52, 70, 140, 16);
						jLBAtoma.setForeground(new java.awt.Color(255, 255, 255));
					}
					{
						jLcentro = new JLabel();
						fondoPrincipal.add(jLcentro);
						jLcentro.setBounds(18, 381, 360, 126);
						jLcentro.setIcon(new ImageIcon(getClass()
								.getClassLoader().getResource(
										"imagenes/ruby1.png")));
					}
					{
						jLImagen = new JLabel();
						fondoPrincipal.add(jLImagen);
						jLImagen.setBounds(271, 23, 603, 400);
						jLImagen.setSize(600, 400);
					}
					jTabbedPane1.addTab("Ventana Principal", null, jPPrincipal,
							null);
					jPPrincipal.setLayout(null);
					jPPrincipal.setPreferredSize(new java.awt.Dimension(736,
							358));
					jPPrincipal.setSize(900, 562);

				}
				{
					jPAnalizadorLexico = new JPanel();
					jPAnalizadorLexico.add(fondoLexico);
					fondoLexico.setBounds(0, 0, 900, 562);
					fondoLexico.setLayout(null);
					jTabbedPane1.addTab("Analizador Lexico", null,
							jPAnalizadorLexico, null);
					jPAnalizadorLexico.setLayout(null);
					jPAnalizadorLexico.setPreferredSize(new java.awt.Dimension(
							844, 465));
					jPAnalizadorLexico.setSize(900, 562);
				}
			}
			pack();
			this.setSize(900, 562);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jBAnalizar) {

			if (!jTCodigoFuente.getText().isEmpty()) {
				// llenarTablaTokens(crearLista(jTCodigoFuente.getText()));

				ArrayList vectorTokens;
				vectorTokens = analizadorLexico.extraerTokens("");
				vectorTokens = analizadorLexico.extraerTokens(jTCodigoFuente
						.getText());
				ArrayList vectorTokensEditados = new ArrayList();
				Token token;
				ArrayList<String> reconocidos = new ArrayList<String>();
				ArrayList<String> noReconocidos = new ArrayList<String>();

				for (int i = 0; i < vectorTokens.size(); i++) {
					token = (Token) vectorTokens.get(i);
					// vectorTokensEditados.add(token.darDescripcion());
					if (token.darTipo().equals("No reconocido")) {
						if (!(token.darLexema().equals(" ") || token.darLexema().equals("\n"))) {
							noReconocidos.add(token.darLexema());
							noReconocidos.add(token.darTipo());
						}

					} else {
						reconocidos.add(token.darLexema());
						reconocidos.add(token.darTipo());
					}

				}

				llenarTablaTokens(reconocidos, noReconocidos);
				AnalizadorSintactico sintactico = new AnalizadorSintactico(reconocidos);

			}

		}
		// aca va el codigo de la simagenes

		if (jCBAtomata.getSelectedIndex() != 0) {

			String path = "/imagenes/"
					+ jCBAtomata.getSelectedItem().toString() + ".jpg";
			URL url = this.getClass().getResource(path);
			ImageIcon icon = new ImageIcon(url);
			jLImagen.setIcon(icon);
		}

	}

	/**
	 * Metodo que lista los tokens reconocidos
	 * 
	 * @param lista
	 *            , lista de palabras
	 */
	private void llenarTablaTokens(ArrayList<String> lista,
			ArrayList<String> listaEr) {

		DefaultTableModel modeloToken = (DefaultTableModel) jTableListaToken
				.getModel();
		DefaultTableModel modelError = (DefaultTableModel) jTableListaErrores
				.getModel();
		try {
			for (int j = 0; j <= modeloToken.getRowCount();) {
				modeloToken.removeRow(j);
			}

		} catch (ArrayIndexOutOfBoundsException ev) {

		}
		try {
			for (int j = 0; j <= modelError.getRowCount();) {
				modelError.removeRow(j);
			}

		} catch (ArrayIndexOutOfBoundsException ev) {

		}

		for (int i = 0; i < lista.size(); i += 2) {

			modeloToken.addRow(new String[] { lista.get(i), lista.get(i + 1) });

		}

		for (int i = 0; i < listaEr.size(); i += 2) {
			if (listaEr.size() > 0) {
				modelError.addRow(new String[] { listaEr.get(i),
						listaEr.get(i + 1) });
			}

		}

	}

}
