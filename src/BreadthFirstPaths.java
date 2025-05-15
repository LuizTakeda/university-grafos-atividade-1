import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstPaths {
  private Graph graph;

  public BreadthFirstPaths(Graph graph) {
    this.graph = graph;
  }

  public HashMap<String, Integer> eccentricity() {
    // Mapa que armazenará o valor da excentricidade de cada vértice
    HashMap<String, Integer> result = new HashMap<>();

    // Conjunto com os IDs de todos os vértices do grafo
    Set<String> verticesId = this.graph.getVerticesId();

    // Para cada vértice do grafo, calculamos sua excentricidade
    for (String targetVertexId : verticesId) {
      // Mapa que indica se um vértice já foi visitado
      HashMap<String, Boolean> visit = new HashMap<>();
      for (String id : verticesId) {
        visit.put(id, false); // Inicialmente, nenhum vértice foi visitado
      }

      // Fila com os vértices a serem visitados na próxima camada da BFS
      Queue<Vertex> queueNextLayer = new LinkedList<>();
      queueNextLayer.add(this.graph.getVertex(targetVertexId));

      int eccentricity = 0; // Contador de camadas da BFS (excentricidade)

      // Executa enquanto houver vértices para visitar
      while (!queueNextLayer.isEmpty()) {
        // Copia a fila atual e limpa a fila da próxima camada
        Queue<Vertex> queueCurrentLayer = new LinkedList<>(queueNextLayer);
        queueNextLayer.clear();

        // Processa todos os vértices da camada atual
        while (!queueCurrentLayer.isEmpty()) {
          Vertex vertex = queueCurrentLayer.poll();

          // Pula se o vértice já foi visitado
          if (visit.get(vertex.getId()))
            continue;

          // Marca o vértice como visitado
          visit.put(vertex.getId(), true);

          // Adiciona os vizinhos não visitados à próxima camada
          for (EdgeTo edge : vertex.getEdges()) {
            Vertex neighbor = edge.getVertex();
            if (!visit.get(neighbor.getId())) {
              queueNextLayer.add(neighbor);
            }
          }
        }

        // Se houver vértices na próxima camada, incrementa a excentricidade
        if (!queueNextLayer.isEmpty()) {
          eccentricity++;
        }
      }

      // Armazena a excentricidade calculada para o vértice
      result.put(targetVertexId, eccentricity);
    }

    // Retorna o mapa com as excentricidades de todos os vértices
    return result;
  }
}
