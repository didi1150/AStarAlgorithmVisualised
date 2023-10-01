package de.astar.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import de.astar.MainClass;
import de.astar.path.C;
import de.astar.path.Node;

public class GraphVisualiser extends JPanel {

	private final List<Node> nodes; // List of nodes to visualize
	private final List<Node> pathNodes; // List of nodes in the special path
	private final Node startNode; // Starting node
	private final Node endNode; // Ending node

	public GraphVisualiser(List<Node> nodes, List<Node> pathNodes, Node startNode, Node endNode) {
		this.nodes = nodes;
		this.pathNodes = pathNodes;
		this.startNode = startNode;
		this.endNode = endNode;

		int padding = 10;
		int maxX = nodes.stream().mapToInt(Node::getX).max().orElse(0);
		int maxY = nodes.stream().mapToInt(Node::getY).max().orElse(0);
		int preferredWidth = (maxX + 1) * MainClass.GRID_SIZE + 2 * padding;
		int preferredHeight = (maxY + 1) * MainClass.GRID_SIZE + 2 * padding;
		setPreferredSize(new Dimension(preferredWidth, preferredHeight));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int gridSize = 30; // Adjust the size of each square
		int padding = 10; // Adjust padding around the grid

		// Loop through nodes and draw squares
		for (Node node : nodes) {
			int x = node.getX() * gridSize + padding;
			int y = node.getY() * gridSize + padding;

			// Set square color based on node properties
			if (node == startNode || node == endNode) {
				g.setColor(Color.YELLOW);
			} else if (pathNodes != null && Utils.containsNode(node, pathNodes)) {
				g.setColor(Color.BLUE);
			} else if (node.getType() == C.SOLID) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.RED);
			}

			// Draw the square
			g.fillRect(x, y, gridSize, gridSize);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, gridSize, gridSize);
		}
	}

}
