import java.util.ArrayList;

public class Vertex {

  private ArrayList<EdgeTo> edges;
  private String id;
  private String name;

  public Vertex(String id, String name, ArrayList<EdgeTo> edges) {
    this.edges = edges;
    this.id = id;
    this.name = name;
  }

  public Vertex(String id, String name) {
    this.edges = new ArrayList<>();
    this.id = id;
    this.name = name;
  }

  public void addEdge(EdgeTo edge) {
    this.edges.add(edge);
  }

  public EdgeTo getEdge(int index) {
    return this.edges.get(index);
  }

  public ArrayList<EdgeTo> getEdges() {
    return this.edges;
  }

  public String getName(){
    return this.name;
  }

  @Override
  public String toString() {
    return this.name + "->" + this.edges.toString();
  }
}
