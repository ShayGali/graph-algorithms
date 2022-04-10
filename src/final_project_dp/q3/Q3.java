package final_project_dp.q3;

import final_project_dp.base_classes.Index;
import final_project_dp.base_classes.Matrix;
import final_project_dp.base_classes.MatrixAsGraph;
import final_project_dp.q1.DfsConnectedComponents;

import java.util.LinkedList;

public class Q3 {

    /**
     * check if a component on graph can represent a submarine.
     *
     * @param component list of index on graph.
     * @return true if the component can represent a submarine.
     */
    private boolean isSubmarine(LinkedList<Index> component) {
        if (component.size() == 1) return false; // if the component size if 1 - only one element - it's not a submarine

        // temp values for the index`s
        Index topLeftIndex = component.getFirst();
        Index topRightIndex = component.getFirst();
        Index bottomLeftIndex = component.getFirst();
        Index bottomRightIndex = component.getFirst();

        // loop on the component to initialize the square edges
        for (Index index : component) {
            if (index.getRow() <= topLeftIndex.getRow() && index.getColumn() <= topLeftIndex.getColumn())
                topLeftIndex = index;

            if (index.getRow() <= topRightIndex.getRow() && index.getColumn() >= topRightIndex.getColumn())
                topRightIndex = index;

            if (index.getRow() >= bottomLeftIndex.getRow() && index.getColumn() <= bottomLeftIndex.getColumn())
                bottomLeftIndex = index;

            if (index.getRow() >= bottomRightIndex.getRow() && index.getColumn() >= bottomRightIndex.getColumn())
                bottomRightIndex = index;

        }

        // if one of the edges is not in the same row or column it means it is not square
        if (topLeftIndex.getRow() != topRightIndex.getRow() ||
                bottomLeftIndex.getRow() != bottomRightIndex.getRow() ||
                topLeftIndex.getColumn() != bottomLeftIndex.getColumn() ||
                topRightIndex.getColumn() != bottomRightIndex.getColumn()
        )
            return false;


        // check if all the index`s from the top left index to the bottom right are in the component
        // if not we will return false
        for (int i = topLeftIndex.getRow(); i <= bottomRightIndex.getRow(); i++) {
            for (int j = topLeftIndex.getColumn(); j <= bottomRightIndex.getColumn(); j++) {
                if (!component.contains(new Index(i, j)))
                    return false;
            }
        }

        return true;
    }

    /**
     * Checks how many submarines can be represented in the matrix.
     * @param mat the matrix.
     * @return the number of submarines that can be represented in the matrix.
     */
    public int numberOfSubmarines(int[][] mat) {
        DfsConnectedComponents<Index> connectedComponentsAlgorithm = new DfsConnectedComponents<>(); // to have the ability for get all the connected components
        MatrixAsGraph matrixAsGraph = new MatrixAsGraph(new Matrix(mat)); // make the matrix a graph
        LinkedList<LinkedList<Index>> connectedComponents = connectedComponentsAlgorithm.allConnectedComponents(matrixAsGraph); //get all the connected components

        int counter = 0; // count the number of submarines
        // loop on all the components
        // and check if it can represent a submarine
        for (LinkedList<Index> component : connectedComponents) {
            if (isSubmarine(component))
                counter++;
        }

        return counter;
    }
}
