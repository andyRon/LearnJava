package com.andyron.v1ch08.pair2;

import com.andyron.v1ch08.pair1.Pair;

import java.time.LocalDate;

/**
 * @author Andy Ron
 */
public class PairTest2 {
    public static void main(String[] args) {

        LocalDate[] birthDays = {
                LocalDate.of(1921, 8, 1),
                LocalDate.of(1949, 10, 1),
                LocalDate.of(1978, 5, 1),
                LocalDate.of(1911, 1, 1),

        };
        Pair<LocalDate> minmax = ArrayAlg.minmax(birthDays);
        System.out.println("min:" + minmax.getFirst());
        System.out.println("max:" + minmax.getSecond());
    }

}
class ArrayAlg {
    public static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        T min = a[0];
        T max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) {
                min = a[i];
            }
            if (max.compareTo(a[i]) < 0) {
                max = a[i];
            }
        }
        return new Pair<>(min, max);
    }
}