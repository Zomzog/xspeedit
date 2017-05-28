package bzh.zomzog.vsct.xspeedIt;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

import bzh.zomzog.vsct.xspeedIt.service.ParcelService;

/**
 * Utils for tests
 * 
 * @author Thibault Duperron
 *
 */
public class TestUtils {

    /**
     * Check if articles all articles are in parcels
     * 
     * @param articles
     *            articles as String
     * @param parcels
     *            parcels as string separated by
     *            {@value ParcelService#SEPARATOR}
     */
    public static void checkAllArticlesOnParcels(final String articles, final String parcels) {
        System.out.println("Input  : " + articles);
        System.out.println("Result : " + parcels);
        final String flitredResult = parcels.replace(ParcelService.SEPARATOR, "");
        assertThat(sortStringChars(articles)).isEqualTo(sortStringChars(flitredResult));
    }

    /**
     * Sort char of a string
     * 
     * @param s
     *            the string
     * @return the string sorted
     */
    private static String sortStringChars(final String s) {
        final char[] chars = s.toCharArray();
        Arrays.sort(chars);
        final String sorted = new String(chars);
        return sorted;
    }

    @Test
    public void sortStringTest() {
        assertThat(sortStringChars("aabbcc")).isEqualTo("aabbcc");
        assertThat(sortStringChars("abcabc")).isEqualTo("aabbcc");
        assertThat(sortStringChars("zer")).isEqualTo("erz");
    }

    @Test
    public void checkArticleListTest() {
        checkAllArticlesOnParcels("aabbcc", "aa/bb/cc");
        checkAllArticlesOnParcels("aabbcc", "ab/ca/bc");
        checkAllArticlesOnParcels("zer", "z/er");
    }
}
