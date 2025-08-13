package org.example;

import javax.swing.*;


public class StartMenu extends JPanel {
    public StartMenu(int x,int y,int width,int height) {
        this.setBounds(x,y,width,height);
        this.setLayout(null);
        this.setVisible(true);
        JButton start_button=this.create_Button((int)((width/2)*1.2),height/2+50,100,100,"Start");
        JButton exit_button=this.create_Button((int)((width/2)*0.5),height/2+50,100,100,"Exit");
        exit_button.addActionListener((event)->{
            System.exit(0);
        });
        start_button.addActionListener((event)->{
          Main.start_Game();

        });
    }
    private JButton create_Button(int x,int y,int width,int height,String text) {
        JButton startButton = new JButton(text);
        startButton.setBounds(x, y, width, height);
        this.add(startButton);
        return startButton;
    }
//    private JCheckBox create_CheckBox(){
//        JCheckBox full_screen = new JCheckBox("Full screen");
//        full_screen.setBounds(540, 250, 100, 100);
//        this.add(full_screen);
//        full_screen.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                Main.set_Screen(full_screen.isSelected());
//
//            }
//        });
//        return full_screen;
//
//    }

}
