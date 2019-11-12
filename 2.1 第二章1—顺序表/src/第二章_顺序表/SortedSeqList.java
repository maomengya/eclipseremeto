package 第二章_顺序表;
//排序顺序表类（升序），T或T的某个祖先类实现Comparable<T>接口；继承顺序表类
public class SortedSeqList<T extends Comparable<?super T>> extends SeqList<T>
{
	public SortedSeqList()                        //构造空排序顺序表
	{
		super();                                //默认调用父类构造方法SeqList()
	}
	public SortedSeqList(SeqList<Student> lista)                  //构造空排序顺序表，容量为length
	{
		super(lista);                                //调用SeqList(length)。若省略，则默认调用super()
	}
	public SortedSeqList(T[] values)                  //构造排序顺序表，由数组提供元素，O(n^2)
	{
		super(values.length);                    //创建空排序顺序表，指定容量
		for (int i = 0;i<values.length;i++)
			this.insert(values[i]);                      //插入元素，根据对象大小确定插入位置，O(n)
	}

	public SortedSeqList(int i) {
		super(i);
	}
	
	public int insert(T x)
	{
		int i = 0;
		if(this.isEmpty()||x.compareTo(this.get(this.size()-1))>0)        //compareTo(T)比较大小
			i = this.n;                                                  //最大值尾插入，O(1)
		else
			while(i < this.n && x.compareTo(this.get(i))>0)                  //寻找插入位置（升序）
				i++;
		super.insert(i,x);                                          //调用父类被覆盖的insert(i,x)方法，插入x作为第i个元素
		return i;
	}
	
//	public int inserDifferent(T x)                            //插入不重复元素。查找不成功时，按值插入。覆盖，方法体省略

	int start;
	//不支持父类的以下两个方法，将其覆盖并抛出异常
	public void set(int i,T x)                               //只读特性
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
