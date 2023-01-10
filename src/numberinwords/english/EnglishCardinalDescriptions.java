package numberinwords.english;

import numberinwords.Suffix;

import java.util.HashMap;
import java.util.Map;

public class EnglishCardinalDescriptions {
    static final String[] suffixes = new String[]{
            "", "thousand", "million", "billion", "trillion", "quadrillion",
            "quintillion", "sextillion", "septillion", "octillion",
            "nonillion", "decillion"};

    static final String DEFAULT_NEGATIVE_SIGNAL_DESCRIPTION = "negative";
    static final String DEFAULT_POSITIVE_SIGNAL_DESCRIPTION = "";
    static final Map<Integer, String> descriptionsMap = new HashMap<>();

    static String getSuffixDescription(Suffix suffix) {
        return suffixes[suffix.suffixIndex];
    }

    static String getSuffixDescriptionForValue(Suffix suffix, Long forValue) {
        return getSuffixDescription(suffix);
    }

    static {
        descriptionsMap.put(0, "zero");
        descriptionsMap.put(1, "one");
        descriptionsMap.put(2, "two");
        descriptionsMap.put(3, "three");
        descriptionsMap.put(4, "four");
        descriptionsMap.put(5, "five");
        descriptionsMap.put(6, "six");
        descriptionsMap.put(7, "seven");
        descriptionsMap.put(8, "eight");
        descriptionsMap.put(9, "nine");
        descriptionsMap.put(10, "ten");
        descriptionsMap.put(11, "eleven");
        descriptionsMap.put(12, "twelve");
        descriptionsMap.put(13, "thirteen");
        descriptionsMap.put(14, "fourteen");
        descriptionsMap.put(15, "fifteen");
        descriptionsMap.put(16, "sixteen");
        descriptionsMap.put(17, "seventeen");
        descriptionsMap.put(18, "eighteen");
        descriptionsMap.put(19, "nineteen");
        descriptionsMap.put(20, "twenty");
        descriptionsMap.put(30, "thirty");
        descriptionsMap.put(40, "forty");
        descriptionsMap.put(50, "fifty");
        descriptionsMap.put(60, "sixty");
        descriptionsMap.put(70, "seventy");
        descriptionsMap.put(80, "eighty");
        descriptionsMap.put(90, "ninety");
        descriptionsMap.put(100, "hundred");
    }
}