package de.astar.path;

public class Node {

	private int x, y, g, h;
	private C type;
	private Node parent;

	public Node(int x, int y, C type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public Node(Node node) {
		this(node.x, node.y, node.type);
	}

	public C getType() {
		return type;
	}

	public int getF() {
		return g + h;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
}
