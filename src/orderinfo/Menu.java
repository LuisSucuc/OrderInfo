package orderinfo;

import java.io.IOException;
import java.util.Scanner;

/**
 * Maneja y obtiene las opciones del menu principal
 * @author luis
 */
public class Menu {
    
    private final Scanner read = new Scanner(System.in);

    /**
     * Obtiene la opción elegida por el usuario
     * @return La opción seleccionada
     */
    public int getOpcion() {
        System.out.println("\n******* SELECCIONA UNA OPCIÓN *******");
        System.out.println("1. Ingresar ubicación");
        System.out.println("2. Ordenar por carnet");
        System.out.println("3. Ordenar por carrera");
        System.out.println("4. Ordenar por nombre y apellido");
        System.out.println("5. Salir");
        System.out.println("\n");
        int option = read.nextInt();
        System.out.println("\n");
        return option;
    }
    
    /**
     * Obtiene la ubicación del archivo
     * @return El String de ubicación
     */
    public String setPath() {
        System.out.println("> Ingresa la ubicación del archivo");
        read.nextLine();
        String path = read.nextLine();
        System.out.println("\n");
        return path;
    }
    
    /**
     * Muestra el error de dirección del archivo
     */
    public void noPath(){
        System.out.println("- - - ERROR: Ubicación del archivo no ingresada - - - ");
    }
    

    /**
     * Función que inicializa todo el programa
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //Objeto de la lógica que controla el árbol
        Logic logic = new Logic();
        //Objeto que ayuda a obtener las opciones, la ubicación y mensajes de error
        Menu menu = new Menu();
        int option = 0;
        logic.path  = null; // "/home/luis/Documentos/UMG/Programación 3/Proyectos/OrderInfo/test.txt";

        while (option != 5) {

            option = menu.getOpcion();

            switch (option) {

                case 1:
                    logic.path = menu.setPath();
                    logic.testRead();
                    break;

                case 2:
                    if (logic.path != null) {
                        logic.orderBy(By.ID);
                    } else {
                        menu.noPath();
                    }
                    break;

                case 3:
                    if (logic.path != null) {
                        logic.orderBy(By.CARRER);
                    } else {
                        menu.noPath();
                    }
                    break;

                case 4:
                    if (logic.path != null) {
                        logic.orderBy(By.LAST_AND_NAME);
                    } else {
                        menu.noPath();
                    }
                    break;
                default:
                    break;

            }
        }

    }

}
