package numberinwords;

import numberinwords.english.CardinalInEnglish;
import numberinwords.portuguese.CardinalInPortuguese;
import numberinwords.portuguese.OrdinalInPortuguese;
import numberinwords.spanish.CardinalInSpanish;

public class NumberInWordsFactory {
    public static CardinalInWordsBuilder createCardinalInWords() {
        return new CardinalInWordsBuilder();
    }

    public static OrdinalInWordsBuilder createOrdinalInWords() {
        return new OrdinalInWordsBuilder();
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
}
