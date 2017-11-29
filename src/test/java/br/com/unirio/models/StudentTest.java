package br.com.unirio.models;

import br.com.unirio.builders.StudentBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class StudentTest {

    @Test
    public void checkNumberOfEnrolledSubjects(){
        Subject subject1 = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "DIS - Dispensa sem nota");
        Subject subject2 = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "DIS - Dispensa sem nota");
        Subject subject3 = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "ASC - Matrícula");
        Subject subject4 = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "ASC - Matrícula");
        List<Subject> subjects = Arrays.asList(subject1,subject2,subject3,subject4);
        Student student = StudentBuilder.build("Douglas", "8.0","3", "2016", subjects);

        Assert.assertEquals(student.checkNumberOfEnrollenmentSubjects(subjects), 2);
    }

    @Test
    public void checkIfEnrolledInAtLeatThreeSubjectsFailsWithTwoSubjects(){
        Subject subject1 = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "DIS - Dispensa sem nota");
        Subject subject2 = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "DIS - Dispensa sem nota");
        Subject subject3 = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "ASC - Matrícula");
        Subject subject4 = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "ASC - Matrícula");
        List<Subject> subjects = Arrays.asList(subject1,subject2,subject3,subject4);
        Student student = StudentBuilder.build("Douglas", "8.0","3", "2016", subjects);

        Assert.assertFalse(student.isEnrolledInAtLeastThreeSubjects());

    }

    @Test
    public void checkIfEnrolledInAtLeatThreeSubjectsSucceedsWithThreeSubjects(){
        Subject subject1 = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "DIS - Dispensa sem nota");
        Subject subject2 = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "ASC - Matrícula");
        Subject subject3 = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "ASC - Matrícula");
        Subject subject4 = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "ASC - Matrícula");
        List<Subject> subjects = Arrays.asList(subject1,subject2,subject3,subject4);
        Student student = StudentBuilder.build("Douglas", "8.0","3", "2016", subjects);

        Assert.assertTrue(student.isEnrolledInAtLeastThreeSubjects());

    }

    @Test
    public void checkIfStudenHasQuadrupleFailedSubjects(){
        Subject subject1 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        Subject subject2 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        Subject subject3 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        Subject subject4 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        List<Subject> subjects = Arrays.asList(subject1,subject2,subject3,subject4);
        Student student = StudentBuilder.build("Douglas", "3.0","3", "2016", subjects);

        Assert.assertTrue(student.checkForQuadrupleFailedSubjects(subjects));
    }

    @Test
    public void studentShouldBeExpelledIfCRAisLessThanFourAndHasQuadrupleFailsInOneSubject(){
        Subject subject1 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        Subject subject2 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        Subject subject3 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        Subject subject4 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        List<Subject> subjects = Arrays.asList(subject1,subject2,subject3,subject4);
        Student student = StudentBuilder.build("Douglas", "3.0","3", "2016", subjects);

        Assert.assertTrue(student.shouldBeExpelled());
    }

    @Test
    public void oldStudentShouldPresentIntegralizationPlan(){
        Subject subject1 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        Subject subject2 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        Subject subject3 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        Subject subject4 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        List<Subject> subjects = Arrays.asList(subject1,subject2,subject3,subject4);

        Student student = StudentBuilder.build("Douglas", "3.0","16", "2010", subjects);
        Assert.assertTrue(student.shouldPresentIntegralizationPlan());

    }


    @Test
    public void newStudentShouldPresentIntegralizationPlan(){
        Subject subject1 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        Subject subject2 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        Subject subject3 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        Subject subject4 = new Subject("TIN0143", "Test Subject", "1",
                "4", "3.0", "88.0", "RPV- Reprovado");
        List<Subject> subjects = Arrays.asList(subject1,subject2,subject3,subject4);

        Student student = StudentBuilder.build("Douglas", "3.0","8", "2014", subjects);
        Assert.assertTrue(student.shouldPresentIntegralizationPlan());

    }


}
