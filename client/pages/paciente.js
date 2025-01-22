import { useState } from "react";
import { useRouter } from "next/router";
import axios from "axios";

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

        // Redireciona para a página de input dos macros
        router.push({ pathname: "/input", query: { pacienteId: pacienteSelecionado } });
      } else {
        // Salva novo paciente
        const response = await axios.post(PACIENTES_URL, novoPaciente);
        const paciente = response.data;

        // Redireciona para a página de input dos macros
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
    <div>
      <h1>Informações do Paciente</h1>

      <h2>Cadastrar ou Atualizar Paciente</h2>
      <form onSubmit={(e) => e.preventDefault()}>
        <label>
          Nome:
          <input
            type="text"
            name="nome"
            placeholder="Nome"
            value={novoPaciente.nome}
            onChange={handleNovoPacienteChange}
          />
        </label>
        <label>
          Idade:
          <input
            type="number"
            name="idade"
            placeholder="Idade"
            value={novoPaciente.idade}
            onChange={handleNovoPacienteChange}
          />
        </label>
        <label>
          Altura (cm):
          <input
            type="number"
            name="altura"
            placeholder="Altura (cm)"
            value={novoPaciente.altura}
            onChange={handleNovoPacienteChange}
          />
        </label>
        <label>
          Peso (kg):
          <input
            type="number"
            name="peso"
            placeholder="Peso (kg)"
            value={novoPaciente.peso}
            onChange={handleNovoPacienteChange}
          />
        </label>
        <label>
          Gênero:
          <select
            name="genero"
            value={novoPaciente.genero}
            onChange={handleNovoPacienteChange}
          >
            <option value="">Selecione</option>
            <option value="masculino">Masculino</option>
            <option value="feminino">Feminino</option>
          </select>
        </label>
        <label>
          Atividade Física:
          <select
            name="atividadeFisica"
            value={novoPaciente.atividadeFisica}
            onChange={handleNovoPacienteChange}
          >
            <option value="">Selecione</option>
            <option value="1">Sedentário</option>
            <option value="1.5">Moderado</option>
            <option value="2">Intenso</option>
          </select>
        </label>
        <button type="button" onClick={salvarPaciente}>
          {pacienteSelecionado ? "Atualizar Paciente" : "Salvar Paciente"}
        </button>
      </form>

      <h2>Selecionar Paciente Existente</h2>
      <button onClick={buscarPacientes} disabled={loadingPacientes}>
        {loadingPacientes ? "Carregando..." : "Buscar Pacientes"}
      </button>
      {pacientes.length > 0 && (
        <ul>
          {pacientes.map((paciente) => (
            <li key={paciente.id}>
              {paciente.nome} ({paciente.id})
              <button onClick={() => selecionarPacienteExistente(paciente.id)}>
                Selecionar
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
