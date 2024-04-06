public class Heuristics {

    // Heuristica admisible respecto coste (Uso euclidiana-teorema pitagoras)
    public static float Heuristic1(State currentState, State targetState, float[][] map) {
        float x = currentState.getPosX() - targetState.getPosX();
        float y = currentState.getPosY() - targetState.getPosY();
        return (float) (Math.sqrt(x * x - y * y)*0.5); //hipotenusa * gasto minimo para que sea admisible al costo
    }

    // Heuristica admisible respecto distancia, uso de distancia manhattan
    public static float Heuristic2(State currentState, State targetState, float[][] map) {
        return (Math.abs(currentState.getPosX() - targetState.getPosX())
                + Math.abs(currentState.getPosY() - targetState.getPosY()));
    }

    //Heuristica admisible para distancia y coste
    public static float Heuristic3(State currentState, State targetState, float[][] map) {
        float dist = Math.abs(currentState.getPosX() - targetState.getPosX()) + Math.abs(currentState.getPosY() - targetState.getPosY());
        float cost = map[currentState.getPosX()][currentState.getPosY()]; //Coste de esa posición en mapa
        float costAcu = currentState.getContOr(); //Coste acumulado del esatdo actual
        return dist + cost + costAcu;
    }
}
