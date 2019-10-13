package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckoutSolution {

    private Map<String, Product> products = new HashMap<String, Product>() {{
        put("A", new Product("A", 50, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(200, 5));
            add(new SpecialOffer(130, 3));
        }}));
        put("B", new Product("B", 30, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(45, 2));
        }}));
        put("C", new Product("C", 20, null));
        put("D", new Product("D", 15, null));
        put("E", new Product("E", 40, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(2, "B"));
        }}));
        put("F", new Product("F", 10, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(2, "F"));
        }}));
        put("G", new Product("G", 20, null));
        put("H", new Product("H", 10, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(80, 10));
            add(new SpecialOffer(45, 5));
        }}));
        put("I", new Product("I", 35, null));
        put("J", new Product("J", 60, null));
        put("K", new Product("K", 80, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(150, 2));
        }}));
        put("L", new Product("L", 90, null));
        put("M", new Product("M", 15, null));
        put("N", new Product("N", 40, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(3, "M"));
        }}));
        put("O", new Product("O", 10, null));
        put("P", new Product("P", 50, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(200, 5));
        }}));
        put("Q", new Product("Q", 30, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(80, 3));
        }}));
        put("R", new Product("R", 50, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(3, "Q"));
        }}));
        put("S", new Product("S", 30, null));
        put("T", new Product("T", 20, null));
        put("U", new Product("U", 40, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(3, "U"));
        }}));
        put("V", new Product("V", 50, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(90, 2));
            add(new SpecialOffer(130, 3));
        }}));
        put("W", new Product("W", 20, null));
        put("X", new Product("X", 90, null));
        put("Y", new Product("Y", 10, null));
        put("Z", new Product("Z", 50, null));
    }};

    public Integer checkout(String skus) {
        if (isValid(skus)) {
            final Integer[] total = {0};
            Map<java.lang.String, Long> skuGroups = groupSkus(skus);
            skuGroups.forEach((sku, q) -> processFreeProductOffers(products.get(sku), skuGroups));
            skuGroups.forEach((sku, q) -> total[0] += calculateValueForSpecialOffer(q.intValue(), products.get(sku)));
            return total[0];
        }
        return -1;
    }

    private void processFreeProductOffers(Product product, Map<String, Long> skuGroups) {
        List<SpecialOffer> offers = product.getSpecialOffers();
        final Integer[] remaining = { skuGroups.get(product.getSku()).intValue() };
        if (offers != null) {
            offers.forEach(offer -> {
                if (offer.getType().equals(OfferType.FREE_PRODUCT) && skuGroups.get(offer.getFreeProductSku()) != null) {
                    int availableAmount = (int) Math.floor(remaining[0] / offer.getQuantity());
                    while (availableAmount > 0) {
                        if ((product.getSku().equals(offer.getFreeProductSku()) && remaining[0] > offer.getQuantity())) {
                            skuGroups.put(offer.getFreeProductSku(), (long) Math.max(skuGroups.get(offer.getFreeProductSku()).intValue() - 1, 0));
                            remaining[0] = remaining[0] - offer.getQuantity() - 1;
                            availableAmount = (int) Math.floor(remaining[0] / offer.getQuantity());
                        } else if ((!product.getSku().equals(offer.getFreeProductSku()) && skuGroups.get(offer.getFreeProductSku()) > 0)) {
                            skuGroups.put(offer.getFreeProductSku(), (long) Math.max(skuGroups.get(offer.getFreeProductSku()).intValue() - 1, 0));
                            remaining[0] = remaining[0] - offer.getQuantity();
                            availableAmount = (int) Math.floor(remaining[0] / offer.getQuantity());
                        } else break;
                    }
                }
            });
        }
    }

    private Integer calculateValueForSpecialOffer(
            Integer quantity,
            Product product
    ) {
        List<SpecialOffer> specialOffers = product.getSpecialOffers();
        final Integer[] total = {0};
        final Integer[] remaining = {quantity};
        if (specialOffers != null) {
            specialOffers.sort(new SpecialOffer.OfferComparator());
            specialOffers.forEach(offer -> {
                Integer availableAmount = (int) Math.floor(remaining[0] / offer.getQuantity());
                if (offer.getType().equals(OfferType.SPECIAL_PRICE)) {
                    total[0] = total[0] + availableAmount * offer.getSpecialPrice();
                    remaining[0] = remaining[0] - availableAmount * offer.getQuantity();
                }
            });
        }
        total[0] = total[0] + remaining[0] * product.getPrice();
        return total[0];
    }

    private Map<java.lang.String, Long> groupSkus(String skus) {
        return Arrays.stream(skus.split(""))
                .filter(c -> !c.isEmpty())
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    private boolean isValid(String skus) {
        return skus != null
                && skus.replaceAll("[A-Z]+", "").isEmpty();
    }
}
