package Data_Structures.Structures.InDevelopment.Heaps;

import java.util.Iterator;

import Data_Structures.ADTs.Heap;

import Data_Structures.Structures.Data_Structure;
import Data_Structures.Structures.UBA;

/*
 * Implements a standard heap implementation of Heaps using arrays.
 * 
 * Should be written by Bryce Summers on 5 - 23 - 2014.
 * 
 * Note : This is a MIN heap.
 * 
 * Heap property :	For every element in the tree,
 *  				it is less than or equal to its left and right children in the tree as defined by the elements at the indexes associated by
 *  				the relationships index_left(index) and index_right(index).
 *  
 * Root node is always at index 0.
 * 
 * Left biased, when equal keys are present, the one on the left will be chosen.
 * 
 * Allows for duplicate keys.
 * 
 * Binary tree invariants :
 * The heap is represented by a binary tree that is encoded by index relationships within an unbounded array.
 * We maintain the UBA with a minimality of nodes, so the UBA will only contain N elements, when size = n.
 * 
 * The heap is as balanced as possible. This causes their to be a preference for left children over right children.
 * 
 * FIXME : I will need to work to preserve key stability, so that all keys will eventually be deleted, even if all keys entered are equal.
 */

public class ArrayHeap<E extends Comparable<E>> extends Data_Structure<E> implements Heap<E>
{
	// -- Private Data.
	UBA<E> data;

	public ArrayHeap()
	{
		data = new UBA<E>();
	}
	
	public ArrayHeap(int initial_size)
	{
		data = new UBA<E>(initial_size);
	}
	
	public ArrayHeap(UBA<E> data_in)
	{
		data = data_in.clone();
		heapify(0);
	}

	// -- Public interface functions.
	
	public int size()
	{
		return data.size();
	}
	
	public boolean isEmpty()
	{
		return data.isEmpty();
	}
	
	public void add(E elem)
	{
		int len = data.size();
		data.add(elem);
		heapify_up(len);
	}
	
	public E peek_dominating()
	{
		return data.get(0);
	}
	
	// O(log(n)) deletes and returns the minimum element.
	public E extract_dominating()
	{
		// Trivial 1 element heap.
		if(data.size() == 1)
		{
			return data.rem();
		}
		
		// Extract the minimum element.
		E output = data.get(0);
				
		// Maintain the minimum binary tree invariants.
		E last = data.rem();
		data.set(0, last);
		heapify_linear(0);
		
		return output;
	}
	
	// -- Data_structure interface functions.
	public UBA<E> toUBA()
	{
		return data.clone();		
	}
	
	// -- Private functions.
	
	// Heapifies all of the nodes of the Tree with a root at the given index.
	// Builds the heap invariant downwards to all sub trees.
	// O(n), checks each node in the tree once.
	private void heapify(int index)
	{
		int ileft = index_left(index);
		int iright = index_right(index);
		int len = data.size();
		
		if(ileft < len)
		{
			heapify(ileft);
			min_first(index, ileft);
		}
		
		if(iright < len)
		{
			heapify(iright);
			min_first(index, iright);
		}

	}
	
	// Given an index, swaps the node down the tree while maintaining the min
	// heap invariant until the node is in an invariant correct place.
	// O(log(n)). Non recursive, so has O(1) function calls.
	private void heapify_linear(int index)
	{
		int size   = data.size();
		int ileft  = index_left(index);
		int iright = index_right(index);

		E elem = data.get(index);
		
		// While the node has at least 1 child.
		while(ileft < size)
		{
			E e_left  = data.get(ileft);
			E e_right = null;
			
			if(iright < size)
			{
				e_right = data.get(iright);
			}
			
			boolean left_less = e_right == null || e_left.compareTo(e_right) <= 0;
			
			// Go left if appropriate.
			if(e_left.compareTo(elem) <= 0 && left_less)// Equal keys also get sifted down, to preserve some key stability.
				
			{
				data.swap(ileft, index);
				index = ileft;
				ileft  = index_left(index);
				iright = index_right(index);
				continue;
			}
			
			// Stop if there is no right child.
			if(e_right == null)
			{
				break;
			}
			
			if(e_right.compareTo(elem) <= 0)
			{
				data.swap(iright, index);
				index = iright;
				ileft  = index_left(index);
				iright = index_right(index);
				continue;
			}
			
			// The heap invariants are held.
			return;
		}// End of while loop.
		
	}
	
	// Builds the heap invariant going up the tree from a given child node.
	private void heapify_up(int index)
	{
		// Root node is always at index 0.
		if(index > 0)
		{
			int parent_index = index_parent(index);
			min_first(parent_index, index);
			heapify_up(parent_index);
		}		
		
	}
	
	// -- Array tree transversing functions.
	
	private int index_parent(int index)
	{
		return (index-1)/2;
	}
	
	private int index_left(int index)
	{
		return 2*index + 1;
	}
	
	private int index_right(int index)
	{
		return 2*index + 2;
	}

	// REQUIRES : index1 < index2.
	// Performs a swap to fix heap invariant errors for the elements at the given indices.
	private void min_first(int index1, int index2)
	{
		E elem1 = data.get(index1);
		E elem2 = data.get(index2);
		
		if(elem1.compareTo(elem2) > 0)
		{
			data.swap(index1, index2);
		}
	}

	@Override
	public Iterator<E> iterator()
	{
		return data.iterator();
	}

	@Override
	public String toString()
	{
		StringBuilder output = new StringBuilder();
		output.append("\nMinHeap[");
		
		for(E elem : this)
		{
			output.append(elem);
			output.append(",\n");
		}
		
		output.append("]");
		
		return output.toString();
	}

	@Override
	public ArrayHeap<E> clone()
	{
		return new ArrayHeap<E>(data);
	}
	
	// Returns a UBA that is sorted from least to greatest.
	public UBA<E> toSortedUBA()
	{
		int len = data.size();
		UBA<E> output = new UBA<E>(len);
		
		ArrayHeap<E> heap = clone();
		
		for(int i = 0; i < len; i++)
		{
			output.set(i, heap.extract_dominating());
		}
		
		return output;
	}

	
}