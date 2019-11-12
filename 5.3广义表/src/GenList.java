
public class GenList<T>                                           //双链表示的广义表类，没有表名
{
	public GenNode<T> head;                                        //头指针，指向（引用）头结点
	public GenList()                                        //构造空广义表
	{
		this.head = new GenNode<T>();                                        //创建头结点，3个域值均为null
	}
	
	public String toString()                                        //返回广义表所有元素的描述字符串
	{
		return this.toString("");
	}
	
	public String toString(String str)                                        //返回广义表所有元素值对应的字符串，形式为“（，）”，广义表遍历算法，递归方法
	{
		str+="(";
		for(GenNode<T> p = this.head.next;p!=null;p = p.next)
		{
			if(p.child == null)
				str += p.data.toString();
			else
				str += p.child.toString();                                        //递归调用，遍历子表添加子表描述
			if(p.next!=null)
				str += ",";
		}
		return str += ")";                                        //空表返回（）
	}
	
	public int depth()                                        //返回广义表深度，递归算法
	{
		int max=1;
        for (GenNode<T> p=this.head.next;  p!=null;  p=p.next)
            if (p.child!=null)
            {
                int d=p.child.depth();                     //递归调用，返回子表深度
                if (max<=d)                                //记住最大子表深度
                    max=d+1;                               //当前广义表深度为子表深度加1
            }
        return max;
	}
	
	public GenList(T[] atoms)                                        //构造广义表，由atoms数组提供原子初值
	{
		this();                                            //创建空广义表，只有头结点
        GenNode<T> rear=this.head;
        for (int i=0; i<atoms.length; i++)
        {
            rear.next=new GenNode<T>(atoms[i]);            //尾插入
            rear = rear.next; 
        }
	}
	
	public boolean isEmpty()                                        //判断广义表是否空
	{
		return head.next==null;
	}
	
	public int size()                                        //返回广义表长度
	{
		int i=0; 
        for (GenNode<T> p=this.head.next;  p!=null;  p=p.next)
            i++;
        return i;
	}
	
	public GenNode<T> insert(int i,T x)                                        //插入原子x作为第i个元素；对i容错
	{
		if (x==null)
            throw new NullPointerException("x==null");     //不能插入空对象，抛出空对象异常
        GenNode<T> front=this.head;                        //front指向头结点
        for (int j=0;  front.next!=null && j<i;  j++)      //寻找第i-1个或最后一个结点（front指向）
            front = front.next; 
        front.next=new GenNode<T>(x, null, front.next);    //在front之后插入值为x结点
        return front.next;  
	}
	
	public GenNode<T> insert(T x)                                        //在广义表最后添加原子x结点
	{
		return insert(Integer.MAX_VALUE, x);
	}
	
	public GenNode<T> insert(int i,GenList<T> glist)                                        //插入子表glist作为第i个元素；对i容错
	{
		if (glist==null)
            throw new NullPointerException("x==null");     //不能插入空对象，抛出空对象异常
        GenNode<T> front=this.head;                        //front指向头结点
        for (int j=0;  front.next!=null && j<i;  j++)      //寻找第i-1个或最后一个结点（front指向）
            front = front.next; 
        front.next=new GenNode<T>(null, glist, front.next);//在front之后插入结点，无值，child指向glist
        return front.next;  
	}
	
	public GenNode<T> insert(GenList<T> glist)                                        //在广义表最后添加子表glist
	{
		 return insert(Integer.MAX_VALUE, glist);
	}
	
	public void remove(int i)                                        //删除第i个元素
	{
		
	}
	
	
	public static void main(String args[])
	{
		String[] atoms = {"a","b"};
		GenList<String> glist_L = new GenList<String>(atoms);                                           //由原子数组构造广义表L
		System.out.println("L="+glist_L.toString()+",size="+glist_L.size()+",depth="+glist_L.depth());  
		GenList<String>glist_T = new GenList<String>();                                          //构造空广义表G
		glist_T.insert("c");                                          //插入原子c
		glist_T.insert(glist_L);                                          //尾插入子表L
		System.out.println("T="+glist_T.toString()+",size="+glist_T.size()+",depth="+glist_T.depth());
		GenList<String>glist_G = new GenList<String>();                                          //构造空广义表G
		glist_G.insert("d");                                          //插入原子d
		glist_G.insert(glist_L);                                          //尾插入子表L
		glist_G.insert(glist_T);                                          //尾插入子表T，glist_L成为共享子表
		System.out.println("G="+glist_G.toString()+",size="+glist_G.size()+",depth="+glist_G.depth());
	}
}
