package final_project_dp;




import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node<T> implements Serializable {

    private static final Long serialVersionUID = 1L;

    private T data;
    private List<Node<T>> parents;
    private int dist;



    public Node(T data){
        this.data = data;
        this.parents = new ArrayList<>();
    }

    public Node(){

    }

    public T getData() {
        return data;
    }

    public void setData(@NotNull T data) {
        this.data = data;
    }

    public List<Node<T>> getParents() {
        return parents;
    }

    public void setParent(@NotNull List<Node<T>> parents) {
        this.parents = parents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node<?> state1 = (Node<?>) o;
        return Objects.equals(data,state1.data);
    }

    /*
    question ? ifTrue : ifFalse
     */
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

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", parents=" + parents +
                ", dist=" + dist +
                '}';
    }
}

