package br.com.unirio;

import br.com.unirio.models.Subject;
import br.com.unirio.parsers.PDFParser;
import br.com.unirio.parsers.TXTParser;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );
        PDFParser pdfParser = new PDFParser();
        String parsed_txt = pdfParser.parsePdf("files/historico-douglas.pdf");

        TXTParser txtParser = new TXTParser();
        List<Subject> subjects = txtParser.extract_subjects_from_txt(parsed_txt);
        for (Subject subject : subjects){
            System.out.println(subject);
        }



    }

}
