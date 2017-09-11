package katas.foobarqix;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class Foobarqix {

    public static String convert(int input) {
        StringBuilder toReturn = new StringBuilder();

        if (isAMultipleOfThree(input)) {
            toReturn.append("Foo");
        }
        if (isAMultipleOfFive(input)) {
            toReturn.append("Bar");
        }
        if (isAMultipleOfSeven(input)) {
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
                    if (!isAMultipleOfThreeOrFiveOrSeven(input)) {
                        toReturn.append(caracter);
                    }
                    break;
            }
        }

        return toReturn.toString();
    }


    private static boolean isAMultipleOfThree(int value) {
        return value % 3 == 0;
    }


    private static boolean isAMultipleOfFive(int value) {
        return value % 5 == 0;
    }


    private static boolean isAMultipleOfSeven(int value) {
        return value % 7 == 0;
    }


    private static boolean isAMultipleOfThreeOrFiveOrSeven(int value) {
        return isAMultipleOfThree(value)
               || isAMultipleOfFive(value)
               || isAMultipleOfSeven(value);
    }

}
