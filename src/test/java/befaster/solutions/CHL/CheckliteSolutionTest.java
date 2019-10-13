package befaster.solutions.CHL;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckliteSolutionTest {

    private final static int DEFAULT_VALUE = -1;

    private CheckliteSolution solution;

    @Before
    public void setup() {
        solution = new CheckliteSolution();
    }

    @Test
    public void should_returnDefaultValue_when_invalidInput() {
        String input = "ABC45";
        assertThat(solution.checklite(input), equalTo(DEFAULT_VALUE));
    }

    @Test
    public void should_returnDefaultValue_when_inputNull() {
        String input = null;
        assertThat(solution.checklite(input), equalTo(DEFAULT_VALUE));
    }

    @Test
    public void should_returnDefaultValue_when_inputIsEmptyList() {
        String input = "";
        assertThat(solution.checklite(input), equalTo(DEFAULT_VALUE));
    }

    @Test
    public void should_returnCorrectSum_when_validInput() {
        String input = "AAAABBBCD";
        assertThat(solution.checklite(input), equalTo(290));
    }
}
