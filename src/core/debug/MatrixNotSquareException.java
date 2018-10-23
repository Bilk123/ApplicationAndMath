package core.debug;


import core.math.matrix.SquareMatrix;

public class MatrixNotSquareException extends MatrixInvalidException{
    private static final long serialVersionUID = -2962803943206028218L;

    public MatrixNotSquareException(float[][] a){
        super(a);
    }

    @Override
    public String toString() {
        return String.format("SquareMatrix is not square:\n%s\nMatrixNf must be NxN.", SquareMatrix.writeMatrix(a));
    }
}
