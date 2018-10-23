package core.debug;


public class MatrixMismatchException extends Exception {
    private static final long serialVersionUID = -8650277152253234583L;

    public enum Function {
        addition("A<nxm> : B<nxm>"), multiplication("A<pxn> : B<nxq>");
        String requirement;

        Function(String requirement){
            this.requirement =requirement;
        }

        public String getRequirement() {
            return requirement;
        }
    }
    private Function f;

    public MatrixMismatchException(Function f) {
        this.f=f;
    }

    @Override
    public String toString() {
        return String.format("Matrix A's and Matrix B's dimensions are invalid for %s: %s needed", f, f.getRequirement());
    }
}
