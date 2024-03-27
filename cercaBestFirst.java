import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class cercaBestFirst extends Search {
    float totalOr;
    List<State> resultado;
    List<State> pendientes;
    List<State> tratados;
    boolean encontrado;

    public cercaBestFirst(float[][] costMap, Heuristic heuristic) {
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
            Collections.sort(pendientes); // Ordena de tal manera que los ultimos hijos seran los primeros de la lista,
                                          // por lo que tratara esos
            State actual = pendientes.remove(0);

            if (actual.equals(targetState)) {
                encontrado = true;
                finalS = actual; // Me guardo la referencia

                // resultado.add(actual);

            } else {
                List<State> sucesores = EvaluateOperators(actual, targetState); // Busca los hijos
                for (State sucesor : sucesores) {
                    if (!tratados.contains(sucesor) && !pendientes.contains(sucesor)) {
                        sucesor.setAnterior(actual);
                        pendientes.add(sucesor);
                    }
                }
                tratados.add(actual);
            }
        }
        if (encontrado) {
            State hijos = finalS;
            while (hijos.anterior != null) {
                resultado.add(hijos);
                hijos = hijos.anterior;
            }
            Collections.reverse(resultado);
            return resultado;

        } else {
            System.out.println("No existeix cami");
            return null;
        }
    }

}
