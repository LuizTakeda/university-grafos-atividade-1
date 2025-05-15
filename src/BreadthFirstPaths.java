import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;

public class BreadthFirstPaths {
  private Graph graph;

  public BreadthFirstPaths(Graph graph) {
    this.graph = graph;
  }

  public HashMap<String, Integer> eccentricity() {
    HashMap<String, Integer> result = new HashMap<>();

    Set<String> verticesId = this.graph.getVerticesId();

    for (String targetVerticeId : verticesId) {
      int max_dist = 0;
      Set<Vertex> visited = new HashSet<>();

      Queue<Entry<Vertex, Integer>> queue = new LinkedList<>();
      queue.add(Map.entry(this.graph.getVertex(targetVerticeId), max_dist));

      while (!queue.isEmpty()) {
        Entry<Vertex, Integer> currentVertexDist = queue.poll();

        Vertex vertex = currentVertexDist.getKey();
        int dist = currentVertexDist.getValue();

        if (visited.contains(vertex)) {
          continue;
        }

        visited.add(vertex);

        if (dist > max_dist) {
          max_dist = dist;
        }

        for (EdgeTo edge : vertex.getEdges()) {
          Vertex vertex_neighbor = edge.getVertex();

          if (visited.contains(vertex_neighbor)) {
            continue;
          }

          queue.add(Map.entry(vertex_neighbor, dist + 1));
        }
      }

      result.put(this.graph.getVertex(targetVerticeId).toString(), max_dist);
    }

    return result;
  }
}