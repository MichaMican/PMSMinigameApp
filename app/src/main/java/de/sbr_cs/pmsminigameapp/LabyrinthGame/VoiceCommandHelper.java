package de.sbr_cs.pmsminigameapp.LabyrinthGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class for Voice command processing
 */
public abstract class VoiceCommandHelper {

    //Synonym definitions
    private static List<String> upSynonyms = Arrays.asList("up", "hoch", "rauf", "oben", "aufwärts", "darüber", "drüber", "hinauf", "vorwärts", "vorran", "nord", "norden", "nördlich");
    private static List<String> rightSynonyms = Arrays.asList("right", "rechts", "steuerbord", "rechte", "ost", "osten", "östlich");
    private static List<String> downSynonyms = Arrays.asList("down", "runter", "herunter", "unten", "rückwärts", "süd", "süden", "südlich");
    private static List<String> leftSynonyms = Arrays.asList("left", "links", "backbord", "linke", "westen", "west", "westlich");

    /**
     * converts a command word to a move direction
     * @param word command word
     * @return Direction the word commands | returns null if the provided word isn't a move command
     */
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
