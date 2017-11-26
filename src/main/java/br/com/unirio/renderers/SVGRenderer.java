package br.com.unirio.renderers;

import br.com.unirio.constants.SubjectSituationConstants;
import br.com.unirio.constants.SubjectTypeConstants;
import br.com.unirio.models.Subject;
import br.com.unirio.constants.ColorConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.util.List;

public class SVGRenderer {

    private static final int complementaryStartingCode = 54;

    private int optativesAlreadyDrawn = 0;
    private int electiveAlreadyDrawn = 0;
    private int complementaryAlreadyDrawn = complementaryStartingCode;


    public void drawGrid(Document grid, String filePath) throws  TransformerException {
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(new DOMSource(grid), new StreamResult(new File(filePath)));
    }

    public Document updateSubjectColor(Subject subject, ColorConstants hexadecimalColor, Document grid) throws XPathExpressionException, TransformerException {
        String xpathExpression = getXpathExpression(subject);

        System.out.println(xpathExpression);
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        XPathExpression expression = xpath.compile(xpathExpression);
        System.out.println("Fazendo a " + subject.getName() + " tipo:" + subject.getType() + " status: " + subject.getSituation());
        Node box = (Node) expression.evaluate(grid, XPathConstants.NODE);
        changeBoxBackgroundColor(box, hexadecimalColor);

        return grid;
    }

    private Node changeBoxBackgroundColor(Node box, ColorConstants hexadecimalColor){
        Node styleAttribute = box.getAttributes().getNamedItem("style");
        String styleValue = styleAttribute.getNodeValue();
        styleValue = styleValue.replaceAll("^(fill:)#.{6}", "$1" + hexadecimalColor.getColor());
        styleAttribute.setNodeValue(styleValue);

        return box;
    }

    private String getXpathExpression(Subject subject){
        String xpathExpression = "";
        if(subject.getType() == SubjectTypeConstants.OPTIONAL){
            optativesAlreadyDrawn++;
            xpathExpression = "//g/path[@id='OPTATIVA_0"+ optativesAlreadyDrawn + "']";
        }else if (subject.getType() == SubjectTypeConstants.ELECTIVE){
            electiveAlreadyDrawn++;
            xpathExpression = "//g/path[@id='ELETIVA_0"+ electiveAlreadyDrawn  + "']";
        }else if (subject.getType() == SubjectTypeConstants.COMPLEMENTARY){
            xpathExpression = "//g/path[@id='TIN00"+ complementaryAlreadyDrawn  + "']";
            complementaryAlreadyDrawn++;
        }else{
            xpathExpression = "//g/path[@id='"+ subject.getCode() + "']";
        }

        return xpathExpression;
    }

    public void printSVG(List<Subject> subjects, String outputFile, Document grid) throws TransformerException, XPathExpressionException {
        for (Subject subject : subjects) {
            if(this.shouldPrintGreen(subject)){
                Document newGrid = this.updateSubjectColor(subject, ColorConstants.REPROVED_COLOR, grid);
                this.drawGrid(newGrid, outputFile);
            }else if(this.shouldPrintRed(subject)) {

                Document newGrid = this.updateSubjectColor(subject, ColorConstants.APPROVED_COLOR, grid);
                this.drawGrid(newGrid, outputFile);
            }else if(this.shouldPrintYellow(subject)){
                Document newGrid = this.updateSubjectColor(subject, ColorConstants.ENROLLMENT_COLOR, grid);
                this.drawGrid(newGrid, outputFile);
            }
        }
    }

    public boolean shouldPrintGreen(Subject subject){
        if(subject.getSituation() == SubjectSituationConstants.FAILED ||
            subject.getSituation() == SubjectSituationConstants.FAILED_TWO ||
            subject.getSituation() == SubjectSituationConstants.FAILED_WITHOUT_GRADE ||
            subject.getSituation() == SubjectSituationConstants.FAILED_FOR_LACK_OF_PRESENCE){
            return true;
        }else{
            return false;
        }
    }

    public boolean shouldPrintRed(Subject subject){
        if(subject.getSituation() == SubjectSituationConstants.APPROVED ||
            subject.getSituation() == SubjectSituationConstants.APPROVED_WITHOUT_GRADE ||
            subject.getSituation() == SubjectSituationConstants.DISPENSED_WITHOUT_GRADE ||
            subject.getSituation() == SubjectSituationConstants.DISPENSED_WITH_GRADE){
            return true;
        }else{
            return false;
        }
    }

    public boolean shouldPrintYellow(Subject subject){
        if(subject.getSituation() == SubjectSituationConstants.ENROLLMENT){
            return true;
        }else{
            return false;
        }
    }



}
