package com.codewars.chrisgw.mcduell;

import org.apache.poi.ss.usermodel.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.trimToNull;


public class McDuellQuestionParser {

    public static final Pattern QUESTION_TEXT_PATTERN = Pattern.compile("([A-Z0-9\\-]+)-(\\d+)\\s+(.++)");
    public static final Pattern ANSWER_TEXT_PATTERN = Pattern.compile("\\s*([a-z])\\)(.+)");

    private Workbook workbook;
    private Sheet sheet;
    private Document doc;


    private McDuellQuestionParser(Workbook workbook, Document doc) {
        this.workbook = workbook;
        this.sheet = workbook.getSheetAt(0);
        this.doc = doc;
    }

    public static Document convertMcDuellQuestionExcelIntoXml(File excelFileInput) {
        try (Workbook workbook = WorkbookFactory.create(excelFileInput)) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            String quizName = excelFileInput.getName();
            quizName = quizName.substring(0, quizName.lastIndexOf("."));
            return new McDuellQuestionParser(workbook, doc).convertMcDuellQuestionExcelIntoXml(quizName);
        } catch (Exception e) {
            throw new RuntimeException("could not create McDuell question xml", e);
        }
    }

    private Document convertMcDuellQuestionExcelIntoXml(String quizName) {
        Element quiz = doc.createElement("quiz");
        doc.appendChild(quiz);
        quiz.appendChild(crateQuizCategoryName(quizName));

        List<QuizQuestion> quizQuestions = parseQuizQuestions();
        for (QuizQuestion quizQuestion : quizQuestions) {
            Element question = convertToQuestionElement(quizQuestion);
            quiz.appendChild(question);
        }
        return doc;
    }


    private List<QuizQuestion> parseQuizQuestions() {
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        QuizQuestion currentQuizQuestion = null;
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell == null || cell.getCellType() != Cell.CELL_TYPE_STRING) {
                continue;
            }
            String value = cell.getStringCellValue();
            Matcher questionTextMatcher = QUESTION_TEXT_PATTERN.matcher(value);
            Matcher answerTextMatcher = ANSWER_TEXT_PATTERN.matcher(value);
            if (questionTextMatcher.matches()) {
                currentQuizQuestion = newQuizQuestion(questionTextMatcher);
                quizQuestions.add(currentQuizQuestion);
            } else if (answerTextMatcher.matches() && currentQuizQuestion != null) {
                QuizAnswer quizAnswer = newQuestionAnswer(answerTextMatcher);
                currentQuizQuestion.addAnswer(quizAnswer);
            } else if (value.startsWith("Vls.")) {
                // skip vls
            } else {
                int rowIndex = cell.getRowIndex();
                int columnIndex = cell.getColumnIndex();
                System.out.printf("Unknown CellText at (%2d;%2d) = %s\n", rowIndex, columnIndex, value);
            }
        }
        return quizQuestions;
    }

    private QuizQuestion newQuizQuestion(Matcher questionTextMatcher) {
        QuizQuestion quizQuestion = new QuizQuestion();
        String frageName = questionTextMatcher.group(1) + "-" + questionTextMatcher.group(2);
        quizQuestion.setFrageName(frageName);
        quizQuestion.setText(trimToNull(questionTextMatcher.group(3)));
        quizQuestion.setType("multichoice");
        return quizQuestion;
    }

    private QuizAnswer newQuestionAnswer(Matcher answerTextMatcher) {
        QuizAnswer quizAnswer = new QuizAnswer();
        quizAnswer.setFormat("html");
        String answerText = answerTextMatcher.group(2).replace("\u00a0", "");
        quizAnswer.setText(trimToNull(answerText));
        String answerChoice = answerTextMatcher.group(1);
        if ("a".equals(answerChoice)) {
            quizAnswer.setFraction(100);
        } else {
            quizAnswer.setFraction(0);
        }
        return quizAnswer;
    }


    private Element crateQuizCategoryName(String quizName) {
        Element questionKategorie = doc.createElement("question");
        questionKategorie.setAttribute("type", "category");

        Element category = doc.createElement("category");
        questionKategorie.appendChild(category);
        category.appendChild(toTextElement("$course$/" + quizName));
        return questionKategorie;
    }


    private Element convertToQuestionElement(QuizQuestion quizQuestion) {
        Element question = doc.createElement("question");
        question.setAttribute("type", quizQuestion.getType());

        Element name = doc.createElement("name");
        name.appendChild(toTextElement(quizQuestion.getFrageName()));
        question.appendChild(name);

        Element questiontext = doc.createElement("questiontext");
        questiontext.setAttribute("format", "html");
        String frageText = quizQuestion.getFrageName() + " " + quizQuestion.getText();
        questiontext.appendChild(toTextElement(frageText));
        question.appendChild(questiontext);

        for (QuizAnswer quizAnswer : quizQuestion.getAnswers()) {
            Element answerElement = convertToQuestionAnswerElement(quizAnswer);
            question.appendChild(answerElement);
        }
        return question;
    }

    private Element convertToQuestionAnswerElement(QuizAnswer quizAnswer) {
        Element answerElement = doc.createElement("answer");
        answerElement.setAttribute("fraction", String.valueOf(quizAnswer.getFraction()));
        answerElement.setAttribute("format", quizAnswer.getFormat());
        answerElement.appendChild(toTextElement(quizAnswer.getText()));
        return answerElement;
    }

    public Element toTextElement(String text) {
        Element textElement = doc.createElement("text");
        textElement.setTextContent(text);
        return textElement;
    }


    public static void main(String[] args) throws IOException {
        Path directory = Paths.get("src\\main\\resources\\MC-Duell_Neues_Studiendesign");
        Files.walk(directory).filter(path -> path.toString().endsWith(".xlsx")).forEach(excelFile -> {
            System.out.println("read fragenkatalog excel from: " + excelFile);
            Document document = convertMcDuellQuestionExcelIntoXml(excelFile.toFile());

            String xmlFileName = excelFile.getFileName().toString().replace(".xlsx", ".xml");
            Path xmlFile = excelFile.resolveSibling(xmlFileName);
            System.out.println("write fragenkatalog xml to: " + xmlFile);
            writeMcDuellQuestionXml(document, xmlFile.toFile());
        });
    }

    private static void writeMcDuellQuestionXml(Document document, File frageKatalogeXml) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(frageKatalogeXml);
            transformer.transform(domSource, streamResult);
        } catch (Exception e) {
            throw new RuntimeException("could not write MC-Duell quiz xml", e);
        }
    }

}
