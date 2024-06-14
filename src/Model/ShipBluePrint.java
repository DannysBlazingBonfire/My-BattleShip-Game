/*
• Danny Gazic Hallberg
• am2931
• Systemutveckling DA339A
 */

package Model;
import java.util.HashMap;
import static java.util.Map.entry;

//abstract class used to set abstract blueprint of ship object and assign length depending on type
public abstract class ShipBluePrint {

    HashMap<String, Integer> boattypeslength = assignlength();

    //assigns a length depending on ship type. values stored in hashmap
    public HashMap assignlength(){

        HashMap<String, Integer> nyhash = new HashMap<String, Integer>();

        nyhash.put("u-boat", 1);
        nyhash.put("torpedo", 2);
        nyhash.put("hunter", 3);
        nyhash.put("cruiser", 4);
        nyhash.put("battleship", 5);

        return nyhash;
    }

    public HashMap<String, Integer> getBoattypeslength() {
        return boattypeslength;
    }

    public void setBoattypeslength(HashMap<String, Integer> boattypeslength) {
        this.boattypeslength = boattypeslength;
    }

    /*
  u-båtar, en ruta stort
• torpedbåtar, 2 rutor stort
• jagare, 3 rutor stort
• kryssare, 4 rutor stort
• slagskepp, 5 rutor stort
     */
}
