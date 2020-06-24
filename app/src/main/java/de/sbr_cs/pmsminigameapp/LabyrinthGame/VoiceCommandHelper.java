package de.sbr_cs.pmsminigameapp.LabyrinthGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class VoiceCommandHelper {

    private static List<String> upSynonyms = Arrays.asList("hoch","rauf","oben","up");
    private static List<String> rightSynonyms = Arrays.asList("rechts","right");
    private static List<String> downSynonyms = Arrays.asList("runter","herunter","unten","down");
    private static List<String> leftSynonyms = Arrays.asList("links","left");

    public static Direction convertWordToDirection(String word){

        if(upSynonyms.contains(word.toLowerCase())){
            return Direction.UP;
        } else if (rightSynonyms.contains(word.toLowerCase())){
            return Direction.RIGHT;
        } else if (downSynonyms.contains(word.toLowerCase())){
            return Direction.DOWN;
        } else if (leftSynonyms.contains(word.toLowerCase())){
            return Direction.LEFT;
        }


        return null;
    }

}
