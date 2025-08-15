import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Gravity Box");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel(1000,700);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.start();

    }

    static class GamePanel extends JPanel implements Runnable{
        private final int width,height;
        private final int margin;// מרחק קבוע מהדפנות
        private final int wall = 4;

        private double x,y;
        private final double radius = 18;

        private Thread loop;
        private volatile boolean running = false;

        private double velocityX = 220;
        private double velocityY =0;

        private final double minVelocity = 1;
        private final double bounceBoost = 1.05; // בכל פגיעה הכדור יקבל ״בוסט״
        private final double maxSpeed = 1300;

        private final double gravity = 1200.0;// פיקסלים לשניה בריבוע למטה
        private final double restitution = 1;//קפיציות
        private final double airDrag = 1;// חיכוך עם האוויר


        GamePanel(int width, int height){
            this.width = width;
            this.height = height;
            this.margin = (int) (height *0.01);// חישוב margin ביחס לגודל החלון
            setPreferredSize(new Dimension(width, height));
            setBackground(Color.white);

            int boxWidth = width-2*margin;
            int boxHeight = height -2 * margin;
            x = margin+boxWidth * 0.35;
            y = margin+boxHeight * 0.25;
        }

        protected void paintComponent (Graphics graphics){
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

            //ציור הקופסה
            graphics2D.setStroke(new BasicStroke(wall));
            graphics2D.setColor(new Color(0x1976D2));
            graphics2D.drawRect(margin,margin,width-2 * margin,height -2 * margin);

            //ציור הכדור
            int dimensions = (int) (2*radius);
            int drawX = (int) Math.round(x-radius);
            int drawY = (int) Math.round(y-radius);
            graphics2D.setColor(new Color(0x00334A));
            graphics2D.fillOval(drawX,drawY,dimensions,dimensions);
            graphics2D.setStroke(new BasicStroke(4f));
            graphics2D.drawOval(drawX,drawY,dimensions,dimensions);

            graphics2D.dispose();
        }

        void start (){
            if (running) return;
            running = true;
            loop = new Thread(this,"game-loop");
            loop.start();
        }

        public void run() {
            long last = System.nanoTime();
            while (running) { //לולאה לחישוב זמן תמידי
                long now = System.nanoTime();//הזמן הנוכחי של המערכת
                double deltaTime = (now - last) / 1_000_000_000.0; //ההפרש בין זמן הריצה הנוכחי לבין last מוצג בשניות
                last = now;

                update(deltaTime);// כל חישוב תנועה יתבצע בפיקסל/שניה ולא פיקסל/פריים
                repaint();

                try {
                    Thread.sleep(1000 / 60);
                } catch (InterruptedException ignored) {}
            }
        }

        private void update (double dt){
            //גבולות המגרש
            double left = margin + radius;
            double right = width - margin - radius;
            double top = margin + radius;
            double bottom = height - margin - radius;

            double speed = Math.hypot(velocityX,velocityY);
            if (speed > maxSpeed){
                double scale = maxSpeed / speed;
                velocityX *= scale;
                velocityY *= scale;
            }

            velocityY += gravity * dt;
            x += velocityX * dt;
            y += velocityY * dt;

            if (x < left){
                x = left;
                velocityX = Math.abs(velocityX);// קפיצה ימינה
                velocityX *= bounceBoost;
            }else if (x > right){
                x = right;
                velocityX = -Math.abs(velocityX) ; //קפיצה שמאלה
                velocityX *= bounceBoost;
            }

            if (y < top){
                y = top;
                velocityY = Math.abs(velocityY) * restitution; // קפיצה למטה
                velocityY *= bounceBoost;
            }else if (y > bottom){
                y = bottom;
                velocityY = -Math.abs(velocityY) * restitution;// קפיצה למעלה
               // velocityX *= 0.995;// חיכוך עם הריצפה
                velocityY *= bounceBoost;

                if (Math.abs(velocityY) < minVelocity){
                    velocityY =-minVelocity;
                }else {
                    velocityY *= bounceBoost;
                }
            }

            velocityX *=airDrag;
            velocityY *= airDrag;


        }
    }



}