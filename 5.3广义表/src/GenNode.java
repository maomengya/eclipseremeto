
public class GenNode<T> 
{
	 public T data;                                         //数据域
	 public GenList<T> child;                               //地址域，指向子表
	 public GenNode<T> next;                                //地址域，指向后继结点

	 //构造结点，data指定元素，child指向子表，next指向后继结点
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
