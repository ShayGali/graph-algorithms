package final_project_dp.q4;

import final_project_dp.Index;
import final_project_dp.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WeightedMatrixAsGraph implements WeightedGraph<Index> {

    private WeightedMatrix innerMatrix;
    private Index source;

    public WeightedMatrixAsGraph(WeightedMatrix matrix) {
        if (matrix != null) this.innerMatrix = matrix; // אם העבירו לי מטריצה לא ריקה
        else innerMatrix = new WeightedMatrix(); // אם היא לא ריקה אז נעשה מטריצה רנדומאלית
//        source = new Index(0, 0); // נשים את השורש בהתחלה של במטריצה
        setSource();
    }


    public WeightedMatrix getInnerMatrix() {
        return innerMatrix;
    }

    public Index getSource() {
        return source;
    }


    private void setSource() {
        int[][] arr = this.innerMatrix.primitiveMatrix;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > 0) {
                    source = new Index(i, j);
                    return;
                }
            }
        }
    }

    @Override
    public boolean nextNodeInTheGraph() {
        int[][] arr = this.innerMatrix.primitiveMatrix;
        for (int j = source.getColumn() + 1; j < arr[source.getRow()].length; j++) {
            if (arr[source.getRow()][j] > 1) {
                source = new Index(source.getRow(), j);
                return true;
            }
        }
        for (int i = source.getRow() + 1; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > 1) {
                    source = new Index(i, j);
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public Node<Index> getRoot() {
        return new Node<>(source);
    }

    @Override
    public boolean backToRoot() {
        setSource();
        return true;
    }

    @Override
    public Node<Index> getNode(Index index) {
        return innerMatrix.getValue(index) > 0 ? new Node<>(index) : null;

    }


    @Override
    public Collection<Node<Index>> getReachableNodes(Node<Index> aNode) {
        if (innerMatrix.getValue(aNode.getData()) > 0) { // רק אם הקודוקד שהעבירו לי הערך של האינדקס שלו הוא אחד אני אעשה את שאר הפונקציה
            List<Node<Index>> reachableNodes = new ArrayList<>(); // רשימה שתכיל לי את הרשימה של השכנים ב node
            for (Index index : innerMatrix.getNeighbors(aNode.getData())) {  // עבור כל שכן של אותו אידקס של ה node ששלחו לי
                if (innerMatrix.getValue(index) > 0) { // אם הערך באותו אידקס הוא אחד
                    Node<Index> newNode = new Node<>(index);
                    reachableNodes.add(newNode);  // נוסיף אותו בתור node לרשימה של השכנים
                }
            }
            return reachableNodes; // תזחר את אותה רשימה
        }
        return null; // אם זה לא אחד אני אחזיר רשימה ריקה - null
    }

    @Override
    public Integer getWeight(Index i, Index j) {
        Node<Index> iIndex = new Node<>(i);
        Node<Index> jIndex = new Node<>(j);
        if (getReachableNodes(iIndex).contains(jIndex)) {
            return innerMatrix.getValue(i) + innerMatrix.getValue(j);
        }
        return null;
    }
}





