package org.example;

public  enum CollisionPart {
    LEFT{
        void move_ball(){
            Ball.X_MOVEMENT = (Ball.X_MOVEMENT-2);
            Ball.Y_MOVEMENT = (Ball.Y_MOVEMENT)*-1;
        }
    },
    MIDDLE{
        void move_ball(){
            Ball.X_MOVEMENT = (Ball.X_MOVEMENT-2);
            Ball.Y_MOVEMENT = (Ball.Y_MOVEMENT)*-1;
        }
    },
    RIGHT{
        void move_ball(){
            Ball.X_MOVEMENT = (Ball.X_MOVEMENT-2);
            Ball.Y_MOVEMENT = (Ball.Y_MOVEMENT)*-1;
        }
    };
    abstract void move_ball();
}
