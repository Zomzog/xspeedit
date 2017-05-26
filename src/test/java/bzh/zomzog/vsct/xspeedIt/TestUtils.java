package bzh.zomzog.vsct.xspeedIt;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class TestUtils {

    public static void checkArticleList(final String input, final String result) {
        System.out.println("Input  : " + input);
        System.out.println("Result : " + result);
        final String flitredResult = result.replace(PackageSorter.SEPARATOR, "");
        assertThat(sortString(input)).isEqualTo(sortString(flitredResult));
    }

    private static String sortString(final String s) {
        final char[] chars = s.toCharArray();
        Arrays.sort(chars);
        final String sorted = new String(chars);
        return sorted;
    }

    @Test
    public void sortStringTest() {
        assertThat(sortString("aabbcc")).isEqualTo("aabbcc");
        assertThat(sortString("abcabc")).isEqualTo("aabbcc");
        assertThat(sortString("zer")).isEqualTo("erz");
    }

    @Test
    public void checkArticleListTest() {
        checkArticleList("aabbcc", "aa/bb/cc");
        checkArticleList("aabbcc", "ab/ca/bc");
        checkArticleList("zer", "z/er");
    }
}
