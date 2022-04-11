package final_project_dp.q2;

import final_project_dp.base_classes.IGraph;
import final_project_dp.base_classes.Index;
import final_project_dp.base_classes.Node;

import java.util.*;
import java.util.stream.Collectors;

public class ShortsPath {


    public LinkedList<Queue<Index>> shortsPath(IGraph<Index> graph, Index source, Index destination) {

        // רשימה של כל המסלולים מהמקור ליעד
        LinkedList<Queue<Index>> phatFromSourceToDestination = new LinkedList<>();

        // שמים את המקור והיעד בתוך node
        Node<Index> sourceNode = new Node<>(source);
        Node<Index> destinationNode = new Node<>(destination);

        // מפה שמכילה את המסלול לפי כל אינדקס
        LinkedHashMap<Index, Queue<Index>> map = new LinkedHashMap<>();

        Queue<Node<Index>> workingQ = new LinkedList<>();
        HashSet<Node<Index>> visited = new HashSet<>();

        // תור בשביל הצומת יעד
        Queue<Index> path = new LinkedList<>();
        map.put(source, path);

        workingQ.add(sourceNode);

        while (!workingQ.isEmpty()) {
            Node<Index> removed = workingQ.remove(); // מוציא מראש המחסנית
            visited.add(removed); // מסמן שביקרתי בצומת
            map.get(removed.getData()).add(removed.getData()); // שמים בתוך התור של ה remove את עצמו

            Collection<Node<Index>> neighbors = graph.getReachableNodes(removed);
            for (Node<Index> neighbor : neighbors) {
                if (neighbor.equals(destinationNode)) { // אם השכן הוא היעד
                    map.get(removed.getData()).add(neighbor.getData());// אני מוסיף אותו למסלול
                    phatFromSourceToDestination.add(map.get(removed.getData())); // ואני מוסיף אותו לרשימה של המסלולים
                }
                if (!visited.contains(neighbor) && !workingQ.contains(neighbor)) { // אם לא ביקרתי בו עדיין
                    workingQ.add(neighbor); // מוסיף אותו לרישמה
                    // להוסיף את המסלול למפה
                    Queue<Index> q = new LinkedList<>(map.get(removed.getData()));
                    map.put(neighbor.getData(), q);
                }
            }

        }

        // צריך מכל המסלולים לפלטר את הכי קצרים
        int minLength = phatFromSourceToDestination.getFirst().size(); // הגודל של המסלול הכי קצר
        // למצוא את הכוגל שלו
        for (Queue<Index> q : phatFromSourceToDestination)
            minLength = Math.min(minLength, q.size());

        LinkedList<Queue<Index>> finalPath = new LinkedList<>(); // הרשימה של התורים שאותם נחזיר
        // נכניס לרשימה רק את התורים שהגודל שלהם שווה לגודל המינימאלי
        for (Queue<Index> q : phatFromSourceToDestination)
            if (q.size() == minLength)
                finalPath.add(q);

        return finalPath;
    }
}
