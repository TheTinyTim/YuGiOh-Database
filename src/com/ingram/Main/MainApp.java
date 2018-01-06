package com.ingram.Main;

import com.ingram.Data.FetchCardNames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp {
    
    private JPanel panelMain;
    private JButton btnTestFetch;
    
    public MainApp ()
    {
        btnTestFetch.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e)
            {
                FetchCardNames.fetchCardNames ();
            }
        });
    }
    
    public static void main (String[] args)
    {
        JFrame mainFrame = new JFrame ("MainApp");
        mainFrame.setContentPane (new MainApp ().panelMain);
        mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        mainFrame.pack ();
        mainFrame.setVisible (true);
    }
    
}
