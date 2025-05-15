public class EdgeTo {
  private Vertex vertex;

  public EdgeTo(Vertex vertex) {
    this.vertex = vertex;
  }

  public Vertex getVertex() {
    return this.vertex;
  }

   @Override
    public String toString() {
        return this.vertex.toString();
    }
}
