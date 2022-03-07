package final_project_dp;



import java.util.Collection;

/**
 * This interface declares the common functionality required of a Graph
 */
public interface IGraph<T> {
    Node<T> getRoot();
    boolean nextNodeInTheGraph();
    boolean backToRoot();
    Node<T> getNode(T index);
    Collection<Node<T>>  getReachableNodes(Node<T> aNode);
}
