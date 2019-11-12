package 第二章2;


//单链表类，实现ADTList<T>声明的方法，T表示数据元素的数据类型
public class SinglyList<T> extends Object
{
	public Node<T>head;                                   //头指针，指向单链表的头结束
	
	//（1） 构造方法
	public SinglyList()                                  //构造空单链表
	{          
		this.head = new Node<T>();                       //创建头结点，data和next值均为null
	}
	
	public SinglyList(T[] values)                        //构造单链表，由values数组提供元素
	{
		this();                                           //创建空单链表，只有头结点
		Node<T>rear = this.head;                            //rear指向单链表最后一个结点
		for (int i = 0;i<values.length;i++)              //若values.length == 0，构造空链表
		{
			rear.next = new Node<T>(values[i],null);          //尾插入，创建结点链入rear结点之后
			rear = rear.next;                             //rear指向新的链尾结点
		}
	}
	
	public SinglyList(SinglyList<T>list)                            //深拷贝
	{    
		this();                                                     //创建空单链表
		Node<T> rear = this.head;
		for (Node<T> p =list.head.next;p!=null;p = p.next)
		{
			rear.next = new Node<T>(p.data,null);
			rear = rear.next;                                       //指向this单链表尾
		}
	}
	
	public boolean isEmpty()                               //判断单链表是否空，O(1)
	{
		return this.head.next == null;
	}
	
	//（1）存取
	public T get(int i)                              //返回第i个元素，0<=i<表长度。若i越界，则返回null.O(n)
	{
		Node<T> p = this.head.next;
		for(int j = 0;p!=null && j<i;j++)                    //遍历单链表，寻找第i个结点（p指向）
			p = p.next;
		return (i>=0&&p!=null)?p.data:null;                 //若p指向第i个结点，返回其元素值
	}
	
//	public void set(int i,T x)                   //设置第i个元素为x，0<=i<表长度，x！=null。方法体省略
//	public int size()                                   //返回单链表长度，O(n)。方法体省略
	
	//返回单链表所有元素的描述字符串，形式为"(,)"/覆盖Object类的toString()方法，O(n)
	public String toString()
	{
		String str = this.getClass().getName()+"(";           //返回类名
		for (Node<T>p = this.head.next;p != null;p = p.next)          //遍历单链表
		{
			str += p.data.toString();
			if (p.next!=null)
				str += ",";                                       //不是最后一个结点时，加分隔符
		}
		return str+")";                                       //空表返回（）
	}
	
	//（3）插入
	//插入x作为第i个元素，x！=null，返回插入结点
	//对序号i采取容错措施，若i<0,则插入x在最前面；若i>n，则插入x在最后。O(n)
	public Node<T>insert(int i,T x)
	{
		if(x == null)
			throw new NullPointerException("x == null");               //抛出空对象异常
		Node<T>front = this.head;                                     //front指向头结点   
		for (int j = 0;front.next!=null&&j<i;j++)                       //寻找第i-1个或最后一个结点(front指向)
			front = front.next;
		front.next = new Node<T>(x,front.next);                        //在front之后插入值为x的结点
		return front.next;                                                //返回插入结点
	}
	
	public Node<T> insert(T x)                                           //在单链表最后添加x对象，O(n)
	{
		//调用insert(i,x),用整数最大值指定插入在最后，遍历一遍，i必须容错
		return insert(Integer.MAX_VALUE,x);
	}
	
	//（4）删除
	public T remove(int i)                                             //删除第i个元素，0<=i<n，返回被删除元素；若i越界，则返回null。O(n)
	{
		Node<T>front = this.head;                                     //front指向头结点
		for(int j = 0;front.next!=null&&j<i;j++)                        //遍历寻找第i-1结点（front指向）
			front = front.next;
		if (i>=0&&front.next!=null)                                     //若front的后继结点存在，则删除之
		{
			T old = front.next.data;                                    //获得待删除结点引用的对象
			front.next = front.next.next;                                 //删除front的后继，包括头删除，中间/尾删除
			                                                              //由Java虚拟机稍后释放结点占用的存储单元
			return old;                                                
		}
		return null;                                                     //若i<0或i>表长
	}
	
	public void clear()                                                  //删除单链表所有元素
	{
		this.head.next = null;                                             //Java自动收回所有结点占用的空间
	}
	
    //比较相等
	public boolean equals(Object obj)
	{
		if(obj == this)
			return true;
		if (!(obj instanceof SinglyList<?>))
			return false;
		Node<T> p = this.head.next;
		Node<T> q = ((SinglyList<T>)obj).head.next;
		while(p!=null&&q!=null&&p.data.equals(q.data))
		{
			p = p.next;
			q = q.next;
		}
		return p == null&& q == null;
	}

//	public int compareTo(SinglyList<T> list)
//	{
//		int result ;
//		if (this.equals(list))
//			result = 0;
//		else
//			if(this>list.data)
//				result = -1;
//	}
	
	//在this单链表之后连接list单链表；设置list为空；集合并
	public void concat(SinglyList<T> list)
	{
		Node<T>rear = this.head;
		while (rear.next!=null)
			rear = rear.next;
		rear.next = list.head.next;
		list.head.next = null;
	}
	
	//深拷贝list，不改变list
	public void addAll(SinglyList<T> list)
	{
		this.concat(new SinglyList<T>(list));
	}
	
		
	//返回复制this和list合并连接的单链表，并集，不改变this和list
	public SinglyList<T> union(SinglyList<T>list)
	{
		SinglyList<T> result = new SinglyList<T>(this);
		result.addAll(list);
		return result;
	}
	

	public SinglyList<T> addAll(int i,SinglyList<T> list)
	{
		SinglyList<T> result = new SinglyList<T>(this);
		SinglyList<T> link = new SinglyList<T>(list);

		if(i<0)
		{
			Node<T>rear = link.head;
			while (rear.next!=null)
				rear = rear.next;
			rear.next = result.head.next;
			result.head = link.head;
		}
		else
		{
			Node<T>rear = result.head;
			for(int j = 0;j<i;j++)
				rear = rear.next;
			if(rear.next == null)                  //判断i是否大于长度，是则尾插入
			{
				result.concat(link);
			}
			else
			{
				Node<T> p = link.head;
				while(p.next != null)
					p = p.next;
				p.next = rear.next;
				rear.next = link.head.next;
			}
		}
		return result;
	}
	
//	（5）顺序查找和基于查找算法的操作。
	public Node<T> search(T key)                                         //查找返回首个与key相等元素结点，查找不成功返回null
	{
		//for(Node<T> p = this.head.next;p!=null&&(key.comparaTo(p.data)==0))
		for(Node<T> p = this.head.next;p!=null && (key.equals(p.data));p=p.next)
		{
//			//if (key.compareTo(p.data)==0)
			if (key.equals(p.data))
				return p;
		}
		return null;
	}
//	public boolean contains(T key)                                       //判断是否包含关键字为key元素
	public Node<T> insertDifferent(T x)                                   //插入不重复元素，查找不成功时尾插入
	{
		Node<T>front = this.head,p = front.next;
		//while(p!=null&&x.compareTo(p.data)>0)
		while(p!=null&&x.equals(p.data))
		{
			front = p;
			p = p.next;
		}
		//if(p!=null&&x.compareTo(p.data)==0)
		if(p!=null&&x.equals(p.data))
			return null;
		front.next = new Node<T>(x,p);
		return front.next;
	}
	public T remove (T key)                                               //删除首个与key相等元素，返回被删除元素；查找不成功返回null
	{
		Node<T> front = this.head,p = front.next;
		//while(p!= null&&key.compareTo(p.data)==0)
		while(p!= null&&key.equals(p.data))
		{
			front.next = p.next;
			return p.data;
		}
		return null;
	}
	
	public static void main(String[] args)
	{
//		Node p = new Node(10, null);
//		System.out.println(p.data.equals(10));
//		System.out.println(p.data.equals(13));
		
		String[] values1 = {"S","R","T","S","E"};
		String[] values2 = {"K","T","S","U","D"};
		SinglyList<String>lista = new SinglyList<String>(values1);
		SinglyList<String>listb = new SinglyList<String>(lista);
		SinglyList<String>listc = new SinglyList<String>(values2);

		System.out.println(lista.equals(listb));
		System.out.println(lista.equals(listc));
		
		System.out.println(lista.union(listb));
		System.out.println(lista.union(listc));
		
		System.out.println(lista.addAll(3,listc));
	}
}
