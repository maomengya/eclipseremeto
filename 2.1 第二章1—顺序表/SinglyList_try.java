package ʵ���;

import �ڶ���2.Node;
import �ڶ���2.SinglyList;

public class SinglyList_try <T> extends Object
{
	protected Object[] element;                    //��������洢˳��������Ԫ�أ�������Ա
	protected int n;                               //˳���Ԫ�صĸ��������ȣ�
	
	public T get(int i)                                //���ص�i��Ԫ�أ�0<=i<n.��iԽ�磬�򷵻�null
	{
		if(i>=0 && i<this.n)
			return (T)this.element[i];                  //��������Ԫ�����õĶ��󣬴��ݶ�������
		return null;
	}
	
	public boolean equals(Object obj)                     //�Ƚ�����˳����Ƿ���ȣ�����
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
		
//		if (this==obj)                               //��this��obj����ͬһ��˳���ʵ���������
//			return true;
//		if (obj instanceof SinglyList_try<?>)                     //��obj����˳���ʵ����SeqList<?>������SeqList<T>�ĸ���
//		{
//			SinglyList_try<T>list = (SinglyList_try<T>)obj;               //����listҲ����obj���õ�ʵ��
//			if (this.n==list.n)                                //�Ƚ����߳����Ƿ����
//			{
//				for (int i = 0;i<this.n;i++)                   //�Ƚ�����˳��������Ԫ���Ƿ����
//					if(!(this.get(i).equals(list.get(i))))                     //equals(Object)��������ʱ��̬
//						return true;
//			}
//		}
//		return false;
	}
	
	public T subList(int begin,int end)
	{
		if (this.n>0 && begin>=0 && end<this.n)
		{ 
			T number = null;                  //number����Ӽ�
			for(int j = begin;j<end;j++)
				number = (T) this.element[j];         //���
			return number;                             
		}
		return null;
	}
	
	public T remove(int i)           //ɾ����i��Ԫ�أ�0<=i<n�����ر�ɾ��Ԫ�ء���iԽ�磬�򷵻�null
	{
		if (this.n>0 && i>=0 && i<this.n)
		{ 
			T old = (T)this.element[i];                  //old�д洢��ɾ��Ԫ��
			for(int j = i;j<this.n-1;j++)
				this.element[j] = this.element[j+1];         //Ԫ��ǰ��һ��λ��
			this.element[this.n-1] = null;                 //��������Ԫ�ض���Ϊ�գ��ͷ�ԭ����ʵ��
			this.n--;
			return old;                              //����old�ֲ��������õĶ��󣬴��ݶ�������
		}
		return null;
	}
	//˳������״γ�����key���Ԫ�أ�����Ԫ�����i��0<=i<n�����Ҳ��ɹ�����-1
	//��key==null�����׳��ն����쳣NullPointerException
	public int search(T key)
	{
		for (int i = 0;i<this.n;i++)
			if (key.equals(this.element[i]))               //ִ��T���equals(Object)����������ʱ��̬
				return i;
		return -1;                                      //�ձ��δ�ҵ�ʱ
	}
	//�ж��Ƿ�����ؼ���ΪkeyԪ��
	public boolean contains(T key)
	{
		return this.search(key)!=-1;
	}
	
	public int addAll (int i,SinglyList_try<T> list)
	{
		if(list == null)
			throw new NullPointerException("list == null");                   //�׳��ն����쳣
		if(i<0)                                              //����λ��i�ݴ���������ǰ
			i = 0;
		if(i > this.n)                                      //���������
			i = this.n;
		Object[] source = this.element;                          //�������ø�ֵ��sourceҲ����element
		if(this.n == element.length)                         //����������������˳������������
		{
			this.element = new Object[source.length*2];             //��������һ���������������
			for (int j = 0;j<i;j++)                               //���Ƶ�ǰ����ǰi-1��Ԫ��
				this.element[j] = source[j];
		}
		for(int j = this.n-1;j>=i;j--)                            //��i��ʼ����β��Ԫ������ƶ�������Ӻ���ǰ
			this.element[j+1] = source[j];
		this.element[i] = list;
		this.n++;
		return i;                                              //����x���
	}
}
