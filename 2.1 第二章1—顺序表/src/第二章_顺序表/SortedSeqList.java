package �ڶ���_˳���;
//����˳����ࣨ���򣩣�T��T��ĳ��������ʵ��Comparable<T>�ӿڣ��̳�˳�����
public class SortedSeqList<T extends Comparable<?super T>> extends SeqList<T>
{
	public SortedSeqList()                        //���������˳���
	{
		super();                                //Ĭ�ϵ��ø��๹�췽��SeqList()
	}
	public SortedSeqList(SeqList<Student> lista)                  //���������˳�������Ϊlength
	{
		super(lista);                                //����SeqList(length)����ʡ�ԣ���Ĭ�ϵ���super()
	}
	public SortedSeqList(T[] values)                  //��������˳����������ṩԪ�أ�O(n^2)
	{
		super(values.length);                    //����������˳���ָ������
		for (int i = 0;i<values.length;i++)
			this.insert(values[i]);                      //����Ԫ�أ����ݶ����Сȷ������λ�ã�O(n)
	}

	public SortedSeqList(int i) {
		super(i);
	}
	
	public int insert(T x)
	{
		int i = 0;
		if(this.isEmpty()||x.compareTo(this.get(this.size()-1))>0)        //compareTo(T)�Ƚϴ�С
			i = this.n;                                                  //���ֵβ���룬O(1)
		else
			while(i < this.n && x.compareTo(this.get(i))>0)                  //Ѱ�Ҳ���λ�ã�����
				i++;
		super.insert(i,x);                                          //���ø��౻���ǵ�insert(i,x)����������x��Ϊ��i��Ԫ��
		return i;
	}
	
//	public int inserDifferent(T x)                            //���벻�ظ�Ԫ�ء����Ҳ��ɹ�ʱ����ֵ���롣���ǣ�������ʡ��

	int start;
	//��֧�ָ���������������������串�ǲ��׳��쳣
	public void set(int i,T x)                               //ֻ������
	{
		throw new java.lang.UnsupportedOperationException("insert(int i,T,x)");
	}
	public int insert (int i,T x)
	{
		throw new UnsupportedOperationException("insert(int i,T,x)");
	}
	public int search(T key)
	{
		for (int i = start;i<this.n&&key.compareTo(this.get(i))>0;i++)
			if (key.compareTo(this.get(i))==0)
				return i;
		return -1;
	}
	
//	public T remove(T key)
//	{
//		return this.remove(this.search(key));
//	}
}
