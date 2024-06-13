package clases;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

//clase que representa una partida de domino
public abstract class Juego {
    Scanner scanner = new Scanner(System.in);
    private final LinkedList<Ficha> mesa;
    private final PaqueteFicha pozo;
    private final ArrayList<Integer> turnos;
    private final Jugador jugador1;
    private final Jugador jugador2;

    public Juego() {
        mesa = new LinkedList<>();
        pozo = new PaqueteFicha();
        pozo.revolverFichas();
        turnos = new ArrayList<>();
        jugador1 = new Jugador();
        jugador1.llenarMano(pozo.getFichas());
        jugador2 = new Jugador();
        jugador2.llenarMano(pozo.getFichas());
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public LinkedList<Ficha> getMesa() {
        return mesa;
    }

    public LinkedList<Ficha> getPozo() {
        return pozo.getFichas();
    }

    public ArrayList<Integer> getTurnos() {
        return turnos;
    }

    public void jugar() {
        while (true) {
            if (mesa.isEmpty()) {
                Ficha dobleMayorJ1 = dobleMayorJugador(jugador1);
                Ficha dobleMayorJ2 = dobleMayorJugador(jugador2);
                empiezaDobleMayor(dobleMayorJ1, dobleMayorJ2);
            }

            if (turnos.get(turnos.size() - 1) == 1) {
                System.out.println("\n========== TURNO JUGADOR 2 ==========\n");
                System.out.println("Fichas jugador #2: " + jugador2.getMano().toString());
                System.out.println("Mesa actual: " + mostrarMesa() + "\n");
                turnoJugador2();
                if (jugador2.getMano().isEmpty()) {
                    System.out.println("\n******************************************************");
                    System.out.println("**  JUGADOR 2 VACIÓ SU MANO, ¡¡¡GANA LA PARTIDA!!!  **");
                    System.out.println("******************************************************");
                    System.exit(0);
                }
                turnos.add(2);
            }
            if (turnos.get(turnos.size() - 1) == 2) {
                System.out.println("\n========== TURNO JUGADOR 1 ==========\n");
                System.out.println("Fichas jugador #1: " + jugador1.getMano().toString());
                System.out.println("Mesa actual: " + mostrarMesa() + "\n");
                turnoJugador1();
                if (jugador1.getMano().isEmpty()) {
                    System.out.println("\n******************************************************");
                    System.out.println("**  JUGADOR 1 VACIÓ SU MANO, ¡¡¡GANA LA PARTIDA!!!  **");
                    System.out.println("******************************************************");
                    System.exit(0);
                }
            }
            if (pozo.getFichas().isEmpty() && !jugador1.puedeJugar(mesa) && !jugador2.puedeJugar(mesa)) {
                System.out.println("\n...No hay más movimientos y el pozo está vacío...\n");
                System.out.println("Fichas jugador #1: " + jugador1.getMano().toString());
                System.out.println("Fichas jugador #2: " + jugador2.getMano().toString());
                if (jugador1.calcularPuntos() > jugador2.calcularPuntos()) {
                    System.out.println("\n**********************************************************************");
                    System.out.println("**  JUGADOR 2 TIENE MENOS PINTAS EN SU MANO, ¡¡¡GANA LA PARTIDA!!!  **");
                    System.out.println("**********************************************************************");
                    System.exit(0);
                } else if (jugador1.calcularPuntos() < jugador2.calcularPuntos()) {
                    System.out.println("\n**********************************************************************");
                    System.out.println("**  JUGADOR 1 TIENE MENOS PINTAS EN SU MANO, ¡¡¡GANA LA PARTIDA!!!  **");
                    System.out.println("**********************************************************************");
                    System.exit(0);
                } else {
                    System.out.println("\n*******************");
                    System.out.println("**  ¡¡¡EMPATE!!  **");
                    System.out.println("*******************");
                    System.exit(0);
                }
            }
        }
    }

    private void empiezaDobleMayor(Ficha dobleMayorJ1, Ficha dobleMayorJ2) {
        if (dobleMayorJ1.getValue1() > dobleMayorJ2.getValue1()) {
            System.out.println("\nComienza jugador 1 con el doble mayor: " + dobleMayorJ1);
            jugador1.ponerEnLaMesa(mesa, dobleMayorJ1);
            turnos.add(1);
        } else if (dobleMayorJ1.getValue1() < dobleMayorJ2.getValue1()) {
            System.out.println("\nComienza jugador 2 con el doble mayor: " + dobleMayorJ2);
            jugador2.ponerEnLaMesa(mesa, dobleMayorJ2);
            turnos.add(2);
        } else {
            System.out.println("...Los jugadores no tienen dobles...");
            masPintas();
        }
    }

    private void masPintas() {
        Ficha mayor1 = new Ficha(-1, -1);
        Ficha mayor2 = new Ficha(-1, -1);
        for (Ficha aux : jugador1.getMano()) {
            if ((mayor1.getValue1() + mayor1.getValue2()) < (aux.getValue1() + aux.getValue2()))
                mayor1 = aux;
        }
        for (Ficha aux : jugador2.getMano()) {
            if ((mayor2.getValue1() + mayor2.getValue2()) < (aux.getValue1() + aux.getValue2()))
                mayor2 = aux;
        }
        if ((mayor1.getValue1() + mayor1.getValue2()) > (mayor2.getValue1() + mayor2.getValue2())) {
            System.out.println("Empieza jugador 1 con la ficha de más pintas: " + mayor1);
            jugador1.ponerEnLaMesa(mesa, mayor1);
            turnos.add(1);
        } else {
            System.out.println("Empieza jugador 2 con la ficha de más pintas: " + mayor2);
            jugador1.ponerEnLaMesa(mesa, mayor2);
            turnos.add(2);
        }
    }

    public abstract void turnoJugador2();

    public void turnoJugador1() {
        if (jugador1.puedeJugar(mesa)) {
            int seletion, numFicha;
            jugador1.mostrarOpcionesFichas(getMesa());
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
                            if (validarTurnoIzq(jugador1, numFicha)) {
                                jugador1.ponerEnLaMesaIzquieda(mesa, jugador1.getMano().get(numFicha));
                                turnos.add(1);
                            } else {
                                System.out.println("\n** Error, no se puede colocar esta ficha a la izquierda, intente con otra. **");
                            }
                        }
                        case 2 -> {
                            System.out.print("> Número de la ficha a colocar: ");
                            numFicha = scanner.nextInt();
                            scanner.nextLine();

                            if (validarTurnoDer(jugador1, numFicha)) {
                                jugador1.ponerEnLaMesaDerecha(mesa, jugador1.getMano().get(numFicha));
                                turnos.add(1);
                            } else {
                                System.out.println("\nError, no se puede colocar esta ficha a la derecha, intente con otra.");
                            }
                        }
                        case 3 -> {
                            System.out.println("\n** Finalizó el juego, no hubo ganador. **\n");
                            System.exit(0);
                        }
                        default -> System.out.println("\nOpción no válida, intenta nuevamente!\n");
                    }
                    if (turnos.get(turnos.size() - 1) == 1) break;
                } else System.out.println("\n** Error, ingrese una opción valida (1-3) **");
            }
        } else {
            System.out.println("...No hay fichas para jugar, tomando ficha del pozo...");
            jugador1.tomarDelPozo(getPozo(), mesa);
            turnos.add(1);
        }
    }

    public boolean validarTurnoIzq(Jugador jugador, int numFicha) {
        for (int i = 0; i < jugador.getMano().size(); i++) {
            if (jugador.getMano().get(i).getValue1() == mesa.getFirst().getValue1() || jugador.getMano().get(i).getValue2() == mesa.getFirst().getValue1()) {
                ArrayList<Integer> numValidos = new ArrayList<>();
                numValidos.add(i);
                if (numValidos.contains(numFicha)) return true;
            }
        }
        return false;
    }

    public boolean validarTurnoDer(Jugador jugador, int numFicha) {
        ArrayList<Integer> numValidos = new ArrayList<>();
        for (int i = 0; i < jugador.getMano().size(); i++) {
            if (jugador.getMano().get(i).getValue1() == mesa.getLast().getValue2() || jugador.getMano().get(i).getValue2() == mesa.getLast().getValue2()) {
                numValidos.add(i);
                if (numValidos.contains(numFicha))
                    return true;
            }
        }
        return false;
    }

    public Ficha dobleMayorJugador(Jugador jugador) {
        Ficha fichaMayor = new Ficha(-1, -1);
        for (int i = 0; i < jugador.getMano().size(); i++) {
            if (jugador.getMano().get(i).getValue1() == jugador.getMano().get(i).getValue2()) {
                if (jugador.getMano().get(i).getValue1() > fichaMayor.getValue1()) {
                    fichaMayor = jugador.getMano().get(i);
                }
            }
        }
        return fichaMayor;
    }

    public String mostrarMesa() {
        return " " + mesa.toString();
    }
}