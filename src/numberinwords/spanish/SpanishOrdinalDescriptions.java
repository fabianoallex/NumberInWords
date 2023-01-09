package numberinwords.spanish;

import numberinwords.Gender;
import numberinwords.Suffix;

import java.util.HashMap;
import java.util.Map;

public class SpanishOrdinalDescriptions {
    static final String[] maleSuffixes = new String[] {
            "", "milésimo", "millonésimo", "bilionésimo", "trilionésimo",
            "quatrilionésimo", "quintilionésimo", "sextilionésimo",
            "septilionésimo", "octilionésimo", "nonilionésimo", "decilionésimo"
    };

    static final String[] femaleSuffixes = new String[] {
            "", "milésima", "millonésima", "bilionésima", "trilionésima",
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
        maleDescriptionsMap.put(1, "primero");
        maleDescriptionsMap.put(2, "segundo");
        maleDescriptionsMap.put(3, "tercero");
        maleDescriptionsMap.put(4, "cuarto");
        maleDescriptionsMap.put(5, "quinto");
        maleDescriptionsMap.put(6, "sexto");
        maleDescriptionsMap.put(7, "séptimo");
        maleDescriptionsMap.put(8, "octavo");
        maleDescriptionsMap.put(9, "noveno");
        maleDescriptionsMap.put(10, "décimo");
        maleDescriptionsMap.put(11, "undécimo");
        maleDescriptionsMap.put(12, "duodécimo");
        maleDescriptionsMap.put(13, "decimotercero");
        maleDescriptionsMap.put(14, "decimocuarto");
        maleDescriptionsMap.put(15, "decimoquinto");
        maleDescriptionsMap.put(16, "decimosexto");
        maleDescriptionsMap.put(17, "decimoséptimo");
        maleDescriptionsMap.put(18, "decimoctavo");
        maleDescriptionsMap.put(19, "decimonoveno");
        maleDescriptionsMap.put(20, "vigésimo");
        maleDescriptionsMap.put(30, "trigésimo");
        maleDescriptionsMap.put(40, "cuadragésimo");
        maleDescriptionsMap.put(50, "quincuagésimo");
        maleDescriptionsMap.put(60, "sexagésimo");
        maleDescriptionsMap.put(70, "septuagésimo");
        maleDescriptionsMap.put(80, "octagésimo");
        maleDescriptionsMap.put(90, "nonagésimo");
        maleDescriptionsMap.put(100, "centésimo");
        maleDescriptionsMap.put(200, "ducentésimo");
        maleDescriptionsMap.put(300, "tricentésimo");
        maleDescriptionsMap.put(400, "cuadringentésimo");
        maleDescriptionsMap.put(500, "quingentésimo");
        maleDescriptionsMap.put(600, "sexcentésimo");
        maleDescriptionsMap.put(700, "septingentésimo");
        maleDescriptionsMap.put(800, "octingentésimo");
        maleDescriptionsMap.put(900, "noningentésimo");
        maleDescriptionsMap.put(1000, "milésimo");

        femaleDescriptionsMap.put(1, "primera");
        femaleDescriptionsMap.put(2, "segunda");
        femaleDescriptionsMap.put(3, "tercera");
        femaleDescriptionsMap.put(4, "cuarta");
        femaleDescriptionsMap.put(5, "quinta");
        femaleDescriptionsMap.put(6, "sexta");
        femaleDescriptionsMap.put(7, "séptima");
        femaleDescriptionsMap.put(8, "octava");
        femaleDescriptionsMap.put(9, "novena");
        femaleDescriptionsMap.put(10, "décima");
        femaleDescriptionsMap.put(11, "undécima");
        femaleDescriptionsMap.put(12, "duodécima");
        femaleDescriptionsMap.put(13, "decimatercera");
        femaleDescriptionsMap.put(14, "decimacuarta");
        femaleDescriptionsMap.put(15, "decimaquinta");
        femaleDescriptionsMap.put(16, "decimasexta");
        femaleDescriptionsMap.put(17, "decimaséptima");
        femaleDescriptionsMap.put(18, "decimactava");
        femaleDescriptionsMap.put(19, "decimanovena");
        femaleDescriptionsMap.put(20, "vigésima");
        femaleDescriptionsMap.put(30, "trigésima");
        femaleDescriptionsMap.put(40, "cuadragésima");
        femaleDescriptionsMap.put(50, "quincuagésima");
        femaleDescriptionsMap.put(60, "sexagésima");
        femaleDescriptionsMap.put(70, "septuagésima");
        femaleDescriptionsMap.put(80, "octagésima");
        femaleDescriptionsMap.put(90, "nonagésima");
        femaleDescriptionsMap.put(100, "centésima");
        femaleDescriptionsMap.put(200, "ducentésima");
        femaleDescriptionsMap.put(300, "tricentésima");
        femaleDescriptionsMap.put(400, "cuadringentésima");
        femaleDescriptionsMap.put(500, "quingentésima");
        femaleDescriptionsMap.put(600, "sexcentésima");
        femaleDescriptionsMap.put(700, "septingentésima");
        femaleDescriptionsMap.put(800, "octingentésima");
        femaleDescriptionsMap.put(900, "noningentésima");
        femaleDescriptionsMap.put(1000, "milésima");
    }
}
