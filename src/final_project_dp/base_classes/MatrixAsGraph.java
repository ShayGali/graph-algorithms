package final_project_dp.base_classes;


import java.util.*;

/**
 * This class uses the Adapter pattern, also known as Wrapper/Decorator.
 * the class adapts a Matrix type to thr functionality of IGraph interface
 */

public class MatrixAsGraph implements IGraph<Index> {
    private Matrix innerMatrix;
    private Index source;
    private Map<Index, Node<Index>> nodesMap;


    public MatrixAsGraph(Matrix matrix) {
        if (matrix != null) this.innerMatrix = matrix; // אם העבירו לי מטריצה לא ריקה
        else innerMatrix = new Matrix(); // אם היא לא ריקה אז נעשה מטריצה רנדומאלית
//        source = new Index(0, 0); // נשים את השורש בהתחלה של במטריצה
        setSource();
        setNodesMap();
    }

    public Matrix getInnerMatrix() {
        return innerMatrix;
    }

    public Index getSource() {
        return source;
    }

    private void setSource() {
        int[][] arr = this.innerMatrix.primitiveMatrix;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
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
            if (arr[source.getRow()][j] == 1) {
                source = new Index(source.getRow(), j);
                return true;
            }
        }
        for (int i = source.getRow() + 1; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    source = new Index(i, j);
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public boolean backToRoot() {
        setSource();
        return true;
    }

    @Override
    public Node<Index> getNode(Index index) {
        return innerMatrix.getValue(index) == 1 ? new Node<>(index) : null;
    }

    @Override
    public Node<Index> getRoot() {
        return new Node<>(source);
    }

    @Override
    public Collection<Node<Index>> getReachableNodes(Node<Index> aNode) {
        if (innerMatrix.getValue(aNode.getData()) == 1) { // רק אם הקודוקד שהעבירו לי הערך של האינדקס שלו הוא אחד אני אעשה את שאר הפונקציה
            List<Node<Index>> reachableNodes = new ArrayList<>(); // רשימה שתכיל לי את הרשימה של השכנים ב node
            for (Index index : innerMatrix.getNeighbors(aNode.getData())) {  // עבור כל שכן של אותו אידקס של ה node ששלחו לי
                if (innerMatrix.getValue(index) == 1) { // אם הערך באותו אידקס הוא אחד
                    Node<Index> newNode = new Node<>(index);
                    reachableNodes.add(newNode);  // נוסיף אותו בתור node לרשימה של השכנים
                }
            }

            return reachableNodes; // תזחר את אותה רשימה
        }
        return null; // אם זה לא אחד אני אחזיר רשימה ריקה - null
    }

    @Override
    public Map<Index, Node<Index>> getNodesMap() {
        return this.nodesMap;
    }


    public void setNodesMap() {
        this.nodesMap = Collections.synchronizedMap(new HashMap<>());
        for (int i = 0; i < innerMatrix.primitiveMatrix.length; i++) {
            for (int j = 0; j < innerMatrix.primitiveMatrix[i].length; j++) {
                Index temp = new Index(i, j);
                if (innerMatrix.getValue(temp) == 1)
                    nodesMap.put(temp, new Node<>(temp));
            }
        }
    }

    @Override
    public boolean isReachable(Index n1, Index n2) {
        Map<Index, Boolean> isVisited = Collections.synchronizedMap(new HashMap<>());
        for (Map.Entry<Index, Node<Index>> entry : this.nodesMap.entrySet())
            isVisited.put(entry.getKey(), false);

        Queue<Index> workingQ = new LinkedList<>();
        workingQ.add(n1);
        while (!workingQ.isEmpty()) {
            Index removed = workingQ.remove();
            isVisited.put(removed, true);
            Collection<Node<Index>> neighbors = this.getReachableNodes(new Node<>(removed));
            for (Node<Index> neighbor : neighbors) {
                Index neighborIndex = neighbor.getData();
                if (neighborIndex.equals(n2))
                    return true;
                if (!isVisited.get(neighborIndex)) {
                    isVisited.put(neighborIndex, true);
                    workingQ.add(neighborIndex);
                }
            }
        }
        return false;
    }

}
