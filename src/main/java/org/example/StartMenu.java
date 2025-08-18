package org.example;


import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class StartMenu extends JPanel {
    public static int BLOCKS_LEVEL =0;

    //מחלקה פנימית של רקעים למסך פתיחה
    private class Background extends JPanel {
        private final Image image;
        private Background(String imageName) {
            this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/Backgrounds/"+ imageName +".png"))).getImage();
            this.setLayout(null);
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }
    private Background image_Background;
    private String[] images={"stars","stars2","retro","hills"};

    public StartMenu(int x,int y,int width,int height) {
        this.setBounds(x,y,width,height);
        this.setLayout(null);
        //טעינת תמונה רנדומלית לרקע
        this.image_Background = new Background(images[(int)(Math.random()*images.length)]);
        this.image_Background.setBounds(0,0,width,height);

        //יצירת אובייקט הפעיל מוזיקה
         SoundManager manger = new SoundManager("Main_page_music.wav");


        Font font = new Font ("Ariel" , Font.BOLD, 50);
        JLabel start_label=create_Label(0,20,Main.MENU_WIDTH,45,"Blocks Bomber",font);
        start_label.setHorizontalAlignment(SwingConstants.CENTER);
        start_label.setForeground(Color.WHITE);
        JTextField user_name=new JTextField(10);
        user_name.setBounds(Main.MENU_WIDTH/2+50,100,175, 30);
        user_name.setEditable(true);
        user_name.setFont(new Font("Ariel",Font.BOLD,15));
        JLabel enter_Uname=create_Label(Main.MENU_WIDTH/2-200,100,250,30,"Enter your name", new Font("Ariel",Font.BOLD,20));
        enter_Uname.setForeground(Color.WHITE);
        this.add(user_name);


        //יצירת 4 רשימות נגללות לבחירת מצבי משחק
        Font choice=new Font("Ariel",Font.BOLD,20);
        JLabel choose_level=create_Label(Main.MENU_WIDTH/2-200,150,250,30,"Choose Difficulty",choice);
        choose_level.setForeground(Color.WHITE);
        JComboBox<String> levelsSelector =create_JComb(Main.MENU_WIDTH/2+50,150,175,30,new String[]{"Easy","Medium","Hard"});
        JLabel choose_bars=create_Label(Main.MENU_WIDTH/2 -200,200,250,30,"Choose Player",choice);
        JComboBox<String> barsSelector =create_JComb(Main.MENU_WIDTH/2+50,200,175,30, new String[]{ "blue", "green", "pink" });
        choose_bars.setForeground(Color.WHITE);
        JLabel choose_blocks=create_Label(Main.MENU_WIDTH/2 -200,250,250,30,"Choose Blocks",choice);
        JComboBox<String> blocksSelector =create_JComb(Main.MENU_WIDTH/2+50,250,175,30, new String[]{ "blue", "orange", "purple" });
        choose_blocks.setForeground(Color.WHITE);
        JLabel choose_balls=create_Label(Main.MENU_WIDTH/2 -200,300,250,30,"Choose Ball",choice);
        JComboBox<String> ballsSelector =create_JComb(Main.MENU_WIDTH/2+50,300,175,30, new String[]{ "Basic", "Basketball", "Football","Tennis" });
        choose_balls.setForeground(Color.WHITE);

        //הגדרת פעולות לכפתורים פעולת יציאה ופעולת פתיחת חלונית המשחק
        JButton start_button=this.create_Button((int)((Main.MENU_WIDTH/2)+125),350,100,30,"Start");
        JButton instructions = this.create_Button((int)((Main.MENU_WIDTH/2)-75) ,350,150,30,"Instructions");
        JButton exit_button=this.create_Button((int)((Main.MENU_WIDTH/2)-200),350,100,30,"Exit");

        //השתקת סאונד
        JButton sound_button = this.create_Button(5,5,70,30,"Music");
        sound_button.setFont(new Font("Ariel",Font.BOLD,10));
        sound_button.setBackground(Color.green);
        sound_button.addActionListener((ActionEvent) ->{
                 manger.switch_status();
            if (manger.clip_is_runnig()) {
                sound_button.setBackground(Color.green);
            }else{
                sound_button.setBackground((Color.red));
            }
        });

        exit_button.addActionListener((event)->{
            System.exit(0);
        });
        //פעולת התחלת משחק
        start_button.addActionListener((event)->{
            String level=(String) levelsSelector.getSelectedItem();
            String bar_selected=(String) barsSelector.getSelectedItem();
            String block_selected=(String) blocksSelector.getSelectedItem();
            String ball_selected=(String) ballsSelector.getSelectedItem();
            start_Game(user_name.getText(),level,bar_selected,block_selected,ball_selected,manger);
        });
        this.add(image_Background);
        this.setVisible(true);
        this.repaint();

        JPanel instructions_Panel = new JPanel();
            instructions_Panel.setBackground(Color.black);
            instructions_Panel.setBounds(0,0,width,height);
            instructions_Panel.setLayout(null);
            instructions_Panel.setVisible(true);
        // חלון הוראות
        instructions.addActionListener((event)->{
            this.remove(getComponents());
            instructions_Panel.setVisible(false);
            this.setLayout(null);
            JTextArea textPane = new JTextArea();
            textPane.setBounds(80,0,width-100,height);
            textPane.setText( "הוראות משחק – Blocks Bomber\n\n" +
                    "• תנועה: חיצים (← →) או A / D\n" +
                    "• עצירת המשחק להפסקה: Escape\n" +
                    "• מטרה: לשבור את כל הבלוקים עם הכדור\n" +
                    "• ניקוד: קל +10, בינוני +20, קשה +30\n" +
                    "• ניצחון: ניקוי כל הבלוקים\n" +
                    "• הפסד: אם הכדור נופל מתחת למסך\n\n" +
                    "טיפים:\n" +
                    "• פגיעה בקצות ה-Bar משנה את זווית הכדור\n" +
                    "• בשלבים מסוימים המהירות עולה\n"
            );
            textPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            textPane.setEditable(false);
            textPane.setFont(new Font("Ariel",Font.BOLD,15));
            textPane.setForeground(Color.WHITE);
            textPane.setBackground(Color.BLACK);
            instructions_Panel.add(textPane);
            instructions_Panel.setVisible(true);
            instructions_Panel.repaint();
            instructions_Panel.revalidate();
            JButton backToMenu = new JButton("Back To Menu");
            //לשנות את המספרים למשתנים למצוא דרך
            //להכניס את כל הקוד פה לפונקציה ככה שהיהי יותר יעיל
            backToMenu.setBounds(250,300,200,30);
            backToMenu.setHorizontalAlignment(SwingConstants.CENTER);
            backToMenu.setFont(new Font("Ariel",Font.BOLD,10));
            backToMenu.addActionListener((ActionEvent) ->{
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                if (topFrame != null) {
                    topFrame.dispose(); // סוגר את חלון המשחק
                }
                Main.open_Menu(); // פותח את חלון התפריט
            });
            Component[] compto_current=new Component[]{
                    sound_button,backToMenu,instructions_Panel
            };
            addto_JPanel(this,compto_current);
        });
    }
    private void addto_JPanel(JPanel panel,Component[] component){
        for(int i=0;i<component.length;i++){
            panel.add(component[i]);
        }
        panel.setVisible(true);
        panel.repaint();
        panel.revalidate();
    }

    private void remove(Component[] components) {
        int i=0;
        while(i!=components.length){
            this.remove(components[i]);
            i++;
        }
    }

    //פונקציית התחלת משחק
    private void start_Game(String player_name,String level,String bar,String block,String ball,SoundManager player) {
        MainGameView.USER_NAME=player_name;
        MainGameView.sound=new SoundManager(player);
        level=level.toUpperCase();
        LevelDifficulty chosen_diff=LevelDifficulty.valueOf(level);
        chosen_diff.set_difficulty();
        ImageEditor.ImageEditorPlayer(bar);
        ImageEditor.ImageEditorBall(ball);
        ImageEditor.ImageEditorBlocks(block);
        Main.start_Game();
    }
    //פונקצייה לייצירת רשימה נגללת
    private JComboBox<String> create_JComb(int x,int y,int width,int height,String[] text) {
        JComboBox<String> temp=new JComboBox(text);
        temp.setBounds(x,y,width,height);
        temp.setFont(new Font("Ariel",Font.BOLD,20));
        this.add(temp);
        return temp;
    }
    // פונקציית יצירת כפתור
    private JButton create_Button(int x,int y,int width,int height,String text) {
        JButton startButton = new JButton(text);
        startButton.setBounds(x, y, width, height);
        this.add(startButton);
        return startButton;
    }

    // פונקציית יצירת Label- טקסט
    private JLabel create_Label(int x, int y, int width, int height, String text, Font font) {
        JLabel startLabel = new JLabel(text);
        startLabel.setBounds(x, y, width, height);
        startLabel.setFont(font);
        startLabel.setText(text);
        this.add(startLabel);
        return startLabel;
    }
}
