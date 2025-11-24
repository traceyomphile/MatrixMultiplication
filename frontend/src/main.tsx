import React from "react";
import { createRoot } from "react-dom/client";

const App: React.FC = () => {
  return (
	<div>
	  <h1>Matrix Multiplication</h1>
	  <p>App component placeholder</p>
	</div>
  );
};

createRoot(document.getElementById("root")!).render(<App />);
