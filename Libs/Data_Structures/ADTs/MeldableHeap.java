package Data_Structures.ADTs;

/*
 * Abstract Data Type Specification for Meldable Heaps.
 * 
 * Written by Bryce Summers on 12 - 20 - 2013.
 * 
 * Purpose : MeldableHeaps are Heaps that support a merging operation. 
 */

public interface MeldableHeap<E, T extends MeldableHeap<E, T>> extends PriorityQueue<E, T>
{
	
	// Merges two Meldable Heaps together.
	public MeldableHeap<E, T> merge(T other);
	
}
