package core.debug;

import core.math.matrix.SquareMatrix;

public class MatrixInvalidException extends Exception{
    private static final long serialVersionUID = -8591762087507223701L;

    protected float[][]a;

    public MatrixInvalidException(float[][] a){
        this.a=a;
    }

    @Override
    public String toString() {
        return String.format("Matrix is not rectangular:\n%s\nSquareMatrix must be NxM.", SquareMatrix.writeMatrix(a));
    }
}
