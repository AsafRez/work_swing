package org.example;

public enum LevelDifficulty {
    EASY
            {
                @Override
                void set_difficulty() {
                    StartMenu.BLOCKS_LEVEL=5;
                    Ball.X_MOVEMENT=2;
                    MainGameView.points_per_block=10;
                }
            },MEDIUM
            {
                @Override
                void set_difficulty() {
                    StartMenu.BLOCKS_LEVEL=3;
                    Ball.X_MOVEMENT=4;
                    MainGameView.points_per_block=20;

                }

            },HARD{
        @Override
        void set_difficulty() {
            StartMenu.BLOCKS_LEVEL=1;
            Ball.X_MOVEMENT=6;
            MainGameView.points_per_block=30;

        }

    };




    abstract void set_difficulty();

    }
