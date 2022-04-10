package final_project_dp.q4;

import final_project_dp.base_classes.Node;

import java.util.*;


public class Q4<T> {

    Collection<T> dijkstraAlgorithm(WeightedGraph<T> graph, T source, T dest) {
        if (!graph.isReachable(source, dest)) return null;
        graph.InitializeDistancesFromSource(source);
        Map<T, Node<T>> nodesMap = graph.getNodesMap();
        Node<T> destinationNode = nodesMap.get(dest);
        List<T> path = new LinkedList<>();
        Node<T> temp = destinationNode;
        while (temp != null) {
            path.add(temp.getData());
            temp = temp.getParents();
        }
        Collections.reverse(path);
        return path;
    }
}
