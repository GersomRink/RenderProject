package com.louwrenstechnologies.RenderProject.pages.renders;

import com.louwrenstechnologies.RenderProject.internal.renders.ImageRenderer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SubtitleRendererPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton renderButton = new JButton("Render");
    JDialog renderDialog = new JDialog();
    JPanel progressPanel = new JPanel();
    JFileChooser directoryChooser = new JFileChooser();
    JTree tree = new JTree();
    Timer timer;
    String path;

    List<ImageRenderer> renderers = new ArrayList<ImageRenderer>();

    public SubtitleRendererPage(){
        directoryChooser.setAcceptAllFileFilterUsed(true);
        directoryChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if(f.isDirectory()) {
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "Directories";
            }
        });
        directoryChooser.showSaveDialog(frame);

        renderers.add(new ImageRenderer("hoi,","hallo"));

        renderers.add(new ImageRenderer("blblblblb,","hallo"));

        renderers.add(new ImageRenderer("fasdfadsf,","hallo"));

        renderers.add(new ImageRenderer("afsafaasdf,","hallo"));

        renderers.add(new ImageRenderer("fasdfasdf,","hallo"));

        renderers.add(new ImageRenderer("afasdfasdf,","hallo"));

        renderers.add(new ImageRenderer("asfdasdf,","hallo"));

        renderers.add(new ImageRenderer("fasdfasdf,","hallo"));

        renderers.add(new ImageRenderer("asdfasdf,","hallo"));

        // Setting the data for the Render button
        renderButton.setBounds(290,320,100,20);
        renderButton.setFocusable(true);
        renderButton.addActionListener(this);

        // Adding the render button the frame
        frame.add(renderButton);

        // Setting the data for the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

        // Setting the data for the tree view
        timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println("["+dtf.format(now)+"] "+"Refreshing tree list...");
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();

        // Setting the data for the render dialog (The screen showed when there is progress being made.)
        renderDialog.setTitle("Rendering");
        renderDialog.setSize(420,100);
        renderDialog.setContentPane(progressPanel);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        renderDialog.setLocation(x,y);
        renderDialog.setAlwaysOnTop(true);
        renderDialog.setAutoRequestFocus(true);
        renderDialog.setModal(true);
        renderDialog.setModalityType(Dialog.ModalityType.TOOLKIT_MODAL);
        renderDialog.setResizable(false);

        // Setting the content for the render dialog
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressPanel.add(progressBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(renderButton)) {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println("["+dtf.format(now)+"] "+"Starting render task: Subtitle");

            // Here is where the rendering part comes in.
            timer.stop();
            JTextPane progressText = new JTextPane();
            int i = 0;
            progressText.setText("Rendering: "+i+"/"+renderers.size());
            progressText.setEditable(false);

            // A for loop to render all subtitles:
            for(ImageRenderer renderer : renderers) {
                File rendered = new File(path+"/"+i+".png");
                if(!(rendered.exists())) {
                    try {
                        rendered.createNewFile();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }else {
                    JDialog errorDialog = new JDialog();
                    errorDialog.setTitle("Error: File exists!");
                }
                int progress = i+1;
                System.out.println("["+dtf.format(now)+"] " + "Rendering Subtitle: "+progress+"/"+renderers.size());
                progressText.setText("Rendering: "+progress+"/"+renderers.size());
                i++;
            }

            progressPanel.add(progressText);
            renderDialog.show();
        }
    }
}
