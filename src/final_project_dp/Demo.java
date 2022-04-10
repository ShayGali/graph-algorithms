package final_project_dp;


import final_project_dp.q1.DfsConnectedComponents;
import final_project_dp.q2.ShortsPath;
import final_project_dp.q3.Q3;

import java.util.LinkedHashSet;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
//        int[][] mat = {
//             //  0  1  2
//                {1, 0, 0}, // 0
//                {1, 1, 0}, // 1
//                {0, 1, 1}, // 2
//                {1, 1, 1}, // 3
//                {1, 1, 1}, // 4
//        };


        int[][] mat = {
             //  0 1 2 3 4
           /*0*/{0,1,1,0,0},
           /*1*/{0,0,0,0,1},
           /*2*/{0,0,0,0,1},
        };

//        MatrixAsGraph matrixAsGraph = new MatrixAsGraph(new Matrix(mat));
        Q3 numberOfSubmarinesAlgorithm = new Q3();
        int n = numberOfSubmarinesAlgorithm.numberOfSubmarines(mat);
        System.out.println(n);
//        ShortsPath<Index> ShortsPathAl = new ShortsPath<>();
//        DfsConnectedComponents<Index> dfsConnectedComponents = new DfsConnectedComponents<>();
//
//        Set<Index> bfs = ShortsPathAl.bfs(matrixAsGraph, new Index(0,0), new Index(2,2));
//
////        Set<Index> cc = bfs.shortsPath(matrixAsGraph, new Index(0,0), new Index(4,2));
////        Set<Set<Index>> cc2 = dfsConnectedComponents.allConnectedComponents(matrixAsGraph);
//
//        System.out.println(bfs);
////        System.out.println(cc2);
//        Set<String> s = new LinkedHashSet<>();
    }

}
