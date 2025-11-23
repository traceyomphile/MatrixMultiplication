
import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimsA = new int[2];
        int[] dimsB = new int[2];

        System.out.println("Enter dimensions of Matrix A, space separated:");
        String dimensionsA = scanner.nextLine();
        while (dimensionsA.isEmpty()) {
            System.out.println("Enter dimensions of Matrix A, space separated (non-empty):");
            dimensionsA = scanner.nextLine();
        }
        String[] temp = dimensionsA.split(" ");
        if (Integer.parseInt(temp[0]) > 0 && Integer.parseInt(temp[1]) > 0) {
            dimsA[0] = Integer.parseInt(temp[0]);
            dimsA[1] = Integer.parseInt(temp[1]);
        } else {
            System.err.println("Invalid dimensions! Exiting Program...");
            System.exit(0);
        }

        System.out.println("Enter dimensions of Matrix B, space separated:");
        String dimensionsB = scanner.nextLine();
        while (dimensionsB.isEmpty()) {
            System.out.println("Enter dimensions of Matrix B, space separated (non-empty):");
            dimensionsB = scanner.nextLine();
        }

        temp = dimensionsB.split(" ");
        try {
            if (Integer.parseInt(temp[0]) > 0 && Integer.parseInt(temp[1]) > 0) {
                dimsB[0] = Integer.parseInt(temp[0]);
                dimsB[1] = Integer.parseInt(temp[1]);
            } else {
                System.err.println("Invalid dimensions! Exiting Program...");
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            System.err.println("Exiting Program...");
            System.exit(0);
        } 

        System.out.println("");
        double[][] matrixA = new double[dimsA[0]][dimsA[1]];
        System.out.println("Enter the values for Matrix A, one line at the time, space separated!");

        for (int i = 0; i < dimsA[0]; i++) {
            String values = scanner.nextLine();
            while (values.isEmpty()) {
                System.out.println("Enter non-empty matrix values (space-separated)!");
                values = scanner.nextLine();
            }

            temp = values.split(" ");
            if (temp.length != dimsA[1]) {
                System.err.println("""
                                   ERROR! Values given don't match the dimensions.
                                   Exiting Program...""");
                System.exit(0);
            }
            try {
                int counter = 0;
                while (counter < dimsA[1]) {
                    if (Integer.parseInt(temp[counter]) < 0) {
                        System.err.println("Value Error! Elements must be double values greater or equal to 0!");
                        System.exit(0);
                    } 
                    matrixA[i][counter] = Integer.parseInt(temp[counter]);
                    counter++;
                }
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
                System.exit(0);
            }
        }

        System.out.println("");
        double[][] matrixB = new double[dimsB[0]][dimsB[1]];
        System.out.println("Enter the values for Matrix B, one line at the time, space separated!");

        for (int i = 0; i < dimsB[0]; i++) {
            String values = scanner.nextLine();
            while (values.isEmpty()) {
                System.out.println("Enter non-empty matrix values (space-separated)!");
                values = scanner.nextLine();
            }

            temp = values.split(" ");
            if (temp.length != dimsB[1]) {
                System.err.println("""
                                   ERROR! Values given don't match the dimensions.
                                   Exiting Program...""");
                System.exit(0);
            }
            try {
                int counter = 0;
                while (counter < dimsB[1]) {
                    if (Integer.parseInt(temp[counter]) < 0) {
                        System.err.println("Value Error! Elements must be double values greater or equal to 0!");
                        System.exit(0);
                    } 
                    matrixB[i][counter] = Integer.parseInt(temp[counter]);
                    counter++;
                }
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
                System.exit(0);
            }
        }

        System.out.println("");
        System.out.println("Computing matrix multiplication...");
        MatrixMult matrixMult = new MatrixMult(matrixA, matrixB);
        double[][] result = matrixMult.matrixMult();
        int[] resDims = matrixMult.getResDims();

        System.out.println("");
        System.out.println("Resulting dimensions: " + resDims[0] + "x" + resDims[1]);
        for (int i = 0; i < resDims[0]; i++) {
            String row = "";
            for (int j = 0; j < resDims[1]; j++) {
                row += result[i][j] + " ";
            }
            System.out.println(row);
        }

        scanner.close();
        System.out.println("Matrix Multiplication complete!");
        System.exit(0);
    }
}