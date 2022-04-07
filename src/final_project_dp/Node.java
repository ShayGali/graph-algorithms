package final_project_dp;


import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Node<T> implements Serializable, Comparator<Node<T>> {

    private static final Long serialVersionUID = 1L;

    private T data;
    private Node<T> parent;
    private int dist;


    public Node(T data) {
        this.data = data;
        this.parent = null;
        dist = Integer.MAX_VALUE;
    }

    public Node() {
        this(null);
    }

    public T getData() {
        return data;
    }

    public void setData(@NotNull T data) {
        this.data = data;
    }

    public Node<T> getParents() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }


    @Override
    public int compare(Node<T> o1, Node<T> o2) {
        return (o1.getDist() < o2.getDist()) ? -1 : ((o1.getDist() == o2.getDist()) ? 0 : 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node<?> state1 = (Node<?>) o;
        return Objects.equals(data, state1.data);
    }


    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }


    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    String printParent(){
        if (parent == null)
            return null;
        return parent.getData() + "";
    }
    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", parent=" + printParent() +
                ", dist=" + dist +
                '}';
    }
}

