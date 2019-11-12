package �ڶ���3;
//����ѭ��˫�������򣩣�����x�����С˳�����ȷ������λ�ã������ڵ�ֵ���֮ǰ
//���ز�����.O(n).���Ǹ����insert(x)����
public class SortedCirDoublyList<T extends Comparable<?super T>> extends CirDoublyList<T> 
{
	//���췽���������ͷ�����ͬ��������
	public SortedCirDoublyList()
	{
		super();                                                  //Ĭ�Ϲ��췽��
	}
	
	public SortedCirDoublyList(T[] values)
	{
		super();
		for(int i = 0; i<values.length;i++)
			this.insert(values[i]);                            //��ֵ����
	}
	
	public SortedCirDoublyList(SortedCirDoublyList<T> list)
	{
		super(list);
	}
	
	//�ɵ�����list��������ѭ��˫��������������췽����ֱ�Ӳ��롣
//	public SortedCirDoublyList(SinglyList<T> list)
//	{
//		super();
//		for(Node<T> p = list.head.next;p!=list.head;p = p.next)
//			this.insert(p.data);
//	}
	
	public void set(int i,T x)
	{
		throw new UnsupportedOperationException("set(int i,T x");
	}

    public DoubleNode<T> insert(int i, T x)
    {
        throw new UnsupportedOperationException("insert(int i, T x)");
    }
    
	public DoubleNode<T> insert(T x)
	{
		if(this.isEmpty()||x.compareTo(this.head.prev.data)>0)
			return super.insert(x);                //���ø��౻���ǵ�insert(T)���������ֵ������ͷ���֮ǰ����β���룬O(1)
		DoubleNode<T> p = this.head.next;
		while(p!=head&&x.compareTo(p.data)>0)                        //Ѱ�Ҳ���λ�ã�pָ��
			p = p.next;
		DoubleNode<T>q = new DoubleNode<T>(x,p.prev,p);               //��p���֮ǰ����ֵΪx�Ľ��
		p.prev.next = q;
		p.prev = q; 
		return q;                                                 //���ز�����
	}

	public T removeLast() {
		return remove(1);
	}

	public String toPreviousString() {
		return toString();
	}

}
