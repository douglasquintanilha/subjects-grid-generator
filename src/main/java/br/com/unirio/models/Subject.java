package br.com.unirio.models;

public class Subject {
    private String code;
    private String name;
    private String term;
    private String credits;
    private String grade;
    private String frequency;
    private String situation;

    public Subject(String code, String name, String term, String credits, String grade, String frequency, String situation) {
        this.code = code;
        this.name = name;
        this.term = term;
        this.credits = credits;
        this.grade = grade;
        this.frequency = frequency;
        this.situation = situation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
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
