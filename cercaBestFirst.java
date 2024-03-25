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

    @Override
    protected List<State> EvaluateOperators(State currentState, State targetState) {
        int posX = currentState.getPosX();
        int posY = currentState.getPosY();
        List<State> sucesores = new ArrayList<>();
        float OrAct = currentState.getContOr();
        int contDias = currentState.getContDias();

        // arriba, si Y no es la primera posición, se puede mirar arriba
        if (posY >= 0 && posY < costMap.length -1) {
            State auxUp = new State(posX, posY + 1);
            auxUp.setHeuristica(heuristic.Evaluate(auxUp, targetState, costMap));
            if (costMap[auxUp.getPosY()][auxUp.getPosX()] < 100) { //Si no es montaña
                sucesores.add(auxUp);
                auxUp.setContOr(OrAct + (5-costMap[auxUp.getPosY()][auxUp.getPosX()]));
                auxUp.setContDias(contDias + 1);

            }
        }

        // abajo, si Y es mas pequeño que el maximo de filas, podemos mirar abajo
        if (posY < costMap.length - 1 && posY != 0) {
            State auxDown = new State(posX, posY - 1);
            auxDown.setHeuristica(heuristic.Evaluate(auxDown, targetState, costMap));
            if (costMap[auxDown.getPosY()][auxDown.getPosX()] < 100) {
                sucesores.add(auxDown);
                auxDown.setContOr(OrAct + (5-costMap[auxDown.getPosY()][auxDown.getPosX()]));
                auxDown.setContDias(contDias + 1);
            }
        }

        // derecha, si X es mas grande que 0, podemos mirar a la derecha
        if (posX >= 0 && posX < costMap.length -1) {
            State auxRight = new State(posX + 1, posY);
            auxRight.setHeuristica(heuristic.Evaluate(auxRight, targetState, costMap));
            if (costMap[auxRight.getPosY()][auxRight.getPosX()] < 100) {
                sucesores.add(auxRight);
                auxRight.setContOr(OrAct + (5-costMap[auxRight.getPosY()][auxRight.getPosX()]));
                auxRight.setContDias(contDias + 1);
            }
        }

        // izquierda si x es mas pequeño que el max de columnas podemos mirar a la
        // izquierda
        if (posX > costMap.length - 1 && posX != 0) {
            State auxLeft = new State(posX - 1, posY);
            auxLeft.setHeuristica(heuristic.Evaluate(auxLeft, targetState, costMap));
            if (costMap[auxLeft.getPosY()][auxLeft.getPosX()] < 100) {
                sucesores.add(auxLeft);
                auxLeft.setContOr(OrAct + (5-costMap[auxLeft.getPosY()][auxLeft.getPosX()]));
                auxLeft.setContDias(contDias + 1);
            }
        }

        return sucesores;

    }

}
