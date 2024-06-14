/*
• Danny Gazic Hallberg
• am2931
• Systemutveckling DA339A
 */

package Model;

import java.util.ArrayList;
import java.util.Collections;

public class GameModel {

    private ArrayList<Ship> ships; //array for ships

    private Map map; //map object
    private Ship ship; //ship object
    private Player player; //playerobject
    private int playerAmount; //only used in textbases version

    private Highscorelist highscorelist; //instance of highscore list

    //model constructor
    public GameModel() {

        this.highscorelist = new Highscorelist();

        this.playerAmount = -1;

        this.ships = new ArrayList<Ship>();

        //set and store ships
        this.ship = new Ship("u-boat");
        ships.add(this.ship);
        this.ship = new Ship("torpedo");
        ships.add(this.ship);
        this.ship = new Ship("hunter");
        ships.add(this.ship);
        this.ship = new Ship("cruiser");
        ships.add(this.ship);
        this.ship = new Ship("battleship");
        ships.add(this.ship);

        this.map = new Map(); //enter dimensions
    }

    //use ship blueprint to assign ships length
    public void initializeShip(){
        getShips().get(0).setHealth(getShips().get(0).getLength());
        getShips().get(1).setHealth(getShips().get(1).getLength());
        getShips().get(2).setHealth(getShips().get(2).getLength());
        getShips().get(3).setHealth(getShips().get(3).getLength());
        getShips().get(4).setHealth(getShips().get(4).getLength());
    }

    //getters-setters
    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Map getMap() {
        return map;
    }

    public Ship getShip() {
        return ship;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Highscorelist getHighscorelist() {
        return highscorelist;
    }

    public void setHighscorelist(Highscorelist highscorelist) {
        this.highscorelist = highscorelist;
    }

    public void setPlayerAmount(int playerAmount) {
        this.playerAmount = playerAmount;
    }

    public int getPlayerAmount() {
        return playerAmount;
    }

}
