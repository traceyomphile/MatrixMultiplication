
public class MatrixMult {
    private final double[][] matrixA;
    private final int[] dimsA;
    private final double[][] matrixB;
    private final int[] dimsB;
    private final int[] resDims;

    public MatrixMult(double[][] matrixA, double[][] matrixB) {
        this.matrixA = matrixA;
        this.dimsA = new int[2];
        this.matrixB = matrixB;
        this.dimsB = new int[2];
        this.resDims = new int[2];
    }

    public double[][] matrixMult() {
        setDims();
        if (!isCompatible()) {
            throw new IllegalArgumentException("Matrices are of incompatible dimensions!");
        }

        double[][] result = new double[this.dimsA[0]][this.dimsB[1]];
        this.resDims[0] = this.dimsA[0];
        this.resDims[1] = this.dimsB[1];

        for (int i = 0; i < this.dimsA[0]; i++) {
            for (int j = 0; j < this.dimsB[1]; j++) {
                double sum = 0;
                for (int k = 0; k < this.dimsA[1]; k++) {
                    sum += (this.matrixA[i][k] * this.matrixB[k][j]);
                } result[i][j] = sum;
            }
        } return result;
    }

    public void setDims() {
        // MatrixA Dimensions
        this.dimsA[0] = this.matrixA.length;
        this.dimsA[1] = this.matrixA[0].length;
        // MatrixB Dimensions
        this.dimsB[0] = this.matrixB.length;
        this.dimsB[1] = this.matrixB[0].length;
    }

    public int[] getDims(String matrix) {
        if (matrix.equals("A")) {
            return this.dimsA;
        } else if (matrix.equals("B")) {
            return this.dimsB;
        } else {
            throw new IllegalArgumentException("Argument must be A or B!");
        }
    }

    public int[] getResDims() {
        return this.resDims;
    }

    public boolean isCompatible() {
        return (this.dimsA[1] == this.dimsB[0]);
    }
}