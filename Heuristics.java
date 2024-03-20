public class Heuristics {
    
    //Algoritmo Manhattan 
    public static float Heuristic1(State currentState, State targetState, float[][] map){
        return Math.abs(currentState.getPosX() - targetState.getPosY()) + Math.abs(currentState.getPosY() - targetState.getPosY());
    }

    public static float Heuristic2(State currentState, State targetState, float[][] map){
        float todo = 0;
        /* TODO: Implement a heuristic 
         * You CANNOT change the input parameters and return type.
         * The value returned can ONLY be based on the current state and the target state, NOT intermediate states.
         */
        return todo;
    }

    public static float Heuristic3(State currentState, State targetState, float[][] map){
        /* TODO: Implement a heuristic 
         * You CANNOT change the input parameters and return type.
         * The value returned can ONLY be based on the current state and the target state, NOT intermediate states.
         */
        return 0;
    }
}
