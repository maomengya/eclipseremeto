package �ڶ���3;

public class DoubleNode<T>                                            //˫�������࣬Tָ������Ԫ������
{
	public T data;                                                     //�����򣬴洢����Ԫ��
	public DoubleNode<T>prev,next;                                      //��ַ�򣬴洢����Ԫ��
	//�����㣬dataָ��Ԫ�أ�prevָ��ǰ����㣬nextָ���̽��
	public DoubleNode(T data,DoubleNode<T> prev,DoubleNode<T> next)                //���췽��
	{
		this.data = data;                    //T�������ø�ֵ
		this.prev = prev;                      //DoubleNode<T>�������ø�ֵ
		this.next = next;
	}
	public DoubleNode(T data)
	{
		this(data, null, null);
	}
	public DoubleNode()
	{
		this(null,null,null);
	}
	public String toString()                          //���ؽ��������������ַ���                           //���ؽ��������������ַ���
	{
		return this.data.toString(); 
	}
}
