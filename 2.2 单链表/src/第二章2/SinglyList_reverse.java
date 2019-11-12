package �ڶ���2;

public class SinglyList_reverse 
{
	//���췴��������values�����ṩԪ�ء�����ֵ����ǰ�������Ͳ���T
	//����ͷ���룬������Ԫ�ش���������Ԫ�ش����෴��������ʡ��
	public static <T>SinglyList<T>createReverse(T[] values)
	{
		SinglyList<T> list = new SinglyList<T>();
		for(int i = 0;i<values.length;i++)
			list.head.next = new Node<T>(values[i],list.head.next);              //ͷ����
		return list;                                                          //���ص������������
	}
	public static <T>void reverse(SinglyList<T>list)                          //��list��������ת��
	{
		Node<T> p = list.head.next,succ = null,front = null;                  //head��������Ϊpublic
		while (p!= null)                          
		{
			succ = p.next;                                                    //����succ��p���ĺ�̽��
			p.next = front;                                                   //ʹp.nextָ��p����ǰ�����
			front = p;
			p = succ;                                                         //p�����һ��
		}
		list.head.next = front;
	}
	public static void main(String args[])
	{
		String[] values = {"A","B","C","D","E","F"};
		SinglyList<String> list = createReverse(values);
		System.out.print("list=" + list.toString());
		reverse(list);
		System.out.println(",��ת��"+list.toString());
	}
}
