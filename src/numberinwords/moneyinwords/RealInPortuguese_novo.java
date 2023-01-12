package numberinwords.moneyinwords;

import numberinwords.NumberInWordsFactory;
import numberinwords.portuguese.DecimalUnitInPortuguese;
import java.math.BigDecimal;

public class RealInPortuguese_novo extends DecimalUnitInPortuguese {
    private static final String SINGULAR_CENTS_DESCRIPTION = "centavo";
    private static final String PLURAL_CENTS_DESCRITPION = "centavos";
    private static final String SINGULAR_CENTS_DESCRIPTION_WHEN_LESS_ONE = "centavo de real";
    private static final String PLURAL_CENTS_DESCRITPION_WHEN_LESS_ONE = "centavos de real";

    public RealInPortuguese_novo(Builder builder) {
        super(builder.superBuilder);
    }

    @Override
    protected String getDecimalPartDescription(BigDecimal value) {
        if (this.getNumberOfDecimalPlaces(value) > 2)
            return super.getDecimalPartDescription(value);

        return this.getCentsDescription(value);
    }

    private String getCentsDescription(BigDecimal value) {
        String centsDescription = "";

        long integerPart = this.getIntegerPart(value);
        long centsPart = this.getDecimalPart(value);
        int numberOfDecimalPlaces = getNumberOfDecimalPlaces(value);

        if (numberOfDecimalPlaces == 1)
            centsPart = centsPart * 10;

        var cardinalInWords = NumberInWordsFactory.createCardinalInWordsBuilder()
                .forPortugueseLanguage()
                .withCommaSeparator(this.useCommaSeparator)
                .build();

        if (centsPart > 0)
            centsDescription = cardinalInWords.inWords(centsPart);

        if (integerPart == 0 && centsPart == 1)
            centsDescription += " " + SINGULAR_CENTS_DESCRIPTION_WHEN_LESS_ONE;

        if (integerPart == 0 && centsPart > 1)
            centsDescription += " " + PLURAL_CENTS_DESCRITPION_WHEN_LESS_ONE;

        if (integerPart > 0 && centsPart == 1)
            centsDescription += " " + SINGULAR_CENTS_DESCRIPTION;

        if (integerPart > 0 && centsPart > 1)
            centsDescription += " " + PLURAL_CENTS_DESCRITPION;

        return centsDescription;
    }

    public static class Builder {
        private final DecimalUnitInPortuguese.Builder superBuilder;
        public Builder() {
            superBuilder = new DecimalUnitInPortuguese.Builder();
            superBuilder.withMaleGender();
            superBuilder.withUnitDescriptions("real", "reais");
        }

        Builder withCommaSeparator() {
            this.superBuilder.withCommaSeparator();
            return this;
        }

        public RealInPortuguese_novo build() {
            return new RealInPortuguese_novo(this);
        }
    }
}
