package numberinwords.portuguese;

import java.util.HashMap;
import java.util.Map;

public class CardinalDescriptions {
    static final String[] singularSuffixes = new String[]{
            "", "mil", "milhão", "bilhão", "trilhão", "quatrilhão",
            "quintilhão", "sextilhão", "septilhão", "octilhão",
            "nonilhão", "decilhão"};

    static final String[] pluralSuffixes = new String[]{
            "", "mil", "milhões", "bilhões", "trilhões", "quatrilhões",
            "quintilhões", "sextilhões", "septilhões", "octilhões",
            "nonilhões", "decilhões"};

    static final String DEFAULT_NEGATIVE_SIGNAL_DESCRIPTION = "menos";
    static final String DEFAULT_POSITIVE_SIGNAL_DESCRIPTION = "";
    static final Map<Integer, String> maleDescriptionsMap = new HashMap<>();
    static final Map<Integer, String> femaleDescriptionsMap = new HashMap<>();

    static String getSingularSuffixDescription(Suffix suffix) {
        return singularSuffixes[suffix.suffixIndex];
    }

    static String getPluralSuffixDescription(Suffix suffix) {
        return pluralSuffixes[suffix.suffixIndex];
    }

    static String getSuffixDescriptionForValue(Suffix suffix, Long forValue) {
        return (forValue == 1 ? getSingularSuffixDescription(suffix) : getPluralSuffixDescription(suffix));
    }

    static {
        maleDescriptionsMap.put(0, "zero");
        maleDescriptionsMap.put(1, "um");
        maleDescriptionsMap.put(2, "dois");
        maleDescriptionsMap.put(3, "três");
        maleDescriptionsMap.put(4, "quatro");
        maleDescriptionsMap.put(5, "cinco");
        maleDescriptionsMap.put(6, "seis");
        maleDescriptionsMap.put(7, "sete");
        maleDescriptionsMap.put(8, "oito");
        maleDescriptionsMap.put(9, "nove");
        maleDescriptionsMap.put(10, "dez");
        maleDescriptionsMap.put(11, "onze");
        maleDescriptionsMap.put(12, "doze");
        maleDescriptionsMap.put(13, "treze");
        maleDescriptionsMap.put(14, "quatorze");
        maleDescriptionsMap.put(15, "quinze");
        maleDescriptionsMap.put(16, "dezesseis");
        maleDescriptionsMap.put(17, "dezessete");
        maleDescriptionsMap.put(18, "dezoito");
        maleDescriptionsMap.put(19, "dezenove");
        maleDescriptionsMap.put(20, "vinte");
        maleDescriptionsMap.put(30, "trinta");
        maleDescriptionsMap.put(40, "quarenta");
        maleDescriptionsMap.put(50, "cinquenta");
        maleDescriptionsMap.put(60, "sessenta");
        maleDescriptionsMap.put(70, "setenta");
        maleDescriptionsMap.put(80, "oitenta");
        maleDescriptionsMap.put(90, "noventa");
        maleDescriptionsMap.put(-100, "cem");
        maleDescriptionsMap.put(100, "cento");
        maleDescriptionsMap.put(200, "duzentos");
        maleDescriptionsMap.put(300, "trezentos");
        maleDescriptionsMap.put(400, "quatrocentos");
        maleDescriptionsMap.put(500, "quinhentos");
        maleDescriptionsMap.put(600, "seiscentos");
        maleDescriptionsMap.put(700, "setecentos");
        maleDescriptionsMap.put(800, "oitocentos");
        maleDescriptionsMap.put(900, "novecentos");

        femaleDescriptionsMap.put(0, "zero");
        femaleDescriptionsMap.put(1, "uma");
        femaleDescriptionsMap.put(2, "duas");
        femaleDescriptionsMap.put(3, "três");
        femaleDescriptionsMap.put(4, "quatro");
        femaleDescriptionsMap.put(5, "cinco");
        femaleDescriptionsMap.put(6, "seis");
        femaleDescriptionsMap.put(7, "sete");
        femaleDescriptionsMap.put(8, "oito");
        femaleDescriptionsMap.put(9, "nove");
        femaleDescriptionsMap.put(10, "dez");
        femaleDescriptionsMap.put(11, "onze");
        femaleDescriptionsMap.put(12, "doze");
        femaleDescriptionsMap.put(13, "treze");
        femaleDescriptionsMap.put(14, "quatorze");
        femaleDescriptionsMap.put(15, "quinze");
        femaleDescriptionsMap.put(16, "dezesseis");
        femaleDescriptionsMap.put(17, "dezesste");
        femaleDescriptionsMap.put(18, "dezoito");
        femaleDescriptionsMap.put(19, "dezenove");
        femaleDescriptionsMap.put(20, "vinte");
        femaleDescriptionsMap.put(30, "trinta");
        femaleDescriptionsMap.put(40, "quarenta");
        femaleDescriptionsMap.put(50, "cinquenta");
        femaleDescriptionsMap.put(60, "sessenta");
        femaleDescriptionsMap.put(70, "setenta");
        femaleDescriptionsMap.put(80, "oitenta");
        femaleDescriptionsMap.put(90, "noventa");
        femaleDescriptionsMap.put(-100, "cem");
        femaleDescriptionsMap.put(100, "cento");
        femaleDescriptionsMap.put(200, "duzentas");
        femaleDescriptionsMap.put(300, "trezentas");
        femaleDescriptionsMap.put(400, "quatrocentas");
        femaleDescriptionsMap.put(500, "quinhentas");
        femaleDescriptionsMap.put(600, "seiscentas");
        femaleDescriptionsMap.put(700, "setecentas");
        femaleDescriptionsMap.put(800, "oitocentas");
        femaleDescriptionsMap.put(900, "novecentas");
    }
}