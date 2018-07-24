package core.math;


public class MatrixNotSquareException extends MatrixInvalidException{

    MatrixNotSquareException(float[][] a){
        super(a);
    }

    @Override
    public String toString() {
        return String.format("SquareMatrix is not square:\n%s\nMatrixNf must be NxN.", SquareMatrix.writeMatrix(a));
    }
}
