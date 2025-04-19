import { useState } from "react";
import { useRouter } from "next/router";
import axios from "axios";
import Image from "next/image";

import logo from "../assets/logo.png";
import proteinaIcon from "../assets/proteina_icon.png";
import carboidratoIcon from "../assets/carboidrato_icon.png";
import lipidioIcon from "../assets/lipidio_icon.png";

export default function MacrosInput() {
  const router = useRouter();
  const { pacienteId } = router.query;
  const [formData, setFormData] = useState({
    proteina: "",
    carboidrato: "",
    lipidio: "",
    flagKcal: true,
  });
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: name === "flagKcal" ? value === "true" : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!pacienteId) {
      setErrorMessage("ID do paciente não encontrado!");
      return;
    }

    try {
      const response = await axios.post(
        `http://localhost:8080/macros-input/${pacienteId}`,
        formData
      );

      const inputId = response.data.id;
      setSuccessMessage("Macros salvos com sucesso! Redirecionando...");
      router.push({
        pathname: "/resultado",
        query: { inputId, pacienteId },
      });
    } catch (error) {
      console.error("Erro ao salvar macros:", error);
      setErrorMessage("Erro ao salvar os macros. Por favor, tente novamente.");
    }
  };

  return (
    <div className="min-h-screen bg-lime-100 p-8 flex flex-col justify-between">
      {/* Topo - Logo */}
      <div className="flex justify-end">
        <Image src={logo} alt="Logo Macros Game" width={120} height={80} />
      </div>

      <div className="flex flex-col gap-6  items-center text-orange-500 font-bold text-lg uppercase mb-2">
        {/* Título e seleção */}
        <h2>
          Selecione se prefere informar seus resultados em gramas ou calorias...
        </h2>
        <h2>Não esqueça de se desafiar, viu?!</h2>
      </div>
      

      {/* Seleção de unidade */}
      <div className="flex  flex-row gap-8 mb-6 justify-center size">
        <label className="flex gap-2 text-green-900 font-bold">
          <input
            type="radio"
            name="flagKcal"
            value={false}
            checked={formData.flagKcal === false}
            onChange={handleChange}
          />
          GRAMAS
        </label>
        <label className="flex items-center gap-2 text-green-900 font-bold">
          <input
            type="radio"
            name="flagKcal"
            value={true}
            checked={formData.flagKcal === true}
            onChange={handleChange}
          />
          CALORIAS
        </label>
      </div>


      <div className="flex flex-col gap-6 items-center">
        <h3 className="text-orange-500 font-bold text-xl uppercase mb-4">
          E então, quais valores você encontrou?
        </h3>

        <form onSubmit={handleSubmit} >
          {/* Contêiner para os 3 primeiros campos */}
          <div className="flex gap-6 justify-center">
            {/* Proteína */}
            <label className="flex items-center gap-4">
              <Image src={proteinaIcon} alt="Proteína" width={200} height={120} />
              <input
                type="number"
                name="proteina"
                value={formData.proteina}
                onChange={handleChange}
                required
                className="rounded-full border-2 border-green-900 px-4 py-1 w-32"
              />
            </label>

            {/* Carboidrato */}
            <label className="flex items-center gap-4">
              <Image src={carboidratoIcon} alt="Carboidrato" width={200} height={120} />
              <input
                type="number"
                name="carboidrato"
                value={formData.carboidrato}
                onChange={handleChange}
                required
                className="rounded-full border-2 border-green-900 px-4 py-1 w-32"
              />
            </label>

            {/* Lipídio */}
            <label className="flex items-center gap-4">
              <Image src={lipidioIcon} alt="Lipídio" width={200} height={120} />
              <input
                type="number"
                name="lipidio"
                value={formData.lipidio}
                onChange={handleChange}
                required
                className="rounded-full border-2 border-green-900 px-4 py-1 w-32"
              />
            </label>
          </div>

          {/* Mensagens */}
          {errorMessage && <p className="text-red-600">{errorMessage}</p>}
          {successMessage && <p className="text-green-600">{successMessage}</p>}

          {/* Botões */}
          <div className="flex gap-4 mt-6 justify-center">
            <button
              type="submit"
              className="bg-orange-500 text-white font-bold px-6 py-2 rounded-full hover:bg-orange-600 transition"
            >
              Salvar e Calcular
            </button>
            <button
              type="button"
              onClick={() => router.push("/paciente")}
              className="bg-green-900 text-white font-bold px-6 py-2 rounded-full hover:bg-green-800 transition"
            >
              Voltar
            </button>
          </div>
        </form>
      </div>
      

    </div>
  );
}
