
public class Triple implements Comparable<Triple> ,Addible<Triple>
{
	int row,column,value;
	//���췽������������ָ���кš��кš�Ԫ��ֵ�����кš��к�Ϊ�������׳���Ч�����쳣
	public Triple(int row,int column,int value)
	{
		if(row>=0&&column>=0)
		{
			this.row = row;
			this.column = column;
			this.value = value;
		}
		else
			throw new IllegalArgumentException("�С��кŲ���Ϊ������row="+row+",column="+column);
	}
	
	public Triple(Triple tri)               //�������췽��������һ����Ԫ��
	{
		this(tri.row,tri.column,tri.value);
	}
	
	public String toString()               //������Ԫ�������ַ���
	{
		return "("+row+","+column+","+value+")";
	}
	//�����С���λ�ñȽ���Ԫ������С����Ԫ��ֵ�޹أ�Լ����Ԫ���������
	public int compareTo(Triple tri)
	{
		if(this.row == this.row&&this.column == tri.column)               //��ȣ���equals()�������岻ͬ
			return 0;
		return (this.row<tri.row||this.row==tri.row&&this.column<tri.column)?-1:1;
	}
	
//	public boolean equals(Object obj)               //�Ƚ���Ԫ���Ƿ���ȣ��Ƚ�λ�ú�Ԫ��ֵ��������ʡ��
//	{
//		
//	}
	
	public void add(Triple term)               //�ӷ���+=���㣩��ʵ��Addible<T>�ӿ�
	{
		if(this.compareTo(term)==0)
			this.value+= term.value;
		else
			throw new IllegalArgumentException("�����ָ����ͬ���������.");
	}
	
	public boolean removable()               //Լ��ɾ��Ԫ����������ʵAddible<T>�ӿ�
	{
		return this.value == 0;               //���洢ֵΪ0��Ԫ��
	}
	
	public Triple toSymmetry()               //���ؾ���Գ�λ�õ���Ԫ��
	{
		return new Triple(this.column,this.row,this.value);
	}
}
