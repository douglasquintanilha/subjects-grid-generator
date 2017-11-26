package br.com.unirio.models;

import br.com.unirio.constants.SubjectCodeConstants;
import br.com.unirio.constants.SubjectSituationConstants;
import br.com.unirio.constants.SubjectTypeConstants;

public class Subject {
    private String code;
    private String name;
    private String term;
    private String credits;
    private String grade;
    private String frequency;
    private SubjectSituationConstants situation;
    private SubjectTypeConstants type;

    public Subject(String code, String name, String term, String credits, String grade, String frequency, String situation) {
        this.code = code;
        this.name = name;
        this.term = term;
        this.credits = credits;
        this.grade = grade;
        this.frequency = frequency;
        this.situation = SubjectSituationConstants.fromString(situation);
        this.type = this.checkType(code);
    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getTerm() {
        return term;
    }

    public String getCredits() {
        return credits;
    }

    public String getGrade() {
        return grade;
    }

    public String getFrequency() {
        return frequency;
    }

    public SubjectSituationConstants getSituation() {
        return situation;
    }

    public SubjectTypeConstants getType() {
        return type;
    }

    public SubjectTypeConstants checkType(String code){
        if(SubjectCodeConstants.REQUIRED_SUBJECTS_SET.contains(code)){
            return SubjectTypeConstants.REQUIRED;
        }else if(SubjectCodeConstants.OPTIONAL_SUBJECTS_SET.contains(code)){
            return SubjectTypeConstants.OPTIONAL;
        }else if(SubjectCodeConstants.COMPLEMENTARY_SUBJECTS_SET.contains(code)){
            return SubjectTypeConstants.COMPLEMENTARY;
        } else{
            System.out.println(String.format("The code %s of the subject %s is not known, probably elective",code, this.name ));
            return SubjectTypeConstants.ELECTIVE;
        }
    }

    @Override
    public String toString() {
        return "Subject{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", term='" + term + '\'' +
                ", credits='" + credits + '\'' +
                ", grade='" + grade + '\'' +
                ", frequency='" + frequency + '\'' +
                ", situation='" + situation + '\'' +
                '}';
    }
}
