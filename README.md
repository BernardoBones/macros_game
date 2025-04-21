# 🥦 Macros Game

**Macros Game** é um jogo interativo desenvolvido para auxiliar estudantes e profissionais da área de nutrição a aprender e testar seus conhecimentos sobre macronutrientes (proteínas, carboidratos e lipídios) de maneira divertida.

O objetivo é propor desafios nutricionais nos quais o jogador deve identificar a distribuição correta dos macros com base em informações sobre pacientes fictícios.

O cálculo é baseado na equação de Harris-Benedict que calcula a Taxa Metabólica Basal e múltiplica pelo Fator Atividade

Referências:
https://pmc.ncbi.nlm.nih.gov/articles/PMC1091498/

https://openknowledge.fao.org/server/api/core/bitstreams/65875dc7-f8c5-4a70-b0e1-f429793860ae/content


---

## 🚀 Funcionalidades

- 🔍 **Leitura de fichas nutricionais**: O jogo apresenta pacientes com dados nutricionais e objetivos específicos.
- 🎯 **Desafios de macros**: O jogador deve selecionar a combinação correta de proteínas, carboidratos e lipídios.
- ⚖️ **Sistema de feedback**: Após cada tentativa, o jogo mostra se o jogador acertou ou errou, com feedback visual e informativo sobre os erros.
- 📊 **Análise de adequação**: Avaliação automática de cada macronutriente e calorias com base nos objetivos do paciente.
- 🌐 **Frontend intuitivo**: Interface simples e responsiva feita com HTML, CSS e JavaScript (e um toque de estilo com Tailwind no novo design).
- ⚙️ **Backend em Java**: Lógica de avaliação e regras de negócio implementadas em Java com persistência de dados em PostgreSQL.

---

## 🛠️ Tecnologias Utilizadas

- **Frontend**: HTML, CSS, JavaScript, Tailwind CSS
- **Backend**: Java (Spring Boot)
- **Banco de Dados**: PostgreSQL

---

## 🧭 Estrutura do Projeto

```
macros_game/
├── client/       # Código-fonte do frontend
├── server/       # Backend em Java
└── .idea/        # Arquivos de configuração da IDE
```

---

## ▶️ Como Executar Localmente

### Pré-requisitos

- Java 17+
- Node.js + npm
- PostgreSQL (com banco configurado)

### Instruções

1. Clone o repositório:
   ```bash
   git clone https://github.com/BernardoBones/macros_game.git
   cd macros_game
   ```

2. Configure e execute o backend:
   ```bash
   cd server
   ./mvnw spring-boot:run
   ```

3. Configure e execute o frontend:
   ```bash
   cd client
   npm install
   npm run dev
   ```

---


## 🤝 Contribuições

Contribuições são super bem-vindas! Se você quiser adicionar novos desafios, melhorar o visual ou otimizar o backend, é só abrir uma issue ou um pull request.

---

Feito com dedicação e um toque de criatividade nutricional 🧠💪
