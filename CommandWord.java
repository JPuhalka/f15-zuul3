/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jennifermoran
 */
public enum CommandWord {

	GO("go"), LOOK("look"), TAKE("take"), QUIT("quit"), HELP("help"), UNKNOWN("unknown");
	String id;
	private static final Map<String, CommandWord> lookup = new HashMap<>();

	static {
		for (CommandWord command : EnumSet.allOf(CommandWord.class)) {
			lookup.put(command.getId(), command);
		}
	}

	CommandWord(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public static CommandWord getCommand(String id) {
		CommandWord command = (id != null) ? lookup.get(id) : UNKNOWN;
		if (command == null) {
			command = UNKNOWN;
		}
		return command;
	}

	public static boolean isValidItem(String id) {
		CommandWord command = getCommand(id);
		return command != UNKNOWN;
	}

	public static void showAllCommands() {
		for (Map.Entry<String, CommandWord> command : lookup.entrySet()) {
			if (command.getValue() != UNKNOWN) {
				System.out.print(command.getKey() + "  ");
			}
		}
		System.out.println();
	}
}
