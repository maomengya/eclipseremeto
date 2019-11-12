package 第四章;

import 第二章_顺序表.SeqList;
//顺序栈类，最终类，实现栈接口，T表示数据元素的数据类型
public final class SeqStack<T> implements Stack<T>
{
	private SeqList<T> list;                                 //使用顺序表（第2章）存储栈元素
	
	public SeqStack(int length)                             //构造容量为length的空栈
	{
		this.list = new SeqList<T>(length);                 //执行方法顺序表构造方法
	}
	public SeqStack()                                       //构造默认容量的空栈
	{
		this(64);
	}
	
	public boolean isEmpty()                                 //判断栈是否空，若空返回true    
	{
		return this.list.isEmpty();
	}
	
	public void push(T x)                                     //元素x入栈，空对象不能入栈
	{
		this.list.insert(x);                                     //顺序表尾插入元素x，自带扩充容量
	}
	
	public T peek()                                     //返回栈顶元素（未出栈），若栈空返回null
	{
		return this.list.get(list.size()-1);            //若栈空，get(i)返回null
	}
	
	public T pop()                                       //出栈，返回栈顶元素；若栈空返回null
	{
		return list.remove(list.size()-1);                  //若栈不空，顺序表尾删除，返回删除元素                   
	}
	
//	public static StringBuffer radix10_2(String infix)
//	{
//		Stack<String> s1 = new SeqStack<String>();
//		Stack<Integer> s2 = new SeqStack<Integer>();
////		StringBuffer out = new StringBuffer(infix.length()*2);
//		int i=0,value = 0;
//		while(i<infix.length())
//		{
//			char c = infix.charAt(i);
//			s1.push(c+"");
//		}
//		for(i = 0;i<infix.length();i++)
//		{
//			char c = infix.charAt(i);
//			value = value
//		}
//	}
	
	public static int radix10_2(int e)
	{
		int sum = 0,c = e;
		Stack<Integer> s = new SeqStack<Integer>();
		System.out.println("c=" + e);
		while(e>2)
		{
			c = e%2;
			e = e/2;
			s.push(c);
		}
		s.push(e);
		while(!s.isEmpty())
		{
			sum = sum*10+s.pop();
		}
		return sum;
	}
	
	public static int radix10_8(int e)
	{
		int sum = 0,c = e;
		Stack<Integer> s = new SeqStack<Integer>();
		System.out.println("c=" + e);
		while(e>8)
		{
			c = e%8;
			e = e/8;
			s.push(c);
		}
		s.push(e);
		while(!s.isEmpty())
		{
			sum = sum*10+s.pop();
		}
		return sum;
	}
	
	public static void radix10_16(int e)
	{
		char c = '0';
		Stack<String> s = new SeqStack<String>();
		System.out.println("c=" + e);
		while(e>16)
		{
			c = (char) (e%16+'0');
			e = e/16;
			if(c<='9')
				s.push(c+"");
			else
			{
				c = (char)(c - '0' + 'A');
				s.push(c+"");
			}
		}
		if(e<=9)
			s.push((char)(e+'0')+"");
		else
		{
			c = (char)(e - 0 + 'A');
			s.push(c+"");
		}
		while(!s.isEmpty())
		{
			System.out.print(s.pop()+"");
		}
	}
	
	public static void main(String args[])
	{
		System.out.println(radix10_2(12));
		System.out.println(radix10_8(12));
		radix10_16(18);
//		radix10_2(12342);
		
//		char[] e = {1,2,4,5,6};
//		int len,i,sum = 0;
//		String c;
//		Stack<String> s = new SeqStack<String>();
//		
//		for(int j=0;j<e.length;j++)
//			s.push(e[j]+"");
//		for(int j = 0;j<e.length;j++)
//		{
//			c = s.pop();
//			sum = sum + ((int)c-48)*pow(2,j);
//		}
		
	}

}
