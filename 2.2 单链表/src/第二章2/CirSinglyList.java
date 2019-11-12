package �ڶ���2;

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
		String str = this.getClass().getName()+"(";           //��������          
		for(Node<T>p = this.head.next;p!=this.head;p = p.next)
		{         
			str += p.data.toString();
			if (p.next!=null)
				str += ",";                                       //�������һ�����ʱ���ӷָ���
		}
		return str+")";                                       //�ձ��أ���
	}

}
