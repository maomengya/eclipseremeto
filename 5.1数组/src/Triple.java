
public class Triple implements Comparable<Triple> ,Addible<Triple>
{
	int row,column,value;
	//构造方法，参数依次指定行号、列号、元素值。若行号、列号为负，则抛出无效参数异常
	public Triple(int row,int column,int value)
	{
		if(row>=0&&column>=0)
		{
			this.row = row;
			this.column = column;
			this.value = value;
		}
		else
			throw new IllegalArgumentException("行、列号不能为负数：row="+row+",column="+column);
	}
	
	public Triple(Triple tri)               //拷贝构造方法，复制一个三元组
	{
		this(tri.row,tri.column,tri.value);
	}
	
	public String toString()               //返回三元组描述字符串
	{
		return "("+row+","+column+","+value+")";
	}
	//根据行、列位置比较三元组对象大小，与元素值无关，约定三元组排序次序
	public int compareTo(Triple tri)
	{
		if(this.row == this.row&&this.column == tri.column)               //相等，与equals()方法含义不同
			return 0;
		return (this.row<tri.row||this.row==tri.row&&this.column<tri.column)?-1:1;
	}
	
//	public boolean equals(Object obj)               //比较三元组是否相等，比较位置和元素值，方法体省略
//	{
//		
//	}
	
	public void add(Triple term)               //加法（+=运算），实现Addible<T>接口
	{
		if(this.compareTo(term)==0)
			this.value+= term.value;
		else
			throw new IllegalArgumentException("两项的指数不同，不能相加.");
	}
	
	public boolean removable()               //约定删除元素条件，现实Addible<T>接口
	{
		return this.value == 0;               //不存储值为0的元素
	}
	
	public Triple toSymmetry()               //返回矩阵对称位置的三元组
	{
		return new Triple(this.column,this.row,this.value);
	}
}
