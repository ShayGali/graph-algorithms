package final_project_dp;


import final_project_dp.base_classes.Index;
import final_project_dp.base_classes.Matrix;
import final_project_dp.base_classes.MatrixAsGraph;
import final_project_dp.q1.DfsConnectedComponents;
import final_project_dp.q2.ShortsPath;
import final_project_dp.q3.Q3;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        int[][] mat = {
             //  0  1  2
                {1, 0, 0}, // 0
                {1, 1, 0}, // 1
                {1, 1, 1}, // 2
                {1, 1, 1}, // 3
                {1, 1, 1}, // 4
        };
        MatrixAsGraph matrixAsGraph = new MatrixAsGraph(new Matrix(mat));

        ShortsPath ShortsPathAl = new ShortsPath();
        LinkedList<Queue<Index>> shortsPath = ShortsPathAl.shortsPath(matrixAsGraph, new Index(0,0), new Index(2,0));

        System.out.println(shortsPath);
//        DfsConnectedComponents<Index> dfsConnectedComponents = new DfsConnectedComponents<>();
//
//        Set<Index> bfs = ShortsPathAl.bfs(matrixAsGraph, new Index(2,2), new Index(0,0));
//
////        Set<Index> cc = bfs.shortsPath(matrixAsGraph, new Index(0,0), new Index(4,2));
////        Set<Set<Index>> cc2 = dfsConnectedComponents.allConnectedComponents(matrixAsGraph);
//
//        System.out.println(bfs);
////        System.out.println(cc2);
//        Set<String> s = new LinkedHashSet<>();
    }

}
