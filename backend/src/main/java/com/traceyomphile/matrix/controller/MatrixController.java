@RestController
@RequestMapping("/api/matrix")

public class MatrixController {

    @PostMapping("/multiply")
    public double[][] multiply(@RequestBody MatrixRequest req) {

        double[][] A = req.getMatrixA();
        double[][] B = req.getMatrixB();

        if (A[0].length != B.length) {
            throw new IllegalArgumentException("Invalid dimensions.");
        }

        return MatrixMult.multiply(A, B);
    }
}
