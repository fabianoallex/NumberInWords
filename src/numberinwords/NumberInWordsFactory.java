package numberinwords;

import numberinwords.english.CardinalInEnglish;
import numberinwords.english.DateInEnglish;
import numberinwords.english.OrdinalInEnglish;
import numberinwords.portuguese.*;
import numberinwords.spanish.CardinalInSpanish;

public class NumberInWordsFactory {
    public static CardinalBuilderChooser createCardinalBuilderChooser() {
        return new CardinalBuilderChooser();
    }

    public static OrdinalBuilderChooser createOrdinalBuilderChooser() {
        return new OrdinalBuilderChooser();
    }

    public static DecimalBuilderChooser createDecimalBuilderChooser() {
        return new DecimalBuilderChooser();
    }

    public static DecimalUnitBuilderChooser createDecimalUnitBuilderChooser() {
        return new DecimalUnitBuilderChooser();
    }

    public static MoneyBuilderChoosers createMoneyBuilderChoosers() {
        return new MoneyBuilderChoosers();
    }

    public static DateBuilderChooser createDateBuilderChooser() {
        return new DateBuilderChooser();
    }

    public static FractionalBuilderChooser createFractionalBuilderChooser() {
        return new FractionalBuilderChooser();
    }

    public static PercentBuilderChooser createPercentBuildeChooser() {
        return new PercentBuilderChooser();
    }

    public static TimeBuilderChooser createTimeBuilderChooser() {
        return new TimeBuilderChooser();
    }


    public static class TimeBuilderChooser {
        public TimeInPortuguese.Builder forPortugueseLanguage() {
            return new TimeInPortuguese.Builder();
        }
    }

    public static class PercentBuilderChooser {
        public PercentInPortuguese.Builder forPortugueseLanguage() {
            return new PercentInPortuguese.Builder();
        }
    }

    public static class FractionalBuilderChooser {
        public FractionalInPortuguese.Builder forPortugueseLanguage() {
            return new FractionalInPortuguese.Builder();
        }
    }

    public static class DateBuilderChooser {
        public DateInPortuguese.Builder forPortugueseLanguage() {
            return new DateInPortuguese.Builder();
        }
        public DateInEnglish.Builder forEnglishLanguage() {
            return new DateInEnglish.Builder();
        }
    }

    public static class DecimalUnitBuilderChooser {
        public DecimalUnitInPortuguese.Builder forPortugueseLanguage() {
            return new DecimalUnitInPortuguese.Builder();
        }
    }

    public static class CardinalBuilderChooser {
        public CardinalInPortuguese.Builder forPortugueseLanguage() {
            return new CardinalInPortuguese.Builder();
        }

        public CardinalInEnglish.Builder forEnglishLanguage() {
            return new CardinalInEnglish.Builder();
        }

        public CardinalInSpanish.Builder forSpanishLanguage() {
            return new CardinalInSpanish.Builder();
        }
    }

    public static class OrdinalBuilderChooser {
        public OrdinalInPortuguese.Builder forPortugueseLanguage() {
            return new OrdinalInPortuguese.Builder();
        }

        public OrdinalInEnglish.Builder forEnglishLanguage() {
            return new OrdinalInEnglish.Builder();
        }
    }

    public static class DecimalBuilderChooser {
        public DecimalInPortuguese.Builder forPortugueseLanguage() {
            return new DecimalInPortuguese.Builder();
        }
    }
}


