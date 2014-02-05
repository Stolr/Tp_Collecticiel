package stolr.graphstream.test;

import java.util.Iterator;
import java.util.Scanner;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

class ColorationThread extends Thread {

	public void run(Graph graph, Node sBegin, String tabNode) {

		
		tabNode+=  ";;;"+sBegin.getId();
		
		System.out.println("Salut");
		try {
			this.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		Iterator<? extends Node> nodes = sBegin.getNeighborNodeIterator();
		

		System.out.println(" Node en cours " + sBegin.getId());
		while (nodes.hasNext()) {
			i++;
			Node ntemp= nodes.next();
			try {
				graph.getEdge(sBegin.getId() + "+" + ntemp.getId())
						.setAttribute("ui.class", "rum");
			} catch (Exception e) {
				try {
					graph.getEdge(nodes.next().getId() + "+" + sBegin.getId())
							.setAttribute("ui.class", "rum");

				} catch (Exception e2) {

					System.out.println(" null pointer exception");
					System.out.println(" Next = "+ ntemp.getId());	
					Graph temp = graph;
					
				}

			}
		}

		nodes = sBegin.getNeighborNodeIterator();
		while (nodes.hasNext()) {
			
			if (!( tabNode.contains(nodes.next().getId())))
			{
			ColorationThread trc = new ColorationThread();
			trc.run(graph, nodes.next(),tabNode);
			}
		}

	}

}