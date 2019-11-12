package 第二章3;
//循环双链表类，实现ADTList<T>声明的方法，T表示数据元素的数据类型
public class CirDoublyList<T>
{
	public DoubleNode<T> head;                               //头指针
	
	public CirDoublyList()                                      //构造空循环双链表
	{
		this.head = new DoubleNode<T>();                        //创建头节点，3个域值均为null
		this.head.prev = this.head;
		this.head.next = this.head;
	}
	
	public CirDoublyList(T[] values)                              //构造循环双链表
	{
		this();                                                   //创建空循环双链表
		DoubleNode<T> rear = this.head;
		for(int i = 0;i<values.length;i++)
		{
			rear.next = new DoubleNode<T>(values[i],rear,this.head);             //尾插入
			rear = rear.next;
		}
		this.head.prev = rear;
	}
	
	public CirDoublyList(CirDoublyList<T> list)                          //深拷贝构造方法
	{
		this();                                                       //创建空循环双链表
		DoubleNode<T> rear = this.head;
		for (DoubleNode<T> p = list.head.next;p!=list.head;p = p.next)
		{
			rear.next = new DoubleNode<T>(p.data,rear,this.head);
			rear = rear.next;
		}
		this.head.prev = rear;
	}
	
	public boolean isEmpty()                                   //判断循环双链表是否空
	{
		return this.head.next == this.head;
	}
	//插入x为第i个元素，x！=null，返回结点。对i容错，若i<0，则头插入；若i>长度n，则尾插入
	public DoubleNode<T>insert(int i,T x)
	{
		if(x == null)
			throw new NullPointerException("x == null");           //抛出空对象异常
		DoubleNode<T> front = this.head;                            //front指向头结点
		for(int j = 0;front.next!= this.head&&j<i;j++)                   //寻找第i-1个或最后一个结点(front指向)
			front = front.next;
		DoubleNode<T> q = new DoubleNode<T>(x,front,front.next);                 //在front之后插入x结点
		front.next.prev = q;
		front.next = q;
		return q;                                                    //返回插入点
	}
	public DoubleNode<T>insert(T x)                                   //尾插入x元素，返回x结点。算法在开头结点之前插入，O(1)
	{
		if(x==null)
			throw new NullPointerException("x == null");               //返回空对象异常
		DoubleNode<T>q = new DoubleNode<T>(x,head.prev,head);
		head.prev.next = q;                                            //在头结点之前插入，即尾插入
		head.prev = q;
		return q;
	}
	
	public T get(int i)                            //返回第i个元素，0≤i<长度。若i越界，返回null。O(n)
    {
        DoubleNode<T> p=this.head.next;
        for (int j=0; p!=null && j<i; j++)
            p = p.next;
        return (i>=0 && p!=null) ? p.data : null;          //若p指向第i个结点，返回其元素值
    }
	//设置第i个元素为x，0<=i<长度。若i越界，抛出序号越界异常。
	public void set(int i,T x)
	{
		if (x == null)
			throw new NullPointerException("x == null");                          //抛出空对象异常
		DoubleNode<T> p = this.head.next;
		for(int j = 0;p!=null&&j<i;j++)
			p = p.next;
		if(i>=0&&p!=null)
			p.data = x;                                                          //p指向第i个结点
		else throw new IndexOutOfBoundsException(i+"");                          //抛出序号越界异常
	}
	
	public int size()                                                 //返回循环双链表长度
	{
		int i=0;
		for(DoubleNode<T> p = this.head.next;p!=this.head;p=p.next)
			i++;
		return i;
	}
	
	public String toString()                                           //返回循环双链表所有元素的描述字符串
	{
		String str = this.getClass().getName()+"(";                       //返回类名
		for (DoubleNode<T> p = this.head.next;p!=this.head;p=p.next)
		{
			str += p.data.toString();
			if(p.next!=this.head)
				str +=",";
		}
		return str+")";                                                    //空表返回()
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
	//比较两条循环双链表是否相等，覆盖Object类的equals(obj)方法
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
	
	//在this循环双链表之后，合并连接list中所有结点，并设置list为空
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
	
	//删除第i个元素，返回被删除元素；0<=i<n,若i越界，不删除，返回null
	public T remove(int i)
	{
		DoubleNode<T> p = this.head.next;
		for(int j = 0;p!=head&&j<i;j++)                         //遍历循环
			p = p.next;
		if(p!=head&&i>=0)
		{
			p.prev.next = p.next;                                //删除p结点
			p.next.prev = p.prev;
			return p.data;                                    //返回p结点
		}
		return null;                                        //当i<0或者越界，返回null
	}
	
	public void clear()                                    //删除循环双链表所有元素
	{
		this.head.prev = head;
        this.head.next = head;
	}
	
	
//	public Sring toPreviousString()                                     //返回所有元素的描述字符串（元素次序从后向前）
//	public T removeLast()                                            //删除最后一个元素，返回被删除元素。若链表空，返回null
																	  //算法删除头结点的前驱结点，O(1)
} 
