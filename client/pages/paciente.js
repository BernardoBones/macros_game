import { useState } from "react";
import { useRouter } from "next/router";
import axios from "axios";
import Image from "next/image"; 
import logo from "../assets/logo.png"; 

export default function InputPage() {
  const [novoPaciente, setNovoPaciente] = useState({
    nome: "",
    idade: "",
    altura: "",
    peso: "",
    genero: "",
    atividadeFisica: "",
  });

  const PACIENTES_URL = "http://localhost:8080/pacientes";
  const [pacientes, setPacientes] = useState([]);
  const [pacienteSelecionado, setPacienteSelecionado] = useState(null);
  const router = useRouter();
  const [loadingPacientes, setLoadingPacientes] = useState(false);

  const handleNovoPacienteChange = (e) => {
    const { name, value } = e.target;
    setNovoPaciente({ ...novoPaciente, [name]: value });
  };

  const buscarPacientes = async () => {
    setLoadingPacientes(true);
    try {
      const response = await axios.get(PACIENTES_URL);
      if (Array.isArray(response.data)) {
        setPacientes(response.data);
      } else {
        throw new Error("Dados inválidos recebidos da API");
      }
    } catch (error) {
      console.error("Erro ao buscar pacientes:", error);
      alert("Erro ao buscar pacientes");
    } finally {
      setLoadingPacientes(false);
    }
  };

  const salvarPaciente = async () => {
    try {
      if (pacienteSelecionado) {
        await axios.put(`${PACIENTES_URL}/${pacienteSelecionado}`, novoPaciente);
        router.push({ pathname: "/input", query: { pacienteId: pacienteSelecionado } });
      } else {
        const response = await axios.post(PACIENTES_URL, novoPaciente);
        const paciente = response.data;
        console.log('paciente', paciente.id)
        router.push({ pathname: "/input", query: { pacienteId: paciente.id } });
      }
    } catch (error) {
      console.error("Erro ao salvar paciente:", error);
      alert("Erro ao salvar paciente");
    }
  };

  const selecionarPacienteExistente = (id) => {
    const paciente = pacientes.find((p) => p.id === id);
    if (paciente) {
      setNovoPaciente({
        nome: paciente.nome,
        idade: paciente.idade,
        altura: paciente.altura,
        peso: paciente.peso,
        genero: paciente.genero,
        atividadeFisica: paciente.atividadeFisica,
      });
      setPacienteSelecionado(paciente.id);
    }
  };

  return (
    <div className="min-h-screen bg-lime-100 flex flex-col md:flex-row justify-between p-6 md:p-12">
      {/* Esquerda - Formulário */}
      <div className="flex flex-col gap-4" id="form_paciente">
        <h2 className="text-2xl font-bold text-orange-500 uppercase">INFORMAÇÕES DO PACIENTE:</h2>

        {/* Nome */}
        <label className="flex items-center gap-4 text-green-900">
          COMO É O SEU NOME?
          <input
            type="text"
            name="nome"
            value={novoPaciente.nome}
            onChange={handleNovoPacienteChange}
            className="border-2 border-green-900 rounded-full px-4 py-1 w-60"
          />
        </label>

        {/* Gênero */}
        <label className="flex items-center gap-4 text-green-900">
          QUAL É O SEU GÊNERO?
          <select
            name="genero"
            value={novoPaciente.genero}
            onChange={handleNovoPacienteChange}
            className="border-2 border-green-900 rounded-full px-4 py-1 w-60"
          >
            <option value="">Selecione</option>
            <option value="masculino">Masculino</option>
            <option value="feminino">Feminino</option>
          </select>
        </label>

        {/* Idade */}
        <label className="flex items-center gap-4 text-green-900">
          QUAL É A SUA IDADE?
          <input
            type="number"
            name="idade"
            value={novoPaciente.idade}
            onChange={handleNovoPacienteChange}
            className="border-2 border-green-900 rounded-full px-4 py-1 w-32"
          />
        </label>

        {/* Peso */}
        <label className="flex items-center gap-4 text-green-900">
          QUAL É O SEU PESO?
          <input
            type="number"
            name="peso"
            value={novoPaciente.peso}
            onChange={handleNovoPacienteChange}
            className="border-2 border-green-900 rounded-full px-4 py-1 w-32"
          />
        </label>

        {/* Altura */}
        <label className="flex items-center gap-4 text-green-900">
          QUAL A SUA ALTURA?
          <input
            type="number"
            name="altura"
            value={novoPaciente.altura}
            onChange={handleNovoPacienteChange}
            className="border-2 border-green-900 rounded-full px-4 py-1 w-20"
          />
        </label>

        {/* Nível de Atividade Física */}
        <label className="flex items-center gap-4 text-green-900">
          QUAL O SEU NÍVEL DE ATIVIDADE FÍSICA?
          <select
            name="atividadeFisica"
            value={novoPaciente.atividadeFisica}
            onChange={handleNovoPacienteChange}
            className="border-2 border-green-900 rounded-full px-4 py-1 w-60"
          >
            <option value="">Selecione</option>
            <option value="1.2">Sedentário</option>
            <option value="1.375">Levemente ativo</option>
            <option value="1.55">Moderadamente ativo</option>
            <option value="1.725">Muito ativo</option>
            <option value="1.9">Extremamente ativo/Atleta</option>
          </select>
        </label>

        {/* Botão de Salvar ou Atualizar */}
        <button
          type="button"
          onClick={salvarPaciente}
          className="mt-6 bg-orange-500 text-white font-bold px-6 py-2 rounded-full shadow-md hover:bg-orange-600 transition duration-300"
        >
          {pacienteSelecionado ? "Atualizar Paciente" : "Salvar Paciente"}
        </button>
      </div>

      {/* Direita - Instruções + logo */}
      <div className="md:w-1/3 flex flex-col justify-between">
      <div className="flex justify-end">
        <Image src={logo} alt="Logo Macros Game" width={120} height={80} />
      </div>

        <div className="text-red-600 mt-8 text-sm md:text-base" id="cuidado_p">
          <p className="mb-4">
            Cuidado! É sempre importante informar o valor corretamente.
            Caso use a unidade incorreta todo seu cálculo será perdido. A altura deve ser informada em cm e não m.
          </p>
          <p>
            Sem o nível de atividade física não conseguimos calcular o Valor Energético Total (VET), apenas o Valor Energético Basal.
            Lembre-se que são valores diferentes e avalie bem cada caso!
          </p>
        </div>
      </div>
    </div>
  );
}
