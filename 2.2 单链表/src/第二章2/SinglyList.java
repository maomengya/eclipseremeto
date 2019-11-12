package �ڶ���2;


//�������࣬ʵ��ADTList<T>�����ķ�����T��ʾ����Ԫ�ص���������
public class SinglyList<T> extends Object
{
	public Node<T>head;                                   //ͷָ�룬ָ�������ͷ����
	
	//��1�� ���췽��
	public SinglyList()                                  //����յ�����
	{          
		this.head = new Node<T>();                       //����ͷ��㣬data��nextֵ��Ϊnull
	}
	
	public SinglyList(T[] values)                        //���쵥������values�����ṩԪ��
	{
		this();                                           //�����յ�����ֻ��ͷ���
		Node<T>rear = this.head;                            //rearָ���������һ�����
		for (int i = 0;i<values.length;i++)              //��values.length == 0�����������
		{
			rear.next = new Node<T>(values[i],null);          //β���룬�����������rear���֮��
			rear = rear.next;                             //rearָ���µ���β���
		}
	}
	
	public SinglyList(SinglyList<T>list)                            //���
	{    
		this();                                                     //�����յ�����
		Node<T> rear = this.head;
		for (Node<T> p =list.head.next;p!=null;p = p.next)
		{
			rear.next = new Node<T>(p.data,null);
			rear = rear.next;                                       //ָ��this������β
		}
	}
	
	public boolean isEmpty()                               //�жϵ������Ƿ�գ�O(1)
	{
		return this.head.next == null;
	}
	
	//��1����ȡ
	public T get(int i)                              //���ص�i��Ԫ�أ�0<=i<���ȡ���iԽ�磬�򷵻�null.O(n)
	{
		Node<T> p = this.head.next;
		for(int j = 0;p!=null && j<i;j++)                    //����������Ѱ�ҵ�i����㣨pָ��
			p = p.next;
		return (i>=0&&p!=null)?p.data:null;                 //��pָ���i����㣬������Ԫ��ֵ
	}
	
//	public void set(int i,T x)                   //���õ�i��Ԫ��Ϊx��0<=i<���ȣ�x��=null��������ʡ��
//	public int size()                                   //���ص������ȣ�O(n)��������ʡ��
	
	//���ص���������Ԫ�ص������ַ�������ʽΪ"(,)"/����Object���toString()������O(n)
	public String toString()
	{
		String str = this.getClass().getName()+"(";           //��������
		for (Node<T>p = this.head.next;p != null;p = p.next)          //����������
		{
			str += p.data.toString();
			if (p.next!=null)
				str += ",";                                       //�������һ�����ʱ���ӷָ���
		}
		return str+")";                                       //�ձ��أ���
	}
	
	//��3������
	//����x��Ϊ��i��Ԫ�أ�x��=null�����ز�����
	//�����i��ȡ�ݴ��ʩ����i<0,�����x����ǰ�棻��i>n�������x�����O(n)
	public Node<T>insert(int i,T x)
	{
		if(x == null)
			throw new NullPointerException("x == null");               //�׳��ն����쳣
		Node<T>front = this.head;                                     //frontָ��ͷ���   
		for (int j = 0;front.next!=null&&j<i;j++)                       //Ѱ�ҵ�i-1�������һ�����(frontָ��)
			front = front.next;
		front.next = new Node<T>(x,front.next);                        //��front֮�����ֵΪx�Ľ��
		return front.next;                                                //���ز�����
	}
	
	public Node<T> insert(T x)                                           //�ڵ�����������x����O(n)
	{
		//����insert(i,x),���������ֵָ����������󣬱���һ�飬i�����ݴ�
		return insert(Integer.MAX_VALUE,x);
	}
	
	//��4��ɾ��
	public T remove(int i)                                             //ɾ����i��Ԫ�أ�0<=i<n�����ر�ɾ��Ԫ�أ���iԽ�磬�򷵻�null��O(n)
	{
		Node<T>front = this.head;                                     //frontָ��ͷ���
		for(int j = 0;front.next!=null&&j<i;j++)                        //����Ѱ�ҵ�i-1��㣨frontָ��
			front = front.next;
		if (i>=0&&front.next!=null)                                     //��front�ĺ�̽����ڣ���ɾ��֮
		{
			T old = front.next.data;                                    //��ô�ɾ��������õĶ���
			front.next = front.next.next;                                 //ɾ��front�ĺ�̣�����ͷɾ�����м�/βɾ��
			                                                              //��Java������Ժ��ͷŽ��ռ�õĴ洢��Ԫ
			return old;                                                
		}
		return null;                                                     //��i<0��i>��
	}
	
	public void clear()                                                  //ɾ������������Ԫ��
	{
		this.head.next = null;                                             //Java�Զ��ջ����н��ռ�õĿռ�
	}
	
    //�Ƚ����
	public boolean equals(Object obj)
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
	}

//	public int compareTo(SinglyList<T> list)
//	{
//		int result ;
//		if (this.equals(list))
//			result = 0;
//		else
//			if(this>list.data)
//				result = -1;
//	}
	
	//��this������֮������list����������listΪ�գ����ϲ�
	public void concat(SinglyList<T> list)
	{
		Node<T>rear = this.head;
		while (rear.next!=null)
			rear = rear.next;
		rear.next = list.head.next;
		list.head.next = null;
	}
	
	//���list�����ı�list
	public void addAll(SinglyList<T> list)
	{
		this.concat(new SinglyList<T>(list));
	}
	
		
	//���ظ���this��list�ϲ����ӵĵ��������������ı�this��list
	public SinglyList<T> union(SinglyList<T>list)
	{
		SinglyList<T> result = new SinglyList<T>(this);
		result.addAll(list);
		return result;
	}
	

	public SinglyList<T> addAll(int i,SinglyList<T> list)
	{
		SinglyList<T> result = new SinglyList<T>(this);
		SinglyList<T> link = new SinglyList<T>(list);

		if(i<0)
		{
			Node<T>rear = link.head;
			while (rear.next!=null)
				rear = rear.next;
			rear.next = result.head.next;
			result.head = link.head;
		}
		else
		{
			Node<T>rear = result.head;
			for(int j = 0;j<i;j++)
				rear = rear.next;
			if(rear.next == null)                  //�ж�i�Ƿ���ڳ��ȣ�����β����
			{
				result.concat(link);
			}
			else
			{
				Node<T> p = link.head;
				while(p.next != null)
					p = p.next;
				p.next = rear.next;
				rear.next = link.head.next;
			}
		}
		return result;
	}
	
//	��5��˳����Һͻ��ڲ����㷨�Ĳ�����
	public Node<T> search(T key)                                         //���ҷ����׸���key���Ԫ�ؽ�㣬���Ҳ��ɹ�����null
	{
		//for(Node<T> p = this.head.next;p!=null&&(key.comparaTo(p.data)==0))
		for(Node<T> p = this.head.next;p!=null && (key.equals(p.data));p=p.next)
		{
//			//if (key.compareTo(p.data)==0)
			if (key.equals(p.data))
				return p;
		}
		return null;
	}
//	public boolean contains(T key)                                       //�ж��Ƿ�����ؼ���ΪkeyԪ��
	public Node<T> insertDifferent(T x)                                   //���벻�ظ�Ԫ�أ����Ҳ��ɹ�ʱβ����
	{
		Node<T>front = this.head,p = front.next;
		//while(p!=null&&x.compareTo(p.data)>0)
		while(p!=null&&x.equals(p.data))
		{
			front = p;
			p = p.next;
		}
		//if(p!=null&&x.compareTo(p.data)==0)
		if(p!=null&&x.equals(p.data))
			return null;
		front.next = new Node<T>(x,p);
		return front.next;
	}
	public T remove (T key)                                               //ɾ���׸���key���Ԫ�أ����ر�ɾ��Ԫ�أ����Ҳ��ɹ�����null
	{
		Node<T> front = this.head,p = front.next;
		//while(p!= null&&key.compareTo(p.data)==0)
		while(p!= null&&key.equals(p.data))
		{
			front.next = p.next;
			return p.data;
		}
		return null;
	}
	
	public static void main(String[] args)
	{
//		Node p = new Node(10, null);
//		System.out.println(p.data.equals(10));
//		System.out.println(p.data.equals(13));
		
		String[] values1 = {"S","R","T","S","E"};
		String[] values2 = {"K","T","S","U","D"};
		SinglyList<String>lista = new SinglyList<String>(values1);
		SinglyList<String>listb = new SinglyList<String>(lista);
		SinglyList<String>listc = new SinglyList<String>(values2);

		System.out.println(lista.equals(listb));
		System.out.println(lista.equals(listc));
		
		System.out.println(lista.union(listb));
		System.out.println(lista.union(listc));
		
		System.out.println(lista.addAll(3,listc));
	}
}
