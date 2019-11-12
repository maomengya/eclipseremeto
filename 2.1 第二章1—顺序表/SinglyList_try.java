package 实验二;

import 第二章2.Node;
import 第二章2.SinglyList;

public class SinglyList_try <T> extends Object
{
	protected Object[] element;                    //对象数组存储顺序表的数据元素，保护成员
	protected int n;                               //顺序表元素的个数（长度）
	
	public T get(int i)                                //返回第i个元素，0<=i<n.若i越界，则返回null
	{
		if(i>=0 && i<this.n)
			return (T)this.element[i];                  //返回数组元素引用的对象，传递对象引用
		return null;
	}
	
	public boolean equals(Object obj)                     //比较两个顺序表是否相等，覆盖
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
		
//		if (this==obj)                               //若this和obj引用同一个顺序表实例，则相等
//			return true;
//		if (obj instanceof SinglyList_try<?>)                     //若obj引用顺序表实例。SeqList<?>是所有SeqList<T>的父类
//		{
//			SinglyList_try<T>list = (SinglyList_try<T>)obj;               //声明list也引用obj引用的实例
//			if (this.n==list.n)                                //比较两者长度是否相等
//			{
//				for (int i = 0;i<this.n;i++)                   //比较两个顺序表的所有元素是否相等
//					if(!(this.get(i).equals(list.get(i))))                     //equals(Object)方法运行时多态
//						return true;
//			}
//		}
//		return false;
	}
	
	public T subList(int begin,int end)
	{
		if (this.n>0 && begin>=0 && end<this.n)
		{ 
			T number = null;                  //number存放子集
			for(int j = begin;j<end;j++)
				number = (T) this.element[j];         //深拷贝
			return number;                             
		}
		return null;
	}
	
	public T remove(int i)           //删除第i个元素，0<=i<n；返回被删除元素。若i越界，则返回null
	{
		if (this.n>0 && i>=0 && i<this.n)
		{ 
			T old = (T)this.element[i];                  //old中存储被删除元素
			for(int j = i;j<this.n-1;j++)
				this.element[j] = this.element[j+1];         //元素前移一个位置
			this.element[this.n-1] = null;                 //设置数组元素对象为空，释放原引用实例
			this.n--;
			return old;                              //返回old局部变量引用的对象，传递对象引用
		}
		return null;
	}
	//顺序查找首次出现与key相等元素，返回元素序号i，0<=i<n；查找不成功返回-1
	//若key==null，则抛出空对象异常NullPointerException
	public int search(T key)
	{
		for (int i = 0;i<this.n;i++)
			if (key.equals(this.element[i]))               //执行T类的equals(Object)方法，运行时多态
				return i;
		return -1;                                      //空表或未找到时
	}
	//判断是否包含关键字为key元素
	public boolean contains(T key)
	{
		return this.search(key)!=-1;
	}
	
	public int addAll (int i,SinglyList_try<T> list)
	{
		if(list == null)
			throw new NullPointerException("list == null");                   //抛出空对象异常
		if(i<0)                                              //插入位置i容错，插入在最前
			i = 0;
		if(i > this.n)                                      //插入在最后
			i = this.n;
		Object[] source = this.element;                          //数组引用赋值，source也引用element
		if(this.n == element.length)                         //若数组满，则扩充顺序表的数组容量
		{
			this.element = new Object[source.length*2];             //重新申请一个容量更大的数组
			for (int j = 0;j<i;j++)                               //复制当前数组前i-1个元素
				this.element[j] = source[j];
		}
		for(int j = this.n-1;j>=i;j--)                            //从i开始至表尾的元素向后移动，次序从后向前
			this.element[j+1] = source[j];
		this.element[i] = list;
		this.n++;
		return i;                                              //返回x序号
	}
}
