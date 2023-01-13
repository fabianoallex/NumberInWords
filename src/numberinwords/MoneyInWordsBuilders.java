package numberinwords;

import numberinwords.portuguese.MoneyInPortuguese;

public class MoneyInWordsBuilders {
    public MoneyInPortuguese.Builder forPortugueseLanguage() {
        return new MoneyInPortuguese.Builder();
    }

    public MoneyInPortuguese.Builder forDollarInPortuguese() {
        return this.forPortugueseLanguage()
                .withSubdivisionDecimalPlaces(2)
                .withCurrencyName("dólar", "dólares")
                .withCentsName("centavo", "centavos")
                .withCentsNameWhenLessOne("centavo de dólar", "centavos de dólar");
    }

    public MoneyInPortuguese.Builder forRealInPortuguese() {
        return this.forPortugueseLanguage()
                .withCurrencyName("real", "reais")
                .withCentsName("centavo", "centavos")
                .withCentsNameWhenLessOne("centavo de real", "centavos de real");
    }

    public MoneyInPortuguese.Builder forPoundInPortuguese() {
        return this.forPortugueseLanguage()
                .withGender(Gender.FEMALE)
                .withCurrencyName("libra esterlina", "libras esterlinas")
                .withCentsName("penny", "pence")
                .withCentsNameWhenLessOne("penny de libra esterlina", "pence de libra esterlina");
    }
}
