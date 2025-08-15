# YouTube Transcript Search Engine

A Spring Boot-based web application that provides intelligent search functionality for YouTube video transcripts using multiple search algorithms.

## 🚀 Features

- **Multi-Algorithm Search**: Implements three different search algorithms:
  - Simple Search Service
  - Threshold-based Search Service
  - Frequency-based Search Service
- **Web Interface**: Clean and responsive web UI for searching transcripts
- **Database Integration**: H2 in-memory database for data persistence
- **Click Tracking**: Tracks user interactions with search results
- **Excel Data Import**: Supports importing transcript data from Excel files

## 🛠️ Technology Stack

- **Backend**: Spring Boot 3.1.5
- **Java Version**: 17
- **Database**: H2 (In-Memory)
- **Template Engine**: Thymeleaf
- **Build Tool**: Maven
- **Dependencies**:
  - Spring Boot Starter Web
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Thymeleaf
  - Apache POI (Excel processing)
  - Apache Commons Lang3
  - H2 Database

## 📋 Prerequisites

Before running this application, make sure you have the following installed:

- Java 17 or higher
- Maven 3.6 or higher
- Git

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd Youtube-Transcript-Search-Engine
```

### 2. Navigate to Project Directory

```bash
cd transcriptengine
```

### 3. Build the Project

```bash
./mvnw clean install
```

### 4. Run the Application

```bash
./mvnw spring-boot:run
```

Or using Maven wrapper on Windows:

```bash
mvnw.cmd spring-boot:run
```

### 5. Access the Application

Open your web browser and navigate to:
- **Main Application**: http://localhost:8080
- **H2 Database Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `dmasood`
  - Password: `masood176`

## 📁 Project Structure

```
transcriptengine/
├── src/
│   ├── main/
│   │   ├── java/com/example/transcriptengine/transcriptengine/
│   │   │   ├── config/
│   │   │   │   └── AppConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── DataController.java
│   │   │   │   ├── ResultController.java
│   │   │   │   └── SearchController.java
│   │   │   ├── dto/
│   │   │   │   └── ClickRequest.java
│   │   │   ├── repository/
│   │   │   │   └── LinkClickRepository.java
│   │   │   ├── service/
│   │   │   │   ├── DataService.java
│   │   │   │   ├── FrequencyBasedSearchService.java
│   │   │   │   ├── LinkClickService.java
│   │   │   │   ├── SearchAlgoInterface.java
│   │   │   │   ├── SimpleSearchService.java
│   │   │   │   └── ThreshHoldSearchService.java
│   │   │   └── TranscriptengineApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── templates/
│   │       │   ├── result.html
│   │       │   └── searchEngine.html
│   │       └── styles.css
│   └── test/
│       └── java/com/example/transcriptengine/transcriptengine/
│           └── TranscriptengineApplicationTests.java
├── Transcript.xls
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## 🔍 How It Works

### Search Algorithms

1. **Simple Search Service**: Basic text matching algorithm
2. **Threshold-based Search Service**: Uses threshold criteria for relevance scoring
3. **Frequency-based Search Service**: Analyzes word frequency for better relevance

### Data Flow

1. User enters search query on the web interface
2. Query is processed by all three search algorithms simultaneously
3. Results are combined and ranked
4. Results are displayed with click tracking capabilities

## 📊 Data Management

The application uses an H2 in-memory database and can import transcript data from Excel files. The `Transcript.xls` file contains the video transcript data that powers the search functionality.

## 🎯 Usage

1. **Search Interface**: Visit http://localhost:8080 to access the search interface
2. **Enter Query**: Type your search terms in the search box
3. **View Results**: Results from all three algorithms will be displayed
4. **Click Tracking**: Click on results to track user interactions

## 🔧 Configuration

Key configuration settings in `application.properties`:

- **Database**: H2 in-memory database
- **Template Engine**: Thymeleaf with HTML templates
- **H2 Console**: Enabled for database management
- **Logging**: Configurable debug levels

## 🧪 Testing

Run the test suite:

```bash
./mvnw test
```

## 📝 API Endpoints

- `GET /` - Main search interface
- `POST /search` - Perform search with query parameter
- `GET /h2-console` - H2 database console

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

If you encounter any issues or have questions:

1. Check the application logs for error messages
2. Verify that Java 17+ is installed
3. Ensure all dependencies are properly resolved
4. Check the H2 console for database connectivity

## 🔮 Future Enhancements

- [ ] Add more sophisticated search algorithms
- [ ] Implement user authentication
- [ ] Add support for real-time transcript updates
- [ ] Enhance the UI with modern frameworks
- [ ] Add API documentation with Swagger
- [ ] Implement caching for better performance 