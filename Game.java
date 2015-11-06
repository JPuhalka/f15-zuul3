/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jennifermoran
 */
public class Game {

	private final Parser parser;
	private final Player player;
	private final Map<String, Room> rooms = new HashMap<>();
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
		outside.addExit(ExitType.EAST, theater);
		outside.addExit(ExitType.SOUTH, lab);
		outside.addExit(ExitType.WEST, pub);
		outside.addItem(new Item(ItemType.BATH, "A bucket of clean water", 50.0));

		theater.addExit(ExitType.WEST, outside);
		theater.addExit(ExitType.EAST, lab);
		theater.setChallenge(new Challenge("There's a guard sleeping next to the door to the bedroom", ChallengeType.GUARD, ExitType.EAST, ItemType.KEY));

		pub.addExit(ExitType.EAST, outside);
		pub.addItem(new Item(ItemType.FOOD, "A bowl of nuts", 5.0));

		lab.addExit(ExitType.NORTH, outside);
		lab.addExit(ExitType.EAST, office);
		lab.addItem(new Item(ItemType.FOOD, "A can of pringles", 3.0));

		office.addExit(ExitType.WEST, lab);
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
		player.addMove(rooms.get(OUTSIDE));
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
			showInventory();
			break;
		case DROP:
			dropItem(command);
			break;
		case EAT:
			player.eatFood();
			break;
		case MOVES:
			showMoves();
			break;
		case BACK:
			goBack(command);
			break;
		case USE:
			useItem();
			break;
		}
		return wantToQuit;
	}

	/**
	 * Drop command
	 *
	 * @param command
	 */
	private void dropItem(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know what to drop...
			System.out.println("Drop what?");
			return;
		}
		// If you want to drop an item it removes it from your inventory and adds it back to the room
		ItemType itemId = ItemType.getItemType(command.getSecondWord());
		if (player.hasInventory(itemId)) {
			Item item = player.getInventory(itemId);
			player.removeInventory(itemId);
			player.getCurrentRoom().addItem(item);
			System.out.println("You dropped " + item.getDescription() + " and added it back to the room.\n");
		} else {
			System.out.println(itemId + " is not in your inventory.\n");
		}
	}

	/**
	 * Use command
	 *
	 */
	private void useItem() {
		if (!player.getCurrentRoom().hasChallenge()) {
			System.out.println("There is no challenge here");
		} else {
			ItemType itemType = player.getCurrentRoom().getChallenge().getResolution();
			if (player.hasInventory(itemType)) {
				System.out.println("You have used the " + itemType + " to pass the challenge - you may go now.\n");
				player.removeInventory(itemType);
				player.getCurrentRoom().removeChallenge();
			} else {
				System.out.println("You need a " + itemType + " to pass the challenge and you don't have it - go find it.\n");
			}
		}
	}

	/**
	 * Take command
	 *
	 * @param command
	 */
	private void takeItem(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Take what?");
			return;
		}
		// If room has the item you want you can remove it from the room and add it to your inventory
		ItemType itemId = ItemType.getItemType(command.getSecondWord());
		if (player.getCurrentRoom().hasItem(itemId)) {
			Item item = player.getCurrentRoom().getItem(itemId);
			if (player.addInventory(item)) {
				player.getCurrentRoom().takeItem(itemId);
				System.out.println("You took " + item.getDescription() + " and added it to your inventory.\n");
			} else {
				System.out.println("You are carrying too much weight to add this item! Put something down first!");
			}
		} else {
			System.out.println(itemId + " is not in this room.\n");
		}
	}

	/**
	 * Look command
	 */
	private void examineRoom() {
		System.out.println(player.getCurrentRoom().getRoomExamination());

	}

	/**
	 * Inventory command
	 */
	private void showInventory() {
		System.out.println(player.showInventory());
	}

	/**
	 * Moves Command
	 */
	private void showMoves() {
		System.out.println(player.showMoves());
	}

	/**
	 * Print out some help information.
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
	 * Go command
	 *
	 * @param command
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Go where?");
			return;
		}

		ExitType direction = ExitType.getExitType(command.getSecondWord());

		// Try to leave current room.
		Room nextRoom = player.getCurrentRoom().getNextRoom(direction);

		if (nextRoom == null) {
			System.out.println("There is no door!");
		} else {
			if (player.getCurrentRoom().canExit(direction) == false) {
				System.out.println("Your path is blocked! " + player.getCurrentRoom().getChallenge().getDescription());
			} else {
				if (player.checkHunger()) {
					player.setCurrentRoom(nextRoom);
					player.addMove(nextRoom);
					System.out.println(player.getCurrentRoom().getLongDescription());
				}
			}
		}
	}

	/**
	 * Back command
	 *
	 * @param command
	 */
	private void goBack(Command command) {
		int howMany = 1;
		if (command.hasSecondWord()) {
			try {
				howMany = Integer.parseInt(command.getSecondWord());
			} catch (NumberFormatException nfe) {
				System.out.println("Number of moves back must be blank (1 move) or numeric");
				return;
			}
		}

		if (player.getMoveHistory().size() <= howMany) {
			System.out.println("You only have " + player.getMoveHistory().size() + " move(s)");
			return;
		}

		player.getMoveHistory().subList(player.getMoveHistory().size() - howMany, player.getMoveHistory().size()).clear();
		player.setCurrentRoom(player.getMoveHistory().get(player.getMoveHistory().size() - 1));
		System.out.println(player.getCurrentRoom().getLongDescription());
	}

	/**
	 * Quit command
	 *
	 * @param command
	 * @return boolean
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
