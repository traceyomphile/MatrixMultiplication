import React, { useState } from "react";
import "./MatrixUI.css";

const generateMatrix = (rows: number, cols: number) =>
  Array.from({ length: rows }, () => Array(cols).fill(0));

export default function MatrixUI() {
  const [rowsA, setRowsA] = useState<number>(2);
  const [colsA, setColsA] = useState<number>(2);
  const [rowsB, setRowsB] = useState<number>(2);
  const [colsB, setColsB] = useState<number>(2);

  const [matrixA, setMatrixA] = useState<number[][]>(generateMatrix(2, 2));
  const [matrixB, setMatrixB] = useState<number[][]>(generateMatrix(2, 2));
  const [result, setResult] = useState<number[][] | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);

  const handleGenerate = () => {
    if (rowsA <= 0 || colsA <= 0 || rowsB <= 0 || colsB <= 0) {
      setError("Dimensions must be positive integers.");
      return;
    }
    setError(null);
    setMatrixA(generateMatrix(rowsA, colsA));
    setMatrixB(generateMatrix(rowsB, colsB));
    setResult(null);
  };

  const updateValue = (mat: number[][], setter: (m: number[][]) => void, r: number, c: number, value: string) => {
    const copy = mat.map(row => [...row]);
    const num = Number(value);
    if (Number.isNaN(num)) return;
    copy[r][c] = num;
    setter(copy);
  };

  const multiply = async () => {
    setError(null);
    setResult(null);

    if (matrixA[0].length !== matrixB.length) {
      setError("Incompatible dimensions: columns of A must equal rows of B.");
      return;
    }

    setLoading(true);
    try {
      const res = await fetch("http://localhost:8080/api/matrix/multiply", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ matrixA, matrixB })
      });

      if (!res.ok) {
        const txt = await res.text();
        setError("Server error: " + txt);
        setLoading(false);
        return;
      }

      const data = await res.json();
      setResult(data);
    } catch (e) {
      setError("Network error: " + (e as Error).message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="matrix-ui">
      <h1>Matrix Multiplication</h1>

      <div className="controls-row">
        <div>
          <h3>Matrix A</h3>
          <label>Rows: <input type="number" value={rowsA} onChange={e => setRowsA(Number(e.target.value))} /></label>
          <label className="label-spaced">Cols: <input type="number" value={colsA} onChange={e => setColsA(Number(e.target.value))} /></label>
        </div>
        <div>
          <h3>Matrix B</h3>
          <label>Rows: <input type="number" value={rowsB} onChange={e => setRowsB(Number(e.target.value))} /></label>
          <label className="label-spaced">Cols: <input type="number" value={colsB} onChange={e => setColsB(Number(e.target.value))} /></label>
        </div>
      </div>

      <button className="generate-button" onClick={handleGenerate}>Generate Matrices</button>

      <div className="matrices-row">
        <div>
          <h4>Matrix A</h4>
          {matrixA.map((row, i) => (
            <div key={i}>
              {row.map((val, j) => (
                <input
                  key={j}
                  type="number"
                  value={val}
                  onChange={e => updateValue(matrixA, setMatrixA, i, j, e.target.value)}
                  className="matrix-input"
                  title={`A[${i + 1},${j + 1}]`}
                  placeholder="0"
                />
              ))}
            </div>
          ))}
        </div>

        <div>
          <h4>Matrix B</h4>
          {matrixB.map((row, i) => (
            <div key={i}>
              {row.map((val, j) => (
                <input
                  key={j}
                  type="number"
                  value={val}
                  onChange={e => updateValue(matrixB, setMatrixB, i, j, e.target.value)}
                  className="matrix-input"
                  title={`B[${i + 1},${j + 1}]`}
                  placeholder="0"
                />
              ))}
            </div>
          ))}
        </div>
      </div>

      <div className="action-row">
        <button onClick={multiply} disabled={loading}>Multiply</button>
        {loading && <span className="computing-text">Computing...</span>}
      </div>

      {error && <div className="error-text">{error}</div>}

      {result && (
        <div className="result-section">
          <h3>Result</h3>
          {result.map((row, i) => (
            <div key={i} className="result-row">
              {row.map((v, j) => <span key={j} className="result-cell">{v}</span>)}
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
