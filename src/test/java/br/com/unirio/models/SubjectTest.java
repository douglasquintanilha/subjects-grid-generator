package br.com.unirio.models;

import br.com.unirio.constants.SubjectSituationConstants;
import br.com.unirio.constants.SubjectTypeConstants;
import org.junit.Assert;
import org.junit.Test;

public class SubjectTest {


    @Test
    public void checkTypeRequired(){
        Subject requiredSubject = new Subject("TIN0011", "Test Subject", "1",
                                "4", "7.0", "88.0", "APV- Aprovado");

        Assert.assertEquals(requiredSubject.checkType(requiredSubject.getCode()), SubjectTypeConstants.REQUIRED);
    }

    @Test
    public void checkTypeComplementary(){
        Subject complementarySubject = new Subject("TIN0054", "Test Subject", "1",
                "4", "7.0", "88.0", "APV- Aprovado");

        Assert.assertEquals(complementarySubject.checkType(complementarySubject.getCode()), SubjectTypeConstants.COMPLEMENTARY);
    }

    @Test
    public void checkTypeElective(){
        Subject electiveSubject = new Subject("TIN1234", "Test Subject", "1",
                "4", "7.0", "88.0", "APV- Aprovado");

        Assert.assertEquals(electiveSubject.checkType(electiveSubject.getCode()), SubjectTypeConstants.ELECTIVE);
    }

    @Test
    public void checkTypeOptional(){
        Subject optionalSubject = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "APV- Aprovado");

        Assert.assertEquals(optionalSubject.checkType(optionalSubject.getCode()), SubjectTypeConstants.OPTIONAL);
    }


    @Test
    public void shouldFailIfSituationIsFailed(){
        Subject subject = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "RPV- Reprovado");
        Assert.assertTrue(subject.hasFailed());
    }

    @Test
    public void subjectFailsIfSituationIsFailedTwo(){
        Subject subject = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "REP - Reprovado por");
        Assert.assertTrue(subject.hasFailed());
    }

    @Test
    public void subjectFailsIfSituationIsFailedWithoutGrade(){
        Subject subject = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "ASC - Reprovado sem nota");
        Assert.assertTrue(subject.hasFailed());
    }

    @Test
    public void subjectFailsIfSituationIsFailedForLackOfPresence(){
        Subject subject = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "REF - Reprovado por falta");
        Assert.assertTrue(subject.hasFailed());
    }

    @Test
    public void subjectAprovedIfSituationIsDispensedWithoutGrade(){
        Subject subject = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "DIS - Dispensa sem nota");
        Assert.assertTrue(subject.isApproved());
    }

    @Test
    public void subjectAprovedIfSituationIsDispensedWithGrade(){
        Subject subject = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "DIS - Dispensa com nota");
        Assert.assertTrue(subject.isApproved());
    }

    @Test
    public void subjectAprovedIfSituationIsApproved(){
        Subject subject = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "APV- Aprovado");
        Assert.assertTrue(subject.isApproved());
    }

    @Test
    public void subjectAprovedIfSituationIsApprovedWithouGrade(){
        Subject subject = new Subject("TIN0143", "Test Subject", "1",
                "4", "7.0", "88.0", "APV - Aprovado sem nota");
        Assert.assertTrue(subject.isApproved());
    }
}
