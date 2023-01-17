package numberinwords.portuguese;

import numberinwords.Block;
import numberinwords.Gender;
import numberinwords.Suffix;

import java.util.Map;

public class PortugueseOrdinalBlock extends Block {
    private final OrdinalInPortuguese ordinalInPortuguese;

    private PortugueseOrdinalBlock(Builder builder) {
        super(builder);
        this.ordinalInPortuguese = builder.ordinalInPortuguese;
    }

    private PortugueseOrdinalBlock(Long value, PortugueseOrdinalBlock next) {
        super(value, next);
        this.ordinalInPortuguese = next.ordinalInPortuguese;
    }

    @Override
    public PortugueseOrdinalBlock addNext(Long value) {
        return new PortugueseOrdinalBlock(value, this);
    }

    @Override
    public PortugueseOrdinalBlock getNextPronounceable() {
        return (PortugueseOrdinalBlock) super.getNextPronounceable();
    }

    @Override
    public String getNumberDescription() {
        if (this.getValue() == 0)
            return "";

        int hundred = (int) (this.getValue() / 100);
        int dozens = (int) (this.getValue() % 100);

        var numberDescriptionMap = getNumberDescriptionsMapForGender(this.ordinalInPortuguese.getGender());
        String result = "";

        if (hundred > 0)
            result = numberDescriptionMap.get(hundred * 100) + " ";

        if (dozens > 0 && dozens < 10)
            //ex. 1200. previne 'primeiro milésimo ducentésimo'. fica 'milésimo ducentésimo'
            if (!(dozens == 1 && this.getSuffix().compareTo(Suffix.NO_SUFFIX) > 0))
                result += numberDescriptionMap.get(dozens) + " ";

        if (dozens < 10)
            return result;

        int ten = dozens / 10;
        int one = dozens % 10;

        result += numberDescriptionMap.get(ten * 10) + " ";

        if (one > 0)
            result += numberDescriptionMap.get(one) + " ";

        return result;
    }

    @Override
    public String getConjuction() {
        if (this.isLastPronounceable())
            return "";

        return this.ordinalInPortuguese.isUsingCommaSeparator() ? ", " : " ";
    }

    @Override
    public String getSuffixDescription() {
        return PortugueseOrdinalDescriptions.getSuffixDescriptionForGender(
                this.getSuffix(),
                this.ordinalInPortuguese.getGender());
    }

    private Map<Integer, String> getNumberDescriptionsMapForGender(Gender gender) {
        if (gender.equals(Gender.MALE))
            return PortugueseOrdinalDescriptions.maleDescriptionsMap;

        return PortugueseOrdinalDescriptions.femaleDescriptionsMap;
    }

    public static class Builder extends Block.Builder {
        OrdinalInPortuguese ordinalInPortuguese;

        public Builder(Long number) {
            super(number);
        }

        public Builder withOrdinalInPortuguese(OrdinalInPortuguese ordinalInPortuguese) {
            this.ordinalInPortuguese = ordinalInPortuguese;
            return this;
        }

        @Override
        public PortugueseOrdinalBlock build() {
            PortugueseOrdinalBlock block = new PortugueseOrdinalBlock(this);

            for (long number = this.number/1000; number > 0; number /= 1000)
                block = block.addNext(number);

            return block;
        }
    }
}
