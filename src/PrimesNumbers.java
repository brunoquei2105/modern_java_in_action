import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimesNumbers {

    /*
       Verify if a number passed as argument is prime returning true or false
    */
    public static boolean isPrime (int candidate){
        return IntStream.range(2, candidate)
                .noneMatch(i -> candidate % i == 0);
    }

    /*
    Partition all numbers including  "n" (passed as argument) return a Map whit key true/false followed by
    the list of numbers.
      true = {} , false = {}
     */

    public static Map<Boolean, List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2, n)
                .boxed()
                .collect(Collectors.partitioningBy(i -> isPrime(i)));
    }
}
