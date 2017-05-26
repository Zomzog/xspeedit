package bzh.zomzog.vsct.xspeedIt;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PackageSorterTest {

    /**
     * Best sort : "19/19/19" => 3 packages
     */
    @Test
    public void simpleSort() {
        final String input = "191919";

        final String result = PackageSorter.sort(input);
        TestUtils.checkArticleList(input, result);
        final String[] packageList = result.split(PackageSorter.SEPARATOR);
        assertThat(packageList).hasSize(3);
    }

    /**
     * Best sort : "19/19/19" => 3 packages
     */
    @Test
    public void complexeSort() {
        final String input = "111999";

        final String result = PackageSorter.sort(input);
        TestUtils.checkArticleList(input, result);
        final String[] packageList = result.split(PackageSorter.SEPARATOR);
        assertThat(packageList).hasSize(3);
    }

    /**
     * Best sort : "82/1" => 2 packages
     */
    @Test
    public void limitSize() {
        final String input = "821";

        final String result = PackageSorter.sort(input);
        TestUtils.checkArticleList(input, result);
        final String[] packageList = result.split(PackageSorter.SEPARATOR);
        assertThat(packageList).hasSize(2);
    }

    /**
     * Best sort : "82/1" => 2 packages
     */
    @Test
    public void fiveHellTest() {
        final String input = "55";

        final String result = PackageSorter.sort(input);
        TestUtils.checkArticleList(input, result);
        final String[] packageList = result.split(PackageSorter.SEPARATOR);
        assertThat(packageList).hasSize(1);
    }

    /**
     * Best sort : "163/82/46/19/8/55/73/7" => 8 packages
     */
    @Test
    public void masterSort() {
        final String input = "163841689525773";

        final String result = PackageSorter.sort(input);
        TestUtils.checkArticleList(input, result);
        final String[] packageList = result.split(PackageSorter.SEPARATOR);
        assertThat(packageList).hasSize(8);
    }

}
