package �ڶ���2;

//�����������򣩣�T��T��ĳ��������ʵ��Comparable<T>�ӿڣ��̳е�������
public class SortedSinglyList <T extends Comparable<?super T>>extends SinglyList<T>
{
//	public Node<T>head;  
	public SortedSinglyList()                                 //�������������
	{
		super();                                               //Ĭ�ϵ��ø��๹�췽��SinglyList()
	}
	public SortedSinglyList(T[] values)                        //���죬��values���������ж���ֵ����
	{
		super();                                                 //�����յ�����Ĭ�ϵ���SlinglyList()
		for(int i = 0;i<values.length;i++)
			this.insert(values[i]);                               //��������ֵ����
	}
	
//	public SortedSinglyList(SortedSinglyList<T> list)             //������췽��
//	{
//		super(list);                                              //���ø���ͬ�����Ĺ��췽��
//	}
	
	public SortedSinglyList(SinglyList<T>list)                    //����������ɵ���������������
	{
		super();                                                  //�����յ�����
		for(Node<T>p = list.head.next;p!=null;p=p.next)
			this.insert(p.data);                                  //��������ֵ����
	}
	//��֧�ָ���������������������串�ǲ��׳��쳣��������ͬ����˳���
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
	 

		//����x��x��=null������x�����С˳�����ȷ������λ�ã������ڵ�ֵ���֮ǰ
		//���ز����㡣O(n).���Ǹ����insert(x)����
		public Node<T> insert(T x)
		{
			Node<T>front = this.head,p = front.next;                 //frontָ��p��ǰ�����
			while (p!=null&&x.compareTo(p.data)>0)                           //Ѱ�Ҳ���λ��
			{
				front = p;
				p = p.next;
			}
			front.next = new Node<T>(x,p);                            //��front֮��p֮ǰ����ֵΪx���
			return front.next;                                        //���ز�����
		}
	
	//����˳����Һͻ��ڲ����㷨�Ĳ����������Ǹ����ͬ������
	//����compareTo()�����Ƚϴ�С����ȡ�������ʡ��
//	public Node<T> search(T key)                                     //���ҷ����׸���key���Ԫ�ؽ�㣬���Ҳ��ɹ�����null
//	public Node<T> insertDifferent(T x)                              //���벻�ظ�Ԫ�أ����Ҳ��ɹ�ʱ��ֵ����
//	public T remove(T key)                                          //ɾ���׸���key���Ԫ�أ����ر�ɾ��Ԫ�أ����Ҳ��ɹ�����null

		 
		//˳������׸���key���Ԫ�أ����ؽ�㣬�����Ҳ��ɹ�����null��O(n)�����ǡ�    //����5.2.2��ϡ������еĵ�����
//		public Node<T> search(T key) 
//		{
//		   for (Node<T> p=this.head.next;  p!=null && key.compareTo(p.data)>=0;  p=p.next)
//			{
////		    System.out.print(p.data+"��");
//			    if (key.compareTo(p.data)==0)                  //��compareTo()�ṩ�Ƚ϶����С����ȵ�����
//			        return p;
//			}
////		 System.out.println();
//		 return null; 
//		 }
		    
		 public Node<T> insertDifferent(T x)
		 {
		     Node<T> front=this.head, p=front.next;             //frontָ��p��ǰ�����
		     while (p!=null && x.compareTo(p.data)>0)           //Ѱ�Ҳ���λ��
		     {
		         front = p;
		         p = p.next;
		     }
		     if (p!=null && x.compareTo(p.data)==0)
		        return p;                                      //���ҳɹ���Ԫ���ظ��������룬���ظý��
		     return front.next = new Node<T>(x, p);             //��front֮��p֮ǰ����ֵΪx��㣬���ز�����
		 }
		    
	public void concat(SinglyList<T> list)
	{
		throw new UnsupportedOperationException("concat(SinglyList<T>....");
	}
	//��list������Ԫ�ز��뵽this���������ı�list�����ϲ���
	//���ǣ������븸����ͬ���㷨ʵ�ֲ�ͬ��ÿ�β���ȫ��Ԫ�غ��ٲ��룬O(n)
	public void addAll(SinglyList<T> list)
	{
		for (Node<T> p = list.head.next;p!= null;p=p.next)              //ֱ�Ӳ�����������
			this.insert(p.data);
		
//		SinglyList<T>front = this.head,p = front.next;
	}
	public SortedSinglyList<T> union(SinglyList<T> list)
	{
		SortedSinglyList<T> result = new SortedSinglyList<T>(this);
		result.addAll(list);
		return result;
	}
	
	//ƽ��ֵaverage
//	public String average(SortedSinglyList<Integer>list)
//	{
//		
//	}
}
