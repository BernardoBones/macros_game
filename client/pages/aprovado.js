// pages/final.tsx
import { useRouter } from "next/router";
import Image from "next/image";

import logo from "../assets/logo.png";
import estrelas from "../assets/estrelas.png";

export default function FinalPage() {
  const router = useRouter();

  const handleContinuar = (continuar) => {
    if (continuar) {
      router.push("/paciente"); // ajuste para a próxima etapa real
    } else {
      router.push("/"); // volta para a tela inicial
    }
  };

  return (
    <div className="min-h-screen bg-lime-100 p-8 flex flex-col justify-between">
      {/* Logo no canto superior direito */}
      <div className="flex justify-end">
        <Image src={logo} alt="Logo Macros Game" width={120} height={80} />
      </div>

      {/* Mensagem principal */}
      <div className="text-center flex justify-evenly text-green-900 font-bold mb-6">
        <h2 className="text-3xl mb-4">Muito bem!</h2>
        <Image src={estrelas} alt="Estrelas" width={80} height={80} />
      </div>
      {/* Mensagem principal */}
      <div className="text-center text-green-900 font-bold mb-6">
        <h2 className="text-2xl mb-2">Você teve um ótimo desempenho e acertou tudo!</h2>
      </div>

      {/* Pergunta de continuação */}
      <div className="text-center text-green-900 font-bold mb-6">
        <h2 className="text-xl">Deseja continuar jogando?</h2>
      </div>

      {/* Botões */}
      <div className="flex justify-center gap-12 items-center text-green-900 font-bold text-xl">
        <button
          onClick={() => handleContinuar(true)}
          className="flex items-center gap-2"
        >
          <span className="w-5 h-5 rounded-full inline-block border-2 border-green-900" />
          SIM
        </button>
        <button
          onClick={() => handleContinuar(false)}
          className="flex items-center gap-2"
        >
          <span className="w-5 h-5 border-2 border-green-900 rounded-full inline-block" />
          NÃO
        </button>
      </div>
    </div>
  );
}
