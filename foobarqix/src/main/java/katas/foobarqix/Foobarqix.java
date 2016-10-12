package katas.foobarqix;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class Foobarqix {

    public static String convert(int input) {
        StringBuilder toReturn = new StringBuilder();

        if (input % 3 == 0) {
            toReturn.append("Foo");
        }
        if (input % 5 == 0) {
            toReturn.append("Bar");
        }
        if (input % 7 == 0) {
            toReturn.append("Qix");
        }

        for (char caracter : Integer.toString(input).toCharArray()) {
            switch (caracter) {
                case '3':
                    toReturn.append("Foo");
                    break;
                case '5':
                    toReturn.append("Bar");
                    break;
                case '7':
                    toReturn.append("Qix");
                    break;
                case '0':
                    toReturn.append("*");
                    break;
                default:
                    if (!toReturn.toString().contains("Foo")
                        && !toReturn.toString().contains("Bar")
                        && !toReturn.toString().contains("Qix")) {
                        toReturn.append(caracter);
                    }
                    break;
            }
        }

        return toReturn.toString();
    }

}
