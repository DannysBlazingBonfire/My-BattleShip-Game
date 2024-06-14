/*
• Danny Gazic Hallberg
• am2931
• Systemutveckling DA339A
 */

package Model;

//class for the highscorelist
public class Highscorelist {
    private Player[] players;
    private String initials;
    private String highscoretext;
    private int playerScore;

    //constructor
    public Highscorelist(){
        this.highscoretext = "";
        this.players = new Player[11];
    }

    //getters-setters
    public int getPlayerScore() {
        return playerScore;
    }

    public String getInitials() {
        return initials;
    }

    public String getHighscoretext() {
        return highscoretext;
    }

    public void setHighscoretext(String highscoretext) {
        this.highscoretext = highscoretext;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

}
