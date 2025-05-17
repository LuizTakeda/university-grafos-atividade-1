import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

/**
 * Centralidade de Proximidade (Closeness Centrality)
 * 
 * A closeness centrality mede a proximidade média de um vértice em
 * relação a todos os outros vértices alcançáveis.
 * 
 * Fórmula Normalizada: C(v) = N - 1/ sum(d(v, ui))
 * 
 * Onde `N` é o número total de vértices alcançáveis e `d(v, u)` é a menor
 * distância de `v` até `u`.
 * 
 * **Interpretação**: Quanto maior o valor, mais "central" é o vértice na rede.
 * 
 * **Cálculo**: Para cada vértice, é realizada uma BFS para somar as distâncias
 * mínimas até os demais.
 * 
 */
public class ClosenessCentrality {
  // Grafo onde a centralidade será calculada
  private Graph graph;

  // Vetor que armazena os valores de centralidade de proximidade de cada vértice
  private Vector<Entry<Vertex, Float>> closenessCentralityValues;

  // Construtor: inicializa o grafo e calcula os valores de centralidade
  public ClosenessCentrality(Graph graph) {
    this.graph = graph;
    this.closenessCentralityValues = new Vector<>();

    // Inicia o cálculo de centralidade para todos os vértices
    this.bfs();
  }

  // Método que realiza a busca em largura (BFS) a partir de cada vértice
  public void bfs() {
    for (Vertex currentVertex : this.graph.getVertices()) {
      int totalDist = 0; // Soma total das distâncias até os outros vértices

      // Conjunto de vértices já visitados
      Set<Vertex> verticesVisited = new HashSet<>();

      // Fila usada na BFS, armazena pares (vértice, distância)
      Queue<Map.Entry<Vertex, Integer>> queue = new LinkedList<>();
      queue.add(Map.entry(currentVertex, totalDist));

      int numberOfReachableVertices = 0; // Conta quantos vértices foram alcançados

      // Loop da BFS
      while (!queue.isEmpty()) {
        Map.Entry<Vertex, Integer> item = queue.poll();

        Vertex vertex = item.getKey();
        int dist = item.getValue();

        // Se o vértice já foi visitado, pula para o próximo
        if (verticesVisited.contains(vertex)) {
          continue;
        }

        numberOfReachableVertices++;
        verticesVisited.add(vertex);

        // Adiciona os vizinhos do vértice atual na fila com a distância incrementada
        for (EdgeTo edge : vertex.getEdges()) {
          queue.add(Map.entry(edge.getVertex(), dist + 1));
        }

        totalDist += dist;
      }

      // Se não houverem vértices alcançáveis, a centralidade é 0
      if (totalDist == 0) {
        this.closenessCentralityValues.add(Map.entry(currentVertex, 0f));
      } else {
        // Calcula a centralidade de proximidade: (n - 1) / soma das distâncias
        this.closenessCentralityValues
            .add(Map.entry(currentVertex, (numberOfReachableVertices - 1) / (float) totalDist));
      }
    }
  }

  // Retorna os valores de centralidade de proximidade calculados
  public Vector<Entry<Vertex, Float>> getValues() {
    return this.closenessCentralityValues;
  }

  // Retorna uma representação em string dos valores calculados
  @Override
  public String toString() {
    return this.closenessCentralityValues.toString();
  }
}
