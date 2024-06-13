package clases;

import java.util.Scanner;

public class Mesa {
    public static void main(String[] args) {
        int option;
        Scanner scanner = new Scanner(System.in);
        System.err.println("\nRealizado por Javier Santodomingo y Jonathan Vega\n");
        while (true) {
            System.out.println("=============================================");
            System.out.println("======== BIENVENIDO - CLASSIC DOMINO ========");
            System.out.println("=============================================");
            System.out.println("1. PLAY SINGLEPLAYER");
            System.out.println("2. PLAY PLAYER vs PLAYER");
            System.out.println("3. SALIR");
            System.out.println("=============================================");
            System.out.print("> Digite su opción: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> {
                    Juego juego = new ContraMaquina();
                    juego.jugar();
                }
                case 2 -> {
                    Juego juego = new JcJ();
                    juego.jugar();
                }
                case 3 -> {
                    System.out.println("\n======== CLASSIC DOMINO ========");
                    System.out.println("¡SALIENDO... GRACIAS POR JUGAR!");
                    System.out.println("================================");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("\nError, digite una opción válida.\n");
            }
        }
    }
}