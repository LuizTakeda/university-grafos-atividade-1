public class App {
  public static void main(String[] args) throws Exception {
    Graph graph = new Graph("data/LesMiserables.gexf");

    BreadthFirstPaths bfs = new BreadthFirstPaths(graph);

    System.out.println("\nEccentricity");
    System.out.println(bfs.eccentricity());

    System.out.println("\nClosenessCentrality");
    System.out.println(bfs.closenessCentrality());
  }
}
