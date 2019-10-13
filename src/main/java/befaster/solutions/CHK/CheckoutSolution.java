package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckoutSolution {

    private List<String> products = new ArrayList<String>(){{
        add(new String("A", 50, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(5, 200));
            add(new SpecialOffer(3, 130));
        }}));
        add(new String("B", 20, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(2, 45));
        }}));
        add(new String("C", 30, null));
        add(new String("D", 15, null));
        add(new String("E", 40, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(new Integer(2), "A"));
        }}));
    }};

    private final static Map<java.lang.String, Integer> specialPrices = new HashMap<java.lang.String, Integer>() {{
        put("A", 130);
        put("B", 45);
    }};

    private final static Map<java.lang.String, Integer> comboQuantities = new HashMap<java.lang.String, Integer>() {{
        put("A", 3);
        put("B", 2);
    }};

    public Integer checkout(java.lang.String skus) {
        if (isValid(skus)) {
            final Integer[] total = {0};
            groupSkus(skus).forEach((sku, q) -> {
                Integer quantity = q.intValue();
                if (sku.equals("E")) {
                    total[0] = total[0] + quantity * productValues.get(sku);
                } else if (sku.equals("A") || sku.equals("B")) {
                    total[0] =
                            total[0] + calculateValueForSpecialOffer(quantity,
                                                                     productValues.get(sku),);
                } else {
                    total[0] = total[0] + quantity * productValues.get(sku);
                }
            });
            return total[0];
        }
        return -1;
    }

    private Integer calculateValueForSpecialOffer(
            Integer quantity,
            Integer normalPrice,
            Map<Integer, Integer> specialConditions
    ) {
        return ((quantity % comboQuantity) * normalPrice) + (int) Math.floor(quantity / comboQuantity) * specialPrice;
    }

    private Map<java.lang.String, Long> groupSkus(java.lang.String skus) {
        return Arrays.stream(skus.split("")).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    private boolean isValid(java.lang.String skus) {
        return skus != null
                && skus.replaceAll("[A-D]+", "").isEmpty();
    }
}

