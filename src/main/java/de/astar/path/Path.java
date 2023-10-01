package de.astar.path;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import de.astar.utils.Utils;

public class Path {

	private List<Node> graph;
	private Node start, end;

	public Path(List<Node> graph, Node start, Node end) {
		this.graph = graph;
		this.start = start;
		this.end = end;
	}

	public List<Node> calculate() {
		List<Node> path = new ArrayList<>();

		Queue<Node> openQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
		List<Node> closedList = new ArrayList<>();

		openQueue.add(start);

		while (!openQueue.isEmpty()) {
			Node current = openQueue.poll();
			closedList.add(current);
//			System.out.println("Searching: " + current.getX() + " | " + current.getY());
			if (Utils.isSame(current, end)) {
				path = reconstructPath(current);
				break;
			}

			List<Node> neighbours = findNeighbours(current);
			for (Node neighbour : neighbours) {
//				System.out.println("Looking up Neighbour: " + neighbour.getX() + " | " + neighbour.getY());
				if (neighbour.getType() == C.AIR || Utils.containsNode(neighbour, closedList)) {
					continue;
				}

				neighbour.setG(current.getG() + Utils.manhattanDistance(current, neighbour));
				neighbour.setH(Utils.manhattanDistance(neighbour, end));

				if (!Utils.containsNodeWithLowerF(neighbour, openQueue) || !Utils.containsNode(neighbour, openQueue)) {
					if (!Utils.containsNode(neighbour, openQueue)) {
						openQueue.add(neighbour);
					}
				}
			}
		}

		return path;
	}

	private List<Node> findNeighbours(Node current) {
		List<Node> neighbours = new ArrayList<>();

		int northX = current.getX();
		int northY = current.getY() + 1;
		Node north = new Node(northX, northY, C.SOLID);

		int westX = current.getX() + 1;
		int westY = current.getY();
		Node west = new Node(westX, westY, C.SOLID);

		int southX = current.getX();
		int southY = current.getY() - 1;
		Node south = new Node(southX, southY, C.SOLID);

		int eastX = current.getX() - 1;
		int eastY = current.getY();
		Node east = new Node(eastX, eastY, C.SOLID);

		graph.forEach(n -> {
			if (Utils.isSame(n, north) || Utils.isSame(n, west) || Utils.isSame(n, east) || Utils.isSame(n, south)) {
				Node neighbour = new Node(n);
				neighbour.setParent(current);
				neighbours.add(neighbour);
			}
		});
		return neighbours;
	}

	private List<Node> reconstructPath(Node current) {
		List<Node> path = new ArrayList<Node>();
		while (current != null) {
			path.add(current);
			current = current.getParent();
		}

		return path;
	}

}
