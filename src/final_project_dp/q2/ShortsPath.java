package final_project_dp.q2;

import final_project_dp.base_classes.IGraph;
import final_project_dp.base_classes.Index;
import final_project_dp.base_classes.Node;

import java.util.*;
import java.util.stream.Collectors;

public class ShortsPath {


    // לא עובד
    public LinkedList<Queue<Index>> shortsPath(IGraph<Index> graph, Index source, Index destination) {

        LinkedList<Queue<Index>> phatFromSourceToDestination = new LinkedList<>();

        Node<Index> sourceNode = new Node<>(source);
        Node<Index> destinationNode = new Node<>(destination);
        LinkedHashMap<Index, Queue<Index>> map = new LinkedHashMap<>();

        Queue<Node<Index>> workingQ = new LinkedList<>();
        HashSet<Node<Index>> visited = new HashSet<>();
        Queue<Index> path = new LinkedList<>();
        map.put(source, path);
        workingQ.add(sourceNode);

        while (!workingQ.isEmpty()) {
            Node<Index> removed = workingQ.remove();
            visited.add(removed);
            map.get(removed.getData()).add(removed.getData());
            Collection<Node<Index>> neighbors = graph.getReachableNodes(removed);
            for (Node<Index> neighbor : neighbors) {
                if (neighbor.equals(destinationNode)) {
                    map.get(removed.getData()).add(neighbor.getData());
                    phatFromSourceToDestination.add(map.get(removed.getData()));
                }
                if (!visited.contains(neighbor) && !workingQ.contains(neighbor)) {
                    workingQ.add(neighbor);
                    Queue<Index> q = new LinkedList<>(map.get(removed.getData()));
                    map.put(neighbor.getData(), q);
                }
            }

        }
        int minLength = phatFromSourceToDestination.getFirst().size();
        for (Queue<Index> q : phatFromSourceToDestination)
            minLength = Math.min(minLength, q.size());

        LinkedList<Queue<Index>> finalPath = new LinkedList<>();
        for (Queue<Index> q : phatFromSourceToDestination)
            if (q.size() == minLength)
                finalPath.add(q);

        return finalPath;
    }
}
