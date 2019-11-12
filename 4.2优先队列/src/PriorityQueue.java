import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import 第二章3.SortedCirDoublyList;

public final class PriorityQueue<T extends Comparable<? super T>> implements Queue<T>
{
	private SortedCirDoublyList<T> list;
	private boolean asc;
	public PriorityQueue(boolean asc)
	{
		this.list = new SortedCirDoublyList<T>();
		this.asc = asc;
	}
	
	public PriorityQueue()
	{
		this(true);
	}
	
	public boolean isEmpty()
	{
		return this.list.isEmpty();
	}
	
	public boolean add(T x)
	{
		return this.list.insert(x)!=null;
	}
	
	public T peek()
	{
		return this.asc?this.list.get(0):this.list.head.prev.data;
	}
	
	public T poll()
	{
		return this.asc?this.list.remove(0):this.list.removeLast();
	}
	
	public String toString()
	{
		return this.getClass().getName()+""+(this.asc?this.toString():this.list.toPreviousString());
	}
	
	

	@Override
	public int size() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public boolean contains(Object o) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public void clear() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public boolean offer(T e) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public T remove() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public T element() {
		// TODO 自动生成的方法存根
		return null;
	}

}
