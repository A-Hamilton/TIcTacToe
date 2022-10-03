package me.adamhamilton;

import java.util.*;

public class Game {

    private final Console c = new Console();
    public static HashMap<Integer, Boolean> spaces = new HashMap<>();
    public static String savedCharacter;
    public static String botCharacter;
    public static boolean hasGameEnded;

    public static void setHasGameEnded(boolean hasGameEnded) {
        Game.hasGameEnded = hasGameEnded;
    }

    public static void setSavedCharacter(String savedCharacter) {
        Game.savedCharacter = savedCharacter;
    }

    public static void setBotCharacter(String botCharacter) {
        Game.botCharacter = botCharacter;
    }

    public static String getBotCharacter() {
        return botCharacter;
    }

    private boolean isSpacePicked(int space){
        if(spaces.get(space) == null) return false;
        return true;
    }

    public static String getSavedCharacter() {
        return savedCharacter;
    }

    public void startGame() {
        c.print("Type 1 for 'X', Type 2 for 'O");
        try {
            int cI = Integer.parseInt(c.listenForArgs());
            if (cI == 1 || cI == 2) {
                setSavedCharacter(cI == 1 ? "X" : "O");
                setBotCharacter(cI == 2 ? "X" : "O");
            } else restartGame();
        } catch (NumberFormatException e) {
            restartGame();
        }
        while(!hasGameEnded) {
            rePrint();
            pick();
            checkForWinner();
            checkForDraw();
            rePrint();
            botPick();
            checkForWinner();
            checkForDraw();
        }
    }

    public void checkForDraw(){
        boolean isFreeSpace = true;
        for (int i = 0; i < 9; i++) {
            if(!isSpacePicked(i)) {
                isFreeSpace = false;
                break;
            }
        }
        if(isFreeSpace) setHasGameEnded(true);
    }

    public void checkForWinner(){

        boolean botWin1 = !c.nullCheckBot(1) && !c.nullCheckBot(4) && !c.nullCheckBot(7);
        boolean botWin2 = !c.nullCheckBot(1) && !c.nullCheckBot(5) && !c.nullCheckBot(9);
        boolean botWin3 = !c.nullCheckBot(3) && !c.nullCheckBot(5) && !c.nullCheckBot(7);
        boolean botWin4 = !c.nullCheckBot(1) && !c.nullCheckBot(2) && !c.nullCheckBot(3);
        boolean botWin5 = !c.nullCheckBot(4) && !c.nullCheckBot(5) && !c.nullCheckBot(6);
        boolean botWin6 = !c.nullCheckBot(7) && !c.nullCheckBot(8) && !c.nullCheckBot(9);
        boolean botWin7 = !c.nullCheckBot(2) && !c.nullCheckBot(5) && !c.nullCheckBot(8);
        boolean botWin8 = !c.nullCheckBot(3) && !c.nullCheckBot(6) && !c.nullCheckBot(9);
        boolean playerWin1 = c.nullCheckPlayer(1) && c.nullCheckPlayer(4) && c.nullCheckPlayer(7);
        boolean playerWin2 = c.nullCheckPlayer(1) && c.nullCheckPlayer(5) && c.nullCheckPlayer(9);
        boolean playerWin3 = c.nullCheckPlayer(3) && c.nullCheckPlayer(5) && c.nullCheckPlayer(7);
        boolean playerWin4 = c.nullCheckPlayer(1) && c.nullCheckPlayer(2) && c.nullCheckPlayer(3);
        boolean playerWin5 = c.nullCheckPlayer(4) && c.nullCheckPlayer(5) && c.nullCheckPlayer(6);
        boolean playerWin6 = c.nullCheckPlayer(7) && c.nullCheckPlayer(8) && c.nullCheckPlayer(9);
        boolean playerWin7 = c.nullCheckPlayer(2) && c.nullCheckPlayer(5) && c.nullCheckPlayer(8);
        boolean playerWin8 = c.nullCheckPlayer(3) && c.nullCheckPlayer(6) && c.nullCheckPlayer(9);

        List<Boolean> botWins = Arrays.asList(botWin1, botWin2, botWin3, botWin4, botWin5, botWin6, botWin7, botWin8);
        List<Boolean> playerWins = Arrays.asList(playerWin1, playerWin2, playerWin3, playerWin4, playerWin5, playerWin6, playerWin7, playerWin8);

        botWins.forEach(win -> {
            if(win) {
                setHasGameEnded(true);
                printBotWinMessage();
                return;
            }
        });
        playerWins.forEach(win -> {
            if(win) {
                setHasGameEnded(true);
                printPlayerWinMessage();
                return;
            }
        });
    }

    public void printPlayerWinMessage(){
        c.clear();
        c.print("Congrats you've won!");
    }

    public void printBotWinMessage(){
        c.clear();
        c.print("Unlucky better luck next time!");
    }

    public void printDrawMessage(){
        c.clear();
        c.print("Draw!");
    }

    public void rePrint(){
        c.clear();
        c.printGame();
    }

    public void pick(){
        c.print("Pick a space 1-9");
        try {
            int s = Integer.parseInt(c.listenForArgs());
            if (s >= 1 && s <= 9) {
                if(isSpacePicked(s)){
                    pick();
                } else {
                    spaces.putIfAbsent(s, true);
                }
            } else restartPick();
        } catch (NumberFormatException e) {
            restartPick();
        }
    }

    public void botPick(){
        int i = new Random().nextInt((9 - 1) + 1) + 1;
        if(isSpacePicked(i)){
            botPick();
        } else {
            spaces.putIfAbsent(i, false);
        }
    }


    private void restartGame() {
        c.clear();
        startGame();
    }

    private void restartPick() {
        c.clear();
        pick();
    }

}
