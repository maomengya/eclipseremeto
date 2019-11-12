//顺序循环队列类，最终类，实现队列接口，T表示数据元素的数据类型
public final class SeqQueue<T> implements Queue<T> 
{
	private Object element[];                  //存储队列数据元素的数组
	private int front,rear;                    //front、rear分别为队列头尾下标
	public SeqQueue(int length)                //构造容量为length的空队列
	{
		if(length<64)
			length = 64;                         //设置队列数组容量最小值
		this.element = new Object[length];
		this.front = this.rear = 0;
	}
	
	public SeqQueue()                            //构造默认容量的空队列
	{
		this(64);
	}
	
	public boolean isEmpty()                       //判断队列是否空，若是返回null
	{
		return this.front == this.rear;
	}
	
	public boolean add(T x)                       //元素x入队，空对象不能入队
	{
		if(x == null)
			return false;
		if(this.front == (this.rear+1)%this.element.length)       //若队列满，则扩充数组
		{
			Object[] temp = this.element;                         //重新申请一个容量更大的数组
			this.element = new Object[temp.length*2];
			int j = 0;
			for(int i = this.front;i!=this.rear;i=(i+1)%temp.length)
				this.element[j++] = temp[i];                       //按照队列元素次序复制数组元素
			this.front = 0;
			this.rear = j;
		}
		this.element[this.rear] = x;
		this.rear = (this.rear+1)%this.element.length;
		return true;
	}
	
	public T peek()                                             //返回队头元素，没有删除。若队列空，则返回null
	{
		return this.isEmpty()?null:(T)this.element[this.front];
	}
	
	public T poll()                                              //出队，返回队头元素，若队列空返回null
	{
		if(this.isEmpty())
			return null;
		T temp = (T)this.element[this.front];
		this.front = (this.front+1)%this.element.length;
		return temp;
	}

}
