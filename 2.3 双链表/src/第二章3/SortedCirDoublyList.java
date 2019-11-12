package 第二章3;
//排序循环双链表（升序），根据x对象大小顺序查找确定插入位置，插入在等值结点之前
//返回插入结点.O(n).覆盖父类的insert(x)方法
public class SortedCirDoublyList<T extends Comparable<?super T>> extends CirDoublyList<T> 
{
	//构造方法，参数和方法体同排序单链表
	public SortedCirDoublyList()
	{
		super();                                                  //默认构造方法
	}
	
	public SortedCirDoublyList(T[] values)
	{
		super();
		for(int i = 0; i<values.length;i++)
			this.insert(values[i]);                            //按值插入
	}
	
	public SortedCirDoublyList(SortedCirDoublyList<T> list)
	{
		super(list);
	}
	
	//由单链表list构造排序循环双链表，重载深拷贝构造方法。直接插入。
//	public SortedCirDoublyList(SinglyList<T> list)
//	{
//		super();
//		for(Node<T> p = list.head.next;p!=list.head;p = p.next)
//			this.insert(p.data);
//	}
	
	public void set(int i,T x)
	{
		throw new UnsupportedOperationException("set(int i,T x");
	}

    public DoubleNode<T> insert(int i, T x)
    {
        throw new UnsupportedOperationException("insert(int i, T x)");
    }
    
	public DoubleNode<T> insert(T x)
	{
		if(this.isEmpty()||x.compareTo(this.head.prev.data)>0)
			return super.insert(x);                //调用父类被覆盖的insert(T)方法，最大值插入在头结点之前，即尾插入，O(1)
		DoubleNode<T> p = this.head.next;
		while(p!=head&&x.compareTo(p.data)>0)                        //寻找插入位置（p指向）
			p = p.next;
		DoubleNode<T>q = new DoubleNode<T>(x,p.prev,p);               //在p结点之前插入值为x的结点
		p.prev.next = q;
		p.prev = q; 
		return q;                                                 //返回插入结点
	}

	public T removeLast() {
		return remove(1);
	}

	public String toPreviousString() {
		return toString();
	}

}
