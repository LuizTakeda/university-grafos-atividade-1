import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.HashMap;
import java.util.Set;

public class Graph {

  private HashMap<String, Vertex> vertices;

  /**
   * Cria um grafo a partir de um arquivo GEXF.
   * 
   * @param path Caminho para o arquivo GEXF
   */
  public Graph(String path) {
    this.vertices = new HashMap<>();

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    try {
      File file = new File(path);
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(file);
      doc.getDocumentElement().normalize(); // Optional but good practice

      NodeList nodeList = doc.getElementsByTagName("node");

      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        String id = node.getAttributes().getNamedItem("id").getNodeValue();
        String name = node.getAttributes().getNamedItem("label").getNodeValue();
        this.vertices.put(id, new Vertex(id, name));
      }

      NodeList edgeList = doc.getElementsByTagName("edge");

      for (int i = 0; i < edgeList.getLength(); i++) {
        Node edge = edgeList.item(i);

        String sourceId = edge.getAttributes().getNamedItem("source").getNodeValue();
        String targetId = edge.getAttributes().getNamedItem("target").getNodeValue();

        Vertex sourceVertex = this.vertices.get(sourceId);
        Vertex targetVertex = this.vertices.get(targetId);

        this.vertices.get(sourceId).addEdge(new EdgeTo(targetVertex));
        this.vertices.get(targetId).addEdge(new EdgeTo(sourceVertex));
      }

      this.vertices.forEach((key, vertex) -> {
        System.out.println(vertex);
        System.out.println(vertex.getEdges());
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public int getLength() {
    return this.vertices.size();
  }

  public Vertex getVertex(String id) {
    return this.vertices.get(id);
  }

  public Set<String> getVerticesId(){
    return this.vertices.keySet();
  }
}