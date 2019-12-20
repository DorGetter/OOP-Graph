package JUNIT;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import elements.NodeV;

class DGraphTest {
	
	DGraph g;
	
	@BeforeEach
	 void BeforeAll() {
		
		g = new DGraph();
		for (int i = 0, j=1; i < 20; i+=2) {
			g.addNode(new NodeV(i,j,1));
			j*=2;
		}
		for (int i = 0, j=1; i < 20; i+=2) {
			if(i%2 != 0) {
				g.connect(i, i-1,i);
			}
		}
		
	}

	@Test
	void testGetNode() {
		
		for (int i = 0; i < 20; i++) {
			System.out.println(g.getNode(i).getInfo());
		}
		
	}

	@Test
	void testGetEdge() {
		fail("Not yet implemented");
	}

	@Test
	void testAddNode() {
		fail("Not yet implemented");
	}

	@Test
	void testConnect() {
		fail("Not yet implemented");
	}

	@Test
	void testGetV() {
		fail("Not yet implemented");
	}

	@Test
	void testGetE() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveNode() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveEdge() {
		fail("Not yet implemented");
	}

	@Test
	void testNodeSize() {
		fail("Not yet implemented");
	}

	@Test
	void testEdgeSize() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMC() {
		fail("Not yet implemented");
	}

}
