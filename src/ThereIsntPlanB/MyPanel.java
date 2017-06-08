package ThereIsntPlanB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel{
    private JButton up;
    private JButton down;
    private JLabel n;
    private Integer current;

    public MyPanel(int width){
        setBounds(0,0,width,width);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        current = 0;
        n = new JLabel(current.toString());
        up = new JButton("UP");
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current++;
                n.setText(current.toString());
            }
        });
        down = new JButton("DOWN");
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current--;
                n.setText(current.toString());
            }
        });
        add(up);
        add(down);
        add(n);
    }

    public int getCurrent(){
        return current;
    }
}
