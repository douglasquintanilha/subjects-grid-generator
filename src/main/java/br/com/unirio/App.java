package br.com.unirio;

import br.com.unirio.models.Subject;
import br.com.unirio.parsers.PDFParser;
import br.com.unirio.parsers.SVGParser;
import br.com.unirio.parsers.TXTParser;
import br.com.unirio.renders.SVGRenderer;
import br.com.unirio.constants.ColorConstants;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws IOException, XPathExpressionException, TransformerException, ParserConfigurationException, SAXException {
        System.out.println("Hello World!");
        PDFParser pdfParser = new PDFParser();
        SVGRenderer svgRenderer = new SVGRenderer();
        TXTParser txtParser = new TXTParser();
        SVGParser svgParser = new SVGParser();

        Document grid = svgParser.parseSVG("files/grade-curricular.svg");
        String parsed_txt = pdfParser.parsePdf("files/historico-descubra.pdf");

        List<Subject> subjects = txtParser.extractSubjectsFromTxt(parsed_txt);
        int printadas = 0;
        for (Subject subject : subjects) {
            if(svgRenderer.shouldPrintGreen(subject)){

                Document newGrid = svgRenderer.updateSubjectColor(subject, ColorConstants.REPROVED_COLOR, grid);
                svgRenderer.drawGrid(newGrid, "files/boladao.svg");
                printadas++;
            }else if(svgRenderer.shouldPrintRed(subject)) {

                Document newGrid = svgRenderer.updateSubjectColor(subject, ColorConstants.APPROVED_COLOR, grid);
                svgRenderer.drawGrid(newGrid, "files/boladao.svg");
                printadas++;
            }

        }
        System.out.println("Total de matérias: " + subjects.size());
        System.out.println("Total de desenhadas: " + printadas);
    }


}
