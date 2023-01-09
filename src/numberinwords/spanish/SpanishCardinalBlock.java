package numberinwords.spanish;

import numberinwords.Block;
import numberinwords.Gender;
import numberinwords.Suffix;

import java.util.Map;

public class SpanishCardinalBlock extends Block {
    private final CardinalInSpanish cardinalInSpanish;

    private SpanishCardinalBlock(Builder builder) {
        super(builder);
        this.cardinalInSpanish = builder.cardinalInSpanish;
    }

    private SpanishCardinalBlock(Long value, SpanishCardinalBlock next) {
        super(value, next);
        this.cardinalInSpanish = next.cardinalInSpanish;
    }

    @Override
    public SpanishCardinalBlock addNext(Long value) {
        return new SpanishCardinalBlock(value, this);
    }

    @Override
    public SpanishCardinalBlock getNextPronounceable() {
        return (SpanishCardinalBlock) super.getNextPronounceable();
    }

    @Override
    public String getNumberDescription() {
        String result = "";

        var numberDescriptionMap = getNumberDescriptionsMap(this.cardinalInSpanish.getGender());

        if (this.getValue() == 0)
            result = this.getZeroDescription() + " ";

        if (this.getValue() == 100)
            result = numberDescriptionMap.get(-100) + " "; //-100 returns 'cien'. +100 returns 'ciento'

        if (result.isEmpty()) {
            int hundred = (int) (this.getValue() / 100);
            int dozens = (int) (this.getValue() % 100);

            if (hundred > 0)
                result += numberDescriptionMap.get(hundred * 100) + " ";

            boolean useApocope =
                    (dozens == 1 || dozens == 21) &&
                            (this.getSuffix().compareTo(Suffix.THOUSAND) > 0 || this.cardinalInSpanish.isUsingApocope());

            if (dozens > 0 && dozens < 30 && !(dozens == 1 && this.getSuffix().equals(Suffix.THOUSAND)))
                result += numberDescriptionMap.get(useApocope ? -dozens : dozens) + " ";

            if (dozens >= 30) {
                int ten = dozens / 10;
                int one = dozens % 10;

                result += numberDescriptionMap.get(ten * 10) + ((one > 0) ? " y " : " ");

                if (one > 0)
                    result += numberDescriptionMap.get(one) + " ";
            }
        }

        return result;
    }

    private String getZeroDescription() {
        if (this.cardinalInSpanish.getZeroDescription() == null)
            return SpanishCardinalDescriptions.maleDescriptionsMap.get(0);

        return this.cardinalInSpanish.getZeroDescription();
    }

    @Override
    public String getSuffixDescription() {
        return SpanishCardinalDescriptions.getSuffixDescriptionForValue(this.getSuffix(), this.getValue());
    }

    private Map<Integer, String> getNumberDescriptionsMap(Gender gender) {
        if (gender.equals(Gender.MALE))
            return SpanishCardinalDescriptions.maleDescriptionsMap;

        if (this.getSuffix().equals(Suffix.NO_SUFFIX) || this.getSuffix().equals(Suffix.THOUSAND))
            return SpanishCardinalDescriptions.femaleDescriptionsMap;

        return SpanishCardinalDescriptions.maleDescriptionsMap;
    }

    @Override
    public String getConjuction() {
        String comma = this.cardinalInSpanish.isUsingCommaSeparator() ? ", " : " ";

        if (this.isLastPronounceable())
            return "";

        if (!this.getNextPronounceable().isLastPronounceable())
            return comma;

        if (this.getNextPronounceable().getValue() % 100 == 0)
            return " ";

        if (this.getNextPronounceable().getValue() < 100)
            return " y ";

        return comma;
    }

    public static class Builder extends Block.Builder {
        CardinalInSpanish cardinalInSpanish;

        public Builder(Long number) {
            super(number);
        }

        public Builder withCardinalInSpanish(CardinalInSpanish cardinalInSpanish) {
            this.cardinalInSpanish = cardinalInSpanish;
            return this;
        }

        @Override
        public SpanishCardinalBlock build() {
            SpanishCardinalBlock block = new SpanishCardinalBlock(this);

            for (long number = this.number/1000; number > 0; number /= 1000)
                block = block.addNext(number);

            return block;
        }
    }
}
