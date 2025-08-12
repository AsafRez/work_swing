package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EnemiesLayout {
    List <Enemy> enemies;


    public EnemiesLayout (){
    int numberOffEnemies = Main.WINDOW_WITH/Main.ENEMY_WIDTH;
        List<Enemy> enemies1 = new ArrayList<>(numberOffEnemies);
        for (int i=0; i<=enemies1.size(); i++){
              enemies1.add(new Enemy[i](0,0));
        }

        }





    }


