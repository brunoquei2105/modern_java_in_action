
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<String> names = List.of("abreu", "bruno", "caio", "dado", "emilia","joao","gisele", "mario");
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,12,13,17,21,33,22,1,3,2,5,9,7);
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza rucula", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
                new Dish("sushi", false, 600, Dish.Type.FISH);
                new Dish("yakisoba", false, 1000, Dish.Type.OTHER);
                new Dish("pasta a la fungi", true, 475, Dish.Type.OTHER);

        Map<Boolean, List<Integer>> primes = PrimesNumbers.partitionPrimes(10);
        //System.out.println(primes);

        //-----------------------------------------------------
        //            STREAM FILTERING
        //-----------------------------------------------------


        /*
            Filtering dishes that have calories more than 500 and collecting their names
         */
        List<String> highCaloriesDishes = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("High calories dishes: " +highCaloriesDishes);

        /*
            Filtering vegetarian dishes.
            Method filter receive an Predicate<T> as argument.
         */
        List<Dish> vegetariansDishes = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        System.out.println("Vegeterian dishes: "+ vegetariansDishes);

        /*
            Filtering even numbers and usinf distinc method to avoid duplicated ones.
         */
        List<Integer> evenNumbersDistincted = numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Even numbers: "+ evenNumbersDistincted);
        /*
            Other to do the same thing above using Collectors.toSet()
         */
        Set<Integer> evenNumberstoSet = numbers.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toSet());
        System.out.println("Even numbers using toSet: " + evenNumberstoSet);

        //-----------------------------------------------------
        //            STREAM MAPPING
        //-----------------------------------------------------

        /*
            Streams support the map method, which takes a Function (functional interface) as argument. The function is
            applied to each element, mapping it into a new element (the word mapping is used
            because it has a meaning similar to transforming but with the nuance of “creating a new
            version of” rather than “modifying”).
         */

        /*
            Transforming a list of String into a list o Integers that represents the
            length of each name
         */
        List<Integer> namesLength = names.stream()
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println("Length of names: " + namesLength);

        /*
            FLATMAP:
            The flatMap method is a powerful tool in Java streams that combines two functionalities:
            mapping and flattening. It's used to transform a stream of elements into another stream by
            applying a function to each element and then flattening the resulting streams into a single stream.

            Here's a breakdown of how it works:

            1. Mapping:flatMap takes a function (often a lambda expression) as an argument.
            This function is similar to the one used in the map method. It accepts an element from
            the original stream and transforms it into a stream of new values.

           2. Flattening:Unlike map which preserves the structure of the stream,
           flatMap merges all the smaller streams generated from the mapping function into a single,
           flat stream. This process eliminates nested structures and creates a simpler stream to work with.

            Key Points: Input vs. Output: flatMap accepts a stream of elements of type T and the mapper function
            returns a stream of elements of type R. The final output stream will also be of type R.

            Intermediate Operation: flatMap is an intermediate operation in the stream pipeline.
            It doesn't directly produce a result but creates a new stream for further processing.

            Use Cases: Extracting elements from collections: Imagine you have a list of lists of numbers.
            You can use flatMap to extract all the individual numbers into a single stream for further calculations.

            Working with Optionals: flatMap helps deal with nested Optional objects.
            It allows you to chain operations and avoid cumbersome null checks.

            Combining Streams: If you have multiple streams you want to process together,
            flatMap can be used to merge them into a single stream for efficient manipulation.
         */
        List<List<String>> listOfList = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("orange", "mango"));

        // Using flatMap to get all fruits in a single stream
        List<String> allFruits = listOfList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // Now you can use allFruits stream for further processing
        System.out.println("All fruits transformed into single one stream: " + allFruits);

        //-----------------------------------------------------
        //            STREAM- FINDING AND MATCHING
        //-----------------------------------------------------

        /*

        Another common data processing idiom is finding whether some elements in a set of
        data match a given property. The Streams API provides such facilities through the
        allMatch, anyMatch, noneMatch, findFirst, and findAny methods of a stream.

            noneMatch(Predicate<T> predicate) --> boolean
            allMatch(Predicate<T> predicate) --> boolean
            anyMatch(Predicate<T> predicate) --> boolean
            findFirst() --> Optional<T>
            findAny() --> Optional<T>
         */


        //
        /*
        anyMatch(Predicate<T> predicate) --> The anyMatch method can be used to answer the question:
        “Is there an element in thestream matching the given predicate?”
         */
        boolean isVegetarian = menu.stream().anyMatch(Dish::isVegetarian);
        if (isVegetarian) System.out.println("Some dishes are vegetarian");

        /*
        allMatch(Predicate<T> predicate) --> The allMatch method works similarly to
        anyMatch but will check to see if all the elements of the stream match the given predicate.
         */
        boolean isHealthyFood = menu.stream().allMatch(dish -> dish.getCalories() <= 1000);
        if (isHealthyFood) System.out.println("Dishes have calories equals or smaller than 1000 calories.");

        /*
        nomeMatch(Predicate<T> predicate) --> The opposite of allMatch is noneMatch. It ensures that no
        elements in the stream match the given predicate.
         */
        boolean isHealthy = menu.stream().noneMatch(dish -> dish.getCalories() > 1000);
        if (isHealthy) System.out.println("Other way to verify if the dishes are healthy");

        /*
        findAny() --> The findAny method returns an arbitrary element of the current stream. It can be
       used in conjunction with other stream operations. For example, you may wish to find a dish that’s vegetarian.
       You can combine the filter method and findAny to express this query:
         */

        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();

        if (dish.isPresent()) System.out.println("There is some dishes that are vegetarian");
        dish.ifPresent(i -> System.out.println(i.getName()));

        /*
        findFirst() --> Some streams have an encounter order that specifies the order in which items logically
        appear in the stream (for example, a stream generated from a List or from a sorted
        sequence of data). For such streams you may wish to find the first element. There’s
        the findFirst method for this, which works similarly to findAny
         */
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(n -> n * n)
                        .filter(n -> n % 3 == 0)
                        .findFirst();
        firstSquareDivisibleByThree.ifPresent(integer -> System.out.println("Finding first square divisible by three: " +
                integer));




























    }



}
