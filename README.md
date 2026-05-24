# QuizMachine by Aniket

A Spring Boot based MCQ quiz platform that parses questions from PDFs and generates interactive online tests with automated evaluation.
Users can upload:
- **a combined question-answer PDF**
- **separate question and answer PDFs**
  
The system automatically parses questions, options, answers, and generates a live quiz with instant scoring.


# Live link: 
https://quizmachine-by-aniket.onrender.com



# Features
========================
- PDF-based MCQ extraction
- Combined PDF upload support
- Separate Question/Answer PDF support
- Automatic answer parsing using Regex
- Interactive quiz UI
- Real-time scoring
- REST API backend
- Spring Boot architecture
- Apache PDFBox integration
- Responsive frontend UI
- Simple frontend using HTML, CSS, and JavaScript


# Test Pdfs
=========================

-The platform can be directly tested using the sample pdfs provided.
-Combined pdf: **QuizMachine combined sample.pdf**
-separate pdf: **QuizMachine Questions sample.pdf**, **QuizMachine Answers sample.pdf**

  

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
- Docker
- Render


# Project Structure:
========================

src/
 ├── controller/
 ├── service/
 ├── parser/
 ├── model/
 └── resources/
 
 Main components:
- PdfQuestionParser
- PdfAnswerParser
- QuizService
- QuizController
- QuizUploadController


# How it works:
========================

when run Locally: 
In Terminal:
./mvnw spring-boot:run
 Then open:
http://localhost:8080
OR
Live Link(Mentioned above)
1. User uploads PDF(s)
2. Backend extracts raw text using PDFBox
3. Regex-based parsers identify:
   - questions
   - options
   - correct answers
4. Questions are converted into Question objects
5. Frontend renders live quiz
6. User submits answers
7. Backend evaluates score


# Future Improvements:

========================
Better UI/UX
AI-assisted parsing improvements
Authentication system
Database integration
Timer based quizzes
Negative Marking
Leaderboards
Cloud deployment



# Known Limitations
========================

- Currently optimized for English PDFs only
- Some instruction blocks at the beginning of PDFs may get parsed into Question 1
- Parsing accuracy depends on PDF formatting consistency
- Highly image-based/scanned PDFs are not fully supported yet


Author:
Aniket Desai
