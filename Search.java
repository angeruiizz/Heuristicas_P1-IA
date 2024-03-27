
import java.util.List;
import java.util.*;

public abstract class Search {
    protected float[][] costMap;
    protected Heuristic heuristic; 

    public Search(float[][] costMap, Heuristic heuristic){
        this.costMap = costMap;
        this.heuristic = heuristic;
    }

    /**
     * Metodo de cerca, esta la clase cercaBestFirst y cercaEstrella la implementación correspondiente
     * @param initialState
     * @param targetState
     * @return
     */
    public List<State> DoSearch(State initialState, State targetState){
         return null; //Devuelvo null porque la implementación esta en cercaBestFirst o cercaEstrella
    }    

    /**
     * Metodo para dar los sucesores dado el estado actual
     * @param currentState Estado actual
     * @param mapa mapa en el que hay que dar los sucesores
     * @return lista de posibles sucesores
     */
    protected List<State> EvaluateOperators(State currentState, State targetState){
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
                    auxUp.setContOr(OrAct + (5-costMap[auxUp.getPosY()][auxUp.getPosX()]));
                    auxUp.setContDias(contDias + 1);
                    sucesores.add(auxUp);
    
                }
            }
    
            // abajo, si Y es mas pequeño que el maximo de filas, podemos mirar abajo
            if (posY < costMap.length - 1 && posY != 0) {
                State auxDown = new State(posX, posY - 1);
                auxDown.setHeuristica(heuristic.Evaluate(auxDown, targetState, costMap));
                if (costMap[auxDown.getPosY()][auxDown.getPosX()] < 100) {
                    auxDown.setContOr(OrAct + (5-costMap[auxDown.getPosY()][auxDown.getPosX()]));
                    auxDown.setContDias(contDias + 1);
                    sucesores.add(auxDown);
                }
            }
    
            // derecha, si X es mas grande que 0, podemos mirar a la derecha
            if (posX >= 0 && posX < costMap.length -1) {
                State auxRight = new State(posX + 1, posY);
                auxRight.setHeuristica(heuristic.Evaluate(auxRight, targetState, costMap));
                if (costMap[auxRight.getPosY()][auxRight.getPosX()] < 100) {
                    auxRight.setContOr(OrAct + (5-costMap[auxRight.getPosY()][auxRight.getPosX()]));
                    auxRight.setContDias(contDias + 1);
                    sucesores.add(auxRight);
                }
            }
    
            // izquierda si x es mas pequeño que el max de columnas podemos mirar a la
            // izquierda
            if (posX > costMap.length - 1 && posX != 0) {
                State auxLeft = new State(posX - 1, posY);
                auxLeft.setHeuristica(heuristic.Evaluate(auxLeft, targetState, costMap));
                if (costMap[auxLeft.getPosY()][auxLeft.getPosX()] < 100) {
                    auxLeft.setContOr(OrAct + (5-costMap[auxLeft.getPosY()][auxLeft.getPosX()]));
                    auxLeft.setContDias(contDias + 1);
                    sucesores.add(auxLeft);
                }
            }
    
            return sucesores;
    
        }
    
}
