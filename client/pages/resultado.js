import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import axios from "axios";

export default function Resultado(){
  const router = useRouter();
  const { inputId } = router.query; // Obter o ID do input da URL
  const [resultado, setResultado] = useState(null);
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    if (inputId) {
      axios
        .get(`http://localhost:8080/resultados/${inputId}`)
        .then((response) => {
          setResultado(response.data);
        })
        .catch((error) => {
          console.error("Erro ao buscar o resultado:", error);
          setErrorMessage(
            "Erro ao buscar o resultado. Verifique o ID ou tente novamente mais tarde."
          );
        });
    }
  }, [inputId]);

  if (errorMessage) {
    return <p style={{ color: "red" }}>{errorMessage}</p>;
  }

  if (!resultado) {
    return <p>Carregando o resultado...</p>;
  }

  return (
    <div style={{ maxWidth: "600px", margin: "0 auto", padding: "20px" }}>
      <h1>Resultado</h1>
      {resultado.aprovado ? (
        <p style={{ color: "green", fontWeight: "bold" }}>
          Parabéns! Você acertou todos os macros e calorias.
        </p>
      ) : (
        <div>
          <p style={{ color: "red", fontWeight: "bold" }}>
            Você não foi aprovado. Veja onde errou:
          </p>
          <ul>
            {!resultado.adequacaoProteina && (
              <li>- Proteína: Fora da faixa esperada.</li>
            )}
            {!resultado.adequacaoCarboidrato && (
              <li>- Carboidrato: Fora da faixa esperada.</li>
            )}
            {!resultado.adequacaoLipidio && (
              <li>- Lipídio: Fora da faixa esperada.</li>
            )}
            {!resultado.adequacaoCaloriasTotais && (
              <li>- Calorias Totais: Fora da faixa de 95% a 105%.</li>
            )}
          </ul>
        </div>
      )}
      <button
        style={{ marginTop: "20px" }}
        onClick={() => router.push("/input")}
      >
        Voltar para o input dos macros
      </button>
      <button
        style={{ marginTop: "20px" }}
        onClick={() => router.push("/paciente")}
      >
        Voltar para o input das informações do paciente
      </button>
    </div>
  );
};
