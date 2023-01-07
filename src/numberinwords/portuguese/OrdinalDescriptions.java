package numberinwords.portuguese;

import java.util.HashMap;
import java.util.Map;

public class OrdinalDescriptions {
    static final String[] maleSuffixes = new String[] {
            "", "milésimo", "milionésimo", "bilionésimo", "trilionésimo",
            "quatrilionésimo", "quintilionésimo", "sextilionésimo",
            "septilionésimo", "octilionésimo", "nonilionésimo", "decilionésimo"
    };

    static final String[] femaleSuffixes = new String[] {
            "", "milésima", "milionésima", "bilionésima", "trilionésima",
            "quatrilionésima", "quintilionésima", "sextilionésima",
            "septilionésima", "octilionésima", "nonilionésima", "decilionésima"
    };

    static String getSuffixDescriptionForGender(Suffix suffix, Gender gender) {
        if (gender.equals(Gender.MALE))
            return maleSuffixes[suffix.suffixIndex];

        return femaleSuffixes[suffix.suffixIndex];
    }

    static final Map<Integer, String> maleDescriptionsMap = new HashMap<>();
    static final Map<Integer, String> femaleDescriptionsMap = new HashMap<>();
    static {
        maleDescriptionsMap.put(1, "primeiro");
        maleDescriptionsMap.put(2, "segundo");
        maleDescriptionsMap.put(3, "terceiro");
        maleDescriptionsMap.put(4, "quarto");
        maleDescriptionsMap.put(5, "quinto");
        maleDescriptionsMap.put(6, "sexto");
        maleDescriptionsMap.put(7, "sétimo");
        maleDescriptionsMap.put(8, "oitavo");
        maleDescriptionsMap.put(9, "nono");
        maleDescriptionsMap.put(10, "décimo");
        maleDescriptionsMap.put(20, "vigésimo");
        maleDescriptionsMap.put(30, "trigésimo");
        maleDescriptionsMap.put(40, "quadragésimo");
        maleDescriptionsMap.put(50, "quinquagésimo");
        maleDescriptionsMap.put(60, "sexagésimo");
        maleDescriptionsMap.put(70, "septuagésimo");
        maleDescriptionsMap.put(80, "octogésimo");
        maleDescriptionsMap.put(90, "nonagésimo");
        maleDescriptionsMap.put(100, "centésimo");
        maleDescriptionsMap.put(200, "ducentésimo");
        maleDescriptionsMap.put(300, "trecentésimo"); //ou tricentésimo
        maleDescriptionsMap.put(400, "quadringentésimo");
        maleDescriptionsMap.put(500, "quingentésimo");
        maleDescriptionsMap.put(600, "sexcentésimo"); //ou seiscentésimo
        maleDescriptionsMap.put(700, "septingentésimo"); // ou setingentésimo
        maleDescriptionsMap.put(800, "octingentésimo"); // ou octogentésimo
        maleDescriptionsMap.put(900, "noningentésimo"); // ou nongentésimo
        maleDescriptionsMap.put(1000, "milésimo");

        femaleDescriptionsMap.put(1, "primeira");
        femaleDescriptionsMap.put(2, "segunda");
        femaleDescriptionsMap.put(3, "terceira");
        femaleDescriptionsMap.put(4, "quarta");
        femaleDescriptionsMap.put(5, "quinta");
        femaleDescriptionsMap.put(6, "sexta");
        femaleDescriptionsMap.put(7, "sétima");
        femaleDescriptionsMap.put(8, "oitava");
        femaleDescriptionsMap.put(9, "nona");
        femaleDescriptionsMap.put(10, "décima");
        femaleDescriptionsMap.put(20, "vigésima");
        femaleDescriptionsMap.put(30, "trigésima");
        femaleDescriptionsMap.put(40, "quadragésima");
        femaleDescriptionsMap.put(50, "quinquagésima");
        femaleDescriptionsMap.put(60, "sexagésima");
        femaleDescriptionsMap.put(70, "septuagésima");
        femaleDescriptionsMap.put(80, "octogésima");
        femaleDescriptionsMap.put(90, "nonagésima");
        femaleDescriptionsMap.put(100, "centésima");
        femaleDescriptionsMap.put(200, "ducentésima");
        femaleDescriptionsMap.put(300, "trecentésima"); //ou tricentésimo
        femaleDescriptionsMap.put(400, "quadringentésima");
        femaleDescriptionsMap.put(500, "quingentésima");
        femaleDescriptionsMap.put(600, "sexcentésima"); //ou seiscentésimo
        femaleDescriptionsMap.put(700, "septingentésima"); // ou setingentésimo
        femaleDescriptionsMap.put(800, "octingentésima"); // ou octogentésimo
        femaleDescriptionsMap.put(900, "noningentésima"); // ou nongentésimo
        femaleDescriptionsMap.put(1000, "milésima");
    }
}
