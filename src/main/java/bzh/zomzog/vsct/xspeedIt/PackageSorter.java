package bzh.zomzog.vsct.xspeedIt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PackageSorter {

    public static final String SEPARATOR = "/";

    public static final int MAX_ARTICLE_SIZE = 9;
    public static final int MAX_PACKAGE_SIZE = 10;

    /**
     * 
     * @param input
     * @return
     */
    public static String sort(final String input) {
        // Group all package by size
        final Map<Integer, Long> articles = input.chars()
                .mapToObj(i -> (char) i)
                .map(c -> Character.getNumericValue(c))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()));
        completeMap(articles);

        final List<String> result = new ArrayList<>();

        for (int i = MAX_ARTICLE_SIZE; i > 0; i--) {
            while (articles.get(i) > 0) {
                // Update article stock
                final Long currentSizeAvailable = articles.get(i);
                articles.put(i, currentSizeAvailable - 1);

                final StringBuilder currentPackageContent = new StringBuilder().append(i);
                int currentPackageContentSize = i;
                for (int j = i; j > 0; j--) {
                    final Long newSizeAvailable = articles.get(j);
                    if (newSizeAvailable > 0 && currentPackageContentSize + j <= MAX_PACKAGE_SIZE) {
                        // Add article
                        currentPackageContentSize += j;
                        articles.put(j, newSizeAvailable - 1);
                        currentPackageContent.append(j);
                    }
                }
                result.add(currentPackageContent.toString());
            }
        }
        return result.stream().collect(Collectors.joining("/"));
    }

    /**
     * Method used for avoiding null tests on map
     * 
     * @param packages
     */
    private static void completeMap(final Map<Integer, Long> packages) {
        for (int i = 1; i <= MAX_ARTICLE_SIZE; i++) {
            if (!packages.containsKey(i)) {
                packages.put(i, 0L);
            }
        }
    }
}
