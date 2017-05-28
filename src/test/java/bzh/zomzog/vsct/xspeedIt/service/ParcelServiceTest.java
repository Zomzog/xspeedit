package bzh.zomzog.vsct.xspeedIt.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import bzh.zomzog.vsct.xspeedIt.TestUtils;

/**
 * Test for {@link ParcelService}
 * 
 * @author Thibault Duperron
 *
 */
public class ParcelServiceTest {

    /**
     * Best sort : "19/19/19" => 3 packages
     */
    @Test
    public void simpleSort() {
        sortTest("191919", 3);
    }

    /**
     * Best sort : "19/19/19" => 3 packages
     */
    @Test
    public void complexeSort() {
        sortTest("111999", 3);
    }

    /**
     * Best sort : "82/1" => 2 packages
     */
    @Test
    public void limitSize() {
        sortTest("821", 2);
    }

    /**
     * Best sort : "55" => 1 packages
     */
    @Test
    public void multipleFiveTest() {
        sortTest("55", 1);
    }

    /**
     * Best sort : "22222/22222/2" => 3 packages
     */
    @Test
    public void multipleSameSizeTest() {
        sortTest("22222222222", 3);
    }

    /**
     * Best sort : "163/82/46/19/8/55/73/7" => 8 packages
     */
    @Test
    public void masterSortTest() {
        sortTest("163841689525773", 8);
    }

    private void sortTest(final String articles, final int parcelCount) {
        final String parcels = ParcelService.buildParcels(articles);
        TestUtils.checkAllArticlesOnParcels(articles, parcels);
        final String[] packageList = parcels.split(ParcelService.SEPARATOR);
        assertThat(packageList).hasSize(parcelCount);

    }
}
