package befaster.solutions.CHL;

public class CheckliteSolution {
    public Integer checklite(String skus) {
        if (isValid(skus)) {

        }
        return -1;
    }

    private boolean isValid(String skus) {
        return skus != null
                && !skus.isEmpty()
                && skus.toUpperCase().replaceAll("[A-D]", "").length() == skus.length();
    }
}



