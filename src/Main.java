/**
 * The Little Wizard Game File
 * @author Grace Bero
 * Switching Scene method influenced by https://www.youtube.com/watch?v=SB9AnciLmsw by javacodejunkie
 */


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView; 

public class Main extends Application {

    //Spells
    private Spell ult = new Spell("Fireball", 50, 6, "f"); //Ult, does 50 damage, costs 6 stamina
    private Spell basic = new Spell("Spark", 25, 3, "s"); //Basic, does 25 damage, costs 3 stamina
    private Spell heal = new Spell("Heal", 20, 2, "h"); //Heal, heals 20 health, costs 2 stamina
    private Spell sleep = new Spell("Sleep", 0, 6, "z"); //Sleep, puts the enemy to sleep for 1 turn, costs 6 stamina
    private Spell physical = new Spell("Physical Attack", 10, 0, "p"); //Physical Attack, does 10 damage, costs 0 stamina
    
    //People
    private Wizard player = new Wizard("TBD"); //Creates the player
    private Boss bbeg = new Boss("Vlad"); //Creates the boss
    private Goblin minion = new Goblin("Blinky"); //Creates the goblin

    //Images
    private Image wizard_pic = new Image("wizard_picture.jpg"); //Creates the wizard image
    private ImageView wizardStory = new ImageView(wizard_pic); //creates the image view for in story
    private ImageView wizardGCombat = new ImageView(wizard_pic); //creates the image view for goblin combat
    private ImageView wizardCombatV = new ImageView(wizard_pic); //creates the image view for boss combat

    private Image spark_pic = new Image("spark_picture.jpg"); //Creates the spark image
    private ImageView spark = new ImageView(spark_pic); //creates the image view for spark

    private Image heal_pic = new Image("heal_picture.jpg"); //Creates the heal image
    private ImageView heal_img = new ImageView(heal_pic); //creates the image view for heal

    private Image goblin_pic = new Image("goblin_picture.jpg"); //Creates the goblin image
    private ImageView goblinStory = new ImageView(goblin_pic); //Goblin image for story
    private ImageView goblinCombat = new ImageView(goblin_pic); //Goblin image for combat
    
    private Image fireball_pic = new Image("fireball_picture.jpg"); //Creates the fireball image
    private ImageView fireball = new ImageView(fireball_pic); //creates the image view for fireball

    private Image sleep_pic = new Image("sleep_picture.png"); //Creates the sleep image
    private ImageView zzz = new ImageView(sleep_pic); //creates the image view for sleep

    private Image boss_pic = new Image("vlad_picture.jpg"); //Creates the boss image
    private ImageView vladStory = new ImageView(boss_pic);  //Boss image for story
    private ImageView vladCombat = new ImageView(boss_pic); //Boss image for combat

    private Image nurse_pic = new Image("nurse_picture.jpg"); //creates the nurse image
    private ImageView nurse = new ImageView(nurse_pic); //creates the image view for nurse

    private Image headmaster_pic = new Image("headmaster_picture.jpg"); //creates the headmaster image
    private ImageView headmaster = new ImageView(headmaster_pic); //creates the image view for headmaster

    private Image game_over = new Image("GameOver.jpg");
    private ImageView storyGameOver = new ImageView(game_over); //Game over image for story
    private ImageView goblinGameOver = new ImageView(game_over); //Game over image for Goblin Combat
    private ImageView bossGameOver = new ImageView(game_over); //Game over image for Boss Combat

    private Image vlad_lair = new Image("vlad_lair.jpg"); //creates the image for vlads lair
    private ImageView vladLair = new ImageView(vlad_lair); //Boss Lair image for story

    private Image spell_book = new Image("spell_book.jpg"); //creates the image for the spell book
    private ImageView spellBook = new ImageView(spell_book); //Spell book image for story

    private Image happy_ending = new Image("happy_ending.jpg"); //creates the image for the happy ending
    private ImageView happyEnding = new ImageView(happy_ending); //Happy ending image for story

    //storyScene Variables
    private Label storyLabel; //story text that will change as the story progresses
    private Button nextButton; //button that when clicked will change the story progress
    private Button yesButton; //button that when pressed will continue the story, will be used for "yes I accept quest"
    private Button noButton; //button that when pressed will end the story, will be used for "no I do not accept quest"
    private HBox yesNoHbox; //HBox that will hold the yes and no buttons
    private HBox storyHbox; //HBox that will hold the images
    private VBox textVbox; //vbox that will hold the storyLable and nextButton
    private VBox storyVbox; //vbox that will hold the textVbox and storyHbox
    private Button joinButton; //button that when pressed will end the story, will be used for "join vlad"
    private Button refuseButton; //button that when pressed will continue the story, will be used for "refuse vlad"
    private HBox joinHbox; //HBox that will hold the join and refuse buttons
    private int counter = 0; //counter that will keep track of the, yes no path
     private int storyIndex = 0; //Keeps track of the story progress

    /**
     * Creates the stage for the game to take place in the GUI
     * @stage primary stage where the game will take place
     */
    //Primary Stage variables
    private Stage stage; //Creates the stage
    private Scene startScene; //Creates the start scene, title scene
    private Scene storyScene; //Creates the story scene, where all the story will take place
    private Scene goblinScene; //Creates the goblin scene, where the goblin combat will take place
    private Scene bossScene; //Creates the boss scene, where the boss combat will take place

    //goblinScene Variables
    ////Adding a g to the end of the variable name to differentiate from the boss scene variables
    private Label GoblinCombatText; //says what occurs during the turn of combat
    private Button physicalButtonG; //button that when pressed will do a physical attack
    private Button basicButtonG; //button that when pressed will do a basic attack
    private Button ultButtonG; //button that when pressed will do an ult attack
    private Button continueButtonG; //button that when pressed will switch to the storyScene when the goblin is defeated
    private HBox actionHboxG; //HBox that will hold the action buttons (physical, basic, ult, continue)
    private Label playerStatsG; //label that will display the player's stats
    private HBox playerGHbox; //HBox that will hold the playerStatsG and the player image
    private Label goblinStats; //label that will display the goblin's stats
    private HBox goblinGHbox; //HBox that will hold the goblinStats and the goblin image
    private HBox statsHboxG; //HBox that will hold the playerGHbox and the goblinGHbox
    private VBox goblinVbox; //VBox that will hold the GoblinCombatText, statsHboxG, and actionHboxG

    //bossScene Variables
    ////Adding a v to the end of the variable name to differentiate from the goblin scene variables
    private Label vladCombatText; //says what occurs during the turn of combat 
    private Button physicalButtonV; //button that when pressed will do a physical attack
    private Button basicButtonV; //button that when pressed will do a basic attack
    private Button ultButtonV; //button that when pressed will do an ult attack
    private Button healButtonV; //button that when pressed will heal the player
    private Button sleepButtonV; //button that when pressed will put the boss to sleep
    private Button continueButtonV; //button that when pressed will switch to the storyScene when the boss is defeated
    private HBox actionHboxV; //HBox that will hold the action buttons (physical, basic, ult, heal, sleep, continue)
    private Label playerStatsV; //label that will display the player's stats
    private HBox playerHboxV; //HBox that will hold the playerStatsV and the player image
    private Label vladStats; //label that will display the boss's stats
    private HBox vladHbox; //HBox that will hold the vladStats and the boss image
    private HBox statsHboxV; //HBox that will hold the playerHboxV and the vladHbox
    private VBox vladVbox; //VBox that will hold the vladCombatText, statsHboxV, and actionHboxV   

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Creates the start screen
        stage = primaryStage; //Sets the stage
        stage.setTitle("The Little Wizard"); //Sets the title of the stage

        //Creates the scenes
        startScene = createStartScene(); //creates the start scene
        storyScene = createStoryScene(); //creates the story scene
        goblinScene = createGoblinScene(); //creates the goblin scene
        bossScene = createBossScene(); //creates the boss scene

        stage.setScene(startScene); //Sets the start scene as the primary scene

        stage.show(); //Shows the stage
    }

     /**
     * Creates the start scene
     * @return the start scene
     */
    //Variables
    private Text titleText; //Creates the title text
    private Button button; //Creates the start button that will switch to the story scene
    private VBox StartVbox; //Creates the VBox that will hold the title and the button


    public Scene createStartScene() {
        //Creates the title text
        titleText = new Text("The Little Wizard"); //creates the title text
        titleText.setFont(Font.font("Lucida Calligraphy", 30)); //sets font and size of titleText
        
        //Creates the start button
        button = new Button("Start"); //creates the start button
        button.setOnAction(e -> switchScenes(storyScene));  // Switch Scenes to the story scene

        //Creates the VBox that will hold the title and the button
        StartVbox = new VBox(10, titleText, button); //creates the VBox that will hold the title and the button
        StartVbox.setAlignment(Pos.CENTER); // Centers the VBox

        //Creates the scene
        startScene = new Scene(StartVbox, 1000, 700); //creates the scene

        return startScene;
    }

    /**
     * Creates the story scene
     * @return storyScene
     */

    public Scene createStoryScene() {
        //Creates the story label that will change as the story progresses
        storyLabel = new Label("Welcome to the game! You are a young wizard who has just been accepted into wizard academy."); //creates the story label
        storyLabel.setFont(Font.font("Lucina Calligraphy", 14)); //sets font and size of storyLabel
        
        //Creates the next button that will change the story progress
        nextButton = new Button("Next"); //creates the next button
        nextButton.setOnAction(new NextButtonHandler()); //sets the next button to the NextButtonHandler class

        //Creates the yes and no buttons and the button HBox
        yesButton = new Button("Yes"); //creates the yes button
        yesButton.setOnAction(new NextButtonHandler()); //sets the yes button to the NextButtonHandler class
        noButton = new Button("No"); //creates the no button
        noButton.setOnAction(new NoButtonHandler()); //sets the no button to the NoButtonHandler class
        yesNoHbox = new HBox(10, yesButton, noButton); //creates the HBox that will hold the yes and no buttons
        yesNoHbox.setAlignment(Pos.CENTER); // Centers the HBox

        //Creates the join me button
        joinButton = new Button("Agree"); //creates the join me button, ends the story
        joinButton.setOnAction(new joinButtonHandler()); //sets the join me button to the joinButtonHandler class
        refuseButton = new Button("Refuse"); //creates the refuse button, continues the story
        refuseButton.setOnAction(new NextButtonHandler()); //sets the refuse button to the NextButtonHandler class
        joinHbox = new HBox(10, joinButton, refuseButton); //creates the HBox that will hold the join and refuse buttons
        joinHbox.setAlignment(Pos.CENTER); // Centers the HBox


        //Sets the images to the correct size
        wizardStory.setFitHeight(400);
        wizardStory.setFitWidth(200);

        goblinStory.setFitHeight(400);
        goblinStory.setFitWidth(200);

        nurse.setFitHeight(400);
        nurse.setFitWidth(200);

        headmaster.setFitHeight(400);
        headmaster.setFitWidth(200);

        vladStory.setFitHeight(400);
        vladStory.setFitWidth(200);

        spark.setFitHeight(150);
        spark.setFitWidth(150);

        fireball.setFitHeight(150);
        fireball.setFitWidth(150);

        zzz.setFitHeight(150);
        zzz.setFitWidth(150);

        heal_img.setFitHeight(150);
        heal_img.setFitWidth(150);

        storyGameOver.setFitHeight(500);
        storyGameOver.setFitWidth(600);

        vladLair.setFitHeight(500);
        vladLair.setFitWidth(600);

        spellBook.setFitHeight(150);
        spellBook.setFitWidth(150);

        happyEnding.setFitHeight(500);
        happyEnding.setFitWidth(600);

        storyHbox = new HBox(10, wizardStory); //creates the HBox that will hold the images
        storyHbox.setAlignment(Pos.CENTER); // Centers the HBox

        textVbox = new VBox(10, storyLabel, nextButton); //creates the VBox that will hold the storyLabel and nextButton
        textVbox.setAlignment(Pos.CENTER); // Centers the VBox
        
        storyVbox = new VBox(30, textVbox, storyHbox); //creates the VBox that will hold the textVbox and storyHbox
        storyVbox.setAlignment(Pos.TOP_CENTER); // Centers the VBox

        storyScene = new Scene(storyVbox, 1000, 700); //creates the scene
        
        return storyScene;
    }

    /**
     * NextButtonHandler class
     */

    class NextButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            storyIndex++;
            if (storyIndex == 1) { 
                storyLabel.setText("Lets go to your first class!\n You learned the spell Spark!");
                player.addSpell(basic); //Adds the spell to the player's spell list
                storyHbox.getChildren().add(spark); //Adds the image to the HBox
            }
            else if (storyIndex == 2) {
                storyLabel.setText("Your professor is so impressed with your abilities the he decides to teach you a more advanced spell \nYou learned Fireball!");
                player.addSpell(ult); //Adds the spell to the player's spell list
                storyHbox.getChildren().remove(spark); //Removes the image from the HBox
                storyHbox.getChildren().add(fireball); //Adds the image to the HBox

            }
            else if (storyIndex == 3) {
                storyLabel.setText("You thank you and decide to go to bed. Zzzzzzzzzzzzzzz");
                storyHbox.getChildren().remove(fireball); //Removes the image from the HBox
                storyHbox.getChildren().add(zzz); //Adds the image to the HBox
                
            }
            else if (storyIndex == 4) { 
                storyLabel.setText("You wake up to loud screaming! \nA goblin has broken into the school and is attacking your classmates! \nYou must defeat it");
                storyHbox.getChildren().remove(zzz); //Removes the image from the HBox
                storyHbox.getChildren().add(goblinStory); //Adds the image to the HBox
            }
            else if (storyIndex == 5) { //makes the storyScene the scene that will be displayed after the goblin is defeated
                storyLabel.setText("You feel dizzy and pass out!");
                storyHbox.getChildren().remove(goblinStory); //Removes the image from the HBox
                switchScenes(goblinScene); //Switches to the combat scene    
            }
            else if (storyIndex == 6) {
                storyLabel.setText("You wake up in the infirmary \nNurse: 'You were out for awhile! To prevent this in the future I will teach you how to heal yourself!'");
                storyHbox.getChildren().add(nurse); //Adds the image to the HBox
            }
            else if (storyIndex == 7) {
                storyLabel.setText("You learned Heal!");
                player.addSpell(heal); //Adds the spell to the player's spell list
                storyHbox.getChildren().remove(nurse); //Removes the image from the HBox
                storyHbox.getChildren().add(heal_img); //Adds the image to the HBox
            }
            else if (storyIndex == 8) { //Gives the player the option to accept or decline the quest, if declined the game ends
                storyHbox.getChildren().remove(heal_img); //Removes the image from the HBox
                storyHbox.getChildren().add(headmaster); //Adds the image to the HBox
                storyVbox.getChildren().add(yesNoHbox); //Adds the yes and no buttons
                textVbox.getChildren().remove(nextButton); //Removes the next button
                storyLabel.setText("The headmaster calls you into his office \nHeadmaster: I was impressed with your skills in the battle against the goblin. I would like you to go on a quest for me. \nHeadmaster: I need you to go to the cave of the evil wizard Vlad and defeat him. \nHeadmaster: If you do this I will give you a full scholarship to the school. \nHeadmaster: Do you accept?");
            }
            else if (storyIndex == 9) {
                counter ++;
                textVbox.getChildren().add(nextButton); //Adds the next button
                storyVbox.getChildren().remove(yesNoHbox); //Removes the yes and no buttons
                storyLabel.setText("The idea of free tuition is tempting enough for you to accept the quest. \nThe headmaster teaches you the spell Sleep and sends you on your way. \n*Sleep is a spell that will paralyze the enemy for 1 turn*");
                player.addSpell(sleep); //Adds the spell to the player's spell list
            }
            else if (storyIndex == 10) {
                storyHbox.getChildren().remove(headmaster); //Removes the image from the HBox
                storyHbox.getChildren().add(spellBook); //Adds the image to the HBox
                storyLabel.setText("Lets go over what spells you have learned so far: Spark, Fireball, Heal, and Sleep. \nYou head out and begin the trek to Vlad's Lair");

            }
            else if (storyIndex == 11) { //Gives the player the option to continue or not, if not the game ends
                storyHbox.getChildren().remove(spellBook); //Removes the image from the HBox
                storyHbox.getChildren().add(vladLair); //Adds the image to the HBox
                storyHbox.getChildren().remove(wizardStory); //Removes the image from the HBox
                storyVbox.getChildren().add(yesNoHbox); //Adds the yes and no buttons
                textVbox.getChildren().remove(nextButton); //Removes the next button
                storyLabel.setText("You get to the gate of the castle. There's no turning back once you enter, do you enter?");

            }
            else if (storyIndex == 12) { 
                textVbox.getChildren().add(nextButton); //Adds the next button
                storyVbox.getChildren().remove(yesNoHbox); //Removes the yes and no buttons
                storyHbox.getChildren().add(wizardStory); //Adds the image to the HBox
                storyHbox.getChildren().remove(vladLair); //Removes the image from the HBox
                storyHbox.getChildren().add(vladStory); //Adds the image to the HBox
                storyLabel.setText("You enter the castle and begin to quietly walk around. Suddenly you hear a voice behind you \nVlad: Didn't think the old man would resort to sending children");
            }
            else if (storyIndex == 13) { //Gives the player the option to join Vlad or not, if join, the game ends
                textVbox.getChildren().remove(nextButton); //Removes the next button
                storyVbox.getChildren().add(joinHbox); //Adds the join me buttons
                storyLabel.setText("Vlad: Aren't you mad at him for sending you here to die? Vlad: I'll give you a chance to live, join me and I will spare you. \nVlad: You will be my apprentice and we will rule the world together! \nVlad: What do you say?");
            }
            else if (storyIndex == 14) {
                textVbox.getChildren().add(nextButton); //Adds the next button
                storyVbox.getChildren().remove(joinHbox); //Removes the join me buttons
                storyLabel.setText("Player: I'll never join you! \nVlad: Lets make this quick then");;
            }
            else if (storyIndex == 15) { //Sets the storyScene to the scene that will be displayed after the boss is defeated
                storyHbox.getChildren().remove(vladStory); //Removes the image from the HBox
                storyHbox.getChildren().add(headmaster); //Adds the image to the HBox
                storyLabel.setText("You defeated Vlad! You head back to the school and are greeted by the headmaster");
                player.resetStats(); //Resets the player's stats
                switchScenes(bossScene); //Switches to the boss scene
            }
            else if (storyIndex == 16) {
                storyLabel.setText("Headmaster: Congratulations young one, you have protected the school and defeated Vlad. \nHeadmaster: I will keep my promise and give you a full scholarship to the school. \nHeadmaster: You will also be given a private room in the dorms. \nHeadmaster: I hope you enjoy your time here!");
            }
            else if (storyIndex == 17) { 
                textVbox.getChildren().remove(nextButton); //Removes the next button
                storyHbox.getChildren().remove(headmaster); //Removes the image from the HBox
                storyHbox.getChildren().add(happyEnding); //Adds the image to the HBox
                storyLabel.setText("You have completed the game! \nThank you for playing! \nCreated by Grace Bero");
            }

        }
        
    }
    
    /**
     * NoButtonHandler class
     * @ActionEvent event, when the no button is pressed the story will end. There are slightly different storyLabels depending on when No is pressed
     */
    class NoButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            storyVbox.getChildren().remove(yesNoHbox); //Removes the yes and no buttons
            storyHbox.getChildren().clear(); //Removes the images from the HBox
            storyHbox.getChildren().add(storyGameOver); //Adds the game over image to the HBox
            
            counter ++;

            if (counter == 1) { //If the player declines the quest when given by the headmaster
                storyLabel.setText("You decline the headmaster request, head back to your dorm, and go to sleep \nYou wake up to the sound of loud screaming! \nVlad broke in and attacked the school! \nYou were unprepared and everyone died \nGAME OVER");
            } else if (counter == 2) { //if the player decides to abandon the quest when they get to the gate
                storyLabel.setText("You are overwhelmed with fear and decide to abandon the quest. \nYou head back to the school to find it up in flames. \nGAME OVER ");
            }
        }
    }

    /**
     * JoinButton Class
     * @ActionEvent event, when the join button is pressed the story will end
     */
    
    class joinButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) { //If the player decides to join Vlad
            storyVbox.getChildren().remove(joinHbox); //Removes the join me buttons
            storyHbox.getChildren().clear(); //Removes the images from the HBox
            storyHbox.getChildren().add(happyEnding); //Adds the happy ending image to the HBox
            storyLabel.setText("He turns you into a vampire and together you take over the school. \nYou realized that the true villian of the story was the headmaster and you were just a pawn in his game. \nYou and Vlad make real change at the school and it becomes a better place for all. \nTHE END");
        }

    }

    /**
     * Creates the goblin scene
     * @return goblinScene
     */
    
    public Scene createGoblinScene() {
        GoblinCombatText = new Label("COMBAT: \nCombat is a turn based system. You will press the button with the attack you want to use and then the enemy will attack you. \nYou will continue to do this until either you or the enemy dies. \nYou will regenerate 2 stamina per turn \nGood luck!");
        GoblinCombatText.setFont(Font.font("Lucina Calligraphy", 14)); //sets font and size of GoblinCombatText
        GoblinCombatText.setAlignment(Pos.CENTER); // Centers the VBox

        //Creates the Action Buttons
        //All buttons will be setOnAction to the GoblinAttackButtonHandler
        physicalButtonG = new Button("Physical Attack"); //Creates the physical attack button
        physicalButtonG.setOnAction(new GoblinAttackButtonHandler()); //sets the physical attack button to the GoblinAttackButtonHandler class

        basicButtonG = new Button("Spark"); //Creates the basic attack button
        basicButtonG.setOnAction(new GoblinAttackButtonHandler()); //sets the basic attack button to the GoblinAttackButtonHandler class

        ultButtonG = new Button("Fireball"); //Creates the ult attack button
        ultButtonG.setOnAction(new GoblinAttackButtonHandler()); //sets the ult attack button to the GoblinAttackButtonHandler class

        continueButtonG = new Button("Continue"); //Creates the continue button, will be used to switch to the story scene when goblin is dead
        continueButtonG.setOnAction(new GoblinAttackButtonHandler()); //sets the continue button to the GoblinAttackButtonHandler class

        actionHboxG = new HBox(10, physicalButtonG, basicButtonG, ultButtonG); //Creates the HBox that will hold the action buttons
        actionHboxG.setAlignment(Pos.CENTER); // Centers the HBox
        
        //Resizes the images
        wizardGCombat.setFitHeight(300);
        wizardGCombat.setFitWidth(150);
        
        goblinCombat.setFitHeight(300);
        goblinCombat.setFitWidth(150);
        
        goblinGameOver.setFitHeight(500);
        goblinGameOver.setFitWidth(600);

        //Creates the stats that will be displayed
    
        playerStatsG = new Label("Player: \nHealth: " + player.getHealth() + "\nLives: " + player.getLives() + "\nStamina: " + player.getStamina() + "\n" +"\nPlayer Spells: \n" + physical.toString() + "\n" + basic.toString() + "\n" + ult.toString()); //Creates the player stats
        playerStatsG.setFont(Font.font("Lucina Calligraphy", 14)); //sets font and size of playerStatsG
        playerGHbox = new HBox(10, playerStatsG, wizardGCombat); //Creates the HBox that will hold the player stats and the image
    
        goblinStats = new Label("Goblin Stats: \nHealth: " + minion.getHealth()); //Only display the health because the stamina should be a secret
        goblinStats.setFont(Font.font("Lucina Calligraphy", 14)); //sets font and size of goblinStats
        goblinGHbox = new HBox(10, goblinStats, goblinCombat); //Creates the HBox that will hold the goblin stats and the image

        statsHboxG = new HBox(50, playerGHbox, goblinGHbox); //Creates the HBox that will hold the playerGHbox and the goblinGHbox
        statsHboxG.setAlignment(Pos.CENTER); // Centers the HBox

        goblinVbox = new VBox(10, GoblinCombatText, statsHboxG, actionHboxG); //Creates the VBox that will hold the GoblinCombatText, statsHboxG, and actionHboxG
        goblinVbox.setAlignment(Pos.CENTER); // Centers the VBox

        goblinScene = new Scene(goblinVbox, 1000, 700); //creates the scene

        return goblinScene;
    }

    /**
     * GoblinAttackButtonHandler class
     * @ActionEvent event, when the player presses an action button 
     */
    class GoblinAttackButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            
            boolean enoughStamina = false; //Keeps track of if the player has enough stamina to use the attack

            if (player.getLives() > 0 && minion.getHealth() > 0) { //If the player is alive and the goblin is alive
                if (enoughStamina == false) { //If the player does not have enough stamina to use the attack this will prevent the combat from progressing
        
                    if (event.getSource() == basicButtonG) { //If the player decides to use basic 
                        if (player.getStamina() >= basic.getStaminaCost()) { //Checks to see if the player has enough stamina
                            //Player Attacks
                            player.attack(basic); //Uses the basic attack
                            minion.loseHealth(player.getDamage()); //Goblin loses health

                            //Goblin Attacks
                            minion.attack(); //Goblin attacks
                            player.loseHealth(minion.getDamage()); //Player loses health

                            //Updates the combat text
                            GoblinCombatText.setText("Player: You used Spark! Dealing 10 damage! \nMinion: " + minion.getAttackLine());
                            
                            //Regenerate Stamina for both parties
                            minion.staminaRegen(); //Regenerates the goblin's stamina
                            player.staminaRegen(); //Regenerates the player's stamina

                            enoughStamina = true; //Sets enoughStamina to true so the combat can progress

                        } else { //If there is not enough stamina
                            GoblinCombatText.setText("You do not have enough stamina to use this attack!");
                            enoughStamina = false; //Sets enoughStamina to false so the combat cannot progress
                        }
                    
                    } else if (event.getSource() == ultButtonG) { //if the player decides to use ult

                        if (player.getStamina() >= ult.getStaminaCost()) { //Checks to see if the player has enough stamina
                            //Player Attacks
                            player.attack(ult); //Uses the ult attack
                            minion.loseHealth(player.getDamage()); //Goblin loses health

                            //Goblin Attacks
                            minion.attack(); //Goblin attacks
                            player.loseHealth(minion.getDamage()); //Player loses health

                            //Updates the combat text
                            GoblinCombatText.setText("Player: You used Fireball! Dealing 50 damage! \nMinion: " + minion.getAttackLine());

                            //Regenerate Stamina for both parties
                            minion.staminaRegen(); //Regenerates the goblin's stamina
                            player.staminaRegen(); //Regenerates the player's stamina

                            enoughStamina = true;

                        } else { //If there is not enough stamina
                            GoblinCombatText.setText("You do not have enough stamina to use this attack!");
                            enoughStamina = false; //Sets enoughStamina to false so the combat cannot progress
                        }
                    
                    } else if (event.getSource() == physicalButtonG) {  //if the player decides to use physical
                        if (player.getStamina() >= physical.getStaminaCost()) { //Checks to see if the player has enough stamina
                            //Player Attacks
                            player.attack(physical); //Uses the physical attack
                            minion.loseHealth(player.getDamage()); //Goblin loses health

                            //Goblin Attacks
                            minion.attack(); //Goblin attacks
                            player.loseHealth(minion.getDamage()); //Player loses health

                            //Updates the combat text
                            GoblinCombatText.setText("Player: You used Physical Attack! Dealing 10 damage! \nMinion: " + minion.getAttackLine());

                            //Regenerate Stamina for both parties
                            minion.staminaRegen(); //Regenerates the goblin's stamina
                            player.staminaRegen(); //Regenerates the player's stamina

                            enoughStamina = true;  //Sets enoughStamina to true so the combat can progress

                        } else { //If there is not enough stamina
                            GoblinCombatText.setText("You do not have enough stamina to use this attack!");
                            enoughStamina = false; //Sets enoughStamina to false so the combat cannot progress
                        }
                    }
                }
            
            // Update the Player Stats every turn
            playerStatsG.setText("Player: \nHealth: " + player.getHealth() + "\nLives: " + player.getLives() + "\nStamina: " + player.getStamina() + "\n" +"\nPlayer Spells: \n" + physical.toString() + "\n" + basic.toString() + "\n" + ult.toString());
            goblinStats.setText("Goblin: \nHealth: " + minion.getHealth());

            //If the player Dies, the game is over
            } else if (player.getLives() <= 0) { //If the player dies
                goblinVbox.getChildren().remove(actionHboxG); //Removes the action buttons
                goblinVbox.getChildren().remove(statsHboxG); //Removes the stats
                goblinVbox.getChildren().add(goblinGameOver); //Adds the game over image
                GoblinCombatText.setText("You have died! GAME OVER");
            
            //If the goblin dies, the player wins and the story continues
            } else if (minion.getHealth() <= 0) { //If the goblin dies
                goblinVbox.getChildren().remove(actionHboxG); //Removes the action buttons
                goblinVbox.getChildren().remove(statsHboxG); //Removes the stats
                GoblinCombatText.setText("You have defeated the goblin!");
                goblinVbox.getChildren().add(continueButtonG); //Adds the continue button
                switchScenes(storyScene); //Switches to the story scene
            }
        }     
    }

    /**
     * Creates the boss scene
     * @return bossScene
    */

    public Scene createBossScene() {

        vladCombatText = new Label("COMBAT: Combat with Vlad is very similar to combat with the goblin. \nHowever, Vlad is much stronger and has more health. \nHe also has a new stat called bloodlust, when he reaches 5 blood lust he will unleash a powerful attack and heal himself. \nHint: If he is put to sleep his bloodlust is reset to 0 \nGood luck!");
        vladCombatText.setFont(Font.font("Lucina Calligraphy", 14)); //sets font and size of vladCombatText
        vladCombatText.setAlignment(Pos.CENTER); // Centers the VBox

        //Creates the Action Buttons
        //All buttons will be setOnAction to the BossAttackButtonHandler
        physicalButtonV = new Button("Physical Attack"); //Creates the physical attack button
        physicalButtonV.setOnAction(new BossAttackButtonHandler()); //sets the physical attack button to the BossAttackButtonHandler class

        basicButtonV = new Button("Spark"); //Creates the basic attack button
        basicButtonV.setOnAction(new BossAttackButtonHandler()); //sets the basic attack button to the BossAttackButtonHandler class

        ultButtonV = new Button("Fireball"); //Creates the ult attack button
        ultButtonV.setOnAction(new BossAttackButtonHandler()); //sets the ult attack button to the BossAttackButtonHandler class

        healButtonV = new Button("Heal"); //Creates the heal button
        healButtonV.setOnAction(new BossAttackButtonHandler()); //sets the heal button to the BossAttackButtonHandler class

        sleepButtonV = new Button("Sleep"); //Creates the sleep button
        sleepButtonV.setOnAction(new BossAttackButtonHandler()); //sets the sleep button to the BossAttackButtonHandler class

        continueButtonV = new Button("Continue"); //Creates the continue button, will be used to switch to the story scene when vlad is dead
        continueButtonV.setOnAction(new BossAttackButtonHandler()); //sets the continue button to the BossAttackButtonHandler class

        //Creates the HBox that will hold the action buttons
        actionHboxV = new HBox(10, physicalButtonV, basicButtonV, ultButtonV, healButtonV, sleepButtonV); 
        actionHboxV.setAlignment(Pos.CENTER); // Centers the HBox

        //Resizes the images
        wizardCombatV.setFitHeight(300);
        wizardCombatV.setFitWidth(150);

        vladCombat.setFitHeight(300);
        vladCombat.setFitWidth(150);

        bossGameOver.setFitHeight(500);
        bossGameOver.setFitWidth(600);

        //Creates the stats that will be displayed
        playerStatsV = new Label("Player: \nHealth: " + player.getHealth() + "\nLives: " + player.getLives() + "\nStamina: " + player.getStamina() + "\n" +"\nPlayer Spells: \n" + physical.toString() + "\n" + basic.toString() + "\n" + ult.toString() + "\nHeal, +20 HP, 2 stamina" + "\nSleep: Zzzzz, 6 Stamina"); //Creates the player stats
        playerStatsV.setFont(Font.font("Lucina Calligraphy", 14)); //sets font and size of playerStatsV
        playerHboxV = new HBox(10, playerStatsV, wizardCombatV); //Creates the HBox that will hold the player stats and the image

        vladStats = new Label("Vlad Stats: \nHealth: " + bbeg.getHealth() + "\nBloodlust: " + bbeg.getBloodlust()); //Only display the health and bloodlust because the stamina should be a secret
        vladStats.setFont(Font.font("Lucina Calligraphy", 14)); //sets font and size of vladStats
        vladHbox = new HBox(10, vladStats, vladCombat); //Creates the HBox that will hold the vlad stats and the image

        //Creates the HBox that will hold the player and vlad stats
        statsHboxV = new HBox(50, playerHboxV, vladHbox);
        statsHboxV.setAlignment(Pos.CENTER); // Centers the HBox

        //Creates the vbox that will hold the vladCombatText, statsHboxV, and actionHboxV
        vladVbox = new VBox(10, vladCombatText, statsHboxV, actionHboxV);
        vladVbox.setAlignment(Pos.CENTER); // Centers the VBox

        bossScene = new Scene(vladVbox, 1000, 700); //creates the scene

        return bossScene;
    }

    /**
     * BossAttackButtonHandler class
     * @ActionEvent event, when the player presses an action button
    */

    class BossAttackButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            boolean enoughStamina = false; //Keeps track of if the player has enough stamina to use the attack

            //Player's Turn
            if (player.getLives() > 0 && bbeg.getHealth() > 0) { //If the player is alive and the boss is alive
                if (enoughStamina == false) { //If the player does not have enough stamina to use the attack this will prevent the combat from progressing

                    //If the player chooses to use the basic attack
                    if (event.getSource() == basicButtonV) { //If the player decides to use basic
                        if (player.getStamina() >= basic.getStaminaCost()) { //Checks to see if the player has enough stamina
                            //Player Attacks
                            player.attack(basic); //Uses the basic attack
                            bbeg.loseHealth(player.getDamage()); //Boss loses health

                            //Vlad Attacks
                            bbeg.attack(); //Boss attacks
                            player.loseHealth(bbeg.getDamage()); //Player loses health

                            //Updates the combat text
                            vladCombatText.setText("Player: You used Spark! Dealing 10 damage! \nVlad: " + bbeg.getAttackLine());

                            //Regenerate Stamina for both parties
                            bbeg.staminaRegen(); //Regenerates Vlad's stamina
                            player.staminaRegen(); //Regenerates the player's stamina
                            bbeg.bloodlustRegen(); //Regenerates Vlad's bloodlust

                            enoughStamina = true; //Sets enoughStamina to true so the combat can progress
                        
                        } else { //If there is not enough stamina
                            vladCombatText.setText("You do not have enough stamina to use this attack!"); //If there is not enough stamina
                            enoughStamina = false; //Sets enoughStamina to false so the combat cannot progress
                        }
                    } else if (event.getSource() == ultButtonV) { //If the player decides to use ult
                        if (player.getStamina() >= ult.getStaminaCost()) { //Checks to see if the player has enough stamina
                            //Player Attacks
                            player.attack(ult); //Uses the ult attack
                            bbeg.loseHealth(player.getDamage()); //Boss loses health

                            //Vlad Attacks
                            bbeg.attack(); //Boss attacks
                            player.loseHealth(bbeg.getDamage()); //Player loses health

                            //Updates the combat text
                            vladCombatText.setText("Player: You used Fire Ball! Dealing 50 damage! \nVlad: " + bbeg.getAttackLine());

                            //Regenerate Stamina for both parties
                            bbeg.staminaRegen(); //Regenerates Vlad's stamina
                            player.staminaRegen(); //Regenerates the player's stamina
                            bbeg.bloodlustRegen(); //Regenerates Vlad's bloodlust

                            enoughStamina = true; //Sets enoughStamina to true so the combat can progress
                        } else { //If there is not enough stamina
                            vladCombatText.setText("You do not have enough stamina to use this attack!");
                            enoughStamina = false; //Sets enoughStamina to false so the combat cannot progress
                        }
                    
                    } else if (event.getSource() == physicalButtonV) { //If the player decides to use physical
                        if (player.getStamina() >= physical.getStaminaCost()) { //Checks to see if the player has enough stamina
                            //Player Attacks
                            player.attack(physical); //Uses the physical attack
                            bbeg.loseHealth(player.getDamage()); //Boss loses health

                            //Vlad Attacks
                            bbeg.attack(); //Boss attacks
                            player.loseHealth(bbeg.getDamage()); //Player loses health

                            //Updates the combat text
                            vladCombatText.setText("Player: You used Physical! Dealing 10 damage! \nVlad: " + bbeg.getAttackLine());

                            //Regenerate Stamina for both parties
                            bbeg.staminaRegen(); //Regenerates Vlad's stamina
                            player.staminaRegen(); //Regenerates the player's stamina
                            bbeg.bloodlustRegen(); //Regenerates Vlad's bloodlust

                            enoughStamina = true; //Sets enoughStamina to true so the combat can progress
                        } else { //If there is not enough stamina
                            vladCombatText.setText("You do not have enough stamina to use this attack!");
                            enoughStamina = false; //Sets enoughStamina to false so the combat cannot progress
                        }
                    } else if (event.getSource() == healButtonV) { //If the player decides to use heal
                        if (player.getStamina() >= heal.getStaminaCost()) { //Checks to see if the player has enough stamina
                            //Player Attacks
                            player.attack(heal); //Uses the heal attack
                            player.gainHealth(player.getDamage()); //Player gains health

                            //Vlad Attacks
                            bbeg.attack(); //Boss attacks
                            player.loseHealth(bbeg.getDamage()); //Player loses health

                            //Updates the combat text
                            vladCombatText.setText("Player: You used heal, gaining 20 health! \nVlad: " + bbeg.getAttackLine());

                            //Regenerate Stamina for both parties
                            bbeg.staminaRegen(); //Regenerates Vlad's stamina
                            player.staminaRegen(); //Regenerates the player's stamina
                            bbeg.bloodlustRegen(); //Regenerates Vlad's bloodlust

                            enoughStamina = true; //Sets enoughStamina to true so the combat can progress
                        } else { //If there is not enough stamina
                            vladCombatText.setText("You do not have enough stamina to use this attack!");
                            enoughStamina = false; //Sets enoughStamina to false so the combat cannot progress
                        }
                    } else if (event.getSource() == sleepButtonV) { //If the player decides to use sleep
                        if (player.getStamina() >= sleep.getStaminaCost()) { //Checks to see if the player has enough stamina
                            //Player Attacks
                            player.attack(sleep); //Uses the sleep attack
                            player.gainHealth(player.getDamage()); //Player gains health

                            //Vlad Attacks
                            bbeg.goSleep(); //Boss goes to sleep, does not attack, loses all bloodlust

                            //Updates the combat text
                            vladCombatText.setText("Player: You used sleep! \nVlad: " + bbeg.getAttackLine()); 

                            //Regenerate Stamina for both parties
                            bbeg.staminaRegen(); //Regenerates Vlad's stamina
                            player.staminaRegen(); //Regenerates the player's stamina
                            bbeg.bloodlustRegen(); //Regenerates Vlad's bloodlust

                            enoughStamina = true; //Sets enoughStamina to true so the combat can progress
                        } else { //If there is not enough stamina
                            vladCombatText.setText("You do not have enough stamina to use this attack!");
                            enoughStamina = false; //Sets enoughStamina to false so the combat cannot progress
                        }
                    } 
                }
            
            //Update the stats    
            playerStatsV.setText("Player: \nHealth: " + player.getHealth() + "\nLives: " + player.getLives() + "\nStamina: " + player.getStamina() + "\n" +"\nPlayer Spells: \n" + physical.toString() + "\n" + basic.toString() + "\n" + ult.toString() + "\nHeal, +20 HP, 2 stamina" + "\nSleep: Zzzzz, 6 Stamina");
            vladStats.setText("Vlad Stats: \nHealth: " + bbeg.getHealth() + "\nBloodlust: " + bbeg.getBloodlust());
            
            //If the player Dies, the game is over
            } else if (player.getLives() <= 0) { //If the player dies
                vladVbox.getChildren().remove(actionHboxV); //Removes the action buttons
                vladVbox.getChildren().remove(statsHboxV); //Removes the stats
                vladVbox.getChildren().add(bossGameOver); //Adds the game over image
                vladCombatText.setText("You have died! GAME OVER");
            
            //If Vlad dies, the player wins and the story continues
            } else if (bbeg.getHealth() <= 0) { //If Vlad dies
                vladVbox.getChildren().remove(actionHboxV); //Removes the action buttons
                vladVbox.getChildren().remove(statsHboxV); //Removes the stats
                vladCombatText.setText("You have defeated Vlad!");
                vladVbox.getChildren().add(continueButtonV); //Adds the continue button
                switchScenes(storyScene); //Switches to the story scene
            }
        }
    }

    /**
     * Switch Scenes in single Stage 
     * @author javacodejunkie
     * @param scene the scene to switch to
     */
	public void switchScenes(Scene scene) {
		stage.setScene(scene);
	}

    /**
     * Main method to launch the program
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}
