
public class Process implements Comparable<Process>
{
	private String name;
	private int priority;
	public Process(String nema,int priority)
	{
		this.name = name;
		if(priority>=1&&priority<=10)
			this.priority = priority;
		else throw new IllegalArgumentException("priority="+priority);
	}
	
	public Process(String name)
	{
		this(name,5);
	}
	
	public String toString()
	{
		return "("+this.name+","+this.priority+")";
	}
	
	public int compareTo(Process p)
	{
		return this.priority-p.priority;
	}

	
	public static void main(String args[])
	{
		Process process[] = {new Process("A",4),new Process("B",3),new Process("C"),
						   	 new Process("D",4),new Process("E",10),new Process("F",1)};
		PriorityQueue<Process>que = new PriorityQueue<Process>(false);
		System.out.print("入队进程：");
		for(int i = 0;i<process.length;i++)
		{
			que.add(process[i]);
			System.out.print(process[i]+"");
		}
		System.out.print("\n出队进程：");
		while(!que.isEmpty())
			System.out.print(que.poll().toString()+"");
		System.out.println();
	}
}
