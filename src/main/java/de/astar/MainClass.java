package de.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import de.astar.path.C;
import de.astar.path.Node;
import de.astar.path.Path;
import de.astar.utils.GraphVisualiser;

public class MainClass {

	public static final int GRID_SIZE = 100;

	public static void main(String[] args) {
		List<Node> graph = new ArrayList<>();

		Node start = null, end = null;

		for (int x = 0; x <= MainClass.GRID_SIZE; x++) {
			for (int y = 0; y <= MainClass.GRID_SIZE; y++) {
				C type = new Random().nextInt(101) < 80 ? C.SOLID : C.AIR;
				Node node = new Node(x, y, type);
				if (x == 0 && node.getType() == C.SOLID && start == null) {
					start = node;
				}

				if (x == GRID_SIZE && type == C.SOLID && end == null) {
					end = node;
				}

				graph.add(node);
			}
		}
		JFrame frame = new JFrame("Graph Visualiser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		List<Node> pathNodes = new Path(graph, start, end).calculate();

		JScrollPane container = new JScrollPane(new GraphVisualiser(graph, pathNodes, start, end));
		container.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		container.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		frame.getContentPane().add(container);
		frame.pack();
		frame.setVisible(true);

	}

}
