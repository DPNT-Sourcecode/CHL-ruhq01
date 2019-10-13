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
    }

    @Test
    public void should_returnDefaultValue_when_inputNull() {
        String input = null;
        assertThat(solution.checkout(input), equalTo(DEFAULT_VALUE));
    }

    @Test
    public void should_returnDefaultValue_when_inputIsEmptyList() {
        String input = "";
        assertThat(solution.checkout(input), equalTo(DEFAULT_VALUE));
    }

    @Test
    public void should_returnCorrectSum_when_validInput() {
        String input = "AAAABBBCD";
        assertThat(solution.checkout(input), equalTo(290));
    }
}