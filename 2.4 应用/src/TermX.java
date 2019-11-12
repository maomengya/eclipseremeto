//项类，一元多项式的一项，实现可比较接口和可相加接口
public class TermX implements Comparable<TermX>,Addible<TermX>
{
	protected int coef,xexp;                            //系数，x指数（可为正、0）.系数也可为double
	public TermX(int coef,int xexp)                       //构造一项
	{
		
	}
	public TermX(TermX term)                           //拷贝构造方法
	{
		
	}
	//以“系数x^指数”的省略形式构造一元多项式的一项
	//省略形式说明：当系数为1或者-1且指数>0时，省略1，-1只写负号“-”，如x^2,-x^3;
	//当指数为0时，省略x^0，只写系数；当指数为1时，省略^1,只写x
	public TermX(String termstr)
	{
		
	}
	//返回一项对应的“系数”的省略形式字符串，省略形式说明同TermX（String）方法
	public String toString()
	{
		return null;
		
	}
	public boolean equals(Object obj)              //按系数和指数比较两项是否相等
	{
		return false;
		
	}
	public int compareTo(TermX term)                 //按x指数比较两项大小，实习Comparable<T>接口
	{
		return coef;
		
	}
	public void add(TermX term)                     //若指数相同，则系数相加；实现Addible<T>
	{
		
	}
	public boolean removable()                       //若系数为0，则删除元素；实现Addible<T>接口
	{
		return false;
		
	}

}
