package 第二章_顺序表;

//顺序列表，实现ADTList<T>声明的方法，T表示数据元素的数据模型
public class SeqList<T> extends Object
{
	protected Object[] element;                    //对象数组存储顺序表的数据元素，保护成员
	protected int n;                               //顺序表元素的个数（长度）
	public SeqList(int length)                      //构造容量为length的空表
	{
		this.element = new Object[length];               //申请数组的存储空间，元素为null
													//若length<0，则Java抛出负数组长度异常java.lang.NegativeArraySizeException
		this.n = 0;
	}
	public SeqList()                               //创建默认容量的空表，构造方法重载
	{                                      
		this(64);                               //调用本类已声明的指定参数列表的构造方法
	}
	public SeqList(T[] values)                          //构造顺序表，由values.length的空表
	{
		this(values.length);                        //创建容量为values.length的空表
											//若values == null,则用空对象调用方法，Java抛出NullPointerException空对象异常
		for(int i = 0;i<values.length;i++)          //复制数组元素，O(n)
			this.element[i] = values[i];            //对象引用赋值
		this.n = element.length;
	}
	
	public SeqList(SeqList<Student> lista) {
		super();
	}
	
	public boolean isEmpty()                       //判断顺序表是否空，若为空返回true
	{
		return this.n == 0;
	}
	public int size()                               //返回顺序表元素个数
	{
		return this.n;
	}
	public T get(int i)                                //返回第i个元素，0<=i<n.若i越界，则返回null
	{
		if(i>=0 && i<this.n)
			return (T)this.element[i];                  //返回数组元素引用的对象，传递对象引用
		return null;
	}
	//设置第i个元素为x，0<=i<n。若i越界，则抛出序号越界异常；若x == null，则抛出空对象异常
	public void set(int i,T x)
	{
		if(x == null)
			throw new NullPointerException("x == null");                    //抛出空对象异常
		if(i>=0&&i<this.n)
			this.element[i] = x;
		else throw new java.lang.IndexOutOfBoundsException(i+"");          //抛出序号越界异常
	}
	//返回顺序表所有元素的描述字符串，形式为“(,)”，覆盖Object类的toString()方法
	public String toString()
	{
		String str = this.getClass().getName() + "(";          //返回文件名
		if(this.n>0)
			str+=this.element[0].toString();
		for(int i = 1;i<this.n;i++)
			str += ","+this.element[i].toString();                //执行T类的toString()方法，运行时多态
		return str+")";                                             //空表返回()
	}
//	public String toPreviousString()                          //返回所有元素的描述字符串（次序从后向前），方法体省略
//	{
//		
//	}
	
	//插入x作为第i个元素，x！=null，返回x序号。若努力了，则抛出空对象异常。O(n)
	//对序号i采取容错措施，若i<0,则插入x在最前面；若i>n,则插入x在最后
	public int insert (int i,T x)
	{
		if(x == null)
			throw new NullPointerException("x == null");                   //抛出空对象异常
		if(i<0)                                              //插入位置i容错，插入在最前
			i = 0;
		if(i > this.n)                                      //插入在最后
			i = this.n;
		Object[] source = this.element;                          //数组引用赋值，source也引用element
		if(this.n == element.length)                         //若数组满，则扩充顺序表的数组容量
		{
			this.element = new Object[source.length*2];             //重新申请一个容量更大的数组
			for (int j = 0;j<i;j++)                               //复制当前数组前i-1个元素
				this.element[j] = source[j];
		}
		for(int j = this.n-1;j>=i;j--)                            //从i开始至表尾的元素向后移动，次序从后向前
			this.element[j+1] = source[j];
		this.element[i] = x;
		this.n++;
		return i;                                              //返回x序号
	}
	//顺序表尾插入x元素，返回x序号。成员方法重载。插入操作中，this.n加1
	public int insert(T x)
	{
		return this.insert(this.n,x);
	}

	
	public T remove(int i)           //删除第i个元素，0<=i<n；返回被删除元素。若i越界，则返回null
	{
		if (this.n>0 && i>=0 && i<this.n)
		{ 
			T old = (T)this.element[i];                  //old中存储被删除元素
			for(int j = i;j<this.n-1;j++)
				this.element[j] = this.element[j+1];         //元素前移一个位置
			this.element[this.n-1] = null;                 //设置数组元素对象为空，释放原引用实例
			this.n--;
			return old;                              //返回old局部变量引用的对象，传递对象引用
		}
		return null;
	}
	
	public T remove(T key)
	{
		return this.remove(this.search(key));
	}
	
	public void clear()                                 //删除线性表所有元素
	{
		this.n = 0;                                  //设置长度为0，未释放数组空间
	}
	//顺序查找首次出现与key相等元素，返回元素序号i，0<=i<n；查找不成功返回-1
	//若key==null，则抛出空对象异常NullPointerException
	public int search(T key)
	{
		for (int i = 0;i<this.n;i++)
			if (key.equals(this.element[i]))               //执行T类的equals(Object)方法，运行时多态
				return i;
		return -1;                                      //空表或未找到时
	}
	//判断是否包含关键字为key元素
	public boolean contains(T key)
	{
		return this.search(key)!=-1;
	}
	
	public boolean equals(Object obj)                     //比较两个顺序表是否相等，覆盖
	{
		if (this==obj)                               //若this和obj引用同一个顺序表实例，则相等
			return true;
		if (obj instanceof SeqList<?>)                     //若obj引用顺序表实例。SeqList<?>是所有SeqList<T>的父类
		{
			SeqList<T>list = (SeqList<T>)obj;               //声明list也引用obj引用的实例
			if (this.n==list.n)                                //比较两者长度是否相等
			{
				for (int i = 0;i<this.n;i++)                   //比较两个顺序表的所有元素是否相等
					if(!(this.get(i).equals(list.get(i))))                     //equals(Object)方法运行时多态
						return true;
			}
		}
		return false;
	}
	public void addAll(SeqList<? extends T>list)                        //在this之后添加list的所有元素，集合并
	{
		for (int i = 0;i<list.n;i++)
			this.insert(list.get(i));                             //运行时多态，顺序表尾插入；排序顺序表按值插入
	}
	//思考题2-4
//	public <T> union(SeqList<? extends T>list)
//	{
//		SeqList<T> result = new SeqList<T>(this);
//		result.addAll(list);
//		return result;
//	}

    //【思考题2-4】习题解答
   
    //9.5.1 子类mergeAll合并、归并，不用，不声明

    boolean containsAll(SeqList<? extends T> list)  //判断是否包含set的所有元素（是否子集）
    {
        for (int i=0; i<list.n; i++) 
            if (!this.contains(list.get(i)))
                return false;
        return true;
    }
//不能    boolean containsAll(SeqList<?> list)
    
    boolean removeAll(SeqList<? extends T> list)           //删除也包含在set的元素，集合差
    {
        T old=null;
        for (int i=0; i<list.n; i++) 
            old=this.remove(list.get(i));
        return old!=null;
    }
    boolean retainAll(SeqList<? extends T> list)           //仅保留那些也包含在set的元素，集合差
    {
        return false;
    }

    //返回从begin～end元素组成的子表。意为返回T的某个子类
//    SeqList<? extends T> subList(int begin, int end)   //不需要该语法 
    SeqList<T> subList(int begin, int end)             //返回从begin～end组成的子表。含义与上同   
    {
        SeqList<T> list = new SeqList<T>();
        for (int i=begin; i<end; i++) 
            list.insert(this.get(i));
        return list;
    }    
   
    
    //第10章迭代器
    //10.2.1   提供迭代器对象
    //1.  顺序表类提供迭代器
    
    public java.util.Iterator<T>  iterator()               //返回Java迭代器对象
    {   return new SeqIterator();
    }
    private class SeqIterator  implements java.util.Iterator<T>//私有内部类，实现迭代器接口
    {
        int index=-1, succ=0;                              //当前元素和后继元素序号
        public boolean hasNext()                           //若有后继元素，返回true
        {
            return this.succ<SeqList.this.n;               //SeqList.this.n是外部类当前实例的成员变量
        }
        public T next()                                    //返回后继元素，若没有后继元素，返回null
        {
            T value = SeqList.this.get(this.succ);         //调用外部类SeqList当前实例的成员方法
            if (value!=null)
            {   this.index = this.succ++;
                return value;
            }
            throw new java.util.NoSuchElementException();  //抛出无此元素异常
        }
        public void remove()                               //删除迭代器对象表示的集合当前元素
        {
            if (this.index>=0 && this.index<SeqList.this.n)
            {   SeqList.this.remove(this.index);           //调用外部类SeqList当前实例的成员方法
                                                           //删除第index个元素，长度SeqList.this.n-1
                if (this.succ>0)
                    this.succ--;
                this.index=-1;                             //设置不能连续删除
            }
            else throw new java.lang.IllegalStateException(); //抛出无效状态异常
        }
    }//SeqIterator内部类结束


    //【思考题10-3】顺序表类提供列表迭代器。
    public java.util.ListIterator<T> listIterator()        //返回Java列表迭代器对象
    {
        return new SeqListIterator(0);
    }
    public java.util.ListIterator<T> listIterator(final int index) //返回Java列表迭代器对象
    {
        if (index>=0 && index<this.n)
            return new SeqListIterator(index);
        else throw new IndexOutOfBoundsException("Index: "+index);
    }

    //私有内部类，继承实现迭代器接口的SeqIterator内部类，实现列表迭代器接口
    private class SeqListIterator extends SeqIterator implements java.util.ListIterator<T>
    {
        public SeqListIterator(int index)
        {
            this.succ=index;
        }
        public boolean hasPrevious()                       //若有前驱元素，返回true
        {
            return this.succ!=0;
        }

        public T previous()                                //返回前驱元素
        {
            T value = SeqList.this.get(this.succ-1);
            if (value!=null)
            {
                this.index = this.succ--;
                return value;
            }
            throw new java.util.NoSuchElementException();  //抛出无此元素异常
        }
      
        public int nextIndex()                             //返回后继元素序号
        {
            return this.succ;
        }
        public int previousIndex()                         //返回前驱元素序号
        {
            return this.succ-1;
        } 
      
        public void set(T x)                               //将集合当前元素替换为x
        {
            if (this.index>=0 && this.index<SeqList.this.n)
                SeqList.this.set(this.index, x);           //调用外部类当前实例的成员方法
            else throw new java.lang.IllegalStateException(); //抛出无效状态异常
        }
        public void add(T x)                               //增加元素x
        {
            SeqList.this.insert(this.succ, x);             //调用外部类当前实例的成员方法
            this.succ++;                                   //插入元素为当前元素
        }
    }
}
