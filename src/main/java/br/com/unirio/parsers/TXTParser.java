package br.com.unirio.parsers;

import br.com.unirio.models.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TXTParser {

    public List<Subject> extract_subjects_from_txt(String parsed_pdf){
        List<Subject> subjects = new ArrayList<Subject>();

        String subject_code_regex = "(^[A-Z]{3}[0-9]{4})\\s";
        String subject_name_regex = "(\\D*)\\s";
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

            String subject_code = matcher.group(1);
            String subject_name = matcher.group(2);
            String subject_term = matcher.group(3);
            String subject_credits = matcher.group(4);
            String subject_grade = matcher.group(5);
            String subject_frequency = matcher.group(6);
            String subject_situation = matcher.group(7);

            Subject subject = new Subject(subject_code, subject_name, subject_term, subject_credits, subject_grade, subject_frequency, subject_situation);
            subjects.add(subject);
        }
        return subjects;
    }

}
