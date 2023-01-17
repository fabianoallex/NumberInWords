package numberinwords.english;

import numberinwords.Suffix;

import java.util.HashMap;
import java.util.Map;

public class EnglishOrdinalDescriptions {
    static final String[] suffixes = new String[]{
            "", "thousandth", "millionth", "billionth", "trillionth", "quadrillionth",
            "quintillionth", "sextillionth", "septillionth", "octillionth",
            "nonillionth", "decillionth"};
    static final Map<Integer, String> descriptionsMap = new HashMap<>();

    static String getSuffixDescription(Suffix suffix) {
        return suffixes[suffix.suffixIndex];
    }

    static String getSuffixDescriptionForValue(Suffix suffix, Long forValue) {
        return getSuffixDescription(suffix);
    }

    static {
        descriptionsMap.put(1, "first");
        descriptionsMap.put(2, "second");
        descriptionsMap.put(3, "third");
        descriptionsMap.put(4, "fourth");
        descriptionsMap.put(5, "fifth");
        descriptionsMap.put(6, "sixth");
        descriptionsMap.put(7, "seventh");
        descriptionsMap.put(8, "eighth");
        descriptionsMap.put(9, "ninth");
        descriptionsMap.put(10, "tenth");
        descriptionsMap.put(11, "eleventh");
        descriptionsMap.put(12, "twelfth");
        descriptionsMap.put(13, "thirteenth");
        descriptionsMap.put(14, "fourteenth");
        descriptionsMap.put(15, "fifteenth");
        descriptionsMap.put(16, "sixteenth");
        descriptionsMap.put(17, "seventeenth");
        descriptionsMap.put(18, "eighteenth");
        descriptionsMap.put(19, "nineteenth");
        descriptionsMap.put(20, "twentieth");
        descriptionsMap.put(30, "thirtieth");
        descriptionsMap.put(40, "fortieth");
        descriptionsMap.put(50, "fiftieth");
        descriptionsMap.put(60, "sixtieth");
        descriptionsMap.put(70, "seventieth");
        descriptionsMap.put(80, "eightieth");
        descriptionsMap.put(90, "ninetieth");
        descriptionsMap.put(100, "hundredth");
    }
}
