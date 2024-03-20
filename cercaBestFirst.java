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

        pendientes.add(incial);

        while (!encontrado && !pendientes.isEmpty()) {
            State actual = pendientes.remove(0);
            tratados.add(actual);

            if (actual.equals(targetState)) {
                encontrado = true;
                resultado.add(actual);
                return resultado;
            } else {
                List<State> sucesores = EvaluateOperators(actual, targetState);
                for (State sucesor : sucesores) {
                    if (!tratados.contains(sucesor) && !pendientes.contains(sucesor)) {
                        pendientes.add(sucesor);

                    }
                }
                Collections.sort(pendientes);
            }
        }
        if (encontrado){
            return resultado;
        }else{
            System.out.println("No existeix cami");
            return null;
        }
    }

    @Override
    protected List<State> EvaluateOperators(State currentState, State targetState) {
        int posX = currentState.getPosX();
        int posY = currentState.getPosY();
        List<State> sucesores = new ArrayList<>();

        // arriba, si Y no es la primera posición, se puede mirar arriba
        if (posY > 0) {
            State auxUp = new State(posX, posY + 1);
            auxUp.setHeuristica(heuristic.Evaluate(auxUp, targetState, costMap));
            sucesores.add(auxUp);
        }

        // abajo, si Y es mas pequeño que el maximo de filas, podemos mirar abajo
        if (posY < costMap.length - 1) {
            State auxDown = new State(posX, posY - 1);
            auxDown.setHeuristica(heuristic.Evaluate(auxDown, targetState, costMap));
            sucesores.add(auxDown);

        }

        // derecha, si X es mas grande que 0, podemos mirar a la derecha
        if (posX > 0) {
            State auxRight = new State(posX + 1, posY);
            auxRight.setHeuristica(heuristic.Evaluate(auxRight, targetState, costMap));
            sucesores.add(auxRight);
        }

        // izquierda si x es mas pequeño que el max de columnas podemos mirar a la
        // izquierda
        if (posX > costMap.length - 1) {
            State auxLeft = new State(posX - 1, posY);
            auxLeft.setHeuristica(heuristic.Evaluate(auxLeft, targetState, costMap));
            sucesores.add(auxLeft);
        }

        return sucesores;

    }

}
