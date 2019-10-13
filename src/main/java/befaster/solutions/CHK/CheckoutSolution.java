package befaster.solutions.CHK;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if (isValid(skus)) {
            final Integer[] total = {0};
            groupSkus(skus).forEach((sku, q) -> {
                Integer quantity = q.intValue();
                if (sku.equals("A")) {
                    total[0] = total[0] + calculateValueForSpecialOffer(quantity, 50, 130, 3);
                } else if (sku.equals("B")) {
                    total[0] = total[0] + calculateValueForSpecialOffer(quantity, 30, 45, 2);
                } else if (sku.equals("C")) {
                    total[0] = total[0] + quantity * 20;
                } else {
                    total[0] = total[0] + quantity * 15;
                }
            });
            return total[0];
        }
        return -1;
    }

    private Integer calculateValueForSpecialOffer(
            Integer quantity,
            Integer normalPrice,
            Integer specialPrice,
            Integer comboQuantity
    ) {
        return ((quantity % comboQuantity) * normalPrice) + (int) Math.floor(quantity / comboQuantity) * specialPrice;
    }

    private Map<String, Long> groupSkus(String skus) {
        return Arrays.stream(skus.split("")).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    private boolean isValid(String skus) {
        return skus != null
                && skus.replaceAll("[A-D]+", "").isEmpty();
    }
}
