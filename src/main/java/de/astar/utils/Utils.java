package de.astar.utils;

import java.util.Collection;

import de.astar.path.Node;

public class Utils {

	public static int manhattanDistance(Node one, Node two) {
		return Math.abs(one.getX() - two.getX()) + Math.abs(one.getY() - two.getY());
	}

	public static boolean containsNode(Node node, Collection<Node> toSearch) {
		for (Node n : toSearch) {
			if (isSame(n, node)) {
				return true;
			}
		}

		return false;
	}

	public static boolean containsNodeWithLowerF(Node node, Collection<Node> toSearch) {
		for (Node n : toSearch) {
			if (isSame(n, node) && n.getF() < node.getF()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if two nodes have the same position, type is excluded from the
	 * comparison
	 */
	public static boolean isSame(Node one, Node two) {
		if (one.getX() == two.getX() && one.getY() == two.getY()) {
			return true;
		}
		return false;
	}

}
