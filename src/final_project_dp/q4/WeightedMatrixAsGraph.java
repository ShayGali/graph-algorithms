package final_project_dp.q4;

import final_project_dp.base_classes.Index;
import final_project_dp.base_classes.Node;

import java.util.*;

public class WeightedMatrixAsGraph implements WeightedGraph<Index> {

    private WeightedMatrix innerMatrix;
    private Map<Index, Node<Index>> nodesMap;
    private Index source;

    public WeightedMatrixAsGraph(WeightedMatrix matrix) {
        if (matrix != null) this.innerMatrix = matrix; // אם העבירו לי מטריצה לא ריקה
        else innerMatrix = new WeightedMatrix(); // אם היא לא ריקה אז נעשה מטריצה רנדומאלית
//        source = new Index(0, 0); // נשים את השורש בהתחלה של במטריצה
        setSource();
        setNodesMap();
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

    @Override
    public void InitializeDistancesFromSource(Index source) {
        for (Node<Index> n : this.nodesMap.values()) n.setDist(Integer.MAX_VALUE); //כדי לקרוא לפונקציה כמה פעמים

        Map<Index, Boolean> isVisited = Collections.synchronizedMap(new HashMap<>());
        for (Map.Entry<Index, Node<Index>> entry : this.nodesMap.entrySet())
            isVisited.put(entry.getKey(), false);

        Node<Index> sourceNode = this.getNode(source);
        Stack<Node<Index>> workingStack = new Stack<>();


        sourceNode.setDist(0);
        workingStack.push(sourceNode);
        while (!workingStack.isEmpty()) {
            Node<Index> remove = workingStack.pop();
            isVisited.put(remove.getData(), true);
            Collection<Node<Index>> neighbours = this.getReachableNodes(remove);

            for (Node<Index> neighbor : neighbours) {
                int tempDist = nodesMap.get(neighbor.getData()).getDist();
                int distanceBetweenRemoveTempNodeFromStart = this.getWeight(remove.getData(), neighbor.getData()) + remove.getDist();
                if (tempDist < distanceBetweenRemoveTempNodeFromStart) {
                    neighbor.setDist(tempDist);
                    neighbor.setParent(nodesMap.get(neighbor.getData()).getParents());
                } else {
                    neighbor.setDist(distanceBetweenRemoveTempNodeFromStart);
                    neighbor.setParent(remove);
                }
                nodesMap.put(neighbor.getData(), neighbor);
                if (!isVisited.get(neighbor.getData())) {
                    isVisited.put(neighbor.getData(), true);
                    workingStack.push(neighbor);
                }
            }
        }
    }


    private void setNodesMap() {
        this.nodesMap = Collections.synchronizedMap(new HashMap<>());
        for (int i = 0; i < innerMatrix.primitiveMatrix.length; i++) {
            for (int j = 0; j < innerMatrix.primitiveMatrix[i].length; j++) {
                Index temp = new Index(i, j);
                if (innerMatrix.getValue(temp) > 0)
                    nodesMap.put(temp, new Node<>(temp));
            }
        }
    }

    @Override
    public Map<Index, Node<Index>> getNodesMap() {
        return this.nodesMap;
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

    @Override
    public Node<Index> getNode(Index data) {
        return this.nodesMap.get(data);
    }
}





