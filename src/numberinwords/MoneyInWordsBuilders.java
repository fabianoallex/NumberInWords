package numberinwords;

import numberinwords.portuguese.MoneyInPortuguese;

public class MoneyInWordsBuilders {
    public MoneyInPortuguese.Builder forPortugueseLanguage() {
        return new MoneyInPortuguese.Builder();
    }

    public MoneyInWords.Builder forDollarInPortuguese() {
        return this.forPortugueseLanguage()
                .withSubdivisionDecimalPlaces(2)
                .withCurrencyName("dólar", "dólares")
                .withCentsName("centavo", "centavos")
                .withCentsNameWhenLessOne("centavo de dólar", "centavos de dólar");
    }

    public MoneyInWords.Builder forRealInPortuguese() {
        return this.forPortugueseLanguage()
                .withCurrencyName("real", "reais")
                .withCentsName("centavo", "centavos")
                .withCentsNameWhenLessOne("centavo de real", "centavos de real");
    }
}
