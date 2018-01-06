package com.ingram.Main;

import javax.swing.*;

public class MainApp {
    
    private JPanel panelMain;
    
    public static void main (String[] args)
    {
        JFrame mainFrame = new JFrame ("Yu-Gi-Oh! Card Manager");
        mainFrame.setContentPane (new MainApp ().panelMain);
        mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        mainFrame.pack ();
        mainFrame.setVisible (true);
    }
    
}
