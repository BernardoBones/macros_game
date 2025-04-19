import React from 'react';
import { useRouter } from 'next/router';  
import Image from "next/image";
import logo from "../assets/logo.png"; 

const Home = () => {
  const router = useRouter();  

  const handleRedirect = () => {
    router.push('/paciente'); 
  };

  return (
    <div className="flex flex-col md:flex-row h-screen bg-lime-100">
      {/* Lado esquerdo com a logo */}
      <div className="flex-1 flex justify-center items-center p-4">
        <div className="min-h-screen bg-lime-100 flex flex-col items-center justify-center p-8">
            <div className="flex flex-col items-center">
                <Image src={logo} alt="Logo" width={250} height={150} />
                <button
                className="mt-8 bg-orange-500 text-white font-bold px-6 py-2 rounded-full shadow-md hover:bg-orange-600 transition duration-300"
                onClick={handleRedirect} 
                >
                Iniciar o Jogo
                </button>
            </div>
        </div>
      </div>

      {/* Lado direito com texto e decoração */}
      <div className="relative flex-1 flex flex-col justify-center p-6">
        {/* Linha vertical */}
        <div className="absolute left-4 top-10 bottom-10 w-1 bg-orange-400 rounded-full">
          <div className="absolute top-0 left-1/2 transform -translate-x-1/2 w-3 h-3 bg-orange-400 rounded-full"></div>
          <div className="absolute bottom-0 left-1/2 transform -translate-x-1/2 w-3 h-3 bg-orange-400 rounded-full"></div>
        </div>

        {/* Texto principal */}
        <div className="ml-10 mb-10">
          <div className="w-4 h-4 border-4 border-green-900 bg-orange-400 rounded-full mb-4"></div>
          <p className="text-green-900 text-lg">
            O MacrosGame foi desenvolvido com o intuito de ajudar, você
            estudante, a exercitar o uso da fórmula de cálculo energético de
            Harries e Benedict.
          </p>
        </div>

        {/* Texto final */}
        <div className="ml-10 mt-10 flex justify-between items-center">
          <p className="text-green-900">Te desejamos um bom treinamento e boa sorte!</p>
          <div className="w-5 h-5 border-4 border-green-900 bg-orange-400 rounded-full"></div>
        </div>
      </div>
    </div>
  );
}

export default Home;
