package final_project_dp.q4;

import final_project_dp.Index;
import final_project_dp.Matrix;
import final_project_dp.Node;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {
//        int[][] mat = {
//             //  0  1  2
//                {1, 0, 0}, // 0
//                {1, 1, 0}, // 1
//                {1, 1, 1}, // 2
//                {1, 1, 1}, // 3
//                {1, 1, 1}, // 4
//        };
        int[][] mat = {
                {100, 100, 100},
                {500, 100, 300},
        };

        WeightedMatrixAsGraph weightedMatrix = new WeightedMatrixAsGraph(new WeightedMatrix(mat));
//        weightedMatrix.InitializeDistancesFromSource(new Index(1, 0));
//        System.out.println(weightedMatrix.getNodesMap());
//        System.out.println(weightedMatrix.isReachable(new Index(0,0), new Index(0,1)));
//        Map<Index, Node<Index>> nodesMap = weightedMatrix.getNodesMap();
        Q4<Index> q4 = new Q4<>();
//        q4.setDistancesFromSourceNodeInNodesMap(weightedMatrix, new Index(0, 0),nodesMap);
//        for(Map.Entry e : nodesMap.entrySet())
//            System.out.println(e.getValue());
//        Collection<Index> al = q4.func3(weightedMatrix, new Index(0, 0), new Index(3, 1));
        Collection<Index> al = q4.dijkstraAlgorithm(weightedMatrix, new Index(1, 0), new Index(1, 2));
        System.out.println(al);
    }
}
