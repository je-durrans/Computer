package com.durrans.computer.gui;

import com.durrans.computer.gen1.Component;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    static Gui g;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(()->g = new Gui());
//        for(Component c :Component.getAll()) {
//            g.display(c);
//        }
    }

    public Gui(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700,300));
        setResizable(true);
        setTitle("ALU");
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setBackground(Color.BLUE);
//        display(new Component());
        pack();
        setVisible(true);
    }

    private void display(Component c) {
        String name = c.getClass().toString().split("\\.")[c.toString().split("\\.").length-1];
        JLabel l = new JLabel();
        l.setText(name);
        l.setBackground(Color.RED);
        l.setOpaque(true);
        GridBagConstraints con = new GridBagConstraints();
        con.gridx=1; con.gridy=1;
        add(l, con);
        repaint();
    }

}
