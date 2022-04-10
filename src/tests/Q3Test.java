package tests;

import final_project_dp.q3.Q3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Q3Test {
    Q3 q3 = new Q3();

    @Test
    void goodMatrixTest1() {
        int[][] mat = {
                //  0 1 2 3 4
                /*0*/{1, 1, 1, 1, 1},
                /*1*/{1, 1, 1, 1, 1},
                /*2*/{1, 1, 1, 1, 1},
        };
        assertEquals(q3.numberOfSubmarines(mat), 1);
    }

    @Test
    void goodMatrixTest2() {
        int[][] mat = {
                //  0 1 2 3 4
                /*0*/{0, 0, 1, 1, 1},
                /*1*/{0, 0, 0, 0, 0},
                /*2*/{1, 1, 0, 1, 1},
        };
        assertEquals(q3.numberOfSubmarines(mat), 3);
    }

    @Test
    void goodMatrixTest3() {
        int[][] mat = {
                //  0 1 2 3 4
                /*0*/{1, 0, 0, 1, 1},
                /*1*/{1, 0, 0, 1, 1},
                /*2*/{1, 0, 0, 1, 1},
        };
        assertEquals(q3.numberOfSubmarines(mat), 2);
    }

    @Test
    void goodMatrixTest4() {
        int[][] mat = {
                //  0 1 2 3 4
                /*0*/{1, 1, 1, 1, 1},
                /*1*/{0, 0, 0, 0, 0},
                /*2*/{0, 0, 0, 1, 0},
        };
        assertEquals(q3.numberOfSubmarines(mat), 1);
    }

    @Test
    void notGoodMatrixTest1() {
        int[][] mat = {
                //  0 1 2 3 4
                /*0*/{1, 1, 0, 1, 1},
                /*1*/{1, 0, 0, 1, 1},
                /*2*/{1, 0, 0, 1, 1},
        };
        assertEquals(q3.numberOfSubmarines(mat), 1);
    }

    @Test
    void notGoodMatrixTest2() {
        int[][] mat = {
                //  0 1 2 3 4
                /*0*/{1, 1, 0, 1, 1},
                /*1*/{1, 0, 0, 1, 1},
                /*2*/{1, 1, 0, 1, 1},
        };
        assertEquals(q3.numberOfSubmarines(mat), 1);
    }

    @Test
    void notGoodMatrixTest3() {
        int[][] mat = {
                //  0 1 2 3 4
                /*0*/{1, 0, 0, 1, 1},
                /*1*/{1, 0, 0, 1, 1},
                /*2*/{1, 1, 0, 1, 1},
        };
        assertEquals(q3.numberOfSubmarines(mat), 1);
    }

    @Test
    void notGoodMatrixTest4() {
        int[][] mat = {
                //  0 1 2 3 4
                /*0*/{1, 0, 0, 1, 1},
                /*1*/{1, 1, 0, 1, 1},
                /*2*/{1, 1, 0, 1, 1},
        };
        assertEquals(q3.numberOfSubmarines(mat), 1);
    }

    @Test
    void notGoodMatrixTest5() {
        int[][] mat = {
                //  0 1 2 3 4
                /*0*/{1, 0, 0, 1, 0},
                /*1*/{0, 0, 0, 1, 0},
                /*2*/{0, 0, 0, 0, 0},
        };
        assertEquals(q3.numberOfSubmarines(mat), 1);
    }

    @Test
    void notGoodMatrixTest6() {
        int[][] mat = {
                //  0 1 2 3 4
                /*0*/{1, 1, 1, 1, 0},
                /*1*/{0, 0, 0, 0, 1},
                /*2*/{0, 0, 0, 0, 0},
        };
        assertEquals(q3.numberOfSubmarines(mat), 0);
    }

    @Test
    void notGoodMatrixTest7() {
        int[][] mat = {
                //  0 1 2 3 4
                /*0*/{0, 0, 0, 0, 0},
                /*1*/{1, 1, 1, 1, 1},
                /*2*/{0, 0, 1, 0, 0},
        };
        assertEquals(q3.numberOfSubmarines(mat), 0);
    }

}