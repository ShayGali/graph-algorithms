package final_project_dp;



import java.util.Collection;
import java.util.Map;

/**
 * This interface declares the common functionality required of a Graph
 */
public interface IGraph<T> {

    /**
     * get the root of the graph
     * @return the root node of the graph
     */
    Node<T> getRoot();

    /**
     * if we want to go to the next node in the graph
     * @return If the operation was successful
     */
    boolean nextNodeInTheGraph();

    /**
     * if we want to go back to the root node
     * @return If the operation was successful
     */
    boolean backToRoot();

    /**
     * get an index and return it Node on the graph
     * @param index thr index of the node
     * @return the node if he exists else null
     */
    Node<T> getNode(T index);

    /**
     * get all the reachable nodes from a node
     * @param aNode the node
     * @return a Collection of the reachable nodes from the node
     */
    Collection<Node<T>>  getReachableNodes(Node<T> aNode);

    /**
     * get map of all nodes on the graph with generic value as key.
     * @return map of all nodes on the graph with generic value as key
     */
    <K> Map<K, Node<T>> getNodesMap();

    /**
     * check if two nodes are connected
     * @param index1 the index of the fires node
     * @param index2 the index of the second node
     * @return true if they are connected, else false
     */
    boolean isReachable(T index1, T index2);
}
