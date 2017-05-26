package bzh.zomzog.vsct.xspeedIt;

public class PackageSorter {

    public static final String SEPARATOR = "/";

    public static String sort(final String input) {
        final StringBuilder sb = new StringBuilder();
        int currentPackageContent = 0;
        for (int i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);
            final int size = Character.getNumericValue(c);
            if (currentPackageContent + size > 10) {
                sb.append(SEPARATOR);
                currentPackageContent = size;
            } else {
                currentPackageContent += size;
            }
            sb.append(c);
        }
        return sb.toString();
    }

}
