/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jennifermoran
 */
public class Game {

    private Parser parser;
    private Player player;
    private Map<String, Room> rooms = new HashMap<>();
    private static final String OUTSIDE = "outside";
    private static final String LAB = "lab";
    private static final String PUB = "pub";
    private static final String THEATER = "theater";
    private static final String OFFICE = "office";

    /**
     * Create the game and initialize its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
        player = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        // create the rooms
        Room outside = new Room("outside the main entrance of the university");
        Room theater = new Room("in a lecture theater");
        Room pub = new Room("in the campus pub");
        Room lab = new Room("in a computing lab");
        Room office = new Room("in the computing admin office");

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.addItem(new Item(ItemType.BATH, "A bucket of clean water", 50.0));

        theater.setExit("west", outside);
        theater.setExit("east", lab);
        theater.addChallenge(new Challenge("There's a guard sleeping next to the door to the bedroom", ChallengeType.GUARD, "east"));

        pub.setExit("east", outside);
        pub.addItem(new Item(ItemType.FOOD, "A bowl of nuts", 5.0));

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.addItem(new Item(ItemType.FOOD, "A can of pringles", 3.0));

        office.setExit("west", lab);
        office.addItem(new Item(ItemType.KEY, "A bronze key with three teeth", 60.0));

        rooms.put(OUTSIDE, outside);
        rooms.put(LAB, lab);
        rooms.put(THEATER, theater);
        rooms.put(PUB, pub);
        rooms.put(OFFICE, office);
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        // start outside
        player.setCurrentRoom(rooms.get(OUTSIDE));
        printWelcome();
        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while (!finished && player.isLiving()) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
        case UNKNOWN:
            System.out.println("I don't know what you mean...");
            break;
        case HELP:
            printHelp();
            break;
        case GO:
            goRoom(command);
            break;
        case QUIT:
            wantToQuit = quit(command);
            break;
        case LOOK:
            examineRoom();
            break;
        case TAKE:
            takeItem(command);
            break;
        case INVENTORY:
            player.showInventory();
            break;
        case DROP:
            dropItem(command);
            break;
        case EAT:
            player.eatFood();
            break;
        }
        return wantToQuit;
    }

    // implementations of user commands:
    /**
     * drops an item from the user's inventory and adds to the room
     */
    private void dropItem(Command command) {
         if (!command.hasSecondWord()) {
                    // if there is no second word, we don't know where to go...
                    System.out.println("Drop what?");
                    return;
         }
         String itemName = command.getSecondWord();
         ItemType itemTypeToFind = ItemType.getItemType(itemName);
         Item itemToAdd = player.removeItem(itemTypeToFind);
         if(itemToAdd!=null){
             player.getCurrentRoom().addItem(itemToAdd);
             System.out.println("You remove " + itemToAdd.getDescription() + " from your inventory and leave it in the room");
         }
         
    }
    
    /**
     * Retrieves an item in the room and adds it to player inventory.
     */
    private void takeItem(Command command) {
         if (!command.hasSecondWord()) {
                    // if there is no second word, we don't know where to go...
                    System.out.println("Take what?");
                    return;
                }

         String itemString = command.getSecondWord();
         ItemType itemType = ItemType.getItemType(itemString);
         if (itemType == ItemType.UNKNOWN) {
            // This isn't a valid item
            System.out.println(itemString + " isn't something in the room");
            return;
         }

         if(player.addItem(player.getCurrentRoom().hasItem(itemType))){
            Item newItem = player.getCurrentRoom().takeItem(itemType);
            System.out.println("You took " + newItem.getDescription() + " and added it to your inventory.\n");
         }
    }

    /**
     * Print out information about any items or challenges in the room
     */
    private void examineRoom() {
        String roomStatus;
        roomStatus = player.getCurrentRoom().getRoomExamination();
        System.out.println(roomStatus);

    }

    /**
     * Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        CommandWord.showAllCommands();
        System.out.println("Your items are:");
        ItemType.showAllItems();
    }

    /**
     * Try to go in one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            if (player.getCurrentRoom().canExit(direction) == false) {
                System.out.println("Your path is blocked! " + player.getCurrentRoom().getChallengeText());
            } else {
                if(player.checkHunger()){
                    player.setCurrentRoom(nextRoom);
                    player.addMove(direction);
                    System.out.println(player.getCurrentRoom().getLongDescription());
                }
            }
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true; // signal that we want to quit
        }
    }
}
