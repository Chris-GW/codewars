package com.codewars.chrisgw.mcduell;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
import java.util.Base64;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;


public class McDuellImageQuestionParser {

    private Document doc;
    private Path imageBasePath;


    private McDuellImageQuestionParser(Document doc, Path imageBasePath) {
        this.doc = doc;
        this.imageBasePath = imageBasePath;
    }

    public static Document convertMcDuellImageQuestion(File quizFile) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(quizFile);
            Path imageBasePath = quizFile.toPath().resolveSibling(quizFile.getName().replace(".xml", ""));
            return new McDuellImageQuestionParser(doc, imageBasePath).convertMcDuellImageQuestion();
        } catch (Exception e) {
            throw new RuntimeException("could not create McDuell question xml", e);
        }
    }

    private Document convertMcDuellImageQuestion() {
        doc.getDocumentElement().normalize();
        NodeList questions = doc.getElementsByTagName("question");
        for (int i = 0; i < questions.getLength(); i++) {
            Element question = (Element) questions.item(i);
            String type = question.getAttribute("type");
            if (!"multichoice".equalsIgnoreCase(type)) {
                System.out.println("skip non question node: question type=" + type);
                continue;
            }
            convertImageQuestionNode(question);
        }
        return doc;
    }

    private void convertImageQuestionNode(Element question) {
        Element questionTextEl = (Element) question.getElementsByTagName("questiontext").item(0);

        String questionName = findQuestionName(question);
        String questionImageEncoded = encodeQuestionImage(questionName);
        String questionText = findQuestionText(questionTextEl);
        System.out.println(questionName + ":\t" + questionText);

        deleteAllChildNodes(questionTextEl);
        questionTextEl.appendChild(createText(questionName + " " + questionText));
        questionTextEl.appendChild(createFileElement(questionImageEncoded));
    }


    private String findQuestionName(Element question) {
        NodeList names = question.getElementsByTagName("name");
        if (names.getLength() > 0) {
            Element name = (Element) names.item(0);
            Node text = name.getElementsByTagName("text").item(0);
            return text.getTextContent();
        }
        return null;
    }

    private String encodeQuestionImage(String questionName) {
        try {
            Path imagePath = Files.walk(imageBasePath, 1)
                    .filter(isQuestionImagePath(questionName))
                    .findAny()
                    .orElseGet(() -> imageBasePath.resolve(questionName + ".jpg"));
            if (!Files.exists(imagePath)) {
                System.out.println(imagePath + " doesn't exists");
                return imagePath.toAbsolutePath() + " doesn't exists";
            }
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Predicate<Path> isQuestionImagePath(String questionName) {
        String[] split = questionName.split("-");
        String kategorieNumber = split[0];
        String questionNumber = split[1];
        String questionImageNameRegex = kategorieNumber + "-(\\d+\\+)*" + questionNumber + "(\\+\\d+)*(.jpg|.png)";
        Pattern questionImageNamePattern = Pattern.compile(questionImageNameRegex, CASE_INSENSITIVE);
        return path -> {
            String fileName = path.getFileName().toString();
            return questionImageNamePattern.matcher(fileName).matches();
        };
    }

    private String findQuestionText(Element questionTextEl) {
        NodeList textNodes = questionTextEl.getElementsByTagName("text");
        String text = textNodes.item(0).getTextContent();
        int textStart = text.indexOf("<p>");
        int textEnd = text.lastIndexOf("</p>");
        if (textStart >= 0 && textEnd >= 0) {
            text = text.substring(textStart + "<p>".length(), textEnd);
        }
        return text.trim();
    }


    private void deleteAllChildNodes(Node node) {
        while (node.hasChildNodes()) {
            node.removeChild(node.getFirstChild());
        }
    }

    private Element createFileElement(String questionImageEncoded) {
        Element fileEl = doc.createElement("file");
        fileEl.setAttribute("name", imageBasePath.getFileName().toString());
        fileEl.setAttribute("path", imageBasePath.toString());
        fileEl.setAttribute("encoding", "base64");
        fileEl.setTextContent(questionImageEncoded);
        return fileEl;
    }

    private Element createText(String questionText) {
        Element newTextEl = doc.createElement("text");
        newTextEl.setTextContent(questionText);
        return newTextEl;
    }


    public static void main(String[] args) throws IOException {
        Path directory = Paths.get("src\\main\\resources\\MC-Duell_Neues_Studiendesign\\Bilderfragen");
        Files.walk(directory).filter(path -> path.toString().endsWith(".xml")).forEach(quizFile -> {
            System.out.println("transform fragenkatalog xml from: " + quizFile);
            Document document = convertMcDuellImageQuestion(quizFile.toFile());

            String xmlFileName = quizFile.getFileName().toString();
            Path xmlFile = quizFile.getParent().resolveSibling(xmlFileName);
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
