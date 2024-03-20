public class Main {

    public static char[][] OriginalCharMap = {
      {'P','N','N','N','P','P','P','P','P','P'},
      {'P','N','N','N','M','M','P','P','N','P'},
      {'P','N','N','N','M','M','N','N','N','P'},
      {'P','A','A','A','A','A','A','N','N','N'},
      {'P','N','A','C','A','A','A','A','A','N'},
      {'P','A','A','C','M','C','C','A','A','A'},
      {'P','A','M','A','M','M','C','A','A','A'},
      {'A','A','M','A','M','C','C','P','M','P'},
      {'A','A','M','C','M','C','P','P','P','P'},
      {'A','A','C','C','M','C','C','C','C','C'},
    };
    public static Map OriginalMap = new Map(OriginalCharMap);

    public static char[][] CustomCharMap = {
      {'N','N','N','N','N'},
      {'N','N','N','N','N'},
      {'N','N','N','N','N'},
      {'N','N','N','N','N'},
      {'N','N','N','N','N'},
    };
    public static Map CustomMap = new Map(CustomCharMap);

    public static void main(String args[]){      

      // TODO: Declare map
      float[][] mapa = OriginalMap.getCostMap();
      // TODO: Declare initial and target states
      State estatInicial = new State(0, 0);
      State estatFinal = new State(mapa.length-1, mapa.length-1);

      // Declare heuristics
      Heuristic[] heuristics = new Heuristic[3];
      heuristics[0] = Heuristics::Heuristic1;
      heuristics[1] = Heuristics::Heuristic2;
      heuristics[2] = Heuristics::Heuristic3;

      // TODO: Declare search algorithms (if desired, you can move this under "Run experiments")
      cercaBestFirst bestF = new cercaBestFirst(mapa, heuristics[0]);
      // TODO: Run experiments
      bestF.DoSearch(estatInicial, estatFinal);
      // TODO: Show results
      
    }
}



