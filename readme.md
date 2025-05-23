# 🎮 DSList - Game Management API

Uma API REST desenvolvida em **Spring Boot** para gerenciamento de listas de jogos, permitindo organizar, visualizar e reordenar jogos em diferentes categorias.

## 📋 Sobre o Projeto

O DSList é uma aplicação backend que oferece funcionalidades para:

- Listar jogos disponíveis
- Organizar jogos em listas temáticas
- Reordenar jogos dentro das listas
- Consultar detalhes específicos de cada jogo

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Web**
- **H2 Database** (desenvolvimento)
- **PostgreSQL** (produção)
- **Maven** (gerenciamento de dependências)

## 📊 Modelo de Dados

O projeto utiliza as seguintes entidades principais:

- **Game**: Representa um jogo com informações como título, ano, gênero, plataformas, etc.
- **GameList**: Representa uma lista temática de jogos (ex: "Aventura", "RPG")
- **Belonging**: Entidade de associação que define a posição de um jogo em uma lista específica

## 🛠️ Configuração e Instalação

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

### Instalação

1. **Clone o repositório**

```bash
git clone https://github.com/Sinnisterr/dslist.git
cd dslist
```

2. **Execute o projeto**

```bash
mvn spring-boot:run
```

3. **Acesse a aplicação**

- URL base: `http://localhost:8080`
- Console H2: `http://localhost:8080/h2-console`

## 📚 Endpoints da API

### 🎲 Jogos

#### Listar todos os jogos

```http
GET /games
```

#### Buscar jogo por ID

```http
GET /games/{id}
```

**Exemplo de resposta:**

```json
{
	"id": 1,
	"title": "Mass Effect Trilogy",
	"year": 2012,
	"genre": "Role-playing (RPG), Shooter",
	"platforms": "XBox, Playstation, PC",
	"score": 4.8,
	"imgUrl": "https://example.com/image.jpg",
	"shortDescription": "Lorem ipsum dolor sit amet...",
	"longDescription": "Lorem ipsum dolor sit amet..."
}
```

### 📋 Listas de Jogos

#### Listar todas as listas

```http
GET /lists
```

#### Buscar jogos de uma lista específica

```http
GET /lists/{listId}/games
```

#### Reordenar jogos em uma lista

```http
POST /lists/{listId}/games/replacement
Content-Type: application/json

{
  "sourceIndex": 3,
  "destinationIndex": 1
}
```

## 🧪 Exemplos de Uso

### Listar todos os jogos

```bash
curl -X GET http://localhost:8080/games
```

### Obter detalhes de um jogo específico

```bash
curl -X GET http://localhost:8080/games/1
```

### Listar todas as categorias de jogos

```bash
curl -X GET http://localhost:8080/lists
```

### Obter jogos de uma lista específica

```bash
curl -X GET http://localhost:8080/lists/2/games
```

### Mover um jogo da posição 3 para posição 1

```bash
curl -X POST http://localhost:8080/lists/2/games/replacement \
  -H "Content-Type: application/json" \
  -d '{
    "sourceIndex": 3,
    "destinationIndex": 1
  }'
```

## 🏗️ Arquitetura do Projeto

```
src/main/java/com/devsuperior/dslist/
├── controllers/         # Controladores REST
│   ├── GameController.java
│   └── GameListController.java
├── dto/                # Data Transfer Objects
│   ├── GameDTO.java
│   ├── GameMinDTO.java
│   ├── GameListDTO.java
│   └── ReplacementDTO.java
├── entities/           # Entidades JPA
│   ├── Game.java
│   ├── GameList.java
│   ├── Belonging.java
│   └── BelongingPK.java
├── projections/        # Projeções para consultas
│   └── GameMinProjection.java
├── repositories/       # Repositórios JPA
│   ├── GameRepository.java
│   └── GameListRepository.java
└── services/          # Camada de negócio
    ├── GameService.java
    └── GameListService.java
```

## 🗄️ Banco de Dados

### Estrutura das Tabelas

**tb_game**

- id (PK)
- title
- game_year
- genre
- platforms
- score
- img_url
- short_description
- long_description

**tb_game_list**

- id (PK)
- name

**tb_belonging**

- game_id (FK)
- list_id (FK)
- position

## 🌍 Deploy

### Configuração para Produção

1. **Configure o banco PostgreSQL** no `application-prod.properties`
2. **Configure as variáveis de ambiente**:

   - `DATABASE_URL`
   - `DATABASE_USERNAME`
   - `DATABASE_PASSWORD`

3. **Build da aplicação**:

```bash
mvn clean package -Pprod
```

## 👤 Autor

**Willian Bruno**

- GitHub: (https://github.com/Sinnisterr)
- LinkedIn: (https://www.linkedin.com/in/willian-bruno-28924082/)

## 🙏 Agradecimentos

- [DevSuperior](https://devsuperior.com.br/) - Pela metodologia e estrutura do projeto
- Comunidade Spring Boot
- Todos que contribuíram com feedback e sugestões

---

⭐ **Se este projeto te ajudou, deixe uma estrela no repositório!**
