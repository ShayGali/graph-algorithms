package final_project_dp.q1;

import final_project_dp.IGraph;
import final_project_dp.Node;

import java.util.*;

public class DfsConnectedComponents<T> {
    
    private Stack<Node<T>> workingStack; // באיזה קודקודים אני מבקר
    private Set<Node<T>> finished; // סט של מי שאני כבר ביקרתי אצלו בסיבוב הנוכחי
    private Set<Node<T>> visitedVertices; // סט של כל הקודקודים שביקרתי בהם

    public DfsConnectedComponents() {
        workingStack = new Stack<>();
        finished = new LinkedHashSet<>();
        visitedVertices = new LinkedHashSet<>();

    }

    public Set<Set<T>> allConnectedComponents(IGraph<T> someGraph) {
        Set<Set<T>> components = new LinkedHashSet<>(); // the return HashSet

        do {
            if (visitedVertices.contains(someGraph.getRoot())) { // if the current root was visited we continue looking for new root
                continue;
            }

            //DFS
            workingStack.push(someGraph.getRoot()); // add the root of the graph the new root
            while (!workingStack.isEmpty()) { // if the workingStack is empty we're done with explore this components
                Node<T> removed = workingStack.pop();
                finished.add(removed); //enter to the finish set
                Collection<Node<T>> reachableNodes = someGraph.getReachableNodes(removed); // get the neighbors of this node
                for (Node<T> reachableNode : reachableNodes) { // for each neighbor if we not visit him we want to explore him
                    if (!finished.contains(reachableNode) && !workingStack.contains(reachableNode))
                        workingStack.push(reachableNode);
                }
            }
            // when we're done exploring from the root we add the components to the visitedVertices list
            // we want just the data and not the all node, so we put the finished set in new HashSet
            Set<T> blackSet = new LinkedHashSet<>();
            for (Node<T> node : finished) {
                blackSet.add(node.getData());
                visitedVertices.add(node);
            }
            // and add the new HashSet that we get to the components HashSet
            components.add(new LinkedHashSet<>(blackSet));

            // and clear the sets
            blackSet.clear();
            finished.clear();
        } while (someGraph.nextNodeInTheGraph()); // find a new root - if we end the graph it will return false


        someGraph.backToRoot();
        visitedVertices.clear();
        return components; //we return the components
    }
}
