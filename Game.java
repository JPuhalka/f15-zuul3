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
	private static final String STABLE = "stable";
	private static final String COURTYARD = "courtyard";
	private static final String KITCHEN = "kitchen";
	private static final String DININGHALL = "dining Hall";
	private static final String GRANDSTAIRCASE = "grand staircase";
	private static final String CLOSETSTAIR = "closet under the stairs";
	private static final String HALLWAYE = "east end of hallway";
	private static final String HALLWAYW = "west end of hallway";
	private static final String BALLROOMN = "north end of ballroom";
	private static final String BALLROOMS = "south end of ballroom";
	private static final String SERVANTSQUART = "servant's quarters";
	private static final String WASHROOM = "washroom";
	private static final String LIBRARY = "library";
	private static final String ARMORY = "armory";
	private static final String SITTINGROOM = "sitting room";
	private static final String LADYSBED = "lady's bedroom";
	private static final String TOPSTAIRS = "top of the stairs";
	private static final String ROOF = "on the roof";
	private static final String MENSBED = "bedroom";
	private static final String LADYSCLOSET = "lady's bedroom closet";
	private static final String MENSCLOSET = "gentlemen's closet";
	private static final String MAID = "closet under the stairs";
	private static final String STORAGECLOSET = "closet under the stairs";
	private static final String GOAL = "closet under the stairs";
	private static final String KITCHENGARDEN = "";

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

		Room courtyard, kitchen, diningHall,grandStaircase, closetStair, hallwayE, hallwayW,
		stable, ballroomN, ballroomS, servantsQuart, washroom, library, armory, sittingRoom, ladysBed,
        topStairs, roof, mensBed, ladysCloset, mensCloset, maid, storageCloset, goal, kitchenGarden,
        shed, northGardens, gazebo, fountain, southGarden, apothecarysShed, apothecarysHerbGarden;

		// create the rooms
        courtyard = new Room("in the courtyard");
        kitchen = new Room("in the kitchen");
        diningHall = new Room("in the dining hall");
        grandStaircase = new Room("at the Grand Staircase");
        closetStair = new Room("in the closet behind the stairs");
        hallwayE = new Room("at the east end of the hallway");
        hallwayW = new Room("at the west end of the hallway");
        stable = new Room("in the stable");
        ballroomN = new Room("in the north end of the ballroom");
        ballroomS = new Room("in the south end of the ballroom");
        servantsQuart = new Room("in the servant's quarters");
        washroom = new Room("in the washroom");
        library = new Room("in the library");
        armory = new Room("in the armory");
        sittingRoom = new Room("in the sitting room");
        ladysBed = new Room("in the lady's bedroom");
        topStairs = new Room("in at the top of the stairs");
        roof = new Room("on the roof above the kitchemn");
        mensBed = new Room("in the gentlemen's bedroom");
        ladysCloset = new Room("in the lady's bedroom closet");
        mensCloset = new Room("in the gentlemen's bedroom closet");
        maid = new Room("in the maid's quarters");
        storageCloset = new Room("in the storage closet");
        goal = new Room("have reached your goal");
        kitchenGarden = new Room("in the kitchen garden");
        shed = new Room("in the shed");
        northGardens = new Room("in the northern gardens");
        gazebo = new Room("in the gazebo");
        fountain = new Room("at the fountain");
        southGarden = new Room("in the southern gardens");
        apothecarysShed = new Room("in the apothecary's shed");
        apothecarysHerbGarden = new Room("in the apothecary's herb garden");

		// initialise room exits
        courtyard.addExit(ExitType.NORTH, kitchen);
        courtyard.addExit(ExitType.SOUTH, stable);
        courtyard.addExit(ExitType.EAST, grandStaircase);

        kitchen.addExit(ExitType.NORTH, kitchenGarden);
        kitchen.addExit(ExitType.SOUTH, courtyard);
        kitchen.addExit(ExitType.EAST, diningHall);
        kitchen.addExit(ExitType.WEST, servantsQuart);

        diningHall.addExit(ExitType.SOUTH, grandStaircase);
		diningHall.addExit(ExitType.EAST, ballroomN);
		diningHall.addExit(ExitType.WEST, kitchen);

        grandStaircase.addExit(ExitType.NORTH, diningHall);
        grandStaircase.addExit(ExitType.SOUTH, hallwayW);
        grandStaircase.addExit(ExitType.WEST, courtyard);
        grandStaircase.addExit(ExitType.UP, topStairs);

        closetStair.addExit(ExitType.SOUTH, hallwayE);

        hallwayE.addExit(ExitType.NORTH, closetStair);
		hallwayE.addExit(ExitType.SOUTH, library);
		hallwayE.addExit(ExitType.EAST, ballroomS);
        hallwayE.addExit(ExitType.WEST, hallwayW);

        hallwayW.addExit(ExitType.NORTH, grandStaircase);
        hallwayW.addExit(ExitType.EAST, hallwayE);

        stable.addExit(ExitType.NORTH, courtyard);
        stable.addExit(ExitType.SOUTH, armory);

        ballroomN.addExit(ExitType.SOUTH, ballroomS);
        ballroomN.addExit(ExitType.EAST, gazebo);
        ballroomN.addExit(ExitType.WEST, diningHall);

        ballroomS.addExit(ExitType.NORTH, ballroomN);
        ballroomS.addExit(ExitType.EAST, fountain);
        ballroomS.addExit(ExitType.WEST, hallwayE);

        servantsQuart.addExit(ExitType.NORTH, washroom);
		servantsQuart.addExit(ExitType.EAST, kitchen);

        washroom.addExit(ExitType.SOUTH, servantsQuart);

        library.addExit(ExitType.NORTH, hallwayE);
        library.addExit(ExitType.EAST, goal);
        library.addExit(ExitType.WEST, armory);

        armory.addExit(ExitType.NORTH, stable);
        armory.addExit(ExitType.SOUTH, apothecarysHerbGarden);
        armory.addExit(ExitType.EAST, library);

        sittingRoom.addExit(ExitType.SOUTH, topStairs);
        sittingRoom.addExit(ExitType.EAST, ladysBed);
        sittingRoom.addExit(ExitType.WEST, roof);

		ladysBed.addExit(ExitType.SOUTH, ladysCloset);
		ladysBed.addExit(ExitType.WEST, sittingRoom);

		topStairs.addExit(ExitType.NORTH, sittingRoom);
		topStairs.addExit(ExitType.SOUTH, mensBed);
		topStairs.addExit(ExitType.EAST, maid);
		topStairs.addExit(ExitType.DOWN, grandStaircase);

		roof.addExit(ExitType.JUMP, courtyard);

		mensBed.addExit(ExitType.NORTH, topStairs);
		mensBed.addExit(ExitType.SOUTH, mensCloset);

		ladysCloset.addExit(ExitType.NORTH, ladysBed);

		mensCloset.addExit(ExitType.NORTH, mensBed);
		mensCloset.addExit(ExitType.EAST, storageCloset);

		maid.addExit(ExitType.SOUTH, storageCloset);
		maid.addExit(ExitType.WEST, topStairs);

		storageCloset.addExit(ExitType.NORTH, maid);
		storageCloset.addExit(ExitType.WEST, mensCloset);

		kitchenGarden.addExit(ExitType.SOUTH, kitchen);
		kitchenGarden.addExit(ExitType.EAST, shed);

		shed.addExit(ExitType.EAST, northGardens);
		shed.addExit(ExitType.WEST, kitchenGarden);

		northGardens.addExit(ExitType.SOUTH, gazebo);
		northGardens.addExit(ExitType.WEST, shed);

		gazebo.addExit(ExitType.NORTH, northGardens);
		gazebo.addExit(ExitType.SOUTH, fountain);
		gazebo.addExit(ExitType.WEST, ballroomN);

		fountain.addExit(ExitType.NORTH, gazebo);
		fountain.addExit(ExitType.SOUTH, southGarden);
		fountain.addExit(ExitType.WEST, ballroomS);

		southGarden.addExit(ExitType.NORTH, fountain);
		southGarden.addExit(ExitType.SOUTH, apothecarysShed);

		apothecarysShed.addExit(ExitType.NORTH, southGarden);
		apothecarysShed.addExit(ExitType.WEST, apothecarysHerbGarden);

		apothecarysHerbGarden.addExit(ExitType.EAST, apothecarysShed);
		apothecarysHerbGarden.addExit(ExitType.NORTH, armory);

		rooms.put(STABLE, stable);

		//list of items in rooms
		kitchen.addItem(new Item(ItemType.FOOD, "a leg of lamb sitting on the counter", 10.0));
		diningHall.addItem(new Item(ItemType.FOOD, "a tray of appetizers", 5.0));
		servantsQuart.addItem(new Item(ItemType.SERVANTSCLOTHES, "servant's clothing", 0));
		washroom.addItem(new Item(ItemType.BATH, "water", 5.0));
		library.addItem(new Item(ItemType.FOOD, "a bowl of nuts", 5.0));
		library.addItem(new Item(ItemType.BOOK, "an old book", 10.0));
		armory.addItem(new Item(ItemType.SWORD, "a sword", 10.0));
		ladysBed.addItem(new Item(ItemType.PERFUME, "a bottle of perfume from the dresser", 5.0));
		mensBed.addItem(new Item(ItemType.COLOGNE, "a bottle of cologne from the dresser", 5.0));
		ladysCloset.addItem(new Item(ItemType.DRESS, "a dress", 0));
		mensCloset.addItem(new Item(ItemType.SUIT, "a suit", 0));
		kitchenGarden.addItem(new Item(ItemType.FOOD, "a basket of carrots", 5.0));
		apothecarysHerbGarden.addItem(new Item(ItemType.HERBS, "some strange herbs", 1.0));
		mensBed.addItem(new Item(ItemType.KEY, "a bronze key with three teeth", 60.0));

		//challenges
		kitchen.setChallenge(new Challenge("You're naked, put on some clothing", ChallengeType.CLOTHING, ExitType.EAST, ItemType.KEY));
		topStairs.setChallenge(new Challenge("There's a guard sleeping next to the door to the bedroom", ChallengeType.GUARD, ExitType.SOUTH, ItemType.KEY));
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		// start outside
		player.setCurrentRoom(rooms.get(STABLE));
		player.addMove(rooms.get(STABLE));
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
		System.out.println("Welcome to the New World of Zuul!");
		System.out.println("The New World of Zuul is a new, slightly less boring adventure game.");
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
