import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;

public class Graph {

  private ArrayList<Vertex> vertexes;

  /**
   * Cria um grafo apartir de um arquivo gexf
   * 
   * @param path Caminho para o arquivo gexf
   */
  public Graph(String path) {
    this.vertexes = new ArrayList<>();

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    try {
      File file = new File(path);
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(file);
      NodeList nodeList = doc.getElementsByTagName("node");
      NodeList edgeList = doc.getElementsByTagName("edge");

      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        String id = node.getAttributes().getNamedItem("id").getNodeValue();
        String label = node.getAttributes().getNamedItem("label").getNodeValue();

        System.out.println(id + " " + label);

        for (int t = 0; t < edgeList.getLength(); t++) {
          Node edge = edgeList.item(t);

          String source = edge.getAttributes().getNamedItem("source").getNodeValue();
          String target = edge.getAttributes().getNamedItem("target").getNodeValue();

          if(source.equals(id)){
            System.out.println(source + " " + target);
          }
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}