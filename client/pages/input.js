import { useState } from "react";
import { useRouter } from "next/router";
import axios from "axios";

export default function MacrosInput(){
  const router = useRouter();
  const { pacienteId } = router.query; // Pegar o ID do paciente da URL
  const [formData, setFormData] = useState({
    proteina: "",
    carboidrato: "",
    lipidio: "",
    flagKcal: true, // Default é true
  });
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: name === "flagKcal" ? value === "true" : value, // Converter flagKcal para booleano
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!pacienteId) {
      setErrorMessage("ID do paciente não encontrado!");
      return;
    }

    try {
      // Enviar os dados para o backend
      const response = await axios.post(
        `http://localhost:8080/macros-input/${pacienteId}`,
        formData
      );

      // ID do input salvo
      const inputId = response.data.id;
    
      // Redirecionar para a página de resultado
      setSuccessMessage("Macros salvos com sucesso! Redirecionando...");
      
      router.push({ pathname: '/resultado', query: { inputId: inputId, pacienteId: pacienteId } });
    } catch (error) {
      console.error("Erro ao salvar macros:", error);
      setErrorMessage("Erro ao salvar os macros. Por favor, tente novamente.");
    }
  };

  return (
    <div>
      <h1>Input dos Macros</h1>
      {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
      {successMessage && <p style={{ color: "green" }}>{successMessage}</p>}
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: "15px" }}>
          <label htmlFor="proteina">Proteína:</label>
          <input
            type="number"
            id="proteina"
            name="proteina"
            value={formData.proteina}
            onChange={handleChange}
            required
          />
        </div>
        <div style={{ marginBottom: "15px" }}>
          <label htmlFor="carboidrato">Carboidrato:</label>
          <input
            type="number"
            id="carboidrato"
            name="carboidrato"
            value={formData.carboidrato}
            onChange={handleChange}
            required
          />
        </div>
        <div style={{ marginBottom: "15px" }}>
          <label htmlFor="lipidio">Lipídio:</label>
          <input
            type="number"
            id="lipidio"
            name="lipidio"
            value={formData.lipidio}
            onChange={handleChange}
            required
          />
        </div>
        <div style={{ marginBottom: "15px" }}>
          <label htmlFor="flagKcal">Unidade:</label>
          <select
            id="flagKcal"
            name="flagKcal"
            value={formData.flagKcal}
            onChange={handleChange}
          >
            <option value="true">Calorias</option>
            <option value="false">Gramas</option>
          </select>
        </div>
        <button type="submit">Salvar e Calcular</button>
      </form>
      <button
        style={{ marginTop: "20px" }}
        onClick={() => router.push("/paciente")}
      >
        Voltar para o input das informações do paciente
      </button>
    </div>
  );
};
