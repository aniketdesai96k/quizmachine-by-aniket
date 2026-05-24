# QuizMachine by Aniket
========================
A Spring Boot based MCQ quiz platform that parses questions from PDFs and generates interactive online tests with automated evaluation.


# Live link: 
https://quizmachine-by-aniket.onrender.com



# Features
========================
- Upload question PDFs
- Parse MCQs automatically using Apache PDFBox
- Generate interactive quizzes
- One-question-at-a-time quiz mode
- Instant answer feedback
- Automated score calculation
- REST API based backend
- Simple frontend using HTML, CSS, and JavaScript

  

# Instructions
========================
- The platform currently supports *English-only PDFs*.
- If the uploaded PDF contains instructions at the beginning, they may be interpreted as part of the first question during parsing.
- Ensure the PDF is formatted with clear MCQ structure for best results.
-Quiz my take around 60 seconds to launch


# Tech Stack:
========================
- Java
- Spring Boot
- Maven
- Apache PDFBox
- HTML
- CSS
- JavaScript


# Project Structure:
========================
src/
 ├── controller/
 ├── service/
 ├── parser/
 ├── model/
 └── resources/


# How to run:
========================
In Terminal:
./mvnw spring-boot:run
Then open:
http://localhost:8080


# Future Improvements:
========================
Better UI/UX
Authentication system
Database integration
Timer based quizzes
Leaderboards
Cloud deployment


Author:
Aniket Desai
