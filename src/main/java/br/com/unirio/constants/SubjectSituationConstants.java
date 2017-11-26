package br.com.unirio.constants;

public enum SubjectSituationConstants {
    ENROLLMENT("ASC - Matrícula"),
    DISPENSED_WITHOUT_GRADE("DIS - Dispensa sem nota"),
    DISPENSED_WITH_GRADE("DIS - Dispensa com nota"),
    APPROVED("APV- Aprovado"),
    APPROVED_WITHOUT_GRADE("APV - Aprovado sem nota"),
    FAILED("RPV- Reprovado"),
    FAILED_TWO("REP - Reprovado por"),
    FAILED_WITHOUT_GRADE("ASC - Reprovado sem nota "),
    FAILED_FOR_LACK_OF_PRESENCE("REF - Reprovado por falta"),
    FREEZED("TRA - Trancamento de");

    private String situationName;

    SubjectSituationConstants(String situationName) {
        this.situationName = situationName;
    }

    public String getSituationName() {
        return situationName;
    }

    public static SubjectSituationConstants fromString(String text) {
        for (SubjectSituationConstants situation : SubjectSituationConstants.values()) {
            if (situation.situationName.equalsIgnoreCase(text)) {
                return situation;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }

}
