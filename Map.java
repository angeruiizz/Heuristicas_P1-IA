public class Map {
    private char[][] charMap;
    private float[][] costMap;

    public Map(char[][] charMap){
        this.charMap = charMap;
        costMap = generateCostMap();
    }
    
    public char[][] getCharMap(){
        return charMap;
    }

    public float[][] getCostMap(){
        return costMap;
    }

    private float[][] generateCostMap(){
        float[][] costMap = new float[charMap.length][charMap[0].length];        
        
        float cost = -1;
        for(int row=0; row < charMap.length; row++){
            for(int col=0; col < charMap[0].length; col++){
                switch(charMap[row][col]){
                    case 'M': cost=10000; //cost corresponding to "Mountain" ("Muntanya"), pongo valor muy alto para que esta casilla no se tenga en cuenta en las heuristicas
                    break;
                    case 'N': cost= 0; //cost corresponding to "Empty" ("Buit")
                    break;
                    case 'A': cost= 1; //cost corresponding to "Village" ("Aldea")
                    break;
                    case 'P': cost= 3;  //cost corresponding to "Town" ("Poble")
                    break;
                    case 'C': cost= 4.5f; //cost corresponding to "City" ("Ciutat")
                    break;
                }
                costMap[row][col] = cost;
            }
        }

        return costMap;
    }
    public float getCostOfCell(int row, int col) {
        if (row >= 0 && row < costMap.length && col >= 0 && col < costMap[0].length) {
            return costMap[row][col];
        } else {
            throw new IllegalArgumentException("Row or column index out of bounds.");
        }
    }
    public char getCharOfCell (int row, int col){
        if (row >= 0 && row < charMap.length && col >= 0 && col < charMap[0].length) {
            return charMap[row][col];
        } else {
            throw new IllegalArgumentException("Row or column index out of bounds.");
        }
    }


    public String toString(){
        String text = "";

        for (char[] row : charMap) {
            for (char cell : row){
                text+=cell+" ";
            }
            text+="\n";
        }

        // Remove last enter
        text = text.substring(0, text.length()-1);

        return text;
    }
}
