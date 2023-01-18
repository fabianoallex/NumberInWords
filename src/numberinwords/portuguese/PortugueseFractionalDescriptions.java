package numberinwords.portuguese;

import java.util.HashMap;
import java.util.Map;

public class PortugueseFractionalDescriptions {
    static final String[] maleSuffixes = new String[] {
            "", "milésimo", "milionésimo", "bilionésimo", "trilionésimo",
            "quatrilionésimo", "quintilionésimo", "sextilionésimo",
            "septilionésimo", "octilionésimo", "nonilionésimo", "decilionésimo"
    };

    static final Map<Long, String> maleDescriptionsMap = new HashMap<>();
    static final Map<Long, String> femaleDescriptionsMap = new HashMap<>();

    static {
        maleDescriptionsMap.put(0L, "");
        maleDescriptionsMap.put(1L, "");
        maleDescriptionsMap.put(2L, "meio");
        maleDescriptionsMap.put(3L, "terço");
        maleDescriptionsMap.put(4L, "quarto");
        maleDescriptionsMap.put(5L, "quinto");
        maleDescriptionsMap.put(6L, "sexto");
        maleDescriptionsMap.put(7L, "sétimo");
        maleDescriptionsMap.put(8L, "oitavo");
        maleDescriptionsMap.put(9L, "nono");
        maleDescriptionsMap.put(10L, "décimo");
        maleDescriptionsMap.put(11L, "undécimo"); //onze avos
        maleDescriptionsMap.put(12L, "duodécimo"); //doze avos
        maleDescriptionsMap.put(20L, "vigésimo"); //vinte avos
        maleDescriptionsMap.put(30L, "trigésimo"); //trinta avos
        maleDescriptionsMap.put(40L, "quadragésimo"); //quarenta avos
        maleDescriptionsMap.put(50L, "quinquagésimo"); //cinquento avos
        maleDescriptionsMap.put(60L, "sexagésimo"); //sessenta avos
        maleDescriptionsMap.put(70L, "septuagésimo"); //setenta avos
        maleDescriptionsMap.put(80L, "octogésimo"); //oitenta avos
        maleDescriptionsMap.put(90L, "nonagésimo"); //noventa avos
        maleDescriptionsMap.put(100L, "centésimo"); //cem avos
        maleDescriptionsMap.put(200L, "ducentésimo"); //duzentos avos
        maleDescriptionsMap.put(300L, "trecentésimo"); //trezentos avos
        maleDescriptionsMap.put(400L, "quadringentésimo"); //quatrocentos avos
        maleDescriptionsMap.put(500L, "quingentésimo"); //quinhentos avos
        maleDescriptionsMap.put(600L, "sexcentésimo"); //seiscentésimo ou seiscentos avos
        maleDescriptionsMap.put(700L, "septingentésimo"); //setecentos avos
        maleDescriptionsMap.put(800L, "octingentésimo"); //oitocentos avos
        maleDescriptionsMap.put(900L, "nongentésimo"); //novecentos avos
        maleDescriptionsMap.put(1000L, "milésimo"); //mil avos
        maleDescriptionsMap.put(1000000L, "milionésimo"); //novecentos avos
        maleDescriptionsMap.put(1000000000L, "bilionésimo"); //novecentos avos
        maleDescriptionsMap.put(1000000000000L, "trilionésimo"); //novecentos avos
        maleDescriptionsMap.put(1000000000000000L, "quatrilionésimo"); //novecentos avos
        maleDescriptionsMap.put(1000000000000000000L, "quintilionésimo"); //novecentos avos


        femaleDescriptionsMap.put(0L, "");
        femaleDescriptionsMap.put(1L, "");
        femaleDescriptionsMap.put(2L, "metade");
        femaleDescriptionsMap.put(3L, "terça");
        femaleDescriptionsMap.put(4L, "quarta");
        femaleDescriptionsMap.put(5L, "quinta");
        femaleDescriptionsMap.put(6L, "sexta");
        femaleDescriptionsMap.put(7L, "sétima");
        femaleDescriptionsMap.put(8L, "oitava");
        femaleDescriptionsMap.put(9L, "nona");
        femaleDescriptionsMap.put(10L, "décima");
        femaleDescriptionsMap.put(11L, "undécima"); //onze avos
        femaleDescriptionsMap.put(12L, "duodécima"); //doze avos
        femaleDescriptionsMap.put(20L, "vigésima"); //vinte avos
        femaleDescriptionsMap.put(30L, "trigésima"); //trinta avos
        femaleDescriptionsMap.put(40L, "quadragésima"); //quarenta avos
        femaleDescriptionsMap.put(50L, "quinquagésima"); //cinquento avos
        femaleDescriptionsMap.put(60L, "sexagésima"); //sessenta avos
        femaleDescriptionsMap.put(70L, "septuagésima"); //setenta avos
        femaleDescriptionsMap.put(80L, "octogésima"); //oitenta avos
        femaleDescriptionsMap.put(90L, "nonagésima"); //noventa avos
        femaleDescriptionsMap.put(100L, "centésima"); //cem avos
        femaleDescriptionsMap.put(200L, "ducentésima"); //duzentos avos
        femaleDescriptionsMap.put(300L, "trecentésima"); //trezentos avos
        femaleDescriptionsMap.put(400L, "quadringentésima"); //quatrocentos avos
        femaleDescriptionsMap.put(500L, "quingentésima"); //quinhentos avos
        femaleDescriptionsMap.put(600L, "sexcentésima"); //seiscentésimo ou seiscentos avos
        femaleDescriptionsMap.put(700L, "septingentésima"); //setecentos avos
        femaleDescriptionsMap.put(800L, "octingentésima"); //oitocentos avos
        femaleDescriptionsMap.put(900L, "nongentésima"); //novecentos avos
        femaleDescriptionsMap.put(1000L, "milésima"); //mil avos
        femaleDescriptionsMap.put(1000000L, "milionésima"); //novecentos avos
        femaleDescriptionsMap.put(1000000000L, "bilionésima"); //novecentos avos
        femaleDescriptionsMap.put(1000000000000L, "trilionésima"); //novecentos avos
        femaleDescriptionsMap.put(1000000000000000L, "quatrilionésima"); //novecentos avos
        femaleDescriptionsMap.put(1000000000000000000L, "quintilionésima"); //novecentos avos

    }
}
