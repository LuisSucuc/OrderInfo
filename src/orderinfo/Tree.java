
package orderinfo;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Arbol que realiza la inserción y ordenación
 * @author luis
 */
public class Tree {
    
    Nodo root;
    private int order;
    public boolean duplicated = false;
    
    /**
     * Realiza las comparaciones y asignaciones del nodo recibido
     * @param current es el nodo que se esta evaluando
     * @param newNodo  es el nodo que se insertará
     * @return el nodo raíz
     */
    public Nodo insert(Nodo current, Nodo newNodo) {
         
       //Si el nodo está vacío se inserta como raíz el nuevo nodo
        if (current == null) {
            return newNodo;
        }
        
        
        //Se obtiene el texto para la comparación del nodo actual
        String currentText = getComparation(current);
        //Se obitene el texto de ocmparación para el nuevo nodo
        String newText =  getComparation(newNodo);
        
        //Se realiza la comparación
        int comparacion = currentText.compareTo(newText);
        
        //Si es mayor a 0 es porque es menor a el nodo actual
        if (comparacion > 0) {
            //Se realiza la llamada para comparación del nodo izquierdo
            current.left = insert(current.left, newNodo);
            
        //Si es menor a 0 es porque es mayor a el nodo actual    
        } else if (comparacion < 0) {
            //Se realiza la llamada para comparación del nodo derecho
           current.right = insert(current.right, newNodo);
            
        } else {
            
            //El nodo ya existe
            System.out.println("----- ERROR: El archivo contiene un registro duplicado -----");
            System.out.println(getWriteText(newNodo));
            duplicated = true;
            return null;
        }
        return current;
    }
    
    /**
     * Realiza la primera llamada recursiva
     * @param newNodo el nodo leído en el archivo
     */
    public void insert(Nodo newNodo) {
        root = insert(root, newNodo);
    }
    
    /**
     * Retorna el texto ordenado según la elección del usuario
     * @param nodo nuevo creado
     * @return el texto ordenado dependiendo la elección del usuario
     */
    public String getComparation(Nodo nodo){
        switch (order) {
            case By.ID:
                return nodo.ID + nodo.name + nodo.lastName + nodo.career;
            case By.CARRER:
                return nodo.career + nodo.name + nodo.lastName + nodo.ID;
            case By.LAST_AND_NAME:
                return nodo.name + nodo.lastName + nodo.ID + nodo.career;
            default:
                break;
        }
        return "";
    }
    
    /**
     * Retorna el texto ordenado según la elección del usuario y en el formato para su escritura
     * @param nodo a escribir en el archivo
     * @return el texto ordenado dependiendo la elección del usuario
     */
    public String getWriteText(Nodo nodo){
        switch (order) {
            case By.ID:
                return String.format("%s, %s, %s, %s \n", nodo.ID , nodo.name , nodo.lastName , nodo.career);
            case By.CARRER:
                return String.format("%s, %s, %s, %s \n", nodo.career, nodo.name , nodo.lastName, nodo.ID );
            case By.LAST_AND_NAME:
                return String.format("%s, %s, %s, %s \n", nodo.name , nodo.lastName, nodo.ID, nodo.career);
            default:
                break;
        }
        return "";
    }
    
    /**
     * Se recorre en orden y se escribe en archivo
     * @param current Nodo actual en lectura del archivo
     * @param writer es el objeto que realiza la escritura del archivo
     * @throws IOException
     */
    public void inOrderWrite(Nodo current, BufferedWriter writer) throws IOException {
        
        
        if (current != null) {

            if (current.left != null) {
                inOrderWrite(current.left, writer);
            }

            String text = getWriteText(current);
            System.out.println(text);
            //Se escribe el archivo
            writer.write(text);

            if (current.right != null) {
                inOrderWrite(current.right, writer);
            }
        }

    }
    
    /**
     * Define el orden que eligió el usuario
     * @param order
     */
    public void setOrder(int order){
        this.order = order;
    }


    
}
