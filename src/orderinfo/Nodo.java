package orderinfo;

/**
 * Representa el nodo que contiene el carnet, nombre, apellido y carrera
 * @author luis
 */
public class Nodo {
    String ID;
    String name;
    String lastName;
    String career;
    Nodo   left;
    Nodo   right;
    
    //Se inicializan el nodo con los elementos enviados
    Nodo(String ID, String name, String lastName, String career){
        this.ID         = ID;
        this.name       = name;
        this.lastName   = lastName;
        this.career     = career;
        left            = null;
        right           = null;
    }
    
}

