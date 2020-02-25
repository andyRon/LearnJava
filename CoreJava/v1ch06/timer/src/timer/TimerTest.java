package timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @author Andy Ron
 */
public class TimerTest {

    public static void main(String[] args) {
        ActionListener listener = new TimePrinter();  // 构造一个监听器

        Timer t = new Timer(10000, listener);  // 构造一个定时器，每隔固定时间通知listener一次
        t.start();  // 启动定时器。定时调用监听器的actionPerformed
        // 显示一个包含一条消息和OK按钮的对话框。
        JOptionPane.showMessageDialog(null, "Quit program?"); // 点击OK后监听结束
        System.exit(0);
    }

}

class TimePrinter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("At the tone, the time is " + new Date());
        Toolkit.getDefaultToolkit().beep();
        // 获得默认工具箱。
        // 发出一声铃声
    }
}