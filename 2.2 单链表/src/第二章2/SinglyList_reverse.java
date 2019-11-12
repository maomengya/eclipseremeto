package 第二章2;

public class SinglyList_reverse 
{
	//构造反序单链表，由values数组提供元素。返回值类型前声明类型参数T
	//采用头插入，单链表元素次序与数组元素次序相反。方法体省略
	public static <T>SinglyList<T>createReverse(T[] values)
	{
		SinglyList<T> list = new SinglyList<T>();
		for(int i = 0;i<values.length;i++)
			list.head.next = new Node<T>(values[i],list.head.next);              //头插入
		return list;                                                          //返回单链表对象引用
	}
	public static <T>void reverse(SinglyList<T>list)                          //将list单链表逆转。
	{
		Node<T> p = list.head.next,succ = null,front = null;                  //head必须声明为public
		while (p!= null)                          
		{
			succ = p.next;                                                    //设置succ是p结点的后继结点
			p.next = front;                                                   //使p.next指向p结点的前驱结点
			front = p;
			p = succ;                                                         //p向后走一步
		}
		list.head.next = front;
	}
	public static void main(String args[])
	{
		String[] values = {"A","B","C","D","E","F"};
		SinglyList<String> list = createReverse(values);
		System.out.print("list=" + list.toString());
		reverse(list);
		System.out.println(",逆转后"+list.toString());
	}
}
