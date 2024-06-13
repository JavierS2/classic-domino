package clases;

import java.util.LinkedList;

//clase que representa el paquete de 28 fichas
//del juego y las almacena en una linkedlist sin que ninguna se repita
//organizadas de manera aleatoria

public class PaqueteFicha {
    private final LinkedList<Ficha> fichas;

    // Constructor
    public PaqueteFicha() {
        this.fichas = new LinkedList<>();
    }

    public LinkedList<Ficha> getFichas() {
        return fichas;
    }

    // Genera y almacena las fichas del paquete
    public void revolverFichas() {
        while (fichas.size() <= 27) {
            Ficha ficha = new Ficha();
            if (fichas.isEmpty())
                fichas.add(ficha);
            if (!existeFicha(ficha))
                fichas.add(ficha);
        }
    }

    // Comprueba si una ficha existe en el paquete
    public boolean existeFicha(Ficha ficha) {
        for (Ficha value : fichas) {
            if (((ficha.getValue1() == value.getValue1()) && (ficha.getValue2() == value.getValue2())) ||
                    ((ficha.getValue1() == value.getValue2()) && (ficha.getValue2() == value.getValue1()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getClass() +
                "Mano= {" + fichas +
                '}';
    }

}
