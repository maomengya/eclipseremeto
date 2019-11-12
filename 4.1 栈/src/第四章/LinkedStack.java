package 第四章;

import 第二章_顺序表.SeqList;
//链式栈类，最终类，实现栈接口，T表示数据元素的数据类型
public final class LinkedStack<T> implements Stack<T>
{
	private SeqList<T> list;                       //使用单链表存储栈元素
	public LinkedStack()                            //构造空栈
	{
		this.list = new SeqList<T>();                
	}
	
	public boolean isEmpty()                      //判断栈是否空，若空返回true
	{
		return this.list.isEmpty();
	}
	
	public void push(T x)                        //元素x入栈，空对象不能入栈
	{
		this.list.insert(0,x);                    //单链表头插入元素x
	}
	
	public T peek()                               //返回栈顶元素（未出栈），若栈空返回null
	{
		return this.list.get(0);
	}
	
	public T pop()                                 //出栈，返回栈顶元素，若栈空返回null
	{ 
		return this.list.remove(0);                 //若栈不空，单链表头删除，返回删除元素
	}

}
