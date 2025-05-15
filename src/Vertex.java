import java.util.ArrayList;

public class Vertex {

  private ArrayList<EdgeTo> edges;

  public Vertex(ArrayList<EdgeTo> edges) {
    this.edges = edges;
  }

  public Vertex() {
    this.edges = new ArrayList<>();
  }

  public void addEdge(EdgeTo edge) {
    this.edges.add(edge);
  }

  public EdgeTo getEdge(int index) {
    return this.edges.get(index);
  }

  public int getSize() {
    return this.edges.size();
  }
}
