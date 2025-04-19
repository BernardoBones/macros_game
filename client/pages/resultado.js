import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import axios from "axios";

export default function Resultado() {
  const router = useRouter();
  const { inputId, pacienteId } = router.query;
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    if (inputId) {
      axios
        .get(`http://localhost:8080/resultados/${inputId}`)
        .then((response) => {
          const data = response.data;

          if (data.aprovado) {
            router.replace("/aprovado");
          } else {
            const erros = [];

            if (!data.adequacaoProteina) erros.push("Proteína: Fora da faixa esperada.");
            if (!data.adequacaoCarboidrato) erros.push("Carboidrato: Fora da faixa esperada.");
            if (!data.adequacaoLipidio) erros.push("Lipídio: Fora da faixa esperada.");
            if (!data.adequacaoCaloriasTotais) erros.push("Calorias Totais: Fora da faixa de 95% a 105%.");
            router.replace({
              pathname: "/reprovado",
              query: {
                erros: encodeURIComponent(JSON.stringify(erros)),
                pacienteId, // opcional se quiser usar lá também
              },
            });
          }
        })
        .catch((error) => {
          console.error("Erro ao buscar o resultado:", error);
          setErrorMessage(
            error.response?.data?.message || "Erro ao buscar o resultado."
          );
        });
    }
  }, [inputId]);

  if (errorMessage) {
    return <p className="text-red-600 text-center mt-10">{errorMessage}</p>;
  }

  return (
    <div className="flex justify-center items-center h-screen">
      <p className="text-lg text-gray-600">Carregando o resultado...</p>
    </div>
  );
}
