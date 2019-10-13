package befaster.solutions.CHL;

import java.util.HashMap;
import java.util.Map;

public class CheckliteSolution {
    public Integer checklite(String skus) {
        if (isValid(skus)) {
            Map<String, Integer> quantities = new HashMap<>();
            String skusUpper = skus.toUpperCase();
            skusUpper.chars().forEach(c -> {

            });
        }
        return -1;
    }

    private boolean isValid(String skus) {
        return skus != null
                && !skus.isEmpty()
                && skus.toUpperCase().replaceAll("[A-D]+", "").length() == skus.length();
    }
}




