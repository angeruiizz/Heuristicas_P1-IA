import java.util.List;
import java.util.Scanner;

public class Main {

  public static char[][] OriginalCharMap = {
      { 'P', 'N', 'N', 'N', 'P', 'P', 'P', 'P', 'P', 'P' },
      { 'P', 'N', 'N', 'N', 'M', 'M', 'P', 'P', 'N', 'P' },
      { 'P', 'N', 'N', 'N', 'M', 'M', 'N', 'N', 'N', 'P' },
      { 'P', 'A', 'A', 'A', 'A', 'A', 'A', 'N', 'N', 'N' },
      { 'P', 'N', 'A', 'C', 'A', 'A', 'A', 'A', 'A', 'N' },
      { 'P', 'A', 'A', 'C', 'M', 'C', 'C', 'A', 'A', 'A' },
      { 'P', 'A', 'M', 'A', 'M', 'M', 'C', 'A', 'A', 'A' },
      { 'A', 'A', 'M', 'A', 'M', 'C', 'C', 'P', 'M', 'P' },
      { 'A', 'A', 'M', 'C', 'M', 'C', 'P', 'P', 'P', 'P' },
      { 'A', 'A', 'C', 'C', 'M', 'C', 'C', 'C', 'C', 'C' },
  };
  public static Map OriginalMap = new Map(OriginalCharMap);

  public static char[][] CustomCharMap = {
      { 'N', 'N', 'N', 'N', 'N' },
      { 'N', 'N', 'N', 'N', 'N' },
      { 'N', 'N', 'N', 'N', 'N' },
      { 'N', 'N', 'N', 'N', 'N' },
      { 'N', 'N', 'N', 'N', 'N' },
  };
  public static Map CustomMap = new Map(CustomCharMap);

  public static void main(String args[]) {
    int tipoCerca = 0;
    int heuristica = 0;

    float[][] mapa = OriginalMap.getCostMap();

    State estatInicial = new State(0, 0);
    State estatFinal = new State(mapa.length - 1, mapa.length - 1);

    // Declare heuristics
    Heuristic[] heuristics = new Heuristic[3];
    heuristics[0] = Heuristics::Heuristic1;
    heuristics[1] = Heuristics::Heuristic2;
    heuristics[2] = Heuristics::Heuristic3;

    Scanner scanner = new Scanner(System.in);

    System.out.println("Hola, selecciona una de los dos tipos de CERCA o ambos:");
    System.out.println("1. Cerca Best First");
    System.out.println("2. Cerca Estrella");
    System.out.println("3. Ambas cerca con las tres heuristicas");

    tipoCerca = scanner.nextInt();

    if (tipoCerca != 3) {
      System.out.println("Ahora elige la heurística:");
      System.out.println("1. Heurística 1");
      System.out.println("2. Heurística 2");
      System.out.println("3. Heurística 3");

      heuristica = scanner.nextInt();
    }

    scanner.close();

    if (tipoCerca == 1) {
      System.out.println("Se va a ejecutar cerca tipo Best First con la heuristica numero " + heuristica);
      cercaBestFirst bestF = new cercaBestFirst(mapa, heuristics[heuristica - 1]);
      List<State> listaBF = bestF.DoSearch(estatInicial, estatFinal);
      System.out.println("\nCamino:");
      for (int j = 0; j < listaBF.size(); j++) {
        // System.out.println(listaBF.get(j).toString());
        System.out.print("(" + listaBF.get(j).getPosX() + "," + listaBF.get(j).getPosY() + ")");
      }

      System.out.println("\nCoste total " + listaBF.get(listaBF.size() - 1).getContOr());

    }

    if (tipoCerca == 2) {
      System.out.println("Se va a ejecutar cerca tipo estrella con la heuristica numero " + heuristica);
      cercaEstrella estrella = new cercaEstrella(mapa, heuristics[heuristica - 1]);
        List<State> listaStar = estrella.DoSearch(estatInicial, estatFinal);
        System.out.println("\nCamino:");
        for (int j = 0; j < listaStar.size(); j++) {
          System.out.print("(" + listaStar.get(j).getPosX() + "," + listaStar.get(j).getPosY() + ")");
        }

        System.out.println("\nCoste total " + listaStar.get(listaStar.size() - 1).getContOr());
    }

    if (tipoCerca == 3) {
      System.out.println("Primero se ejcuta Best First");
      for (int i = 0; i < 3; i++) {
        System.out.println("\nHeurisitica num " + (i + 1));
        cercaBestFirst bestF = new cercaBestFirst(mapa, heuristics[i]);
        List<State> listaBF = bestF.DoSearch(estatInicial, estatFinal);
        System.out.println("Camino:");
        for (int j = 0; j < listaBF.size(); j++) {
          // System.out.println(listaBF.get(j).toString());
          System.out.print("(" + listaBF.get(j).getPosX() + "," + listaBF.get(j).getPosY() + ")");
        }

        System.out.println("\nCoste total " + listaBF.get(listaBF.size() - 1).getContOr());

      }
      System.out.println("Ahora se ejecuta Estrella");
      for (int i = 0; i < 3; i++) {
        System.out.println("\nHeurisitica num " + (i + 1));
        cercaEstrella estrella = new cercaEstrella(mapa, heuristics[i]);
        List<State> listaStar = estrella.DoSearch(estatInicial, estatFinal);
        System.out.println("Camino:");
        for (int j = 0; j < listaStar.size(); j++) {
          System.out.print("(" + listaStar.get(j).getPosX() + "," + listaStar.get(j).getPosY() + ")");
        }

        System.out.println("\nCoste total " + listaStar.get(listaStar.size() - 1).getContOr());
      }
    }

  }
}
