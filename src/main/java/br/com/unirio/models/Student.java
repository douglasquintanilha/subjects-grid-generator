package br.com.unirio.models;

import java.util.List;

public class Student {

    private String name;
    private String CRA;
    private List<Subject> requiredSubjects;
    private List<Subject> optionalSubjects;
    private List<Subject> electiveSubjects;
    private List<Subject> complementarySubjects;

    public Student(String name, String CRA, List<Subject> requiredSubjects, List<Subject> optionalSubjects, List<Subject> electiveSubjects, List<Subject> complementarySubjects) {
        this.name = name;
        this.CRA = CRA;
        this.requiredSubjects = requiredSubjects;
        this.optionalSubjects = optionalSubjects;
        this.electiveSubjects = electiveSubjects;
        this.complementarySubjects = complementarySubjects;
    }


}
