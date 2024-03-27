import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class cercaEstrella extends Search {
    float totalOr;
    List<State> resultado;
    List<State> pendientes;
    List<State> tratados;
    boolean encontrado;

    public cercaEstrella(float[][] costMap, Heuristic heuristic) {
        super(costMap, heuristic);
        resultado = new ArrayList<>();
        pendientes = new ArrayList<>();
        tratados = new ArrayList<>();
        encontrado = false;
        totalOr = 0;
    }

    @Override
    public List<State> DoSearch(State initialState, State targetState) {
        State incial = new State(initialState.getPosX(), initialState.getPosY());
        incial.setHeuristica(heuristic.Evaluate(initialState, targetState, costMap));
        State finalS = new State(0, 0);

        pendientes.add(incial);

        while (!encontrado && !pendientes.isEmpty()) {
            pendientes = ordenar(pendientes);
            State actual = pendientes.remove(0);

            if (actual.equals(targetState)) {
                encontrado = true;
                finalS = actual;
            } else {
                List<State> sucesores = EvaluateOperators(actual, targetState); // Busca los hijos
                for (State sucesor : sucesores) {
                    if (!tratados.contains(sucesor)) {
                        if (!pendientes.contains(sucesor)) {
                            sucesor.setAnterior(actual); //guardar ref
                            pendientes.add(sucesor);
                            
                        } else if (sucesor.getContOr()+sucesor.getHeuristica() < pendientes.get(pendientes.indexOf(sucesor)).getContOr() 
                        + pendientes.get(pendientes.indexOf(sucesor)).getHeuristica()){
                            pendientes.remove(sucesor); //elimino en esa coordenada
                            sucesor.setAnterior(actual); //Guardamos referencia
                            pendientes.add(sucesor); //añado con el nuevo camino
                        }
                    }
                }
                tratados.add(actual);
            }
        }

        if (encontrado) {
            State hijos = finalS;
            while (hijos.anterior != null) { //hasta llegar al primero
                resultado.add(hijos);
                hijos = hijos.anterior;
            }
            Collections.reverse(resultado); //Dar a la inversa el resultado para que empiece el camino desde el inicio
            
            return resultado;
        } else {
            System.out.println("No existeix cami");
            return null;
        }
    }

    private List<State> ordenar(List<State> pendientesAOrdenar) {
        quickSort(pendientesAOrdenar, 0, pendientesAOrdenar.size() - 1);
        return pendientesAOrdenar;
    }


    //función recursiva que parte en mitades y ordena cada mitad
    private void quickSort(List<State> pendientes, int primer, int ultim) {
        if (primer < ultim) { //Si primer elemeto < que el ultimo para llegar al final del bucle 
            int medio = partir(pendientes, primer, ultim); //sacamos elemento medio 
            quickSort(pendientes, primer, medio - 1); //parte izq
            quickSort(pendientes, medio + 1, ultim); //parte drch
        }
    }

    private int partir(List<State> pendientes, int primer, int ultim) {
        float pivot = pendientes.get(ultim).getHeuristica(); //obtener un pivote para ir comparando
        int i = primer - 1; //Obtener pos real

        for (int j = primer; j < ultim; j++) {
            if (pendientes.get(j).getHeuristica() <= pivot) { //Comparo con a heuristica en la pos j
                i++; //Avanzar en la tabla
                Collections.swap(pendientes, i, j); //Intercambio de posición y pongo en la posición i el valor de la j
            }
        }
        Collections.swap(pendientes, i + 1, ultim); //intercambiamos 
        return i + 1;
    }

}
