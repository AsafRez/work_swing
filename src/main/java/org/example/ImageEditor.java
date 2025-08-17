package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class ImageEditor {
    public static Image image_P;
    public static Image image_B;
    public static Image image_BA;
    public static void ImageEditorPlayer(String image_name) {
        image_P=new ImageIcon(Objects.requireNonNull(ImageEditor.class.getResource("/images/Bars/"+image_name+".png"))).getImage();
    }

    public static void ImageEditorBlocks(String image_name) {
        image_B=new ImageIcon(Objects.requireNonNull(ImageEditor.class.getResource("/images/Blocks/"+image_name+".png"))).getImage();
    }
    public static void ImageEditorBall(String image_name) {
        image_BA=new ImageIcon(Objects.requireNonNull(ImageEditor.class.getResource("/images/BallS/"+image_name+".png"))).getImage();
    }
}
