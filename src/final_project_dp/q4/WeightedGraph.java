package final_project_dp.q4;

import final_project_dp.IGraph;

public interface WeightedGraph<T> extends IGraph<T> {
    /**
     * return the weight between two nodes in the graph
     * @param i node 1
     * @param j node 2
     * @return if the node have an edges the function will return the weight.
     * else the function will return null.
     */
    Integer getWeight(T i, T j);
}
