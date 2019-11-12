package 第三章1;
//常量字符串类，最终类，实现可比较接口和序列化接口
public final class MyString implements Comparable<MyString>,java.io.Serializable
{
	private final char[] value;                             //字符数组，私有最终变量，只能赋值一次
	public MyString()                                       //构造空串“”，串长度为0
	{
		this.value = new char[0];
	}
	public MyString(java.lang.String str)                    //由字符串常量构造串
	{
		this.value = new char[str.length()];                  //申请字符数组并复制str串的所有子串
		for(int i = 0; i<this.value.length;i++)
			this.value[i] = str.charAt(i);
	}
	
	//以value数组从i开始的n个字符构造串，i>=0，n>=0，i+n<=value.length
	//以i与n指定序号越界，则抛出字符串序号越界异常
	public MyString(char[] value,int i,int n)
	{
		if(i>=0&&n>=0&&i+n<=value.length)
		{
			this.value = new char[n];                          //申请字符串数组并复制所有字符
			for(int j = 0;j<n;j++)
				this.value[j] = value[i+j];
		}
		else 
			throw new StringIndexOutOfBoundsException("i=" +i+",n="+",i+n="+(i+n));
	}
	
	public MyString(char[] value)                             //以字符数组构造串
	{
		this(value,0,value.length);
	}
	
	public MyString(MyString str)                             //拷贝构造方法，深度拷贝，复制字符
	{
		this(str.value);
	}
	public int length()                                       //返回串长度，则抛出字符串序号越界异常
	{
		return this.value.length;
	}
	public java.lang.String toString()
	{
		return new String(this.value);
	}
	
	//返回第i个字符，0<=i<length()。若i越界，则抛出字符串序号越界异常
	public char charAt(int i)
	{
		if(i>=0&&i<this.value.length)
			return this.value[i];
		throw new StringIndexOutOfBoundsException(i);
	}

	public MyString substring(int begin,int end)
	{
		if (begin == 0&&end==this.value.length)
			return this;
		return new MyString(this.value,begin,end-begin);
	}	
	
	public MyString substring(int begin)
	{
		return substring(begin,this.value.length);
	}
	
	public MyString concat(MyString str)                            //返回this与str串连接生成的串。若srt==null，则插入“null”
	{
		if(str == null)
			str = new MyString("null");
		char[] buffer = new char[this.value.length+str.length()];
		int i;
		for(i = 0;i<this.value.length;i++)                         //复制当前串
			buffer[i] = this.value[i];
		for(int j=0;j<str.value.length;j++)                        //复制指定串str
			buffer[i+j] = str.value[j];
		return new MyString(buffer);                                //以字符数组构造串对象
	}
	public int compareTo(MyString str)                              //笔比较this与str串的大小，返回两者差值
	{
		for(int i = 0;i<this.value.length;i++)
			if(this.value[i]!=str.value[i])
				return this.value[i] - str.value[i];                 //返回两串第一个不同字符的差值
		return this.value.length - str.value.length;                  //前缀子串，返回两串长度的差值
	}
	
	//返回当前串（目标串）中首个与模式串pattern匹配的子串序号，匹配失败时返回-1
	public int indexOf(MyString pattern)
	{
		return this.indexOf(pattern,0);
	}
	//返回当前串（目标串）从begin开始首个与模式串pattern匹配的子串序号，匹配失败时返回-1.
	//0<=begin<this.length()。对begin容错，若begin<0，从0开始；若begin序号越界，查找不成功
	//若pattern==null，抛出空对象异常
	public int indexOf(MyString pattern,int begin)
	{
		int n = this.length(),m = pattern.length();
		if(begin<0)                                //对begin容错，若begin<0，从0开始
			begin = 0;
		if(n==0||n<m||begin>=n)                      //若目标串空，较短或者begin越界，不需要比较
			return -1;
		int i = begin,j = 0;                         //i、j分别为目标串和模式串当前字符下标
		int count = 0;                                //记载比较次数
		while(i<n&&j<m)
		{
			count++;
			if(this.charAt(i)==pattern.charAt(j))               //若当前两字符相等，则继续比较后续字符
			{
				i++;
				j++;
			}
			else                                            //否则i、j回溯，进行下次匹配
			{ 
				i=i-j+1;                                  //目标串下标i，退回到下个匹配子串序号
				j=0;                                       //模式串下标j，退回到0
				if(i>n-m)                                 //若目标串剩余子串的长度不够，不再比较
					break;
			}
		}
		if(j==m)                                             //匹配成功
			return i-j;                                        //返回匹配的子串序号
		return -1;                                             //匹配失败时返回-1
	}
	
	//将target串中所有与pattern匹配的子串全部替换成str，返回替换后的target
	public static StringBuffer replaceAll(StringBuffer target,String pattern,String str)
	{
		int i = target.indexOf(pattern);
		while (i!=1)
		{
			target.delete(i, i+pattern.length());
			target.insert(i, str);
			i = target.indexOf(pattern,i+str.length());
		}
		return target;
	}
	//删除target串中所有与pattern匹配的子串，返回删除后的target串
	public static StringBuffer removeAll(StringBuffer target,String pattern)
	{
		int n = target.length(),m = pattern.length();
		int empty = target.indexOf(pattern),next = empty;             //empty为首个与pattern匹配子串序号
		while (next!=-1)                                                //循环每次删除一个匹配子串
		{
			int move = next+m;                                          //move为待移动子串序号
			next = target.indexOf(pattern,move);                         //next为从move开始的下个匹配子串序号
			while(next>0&&move<next||next<0&&move<0)                   //将move~next-1之间子串向前移动
				target.setCharAt(empty++, target.charAt(move));
		}
		if(empty!=-1)
			target.setLength(empty);                                    //设置target串长度为empty
		return target;
	}
	
	public static void main(String args[])
	{
		StringBuffer target = new StringBuffer("aaaa");
		String pattern = "a",str = "ab";
		System.out.println("replaceAll(\""+target+"\",\""+pattern+"\",\""+str+"\")=\""+replaceAll(target,pattern,str)+"\"");
	}
}
