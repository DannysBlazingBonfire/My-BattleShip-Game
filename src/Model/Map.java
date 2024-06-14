/*
• Danny Gazic Hallberg
• am2931
• Systemutveckling DA339A
 */

package Model;

import java.util.Arrays;

//map attributes
public class Map {
    private int height;
    private int width;

    private Object[][] map;
    private String[][] hitmap;
    private String hitRepresentation;
    private String sunkenRepresentation;
    private String missRepresentation;
    private String boatRepresentation;

    private int mapDimension;

    //map constructor
    public Map(){
        this.hitmap = new String[10][10];
        this.map = new Object[10][10];

        this.hitRepresentation = "-XX-";
        this.sunkenRepresentation = "-SS-";
        this.missRepresentation = "-MM-";
        this.boatRepresentation = "-OO-";
    }

    //map constructor with dimension input
    public Map(int mapDimension){

        this.hitmap = new String[mapDimension][mapDimension];
        this.map = new Object[mapDimension][mapDimension];

        this.hitRepresentation = "-XX-";
        this.sunkenRepresentation = "-SS-";
        this.missRepresentation = "-MM-";
        this.boatRepresentation = "-OO-";

        //displayMap();
        //displayHitMap();

    }

    //method displaying hitmap
    public void displayHitMap(){
        for(int row = 0; row < hitmap.length; row++){
            for(int col = 0; col < hitmap[row].length; col++){
                System.out.print("[" +hitmap[row][col]+ "]" + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------");
    }

    //method displaying hitmap
    public void displayMap(){
        for(int row = 0; row < map.length; row++){
            for(int col = 0; col < map[row].length; col++){
                System.out.print("[" +map[row][col]+ "]" + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------");
    }

    //getters-setters
    public int getHeight() {
        return height;
    }

    public int getMapDimension() {
        return mapDimension;
    }

    public int getWidth() {
        return width;
    }

    public Object[][] getMap() {
        return map;
    }

    public String[][] getHitmap() {
        return hitmap;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHitmap(String[][] hitmap) {
        this.hitmap = hitmap;
    }

    public void setMap(Object[][] map) {
        this.map = map;
    }

    public void setMapDimension(int mapDimension) {
        this.mapDimension = mapDimension;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setMissRepresentation(String missRepresentation) {
        this.missRepresentation = missRepresentation;
    }

    public void setBoatRepresentation(String boatRepresentation) {
        this.boatRepresentation = boatRepresentation;
    }

    public void setSunkenRepresentation(String sunkenRepresentation) {
        this.sunkenRepresentation = sunkenRepresentation;
    }

    public void setHitRepresentation(String hitRepresentation) {
        this.hitRepresentation = hitRepresentation;
    }

    public String getBoatRepresentation() {
        return boatRepresentation;
    }

    public String getHitRepresentation() {
        return hitRepresentation;
    }

    public String getMissRepresentation() {
        return missRepresentation;
    }

    public String getSunkenRepresentation() {
        return sunkenRepresentation;
    }

}
