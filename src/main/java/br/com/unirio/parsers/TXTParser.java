package br.com.unirio.parsers;

import br.com.unirio.models.Subject;
import br.com.unirio.utils.StringSanitezer;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TXTParser {

    public List<Subject> extractSubjectsFromTxt(String parsed_pdf){
        List<Subject> subjects = new ArrayList<Subject>();

        String subject_code_regex = "(^[A-Z]{3}[0-9]{4})\\s";
        String subject_name_regex = "(\\D*[\\d]?)\\s";
        String subject_term_regex = "(\\d+)\\s";
        String subject_credits_regex = "(\\d+)\\s";
        String subject_grade_regex = "(\\d+,\\d+)\\s";
        String subject_frequency_regex = "(\\d+,\\d+)?";
        String subject_situation_regex = "(.*)";


        Pattern subject_regex = Pattern.compile(subject_code_regex + subject_name_regex +
                                                subject_term_regex + subject_credits_regex +
                                                subject_grade_regex + subject_frequency_regex +
                                                subject_situation_regex,Pattern.MULTILINE);

        final Matcher matcher = subject_regex.matcher(parsed_pdf);

        while (matcher.find()) {

            String subject_code = StringSanitezer.sanitizeString(matcher.group(1));
            String subject_name = StringSanitezer.sanitizeString(matcher.group(2));
            String subject_term = StringSanitezer.sanitizeString(matcher.group(3));
            String subject_credits = StringSanitezer.sanitizeString(matcher.group(4));
            String subject_grade = StringSanitezer.sanitizeString(matcher.group(5));
            String subject_frequency = StringSanitezer.sanitizeString(matcher.group(6));
            String subject_situation = StringSanitezer.sanitizeString(matcher.group(7));

            Subject subject = new Subject(subject_code, subject_name, subject_term, subject_credits, subject_grade, subject_frequency, subject_situation);
            subjects.add(subject);
        }
        return subjects;
    }

    public String extractOneStringFromTxt(String regex, String parsed_pdf){
        Pattern searchPattern = Pattern.compile(regex);
        Matcher matcher = searchPattern.matcher(parsed_pdf);
        matcher.find();
        String result = matcher.group(1);
        return StringSanitezer.sanitizeString(result);
    }

    public String extractStudentName(String parsed_pdf){
        String studentNameRegex = "Nome Aluno:\\s(.*)";
        return extractOneStringFromTxt(studentNameRegex, parsed_pdf);
    }

    public String extractStudentCRA(String parsed_pdf){
        String studentCRARegex = "Coeficiente de Rendimento Geral:\\s(\\d+,\\d+)";
        return extractOneStringFromTxt(studentCRARegex, parsed_pdf);
    }

    public String extractStudentCurrentTerm(String parsed_pdf){
        String studentCurrentTermRegex = "Período Atual:\\s(\\d)";
        return extractOneStringFromTxt(studentCurrentTermRegex, parsed_pdf);
    }

    public String extractStudentFirstYear(String parsed_pdf){
        String studentFirstYearRegex = "(\\d{4})\\d{7}\\sMatrícula:";
        return extractOneStringFromTxt(studentFirstYearRegex, parsed_pdf);
    }



}
