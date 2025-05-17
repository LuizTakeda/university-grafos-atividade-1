# 📊 Análise de Grafos com GEXF: Eccentricity e Closeness Centrality

Este projeto tem como objetivo **analisar propriedades estruturais de grafos não dirigidos a partir de arquivos no formato GEXF**, com foco no cálculo das estatísticas **Excentricidade** e **Centralidade de Proximidade (Closeness Centrality)**.


## 🎯 Objetivos

* Implementar uma aplicação em Java capaz de:

  * Ler um grafo não dirigido a partir de um arquivo `.gexf` utilizando um parser XML (DOM).
  * Armazenar o grafo em uma estrutura de dados eficiente e ordenada (`TreeMap`).
  * Calcular a **excentricidade** de cada vértice, representando sua distância máxima até outros vértices.
  * Calcular a **centralidade de proximidade normalizada**, indicando o quão central um vértice é na rede.
  * Exibir os resultados via terminal de forma clara e organizada.

Este projeto utiliza algoritmos estudados em aula, com foco no uso de **busca em largura (BFS)** para o cálculo de distâncias mínimas entre vértices.


## 📥 Formato de Entrada: GEXF

O **GEXF (Graph Exchange XML Format)** é um formato baseado em XML amplamente utilizado para representar grafos, permitindo a definição de nós e arestas com atributos como `id`, `label`, `source` e `target`.

📚 Mais informações:

* [https://gexf.net/](https://gexf.net/)
* [https://gexf.net/basic.html](https://gexf.net/basic.html)

Exemplo básico:

```xml
<gexf>
  <graph defaultedgetype="undirected">
    <nodes>
      <node id="1" label="A"/>
      <node id="2" label="B"/>
    </nodes>
    <edges>
      <edge source="1" target="2"/>
    </edges>
  </graph>
</gexf>
```


## 🏗️ Estrutura do Projeto

```
📦 projeto/
├── 📁 data/
│   └── LesMiserables.gexf     → Arquivo de entrada
├── 📁 src/
│   ├── Graph.java              → Parser GEXF e estrutura do grafo
│   ├── Vertex.java             → Representação dos vértices
│   ├── EdgeTo.java             → Representação das arestas
│   ├── Eccentricity.java       → Algoritmo de excentricidade
│   ├── ClosenessCentrality.java→ Algoritmo de centralidade
│   └── App.java                → Classe principal (main)
├── 📁 lib/                     → Bibliotecas externas (se houver)
├── 📁 bin/                     → Classes compiladas
└── README.md                  → Este documento
```


## 🧮 Estatísticas Calculadas

### 📏 Excentricidade (Eccentricity)

> A **excentricidade** de um vértice `v` é a maior distância entre `v` e qualquer outro vértice que possa ser alcançado a partir dele.

* **Significado**: Mede o "alcance" máximo de um vértice no grafo.
* **Cálculo**: Para cada vértice, realiza-se uma busca em largura (BFS) para encontrar as distâncias mínimas até todos os demais, e seleciona-se a maior delas.

### 🌐 Centralidade de Proximidade (Closeness Centrality)

> A **closeness centrality** mede a proximidade média de um vértice em relação a todos os outros vértices alcançáveis.

* **Fórmula Normalizada**:

  $$
  C(v) = \frac{N - 1}{\sum_{u \neq v} d(v, u)}
  $$

  Onde `N` é o número total de vértices alcançáveis e `d(v, u)` é a menor distância de `v` até `u`.

* **Interpretação**: Quanto maior o valor, mais "central" é o vértice na rede.

* **Cálculo**: Para cada vértice, é realizada uma BFS para somar as distâncias mínimas até os demais.


## ⚙️ Execução

### Compilação e execução via terminal:

```bash
javac -d bin src/*.java
java -cp bin App
```

Ou utilize um ambiente com suporte a Java, como o **VS Code** com extensão Java.


## 📤 Saída Esperada

```
Eccentricity:
A=2
B=2
C=3

Closeness Centrality:
A=0.66
B=0.75
C=0.60
```


## 🧠 Contribuições para o Aprendizado

Este projeto reforça os seguintes conceitos:

* Leitura e interpretação de grafos a partir de arquivos padronizados (GEXF/XML).
* Implementação de estruturas de dados para grafos não dirigidos.
* Uso de algoritmos de busca em largura (BFS) para análise de redes.
* Cálculo de métricas clássicas de centralidade e sua interpretação em redes reais.
* Modularidade e boas práticas em desenvolvimento orientado a objetos com Java.
