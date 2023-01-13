package numberinwords;

import numberinwords.english.CardinalInEnglish;
import numberinwords.portuguese.*;
import numberinwords.spanish.CardinalInSpanish;

public class NumberInWordsFactory {
    public static CardinalInWordsBuilder createCardinalInWordsBuilder() {
        return new CardinalInWordsBuilder();
    }

    public static OrdinalInWordsBuilder createOrdinalInWordsBuilder() {
        return new OrdinalInWordsBuilder();
    }

    public static DecimalInWordsBuilder createDecimalInWordsBuilder() {
        return new DecimalInWordsBuilder();
    }

    public static DecimalUnitInWordsBuilder createDecimalUnitInWordsBuilder() {
        return new DecimalUnitInWordsBuilder();
    }

    public static RealInWordsBuilder createRealInWordsBuilder() {
        return new RealInWordsBuilder();
    }

    public static MoneyInWordsBuilder createMoneyInWordsBuilder() {
        return new MoneyInWordsBuilder();
    }

    public static class MoneyInWordsBuilder {
        public MoneyInPortuguese.Builder forPortugueseLanguage() {
            return new MoneyInPortuguese.Builder();
        }
    }

    public static class RealInWordsBuilder {
        public RealInPortuguese.Builder forPortugueseLanguage() {
            return new RealInPortuguese.Builder();
        }
    }

    public static class DecimalUnitInWordsBuilder {
        public DecimalUnitInPortuguese.Builder forPortugueseLanguage() {
            return new DecimalUnitInPortuguese.Builder();
        }
    }

    public static class CardinalInWordsBuilder {
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

    public static class OrdinalInWordsBuilder {
        public OrdinalInPortuguese.Builder forPortugueseLanguage() {
            return new OrdinalInPortuguese.Builder();
        }
    }

    public static class DecimalInWordsBuilder {
        public DecimalInPortuguese.Builder forPortugueseLanguage() {
            return new DecimalInPortuguese.Builder();
        }
    }
}
