package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.TreeSet;


public class TreeSetTest extends SortedSetTest {
	@BeforeEach
	@Override
	void setUp() {
		collection = new TreeSet<>(); 
		super.setUp();
	}
}