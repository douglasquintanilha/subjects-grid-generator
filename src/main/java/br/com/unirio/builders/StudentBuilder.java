package br.com.unirio.builders;

import br.com.unirio.constants.SubjectTypeConstants;
import br.com.unirio.models.Student;
import br.com.unirio.models.Subject;

import java.util.ArrayList;
import java.util.List;

public class StudentBuilder {


    public static Student build(String name, String CRA, String currentTerm, String firstYear, List<Subject> subjects){
        List<Subject> requiredSubjects = new ArrayList<Subject>();
        List<Subject> optionalSubjects = new ArrayList<Subject>();
        List<Subject> electiveSubjects = new ArrayList<Subject>();
        List<Subject> complementarySubjects = new ArrayList<Subject>();

        for(Subject subject: subjects){
            if(subject.getType() == SubjectTypeConstants.REQUIRED){
                requiredSubjects.add(subject);
            }else if(subject.getType() == SubjectTypeConstants.OPTIONAL) {
                optionalSubjects.add(subject);
            }else if(subject.getType() == SubjectTypeConstants.ELECTIVE){
                electiveSubjects.add(subject);
            }else if(subject.getType() == SubjectTypeConstants.COMPLEMENTARY){
                complementarySubjects.add(subject);
            }
        }

        Student student = new Student(name, CRA, currentTerm, firstYear, requiredSubjects, optionalSubjects, electiveSubjects, complementarySubjects);
        return student;

    }

}
