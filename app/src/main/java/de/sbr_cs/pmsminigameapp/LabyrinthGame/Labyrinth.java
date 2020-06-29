package de.sbr_cs.pmsminigameapp.LabyrinthGame;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import de.sbr_cs.pmsminigameapp.Interface.Drawable;

/**
 * Maze Object
 */
public class Labyrinth implements Drawable {
    private List<LabyrinthPath> paths;
    private Point currentPos;
    private double scale;
    private Figure figure;

    /**
     * Standard constructor
     */
    public Labyrinth(){
        paths = new ArrayList<>();
    }

    /**
     * Sets scale on all objects that belong to the maze
     * @param scale
     */
    public void setScale(double scale) {
        this.scale = scale;
        for (LabyrinthPath p: paths) {
            p.setScale(scale);
        }
        figure.setScale(scale);
    }

    /**
     * Moves the current point into one of the four directions
     * @param dir direction to move to
     * @return returns if move was valid
     */
    public boolean move(Direction dir){

        final Point dstPos;

        switch (dir){
            case UP:
                dstPos = new Point(currentPos.getX(), currentPos.getY() - 1);
                break;
            case RIGHT:
                dstPos = new Point(currentPos.getX() + 1, currentPos.getY());
                break;
            case DOWN:
                dstPos = new Point(currentPos.getX(), currentPos.getY() + 1);
                break;
            case LEFT:
            default:
                dstPos = new Point(currentPos.getX() - 1, currentPos.getY());
                break;
        }

        List<LabyrinthPath> pathsMovedOver = paths.stream()
                .filter(path -> path.isValidMove(currentPos, dstPos))
                .collect(Collectors.toList());

        if (pathsMovedOver.isEmpty()){
            return false;
        }

        for (LabyrinthPath path: pathsMovedOver) {
            path.setMovedOver();
        }
        currentPos = dstPos;
        figure.setPosition(currentPos);
        return true;
    }

    /**
     * Generates a new Maze path
     * @param numberOfNodes Number of generation steps
     * @param maxX Maze grid X Max
     * @param maxY Maze grid Y Max
     * @return start Position of Figure
     */
    public Point generatePath(int numberOfNodes, int maxX, int maxY){

        int startX = (int) Math.round(Math.random() * maxX);
        int startY = (int) Math.round(Math.random() * maxY);

        currentPos = new Point(startX, startY);

        figure = new Figure(currentPos, maxX, maxY, scale);

        Point previousPoint = currentPos;
        for(int i = 0; i < numberOfNodes; i++){
            Point destPoint = null;
            double dirRand = Math.random();
            while (destPoint == null){
                if(dirRand > 0 && dirRand <= 0.25 && previousPoint.getX() + 1 <= maxX){
                    destPoint = new Point(previousPoint.getX() + 1, previousPoint.getY());
                } else if(dirRand > 0.25 && dirRand <= 0.5 && previousPoint.getY() + 1 <= maxX){
                    destPoint = new Point(previousPoint.getX(), previousPoint.getY() + 1);
                } else if(dirRand > 5 && dirRand <= 0.75 && previousPoint.getX() - 1 >= 0){
                    destPoint = new Point(previousPoint.getX() - 1, previousPoint.getY());
                } else if(previousPoint.getY() - 1 >= 0){
                    destPoint = new Point(previousPoint.getX(), previousPoint.getY() - 1);
                }

                dirRand = (dirRand + 0.25)%1.0f;
            }

            paths.add(new LabyrinthPath(previousPoint, destPoint, scale, maxX, maxY));
            previousPoint = destPoint;
        }

        return currentPos;
    }

    /**
     * Checks if all Path have been visited
     * @return if all Paths have been visited by the figure
     */
    public boolean isCompleted() {
        boolean isCompleted = true;
        for (LabyrinthPath path:paths) {
            isCompleted &= path.isMovedOver();
        }
        return isCompleted;
    }

    /**
     * Draws all Maze objects to the canvas
     * @param canvas Canvas on which the object should be drawn
     */
    @Override
    public void draw(Canvas canvas) {
        for (LabyrinthPath p: paths) {
            p.draw(canvas);
        }
        figure.draw(canvas);
    }

    /**
     * Draws all Maze objects to the canvas
     * @param canvas Canvas on which the object should be drawn
     * @param paint Paint the object should be displayed with
     */
    @Override
    public void draw(Canvas canvas, Paint paint) {
        draw(canvas);
    }
}
