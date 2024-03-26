public class Heuristics {

    // Algoritmo Manhattan admisible respecto distancia
    public static float Heuristic1(State currentState, State targetState, float[][] map) {
        return Math.abs(currentState.getPosX() - targetState.getPosX())
                + Math.abs(currentState.getPosY() - targetState.getPosY());
    }

    // Heuristica admisible respecto coste i distancia
    public static float Heuristic2(State currentState, State targetState, float[][] map) {
        return (Math.abs(currentState.getPosX() - targetState.getPosX())
                + Math.abs(currentState.getPosY() - targetState.getPosY())) * 1 / 2;
    }

    //Heuristica admisible para distancia y coste
    public static float Heuristic3(State currentState, State targetState, float[][] map) {
        float distance = Math.abs(currentState.getPosX() - targetState.getPosX()) + Math.abs(currentState.getPosY() - targetState.getPosY());
        float cost = map[currentState.getPosX()][currentState.getPosY()]; //Coste de esa posición
        float costeAcu = currentState.getContOr(); //Coste acumulado
        return distance + cost + costeAcu;

    }
}
