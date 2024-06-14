/*
• Danny Gazic Hallberg
• am2931
• Systemutveckling DA339A
 */

package Model;

import java.util.HashMap;

//ship class with ship attributes. extends shipblueprint
public class Ship extends ShipBluePrint{

    private int length;
    private String type;
    private int health;

    private String orientation; //vertical/horizontal

    public Ship(){
        assignlength();
        this.length = 1;
        this.health = this.length;
        this.type = "u-boat";
        this.orientation = "hor";
    }

    //ship constructor
    public Ship(String type){
        assignlength();
        this.type = type;
        this.orientation = "hor";
        this.length = getBoattypeslength().get(this.type); //gets types length from hashmap
        this.health = this.length;
    }

    @Override
    public HashMap assignlength() {
        return super.assignlength();
    }

    //debug method
    public void printKeys(){
        for (String i : getBoattypeslength().keySet()) {
            System.out.println(i);
        }
    }

    //get ship length depending on hashmap in blueprint
    @Override
    public HashMap<String, Integer> getBoattypeslength() {
        return super.getBoattypeslength();
    }

    @Override
    public void setBoattypeslength(HashMap<String, Integer> boattypeslength) {
        super.setBoattypeslength(boattypeslength);
    }

    //getters-setters
    public int getLength() {
        return length;
    }

    public String getOrientation() {
        return orientation;
    }

    public String getType() {
        return type;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    //to-string method
    @Override
    public String toString() {
        String outText = String.format("type: " + "%s" + " healthpoints: " + "%s",type,health);
        return outText;
    }
}
