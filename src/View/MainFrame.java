/*
• Danny Gazic Hallberg
• am2931
• Systemutveckling DA339A
 */

package View;

import Controller.GameLogic;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainFrame extends JFrame implements ActionListener{

    //components used in GUI
    private JPanel panel;
    private JFrame frame;
    private GameLogic gameLogic;

    private int height;
    private int width;

    private int Y_Cord;
    private int X_Cord;
    private int counter;

    private JButton[][] buttons;
    private JToggleButton oriBtn;

    private JPanel mapPanel;

    private JLabel nameLbl;
    private JLabel mapDim;
    private JLabel shipLbl;
    private JLabel oriLbl;

    private JTextField nameTxt;
    private JTextField mapDimTxt;
    private JTextArea highScoreTxt;

    private JButton nameBtn;
    private JButton confirmDimensionBtn;
    private JButton placeShip;
    private JButton shootBtn;
    private JButton playAgainBtn;
    private JButton genMapOneBtn;
    private JButton customMapBtn;
    private JButton genHardMapBtn;

    private String shipOri;

    //MainFrame constructor
    public MainFrame(GameLogic gameLogic, int height, int width) {

        this.gameLogic = gameLogic;

        this.height = height;
        this.width = width;
        this.Y_Cord = 0;
        this.X_Cord = 0;
        this.counter = 0;

        buttons = new JButton[1][1];

        mapPanel = new JPanel();
        mapPanel.setBackground(Color.GRAY);
        mapPanel.setLayout(new GridLayout(26,26,5,5));
        mapPanel.setBounds(100,600,this.width-150,this.height-150);
        mapPanel.setLocation(50,50);
        mapPanel.setVisible(false);

        panel = new JPanel(); //instance of panel
        frame = new JFrame(); // instance of frame
        frame.setTitle("Battleship"); //frame settings
        frame.setSize(this.width, this.height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(true); //cant resize panel
        panel.setLayout(null);
        frame.add(panel); //add panel to frame

        shipLbl = new JLabel("input cordinates to place: ");
        shipLbl.setBounds(100,25,500,25);
        shipLbl.setLocation(100,this.height-90);
        shipLbl.setVisible(false);

        oriLbl = new JLabel("Orientation: " + shipOri);
        oriLbl.setBounds(100,25,150,25);
        oriLbl.setLocation(600,this.height-90);
        oriLbl.setVisible(false);

        nameLbl = new JLabel("Input username with format [ABC]");
        nameLbl.setBounds(100,100,200,25);
        nameLbl.setLocation(800,150);
        nameLbl.setVisible(true);

        mapDim = new JLabel("input map dimensions (min 10, max 26)");
        mapDim.setBounds(485,175,225,25);
        mapDim.setLocation(800,150);
        mapDim.setVisible(false);

        mapDimTxt = new JTextField("Dimension");
        mapDimTxt.setBounds(550,205,70,25);
        mapDimTxt.setLocation(860,180);
        mapDimTxt.setEditable(true);
        mapDimTxt.setVisible(false);

        nameTxt = new JTextField("name");
        nameTxt.setBounds(0,0,50,25);
        nameTxt.setLocation(880,180);
        nameTxt.setEditable(true);
        nameTxt.setVisible(true);

        highScoreTxt = new JTextArea("highscores");
        highScoreTxt.setBounds(0,0,500,500);
        highScoreTxt.setBackground(Color.lightGray);
        highScoreTxt.setLocation(640,80);
        highScoreTxt.setEditable(false);
        highScoreTxt.setVisible(false);

        nameBtn = new JButton("confirm");
        nameBtn.setBounds(0,0,100,25);
        nameBtn.setLocation(855,220);
        nameBtn.setVisible(true);

        customMapBtn = new JButton("Custom map");
        customMapBtn.setBounds(0,0,120,25);
        customMapBtn.setLocation(845,250);
        customMapBtn.setVisible(false);

        genHardMapBtn = new JButton("map: hard");
        genHardMapBtn.setBounds(0,0,120,25);
        genHardMapBtn.setLocation(990,250);
        genHardMapBtn.setVisible(false);

        placeShip = new JButton("Place Ship");
        placeShip.setBounds(0,0,120,25);
        placeShip.setLocation(720,this.height-90);
        placeShip.setVisible(false);

        confirmDimensionBtn = new JButton("confirm");
        confirmDimensionBtn.setBounds(545,245,80,25);
        confirmDimensionBtn.setLocation(855,220);
        confirmDimensionBtn.setVisible(false);

        shootBtn = new JButton("Shoot!");
        shootBtn.setBounds(0,0,120,25);
        shootBtn.setLocation(850,this.height-90);
        shootBtn.setVisible(false);

        genMapOneBtn = new JButton("map: easy");
        genMapOneBtn.setBounds(0,0,120,25);
        genMapOneBtn.setLocation(700,250);
        genMapOneBtn.setVisible(false);

        playAgainBtn = new JButton("PlayAgain");
        playAgainBtn.setBounds(0,0,120,25);
        playAgainBtn.setLocation(835,this.height-490);
        playAgainBtn.setVisible(false);

        oriBtn = new JToggleButton("Toggle Orientation");
        oriBtn.setSize(140,25);
        oriBtn.setLocation(450,this.height-90);
        oriBtn.setVisible(false);

        shipOri = "hor";

        panel.add(nameLbl);
        panel.add(shipLbl);
        panel.add(oriLbl);
        panel.add(nameTxt);
        panel.add(highScoreTxt);
        panel.add(nameBtn);
        panel.add(shootBtn);
        panel.add(playAgainBtn);
        panel.add(genMapOneBtn);
        panel.add(customMapBtn);
        panel.add(genHardMapBtn);
        panel.add(placeShip);
        panel.add(oriBtn);
        panel.add(mapDim);
        panel.add(mapDimTxt);
        panel.add(confirmDimensionBtn);
        panel.add(mapPanel);

        //itemlistener for togglebutton
        oriBtn.addItemListener(f -> {
            if (oriBtn.isSelected()) {
                shipOri = "ver";
                oriLbl.setText("Orientation: " + shipOri);
                System.out.println(shipOri);
            }
            else{
                shipOri = "hor";
                oriLbl.setText("Orientation: " + shipOri);
                System.out.println(shipOri);
            }

        });

        //action listeners
        nameBtn.addActionListener(this);
        confirmDimensionBtn.addActionListener(this);
        placeShip.addActionListener(this);
        shootBtn.addActionListener(this);
        playAgainBtn.addActionListener(this);
        genMapOneBtn.addActionListener(this);
        customMapBtn.addActionListener(this);
        genHardMapBtn.addActionListener(this);

        pack();
        frame.setVisible(true);
    }

    //action listeners for buttons
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == nameBtn){
            gameLogic.initializePlayer();
        }
        else if(e.getSource() == confirmDimensionBtn){
            boolean valid = gameLogic.generateMap();
            if(valid){
                mapDimTxt.setText("Dimension");
                //gameLogic.placeShips();
                shipLbl.setText("input cordinates to place: " + gameLogic.getShiptxt());
                hideGenerateMapMenu();
                mapPanel.setVisible(true);
                showPlaceShipMenu();
            }

        }
        else if (e.getSource() == placeShip){
                gameLogic.placeShips();
                System.out.println(counter);
        }
        else if(e.getSource() == shootBtn){
            gameLogic.shootShip();
        }
        else if(e.getSource() == playAgainBtn){
            hideLeaderboard();
            showNameMenu();
        }
        else if(e.getSource() == genMapOneBtn){
            gameLogic.generatePresetMapOne();
            hideMapChoice();
            mapPanel.setVisible(true);
            showShootmenu();
        }
        else if(e.getSource() == customMapBtn){
            hideMapChoice();
            showGenerateMapMenu();
        }
        else if(e.getSource() == genHardMapBtn){
            gameLogic.generatePresetMapTwo();
            hideMapChoice();
            mapPanel.setVisible(true);
            showShootmenu();
        }

        //gets button cordinates when clicked
        for(int row = 0; row < buttons.length; row++){
            for (int col = 0; col < buttons[row].length; col++){
                if (e.getSource() == buttons[row][col]){
                    System.out.println( row + "," + col );
                    this.Y_Cord = row;
                    this.X_Cord = col;
                }
            }
        }



    }

    //getters-setters
    public JButton getNameBtn() {
        return nameBtn;
    }

    public JLabel getNameLbl() {
        return nameLbl;
    }

    public int getY_Cord() {
        return Y_Cord;
    }

    public int getX_Cord() {
        return X_Cord;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public JTextField getNameTxt() {
        return nameTxt;
    }

    public JPanel getMapPanel() {
        return mapPanel;
    }

    public JTextArea getHighScoreTxt() {
        return highScoreTxt;
    }

    public void setHighScoreTxt(JTextArea highScoreTxt) {
        this.highScoreTxt = highScoreTxt;
    }

    public void setMapPanel(JPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    public JLabel getMapDim() {
        return mapDim;
    }

    public JTextField getMapDimTxt() {
        return mapDimTxt;
    }

    public JLabel getShipLbl() {
        return shipLbl;
    }

    public JToggleButton getOriBtn() {
        return oriBtn;
    }

    public void setShipLbl(JLabel shipLbl) {
        this.shipLbl = shipLbl;
    }

    public String getShipOri() {
        return shipOri;
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[][] buttons) {
        this.buttons = buttons;
    }

    //methods hiding and showing menus
    public void showMapChoice(){
        genMapOneBtn.setVisible(true);
        customMapBtn.setVisible(true);
        genHardMapBtn.setVisible(true);
    }

    public void hideMapChoice(){
        genMapOneBtn.setVisible(false);
        customMapBtn.setVisible(false);
        genHardMapBtn.setVisible(false);
    }

    public void hideNameMenu(){
        nameLbl.setVisible(false);
        nameTxt.setVisible(false);
        nameBtn.setVisible(false);
    }

    public void showNameMenu(){
        nameLbl.setVisible(true);
        nameTxt.setVisible(true);
        nameBtn.setVisible(true);
    }

    public void showGenerateMapMenu(){
        mapDim.setVisible(true);
        mapDimTxt.setVisible(true);
        confirmDimensionBtn.setVisible(true);
    }

    public void hideGenerateMapMenu(){
        mapDim.setVisible(false);
        mapDimTxt.setVisible(false);
        confirmDimensionBtn.setVisible(false);
    }

    public void showPlaceShipMenu(){
        shipLbl.setVisible(true);
        oriBtn.setVisible(true);
        oriLbl.setVisible(true);
        placeShip.setVisible(true);
    }

    public void showLeaderboard(){
        highScoreTxt.setVisible(true);
        playAgainBtn.setVisible(true);
    }

    public void hideLeaderboard(){
        highScoreTxt.setVisible(false);
        playAgainBtn.setVisible(false);
    }

    public void showShootmenu(){
        shootBtn.setVisible(true);
    }

    public void hideShootmenu(){
        shootBtn.setVisible(false);
    }

    public void hidePlaceShipMenu(){
        shipLbl.setVisible(false);
        oriBtn.setVisible(false);
        oriLbl.setVisible(false);
        placeShip.setVisible(false);
    }

    //Method to draw up main map
    public void drawMap(int dimension){
        mapPanel.removeAll();
        mapPanel.setLayout(new GridLayout(dimension,dimension,5,5));

        //mapPanel.setLayout(new GridLayout(dimension,dimension,10,10));
        JButton maptile;
        char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        buttons = new JButton[dimension][dimension];

        for(int row = 0; row < dimension; row ++) {
            for (int col = 0; col < dimension; col++) {

                maptile = new JButton(letters[col] + "," + (row+1) );
                maptile.setBackground(Color.WHITE);
                maptile.addActionListener(this);

                buttons[row][col] = maptile;
                mapPanel.add(maptile);
            }
        }
        oriLbl.setText("Orientation: " + shipOri); //sets the shiporientation in orientation label
    }
}
