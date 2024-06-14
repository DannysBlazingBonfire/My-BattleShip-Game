/*
• Danny Gazic Hallberg
• am2931
• Systemutveckling DA339A
 */

package Controller;

//format an abstract view of logical methods needed in order for basic functionality of the game
public interface Logic {

    boolean generateMap();

    boolean checkBoatPosition(int x, int y,String orientation,int ship);

    void initializePlayer();

    void placeShips();

    void placeShipOnMap(int x,int y,int ship);

    void shootShip();

    boolean checkGameOver();

}
