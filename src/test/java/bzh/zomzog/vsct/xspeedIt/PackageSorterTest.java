package bzh.zomzog.vsct.xspeedIt;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PackageSorterTest {

    /**
     * Best sort : "19/19/19" => 3 box
     */
    @Test
    public void simpleSort() {
        final String input = "191919";

        final String result = PackageSorter.sort(input);
        System.out.println("Input  : " + input);
        System.out.println("Result : " + result);
        final String[] packageList = result.split(PackageSorter.SEPARATOR);
        assertThat(packageList).hasSize(3);
    }

    /**
     * Best sort : "19/19/19" => 3 box
     */
    @Test
    public void complexeSort() {
        final String input = "111999";

        final String result = PackageSorter.sort(input);
        System.out.println("Input  : " + input);
        System.out.println("Result : " + result);
        final String[] packageList = result.split(PackageSorter.SEPARATOR);
        assertThat(packageList).hasSize(3);
    }
}
