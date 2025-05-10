import org.gephi.graph.api.*;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.importer.api.*;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.preview.api.*;
import org.gephi.project.api.*;
import org.gephi.statistics.plugin.GraphDistance;
import org.openide.util.Lookup;
import org.gephi.preview.types.DependantOriginalColor;
import org.gephi.preview.types.EdgeColor;

import java.io.File;

public class App {
  public static void main(String[] args) throws Exception {
    try {
      // Iniciando projeto
      ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
      pc.newProject();
      Workspace workspace = pc.getCurrentWorkspace();

      // Importando dados do grafo
      ImportController importController = Lookup.getDefault().lookup(ImportController.class);
      File file = new File("data/LesMiserables.gexf");
      Container container = importController.importFile(file);
      importController.process(container, new DefaultProcessor(), workspace);

      // Calculando Excentricidade e Closeness
      GraphDistance distance = new GraphDistance();
      distance.setDirected(false); // se o grafo for direcionado, mude para true
      GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace);
      distance.execute(graphModel);

      // Imprime os vértices
      Graph graph = graphModel.getGraph();
      System.out.println("Vértices ");
      for (Node node : graph.getNodes()) {
        System.out.println("(" + node.getLabel() + ")");
      }

      // Itera pelos nós e imprime as métricas
      System.out.println("Resultados:");
      for (Node node : graph.getNodes()) {
        double excentricidade = (Double) node.getAttribute(GraphDistance.ECCENTRICITY);
        double closeness = (Double) node.getAttribute(GraphDistance.CLOSENESS);
        System.out
            .println("Vértice (" + node.getLabel() + ") Excentricidade: " + excentricidade + ", Closeness: " + closeness);
      }

      // Configura visualização
      PreviewController previewController = Lookup.getDefault().lookup(PreviewController.class);
      PreviewModel model = previewController.getModel();
      model.getProperties().putValue(PreviewProperty.SHOW_NODE_LABELS, Boolean.TRUE);
      model.getProperties().putValue(PreviewProperty.EDGE_COLOR, new EdgeColor(EdgeColor.Mode.ORIGINAL));
      model.getProperties().putValue(PreviewProperty.NODE_LABEL_COLOR,
          new DependantOriginalColor(java.awt.Color.BLACK));
      model.getProperties().putValue(PreviewProperty.BACKGROUND_COLOR, java.awt.Color.WHITE);

      previewController.refreshPreview();

      // Exporta como PNG o grafo
      ExportController exportController = Lookup.getDefault().lookup(ExportController.class);
      exportController.exportFile(new File("grafo.png"));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
