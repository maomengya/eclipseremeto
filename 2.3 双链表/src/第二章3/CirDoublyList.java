package �ڶ���3;
//ѭ��˫�����࣬ʵ��ADTList<T>�����ķ�����T��ʾ����Ԫ�ص���������
public class CirDoublyList<T>
{
	public DoubleNode<T> head;                               //ͷָ��
	
	public CirDoublyList()                                      //�����ѭ��˫����
	{
		this.head = new DoubleNode<T>();                        //����ͷ�ڵ㣬3����ֵ��Ϊnull
		this.head.prev = this.head;
		this.head.next = this.head;
	}
	
	public CirDoublyList(T[] values)                              //����ѭ��˫����
	{
		this();                                                   //������ѭ��˫����
		DoubleNode<T> rear = this.head;
		for(int i = 0;i<values.length;i++)
		{
			rear.next = new DoubleNode<T>(values[i],rear,this.head);             //β����
			rear = rear.next;
		}
		this.head.prev = rear;
	}
	
	public CirDoublyList(CirDoublyList<T> list)                          //������췽��
	{
		this();                                                       //������ѭ��˫����
		DoubleNode<T> rear = this.head;
		for (DoubleNode<T> p = list.head.next;p!=list.head;p = p.next)
		{
			rear.next = new DoubleNode<T>(p.data,rear,this.head);
			rear = rear.next;
		}
		this.head.prev = rear;
	}
	
	public boolean isEmpty()                                   //�ж�ѭ��˫�����Ƿ��
	{
		return this.head.next == this.head;
	}
	//����xΪ��i��Ԫ�أ�x��=null�����ؽ�㡣��i�ݴ���i<0����ͷ���룻��i>����n����β����
	public DoubleNode<T>insert(int i,T x)
	{
		if(x == null)
			throw new NullPointerException("x == null");           //�׳��ն����쳣
		DoubleNode<T> front = this.head;                            //frontָ��ͷ���
		for(int j = 0;front.next!= this.head&&j<i;j++)                   //Ѱ�ҵ�i-1�������һ�����(frontָ��)
			front = front.next;
		DoubleNode<T> q = new DoubleNode<T>(x,front,front.next);                 //��front֮�����x���
		front.next.prev = q;
		front.next = q;
		return q;                                                    //���ز����
	}
	public DoubleNode<T>insert(T x)                                   //β����xԪ�أ�����x��㡣�㷨�ڿ�ͷ���֮ǰ���룬O(1)
	{
		if(x==null)
			throw new NullPointerException("x == null");               //���ؿն����쳣
		DoubleNode<T>q = new DoubleNode<T>(x,head.prev,head);
		head.prev.next = q;                                            //��ͷ���֮ǰ���룬��β����
		head.prev = q;
		return q;
	}
	
	public T get(int i)                            //���ص�i��Ԫ�أ�0��i<���ȡ���iԽ�磬����null��O(n)
    {
        DoubleNode<T> p=this.head.next;
        for (int j=0; p!=null && j<i; j++)
            p = p.next;
        return (i>=0 && p!=null) ? p.data : null;          //��pָ���i����㣬������Ԫ��ֵ
    }
	//���õ�i��Ԫ��Ϊx��0<=i<���ȡ���iԽ�磬�׳����Խ���쳣��
	public void set(int i,T x)
	{
		if (x == null)
			throw new NullPointerException("x == null");                          //�׳��ն����쳣
		DoubleNode<T> p = this.head.next;
		for(int j = 0;p!=null&&j<i;j++)
			p = p.next;
		if(i>=0&&p!=null)
			p.data = x;                                                          //pָ���i�����
		else throw new IndexOutOfBoundsException(i+"");                          //�׳����Խ���쳣
	}
	
	public int size()                                                 //����ѭ��˫������
	{
		int i=0;
		for(DoubleNode<T> p = this.head.next;p!=this.head;p=p.next)
			i++;
		return i;
	}
	
	public String toString()                                           //����ѭ��˫��������Ԫ�ص������ַ���
	{
		String str = this.getClass().getName()+"(";                       //��������
		for (DoubleNode<T> p = this.head.next;p!=this.head;p=p.next)
		{
			str += p.data.toString();
			if(p.next!=this.head)
				str +=",";
		}
		return str+")";                                                    //�ձ���()
	}
	
	public void print()
	{
		System.out.print("(");
		for(DoubleNode<T> p = this.head.next;p!=this.head;p = p.next)
		{
			System.out.print(p.data.toString());
			if(p.next!=this.head)
				System.out.print(",");
		}
		System.out.println(")");
	}
	//�Ƚ�����ѭ��˫�����Ƿ���ȣ�����Object���equals(obj)����
	public boolean equals(Object obj)
	{
		if(obj == this)
			return true;
		if(!(obj instanceof CirDoublyList<?>))
			return false;
		DoubleNode<T> p = this.head.next;
		CirDoublyList<T> list = (CirDoublyList<T>)obj;
		DoubleNode<T> q = list.head.next;
		while (p!=head&&q!=list.head&&p.data.equals(q.data))
		{
			p = p.next;
			q = q.next;
		}
		return p == head&&q == list.head;
	}
	
	//��thisѭ��˫����֮�󣬺ϲ�����list�����н�㣬������listΪ��
	public void addAll(CirDoublyList<T> list)
	{
		DoubleNode<T> rear = head.prev;
		rear.next = list.head.next;
		list.head.next.prev = rear;
		rear = list.head.prev;
		rear.next = this.head;
		this.head.prev = rear;
		list.head.prev = list.head;
		list.head.next = list.head;
	}
	
	//ɾ����i��Ԫ�أ����ر�ɾ��Ԫ�أ�0<=i<n,��iԽ�磬��ɾ��������null
	public T remove(int i)
	{
		DoubleNode<T> p = this.head.next;
		for(int j = 0;p!=head&&j<i;j++)                         //����ѭ��
			p = p.next;
		if(p!=head&&i>=0)
		{
			p.prev.next = p.next;                                //ɾ��p���
			p.next.prev = p.prev;
			return p.data;                                    //����p���
		}
		return null;                                        //��i<0����Խ�磬����null
	}
	
	public void clear()                                    //ɾ��ѭ��˫��������Ԫ��
	{
		this.head.prev = head;
        this.head.next = head;
	}
	
	
//	public Sring toPreviousString()                                     //��������Ԫ�ص������ַ�����Ԫ�ش���Ӻ���ǰ��
//	public T removeLast()                                            //ɾ�����һ��Ԫ�أ����ر�ɾ��Ԫ�ء�������գ�����null
																	  //�㷨ɾ��ͷ����ǰ����㣬O(1)
} 
