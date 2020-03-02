package bounce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Andy Ron
 */
public class Bounce {

    public static void main(String[] args) {

    }
}

class BounceFrme extends JFrame {
    private BallComponent comp;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;

    public BounceFrme() {
        setTitle("Bounce");
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();

    }

    public void addButton(Container c, String title, ActionListener listener) {
        JButton button= new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall() {
        try {
            Ball ball = new Ball();
            comp.add(ball);

            for (int i = 1; i <= STEPS; i++) {
                ball.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {

        }
    }
}