package numberinwords.portuguese;

import numberinwords.Fractional;
import numberinwords.FractionalInWords;

public class FractionalInPortuguese implements FractionalInWords {
    private FractionalInPortuguese(Builder builder) {

    }

    @Override
    public String inWords(Fractional number) {
        return null;
    }

    public static class Builder {

        public FractionalInPortuguese build() {
            return new FractionalInPortuguese(this);
        }
    }
}
