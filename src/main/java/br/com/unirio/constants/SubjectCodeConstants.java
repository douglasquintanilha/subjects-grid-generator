package br.com.unirio.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SubjectCodeConstants {
    public static final String[] REQUIRED_SUBJECTS = new String[] {"HTD0058", "TIN0011", "TIN0013", "TIN0105", "TIN0106", "TIN0107", "TIN0108", "TIN0109", "TIN0110", "TIN0112", "TIN0114",
            "TIN0115", "TIN0116", "TIN0117", "TIN0118", "TIN0119", "TIN0120", "TIN0121", "TIN0122", "TIN0123", "TIN0125",
            "TIN0126", "TIN0130", "TIN0131", "TIN0132", "TIN0133", "TIN0168", "TIN0169", "TIN0171", "TME0015", "TME0101",
            "TME0112", "TME0113", "TME0114", "TME0115"};

    public static final String[] OPTIONAL_SUBJECTS = new String[] {"TIN0128", "TIN0135", "TIN0136", "TIN0137", "TIN0138",
            "TIN0141", "TIN0142", "TIN0143", "TIN0144", "TIN0145", "TIN0146", "TIN0147", "TIN0148", "TIN0149", "TIN0150",
            "TIN0158", "TIN0159", "TIN0160", "TIN0161", "TIN0162", "TIN0164", "TIN0165", "TIN0166", "TIN0172", "TIN0173"};

    public static final String[] COMPLEMENTARY_ACTIVITIES_SUBJECTS =  new String[] {"TIN0054", "TIN0055", "TIN0056",
            "TIN0057"};

    public static final Set<String> REQUIRED_SUBJECTS_SET = new HashSet<String>(Arrays.asList(REQUIRED_SUBJECTS));

    public static final Set<String> OPTIONAL_SUBJECTS_SET = new HashSet<String>(Arrays.asList(OPTIONAL_SUBJECTS));

    public static final Set<String> COMPLEMENTARY_SUBJECTS_SET = new HashSet<String>(Arrays.asList(COMPLEMENTARY_ACTIVITIES_SUBJECTS));





}

