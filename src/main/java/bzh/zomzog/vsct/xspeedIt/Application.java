package bzh.zomzog.vsct.xspeedIt;

import java.util.Scanner;

import bzh.zomzog.vsct.xspeedIt.service.ParcelService;

public class Application {

    public static void main(final String[] args) throws Exception {
        String articles;
        if (args.length == 0) {
            try (final Scanner reader = new Scanner(System.in)) {
                System.out.println("Enter articles : ");
                articles = reader.next();
            } catch (final Exception e) {
                System.err.println("Error when reading articles from user input");
                throw e;
            }
        } else {
            articles = args[0];
        }
        if (!articles.matches("[1-9]+")) {
            System.err.println("Articles must match [1-9]+");
            throw new Exception("Articles must match [1-9]+");
        }
        final String result = ParcelService.buildParcels(articles);
        System.out.println(result);
    }

}
