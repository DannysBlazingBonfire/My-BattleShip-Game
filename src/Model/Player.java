/*
• Danny Gazic Hallberg
• am2931
• Systemutveckling DA339A
 */

package Model;

//player class with player attributes
public class Player {

    private String name;
    private int score;
    private int sunkenShips;
    private int missedShots;
    private int allShots;
    private int hits;

    public Player(){
        this.name = "NO";
        this.score = 0;
        this.sunkenShips = 0;
        this.missedShots = 0;
        this.hits = 0;
        this.allShots = 0;
    }

    //constructor
    public Player(String name){
        this.name = name;
        this.score = 0;
        this.sunkenShips = 0;
        this.missedShots = 0;
        this.hits = 0;
        this.allShots = 0;
    }

    //getters-setters
    public String getName() {
        return name;
    }

    public int getAllShots() {
        return allShots;
    }

    public int getMissedShots() {
        return missedShots;
    }

    public int getScore() {
        return score;
    }

    public int getSunkenShips() {
        return sunkenShips;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllShots(int allShots) {
        this.allShots = allShots;
    }

    public void setMissedShots(int missedShots) {
        this.missedShots = missedShots;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSunkenShips(int sunkenShips) {
        this.sunkenShips = sunkenShips;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getHits() {
        return hits;
    }

    //to-string showing a players stats, used in highscorelist when game is over
    @Override
    public String toString() {
        String outText = String.format("name: " + "%s" + " score: " + "%s" + " sunken-ships: " + "%s" + " missed-shots: " + "%s" + " hits: " + "%s" + " all-shots: " + "%s", name,score,sunkenShips,missedShots,hits,allShots);
        return outText;
    }
}
