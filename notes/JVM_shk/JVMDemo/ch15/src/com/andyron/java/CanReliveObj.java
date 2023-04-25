package com.andyron.java;

/**
 * 测试Object类中finalize()方法，即对象的finalization机制
 * @author andyron
 **/
public class CanReliveObj {
    public static CanReliveObj obj; // 类变量，属于GC Root

        // 只能被调用一次
//    @Override
//    protected void finalize() throws Throwable {
//        super.finalize();
//        System.out.println("调用当前类重写的finalize()方法");
//        obj = this;  // 把要回收的当前对象与GC Root建立了联系
//    }

    public static void main(String[] args) {
        try {
            obj = new CanReliveObj();
            // 对象第一次成功拯救自己
            obj = null;
            System.gc();  // 调用垃圾回收器
            System.out.println("第1次 gc");
            // 因为Finalizer线程优先级很低，暂停2秒，以等待它
            Thread.sleep(2000);
            if (obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is still alive");
            }
            System.out.println("第2次 gc");
            // 下面代码与上面完全相同，但是这次自救失败了
            obj = null;
            System.gc();
            Thread.sleep(2000);
            if (obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is still alive");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
