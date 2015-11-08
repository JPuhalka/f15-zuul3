import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jennifermoran
 */
public enum ExitType {

	NORTH("north"), SOUTH("south"), EAST("east"), WEST("west"), UP("up"), DOWN("down"), JUMP("jump");
	String id;
	private static final Map<String, ExitType> lookup = new HashMap<>();

	static {
		for (ExitType exitType : EnumSet.allOf(ExitType.class)) {
			lookup.put(exitType.getId(), exitType);
		}
	}

	ExitType(String id) {
		this.id = id;
	}

	public static ExitType getExitType(String id) {
		return lookup.get(id);
	}

	public String getId() {
		return id;
	}

}
