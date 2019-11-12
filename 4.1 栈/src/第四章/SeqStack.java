package ������;

import �ڶ���_˳���.SeqList;
//˳��ջ�࣬�����࣬ʵ��ջ�ӿڣ�T��ʾ����Ԫ�ص���������
public final class SeqStack<T> implements Stack<T>
{
	private SeqList<T> list;                                 //ʹ��˳�����2�£��洢ջԪ��
	
	public SeqStack(int length)                             //��������Ϊlength�Ŀ�ջ
	{
		this.list = new SeqList<T>(length);                 //ִ�з���˳����췽��
	}
	public SeqStack()                                       //����Ĭ�������Ŀ�ջ
	{
		this(64);
	}
	
	public boolean isEmpty()                                 //�ж�ջ�Ƿ�գ����շ���true    
	{
		return this.list.isEmpty();
	}
	
	public void push(T x)                                     //Ԫ��x��ջ���ն�������ջ
	{
		this.list.insert(x);                                     //˳���β����Ԫ��x���Դ���������
	}
	
	public T peek()                                     //����ջ��Ԫ�أ�δ��ջ������ջ�շ���null
	{
		return this.list.get(list.size()-1);            //��ջ�գ�get(i)����null
	}
	
	public T pop()                                       //��ջ������ջ��Ԫ�أ���ջ�շ���null
	{
		return list.remove(list.size()-1);                  //��ջ���գ�˳���βɾ��������ɾ��Ԫ��                   
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
