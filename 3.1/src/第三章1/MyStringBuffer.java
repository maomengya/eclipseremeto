package 第三章1;
//变量字符串类，最终类，实现序列化接口
public final class MyStringBuffer implements java.io.Serializable
{
	private char[] value;                           //字符数组，私有成员变量
	private  MyStringBuffer[] value1;
	private int n;                                  //串长度
	//构造方法
	public MyStringBuffer(int capacity)             //构造容量为capacity的空串
	{
		this.value = new char[capacity];
		this.n = 0;
	}
	
	public MyStringBuffer()                          //以默认容量构造空串
	{
		this(16);
	}
	
	public MyStringBuffer(String str)                  //以字符串容量构造空串
	{
		this(str.length()+16);
		this.n = str.length();                        //复制str串所有字符
		for(int i = 0;i<this.n;i++)
			this.value[i] = str.charAt(i);
	}
	
	public int length()                                //返回字符串长度
	{
		return this.n;
	}
	
	public int capacity()                                //返回字符串数组容量
	{
		return this.value.length;
	}
	
	public synchronized String toString()
	{
		return new String(this.value,0,this.n);             //以value数组从0至n字符构造String串
	}
	//以下方法体省略
//	public synchronized char charAt(int i)                //返回第i个字符，0<=i<length()
//	public void setCharAt(int i,char ch)                   //设置第i个字符为ch，0<=i<length()

	//在第i个字符处插入str串，0<=i<length().若i序号越界，抛出异常；若str == null，插入“null”
	public synchronized MyStringBuffer insert(int i,String str)
	{
		if(this.n==0&&i==0||this.n>0&&i>=0&&i<=this.n)
		{
			if (str == null)
				str = "null";
			char[] temp = this.value;
			if(this.value.length<this.n+str.length())                      //若数组空间不足，则扩充
			{
				this.value = new char[(this.value.length+str.length()*2)];    //重新申请字符数组空间
				for(int j = 0;j<i;j++)                                        //复制当前串前i-1个字符
					this.value[j] = temp[j];
			}
			for(int j = this.n-1;j>=i;j--)                                     //从i开始至串尾的子串向后移动，次序从后向前
				this.value[j+str.length()] = temp[j];
			for(int j = 0;j<str.length();j++)                                 //插入str串
				this.value[i+j] = str.charAt(j);
			this.n+=str.length();
			return this;
		}
		else 
			throw new StringIndexOutOfBoundsException("i="+i);                 //抛出字符串序号越界异常
	}
	
	public synchronized MyStringBuffer insert(int i,MyStringBuffer sbuf)             //算法略
	{
		return null;
	}
	
	public synchronized MyStringBuffer insert(int i,boolean b)               //在i处插入变量值转换成的串
	{
		return this.insert(i, b?"true":"false");
	}
	
	public synchronized MyStringBuffer append(String str)                      //添加str串
	{
		return this.insert(this.n, str);
	}
	
	//删除从begin到end-1的子串，0<=begin<length(),end>=0,begin<=end；
	//若end>=length()，删除到串尾；若begin越界，或者begin>end抛出字符串序号越界异常
	public synchronized MyStringBuffer delete(int begin , int end)
	{
		if (begin>=0&&begin<this.n && end>=0 && begin<=end)
		{
			if (end>this.n)                                      //end超长容错
				end = this.n;
			for(int i = 0;i<this.n-end;i++)                      //从end开始至串尾的子串向前移动
				this.value[begin+i] = this.value[end+1];
			this.n -= end-begin;
			return this;
		}
		else
			throw new StringIndexOutOfBoundsException("begin="+begin+",end"+",end-begin="+(end-begin));
	}
	
	//删除target串中所有与pattern匹配的子串，返回删除后的target串
	public static StringBuffer removeAll(StringBuffer target,String pattern)
	{
		int n = target.length(),m = pattern.length();
		int empty = target.indexOf(pattern),next = empty;                               //empty为首个与pattern匹配子串序号
		while(next!=-1)                                                                  //循环每次删除一个匹配子串
		{
			int move = next+m;                                                           //move为待移动子串序号
			next = target.indexOf(pattern,move);                                          //next为从move开始的下一个匹配子串序号
			while(next>0&&move<next||next<0&&move<n)                                      //将move~next-1之间子串向前移动    
				target.setCharAt(empty++, target.charAt(move++));
		}
		if (empty!=-1)
			target.setLength(empty);                                                    //设置target串长度为empty
		return target;
	}
	
	 public synchronized char charAt(int i)                 //返回第i个字符，0≤i<length()
	 {
	    if (i>=0 && i<this.n)
	        return this.value[i];
	    throw new StringIndexOutOfBoundsException(i);
	 }
	 
	 //返回当前串（目标串）中首个与模式串pattern匹配的子串序号，匹配失败时返回-1
	 public int indexOf(MyString pattern)
	 {
		 return this.indexOf(pattern,0);
	 }
	 
	 //返回当前串（目标串）从begin开始首个与模式串pattern匹配的子串序号，匹配失败返回时-1
	 //0<=begin<this.length().对begin容错，若begin<0，从0开始；若begin序号越界，查找不成功
	 //若pattern == null，抛出空对象异常
	 public int indexOf(MyString pattern,int begin)
	 {
		int n = this.length(),m = pattern.length();
		if(begin<0)                                            //对begin容错，若begin<0，从0开始
			begin = 0;
		if(n == 0||n<m||begin>=0)                              //若目标串空、较短或begin越界，不需比较
			return -1;
		int i = begin,j = 0;                                    //i、j分别为目标串和模式串当前字符下标
		int count = 0;                                          //记载比较次数
		while (i<n&&j<m)
		{
			count++;
			if(this.charAt(i) == pattern.charAt(j))             //若当前两字符相等，则继续比较后续字符
			{
				i++;
				j++;
			}
			else                                                //否则i、j回溯，进行下次匹配
			{ 
				i = i-j+1;                                      //目标串i，退回到下个匹配子串序号
				j = 0;                                          //模式串下标j，退回到0
				if(i>n-m)                                       //若目标串剩余子串的长度不够，不再比较
					break;
			}	
		 }
		if(j == m)                                               //匹配成功
			return i - j ;                                       //返回匹配的子串序号
		return -1;                                               //匹配失败返回-1
	 }
	 
	 //返回模式串pattern的next数组
	 public static int[] getNext(String pattern)
	 {
		 int j = 0,k = -1,next[] = new int[pattern.length()];
		 next[0] = -1;
		 while (j<pattern.length()-1)
			 if(k == -1||pattern.charAt(j) == pattern.charAt(k))
			 {
				 j++;
				 k++;
				 next[j] = k;                                     //有序改进
			 }
			 else
				 k = next[k];
		 return next;
	 }
	 
	 //改进计算方法
	 private static int[] getNext2(String pattern)         ///返回模式串pattern改进的next数组
	 {
		 int j = 0,k = -1,next[] = new int[pattern.length()];
		 next[0] = -1;
		 while (j<pattern.length()-1)
			 if(k == -1||pattern.charAt(j) == pattern.charAt(k))
			 {
				 j++;
				 k++;
				 if(pattern.charAt(j)!=pattern.charAt(k))    //改进之处
					 next[j] = k;
				 else
					 next[j] = next[k];
			 }
			 else
				 k = next[k];
		 return next;
	 }
	 
	 //将target串中所有与pattern匹配的子串全部换成str，返回替换后的target串
	 public static StringBuffer replaceAll(StringBuffer target,String pattern,String str)
	 {
		 int i = target.indexOf(pattern);
		 while(i!=-1)
		 {
			 target.delete(i, i+pattern.length());
			 target.insert(i, str);
			 i = target.indexOf(pattern,i+str.length());
		 }
		 return target;
	 }

	//实验3-10
	//删除第i个字符，返回当前串
	public synchronized MyStringBuffer deleteCharAt(int i)
	{
		for(int j = i;j<n;j++)
			this.value[j] = this.value[j+1];
		this.n  = this.n - 1;
		return this;
	}
	//将当前串逆转，返回当前串
	public synchronized MyStringBuffer reverse()
	{
		if(this.value.length == 0)
			return null;
		
		for(int i = 0,j = this.n-1;i<j;i++,j--)
		{
			char t = this.value[i];
			this.value[i] = this.value[j];
			this.value[j] = t;
		}
		return this;
	}
	
	//实验3-11
	//将所有小写字母转换成对应的大写字母
	public synchronized MyStringBuffer toUpperCase(StringBuffer s)
	{
		for(int i = 0;i<value.length;i++)
			if((value[i] >= 'a')&&value[i] <= 'z')
				value[i] = (char) (value[i] - 'a' + 'A');
		return this;
	}
	//将所有的大写字母转换成对应的小写字母
	public synchronized MyStringBuffer toLowerCase(StringBuffer S2)
	{
		//this.value = new char[S2.length()];
//		System.out.println(this.value);
//		System.out.println(this.n);
//		char[] result = new char[this.n];
		//System.out.println(result);
		
		for(int i = 0;i<this.n;i++)
			if((this.value[i] >= 'A')&&this.value[i]<= 'Z')
				{
					this.value[i] = (char) (this.value[i] - 'A' + 'a');
				}
//			else
//				this.value[i] = (char) (this.value[i]);
		
//		System.out.println(this.value);
//		System.out.println(result);
		System.out.println(this.getClass());
		System.out.println(this);
		return this;
	}
	
	public synchronized MyStringBuffer trim(StringBuffer S3)
	{
		for(int i = 0; i<this.n;i++)
			if(this.value[i] == ' ')
			{
				for(int j = i;j<this.n-1;j++)
				this.value[j] = this.value[j+1];
				this.n --;	
				i--;
			}
		return this;
	}
	
//	public synchronized MyStringBuffer trim(StringBuffer S3)
//	{
//		MyStringBuffer pattern = new MyStringBuffer();
//		System.out.println(pattern.value);
////		System.out.println(111);
//		for(int i = 0,j = 0;i<this.n;i++)
//		{
//			if(this.value[i] != ' ')
//			{
//				pattern.setvalue[j] = this.value[i];
//				j++;
////				System.out.println();
//			}
//			System.out.println(pattern.value);
//		}
//		System.out.println(this.value);
//		System.out.println(this);
//		System.out.println(pattern.value);
//		return pattern;
//	}
	
//	删除串中所有空格字符
	
//	public MyString trim()                              //算法使用字符数组
//	{
//		char[] buffer = new char[this.value.length];
//		int n = 0;
//		for(int i = 0;i<this.value.length;i++)      //复制串一次
//			if(this.value[i]!=' ')
//				buffer[n++] = this.value[i];
//		return new MyString(buffer,0,n);
//		//以buffer数组从0开始的n个字符构造，复制
//	}
	
	//将s中所有空格删除，返回操作后的s串
//	public static StringBuffer trim(StringBuffer s)
//	{
//		int n = s.length();
//		for(int i =0 ; i<n;i++)
//			if(s.charAt(i)==' ')
//				s.delete(i, i+1);
//		return s;
//	}
	
	//比较串s1、串s2是否相等（有误）
	public boolean equals(StringBuffer s1,StringBuffer s2)
	{
		char[] value1 = new char[s1.length()];
		char[] value2 = new char[s2.length()];
		if(value1.length != value2.length)
			return false;
		for(int i = 0;i < value1.length;i++)
			if(value1[i] != value2[i])
				return false;
		System.out.println(12);
		return true;
	}
	
	//比较两串是否相等，忽略字母大小写（有误）
	public boolean equalsIgnoreCase(StringBuffer s1,StringBuffer s2)
	{
		char[] value1 = new char[s1.length()];
		char[] value2 = new char[s2.length()];
		if(value1.length != value2.length)
			return false;
		for(int i = 0;i < value1.length;i++)
			if((value1[i] != value2[i])&& (value1[i]!= value2[i]-'a'+'A')&&(value1[i]!= value2[i]-'A'+'a'))
				return false;
		return true;
	}
	//比较串s1、s2大小，返回两者差值（有误）
	public int compareTo(StringBuffer s1,StringBuffer s2)
	{
		char[] value1 = new char[s1.length()];
		char[] value2 = new char[s2.length()];
		if(value1.length != value2.length)
			return value1.length - value2.length;
		for(int i = 0;i < value1.length;i++)
			if(value1[i] != value2[i])
				return value1[i] - value2[i];
		return 0;
	}
	
	//比较两串大小，忽略大小写（有误）
	public int compareToIgnoreCase(StringBuffer s1,StringBuffer s2)
	{
		char[] value1 = new char[s1.length()];
		char[] value2 = new char[s2.length()];
		if(value1.length > value2.length)
			return 1;
		else
			if(value1.length < value2.length)
			return -1;
		
		for(int i = 0;i < value1.length;i++)
			{
			if((value1[i] > value2[i])&& (value1[i]!= value2[i]-'a'+'A')&&(value1[i]!= value2[i]-'A'+'a'))
				return 1;
			else
				if((value1[i] > value2[i])&& (value1[i]!= value2[i]-'a'+'A')&&(value1[i]!= value2[i]-'A'+'a'))
					return 1;
			}
		return 0;
	}
	
	public static void main(String args[])
	{
//		StringBuffer sbuf = new StringBuffer(8);
//		System.out.println("空串，\""+sbuf+"\",length()="+sbuf.length()+",capacity()="+sbuf.capacity());
//		sbuf.insert(0, "abcdef");                                  //插入串，没用用到返回值
//		System.out.println("插入，\""+sbuf+"\",length()="+sbuf.length()+",capacity()="+sbuf.capacity());
//		String[] str = {"xy",null};                                //序号i不容错，抛出异常
//		int i = 2;       
//		for(int j = 0;j<str.length;j++)
//			System.out.println("插入，\""+sbuf+"\".insert("+i+",\""+str[j]+"\")=\""+sbuf.insert(i,str[j])+"\",length()="+sbuf.length()+",capacity()="+sbuf.capacity());
//		int[] begin = {2,4,2},end = {6,10,2};
//		for(int j = 0;j<begin.length;j++)                         //删除子串
//			System.out.println("删除，\""+sbuf+"\".delete("+begin[j]+","+end[j]+")=\""+sbuf.delete(begin[j], end[j])+"\",length()="+sbuf.length()+",capacity()="+sbuf.capacity());
//		
		System.out.println();
		String s = "assdcnsid";
		StringBuffer S = new StringBuffer(s);
		MyStringBuffer sbuf = new MyStringBuffer(s);
		System.out.println(sbuf.deleteCharAt(3));
		System.out.println(sbuf.reverse());
	
		String s1 = "asSdASsid", s2 = "asSdASsiD",s3 = "s ds  sa";
		
		StringBuffer S2 = new StringBuffer(s2);
		MyStringBuffer sbuf2 = new MyStringBuffer(s2);
		System.out.println(sbuf);
		System.out.println(S2);
		sbuf.insert(0, s2);
		System.out.println(sbuf);
		System.out.println(sbuf2.toUpperCase(S2));
		System.out.println(sbuf2.toLowerCase(S2));
		
		StringBuffer S3 = new StringBuffer(s3);
		MyStringBuffer sbuf3 = new MyStringBuffer(s3);
		System.out.println(sbuf3.trim(S3));
		System.out.println(s1.equals(s2));
		System.out.println(s1.equalsIgnoreCase(s2));
		System.out.println(s1.compareTo(s2));
		System.out.println(s1.compareToIgnoreCase(s2));
		
	}
}
