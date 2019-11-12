
public class GenNode<T> 
{
	 public T data;                                         //������
	 public GenList<T> child;                               //��ַ��ָ���ӱ�
	 public GenNode<T> next;                                //��ַ��ָ���̽��

	 //�����㣬dataָ��Ԫ�أ�childָ���ӱ�nextָ���̽��
	 public GenNode(T data, GenList<T> child, GenNode<T> next) 
	 {
	     this.data = data;
	     this.child = child;
	        this.next = next;
	 }
	 public GenNode(T data)
	 {
	     this(data, null, null);
	 }
	 public GenNode()
	 {
	     this(null, null, null);
	 }

}
