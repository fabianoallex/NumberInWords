package numberinwords.portuguese;

public class RealInPortuguese extends MoneyInPortuguese {
    private static final String SINGULAR_CURRENCY_NAME = "real";
    private static final String PLURAL_CURRENCY_NAME = "reais";
    private static final String SINGULAR_CENTS_NAME = "centavo";
    private static final String PLURAL_CENTS_NAME = "centavos";
    private static final String SINGULAR_CENTS_NAME_WHEN_LESS_ONE = "centavo de real";
    private static final String PLURAL_CENTS_NAME_WHEN_LESS_ONE = "centavos de real";

    public RealInPortuguese(Builder builder) {
        super(builder.superBuilder);
    }

    public static class Builder {
        private final MoneyInPortuguese.Builder superBuilder;
        public Builder() {
            superBuilder = new MoneyInPortuguese.Builder();
            superBuilder.withCurrencyName(SINGULAR_CURRENCY_NAME, PLURAL_CURRENCY_NAME);
            superBuilder.withCentsName(SINGULAR_CENTS_NAME, PLURAL_CENTS_NAME);
            superBuilder.withCentsNameWhenLessOne(SINGULAR_CENTS_NAME_WHEN_LESS_ONE, PLURAL_CENTS_NAME_WHEN_LESS_ONE);
        }

        public Builder withCommaSeparator() {
            superBuilder.withCommaSeparator();
            return this;
        }

        public RealInPortuguese build() {
            return new RealInPortuguese(this);
        }
    }
}

