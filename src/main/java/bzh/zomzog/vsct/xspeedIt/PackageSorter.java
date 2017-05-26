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
        final Map<Integer, Long> packages = input.chars()
                .mapToObj(i -> (char) i)
                .map(c -> Character.getNumericValue(c))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()));

        final List<String> result = new ArrayList<>();

        for (int i = MAX_ARTICLE_SIZE; i > 0; i--) {
            Long currentSizeAvailable = packages.get(i);
            while (currentSizeAvailable != null
                    && currentSizeAvailable > 0) {
                currentSizeAvailable--;
                packages.put(i, currentSizeAvailable);
                final StringBuilder currentPackageContent = new StringBuilder().append(i);

                int currentPackageContentSize = i;
                for (int j = i; j > 0; j--) {
                    final Long newSizeAvailable = packages.get(j);
                    if (newSizeAvailable != null
                            && newSizeAvailable > 0
                            && currentPackageContentSize + j <= MAX_PACKAGE_SIZE) {
                        currentPackageContentSize += newSizeAvailable;
                        packages.put(j, newSizeAvailable - 1);
                        currentPackageContent.append(j);
                    }
                }
                result.add(currentPackageContent.toString());
            }
        }
        return result.stream().collect(Collectors.joining("/"));
    }

}
