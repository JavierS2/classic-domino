package clases;

import java.util.LinkedList;

public class Jugador {
    private final LinkedList<Ficha> mano;

    public Jugador() {
        mano = new LinkedList<>();
    }

    public LinkedList<Ficha> getMano() {
        return mano;
    }

    public void llenarMano(LinkedList<Ficha> fichas) {
        for (int i = 0; i < 7; i++) {
            mano.add(fichas.remove(i));
        }
    }

    public void tomarDelPozo(LinkedList<Ficha> pozo, LinkedList<Ficha> mesa) {
        if (!pozo.isEmpty()) {
            while (true) {
                if (!pozo.isEmpty()) {
                    mano.add(pozo.removeFirst());
                    System.out.println("...Tomando ficha del pozo...");
                    if (puedeJugar(mesa)) {
                        ponerMaquina(mesa);
                        break;
                    }
                } else {
                    System.out.println("...El pozo está vacío...");
                    break;
                }
            }
        }
    }

    public boolean puedeJugar(LinkedList<Ficha> mesa) {
        for (Ficha ficha : mano) {
            if (ficha.getValue1() == mesa.getFirst().getValue1() ||
                    ficha.getValue2() == mesa.getFirst().getValue1())
                return true;
        }
        for (Ficha ficha : mano) {
            if (ficha.getValue1() == mesa.getLast().getValue2() ||
                    ficha.getValue2() == mesa.getLast().getValue2())
                return true;
        }
        return false;
    }

    public Ficha invertirFicha(Ficha ficha) {
        return new Ficha(ficha.getValue2(), ficha.getValue1());
    }

    public void ponerEnLaMesa(LinkedList<Ficha> fichas, Ficha ficha) {
        fichas.add(ficha);
        System.out.println("Poner: " + ficha.toString());
        mano.remove(ficha);
    }

    public boolean ponerEnLaMesaIzquieda(LinkedList<Ficha> fichas, Ficha ficha) {
        if (ficha.getValue2() == fichas.getFirst().getValue1()) {
            fichas.addFirst(ficha);
        } else {
            fichas.addFirst(invertirFicha(ficha));
        }
        System.out.println("Poner: " + ficha);
        return mano.remove(ficha);
    }

    public boolean ponerEnLaMesaDerecha(LinkedList<Ficha> fichas, Ficha ficha) {
        if (fichas.getLast().getValue2() == ficha.getValue1()) {
            fichas.addLast(ficha);
        } else {
            fichas.addLast(invertirFicha(ficha));
        }
        System.out.println("Poner: " + ficha);
        return mano.remove(ficha);
    }

    public void mostrarOpcionesFichas(LinkedList<Ficha> mesa) {
        if (puedeJugar(mesa)) {
            for (int i = 0; i < mano.size(); i++) {
                if (mano.get(i).getValue1() == mesa.getFirst().getValue1() || mano.get(i).getValue2() == mesa.getFirst().getValue1())
                    System.out.println("Opciones lado izquierdo: " + mano.get(i).toString() + "- Escoger PRESS " + i);
            }
            System.out.println(" ");
            for (int i = 0; i < mano.size(); i++) {
                if (mano.get(i).getValue1() == mesa.getLast().getValue2() || mano.get(i).getValue2() == mesa.getLast().getValue2())
                    System.out.println("Opciones lado derecho: " + mano.get(i).toString() + "- Escoger PRESS " + i);
            }
        } else {
            System.out.println("...No hay fichas que puedas poner, tomando fichas del pozo...");
        }
    }

    @Override
    public String toString() {
        return "mano = \n" + mano + "\n";
    }

    public boolean ponerMaquina(LinkedList<Ficha> fichas) {
        int index = getIndex(fichas);
        if (index > -1) {
            if (mano.get(index).getValue1() == fichas.getFirst().getValue1() || mano.get(index).getValue2() == fichas.getFirst().getValue1()) {
                return ponerEnLaMesaIzquieda(fichas, mano.get(index));
            } else if (mano.get(index).getValue1() == fichas.getLast().getValue2() || mano.get(index).getValue2() == fichas.getLast().getValue2()) {
                return ponerEnLaMesaDerecha(fichas, mano.get(index));
            }
        } else {
            System.out.println("...No hay fichas para jugar, tomando una del pozo...\n");
        }
        return false;
    }

    private int getIndex(LinkedList<Ficha> fichas) {
        int index = -1;
        int mayorValor = -1;
        for (int i = 0; i < mano.size(); i++) {
            if (mano.get(i).getValue1() == fichas.getFirst().getValue1() || mano.get(i).getValue2() == fichas.getFirst().getValue1()) {
                if (mayorValor < (mano.get(i).getValue1() + mano.get(i).getValue2())) {
                    mayorValor = mano.get(i).getValue1() + mano.get(i).getValue2();
                    index = i;
                }
            }
        }
        for (int i = 0; i < mano.size(); i++) {
            if (mano.get(i).getValue1() == fichas.getLast().getValue2() || mano.get(i).getValue2() == fichas.getLast().getValue2()) {
                if (mayorValor < (mano.get(i).getValue1() + mano.get(i).getValue2())) {
                    mayorValor = mano.get(i).getValue1() + mano.get(i).getValue2();
                    index = i;
                }
            }
        }
        return index;
    }

    public int calcularPuntos() {
        int puntos = 0;
        for (Ficha aux : mano) {
            puntos += aux.getValue1() + aux.getValue2();
        }
        return puntos;
    }

}
