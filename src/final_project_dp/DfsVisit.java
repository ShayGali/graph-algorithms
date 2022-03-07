package final_project_dp;



import java.util.*;

public class DfsVisit<T> {
    /*
         [1,0,1,1]
         [0,1,0,1]

          מחסנית


         סט finished
          (0,0)
          (1,1)
          (0,2)
          (0,3)
          (1,3)
         רשימת שכנים




         דוגמה 2:
         [1,0,1,1]
         [1,1,1,1]
         */
    /*
       V נכניס למחסנית את הקודקוד שממנו מתחילים לטייל
     V ֿכל עוד המחסנית לא ריקה:
       V נוציא את הקודקוד בראש המחסנית ונשמור אותו למשתנה
       V את הקודקוד הזה שהיה בראש המחסנית נכניס לסט של אלה שסיימנו איתם
        V  נקרא למתודה getReachableNodes כדי להבין מיהם הקודקודים שישיגים ממנו
       V עבור כל קודקוד ישיג שגיליתי:
         V   finished אם השכן שלי לא נמצא במחסנית וגם קודקוד ישיג לא נמצא בסט של :
             מכניס את הקודקוד הישיג למחסנית
     */
    private Stack<Node<T>> workingStack;
    private Set<Node<T>> finished;

    public DfsVisit(){
        workingStack = new Stack<>();
        finished = new LinkedHashSet<>();
    }

    public HashSet<T> traverse(IGraph<T> someGraph){
        workingStack.push(someGraph.getRoot());
        while(!workingStack.isEmpty()){
            Node<T> removed = workingStack.pop();
            finished.add(removed);
            Collection<Node<T>> reachableNodes = someGraph.getReachableNodes(removed);
            for(Node<T> reachableNode: reachableNodes){
                if(!finished.contains(reachableNode)
                        && !workingStack.contains(reachableNode)){
                    workingStack.push(reachableNode);
                }
            }
        }
        // להמיר מ set של node ל set של T
        HashSet<T> blackSet = new LinkedHashSet<>();
        for(Node<T> node: finished){
            blackSet.add(node.getData());
        }
        // we only have the finished set. finished:
        finished.clear();
        return blackSet;
    }
}
