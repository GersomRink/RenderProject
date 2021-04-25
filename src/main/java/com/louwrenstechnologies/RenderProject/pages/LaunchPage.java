package com.louwrenstechnologies.RenderProject.pages;
import com.louwrenstechnologies.RenderProject.pages.renders.SubtitleRendererPage;

import java.awt.event.*;
import javax.swing.*;

public class LaunchPage implements ActionListener{

    JFrame frame = new JFrame();
    JButton subtitleButton = new JButton("Render Subtitles");

    public LaunchPage(){

        subtitleButton.setBounds(100,160,200,40);
        subtitleButton.setFocusable(true);
        subtitleButton.addActionListener(this);

        frame.add(subtitleButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==subtitleButton) {
            frame.dispose();
            new SubtitleRendererPage();
        }
    }
}