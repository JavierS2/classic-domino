package clases;

// Clase que representa una partida contra la máquina
public class ContraMaquina extends Juego {
    public ContraMaquina() {
        super();
    }

    @Override
    public void jugar() {
        System.out.println("\n** ERES EL JUGADOR 1 **");
        super.jugar();
    }
    @Override
    public void turnoJugador2() {
        System.out.println("** MOVIMIENTO MÁQUINA **");
        if (getJugador2().ponerMaquina(getMesa())) {
            getTurnos().add(2);
        } else {
            System.out.println("...Tomando ficha del pozo...");
            getJugador2().tomarDelPozo(getPozo(), getMesa());
            getTurnos().add(2);
        }
    }
}
