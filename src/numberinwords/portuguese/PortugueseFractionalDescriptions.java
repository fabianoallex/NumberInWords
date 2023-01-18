package numberinwords.portuguese;

import java.util.HashMap;
import java.util.Map;

public class PortugueseFractionalDescriptions {
    static final String[] maleSuffixes = new String[] {
            "", "milésimo", "milionésimo", "bilionésimo", "trilionésimo",
            "quatrilionésimo", "quintilionésimo", "sextilionésimo",
            "septilionésimo", "octilionésimo", "nonilionésimo", "decilionésimo"
    };

    static final Map<Long, String> descriptionsMap = new HashMap<>();

    static {
        descriptionsMap.put(0L, "");
        descriptionsMap.put(1L, "");
        descriptionsMap.put(2L, "meio");
        descriptionsMap.put(3L, "terço");
        descriptionsMap.put(4L, "quarto");
        descriptionsMap.put(5L, "quinto");
        descriptionsMap.put(6L, "sexto");
        descriptionsMap.put(7L, "sétimo");
        descriptionsMap.put(8L, "oitavo");
        descriptionsMap.put(9L, "nono");
        descriptionsMap.put(10L, "décimo");
        descriptionsMap.put(11L, "undécimo"); //onze avos
        descriptionsMap.put(12L, "duodécimo"); //doze avos
        descriptionsMap.put(20L, "vigésimo"); //vinte avos
        descriptionsMap.put(30L, "trigésimo"); //trinta avos
        descriptionsMap.put(40L, "quadragésimo"); //quarenta avos
        descriptionsMap.put(50L, "quinquagésimo"); //cinquento avos
        descriptionsMap.put(60L, "sexagésimo"); //sessenta avos
        descriptionsMap.put(70L, "septuagésimo"); //setenta avos
        descriptionsMap.put(80L, "octogésimo"); //oitenta avos
        descriptionsMap.put(90L, "nonagésimo"); //noventa avos
        descriptionsMap.put(100L, "centésimo"); //cem avos
        descriptionsMap.put(200L, "ducentésimo"); //duzentos avos
        descriptionsMap.put(300L, "trecentésimo"); //trezentos avos
        descriptionsMap.put(400L, "quadringentésimo"); //quatrocentos avos
        descriptionsMap.put(500L, "quingentésimo"); //quinhentos avos
        descriptionsMap.put(600L, "sexcentésimo"); //seiscentésimo ou seiscentos avos
        descriptionsMap.put(700L, "septingentésimo"); //setecentos avos
        descriptionsMap.put(800L, "octingentésimo"); //oitocentos avos
        descriptionsMap.put(900L, "nongentésimo "); //novecentos avos
        descriptionsMap.put(1000L, "milésimo"); //mil avos
        descriptionsMap.put(1000000L, "milionésimo"); //novecentos avos
        descriptionsMap.put(1000000000L, "bilionésimo"); //novecentos avos
        descriptionsMap.put(1000000000000L, "trilionésimo"); //novecentos avos
        descriptionsMap.put(1000000000000000L, "quatrilionésimo"); //novecentos avos
        descriptionsMap.put(1000000000000000000L, "quintilionésimo"); //novecentos avos

    }
}
