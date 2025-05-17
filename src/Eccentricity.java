import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

/**
 * Excentricidade (Eccentricity)
 *
 * A excentricidade de um vértice `v` é a maior distância entre `v` e qualquer
 * outro vértice que possa ser alcançado a partir dele.
 *
 * Significado: Mede o "alcance" máximo de um vértice no grafo.
 * Cálculo: Para cada vértice, realiza-se uma busca em largura (BFS) para
 * encontrar as distâncias mínimas até todos os demais, e seleciona-se a maior
 * delas.
 */
public class Eccentricity {
  // Grafo utilizado para o cálculo da excentricidade
  private Graph graph;

  // Vetor que armazena os pares (vértice, excentricidade)
  private Vector<Entry<Vertex, Integer>> eccentricityValues;

  // Construtor: inicializa o grafo e calcula a excentricidade dos vértices
  public Eccentricity(Graph graph) {
    this.graph = graph;
    this.eccentricityValues = new Vector<>();

    // Executa o cálculo da excentricidade via BFS
    this.bfs();
  }

  // Método que percorre todos os vértices do grafo e calcula sua excentricidade
  private void bfs() {
    for (Vertex currentVertex : this.graph.getVertices()) {
      int maxDist = 0; // Distância máxima encontrada a partir do vértice atual

      // Conjunto de vértices visitados para evitar ciclos/repetições
      Set<Vertex> visited = new HashSet<>();

      // Fila utilizada para a busca em largura (BFS)
      Queue<Entry<Vertex, Integer>> queue = new LinkedList<>();
      queue.add(Map.entry(currentVertex, maxDist));

      // Enquanto houver vértices na fila
      while (!queue.isEmpty()) {
        Entry<Vertex, Integer> item = queue.poll();

        Vertex vertex = item.getKey();
        int dist = item.getValue();

        // Se já visitou esse vértice, ignora
        if (visited.contains(vertex)) {
          continue;
        }

        visited.add(vertex);

        // Atualiza a maior distância encontrada
        if (dist > maxDist) {
          maxDist = dist;
        }

        // Adiciona os vizinhos à fila com a distância incrementada
        for (EdgeTo edge : vertex.getEdges()) {
          queue.add(Map.entry(edge.getVertex(), dist + 1));
        }
      }

      // Ao final da BFS, armazena a excentricidade do vértice atual
      this.eccentricityValues.add(Map.entry(currentVertex, maxDist));
    }
  }

  // Retorna os valores de excentricidade calculados
  public Vector<Entry<Vertex, Integer>> getValues() {
    return this.eccentricityValues;
  }

  // Representação em String dos resultados
  @Override
  public String toString() {
    return this.eccentricityValues.toString();
  }
}
