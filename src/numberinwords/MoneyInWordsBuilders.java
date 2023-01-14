package numberinwords;

import numberinwords.portuguese.MoneyInPortuguese;

public class MoneyInWordsBuilders {
    public MoneyInWords.Builder<MoneyInPortuguese> forPortugueseLanguage() {
        return new MoneyInWords.Builder<>(MoneyInPortuguese.class);
    }

    public MoneyInPortuguese.Builder<MoneyInPortuguese> forBitcoinInPortuguese() {
        return this.forPortugueseLanguage()
                .withSubdivisionDecimalPlaces(8)
                .withCurrencyName("bitcoin", "bitcoins")
                .withCentsName("satoshi", "satoshis")
                .withCentsNameWhenLessOne("satoshi de bitcoin", "satoshis de bitcoin")
                .withCommaSeparator()
                .withGender(Gender.MALE);
    }

    public MoneyInPortuguese.Builder<MoneyInPortuguese> forDollarInPortuguese() {
        return this.forPortugueseLanguage()
                .withSubdivisionDecimalPlaces(2)
                .withCurrencyName("dólar", "dólares")
                .withCentsName("centavo", "centavos")
                .withCentsNameWhenLessOne("centavo de dólar", "centavos de dólar");
    }

    public MoneyInPortuguese.Builder<MoneyInPortuguese> forRealInPortuguese() {
        return this.forPortugueseLanguage()
                .withSubdivisionDecimalPlaces(2)
                .withCurrencyName("real", "reais")
                .withCentsName("centavo", "centavos")
                .withCentsNameWhenLessOne("centavo de real", "centavos de real");
    }

    public MoneyInPortuguese.Builder<MoneyInPortuguese> forPoundInPortuguese() {
        return this.forPortugueseLanguage()
                .withSubdivisionDecimalPlaces(2)
                .withGender(Gender.FEMALE)
                .withCurrencyName("libra esterlina", "libras esterlinas")
                .withCentsName("penny", "pence")
                .withCentsNameWhenLessOne("penny de libra esterlina", "pence de libra esterlina");
    }
}
