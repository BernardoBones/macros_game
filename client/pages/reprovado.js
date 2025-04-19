import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import Image from "next/image";
import logo from "../assets/logo.png";

export default function Reprovado() {
  const router = useRouter();
  const [errosDecodificados, setErrosDecodificados] = useState([]);
  const [pacienteId, setPacienteId] = useState(null);

  useEffect(() => {
    if (router.query.erros) {
      const decodedErros = JSON.parse(decodeURIComponent(router.query.erros));
      setErrosDecodificados(decodedErros);
    }

    if (router.query.pacienteId) {
      setPacienteId(router.query.pacienteId);
    }
  }, [router.query]);

  const handleTentarNovamente = () => {
    if (pacienteId) {
      router.push({
        pathname: "/input",
        query: { pacienteId },
      });
    }
  };

  const handleNovoPaciente = () => {
    router.push("/paciente");
  };

  return (
    <div className="min-h-screen bg-lime-100 p-8 flex flex-col justify-between">
      {/* Logo */}
      <div className="flex justify-end">
        <Image src={logo} alt="Logo Macros Game" width={120} height={80} />
      </div>

      {/* Conteúdo central */}
      <div className="flex flex-col items-center justify-center gap-6 flex-grow">
        <h1 className="text-green-900 font-bold text-2xl text-center">
          Ah que pena! Você não atingiu um resultado satisfatório.
        </h1>

        <p className="text-green-900 text-xl text-center">
          Vamos entender o que aconteceu:
        </p>

        <ul className="text-red-600 text-lg list-disc text-left px-4">
          {errosDecodificados.map((erro, index) => (
            <li key={index}>{erro}</li>
          ))}
        </ul>

        <div className="flex gap-6 justify-center">
          <button
            onClick={handleTentarNovamente}
            className="bg-orange-500 text-white font-bold px-6 py-2 rounded-full hover:bg-orange-600 transition mt-4"
          >
            Tentar novamente com o mesmo paciente
          </button>

          <button
            onClick={handleNovoPaciente}
            className="bg-orange-500 text-white font-bold px-6 py-2 rounded-full hover:bg-orange-600 transition mt-4"
          >
            Criar novo paciente
          </button>
        </div>
      </div>
    </div>
  );
}
