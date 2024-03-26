public class Heuristics {

    // Heuristica admisible respecto distancia (Uso euclidiana-teorema pitagoras)
    public static float Heuristic1(State currentState, State targetState, float[][] map) {
        float x = currentState.getPosX() - targetState.getPosX();
        float y = currentState.getPosY() - targetState.getPosY();
        return (float) Math.sqrt(x * x + y * y);
    }

    // Heuristica admisible y optima respecto coste i distancia (uso de Manhattan + coste)
    public static float Heuristic2(State currentState, State targetState, float[][] map) {
        return (Math.abs(currentState.getPosX() - targetState.getPosX())
                + Math.abs(currentState.getPosY() - targetState.getPosY())) + 1 / 2; // 1/2 sera siempre el beneficio minimo 
    }

    //Heuristica admisible para distancia y coste
    public static float Heuristic3(State currentState, State targetState, float[][] map) {
        float dist = Math.abs(currentState.getPosX() - targetState.getPosX()) + Math.abs(currentState.getPosY() - targetState.getPosY());
        float cost = map[currentState.getPosX()][currentState.getPosY()]; //Coste de esa posición en mapa
        float costAcu = currentState.getContOr(); //Coste acumulado del esatdo actual
        return dist + cost + costAcu;

    }
}
