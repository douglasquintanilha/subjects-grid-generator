package br.com.unirio.models;

import br.com.unirio.constants.SubjectSituationConstants;
import br.com.unirio.utils.StringSanitezer;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {

    private String name;
    private Float CRA;
    private int currentTerm;
    private LocalDate firstYear;
    private List<Subject> requiredSubjects;
    private List<Subject> optionalSubjects;
    private List<Subject> electiveSubjects;
    private List<Subject> complementarySubjects;

    public Student(String name, String CRA, String currentTerm, String firstYear, List<Subject> requiredSubjects, List<Subject> optionalSubjects, List<Subject> electiveSubjects, List<Subject> complementarySubjects) {
        this.name = name;
        this.CRA = StringSanitezer.sanitizeCRA(CRA);
        this.currentTerm = Integer.parseInt(currentTerm);
        this.firstYear = LocalDate.of(Integer.parseInt(firstYear), Month.FEBRUARY, 1);
        this.requiredSubjects = requiredSubjects;
        this.optionalSubjects = optionalSubjects;
        this.electiveSubjects = electiveSubjects;
        this.complementarySubjects = complementarySubjects;
    }


    public String getName() {
        return name;
    }

    public Float getCRA() {
        return CRA;
    }

    public List<Subject> getRequiredSubjects() {
        return requiredSubjects;
    }

    public List<Subject> getOptionalSubjects() {
        return optionalSubjects;
    }

    public List<Subject> getElectiveSubjects() {
        return electiveSubjects;
    }

    public List<Subject> getComplementarySubjects() {
        return complementarySubjects;
    }

    public boolean isEnrolledInAtLeastThreeSubjects(){
        int enrolledRequiredSubjects = checkNumberOfEnrollenmentSubjects(this.requiredSubjects);
        int enrolledOptionalSubjects = checkNumberOfEnrollenmentSubjects(this.optionalSubjects);
        int enrolledElectiveSubjects = checkNumberOfEnrollenmentSubjects(this.electiveSubjects);
        int enrolledComplementarySubjects = checkNumberOfEnrollenmentSubjects(this.complementarySubjects);

        if(enrolledRequiredSubjects + enrolledOptionalSubjects
            + enrolledElectiveSubjects + enrolledComplementarySubjects >= 3){
            return true;
        }else{
            return false;
        }
    }

    public int checkNumberOfEnrollenmentSubjects(List<Subject> subjects){
        int enrolledSubjects = 0;
        for (Subject subject: subjects){
            if(subject.getSituation() == SubjectSituationConstants.ENROLLMENT){
                enrolledSubjects ++;
            }
        }
        return enrolledSubjects;
    }

    public boolean shouldBeExpelled(){

        boolean failedRequiredSubjects = checkForQuadrupleFailedSubjects(this.requiredSubjects);
        boolean failedOptionalSubjects = checkForQuadrupleFailedSubjects(this.optionalSubjects);
        boolean failedElectiveSubjects = checkForQuadrupleFailedSubjects(this.electiveSubjects);
        boolean failedComplementarySubjects = checkForQuadrupleFailedSubjects(this.complementarySubjects);

        boolean anyQuadrupleFailedSubject = failedRequiredSubjects || failedOptionalSubjects ||
                                            failedElectiveSubjects || failedComplementarySubjects;

        if( (this.getCRA() < 4.0) && anyQuadrupleFailedSubject){
            return true;
        }else{
            return false;
        }

    }

    public boolean checkForQuadrupleFailedSubjects(List<Subject> subjects){
        Map<Subject,Integer> subjectsFailures = new HashMap<Subject, Integer>();
        for (Subject subject: subjects){
            if(subject.hasFailed()){
                if(subjectsFailures.containsKey(subject)) {
                    subjectsFailures.put(subject, subjectsFailures.get(subject) + 1);
                }else {
                     subjectsFailures.put(subject, 1);
                }
            }
        }
        for (Map.Entry<Subject, Integer> entry : subjectsFailures.entrySet())
        {
            if(entry.getValue() >= 4){
                return true;
            }
        }

        return false;
    }

    public boolean shouldPresentIntegralizationPlan() {
        if(this.firstYear.isBefore(LocalDate.of(2014,1,1))){
            if(this.currentTerm >= 12){
                return true;
            }
        }else if (this.firstYear.isAfter(LocalDate.of(2014,1,1))){
            if(this.currentTerm >= 7){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}