package de.sbr_cs.pmsminigameapp.LabyrinthGame;

import java.util.ArrayList;
import java.util.List;

import de.sbr_cs.pmsminigameapp.FullScreenView;
import de.sbr_cs.pmsminigameapp.Interface.ResizeListener;

/**
 * Control class for the labyrinth game
 */
public class LabyrinthGameManager implements ResizeListener {

    /**
     * Relative radius of path nodes
     */
    public static final float POINT_RADIUS = 5f;
    /**
     * Relative radius of Figure
     */
    public static final float FIGURE_RADIUS = 10f;
    /**
     * Labyrinth Grid XMax
     */
    public static final int MAX_X = 10;
    /**
     * Labyrinth Grid YMax
     */
    public static final int MAX_Y = 10;
    /**
     * Standard width which is used to calculate scale
     */
    public static final int STANDARD_WIDTH = 480;
    /**
     * Standard height which is used to calculate scale
     */
    public static final int STANDARD_HEIGHT = 720;
    private Labyrinth labyrinth;
    private FullScreenView fullScreenView;
    private double scale;
    private float canvasWidth;
    private float canvasHeight;
    private LabyrinthGameActivity labyrinthGameActivity;
    private List<String> commandHistory;

    /**
     * Standard consturctor
     * @param labyrinthGameActivity activity the game manager operates in
     * @param fullScreenView view the labyrinth game gets displayed on
     */
    public LabyrinthGameManager(LabyrinthGameActivity labyrinthGameActivity, FullScreenView fullScreenView){
        this.fullScreenView = fullScreenView;
        this.labyrinthGameActivity = labyrinthGameActivity;
        reset();
    }

    /**
     * Resets the game
     */
    private void reset() {
        fullScreenView.reset();
        labyrinth = new Labyrinth();
        labyrinth.generatePath(10, MAX_X, MAX_Y);
        fullScreenView.registerDrawable(labyrinth);
        fullScreenView.setResizeListener(this);
        canvasHeight = 100;
        canvasWidth = 100;
        commandHistory = new ArrayList<>();
        scale = 1;
    }

    /**
     * Resizes labyrinth objects
     * @param w width in px
     * @param h height in px
     */
    @Override
    public void onResize(float w, float h) {
        canvasWidth = w;
        canvasHeight = h;

        //480 * 800 is test devise size
        scale = canvasWidth / STANDARD_WIDTH;

        labyrinth.setScale(scale);
    }

    /**
     * Extracts and applys move commands from String array
     * @param words string array of potential voice commands
     */
    public void applyCommands(String[] words) {

        boolean gameOver = false;

        for (String word : words) {
            Direction dir = VoiceCommandHelper.convertWordToDirection(word);
            if(dir != null){
                commandHistory.add(word);
                if(!labyrinth.move(dir)){
                    gameOver = true;
                    break;
                }
            }
        }
        //redraws things on display
        fullScreenView.postInvalidate();

        if(gameOver){
            StringBuilder commandHistoryString = new StringBuilder("|");
            for (String s : commandHistory) {
                commandHistoryString.append(s.toUpperCase()).append("|");
            }
            labyrinthGameActivity.triggerGameOver(commandHistoryString.toString());
        }

        if(labyrinth.isCompleted()){
            labyrinthGameActivity.triggerSuccessGameOver();
        }

    }
}
