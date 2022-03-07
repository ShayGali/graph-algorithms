package final_project_dp;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class uses the Adapter pattern, also known as Wrapper/Decorator.
 * the class adapts a Matrix type to thr functionality of IGraph interface
 */

public class MatrixAsGraph implements IGraph<Index> {
    private Matrix innerMatrix;
    private Index source;

    public MatrixAsGraph(Matrix matrix) {
        if (matrix != null) this.innerMatrix = matrix; // אם העבירו לי מטריצה לא ריקה
        else innerMatrix = new Matrix(); // אם היא לא ריקה אז נעשה מטריצה רנדומאלית
//        source = new Index(0, 0); // נשים את השורש בהתחלה של במטריצה
        setSource();
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
    public boolean nextNodeInTheGraph(){
        int[][] arr = this.innerMatrix.primitiveMatrix;
        for (int j = source.getColumn()+1; j < arr[source.getRow()].length; j++) {
            if (arr[source.getRow()][j] == 1) {
                source = new Index(source.getRow(), j);
                return true;
            }
        }
        for (int i = source.getRow()+1; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] == 1){
                    source = new Index(i,j);
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public boolean backToRoot(){
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
                if (innerMatrix.getValue(index) == 1) // אם הערך באותו אידקס הוא אחד
                    reachableNodes.add(new Node<>(index, aNode));  // נוסיף אותו בתור node לרשימה של השכנים
            }

            return reachableNodes; // תזחר את אותה רשימה
        }
        return null; // אם זה לא אחד אני אחזיר רשימה ריקה - null
    }


}
