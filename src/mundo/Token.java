/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quindío (Armenia - Colombia)
 * Programa de Ingeniería de Sistemas y Computación
 *
 * Asignatura: Teoría de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Autor: Leonardo A. Hernández R. - Agosto 2008, sep 2013.
 * Autor:
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;

/**
 * Clase que modela un token
 * 
 * @author Andrea Raigoza
 * @author Miguel Gaona
 * @author Julian Garcia
 */

public class Token {
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------
	/**
	 * Constantes para modelar los posibles tipos de token que se van a analizar
	 */
	final public static String BINARIO = "binario";
	final public static String CARACTERESPECIAL = "Identifica u caracter especial";
	final public static String CIERREBLOQUE = "Cerrar un bloque de codigo";
	final public static String COMENTARIOLINEA = "Comentario de linea";
	final public static String CONDICIONALES = "Condicionales";
	final public static String CONTROL = "Estructura de control";
	final public static String ENTERO = "entero";
	final public static String HEXADECIMAL = "hexadecimal";
	final public static String IDENTIFICADOR = "Identificador";
	final public static String IDENTIFICADORCLASE = "Identificador clase";
	final public static String IDENTIFICADORCONSTRUCTOR = "Constructor";
	final public static String IDENTIFICADORDEVARIABLEGLOBAL = "variable Global";
	final public static String IDENTIFICADORDEVARIABLEINSTANCIA = "variable de instancia";
	final public static String IDENTIFICADORDEVARIABLELOCAL = "variable local";
	final public static String IDENTIFICADORIMPRESION = "Metodo immpresion";
	final public static String IDENTIFICADORMETODO = "Identificador Metodo";
	final public static String IDENTIFICADORVISIBILIDAD = "Visibilidad";
	final public static String INCREMENTO = "Identificador de incremento";
	final public static String INSTANCIADORDEOBJETOS = "Instanciador de objetos";
	final public static String NORECONOCIDO = "No reconocido";
	final public static String OPERADORADITIVO = "Operador aditivo";
	final public static String OPERADORARITMETICO = "Operador Aritmetico";
	final public static String OPERADORASIGNACION = "Operador de asignación";
	final public static String OPERADORLOGICO = "Operador Lógico";
	final public static String OPERADORRELACIONAL = "Operador relacional";
	final public static String PALABRARESERVADA = "Palabra reservada";
	final public static String REAL = "Real";
	final public static String VALORNULIDAD = "Valor nulidad";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	/**
	 * Lexema
	 */
	private String lexema;

	/**
	 * tipo
	 */
	private String tipo;

	/**
	 * posición del siguiente lexema
	 */
	private int indiceSiguiente;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------
	/**
	 * Constructor de un token
	 * 
	 * @param elLexema
	 *            - cadena - laCadena != null
	 * @param elTipo
	 *            - tipo del token - elTipo != null
	 * @param elIndiceSiguiente
	 *            - posición del siguiente token - laPosicionSiguiente > 0
	 */
	public Token(String elLexema, String elTipo, int elIndiceSiguiente) {
		lexema = elLexema;
		tipo = elTipo;
		indiceSiguiente = elIndiceSiguiente;

	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Entrega la información del token
	 * 
	 * @return Descripción del token
	 */
	public String darDescripcion() {
		return "Token: " + lexema + "     Tipo: " + tipo
				+ "     Índice del siguiente: " + indiceSiguiente;
	}

	/**
	 * Método que retorna el lexema del token
	 * 
	 * @return el lexema del token
	 */
	public String darLexema() {
		return lexema;
	}

	/**
	 * Método que retorna la posición del siguiente lexema
	 * 
	 * @return posición del siguiente token
	 */
	public int darIndiceSiguiente() {
		return indiceSiguiente;
	}

	/**
	 * Método que retorna el tipo del token
	 * 
	 * @return el tipo del token
	 */
	public String darTipo() {
		return tipo;
	}

}
