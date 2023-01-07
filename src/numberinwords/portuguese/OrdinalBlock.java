package numberinwords.portuguese;

import java.util.Map;

public class OrdinalBlock extends Block {
    private OrdinalBlock(Builder builder) {
        super(builder);
    }

    private OrdinalBlock(Long value, OrdinalBlock next) {
        super(value, next);
    }

    @Override
    OrdinalBlock addNext(Long value) {
        return new OrdinalBlock(value, this);
    }

    @Override
    OrdinalBlock getNextPronounceableBlock() {
        return (OrdinalBlock) super.getNextPronounceableBlock();
    }

    public String inWords(Gender gender) {
        var numberDescriptionMap = getNumberDescriptionsMap(gender, this.suffix);

        String result = "";

        if (this.getValue() == 0)
            result = "";
        else {
            int hundred = (int) (this.getValue() / 100);
            int dozens = (int) (this.getValue() % 100);

            if (hundred > 0)
                result += numberDescriptionMap.get(hundred * 100) + " ";

            if (dozens > 0 && dozens < 20)
                //ex. 1200. previne 'primeiro milésimo ducentésimo'. fica 'milésimo ducentésimo'
                if (!(dozens == 1 && this.suffix.equals(Suffix.THOUSAND)))
                    result += numberDescriptionMap.get(dozens) + " ";

            if (dozens >= 20) {
                int ten = dozens / 10;
                int one = dozens % 10;

                result += numberDescriptionMap.get(ten * 10) + " ";

                if (one > 0)
                    result += numberDescriptionMap.get(one) + " ";
            }
        }

        result += OrdinalDescriptions.getSuffixDescription(suffix, gender) + " ";

        return result;
    }

    Map<Integer, String> getNumberDescriptionsMap(Gender gender, Suffix suffix) {
        if (gender.equals(Gender.MALE))
            return OrdinalDescriptions.maleDescriptionsMap;

        return OrdinalDescriptions.femaleDescriptionsMap;
    }

    public static class Builder extends Block.Builder {
        Builder withNumber(Long number) {
            this.number = Math.abs(number);
            return this;
        }

        OrdinalBlock build() {
            OrdinalBlock block = new OrdinalBlock(this);

            while (true) {
                number /= 1000;
                if (number == 0)
                    break;
                block = block.addNext(number);
            }

            return block;
        }
    }
}
