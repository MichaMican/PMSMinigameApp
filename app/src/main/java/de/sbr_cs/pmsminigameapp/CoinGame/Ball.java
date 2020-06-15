package de.sbr_cs.pmsminigameapp.CoinGame;

public class Ball {
    double x;
    double y;
    int radius;
    double direction;
    double velocity;

    public Ball(double x, double y, int radius, double direction, double velocity){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.direction = direction;
        this.velocity = velocity;
    }

    //TODO: Evtl vx und vy als klassenvariablen puffern
    private double getVX(){
        return Math.cos(Math.toRadians(direction)) * velocity;
    }

    private double getVY(){
        return Math.sin(Math.toRadians(direction)) * velocity;
    }

    public void changeVelocity(double velocity){
        this.velocity = velocity;
    }

    public void changeVelocity(double vx, double vy){
        velocity = Math.sqrt(vx * vx + vy * vy);
        if(vx >= 0){
            direction = Math.atan(vy/vx);
        } else {
            if(Math.atan(vy/vx) >= 0){
                direction = 180 - Math.atan(vy/vx);
            } else {
                direction = -180 - Math.atan(vy/vx);
            }
        }
    }

    public void changeDirection(double direction){
        this.direction = direction;
    }

    public void move(){
        x+=getVX();
        y+=getVY();
    }
}
