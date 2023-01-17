package numberinwords.portuguese;

import java.util.HashMap;
import java.util.Map;

public class PortugueseFractionalDescriptions {
    static final String[] maleSuffixes = new String[] {
            "", "milésimo", "milionésimo", "bilionésimo", "trilionésimo",
            "quatrilionésimo", "quintilionésimo", "sextilionésimo",
            "septilionésimo", "octilionésimo", "nonilionésimo", "decilionésimo"
    };

    static final Map<Integer, String> descriptionsMap = new HashMap<>();

    static {
        descriptionsMap.put(0, "");
        descriptionsMap.put(1, "");
        descriptionsMap.put(2, "meio");
        descriptionsMap.put(3, "terço");
        descriptionsMap.put(4, "quarto");
        descriptionsMap.put(5, "quinto");
        descriptionsMap.put(6, "sexto");
        descriptionsMap.put(7, "sétimo");
        descriptionsMap.put(8, "oitavo");
        descriptionsMap.put(9, "nono");
        descriptionsMap.put(10, "décimo");
        descriptionsMap.put(11, "undécimo"); //onze avos
        descriptionsMap.put(12, "duodécimo"); //doze avos
        descriptionsMap.put(20, "vigésimo"); //vinte avos
        descriptionsMap.put(30, "trigésimo"); //trinta avos
        descriptionsMap.put(40, "quadragésimo"); //quarenta avos
        descriptionsMap.put(50, "quinquagésimo"); //cinquento avos
        descriptionsMap.put(60, "sexagésimo"); //sessenta avos
        descriptionsMap.put(70, "septuagésimo"); //setenta avos
        descriptionsMap.put(80, "octogésimo"); //oitenta avos
        descriptionsMap.put(90, "nonagésimo"); //noventa avos
        descriptionsMap.put(100, "centésimo"); //cem avos
        descriptionsMap.put(200, "ducentésimo"); //duzentos avos
        descriptionsMap.put(300, "trecentésimo"); //trezentos avos
        descriptionsMap.put(400, "quadringentésimo"); //quatrocentos avos
        descriptionsMap.put(500, "quingentésimo"); //quinhentos avos
        descriptionsMap.put(600, "sexcentésimo"); //seiscentésimo ou seiscentos avos
        descriptionsMap.put(700, "septingentésimo"); //setecentos avos
        descriptionsMap.put(800, "octingentésimo"); //oitocentos avos
        descriptionsMap.put(900, "nongentésimo "); //novecentos avos
    }
}
