package numberinwords;

import numberinwords.english.CardinalInEnglish;
import numberinwords.portuguese.*;
import numberinwords.spanish.CardinalInSpanish;

public class NumberInWordsFactory {
    public static CardinalInWordsBuilderChooser createCardinalInWordsBuilderChooser() {
        return new CardinalInWordsBuilderChooser();
    }

    public static OrdinalInWordsBuilderChooser createOrdinalInWordsBuilderChooser() {
        return new OrdinalInWordsBuilderChooser();
    }

    public static DecimalInWordsBuilderChooser createDecimalInWordsBuilderChooser() {
        return new DecimalInWordsBuilderChooser();
    }

    public static DecimalUnitInWordsBuilderChooser createDecimalUnitInWordsBuilderChooser() {
        return new DecimalUnitInWordsBuilderChooser();
    }

    public static MoneyInWordsBuilderChoosers createMoneyInWordsBuilderChoosers() {
        return new MoneyInWordsBuilderChoosers();
    }

    public static DateInWordsBuilderChooser createDateInWordsBuilder() {
        return new DateInWordsBuilderChooser();
    }



    public static class DateInWordsBuilderChooser {
        public DateInPortuguese.Builder forPortugueseLanguage() {
            return new DateInPortuguese.Builder();
        }
    }

    public static class DecimalUnitInWordsBuilderChooser {
        public DecimalUnitInPortuguese.Builder forPortugueseLanguage() {
            return new DecimalUnitInPortuguese.Builder();
        }
    }

    public static class CardinalInWordsBuilderChooser {
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

    public static class OrdinalInWordsBuilderChooser {
        public OrdinalInPortuguese.Builder forPortugueseLanguage() {
            return new OrdinalInPortuguese.Builder();
        }
    }

    public static class DecimalInWordsBuilderChooser {
        public DecimalInPortuguese.Builder forPortugueseLanguage() {
            return new DecimalInPortuguese.Builder();
        }
    }
}


