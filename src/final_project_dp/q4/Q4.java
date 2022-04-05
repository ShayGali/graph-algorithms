package final_project_dp.q4;

import final_project_dp.Node;

import java.util.*;

public class Q4<T> {

    Map<Node<T>, Integer> visitedVerticesMap;
    Stack<Node<T>> workingStack;
    Set<Node<T>> finished;

    public Q4() {
        workingStack = new Stack<>();
        visitedVerticesMap = new HashMap<>();
        finished = new HashSet<>();
    }

    Collection<T> func(WeightedGraph<T> graph, T source, T dest) {
        Node<T> sourceNode = new Node<>(source);
        sourceNode.setDist(0);
        Node<T> destNode = new Node<>(dest);

        visitedVerticesMap.put(sourceNode, 0);
        workingStack.push(sourceNode);
        while (!workingStack.isEmpty()) {
            Node<T> remove = workingStack.pop();
            finished.add(remove);
            Collection<Node<T>> neighbours = graph.getReachableNodes(remove);
            for (Node<T> neighbor : neighbours) {
                int tempDist = visitedVerticesMap.getOrDefault(neighbor, neighbor.getDist());
                int distanceBetweenRemoveTempNode = graph.getWeight(remove.getData(), neighbor.getData()) + remove.getDist();
                neighbor.setDist(Math.min(tempDist, distanceBetweenRemoveTempNode));
                visitedVerticesMap.put(neighbor, neighbor.getDist());
                if (!finished.contains(neighbor) && !workingStack.contains(neighbor))
                    workingStack.push(neighbor);
            }
        }

        Integer distanceToDestNode = visitedVerticesMap.get(destNode);
        if (distanceToDestNode == null)
            return null;

        finished.clear();
        List<T> returnList = new LinkedList<>();
        workingStack.push(sourceNode);
        while (!workingStack.isEmpty()) {
            Node<T> remove = workingStack.pop();
            returnList.add(remove.getData());
            finished.add(remove);
            if (remove.equals(destNode)) {
                return returnList;
            }

            Collection<Node<T>> neighbours = graph.getReachableNodes(remove);
            Node<T> selectedNode = new Node<>();
            for (Node<T> neighbor : neighbours) {
                neighbor.setDist(visitedVerticesMap.get(neighbor));
                if (neighbor.equals(destNode)) {
                    selectedNode = neighbor;
                    break;
                }
                if (selectedNode.getDist() >= neighbor.getDist() && !finished.contains(neighbor) && !workingStack.contains(neighbor)) {
                    selectedNode = neighbor;
                }
            }
            if (selectedNode.getDist() <= distanceToDestNode)
                workingStack.push(selectedNode);

        }
        return returnList;
    }
}
