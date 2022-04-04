package final_project_dp.q4;

import final_project_dp.Index;
import final_project_dp.Matrix;

import java.util.Collection;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
//        int[][] mat = {
//                //  0  1  2
//                {1, 0, 0}, // 0
//                {1, 1, 0}, // 1
//                {0, 1, 1}, // 2
//                {1, 1, 1}, // 3
//                {1, 1, 1}, // 4
//        };
        int[][]mat = {
                {100,100,100},
                {500,900,300}
        };

        WeightedMatrixAsGraph weightedMatrix = new WeightedMatrixAsGraph(new WeightedMatrix(mat));
        Q4<Index> q4 = new Q4<>();
        System.out.println(weightedMatrix.getWeight(new Index(0,2), new Index(0,1)));
        Collection<Index> al = q4.func(weightedMatrix,new Index(1,0),new Index(1,2));
        System.out.println(al);
    }
}
