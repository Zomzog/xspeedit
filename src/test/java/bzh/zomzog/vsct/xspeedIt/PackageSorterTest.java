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

        final String[] packageList = result.split(PackageSorter.SEPARATOR);
        assertThat(packageList).hasSize(3);
    }

}
