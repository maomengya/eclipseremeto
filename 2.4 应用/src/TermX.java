//���࣬һԪ����ʽ��һ�ʵ�ֿɱȽϽӿںͿ���ӽӿ�
public class TermX implements Comparable<TermX>,Addible<TermX>
{
	protected int coef,xexp;                            //ϵ����xָ������Ϊ����0��.ϵ��Ҳ��Ϊdouble
	public TermX(int coef,int xexp)                       //����һ��
	{
		
	}
	public TermX(TermX term)                           //�������췽��
	{
		
	}
	//�ԡ�ϵ��x^ָ������ʡ����ʽ����һԪ����ʽ��һ��
	//ʡ����ʽ˵������ϵ��Ϊ1����-1��ָ��>0ʱ��ʡ��1��-1ֻд���š�-������x^2,-x^3;
	//��ָ��Ϊ0ʱ��ʡ��x^0��ֻдϵ������ָ��Ϊ1ʱ��ʡ��^1,ֻдx
	public TermX(String termstr)
	{
		
	}
	//����һ���Ӧ�ġ�ϵ������ʡ����ʽ�ַ�����ʡ����ʽ˵��ͬTermX��String������
	public String toString()
	{
		return null;
		
	}
	public boolean equals(Object obj)              //��ϵ����ָ���Ƚ������Ƿ����
	{
		return false;
		
	}
	public int compareTo(TermX term)                 //��xָ���Ƚ������С��ʵϰComparable<T>�ӿ�
	{
		return coef;
		
	}
	public void add(TermX term)                     //��ָ����ͬ����ϵ����ӣ�ʵ��Addible<T>
	{
		
	}
	public boolean removable()                       //��ϵ��Ϊ0����ɾ��Ԫ�أ�ʵ��Addible<T>�ӿ�
	{
		return false;
		
	}

}
