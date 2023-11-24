package ch03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author andyron
 * 案例-电商网站的比价需求
 *
 * 1需求说明
 *
 * - 1.1 同一款产品，同时搜索出同款产品在各大电商平台的售价；
 * - 1.2 同一款产品，同时搜索出本产品在同一个电商平台下，各个入驻卖家售价是多少
 *
 * 2 输出返回：
 *  出来结果希望是同款产品的在不同地方的价格清单列表，返回一个`List<String>`
 *
 * 《mysql》 in jd price is 88.05
 *
 * 《mysql》 in dangdang price is 86.11
 *
 * 《mysql》 in taobao price is 90.43
 *
 * 3 解决方案，比对同一个商品在各个平台上的价格，要求获得一个清单列表，
 *
 * ​	1 step by step，按部就班，查完京东查淘宝，查完淘宝查天猫•
 *
 * ​	2 all in ，万箭齐发，一口气多线程异步任务同时查询。。。。。
 **/
public class CompletableFutureMallDemo {
    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao"),
            new NetMall("pdd"),
            new NetMall("xhs")
    );

    /**
     * step by step 一家一家网站搜查
     * List<NetMall>  ---->  map    -----> List<String>
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list.stream().map(netMall ->   // map是映射，把一种类型映射为另一种类型
                String.format(productName + " in %s price is %.2f",
                        netMall.getNetMallName(),
                        netMall.calcPrice(productName)))
                .collect(Collectors.toList());

    }

    /**
     * List<NetMall>  ---->  List<CompletableFuture<String>>    -----> List<String>
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list.stream().map(netMall ->
                CompletableFuture.supplyAsync(() -> String.format(productName + "in %s pice is %.2f",
                        netMall.getNetMallName(),
                        netMall.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream()
                .map(s -> s.join())
                .collect(Collectors.toList());
        // 中间collect和stream不能省掉，因为steam有类似惰性求值的情况，
        // 如果不先collect的，CompletableFuture就不会工作
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> list1 = getPrice(list, "mysql");
        for (String ele : list1) {
            System.out.println(ele);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime - startTime) + "毫秒");

        System.out.println("-------------------");

        long startTime2 = System.currentTimeMillis();
        List<String> list2 = getPriceByCompletableFuture(list, "mysql");
        for (String ele : list2) {
            System.out.println(ele);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime2 - startTime2) + "毫秒");
    }


}

class NetMall {
    @Getter
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName) {
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        // 根据产品名称模拟价格
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}

















