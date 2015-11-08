/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 * $Id$
 * Universidad del Quindío (Armenia - Colombia)
 * Programa de Ingeniería de Sistemas y Computación
 *
 * Asignatura: Teoría de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Autor: Leonardo A. Hernández R. - Agosto 2008 - Marzo 2009
 * Autor:
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;

import java.util.ArrayList;

/**
 * Clase que modela un analizador lexico
 * 
 * 
 * @author Andrea Raigoza
 * @author Miguel Gaona
 * @author Julian Garcia
 * 
 */
public class AnalizadorLexico {

	private static final String[] LISTAPALABRASRESERVADASRUBY = { "alias",
			"break", "case", "class", "defined?", "elsif", "ensure", "next",
			"nil", "redo", "rescue", "retry", "return", "self", "super",
			"then", "undef", "unless", "until", "when", "while", "yield",
			"_FILE_", "_LINE_" };

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Extrae los tokens de un código fuente dado
	 * 
	 * @param cod
	 *            - código al cual se le van a extraer los tokens - !=null
	 * @return vector con los tokens
	 */
	public ArrayList extraerTokens(String cod) {
		Token token;
		ArrayList vectorTokens = new ArrayList();

		// El primer token se extrae a partir de posición cero
		int i = 0;

		// Ciclo para extraer todos los tokens
		while (i < cod.length()) {
			// Extrae el token de la posición i
			token = extraerSiguienteToken(cod, i);

			vectorTokens.add(token);
			i = token.darIndiceSiguiente();
		}

		return vectorTokens;
	}

	/**
	 * Extrae el token de la cadena cod a partir de la posición i, basándose en
	 * el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a extraer un token - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a extraer el token - i>=0
	 * @return token que se extrajo de la cadena
	 */
	public Token extraerSiguienteToken(String cod, int i) {
		Token token;

		// Intenta extraer identificador de metodo
		token = extraerIdentificadorMetodo(cod, i);
		if (token != null)
			return token;

		// Intenta extraer operadores de incremento
		token = extraerIncremento(cod, i);
		if (token != null)
			return token;

		// Intenta extraer operadores aritmeticos
		token = extraerOperadorAritmetico(cod, i);
		if (token != null)
			return token;

		// Intenta extraer identificador de metodo
		token = extraerEstructuraControl(cod, i);
		if (token != null)
			return token;
		// Intenta extrae cierre de bloque
		token = extraerCierreDeBloque(cod, i);
		if (token != null)
			return token;

		// Intenta extaer visibilidad
		token = extraerVisibilidad(cod, i);
		if (token != null)
			return token;

		// Intenta extaer variable Global
		token = extraerIdentificadorVariableGlobal(cod, i);
		if (token != null)
			return token;

		// Intenta extaer variable local
		token = extraerIdentificadorVariableLocal(cod, i);
		if (token != null)
			return token;

		// Intenta extaer variable instanciaF
		token = extraerIdentificadorVariableInstancia(cod, i);
		if (token != null)
			return token;

		// Intenta extaer operador Logico AND
		token = extraerOperadorLogicoAND(cod, i);
		if (token != null)
			return token;

		// Intenta extaer operador Logico NOT
		token = extraerOperadorLogicoNOT(cod, i);
		if (token != null)
			return token;

		// Intenta extaer instanciador de objeros
		token = extraerIdentificadorInstanciadorDeObjetos(cod, i);
		if (token != null)
			return token;
		// Intenta extaer instanciador de objeros
		token = extraerValordeNulidad(cod, i);
		if (token != null)
			return token;

		// Intenta extaer Comentario de Linea
		token = extraerIdentificadorComentarioLinea(cod, i);
		if (token != null)
			return token;

		// Intenta extaer operador Logico OR
		token = extraerOperadorLogicoOR(cod, i);
		if (token != null)
			return token;

		// Intenta extraer Método imprimir
		token = extraerIdentificadorImprimir(cod, i);
		if (token != null)
			return token;

		// Intenta extraer relacional
		token = extraerRelacional(cod, i);
		if (token != null)
			return token;
		// Intenta extraer numero binario
		token = extraerBinario(cod, i);
		if (token != null)
			return token;
		// Intenta extraer numero hexadecimal
		token = extraerHexadecimal(cod, i);
		if (token != null)
			return token;

		// Intenta extraer entero
		token = extraerEntero(cod, i);
		if (token != null)
			return token;
		// Intenta extraer Real
		token = extraerReal(cod, i);
		if (token != null)
			return token;

		// Intenta extraer identificador de clase
		token = extraerIdentificadorClase(cod, i);
		if (token != null)
			return token;

		// Intenta extraer identificador de palbra reservada break
		token = extraerIdentificadorPalabraReservadaBreak(cod, i);
		if (token != null)
			return token;

		// Intenta extraer identificador de Palabra reservada class
		token = extraerIdentificadorPalabraReservadaClase(cod, i);
		if (token != null)
			return token;

		// Intenta extraer identificador de Palabra reservada module
		token = extraerIdentificadorPalabraReservadaModule(cod, i);
		if (token != null)
			return token;

		// Intenta extraer condicionales
		token = extraerCondicionales(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un operador de asignación
		token = extraerOperadorAsignacion(cod, i);
		if (token != null)
			return token;

		token = extraerEspecial(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un identificador
		// token = extraerIdentificador(cod, i);
		// if (token != null)
		// return token;

		// Extrae un token no reconocido
		token = extraerNoReconocido(cod, i);
		return token;
	}

	/**
	 * Intenta extraer un entero de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un entero -
	 *            codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            entero - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         entero. El Token se compone de el lexema, el tipo y la posición
	 *         del siguiente lexema.
	 */

	// Este método usa el método substring(), que se explica a continuación:
	// x.substring( i, j ) retorna una nueva cadena que es una subcadena de la
	// cadena x.
	// La subcadena comienza en la posición i y
	// se extiende hasta el carácter en la posición j-1.
	// Ejemplo: "universidad".substring(3,6) retorna "ver",

	public Token extraerEntero(String cod, int i) {
		int j = i;
		if (esDigito(cod.charAt(i))) {
			i++;
			try {
				while (esDigito(cod.charAt(i))) {
					i++;
				}
				String lex = cod.substring(j, i);
				Token token = new Token(lex, Token.ENTERO, i++);
				return token;
			} catch (IndexOutOfBoundsException ex) {
				String lex = cod.substring(j, i);
				Token token = new Token(lex, Token.ENTERO, i++);
				return token;
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un real de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un entero -
	 *            codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            real - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         entero. El Token se compone de el lexema, el tipo y la posición
	 *         del siguiente lexema.
	 */
	public Token extraerReal(String cod, int i) {
		int j = i;
		if (esDigito(cod.charAt(i))) {
			i++;
			try {
				while (esDigito(cod.charAt(i)) || cod.charAt(i) == '.') {
					i++;
					// if (cod.charAt(i) == '.') {
					// i++;
					if (esDigito(cod.charAt(i))) {
						i++;
						// }
					}
				}
				String lex = cod.substring(j, i);
				Token token = new Token(lex, Token.REAL, i++);
				return token;
			} catch (IndexOutOfBoundsException ex) {
				String lex = cod.substring(j, i);
				Token token = new Token(lex, Token.REAL, i++);
				return token;
			}
		}

		return null;
	}

	/**
	 * 
	 * 
	 * Intenta extraer un NUMERO HEXDECIMAL, de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un NUMERO
	 *            HEXADECIMAL - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            NUMERO HEXADECIMAL - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         NUMERO HEXADECIMAL. El Token se compone de el lexema, el tipo y
	 *         la posición del siguiente lexema.
	 */
	public Token extraerHexadecimal(String cod, int i) {
		int j = i;
		if (cod.charAt(i) == '0') {
			i++;
			if (cod.charAt(i) == 'h') {
				i++;
				try {
					while (cod.charAt(i) == 'A' || cod.charAt(i) == 'B'
							|| cod.charAt(i) == 'C' || cod.charAt(i) == 'D'
							|| cod.charAt(i) == 'E' || cod.charAt(i) == 'F'
							|| cod.charAt(i) == '1' || cod.charAt(i) == '2'
							|| cod.charAt(i) == '3' || cod.charAt(i) == '4'
							|| cod.charAt(i) == '5' || cod.charAt(i) == '6'
							|| cod.charAt(i) == '7' || cod.charAt(i) == '8'
							|| cod.charAt(i) == '9') {
						i++;
					}
					String lex = cod.substring(j, i);
					Token token = new Token(lex, Token.HEXADECIMAL, i++);
					return token;
				} catch (IndexOutOfBoundsException ex) {
					String lex = cod.substring(j, i);
					Token token = new Token(lex, Token.HEXADECIMAL, i++);
					return token;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * 
	 * Intenta extraer un NUMERO BINARIO, de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un NUMERO BINARIO
	 *            - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            NUMERO BINARIO - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         NUMERO BINARIO. El Token se compone de el lexema, el tipo y la
	 *         posición del siguiente lexema.
	 */
	public Token extraerBinario(String cod, int i) {
		int j = i;
		if (cod.charAt(i) == '0') {
			i++;
			if (cod.charAt(i) == 'b') {
				i++;
				try {
					while (cod.charAt(i) == '1' || cod.charAt(i) == '0') {
						i++;
					}
					String lex = cod.substring(j, i);
					Token token = new Token(lex, Token.BINARIO, i++);
					return token;
				} catch (IndexOutOfBoundsException ex) {
					String lex = cod.substring(j, i);
					Token token = new Token(lex, Token.BINARIO, i++);
					return token;
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un operador aritmetico de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer el operador
	 *            aditivo - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer el
	 *            operador aritmetico - 0<=i<codigo.length()
	 * @return el token operador aritmetico o NULL, si el token en la posición
	 *         dada no es un operador aritmetico.El Token se compone de el
	 *         lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerOperadorAritmetico(String cod, int i) {

		if (cod.charAt(i) == '+' || cod.charAt(i) == '-'
				|| cod.charAt(i) == '*' || cod.charAt(i) == '/') {
			int j = i + 1;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.OPERADORARITMETICO, j);
			return token;
		}
		return null;
	}

	/**
	 * Intenta extraer un caracter especial ([] ? () . <<) de la cadena cod a
	 * partir de la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un caracter
	 *            especial - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            caracter especial - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         entero. El Token se compone de el lexema, el tipo y la posición
	 *         del siguiente lexema.
	 */
	public Token extraerEspecial(String cod, int i) {
		int a = i;
		if (cod.charAt(i) == '[' || cod.charAt(i) == ']'
				|| cod.charAt(i) == '?' || cod.charAt(i) == '('
				|| cod.charAt(i) == ')' || cod.charAt(i) == '.') {
			int j = i + 1;
			String lex = cod.substring(a, j);
			Token token = new Token(lex, Token.CARACTERESPECIAL, j);
			return token;
		}
		if (cod.charAt(i) == '<') {
			i++;
			try {
				if (cod.charAt(i) == '<') {
					int j = i + 1;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.CARACTERESPECIAL, j);
					return token;
				}
			} catch (IndexOutOfBoundsException e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un operador de incremento de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un caracter
	 *            especial - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            operador de incremente el - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         entero. El Token se compone de el lexema, el tipo y la posición
	 *         del siguiente lexema.
	 */
	public Token extraerIncremento(String cod, int i) {
		int a = i;
		if (cod.charAt(i) == '+') {
			i++;
			try {
				if (cod.charAt(i) == '+') {
					int j = i + 1;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.INCREMENTO, j);
					return token;
				}
				if (cod.charAt(i) == '=') {
					int j = i + 1;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.INCREMENTO, j);
					return token;
				}
			} catch (IndexOutOfBoundsException e) {
				return null;
			}
		}
		if (cod.charAt(i) == '-') {
			i++;
			try {
				if (cod.charAt(i) == '-') {
					int j = i + 1;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.INCREMENTO, j);
					return token;
				}
				if (cod.charAt(i) == '=') {
					int j = i + 1;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.INCREMENTO, j);
					return token;
				}
			} catch (IndexOutOfBoundsException e) {
				return null;
			}
		}
		if (cod.charAt(i) == '*') {
			i++;
			try {
				if (cod.charAt(i) == '=') {
					int j = i + 1;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.INCREMENTO, j);
					return token;
				}
			} catch (IndexOutOfBoundsException e) {
				return null;
			}
		}
		if (cod.charAt(i) == '/') {
			i++;
			try {

				if (cod.charAt(i) == '=') {
					int j = i + 1;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.INCREMENTO, j);
					return token;
				}
			} catch (IndexOutOfBoundsException e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * Intenta extraer una estrcutura de control ([] ? () . <<) de la cadena cod
	 * a partir de la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer una estrucutra de
	 *            control - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            estructura de control - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es una
	 *         estrucutra de control . El Token se compone de el lexema, el tipo
	 *         y la posición del siguiente lexema.
	 */
	public Token extraerEstructuraControl(String cod, int i) {
		int a = i;
		if (cod.charAt(i) == 'f') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'o') {
				i++;
				if (i < cod.length() && cod.charAt(i) == 'r') {

					int j = i + 1;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.CONTROL, j);
					return token;
				}
			}
		}
		if (i < cod.length() && cod.charAt(i) == 'e') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'a') {
				i++;
				if (i < cod.length() && cod.charAt(i) == 'c') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 'h') {

						int j = i + 1;
						String lex = cod.substring(a, j);
						Token token = new Token(lex, Token.CONTROL, j);
						return token;
					}
				}
			}
		}
		if (i < cod.length() && cod.charAt(i) == 'w') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'h') {
				i++;
				if (i < cod.length() && cod.charAt(i) == 'i') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 'l') {
						i++;
						if (i < cod.length() && cod.charAt(i) == 'e') {
							int j = i + 1;
							String lex = cod.substring(a, j);
							Token token = new Token(lex, Token.CONTROL, j);
							return token;
						}
					}
				}
			}
		}
		if (i < cod.length() && cod.charAt(i) == 'i') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'n') {

				int j = i + 1;
				String lex = cod.substring(a, j);
				Token token = new Token(lex, Token.CONTROL, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * Intenta extraer una condicion especial de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un caracter
	 *            especial - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            caracter especial - 0<=indice<codigo.length()
	 * @return el token condicion especial o NULL, si el token en la posición
	 *         dada no es una condicion especial El Token se compone de el
	 *         lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerCondicionales(String cod, int i) {
		int a = i;
		if (i < cod.length() && cod.charAt(i) == 'e') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'l') {
				i++;
				if (i < cod.length() && cod.charAt(i) == 's') {
					i++;
					try {
						if (i < cod.length() && cod.charAt(i) == 'i') {
							i++;
							if (i < cod.length() && cod.charAt(i) == 'f') {

								int j = i + 1;
								String lex = cod.substring(a, j);

								Token token = new Token(lex,
										Token.CONDICIONALES, j);
								return token;
							}
						}
					} catch (IndexOutOfBoundsException e) {
						int j = i + 1;
						String lex = cod.substring(a, j);

						Token token = new Token(lex, Token.CONDICIONALES, j);
						return token;
					}
				}
			}

		}
		if (cod.charAt(i) == 'i') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'f') {
				int j = i + 1;
				String lex = cod.substring(a, j);

				Token token = new Token(lex, Token.CONDICIONALES, j);
				return token;
			}

		}
		if (i < cod.length() && cod.charAt(i) == 'e') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'l') {
				i++;
				if (i < cod.length() && cod.charAt(i) == 's') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 'e') {

						int j = i + 1;
						String lex = cod.substring(a, j);

						Token token = new Token(lex, Token.CONDICIONALES, j);
						return token;
					}
				}
			}

		}

		if (i < cod.length() && cod.charAt(i) == 'u') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'n') {
				i++;
				if (i < cod.length() && cod.charAt(i) == 'l') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 'e') {
						i++;
						if (i < cod.length() && cod.charAt(i) == 's') {
							i++;
							if (i < cod.length() && cod.charAt(i) == 's') {

								int j = i + 1;
								String lex = cod.substring(a, j);

								Token token = new Token(lex,
										Token.CONDICIONALES, j);
								return token;
							}
						}
					}

				}
			}
		}
		if (i < cod.length() && cod.charAt(i) == 'c') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'a') {
				i++;
				if (i < cod.length() && cod.charAt(i) == 's') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 'e') {

						int j = i + 1;
						String lex = cod.substring(a, j);

						Token token = new Token(lex, Token.CONDICIONALES, j);
						return token;
					}
				}
			}

		}
		if (i < cod.length() && cod.charAt(i) == 'w') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'h') {
				i++;
				if (i < cod.length() && cod.charAt(i) == 'e') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 'n') {

						int j = i + 1;
						String lex = cod.substring(a, j);

						Token token = new Token(lex, Token.CONDICIONALES, j);
						return token;
					}
				}
			}

		}

		if (i < cod.length() && cod.charAt(i) == 't') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'h') {
				i++;
				if (i < cod.length() && cod.charAt(i) == 'e') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 'n') {

						int j = i + 1;
						String lex = cod.substring(a, j);

						Token token = new Token(lex, Token.CONDICIONALES, j);
						return token;
					}
				}
			}
		}
		return null;

	}

	/**
	 * Intenta extraer un operador logicon AND de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un OPERADOR
	 *            LOGICO AND - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            OPERADOR LOGICO AND - 0<=indice<codigo.length()
	 * @return el token OPERADOR LOIGOC AND o NULL, si el token en la posición
	 *         dada no es un OPERADOR LOIGCO. El Token se compone de el lexema,
	 *         el tipo y la posición del siguiente lexema.
	 */
	public Token extraerOperadorLogicoAND(String cod, int i) {
		int a = i;
		if (i < cod.length() && cod.charAt(i) == 'a') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'n') {
				i++;
				if (i < cod.length() && cod.charAt(i) == 'd') {
					int j = i + 1;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.OPERADORLOGICO, j);
					return token;
				}

			}

		}
		return null;

	}

	/**
	 * Extraer un lexema no reconocido de la cadena cod a partir de la posición
	 * i.
	 * 
	 * @param cod
	 *            - código al cual se le va a extraer el token no reconocido -
	 *            codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a extraer el token no
	 *            reconocido - 0<=indice<codigo.length()
	 * @return el token de operador relacional. El Token se compone de lexema,
	 *         el tipo y la posición del siguiente lexema.
	 */
	public Token extraerRelacional(String cod, int i) {
		int a = i;
		if (cod.charAt(i) == '>') {
			try {
				i++;
				if (cod.charAt(i) == '=') {
					int j = i + 1;

					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
					return token;
				} else {
					int j = i;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
					return token;
				}
			} catch (IndexOutOfBoundsException ex) {
				int j = i;
				String lex = cod.substring(a, j);
				Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
				return token;
			}

		}
		if (cod.charAt(i) == '<') {
			try {
				i++;
				if (cod.charAt(i) == '=') {
					int j = i + 1;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
					return token;
				} else {
					int j = i;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
					return token;
				}
			} catch (IndexOutOfBoundsException ex) {
				int j = i;
				String lex = cod.substring(a, j);
				Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
				return token;
			}
		}
		if (cod.charAt(i) == '=') {
			i++;
			if (i < cod.length() && cod.charAt(i) == '=') {
				int j = i + 1;
				String lex = cod.substring(a, j);
				Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
				return token;
			}

		}
		if (i < cod.length() && cod.charAt(i) == '!') {
			i++;
			if (i < cod.length() && cod.charAt(i) == '=') {
				int j = i + 1;
				String lex = cod.substring(a, j);
				Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
				return token;
			}

		}

		return null;
	}

	/**
	 * Intenta extraer un caracter OPERADOR LOGICO NOT, de la cadena cod a
	 * partir de la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un operador
	 *            logico not - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            operador logico not - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         operador logico not. El Token se compone de el lexema, el tipo y
	 *         la posición del siguiente lexema.
	 */
	public Token extraerOperadorLogicoNOT(String cod, int i) {
		int a = i;
		if (i < cod.length() && cod.charAt(i) == 'n') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'o') {
				i++;
				if (i < cod.length() && cod.charAt(i) == 't') {
					int j = i + 1;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.OPERADORLOGICO, j);
					return token;
				}

			}

		}
		return null;

	}

	/**
	 * 
	 * Intenta extraer un caracter OPERADOR LOGICO OR, de la cadena cod a partir
	 * de la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un operador
	 *            logico not - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            OPERADOR LOGICO OR - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         OPERADOR LOGICO OR. El Token se compone de el lexema, el tipo y
	 *         la posición del siguiente lexema.
	 */
	public Token extraerOperadorLogicoOR(String cod, int i) {
		int a = i;
		if (i < cod.length() && cod.charAt(i) == 'o') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'r') {

				int j = i + 1;
				String lex = cod.substring(a, j);
				Token token = new Token(lex, Token.OPERADORLOGICO, j);
				return token;
			}

		}

		return null;

	}

	/**
	 * 
	 * 
	 * Intenta extraer un caracter CIERRE DE BLOQUE, de la cadena cod a partir
	 * de la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un CIERRE DE
	 *            BLOQUE - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            CIERRE DE BLOQUE - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         CIERRE DE BLOQUE. El Token se compone de el lexema, el tipo y la
	 *         posición del siguiente lexema.
	 */
	public Token extraerCierreDeBloque(String cod, int i) {
		int a = i;
		if (i < cod.length() && cod.charAt(i) == 'e') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'n') {
				i++;
				if (i < cod.length() && cod.charAt(i) == 'd') {
					int j = i + 1;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.CIERREBLOQUE, j);
					return token;
				}

			}

		}
		return null;

	}

	/**
	 * 
	 * 
	 * Intenta extraer un VALOR DE NULIDAD, de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un operador
	 *            logico not - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            VALOR DE NULIDAD, - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         VALOR DE NULIDAD,. El Token se compone de el lexema, el tipo y la
	 *         posición del siguiente lexema.
	 */
	public Token extraerValordeNulidad(String cod, int i) {
		int a = i;
		if (i < cod.length() && cod.charAt(i) == 'n') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'i') {
				i++;
				if (i < cod.length() && cod.charAt(i) == 'l') {
					int j = i + 1;
					String lex = cod.substring(a, j);
					Token token = new Token(lex, Token.VALORNULIDAD, j);
					return token;
				}

			}

		}
		return null;

	}

	/**
	 * 
	 * 
	 * Intenta extraer un INSTANCIADOR DE OBJETOS, de la cadena cod a partir de
	 * la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un INSTANCIADOR
	 *            DE OBJETOS - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            INSTANCIADOR DE OBJETOS - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         INSTANCIADOR DE OBJETOS El Token se compone de el lexema, el tipo
	 *         y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorInstanciadorDeObjetos(String cod, int i) {
		int a = i;
		if (i < cod.length() && cod.charAt(i) == 'n') {
			i++;
			if (i < cod.length() && i < cod.length() && cod.charAt(i) == 'e') {
				i++;
				if (i < cod.length() && i < cod.length()
						&& cod.charAt(i) == 'w') {
					int j = i + 1;
					String lex = cod.substring(a, j);

					Token token = new Token(lex, Token.INSTANCIADORDEOBJETOS, j);

					return token;
				}

			}
		}

		return null;
	}

	/**
	 * 
	 * 
	 * Intenta extraer una PALABRA RESERVADA CLASS, de la cadena cod a partir de
	 * la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer una PALABRA
	 *            RESERVADA CLASS - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            PALABRA RESERVADA CLASS - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         PALABRA RESERVADA CLASS. El Token se compone de el lexema, el
	 *         tipo y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorPalabraReservadaClase(String cod, int i) {

		int a = i;
		if (i < cod.length() && cod.charAt(i) == 'c') {
			i++;
			if (i < cod.length() && i < cod.length() && cod.charAt(i) == 'l') {
				i++;
				if (i < cod.length() && i < cod.length()
						&& cod.charAt(i) == 'a') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 's') {
						i++;
						if (i < cod.length() && cod.charAt(i) == 's') {

							int j = i + 1;
							String lex = cod.substring(a, j);

							Token token = new Token(lex,
									Token.PALABRARESERVADA, j);

							return token;
						}

					}
				}
			}
		}
		return null;

	}

	/**
	 * 
	 * 
	 * Intenta extraer una PALABRA RESERVADA BREAK, de la cadena cod a partir de
	 * la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un operador
	 *            logico not - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer una
	 *            PALABRA RESERVADA BREAK - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         una PALABRA RESERVADA BREAK. El Token se compone de el lexema, el
	 *         tipo y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorPalabraReservadaBreak(String cod, int i) {

		int a = i;
		if (i < cod.length() && cod.charAt(i) == 'b') {
			i++;
			if (i < cod.length() && i < cod.length() && cod.charAt(i) == 'r') {
				i++;
				if (i < cod.length() && i < cod.length()
						&& cod.charAt(i) == 'e') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 'a') {
						i++;
						if (i < cod.length() && cod.charAt(i) == 'k') {

							int j = i + 1;
							String lex = cod.substring(a, j);

							Token token = new Token(lex,
									Token.PALABRARESERVADA, j);

							return token;
						}

					}
				}
			}
		}
		return null;

	}

	/**
	 * 
	 * 
	 * Intenta extraer una PALABRA RESERVADA MODULE, de la cadena cod a partir
	 * de la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer una PALABRA
	 *            RESERVADA MODULE - codigo!=null
	 * @param i
	 *            una PALABRA RESERVADA MODULE - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es una
	 *         PALABRA RESERVADA MODULE. El Token se compone de el lexema, el
	 *         tipo y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorPalabraReservadaModule(String cod, int i) {

		int a = i;
		if (i < cod.length() && cod.charAt(i) == 'm') {
			i++;
			if (i < cod.length() && i < cod.length() && cod.charAt(i) == 'o') {
				i++;
				if (i < cod.length() && i < cod.length()
						&& cod.charAt(i) == 'd') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 'u') {
						i++;
						if (i < cod.length() && cod.charAt(i) == 'l') {
							if (i < cod.length() && cod.charAt(i) == 'e') {

								int j = i + 1;
								String lex = cod.substring(a, j);

								Token token = new Token(lex,
										Token.PALABRARESERVADA, j);

								return token;
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * 
	 * Intenta extraer un caracter una PALABRA RESERVADA DEFINED, de la cadena
	 * cod a partir de la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer una PALABRA
	 *            RESERVADA DEFINED - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un una
	 *            PALABRA RESERVADA DEFINED - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es una
	 *         PALABRA RESERVADA DEFINED. El Token se compone de el lexema, el
	 *         tipo y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorPalabraReservadadefined(String cod, int i) {

		int a = i;
		if (i < cod.length() && cod.charAt(i) == 'd') {
			i++;
			if (i < cod.length() && i < cod.length() && cod.charAt(i) == 'e') {
				i++;
				if (i < cod.length() && i < cod.length()
						&& cod.charAt(i) == 'f') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 'i') {
						i++;
						if (i < cod.length() && cod.charAt(i) == 'n') {
							if (i < cod.length() && cod.charAt(i) == 'e') {
								i++;
								if (i < cod.length() && cod.charAt(i) == 'd') {
									i++;
									if (i < cod.length()
											&& cod.charAt(i) == '?') {

										int j = i + 1;
										String lex = cod.substring(a, j);

										Token token = new Token(lex,
												Token.PALABRARESERVADA, j);

										return token;
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * 
	 * Intenta extraer un IDENTIFICADOR DE METODO CONSTRUCTOR, de la cadena cod
	 * a partir de la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un IDENTIFICADOR
	 *            DE METODO CONSTRUCTOR - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            IDENTIFICADOR DE METODO CONSTRUCTOR- 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         IDENTIFICADOR DE METODO CONSTRUCTOR. El Token se compone de el
	 *         lexema, el tipo y la posición del siguiente lexema.
	 */
	public boolean extraerIdentificadorMetodoConstructor(String cod, int i) {

		int j = i;

		if (cod.length() > j && cod.charAt(j) == 'i') {
			j++;
			if (cod.length() > j && cod.charAt(j) == 'n') {
				j++;
				if (cod.length() > j && cod.charAt(j) == 'i') {
					j++;
					if (cod.length() > j && cod.charAt(j) == 't') {
						j++;
						if (cod.length() > j && cod.charAt(j) == 'i') {
							j++;
							if (cod.length() > j && cod.charAt(j) == 'a') {
								j++;
								if (cod.length() > j && cod.charAt(j) == 'l') {
									j++;
									if (cod.length() > j
											&& cod.charAt(j) == 'i') {
										j++;
										if (cod.length() > j
												&& cod.charAt(j) == 'z') {
											j++;
											if (cod.length() > j
													&& cod.charAt(j) == 'e') {

												return true;

											}

										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;

	}

	/**
	 * 
	 * 
	 * Intenta extraer un IDENTIFICADOR DE VARIABLE DE INSTANCIA, de la cadena
	 * cod a partir de la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un IDENTIFICADOR
	 *            DE VARIABLE DE INSTANCIA - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            IDENTIFICADOR DE VARIABLE DE INSTANCIA -
	 *            0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         IDENTIFICADOR DE VARIABLE DE INSTANCIA. El Token se compone de el
	 *         lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorVariableInstancia(String cod, int i) {
		int j;

		if (cod.charAt(i) == '@') {
			j = i + 1;
			if (j < cod.length() && esLetra(cod.charAt(j))) {

				do
					j++;
				while (j < cod.length()
						&& (esLetra(cod.charAt(j)) || esDigito(cod.charAt(j)) || cod
								.charAt(j) == '_'));

				String lex = cod.substring(i, j);

				Token token = new Token(lex,
						Token.IDENTIFICADORDEVARIABLEINSTANCIA, j);
				return token;
			}
		}

		return null;
	}

	/**
	 * 
	 * 
	 * Intenta extraer un IDENTIFICADOR DE VARIABLE LOCAL, de la cadena cod a
	 * partir de la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un IDENTIFICADOR
	 *            DE VARIABLE LOCAL - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            IDENTIFICADOR DE VARIABLE LOCAL - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         IDENTIFICADOR DE VARIABLE LOCAL. El Token se compone de el
	 *         lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorVariableLocal(String cod, int i) {
		int j;

		if (cod.charAt(i) == '_') {
			j = i + 1;

			if (j < cod.length() && esLetra(cod.charAt(j))) {
				do
					j++;
				while (j < cod.length()
						&& (esLetra(cod.charAt(j)) || esDigito(cod.charAt(j)) || cod
								.charAt(j) == '_'));
				String lex = cod.substring(i, j);
				Token token = new Token(lex,
						Token.IDENTIFICADORDEVARIABLELOCAL, j);
				return token;
			}
		}

		return null;
	}

	/**
	 * 
	 * 
	 * Intenta extraer un IDENTIFICADOR DE VARIABLE GLOBAL, de la cadena cod a
	 * partir de la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un IDENTIFICADOR
	 *            DE VARIABLE GLOBAL- codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            IDENTIFICADOR DE VARIABLE GLOBAL - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         IDENTIFICADOR DE VARIABLE GLOBAL. El Token se compone de el
	 *         lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorVariableGlobal(String cod, int i) {
		int j;

		if (cod.charAt(i) == '$') {
			j = i + 1;
			if (j < cod.length() && esLetra(cod.charAt(j))) {
				do
					j++;
				while (j < cod.length()
						&& (esLetra(cod.charAt(j)) || esDigito(cod.charAt(j))));
				String lex = cod.substring(i, j);
				Token token = new Token(lex,
						Token.IDENTIFICADORDEVARIABLEGLOBAL, j);
				return token;
			}
		}

		return null;
	}

	/**
	 * 
	 * 
	 * Intenta extraer un caracter IDENTIFICADOR DE METODO, de la cadena cod a
	 * partir de la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un IDENTIFICADOR
	 *            DE METODO - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            IDENTIFICADOR DE METODO - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         IDENTIFICADOR DE METODO. El Token se compone de el lexema, el
	 *         tipo y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorMetodo(String cod, int i) {
		int j;

		if (cod.charAt(i) == 'd') {
			j = i + 1;
			if (cod.length() > j && cod.charAt(j) == 'e') {
				j++;
				if (cod.length() > j && cod.charAt(j) == 'f') {
					j++;
					if (cod.length() > j && cod.charAt(j) == ' ') {
						j++;

						if (extraerIdentificadorMetodoConstructor(cod, j)) {
							j += 10;

							String lex = cod.substring(i, j);

							Token token = new Token(lex,
									Token.IDENTIFICADORCONSTRUCTOR, j);
							return token;
						}
						if (j < cod.length() && esLetra(cod.charAt(j))) {

							do
								j++;
							while (j < cod.length()
									&& (esLetra(cod.charAt(j)) || esDigito(cod
											.charAt(j))));

							String lex = cod.substring(i, j);

							Token token = new Token(lex,
									Token.IDENTIFICADORMETODO, j);
							return token;
						}
					}
				}
			}
		}
		return null;

	}

	/**
	 * 
	 * 
	 * Intenta extraer un caracter UN IDENTIFICADOR IMPRIMIR, de la cadena cod a
	 * partir de la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un IDENTIFICADOR
	 *            IMPRIMIR - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            IDENTIIFCADOR IMPROMIR - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         IDENTIFICADOR IMPRIMIR. El Token se compone de el lexema, el tipo
	 *         y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorImprimir(String cod, int i) {

		int a = i;

		if (cod.charAt(i) == 'p') {

			i++;
			if (i < cod.length() && cod.charAt(i) == 'u') {

				i++;
				if (i < cod.length() && cod.charAt(i) == 't') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 's') {

						int j = i + 1;
						String lex = cod.substring(a, j);
						Token token = new Token(lex,
								Token.IDENTIFICADORIMPRESION, j);
						return token;
					}

				}
			}

			else {
				if (i < cod.length() && cod.charAt(i) == 'r') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 'i') {
						i++;
						if (i < cod.length() && cod.charAt(i) == 'n') {
							i++;
							if (i < cod.length() && cod.charAt(i) == 't') {

								int j = i + 1;
								String lex = cod.substring(a, j);
								Token token = new Token(lex,
										Token.IDENTIFICADORIMPRESION, j);
								return token;

							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * 
	 * Intenta extraer un IDENTIFICADOR DE VISIBILIDAD, de la cadena cod a
	 * partir de la posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un IDENTIFICADOR
	 *            DE VISIBILIDAD - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            IDENTIFICADOR DE VISIBILIDAD - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         IDENTIFICADOR DE VISIBILIDAD. El Token se compone de el lexema,
	 *         el tipo y la posición del siguiente lexema.
	 */
	public Token extraerVisibilidad(String cod, int i) {
		int a = i;
		if (i < cod.length() && cod.charAt(i) == 'p') {
			i++;
			if (i < cod.length() && cod.charAt(i) == 'u') {
				i++;
				if (i < cod.length() && cod.charAt(i) == 'b') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 'l') {
						i++;
						if (i < cod.length() && cod.charAt(i) == 'i') {
							i++;
							if (i < cod.length() && cod.charAt(i) == 'c') {

								int j = i + 1;
								String lex = cod.substring(a, j);
								Token token = new Token(lex,
										Token.IDENTIFICADORVISIBILIDAD, j);
								return token;
							}

						}

					}

				}

			} else {

				if (i < cod.length() && cod.charAt(i) == 'r') {
					i++;
					if (i < cod.length() && cod.charAt(i) == 'i') {
						i++;
						if (i < cod.length() && cod.charAt(i) == 'v') {
							i++;
							if (i < cod.length() && cod.charAt(i) == 'a') {
								i++;
								if (i < cod.length() && cod.charAt(i) == 't') {
									i++;
									if (i < cod.length()
											&& cod.charAt(i) == 'e') {

										int j = i + 1;
										String lex = cod.substring(a, j);
										Token token = new Token(lex,
												Token.IDENTIFICADORVISIBILIDAD,
												j);
										return token;

									}

								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un operador de asignación de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer el operador de
	 *            asignación - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer el
	 *            operador de asingación - 0<=i<codigo.length()
	 * @return el token operador asignación o NULL, si el token en la posición
	 *         dada no es un operador de asignación. El Token se compone de el
	 *         lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerOperadorAsignacion(String cod, int i) {
		if (cod.charAt(i) == '=') {
			int j = i + 1;

			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.OPERADORASIGNACION, j);
			return token;
		}

		return null;
	}

	/**
	 * Intenta extraer un comentario de linea de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer COMENTARIO DE
	 *            LINEA - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer el
	 *            comentario de linea - 0<=i<codigo.length()
	 * @return el token comentario de linea o NULL, si el token en la posición
	 *         dada no es comentario de linea. El Token se compone de el lexema,
	 *         el tipo y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorComentarioLinea(String cod, int i) {
		if (cod.charAt(i) == '#') {
			int j = i + 1;
			while (j < cod.length()){
				j++;
				if(j<cod.length() && (cod.charAt(j) == '\n' || cod.charAt(j) == '#')){
					break;
				}
			
			}
			
				String lex = cod.substring(i, j++);
			Token token = new Token(lex, Token.COMENTARIOLINEA, j);
			return token;
		}

		return null;
	}

	/**
	 * Intenta extraer un Identificador de clase de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer el operador de
	 *            asignación - codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer el
	 *            identificador de clase - 0<=i<codigo.length()
	 * @return el token identificador de clase o NULL, si el token en la
	 *         posición dada no es un identificador de clase. El Token se
	 *         compone de el lexema, el tipo y la posición del siguiente lexema.
	 */
	public Token extraerIdentificadorClase(String cod, int i) {
		int j = i;
		if (esMayuscula(cod.charAt(i))) {

			while (j < cod.length()
					&& (esDigito(cod.charAt(j)) || esLetra(cod.charAt(j))))
				j++;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.IDENTIFICADORCLASE, j);
			return token;
		}

		return null;
	}

	/**
	 * Intenta extraer un identificador de la cadena cod a partir de la posición
	 * i, basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer un
	 *            identificador - 0<=indice<codigo.length()
	 * @return el token identificaror o NULL, si el token en la posición dada no
	 *         es un identificador. El Token se compone de el lexema, el tipo y
	 *         la posición del siguiente lexema.
	 */

	public Token extraerIdentificador(String cod, int i) {
		if (cod.charAt(i) == '_') {
			int j = i + 1;
			while (j < cod.length() && esLetra(cod.charAt(j)))
				j++;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.IDENTIFICADOR, j);
			return token;
		}
		return null;
	}

	/**
	 * Extraer un lexema no reconocido de la cadena cod a partir de la posición
	 * i. Antes de utilizar este método, debe haberse intentado todos los otros
	 * métodos para los otros tipos de token
	 * 
	 * @param cod
	 *            - código al cual se le va a extraer el token no reconocido -
	 *            codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a extraer el token no
	 *            reconocido - 0<=indice<codigo.length()
	 * @return el token no reconocido. El Token se compone de lexema, el tipo y
	 *         la posición del siguiente lexema.
	 */
	public Token extraerNoReconocido(String cod, int i) {
		String lexema = cod.substring(i, i + 1);
		int j = i + 1;
		Token token = new Token(lexema, Token.NORECONOCIDO, j);
		return token;
	}

	/**
	 * Determina si un carácter es un dígito
	 * 
	 * @param caracter
	 *            - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un dígito o no
	 */
	public boolean esDigito(char caracter) {
		return caracter >= '0' && caracter <= '9';
	}

	/**
	 * Determina si un carácter es una letra
	 * 
	 * @param caracter
	 *            - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra o no
	 */
	public boolean esLetra(char caracter) {
		return (caracter >= 'A' && caracter <= 'Z')
				|| (caracter >= 'a' && caracter <= 'z');
	}

	/**
	 * Determina si un carácter es una letra Mayuscula
	 * 
	 * @param caracter
	 *            - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra o no
	 */
	public boolean esMayuscula(char caracter) {
		return (caracter >= 'A' && caracter <= 'Z');
	}

	/**
	 * Determina si un carácter es una letra minuscula
	 * 
	 * @param caracter
	 *            - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra o no
	 */
	public boolean esMinuscula(char caracter) {
		return (caracter >= 'a' && caracter <= 'z');
	}

}
