package befaster.solutions.CHK;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSolutionTest {

    private final static int DEFAULT_VALUE = -1;

    private CheckoutSolution solution;

    @Before
    public void setup() {
        solution = new CheckoutSolution();
    }

    @Test
    public void should_returnDefaultValue_when_invalidInput() {
        String input = "ABC45";
        assertThat(solution.checkout(input), equalTo(DEFAULT_VALUE));
        input = "ABCa";
        assertThat(solution.checkout(input), equalTo(DEFAULT_VALUE));
    }

    @Test
    public void should_returnDefaultValue_when_inputNull() {
        String input = null;
        assertThat(solution.checkout(input), equalTo(DEFAULT_VALUE));
    }

    @Test
    public void should_returnZero_when_inputIsEmptyList() {
        String input = "";
        assertThat(solution.checkout(input), equalTo(0));
    }

    @Test
    public void should_returnCorrectSum_when_validInput() {
        String input = "AAAABBBCD";
        assertThat(solution.checkout(input), equalTo(290));
    }

    @Test
    public void should_returnCorrectSum_when_multipleSpecialPriceOffers() {
        String input = "AAAAAAAAABBBCD";
        assertThat(solution.checkout(input), equalTo(490));
    }

    @Test
    public void should_returnCorrectSum_when_freeProductOffers() {
        String input = "AAAAAAAAABBBCDEE";
        assertThat(solution.checkout(input), equalTo(540));
        input = "EEEEBB";
        assertThat(solution.checkout(input), equalTo(160));
        input = "BEBEEE";
        assertThat(solution.checkout(input), equalTo(160));
    }

    @Test
    public void should_returnCorrectSum_when_freeProductOfSameTypeOffers() {
        String input = "AAAAAAAAABBBCDEFFF";
        assertThat(solution.checkout(input), equalTo(550));
        input = "FFF";
        assertThat(solution.checkout(input), equalTo(30));
        input = "FFFF";
        assertThat(solution.checkout(input), equalTo(40));
        input = "FFFFFF";
        assertThat(solution.checkout(input), equalTo(60));
    }

}
