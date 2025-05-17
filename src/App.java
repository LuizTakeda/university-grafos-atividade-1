import java.util.Map.Entry;

public class App {
  public static void main(String[] args) throws Exception {
    // Cria o grafo a partir de um arquivo no formato GEXF
    Graph graph = new Graph("data/LesMiserables.gexf");

    // Exibe todos os vértices do grafo
    System.out.println("\nGraph");
    for (Vertex vertex : graph.getVertices()) {
      System.out.println(vertex);
    }

    // Calcula a excentricidade de cada vértice do grafo
    Eccentricity eccentricity = new Eccentricity(graph);

    // Exibe a excentricidade de cada vértice
    System.out.println("\nEccentricity");
    for (Entry<Vertex, Integer> item : eccentricity.getValues()) {
      System.out.println(item.getKey().getName() + "=" + item.getValue());
    }

    // Calcula a centralidade de proximidade de cada vértice
    ClosenessCentrality closenessCentrality = new ClosenessCentrality(graph);

    // Exibe a centralidade de proximidade de cada vértice
    System.out.println("\nClosenessCentrality");
    for (Entry<Vertex, Float> item : closenessCentrality.getValues()) {
      System.out.println(item.getKey().getName() + "=" + item.getValue());
    }
  }
}
