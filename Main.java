import java.util.ArrayList;
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
      { 'N', 'P', 'N', 'N', 'N' },
      { 'A', 'C', 'M', 'A', 'N' },
      { 'N', 'M', 'M', 'C', 'C' },
      { 'N', 'C', 'N', 'N', 'C' },
      { 'N', 'N', 'P', 'C', 'N' },
  };
  public static Map CustomMap = new Map(CustomCharMap);

  public static void main(String args[]) {
    int tipoCerca = 0;
    int heuristica = 0;
    int tipoMapa = 0;

    float[][] mapa1 = OriginalMap.getCostMap();
    float[][] mapa2 = CustomMap.getCostMap();

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

    System.out.println("Que mapa prefieres? ");
    System.out.println("1. Original");
    System.out.println("2. Custom");

    tipoMapa = scanner.nextInt();
    scanner.close();

    float[][] mapa = null;
    if (tipoMapa == 1) {
      mapa = mapa1;
    } else {
      mapa = mapa2;
    }

    State estatInicial = new State(0, 0);
    State estatFinal = new State(mapa.length - 1, mapa.length - 1);

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
      System.out.println("Duracion del trayecto " + listaBF.get(listaBF.size() - 1).getContDias() + " días");

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
      System.out.println("Duracion del trayecto " + listaStar.get(listaStar.size() - 1).getContDias() + " días");
    }

    if (tipoCerca == 3) {
      ArrayList<Float> coste = new ArrayList<>();
      ArrayList<Integer> dias = new ArrayList<>();
      System.out.println();

      System.out.println("RESULTADOS CAMINO MODO RESUMEN");

      System.out.println("Resultados de Best First:");
      System.out.println("Heurística\tCamino");

      for (int i = 0; i < 3; i++) {
        System.out.print("Heurística " + (i + 1) + "\t\t");

        cercaBestFirst bestF = new cercaBestFirst(mapa, heuristics[i]);
        List<State> listaBF = bestF.DoSearch(estatInicial, estatFinal);

        // Imprimir el camino
        for (State state : listaBF) {
          System.out.print("(" + state.getPosX() + "," + state.getPosY() + ") ");
        }

        System.out.println();

        coste.add(listaBF.get(listaBF.size() - 1).getContOr());
        dias.add(listaBF.get(listaBF.size() - 1).getContDias());

      }

      System.out.println("\nResultados de A*:");
      System.out.println("Heurística\tCamino");
      for (int i = 0; i < 3; i++) {
        System.out.print("Heurística " + (i + 1) + "\t\t");

        cercaEstrella estrella = new cercaEstrella(mapa, heuristics[i]);
        List<State> listaStar = estrella.DoSearch(estatInicial, estatFinal);

        for (State state : listaStar) {
          System.out.print("(" + state.getPosX() + "," + state.getPosY() + ") ");
        }

        System.out.println();
        coste.add(listaStar.get(listaStar.size() - 1).getContOr());
        dias.add(listaStar.get(listaStar.size() - 1).getContDias());
      }

      System.out.println("\nResumen COSTE y DIAS (=estados tratados)");
      System.out.println("BESTFIRST");
      System.out.println("Heurística\tCoste\tDías");

      for (int i = 0; i < 3; i++) {
        System.out.print("Heurística " + (i + 1) + "\t");
        System.out.println(coste.get(i) + "\t" + dias.get(i));
      }

      System.out.println("A*");
      System.out.println("Heurística\tCoste\tDías");

      int j = 0;
      for (int i = 3; i < 6; i++) {
        System.out.print("Heurística " + (j) + "\t");
        System.out.println(coste.get(i) + "\t" + dias.get(i));
        j++;
      }
    }
  }
}
