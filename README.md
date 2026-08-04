# LeetScan

LeetScan is an AI-powered GitHub repository analysis platform that transforms any public GitHub repository into an intelligent, searchable knowledge base. Instead of manually exploring source files, developers can ask natural language questions about the codebase and receive context-aware answers generated using Retrieval-Augmented Generation (RAG).

The application automatically crawls repositories, indexes supported source files, generates vector embeddings using Google's Gemini Embedding API, stores them in PostgreSQL with PgVector, and retrieves the most relevant code snippets to answer developer queries accurately.

---

## Features

- Analyze public GitHub repositories through a repository URL or name.
- Supports repositories written in multiple programming languages.
- Intelligent repository crawling and indexing.
- Retrieval-Augmented Generation (RAG) for code understanding.
- Semantic code search using vector embeddings.
- AI-powered question answering over repository contents.
- Automatic chunking of source code before embedding.
- PostgreSQL + PgVector for vector storage.
- Spring Boot backend with LangChain4j integration.
- Google Gemini Embedding API for semantic indexing.
- Clean and modular architecture.

---

## Supported Languages

LeetScan currently indexes repositories containing files such as:

- Java
- Kotlin
- Groovy
- Python
- JavaScript
- TypeScript
- Go
- Rust
- C
- C++
- C#
- PHP
- Ruby
- Swift
- Scala
- Dart
- SQL
- Shell Scripts
- HTML
- CSS
- SCSS
- Vue
- Svelte
- JSON
- XML
- YAML
- TOML
- Markdown
- Properties files

---

## Tech Stack

### Backend

- Java 21
- Spring Boot 3
- Maven

### AI

- LangChain4j
- Google Gemini
- Gemini Embedding API
- Retrieval-Augmented Generation (RAG)

### Database

- PostgreSQL
- PgVector

### GitHub Integration

- GitHub REST API

---

## Project Architecture

```
                GitHub Repository
                        │
                        ▼
              Repository Crawler
                        │
                        ▼
               Source File Extraction
                        │
                        ▼
                Document Chunking
                        │
                        ▼
           Gemini Embedding Generation
                        │
                        ▼
              PostgreSQL + PgVector
                        │
                        ▼
              Semantic Retrieval
                        │
                        ▼
                Gemini AI Model
                        │
                        ▼
               Natural Language Answer
```

---

## How It Works

### 1. Repository Crawling

The application recursively scans the repository using the GitHub API and extracts supported source files.

---

### 2. Document Processing

Each file is converted into a document and split into smaller semantic chunks.

---

### 3. Embedding Generation

Every chunk is converted into a vector embedding using Google's Gemini Embedding API.

---

### 4. Vector Storage

Generated embeddings are stored inside PostgreSQL using the PgVector extension for efficient similarity search.

---

### 5. Semantic Retrieval

When a user asks a question, the query is converted into an embedding and compared against stored vectors.

The most relevant chunks are retrieved from the vector database.

---

### 6. AI Response

Retrieved repository context is passed to Gemini, which generates an accurate answer grounded only in the indexed repository.

---

## Project Structure

```
src
├── main
│   ├── java
│   │   └── com.shreyansh.LeetScan
│   │       ├── Agent
│   │       ├── Config
│   │       ├── Controller
│   │       ├── Service
│   │       └── LeetScanApplication.java
│   │
│   └── resources
│       ├── static
│       ├── templates
│       └── application.properties
│
└── test
```

---

## Installation

### Clone Repository

```bash
git clone https://github.com/shreyansh-Geek/LeetScan.git
cd LeetScan
```

---

### Configure Environment

Create an `application-local.properties` file and configure the following values:

```properties
GEMINI_API_KEY=YOUR_GEMINI_API_KEY
GITHUB_TOKEN=YOUR_GITHUB_TOKEN

DB_URL=jdbc:postgresql://localhost:5432/vector_db
DB_USERNAME=postgres
DB_PASSWORD=your_password
```

---

### PostgreSQL

Install PostgreSQL and enable the PgVector extension.

Create a database named:

```text
vector_db
```

---

### Build

```bash
mvn clean install
```

---

### Run

```bash
mvn spring-boot:run
```

The application starts on:

```
http://localhost:8082
```

---

## Example Questions

Users can ask questions like:

- Explain this repository.
- What is the purpose of this project?
- How does authentication work?
- Explain the repository architecture.
- Which files are responsible for vector storage?
- How are embeddings generated?
- Explain the crawler implementation.
- Which technologies are used?
- How does semantic retrieval work?

---

## Current Limitations

- Public GitHub repositories only.
- Repository must be indexed before querying.
- Binary files are ignored.
- Performance depends on repository size.
- Answers are limited to indexed repository content.

---

## Future Enhancements

- Support private GitHub repositories.
- Hybrid search (Semantic + Keyword).
- Repository architecture visualization.
- Multi-repository knowledge base.
- Incremental indexing.
- Code dependency graph generation.
- Repository comparison.
- Conversation memory.
- Streaming AI responses.

---

## Contributing

Contributions are welcome.

1. Fork the repository.
2. Create a feature branch.

```bash
git checkout -b feature/your-feature
```

3. Commit your changes.

```bash
git commit -m "feat: add new feature"
```

4. Push the branch.

```bash
git push origin feature/your-feature
```

5. Open a Pull Request.

---

## License

This project is licensed under the MIT License.

---

## Author

**Shreyansh Pandit**

GitHub: https://github.com/shreyansh-Geek
