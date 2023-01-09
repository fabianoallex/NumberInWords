package numberinwords.spanish;

import numberinwords.Suffix;

import java.util.HashMap;
import java.util.Map;

public class CardinalDescriptions {
    static final String[] singularSuffixes = new String[] {
            "", "mil", "millón", "billón", "trillón", "cuatrillón",
            "quintillón", "sextillón", "septillón", "octillón",
            "nonillón", "decillón"};

    static final String[] pluralSuffixes = new String[] {
            "", "mil", "millones", "billones", "trillones", "cuatrillones",
            "quintillones", "sextillones", "septillones", "octillones",
            "nonillones", "decillones"};

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
        maleDescriptionsMap.put(0, "cero");
        maleDescriptionsMap.put(1, "uno");
        maleDescriptionsMap.put(-1, "un"); //apocope
        maleDescriptionsMap.put(2, "dos");
        maleDescriptionsMap.put(3, "tres");
        maleDescriptionsMap.put(4, "cuatro");
        maleDescriptionsMap.put(5, "cinco");
        maleDescriptionsMap.put(6, "seis");
        maleDescriptionsMap.put(7, "siete");
        maleDescriptionsMap.put(8, "ocho");
        maleDescriptionsMap.put(9, "nueve");
        maleDescriptionsMap.put(10, "diez");
        maleDescriptionsMap.put(11, "once");
        maleDescriptionsMap.put(12, "doce");
        maleDescriptionsMap.put(13, "trece");
        maleDescriptionsMap.put(14, "catorce");
        maleDescriptionsMap.put(15, "quince");
        maleDescriptionsMap.put(16, "dieciséis");
        maleDescriptionsMap.put(17, "diecisiete");
        maleDescriptionsMap.put(18, "dieciocho");
        maleDescriptionsMap.put(19, "diecinueve");
        maleDescriptionsMap.put(20, "veinte");
        maleDescriptionsMap.put(21, "veintiuno");
        maleDescriptionsMap.put(-21, "veintiún"); //apocope
        maleDescriptionsMap.put(22, "veintidós");
        maleDescriptionsMap.put(23, "veintitrés");
        maleDescriptionsMap.put(24, "veinticuatro");
        maleDescriptionsMap.put(25, "veinticinco");
        maleDescriptionsMap.put(26, "veintiséis");
        maleDescriptionsMap.put(27, "veintisiete");
        maleDescriptionsMap.put(28, "veintiocho");
        maleDescriptionsMap.put(29, "veintinueve");
        maleDescriptionsMap.put(30, "treinta");
        maleDescriptionsMap.put(40, "cuarenta");
        maleDescriptionsMap.put(50, "cincuenta");
        maleDescriptionsMap.put(60, "sesenta");
        maleDescriptionsMap.put(70, "setenta");
        maleDescriptionsMap.put(80, "ochenta");
        maleDescriptionsMap.put(90, "noventa");
        maleDescriptionsMap.put(-100, "cien");
        maleDescriptionsMap.put(100, "ciento");
        maleDescriptionsMap.put(200, "doscientos");
        maleDescriptionsMap.put(300, "trescientos");
        maleDescriptionsMap.put(400, "cuatrocientos");
        maleDescriptionsMap.put(500, "quinientos");
        maleDescriptionsMap.put(600, "seiscientos");
        maleDescriptionsMap.put(700, "setecientos");
        maleDescriptionsMap.put(800, "ochocientos");
        maleDescriptionsMap.put(900, "novecientos");

        femaleDescriptionsMap.put(0, "cero");
        femaleDescriptionsMap.put(1, "una");
        femaleDescriptionsMap.put(-1, "una");
        femaleDescriptionsMap.put(2, "dos");
        femaleDescriptionsMap.put(3, "tres");
        femaleDescriptionsMap.put(4, "cuatro");
        femaleDescriptionsMap.put(5, "cinco");
        femaleDescriptionsMap.put(6, "seis");
        femaleDescriptionsMap.put(7, "siete");
        femaleDescriptionsMap.put(8, "ocho");
        femaleDescriptionsMap.put(9, "nueve");
        femaleDescriptionsMap.put(10, "diez");
        femaleDescriptionsMap.put(11, "once");
        femaleDescriptionsMap.put(12, "doce");
        femaleDescriptionsMap.put(13, "trece");
        femaleDescriptionsMap.put(14, "catorce");
        femaleDescriptionsMap.put(15, "quince");
        femaleDescriptionsMap.put(16, "dieciséis");
        femaleDescriptionsMap.put(17, "diecisiete");
        femaleDescriptionsMap.put(18, "dieciocho");
        femaleDescriptionsMap.put(19, "diecinueve");
        femaleDescriptionsMap.put(20, "veinte");
        femaleDescriptionsMap.put(21, "veintiuna");
        femaleDescriptionsMap.put(-21, "veintiún"); //apocope
        femaleDescriptionsMap.put(22, "veintidós");
        femaleDescriptionsMap.put(23, "veintitrés");
        femaleDescriptionsMap.put(24, "veinticuatro");
        femaleDescriptionsMap.put(25, "veinticinco");
        femaleDescriptionsMap.put(26, "veintiséis");
        femaleDescriptionsMap.put(27, "veintisiete");
        femaleDescriptionsMap.put(28, "veintiocho");
        femaleDescriptionsMap.put(29, "veintinueve");
        femaleDescriptionsMap.put(30, "treinta");
        femaleDescriptionsMap.put(40, "cuarenta");
        femaleDescriptionsMap.put(50, "cincuenta");
        femaleDescriptionsMap.put(60, "sesenta");
        femaleDescriptionsMap.put(70, "setenta");
        femaleDescriptionsMap.put(80, "ochenta");
        femaleDescriptionsMap.put(90, "noventa");
        femaleDescriptionsMap.put(-100, "cien");
        femaleDescriptionsMap.put(100, "ciento");
        femaleDescriptionsMap.put(200, "doscientos");
        femaleDescriptionsMap.put(300, "trescientos");
        femaleDescriptionsMap.put(400, "cuatrocientos");
        femaleDescriptionsMap.put(500, "quinientos");
        femaleDescriptionsMap.put(600, "seiscientos");
        femaleDescriptionsMap.put(700, "setecientos");
        femaleDescriptionsMap.put(800, "ochocientos");
        femaleDescriptionsMap.put(900, "novecientos");
    }
}