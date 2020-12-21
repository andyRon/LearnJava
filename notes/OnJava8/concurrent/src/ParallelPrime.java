
import onjava.Timer;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.LongStream.*;

/**
 * @author Andy Ron
 */
public class ParallelPrime {
    static final int COUNT = 100_000;
    public static boolean isPrime(long n) {
        return rangeClosed(2, (long)Math.sqrt(n)).noneMatch(i -> n % i == 0);
    }

    public static void main(String[] args) {

        try {
            Timer timer = new Timer();
            List<String> primes = iterate(2, i -> i + 1)
//                    .parallel()
                    .filter(ParallelPrime::isPrime)
                    .limit(COUNT)
                    .mapToObj(Long::toString)
                    .collect(Collectors.toList());
            System.out.println(timer.duration());
            Files.write(Paths.get("primes.txt"), primes, StandardOpenOption.CREATE);

        } catch (IOException e) {
            System.out.println(e.toString());
        }


    }
}
