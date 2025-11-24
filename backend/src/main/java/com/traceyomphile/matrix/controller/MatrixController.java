package backend.src.main.java.com.traceyomphile.matrix.controller;


import backend.src.main.java.com.traceyomphile.matrix.model.MatrixRequest;
import backend.src.main.java.com.traceyomphile.matrix.service.MatrixMult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matrix")
public class MatrixController {

    @PostMapping("/multiply")
    public ResponseEntity<double[][]> multiply(@RequestBody MatrixRequest req) {
        double[][] A = req.getMatrixA();
        double[][] B = req.getMatrixB();

        if (A == null || B == null) {
            return ResponseEntity.badRequest().build();
        }
        if (A.length == 0 || B.length == 0 || A[0].length != B.length) {
            return ResponseEntity.badRequest().build();
        }

        double[][] res = MatrixMult.multiply(A, B);
        return ResponseEntity.ok(res);
    }
}
