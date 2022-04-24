/**
 * @author Andy Ron
 */
public class Demo1 {
    public static void main(String[] args) {
        // 返回虚拟机使用的最大内存
        long max = Runtime.getRuntime().maxMemory();
        // 返回虚拟机初始化的最大内存
        long total = Runtime.getRuntime().totalMemory();

        System.out.println("max=" + max + "字节\t" + (max/(double)(1024*1024)) + "MB" );
        System.out.println("total=" + total + "字节\t" + (total/(double)(1024*1024)) + "MB" );
    }
}
