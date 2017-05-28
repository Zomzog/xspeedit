package bzh.zomzog.vsct.xspeedIt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParcelService {

    public static final String SEPARATOR = "/";

    public static final int MIN_ARTICLE_SIZE = 1;
    public static final int MAX_ARTICLE_SIZE = 9;
    public static final int MAX_PARCEL_SIZE  = 10;

    /**
     * Return a string with articles on parcels separated with
     * {@value #SEPARATOR}
     * 
     * @param articlesRaw
     *            articles as String
     * @return
     */
    public static String buildParcels(final String articlesRaw) {
        // Group all package by size
        final Map<Integer, Long> articles = articlesRaw.chars()
                .mapToObj(i -> (char) i)
                .map(c -> Character.getNumericValue(c))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()));
        // Complete map for easier reading
        completeArticleMap(articles);

        final List<String> result = new ArrayList<>();

        while (hasArticles(articles)) {
            final StringBuilder currentParcelContent = new StringBuilder();
            int currentParcelContentSize = 0;
            // Filling the parcel with bigger articles first
            for (int i = MAX_ARTICLE_SIZE; i >= MIN_ARTICLE_SIZE; i--) {
                while (currentParcelContentSize < MAX_PARCEL_SIZE // check if the parcel is full 
                        && articles.get(i) > 0 // article of size 'i' remaining
                        && currentParcelContentSize + i <= MAX_PARCEL_SIZE // article of size i can be added to the parcel 
                        ) {
                    // Add article to the parcel
                    currentParcelContentSize += i;
                    currentParcelContent.append(i);
                    // Remove article from stock
                    articles.put(i, articles.get(i) - 1);
                    
                }
            }
            // Add current parcel to the content
            result.add(currentParcelContent.toString());
        }
        return result.stream().collect(Collectors.joining("/"));

    }

    /**
     * Return true if an articles is on the stock
     * 
     * @param articles
     * @return
     */
    private static boolean hasArticles(final Map<Integer, Long> articles) {
        for (final Long l : articles.values()) {
            if (l > 0L) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method used for avoiding null tests on map
     * 
     * @param articles
     */
    private static void completeArticleMap(final Map<Integer, Long> articles) {
        for (int i = 1; i <= MAX_ARTICLE_SIZE; i++) {
            if (!articles.containsKey(i)) {
                articles.put(i, 0L);
            }
        }
    }
}
