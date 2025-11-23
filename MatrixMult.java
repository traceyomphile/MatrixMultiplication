
public class MatrixMult {
    private double[][] matrixA;
    private int[] dimsA;
    private double[][] matrixB;
    private int[] dimsB;
    private int[] resDims;

    public MatrixMult(double[][] matrixA, double[][] matrixB) {
        this.matrixA = matrixB;
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
        for (int i = 0; i < result[0].length; i++) {
            for (int j = 0; j < result.length; j++) {
                result[i][j] = sum(this.matrixA[i], this.matrixB[j]);
            }
        } return result;
    }

    public double sum(double[] row, double[] col) {
        double sum = 0;
        for (int i = 0; i < row.length; i++) {
            sum += (row[i] * col[i]);
        } return sum;
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