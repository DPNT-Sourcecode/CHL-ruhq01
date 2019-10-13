package befaster.solutions.CHL;

import java.util.HashMap;
import java.util.Map;

public class CheckliteSolution {
    public Integer checklite(String skus) {
        if (isValid(skus)) {
            final Integer[] total = {0};
            groupSkus(skus).forEach((sku, quantity) -> {
                if (sku.equals("A")) {
                    total[0] = total[0] + calculateValueForSpecialOffer(quantity, 50, 130, 3);
                } else if (sku.equals("B")) {
                    total[0] = total[0] + calculateValueForSpecialOffer(quantity, 30, 45, 2);
                } else if (sku.equals("C")) {
                    total[0] = quantity * 20;
                } else {
                    total[0] = quantity * 15;
                }
            });
        }
        return -1;
    }

    private Integer calculateValueForSpecialOffer(
            Integer quantity,
            Integer normalPrice,
            Integer specialPrice,
            Integer comboQuantity
    ) {
        return ((quantity % comboQuantity) * normalPrice) + (quantity - ((quantity % comboQuantity) * specialPrice));
    }

    private Map<String, Integer> groupSkus(String skus) {
        String skusUpper = skus.toUpperCase();
        Map<String, Integer> quantities = new HashMap<String, Integer>() {{
            put("A", 0);
            put("B", 0);
            put("C", 0);
            put("D", 0);
        }};
        skusUpper.chars().forEach(c -> {
            if (c == 'C') {
                quantities.put("C", quantities.get("C") + 1);
            } else if (c == 'D') {
                quantities.put("D", quantities.get("D") + 1);
            } else if (c == 'A') {
                quantities.put("A", quantities.get("A") + 1);
            } else {
                quantities.put("B", quantities.get("B") + 1);
            }
        });
        return quantities;
    }

    private boolean isValid(String skus) {
        return skus != null
                && !skus.isEmpty()
                && skus.toUpperCase().replaceAll("[A-D]+", "").isEmpty();
    }
}

