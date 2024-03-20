
import java.util.List;

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
        return null;
    }
}
