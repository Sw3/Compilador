package mundo;

import java.util.ArrayList;

import org.omg.CORBA.RepositoryIdHelper;

public class AnalizadorSintactico {
 String incidencias="";
 ArrayList<String> tabla=null;
	
	public AnalizadorSintactico(ArrayList<String> tablaSimbolos){
		this.tabla=tablaSimbolos;
		analizador(); //inicia proceso de compilacion
	}
	
	public void analizador(){
		try{
			int i=0;
			if(tabla.get(i).equals("class")){
				i+=3;
				if(tabla.get(i).equals("Identificador clase")){
					i+=2;
					//inicia con la categoría <cuerpo clase>
					if (tabla.get(i).equals("Identificador Metodo")) {
						i=bloqueFuncion(i);
						System.out.println("analisis del metodo finalizado, continua con: ");
						System.out.println(tabla.get(i));
					}
					if(tabla.get(i).equals("Identificador de variable Globa"));{
						System.out.println("detecto una variable: "+tabla.get(i));
						i+=2;
						if(tabla.get(i).equals("operador de asignacion")){
							
						}
					}
					
				}else{
					DeteccionError("El nombre de la clase no cumple con los estándares establecidos o está ausente");
				}
				
			}else{
				DeteccionError("No se ha encontrado la declaración de clase");
			}		
			
		}catch(IndexOutOfBoundsException ex){
			System.out.println("Reporte finalizado");
		}
		
	}
	public int bloqueFuncion(int pos){
		
		int i=pos;
	
		do{	i+=2;
			if(tabla.get(i).contains("Identificador Metodo")){
				DeteccionError("uno de los metodos no ha finalizado corectamente");
		
			}			
			if(tabla.get(i).equals("Identificador de variable de instancia")){
			
			}if(tabla.get(i).equals("Metodo immpresion")){
				
			}			
		}while(!tabla.get(i).equals("Cerrar un bloque de codigo") || i<tabla.size());
		System.out.println("el metodo termino bien");
		if(tabla.get(i).contains("def ")){
			DeteccionError("No se ha encontrado el cierre del metodo "+tabla.get(pos));
		}
		return i;
		
	}
	
	public void DeteccionError(String segmento){
		if(!incidencias.contains(segmento)){
				incidencias +=segmento+"\n";
		System.out.println(incidencias);
		}	
	}
	
	public int bloqueCondicionales(int pos){
		
		
		return -1;
	}
	
}
