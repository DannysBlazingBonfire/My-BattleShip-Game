/*
• Danny Gazic Hallberg
• am2931
• Systemutveckling DA339A
 */

package Controller;

import Model.GameModel;
import Model.Map;
import Model.Player;
import Model.Ship;
import View.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class GameLogic implements Logic{

    private GameModel gameModel;
    private MainFrame gameView;

    private Scanner scanner; //used in textbased version

    //initialize classes for the game
    public GameLogic(){
        gameModel = new GameModel();
        gameView = new MainFrame(this,1080,1920);

        scanner = new Scanner(System.in);
        testHighScoreList(); //adds players with 0 score for sorting funcionality
    }

    //unused method (used when game was textbased)
    public boolean playAgain(){
        boolean again = false;
        System.out.println("do you want to play again? [y/n]");
        String ans = scanner.nextLine();

        if( Objects.equals(ans, "y") ){
            return again = true;
        }
        else{
            return again;
        }
    }

    //adds 0 score player objects and sorts
    public void testHighScoreList(){

        //gameModel.setPlayerAmount(gameModel.getHighscorelist().getPlayers().length-1);
        for(int i = 0; i < gameModel.getHighscorelist().getPlayers().length; i++){
            Player nyPlayer = new Player(i+"a");
            nyPlayer.setScore( (0) );
            gameModel.getHighscorelist().getPlayers()[i] = nyPlayer;
        }

        sortHighScore();

        for(int n = 0; n < gameModel.getHighscorelist().getPlayers().length; n++){
            System.out.println(gameModel.getHighscorelist().getPlayers()[n]);
            if (gameModel.getHighscorelist().getPlayers()[n] != null){
                gameModel.getHighscorelist().setHighscoretext( gameModel.getHighscorelist().getHighscoretext().concat(gameModel.getHighscorelist().getPlayers()[n].toString() +"\n") );
            }
        }

        gameView.getHighScoreTxt().setText(gameModel.getHighscorelist().getHighscoretext());

    }

    //calculates player score and places it in the highscore array depending on the score
    public void calculatePlayerScore() {
        //assign player a score and clear current highscore in GUI
        gameModel.getHighscorelist().setHighscoretext("");
        gameModel.getPlayer().setAllShots(gameModel.getPlayer().getMissedShots() + gameModel.getPlayer().getHits());
        gameModel.getPlayer().setScore((500 * gameModel.getPlayer().getHits()) + (1000 * gameModel.getPlayer().getSunkenShips()) - (100 * gameModel.getPlayer().getMissedShots()));

        //System.out.println(gameModel.getPlayer().toString());

        gameModel.getHighscorelist().getPlayers()[10] = gameModel.getPlayer(); //place player last for sorting

        sortHighScore(); // sorts the highscore array

        //makes the highscore string
        for(int n = 0; n < gameModel.getHighscorelist().getPlayers().length; n++){
            System.out.println(gameModel.getHighscorelist().getPlayers()[n]);
            if (gameModel.getHighscorelist().getPlayers()[n] != null){
                gameModel.getHighscorelist().setHighscoretext( gameModel.getHighscorelist().getHighscoretext().concat(gameModel.getHighscorelist().getPlayers()[n].toString() +"\n") ); //concats a string with all players

            }
        }
        gameView.getHighScoreTxt().setText(gameModel.getHighscorelist().getHighscoretext()); //sets the highscore list to the concat string

        System.out.println(gameModel.getHighscorelist().getHighscoretext()); //show highscores for debug
    }

    //sorting method for highscore list
    public void sortHighScore(){

        for (int i = 0; i <= gameModel.getHighscorelist().getPlayers().length-1; i++) {

            for (int j = i + 1; j <= gameModel.getHighscorelist().getPlayers().length-1; j++) {

                if (gameModel.getHighscorelist().getPlayers()[j] != null && gameModel.getHighscorelist().getPlayers()[i] != null){

                    if (gameModel.getHighscorelist().getPlayers()[j].getScore() > gameModel.getHighscorelist().getPlayers()[i].getScore()) {
                        //store j
                        Player tempScore = gameModel.getHighscorelist().getPlayers()[j];
                        //assign i to j
                        gameModel.getHighscorelist().getPlayers()[j] = gameModel.getHighscorelist().getPlayers()[i];
                        //assign j to i position
                        gameModel.getHighscorelist().getPlayers()[i] = tempScore;

                    }
                }
                //j greater than i
            }

        }
        gameModel.getHighscorelist().getPlayers()[10] = null;
    }

    //method used in textbased version
    public boolean checkEmpty(){
        boolean empty = true;

        for (int i = 0; i < gameModel.getHighscorelist().getPlayers().length; i++) {
            if (gameModel.getHighscorelist().getPlayers()[i] != null) {
                empty = false;
            }
        }
        return empty;
    }

    //checks for sunkenships
    public void checkSunkenShip(){

        String ori;
        int len;

        for( int row = 0; row < gameModel.getMap().getMap().length; row++ ){
            for(int col = 0; col < gameModel.getMap().getMap()[row].length; col++){

                //check if instance of ship and if sunken
                if(gameModel.getMap().getMap()[row][col] instanceof Ship && ((Ship) gameModel.getMap().getMap()[row][col]).getHealth() == 0 && !Objects.equals(gameModel.getMap().getHitmap()[row][col], gameModel.getMap().getSunkenRepresentation())){

                    ori = ((Ship) gameModel.getMap().getMap()[row][col]).getOrientation();
                    len = ((Ship) gameModel.getMap().getMap()[row][col]).getLength();

                    //update GUI depending on orientation
                    if(Objects.equals(ori, "hor")){
                        for(int i = 0; i < len;i++){ //have get(i)
                            gameModel.getMap().getHitmap()[row][col+i] = String.valueOf(gameModel.getMap().getSunkenRepresentation());
                            gameView.getButtons()[row][col+i].setBackground(Color.decode("#171717"));
                        }
                    }

                    //vertical placement
                    else if(Objects.equals(ori, "ver")){
                        for(int j = 0; j < len;j++){ //have get(i)
                            gameModel.getMap().getHitmap()[row+j][col] = String.valueOf(gameModel.getMap().getSunkenRepresentation());
                            gameView.getButtons()[row+j][col].setBackground(Color.decode("#171717"));
                        }
                    }

                    //increment player stats for sunken ship
                    gameModel.getPlayer().setSunkenShips(gameModel.getPlayer().getSunkenShips()+1);
                    System.out.println("sunken ships: " + gameModel.getPlayer().getSunkenShips());
                }

            }
        }

    }

    //initialize the player
    public void initializePlayer(){

        //reset ship health
        gameModel.initializeShip();

        boolean validName = true;
        String name;

            name = gameView.getNameTxt().getText().toUpperCase();//force input to caps and max 3 letters

            if(name.length() != 3){
                JOptionPane.showMessageDialog(null,"invalid name length","Name-ERROR", JOptionPane.ERROR_MESSAGE);
                validName = false;
            }

            if(validName){
                //gameModel.setPlayerAmount(gameModel.getPlayerAmount()+1);
                gameModel.setPlayer(new Player(name));
                gameView.getNameTxt().setText("name");
                gameView.hideNameMenu();
                gameView.showMapChoice();
                System.out.println("name:" + name);
            }

    }

    //generate easy map
    public void generatePresetMapOne(){
            gameModel.setMap(new Map(10));
            gameModel.getShips().get(0).setOrientation("hor");
            placeShipOnMap(0,1,0);
            placeShipOnHitmap(0,1,0);

            gameModel.getShips().get(1).setOrientation("ver");
            placeShipOnMap(5,4,1);
            placeShipOnHitmap(5,4,1);

            gameModel.getShips().get(2).setOrientation("hor");
            placeShipOnMap(1,9,2);
            placeShipOnHitmap(1,9,2);

            gameModel.getShips().get(3).setOrientation("ver");
            placeShipOnMap(7,2,3);
            placeShipOnHitmap(7,2,3);

            gameModel.getShips().get(4).setOrientation("hor");
            placeShipOnMap(1,7,4);
            placeShipOnHitmap(1,7,4);

            gameModel.getMap().displayMap();
            gameModel.getMap().displayHitMap();
            //draw map in GUI
            gameView.drawMap(10);
    }

    //generate hard map
    public void generatePresetMapTwo(){
        gameModel.setMap(new Map(26));

        gameModel.getShips().get(0).setOrientation("hor");
        placeShipOnMap(10,9,0);
        placeShipOnHitmap(10,9,0);

        gameModel.getShips().get(1).setOrientation("ver");
        placeShipOnMap(0,5,1);
        placeShipOnHitmap(0,5,1);

        gameModel.getShips().get(2).setOrientation("hor");
        placeShipOnMap(10,15,2);
        placeShipOnHitmap(10,15,2);

        gameModel.getShips().get(3).setOrientation("ver");
        placeShipOnMap(24,18,3);
        placeShipOnHitmap(24,18,3);

        gameModel.getShips().get(4).setOrientation("hor");
        placeShipOnMap(17,8,4);
        placeShipOnHitmap(17,8,4);

        gameModel.getMap().displayMap();
        gameModel.getMap().displayHitMap();
        //draw map in GUI
        gameView.drawMap(26);
    }

    //generate custom map
    @Override
    public boolean generateMap() {
        boolean validMap = false;
        String dim = gameView.getMapDimTxt().getText();

        try{
            if( Integer.parseInt(dim) >= 10 && Integer.parseInt(dim) < 27){
                //insert exception if cant parse
                gameModel.setMap(new Map(Integer.parseInt(dim)));
                gameModel.getMap().displayMap();
                gameModel.getMap().displayHitMap();
                //draw map in GUI
                gameView.drawMap(Integer.parseInt(dim));
                return validMap = true;
            }
            else{
                JOptionPane.showMessageDialog(null,"invalid Map Dimension!","MAP-ERROR", JOptionPane.ERROR_MESSAGE);
                validMap = false;
                return validMap;
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null,"invalid Map input!","MAP-ERROR", JOptionPane.ERROR_MESSAGE);
            validMap = false;
            return  validMap;
        }


    }

    //get string of specific ship
    public String getShiptxt(){
        String boat;
        boat = gameModel.getShips().get(gameView.getCounter()).toString();
        return boat;
    }

    //method to place ships on maps
    public void placeShips(){

            int counter = gameView.getCounter();

            gameModel.getShips().get(counter).setOrientation(gameView.getShipOri());

            int x_cord = gameView.getX_Cord();
            int y_cord = gameView.getY_Cord();


            if(checkBoatPosition(x_cord,y_cord,gameModel.getShips().get(counter).getOrientation(),counter)){
                placeShipOnMap(x_cord,y_cord,counter);
                placeShipOnHitmap(x_cord,y_cord,counter);
                drawShipPlacement();
                gameView.setCounter(gameView.getCounter()+1);
            }
            else {
                JOptionPane.showMessageDialog(null,"invalid position for ship!","shipPosition-ERROR!", JOptionPane.ERROR_MESSAGE);
            }

            gameModel.getMap().displayMap();
            gameModel.getMap().displayHitMap();


            if(gameView.getCounter() < 5){
                gameView.getShipLbl().setText("input cordinates to place: " + gameModel.getShips().get(gameView.getCounter()));
            }

            if(gameView.getCounter() == 5){
                gameView.hidePlaceShipMenu();
                gameView.setCounter(0);
                JOptionPane.showMessageDialog(null,"Choose cordinates and shoot!","game has started",JOptionPane.INFORMATION_MESSAGE);
                hideShips();
                gameView.showShootmenu();
            }

        //iterate every ship in array
        //for everyship. ask cordinates
        //check if cordinates are valid and place boat on position

    }

    //place ship in object map
    public void placeShipOnMap(int x,int y,int ship){
        gameModel.getMap().getMap()[y][x] = gameModel.getShips().get(ship);
    }

    //place ship in hitmap
    public void placeShipOnHitmap(int x,int y,int ship){

        //check if vertical or horizontal
        System.out.println(gameModel.getShips().get(ship).getOrientation());
        //horizontal placement
        if(Objects.equals(gameModel.getShips().get(ship).getOrientation(), "hor")){
            for(int i = 0; i < gameModel.getShips().get(ship).getLength();i++){ //have get(i)
                gameModel.getMap().getHitmap()[y][x+i] = String.valueOf(gameModel.getMap().getBoatRepresentation());
            }
        }

        //vertical placement
        else if(Objects.equals(gameModel.getShips().get(ship).getOrientation(), "ver")){
            for(int i = 0; i < gameModel.getShips().get(ship).getLength();i++){ //have get(i)
                gameModel.getMap().getHitmap()[y+i][x] = String.valueOf(gameModel.getMap().getBoatRepresentation());
            }
        }

    }

    //method used to shoot ships
    public void shootShip(){

        int x_cord = gameView.getX_Cord();
        int y_cord = gameView.getY_Cord();

        checkShotPosition(y_cord,x_cord);
        checkSunkenShip();
        checkGameOver();
        if (checkGameOver()){
            gameView.hideShootmenu();
            JOptionPane.showMessageDialog(null,"GameOver","YouWon!",JOptionPane.INFORMATION_MESSAGE);
            gameView.getMapPanel().setVisible(false);
            calculatePlayerScore();
            gameView.showLeaderboard();

        }
        gameModel.getMap().displayMap();
        gameModel.getMap().displayHitMap();
    }

    //check if shot is duplicate and updates GUI when boat shot
    public void checkShotPosition(int y, int x){

        if(gameModel.getMap().getHitmap()[y][x] == null){
            gameModel.getMap().getHitmap()[y][x] = gameModel.getMap().getMissRepresentation();
            gameView.getButtons()[y][x].setBackground(Color.decode("#0077be"));

            gameModel.getPlayer().setMissedShots(gameModel.getPlayer().getMissedShots()+1);
            //gameModel.getPlayers()[gameModel.getPlayerAmount()].setMissedShots(gameModel.getPlayers()[gameModel.getPlayerAmount()].getMissedShots()+1);
        }
        else if ( Objects.equals(gameModel.getMap().getHitmap()[y][x], gameModel.getMap().getMissRepresentation()) || Objects.equals(gameModel.getMap().getHitmap()[y][x], gameModel.getMap().getHitRepresentation()) || Objects.equals(gameModel.getMap().getHitmap()[y][x], gameModel.getMap().getSunkenRepresentation()) ){

            //repeat option to shoot if position contains a missed shot
            //System.out.println("position has already been shot!");
            JOptionPane.showMessageDialog(null,"Position has already been shot","shootPosition-ERROR!", JOptionPane.ERROR_MESSAGE);
            //shootShip();

        }
        else{
            //shot hits a ship
            //iterate objectmap to find object
            //change tile on hit cordinate
            //check if any boats have health left or if game is over
            String ori;
            int len;

            for(int row = 0; row < gameModel.getMap().getMap().length;row++){
                for(int col = 0; col < gameModel.getMap().getMap()[row].length;col++){

                    if(gameModel.getMap().getMap()[row][col] instanceof Ship){

                        //System.out.println(((Ship) gameModel.getMap().getMap()[row][col]).getOrientation());
                        //int boatloc_x;
                        //int boatloc_y;

                        ori = ((Ship) gameModel.getMap().getMap()[row][col]).getOrientation();
                        len = ((Ship) gameModel.getMap().getMap()[row][col]).getLength();

                        //System.out.println(ori);

                        if(Objects.equals(ori, "hor")){

                            for(int i = 0; i < len;i++){
                                if(row == y && col+i == x){

                                    gameModel.getMap().getHitmap()[row][col+i] = gameModel.getMap().getHitRepresentation();
                                    gameView.getButtons()[row][col+i].setBackground(Color.decode("#990000"));

                                    ((Ship) gameModel.getMap().getMap()[row][col]).setHealth(((Ship) gameModel.getMap().getMap()[row][col]).getHealth()-1);
                                    gameModel.getPlayer().setHits(gameModel.getPlayer().getHits()+1);
                                }
                            }
                        }
                        if(Objects.equals(ori, "ver")){

                            for(int j = 0; j < len;j++){
                                //System.out.println((row+j) + "," + y + " " + col + "," + x); debugging
                                if(row+j == y && col == x){

                                    //System.out.println("inside"); debugging
                                    gameModel.getMap().getHitmap()[row+j][col] = gameModel.getMap().getHitRepresentation();
                                    gameView.getButtons()[row+j][col].setBackground(Color.decode("#990000"));

                                    ((Ship) gameModel.getMap().getMap()[row][col]).setHealth(((Ship) gameModel.getMap().getMap()[row][col]).getHealth()-1);
                                    gameModel.getPlayer().setHits(gameModel.getPlayer().getHits()+1);
                                }
                            }
                        }

                    }

                }
            }

        }
    }

    //summarize the health of all ships on map.
    //if health = 0, game is over
    public boolean checkGameOver(){
        //System.out.println("checked game over"); debugging
        boolean gameOver = false;
        int healthSum = 0;
        for( int row = 0; row < gameModel.getMap().getMap().length; row++ ){
            for(int col = 0; col < gameModel.getMap().getMap()[row].length; col++){

                if( gameModel.getMap().getMap()[row][col] instanceof Ship ){
                    healthSum += ((Ship) gameModel.getMap().getMap()[row][col]).getHealth();
                }

            }
        }

        if(healthSum == 0){
            gameOver = true;
            //System.out.println("GameOver!"); debugging
        }

        return gameOver;
    }

    //shows ship when placing on custom map
    public void drawShipPlacement(){
        for(int row = 0; row < gameModel.getMap().getHitmap().length; row ++) {
            for (int col = 0; col < gameModel.getMap().getHitmap()[row].length; col++) {

                if(Objects.equals(gameModel.getMap().getHitmap()[row][col], gameModel.getMap().getBoatRepresentation())){
                    gameView.getButtons()[row][col].setBackground(Color.decode("#FFDF4F"));
                }

            }
        }

    }
    //hides drawn ships
    public void hideShips(){
        for(int row = 0; row < gameView.getButtons().length; row ++) {
            for (int col = 0; col < gameView.getButtons()[row].length; col++) {
                gameView.getButtons()[row][col].setBackground(Color.WHITE);
            }
        }
    }

    //check if boat position is valid
    @Override
    public boolean checkBoatPosition(int x, int y,String orientation, int ship) {

        boolean inBounds = true;
        boolean spotEmpty = true;
        boolean doesNotOverlap = true;

        boolean validPos = true;


        //check bounds
        try{

            System.out.println(gameModel.getMap().getMap()[y][x]);

        } catch (Exception e) {
            inBounds = false;
            System.out.println("Ship not in bounds(map)");
        }

        try {

            if(Objects.equals(gameModel.getShips().get(ship).getOrientation(), "hor")){
                for(int i = 0; i < gameModel.getShips().get(ship).getLength();i++){
                    System.out.println(gameModel.getMap().getHitmap()[y][x+i]);
                }
            }

            else if(Objects.equals(gameModel.getShips().get(ship).getOrientation(), "ver")){
                for(int i = 0; i < gameModel.getShips().get(ship).getLength();i++){
                    System.out.println(gameModel.getMap().getHitmap()[y+i][x]);
                }
            }


        } catch (Exception e) {
            inBounds = false;
            System.out.println("Ship not in bounds(hitmap)");
        }

        //use if else for overlapp because not an exception
        if(inBounds){
            if(gameModel.getMap().getMap()[y][x] == null){
                spotEmpty= true;
            }
            else{
                spotEmpty = false;
                System.out.println("spot is not empty");
            }
        }

        if(inBounds && spotEmpty){

            if(Objects.equals(gameModel.getShips().get(ship).getOrientation(), "hor")){
                for(int i = 0; i < gameModel.getShips().get(ship).getLength();i++){
                    if( gameModel.getMap().getHitmap()[y][x+i] != null ){
                        doesNotOverlap = false;
                        System.out.println("shipplacement overlaps");
                        break;
                    }
                }
            }

            else if(Objects.equals(gameModel.getShips().get(ship).getOrientation(), "ver")){
                for(int i = 0; i < gameModel.getShips().get(ship).getLength();i++){

                    if( gameModel.getMap().getHitmap()[y+i][x] != null ){
                        doesNotOverlap = false;
                        System.out.println("shipplacement overlaps");
                        break;
                    }

                }
            }


        }

        if (inBounds && spotEmpty && doesNotOverlap){
            validPos = true;
        }
        else {
            validPos = false;
        }

        return validPos;
    }

}
