package br.com.unirio.utils;

public class StringSanitezer {

    public static Float sanitizeCRA(String CRA){
        String dotCRA = CRA.replace(",", ".");
        Float floatCRA = Float.parseFloat(dotCRA);
        return floatCRA;
    }

    public static String sanitizeString(String rawString){
        if(rawString != null){
            String stringWithoutBreakingLines = StringSanitezer.removeBreaklines(rawString);
            String stringWithoutCarriageReturn = StringSanitezer.removeCarriageReturn(stringWithoutBreakingLines);
            String trimmedString = stringWithoutCarriageReturn.trim();
            return trimmedString;
        }else{
            return rawString;
        }
    }

    public static String removeBreaklines(String rawString){
        String stringWithoutBreakingLines = rawString.replace("\n","");
        return stringWithoutBreakingLines;
    }

    public static String removeCarriageReturn(String rawString){
        String stringWithoutCarriageReturn = rawString.replace("\r", "");
        return  stringWithoutCarriageReturn;
    }
}
