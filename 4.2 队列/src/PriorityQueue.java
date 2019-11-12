import ตฺถþีย3.SortedCirDoublyList;

public final class PriorityQueue<T extends Comparable<?super T>> implements Queue<T>
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
		return this.getClass().getName()+" "+(this.asc?this.list.toString():this.list.toPreviousString());
	}
}
