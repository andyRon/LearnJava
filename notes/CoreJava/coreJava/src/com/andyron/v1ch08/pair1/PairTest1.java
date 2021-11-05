package com.andyron.v1ch08.pair1;



/**
 * @author Andy Ron
 */
public class PairTest1 {

    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb", "Ab"};
        Pair<String> minmax = ArrayAlg.minmax(words);
        System.out.println("min:" + minmax.getFirst());
        System.out.println("max:" + minmax.getSecond());
    }
}
class ArrayAlg {
    /**
     * 遍历数组同时计算出最大值和最小值
     */
    public static Pair<String> minmax(String[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        String max = a[0];
        String min = a[0];
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
