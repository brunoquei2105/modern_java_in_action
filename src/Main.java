
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        List<String> names = List.of("abreu", "bruno", "caio", "dado", "emilia","joao","gisele", "mario");
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,12,13,17,21,33,22);
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );

        Map<Boolean, List<Integer>> primes = PrimesNumbers.partitionPrimes(10);
        //System.out.println(primes);

        /*
            Filtering dishes that
         */
        List<String> highCaloriesDishes = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(highCaloriesDishes);


    }



}
