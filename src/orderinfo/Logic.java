
package orderinfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Contiene los métodos que manejan la lógica, el control de árboles y la escritura de archivos
 * @author luis
 */
public class Logic {
    String path = null;
    
    /**
     * Realiza la comprobación de la existencia y el formato correcto del archivo
     */
    public void testRead(){
        File file = new File(path);
        
        try {
            //Se crea el objeto scanner para leer el archivo
            Scanner scanner = new Scanner(file);
            //Mientras que exista una siguiente linea
            scanner.hasNextLine();
            
            System.out.println("*** El archivo existe ****\n");
            
            //Si existe algún tipo de error
        } catch (FileNotFoundException e) {
            //Se muestra el mensaje al usuario
            System.out.println("- - - - - ERROR: No se encontró el archivo  - - - - -\n");
        }

    }
    
    /**
     * Obtiene el orden en el que el usuario desea que se ordenen los datos
     * @param order 
     * @throws IOException
     */
    public void orderBy(int order) throws IOException{
        //Para comprobar si existe algun tipo de error
        boolean has_errors = false;
        //Se crea un nuevo arbol
        Tree tree = new Tree();
        //Se crea el archivo
        File file = new File(path);
        try {

            //Se crea el objeto scanner para leer el archivo
            Scanner scanner = new Scanner(file);
            //Mientras que exista una siguiente linea
            while (scanner.hasNextLine()) {
                
               //Se guarda la línea en line
                String line = scanner.nextLine();                        
                //Se separan los datos
                String[] parts = line.split("\\|");
                //Se define el orden en el que se ordenarán los datos
                tree.setOrder(order);
                //Se se realiza la insersión del nodo enviando y creando un nuevo objeto nodo
                tree.insert(new Nodo(cleanSpaces(parts[0]), cleanSpaces(parts[1]), cleanSpaces(parts[2]), cleanSpaces(parts[3])));
            }
         //Si existe algún tipo de error
        } catch (FileNotFoundException e) {
            //Se muestra el mensaje al usuario
            System.out.println("- - - - - ERROR: No se encontró el archivo o el archivo no contiene el formato esperado - - - - -\n");
            has_errors = true;
        }
        
       
        if (!has_errors && !tree.duplicated) {
            //Se da el nombre del archivo que se va a escribir
            String fileName = "Salida.txt";
            //Se crea el archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                //Se envía el objeto que escribirá al archivo y se realiza el recorrido en orden para escribirlo
                tree.inOrderWrite(tree.root, writer);
             }
        }
            
    }
    
    /**
     * Método que limpia los espacios anteriores o finales a un texto o le quita el punto y coma.
     * @param str que contiene el texto
     * @return string lipio de espacios
     */
    public String cleanSpaces(String str){
        //Si al inicio tiene un espacio se le quita
        if( " ".equals(String.valueOf(str.charAt(0))) ){
            str = str.substring(1);
        }
        //Si al final tiene un espacio o un punto y coma se le quita
        if( " ".equals(String.valueOf(str.charAt(str.length()-1))) | ";".equals(String.valueOf(str.charAt(str.length()-1)))){
            str = str.substring(0,str.length()-1);
        } 
        
        return str;
    }
    
    
}
