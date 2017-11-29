package br.com.unirio;

import br.com.unirio.builders.StudentBuilder;
import br.com.unirio.models.Student;
import br.com.unirio.models.Subject;
import br.com.unirio.parsers.PDFParser;
import br.com.unirio.parsers.SVGParser;
import br.com.unirio.parsers.TXTParser;
import br.com.unirio.renderers.SVGRenderer;
import br.com.unirio.constants.ColorConstants;
import br.com.unirio.renderers.TemplateRenderer;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws IOException, XPathExpressionException, TransformerException, ParserConfigurationException, SAXException {
        PDFParser pdfParser = new PDFParser();
        TemplateRenderer templateRenderer = new TemplateRenderer("files/template.html");
        SVGRenderer svgRenderer = new SVGRenderer();
        TXTParser txtParser = new TXTParser();
        SVGParser svgParser = new SVGParser();

        Document grid = svgParser.parseSVG("files/grade-curricular.svg");
        String parsed_pdf = pdfParser.parsePdf("files/historico-descubra.pdf");

        System.out.println(parsed_pdf);

        List<Subject> subjects = txtParser.extractSubjectsFromTxt(parsed_pdf);
        Student student = StudentBuilder.build(txtParser.extractStudentName(parsed_pdf), txtParser.extractStudentCRA(parsed_pdf),
                                                txtParser.extractStudentCurrentTerm(parsed_pdf), txtParser.extractStudentFirstYear(parsed_pdf),
                subjects);
        svgRenderer.printSVG(subjects, "files/output.svg" , grid);

        Map<String, Object> context = new HashMap<>();
        context.put("name", student.getName());
        context.put("cra", student.getCRA());
        context.put("checkEnrollement", student.isEnrolledInAtLeastThreeSubjects());
        context.put("shouldPresentIntegralizationPlan", student.shouldPresentIntegralizationPlan());
        context.put("shouldBeExpelled", student.shouldBeExpelled());
        templateRenderer.renderTemplate(context);

    }


}
