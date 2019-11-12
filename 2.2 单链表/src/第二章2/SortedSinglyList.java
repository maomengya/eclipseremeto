package 第二章2;

//排序单链表（升序），T或T的某个祖先类实现Comparable<T>接口；继承单链表类
public class SortedSinglyList <T extends Comparable<?super T>>extends SinglyList<T>
{
//	public Node<T>head;  
	public SortedSinglyList()                                 //构造空排序单链表
	{
		super();                                               //默认调用父类构造方法SinglyList()
	}
	public SortedSinglyList(T[] values)                        //构造，将values数组中所有对象按值插入
	{
		super();                                                 //创建空单链表，默认调用SlinglyList()
		for(int i = 0;i<values.length;i++)
			this.insert(values[i]);                               //排序单链表按值插入
	}
	
//	public SortedSinglyList(SortedSinglyList<T> list)             //深拷贝构造方法
//	{
//		super(list);                                              //调用父类同参数的构造方法
//	}
	
	public SortedSinglyList(SinglyList<T>list)                    //重载深拷贝，由单链表构造排序单链表
	{
		super();                                                  //创建空单链表
		for(Node<T>p = list.head.next;p!=null;p=p.next)
			this.insert(p.data);                                  //排序单链表按值插入
	}
	//不支持父类的以下两个方法，将其覆盖并抛出异常。方法体同排序顺序表
//	public void set(int i,T x)
//	public Node<T> insert(int i,T x)
	
//	 public void set(int i, T x) 
//	 {
//	    throw new UnsupportedOperationException("set(int i, T x)");
//	 }
//	 public Node<T> insert(int i, T x) 
//	 {
//	    throw new UnsupportedOperationException("insert(int i, T x)");
//	 }
	 

		//插入x，x！=null，根据x对象大小顺序查找确定插入位置，插入在等值结点之前
		//返回插入结点。O(n).覆盖父类的insert(x)方法
		public Node<T> insert(T x)
		{
			Node<T>front = this.head,p = front.next;                 //front指向p的前驱结点
			while (p!=null&&x.compareTo(p.data)>0)                           //寻找插入位置
			{
				front = p;
				p = p.next;
			}
			front.next = new Node<T>(x,p);                            //在front之后，p之前插入值为x结点
			return front.next;                                        //返回插入结点
		}
	
	//以下顺序查找和基于查找算法的操作，都覆盖父类的同名方法
	//调用compareTo()方法比较大小和相等。方法体省略
//	public Node<T> search(T key)                                     //查找返回首个与key相等元素结点，查找不成功返回null
//	public Node<T> insertDifferent(T x)                              //插入不重复元素，查找不成功时按值插入
//	public T remove(T key)                                          //删除首个与key相等元素，返回被删除元素；查找不成功返回null

		 
		//顺序查找首个与key相等元素，返回结点，若查找不成功返回null，O(n)。覆盖。    //用于5.2.2节稀疏矩阵行的单链表
//		public Node<T> search(T key) 
//		{
//		   for (Node<T> p=this.head.next;  p!=null && key.compareTo(p.data)>=0;  p=p.next)
//			{
////		    System.out.print(p.data+"？");
//			    if (key.compareTo(p.data)==0)                  //由compareTo()提供比较对象大小和相等的依据
//			        return p;
//			}
////		 System.out.println();
//		 return null; 
//		 }
		    
		 public Node<T> insertDifferent(T x)
		 {
		     Node<T> front=this.head, p=front.next;             //front指向p的前驱结点
		     while (p!=null && x.compareTo(p.data)>0)           //寻找插入位置
		     {
		         front = p;
		         p = p.next;
		     }
		     if (p!=null && x.compareTo(p.data)==0)
		        return p;                                      //查找成功，元素重复，不插入，返回该结点
		     return front.next = new Node<T>(x, p);             //在front之后、p之前插入值为x结点，返回插入结点
		 }
		    
	public void concat(SinglyList<T> list)
	{
		throw new UnsupportedOperationException("concat(SinglyList<T>....");
	}
	//将list中所有元素插入到this单链表，不改变list，集合并。
	//覆盖，功能与父类相同，算法实现不同，每次查找全部元素后再插入，O(n)
	public void addAll(SinglyList<T> list)
	{
		for (Node<T> p = list.head.next;p!= null;p=p.next)              //直接插入排序单链表
			this.insert(p.data);
		
//		SinglyList<T>front = this.head,p = front.next;
	}
	public SortedSinglyList<T> union(SinglyList<T> list)
	{
		SortedSinglyList<T> result = new SortedSinglyList<T>(this);
		result.addAll(list);
		return result;
	}
	
	//平均值average
//	public String average(SortedSinglyList<Integer>list)
//	{
//		
//	}
}
