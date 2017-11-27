package br.com.unirio.parsers;

import br.com.unirio.models.Subject;
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

            String subject_code = sanitizeString(matcher.group(1));
            String subject_name = sanitizeString(matcher.group(2));
            String subject_term = sanitizeString(matcher.group(3));
            String subject_credits = sanitizeString(matcher.group(4));
            String subject_grade = sanitizeString(matcher.group(5));
            String subject_frequency = sanitizeString(matcher.group(6));
            String subject_situation = sanitizeString(matcher.group(7));

            Subject subject = new Subject(subject_code, subject_name, subject_term, subject_credits, subject_grade, subject_frequency, subject_situation);
            subjects.add(subject);
        }
        return subjects;
    }

    public String extractStudentName(String parsed_pdf){
        String student_name_regex = "Nome Aluno:\\s(.*)";
        Pattern student_pattern = Pattern.compile(student_name_regex);
        Matcher matcher = student_pattern.matcher(parsed_pdf);
        matcher.find();
        String studentName = matcher.group(1);
        return sanitizeString(studentName);
    }

    public String extractStudentCRA(String parsed_pdf){
        String student_CRA_regex = "Coeficiente de Rendimento Geral:\\s(\\d+,\\d+)";
        Pattern student_CRA_pattern = Pattern.compile(student_CRA_regex);
        Matcher matcher = student_CRA_pattern.matcher(parsed_pdf);
        matcher.find();
        String studentCRA = matcher.group(1);
        return sanitizeString(studentCRA);
    }

    private String sanitizeString(String rawString){
        String sanitezedString = rawString;
        if(rawString != null){
            String stringWithoutBreakingLines = rawString.replace("\n","");
            String stringWithoutCarriageReturn = stringWithoutBreakingLines.replace("\r", "");
            String trimmedString = stringWithoutCarriageReturn.trim();
            sanitezedString = trimmedString;
        }

        return sanitezedString;
    }

}
