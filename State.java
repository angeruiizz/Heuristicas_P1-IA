
public class State implements Comparable<State>{
    int posX; // Posición actual X
    int posY; // Posición actual Y
    float contOr; // Contador de oro
    int contDias; // Contador de dias
    float heuristica;
    State anterior;



    public State(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        heuristica = 0;
        contOr = 0;
        contDias = 0;
        anterior = null;
    }

    @Override
    public boolean equals(Object other) {
        return (posX == ((State) other).getPosX() && posY == ((State) other).getPosY()); 
    }

    /*
     * @Override
     * public int hashCode() {
     * /* TO DO if using data structures leveraging hash.
     * IMPORTANT: It MUST be coherent with the "equals" method. That is, if two
     * States are equal, they MUST have the same hashcode.
     * However, due to collisions, two States with the same hashcode are not
     * necessarily equal.
     */
    

    // Getters and Setters
    public int getPosX(){
        return posX;
    }

    @Override
    public int compareTo(State o) {
        if(this.getHeuristica() > o.getHeuristica()){
            return 1;
        } else if(this.getHeuristica() < o.getHeuristica()){
            return -1;
        } else {
            return 0;
        }
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public float getContOr() {
        return contOr;
    }

    public void setContOr(float contOr) {
        this.contOr = contOr;
    }

    public int getContDias() {
        return contDias;
    }

    public void setContDias(int contDist) {
        this.contDias = contDist;
    }
    public float getHeuristica() {
        return heuristica;
    }

    public void setHeuristica(float heuristica) {
        this.heuristica = heuristica;
    }

    @Override
    public String toString() {
        return "State [posX=" + posX + ", posY=" + posY + ", contOr=" + contOr + ", contDias=" + contDias
                + ", heuristica=" + heuristica + "]";
    }

    public State getAnterior() {
        return anterior;
    }

    public void setAnterior(State anterior) {
        this.anterior = anterior;
    }
}
