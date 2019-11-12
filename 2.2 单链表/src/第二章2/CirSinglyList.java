package 第二章2;

public class CirSinglyList<T>
{
	public Node<T> head;
	public CirSinglyList()
	{
		this.head = new Node<T>();
		this.head.next = this.head;
	}
	public boolean isEmpty()
	{
		return this.head.next == this.head;
	}
	
	public String toString()
	{
		String str = this.getClass().getName()+"(";           //返回类名          
		for(Node<T>p = this.head.next;p!=this.head;p = p.next)
		{         
			str += p.data.toString();
			if (p.next!=null)
				str += ",";                                       //不是最后一个结点时，加分隔符
		}
		return str+")";                                       //空表返回（）
	}

}
