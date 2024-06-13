package clases;

// Clase que representa una partida contra otro jugador
public class JcJ extends Juego {
    public JcJ() {
        super();
    }

    @Override
    public void turnoJugador2() {
        if (getJugador2().puedeJugar(getMesa())) {
            int seletion, numFicha;
            getJugador2().mostrarOpcionesFichas(getMesa());
            while (true) {
                System.out.println("\n1. Poner a la izquierda");
                System.out.println("2. Poner a la derecha");
                System.out.println("3. Finalizar juego");
                System.out.print("\n> Digite su opción: ");
                seletion = scanner.nextInt();
                scanner.nextLine();
                if (seletion > 0 && seletion <= 3) {
                    switch (seletion) {
                        case 1 -> {
                            System.out.print("> Número de la ficha a colocar: ");
                            numFicha = scanner.nextInt();
                            scanner.nextLine();
                            if (validarTurnoIzq(getJugador2(), numFicha)) {
                                getJugador2().ponerEnLaMesaIzquieda(getMesa(), getJugador2().getMano().get(numFicha));
                                getTurnos().add(2);
                            } else {
                                System.out.println("\n** Error, no se puede colocar esta ficha a la izquierda, intente con otra. **\n");
                            }
                        }
                        case 2 -> {
                            System.out.print("> Número de la ficha a colocar: ");
                            numFicha = scanner.nextInt();
                            scanner.nextLine();
                            if (validarTurnoDer(getJugador2(), numFicha)) {
                                getJugador2().ponerEnLaMesaDerecha(getMesa(), getJugador2().getMano().get(numFicha));
                                getTurnos().add(2);
                            } else {
                                System.out.println("\n** Error, no se puede colocar esta ficha a la derecha, intente con otra. **\n");
                            }
                        }
                        case 3 -> {
                            System.out.println("\n** Finalizó el juego, no hubo ganador. **\n");
                            System.exit(0);
                        }
                        default -> System.out.println("\nOpción no válida, intenta nuevamente!\n");
                    }
                    if (getTurnos().get(getTurnos().size() - 1) == 2) break;
                } else {
                    System.out.println("\n** Error, ingrese una opción valida (1-3) **");
                }
            }
        } else {
            getJugador2().tomarDelPozo(getPozo(), getMesa());
            getTurnos().add(2);
        }
    }
}
