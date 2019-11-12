package ������1;
//�����ַ����࣬�����࣬ʵ�����л��ӿ�
public final class MyStringBuffer implements java.io.Serializable
{
	private char[] value;                           //�ַ����飬˽�г�Ա����
	private  MyStringBuffer[] value1;
	private int n;                                  //������
	//���췽��
	public MyStringBuffer(int capacity)             //��������Ϊcapacity�Ŀմ�
	{
		this.value = new char[capacity];
		this.n = 0;
	}
	
	public MyStringBuffer()                          //��Ĭ����������մ�
	{
		this(16);
	}
	
	public MyStringBuffer(String str)                  //���ַ�����������մ�
	{
		this(str.length()+16);
		this.n = str.length();                        //����str�������ַ�
		for(int i = 0;i<this.n;i++)
			this.value[i] = str.charAt(i);
	}
	
	public int length()                                //�����ַ�������
	{
		return this.n;
	}
	
	public int capacity()                                //�����ַ�����������
	{
		return this.value.length;
	}
	
	public synchronized String toString()
	{
		return new String(this.value,0,this.n);             //��value�����0��n�ַ�����String��
	}
	//���·�����ʡ��
//	public synchronized char charAt(int i)                //���ص�i���ַ���0<=i<length()
//	public void setCharAt(int i,char ch)                   //���õ�i���ַ�Ϊch��0<=i<length()

	//�ڵ�i���ַ�������str����0<=i<length().��i���Խ�磬�׳��쳣����str == null�����롰null��
	public synchronized MyStringBuffer insert(int i,String str)
	{
		if(this.n==0&&i==0||this.n>0&&i>=0&&i<=this.n)
		{
			if (str == null)
				str = "null";
			char[] temp = this.value;
			if(this.value.length<this.n+str.length())                      //������ռ䲻�㣬������
			{
				this.value = new char[(this.value.length+str.length()*2)];    //���������ַ�����ռ�
				for(int j = 0;j<i;j++)                                        //���Ƶ�ǰ��ǰi-1���ַ�
					this.value[j] = temp[j];
			}
			for(int j = this.n-1;j>=i;j--)                                     //��i��ʼ����β���Ӵ�����ƶ�������Ӻ���ǰ
				this.value[j+str.length()] = temp[j];
			for(int j = 0;j<str.length();j++)                                 //����str��
				this.value[i+j] = str.charAt(j);
			this.n+=str.length();
			return this;
		}
		else 
			throw new StringIndexOutOfBoundsException("i="+i);                 //�׳��ַ������Խ���쳣
	}
	
	public synchronized MyStringBuffer insert(int i,MyStringBuffer sbuf)             //�㷨��
	{
		return null;
	}
	
	public synchronized MyStringBuffer insert(int i,boolean b)               //��i���������ֵת���ɵĴ�
	{
		return this.insert(i, b?"true":"false");
	}
	
	public synchronized MyStringBuffer append(String str)                      //���str��
	{
		return this.insert(this.n, str);
	}
	
	//ɾ����begin��end-1���Ӵ���0<=begin<length(),end>=0,begin<=end��
	//��end>=length()��ɾ������β����beginԽ�磬����begin>end�׳��ַ������Խ���쳣
	public synchronized MyStringBuffer delete(int begin , int end)
	{
		if (begin>=0&&begin<this.n && end>=0 && begin<=end)
		{
			if (end>this.n)                                      //end�����ݴ�
				end = this.n;
			for(int i = 0;i<this.n-end;i++)                      //��end��ʼ����β���Ӵ���ǰ�ƶ�
				this.value[begin+i] = this.value[end+1];
			this.n -= end-begin;
			return this;
		}
		else
			throw new StringIndexOutOfBoundsException("begin="+begin+",end"+",end-begin="+(end-begin));
	}
	
	//ɾ��target����������patternƥ����Ӵ�������ɾ�����target��
	public static StringBuffer removeAll(StringBuffer target,String pattern)
	{
		int n = target.length(),m = pattern.length();
		int empty = target.indexOf(pattern),next = empty;                               //emptyΪ�׸���patternƥ���Ӵ����
		while(next!=-1)                                                                  //ѭ��ÿ��ɾ��һ��ƥ���Ӵ�
		{
			int move = next+m;                                                           //moveΪ���ƶ��Ӵ����
			next = target.indexOf(pattern,move);                                          //nextΪ��move��ʼ����һ��ƥ���Ӵ����
			while(next>0&&move<next||next<0&&move<n)                                      //��move~next-1֮���Ӵ���ǰ�ƶ�    
				target.setCharAt(empty++, target.charAt(move++));
		}
		if (empty!=-1)
			target.setLength(empty);                                                    //����target������Ϊempty
		return target;
	}
	
	 public synchronized char charAt(int i)                 //���ص�i���ַ���0��i<length()
	 {
	    if (i>=0 && i<this.n)
	        return this.value[i];
	    throw new StringIndexOutOfBoundsException(i);
	 }
	 
	 //���ص�ǰ����Ŀ�괮�����׸���ģʽ��patternƥ����Ӵ���ţ�ƥ��ʧ��ʱ����-1
	 public int indexOf(MyString pattern)
	 {
		 return this.indexOf(pattern,0);
	 }
	 
	 //���ص�ǰ����Ŀ�괮����begin��ʼ�׸���ģʽ��patternƥ����Ӵ���ţ�ƥ��ʧ�ܷ���ʱ-1
	 //0<=begin<this.length().��begin�ݴ���begin<0����0��ʼ����begin���Խ�磬���Ҳ��ɹ�
	 //��pattern == null���׳��ն����쳣
	 public int indexOf(MyString pattern,int begin)
	 {
		int n = this.length(),m = pattern.length();
		if(begin<0)                                            //��begin�ݴ���begin<0����0��ʼ
			begin = 0;
		if(n == 0||n<m||begin>=0)                              //��Ŀ�괮�ա��϶̻�beginԽ�磬����Ƚ�
			return -1;
		int i = begin,j = 0;                                    //i��j�ֱ�ΪĿ�괮��ģʽ����ǰ�ַ��±�
		int count = 0;                                          //���رȽϴ���
		while (i<n&&j<m)
		{
			count++;
			if(this.charAt(i) == pattern.charAt(j))             //����ǰ���ַ���ȣ�������ȽϺ����ַ�
			{
				i++;
				j++;
			}
			else                                                //����i��j���ݣ������´�ƥ��
			{ 
				i = i-j+1;                                      //Ŀ�괮i���˻ص��¸�ƥ���Ӵ����
				j = 0;                                          //ģʽ���±�j���˻ص�0
				if(i>n-m)                                       //��Ŀ�괮ʣ���Ӵ��ĳ��Ȳ��������ٱȽ�
					break;
			}	
		 }
		if(j == m)                                               //ƥ��ɹ�
			return i - j ;                                       //����ƥ����Ӵ����
		return -1;                                               //ƥ��ʧ�ܷ���-1
	 }
	 
	 //����ģʽ��pattern��next����
	 public static int[] getNext(String pattern)
	 {
		 int j = 0,k = -1,next[] = new int[pattern.length()];
		 next[0] = -1;
		 while (j<pattern.length()-1)
			 if(k == -1||pattern.charAt(j) == pattern.charAt(k))
			 {
				 j++;
				 k++;
				 next[j] = k;                                     //����Ľ�
			 }
			 else
				 k = next[k];
		 return next;
	 }
	 
	 //�Ľ����㷽��
	 private static int[] getNext2(String pattern)         ///����ģʽ��pattern�Ľ���next����
	 {
		 int j = 0,k = -1,next[] = new int[pattern.length()];
		 next[0] = -1;
		 while (j<pattern.length()-1)
			 if(k == -1||pattern.charAt(j) == pattern.charAt(k))
			 {
				 j++;
				 k++;
				 if(pattern.charAt(j)!=pattern.charAt(k))    //�Ľ�֮��
					 next[j] = k;
				 else
					 next[j] = next[k];
			 }
			 else
				 k = next[k];
		 return next;
	 }
	 
	 //��target����������patternƥ����Ӵ�ȫ������str�������滻���target��
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

	//ʵ��3-10
	//ɾ����i���ַ������ص�ǰ��
	public synchronized MyStringBuffer deleteCharAt(int i)
	{
		for(int j = i;j<n;j++)
			this.value[j] = this.value[j+1];
		this.n  = this.n - 1;
		return this;
	}
	//����ǰ����ת�����ص�ǰ��
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
	
	//ʵ��3-11
	//������Сд��ĸת���ɶ�Ӧ�Ĵ�д��ĸ
	public synchronized MyStringBuffer toUpperCase(StringBuffer s)
	{
		for(int i = 0;i<value.length;i++)
			if((value[i] >= 'a')&&value[i] <= 'z')
				value[i] = (char) (value[i] - 'a' + 'A');
		return this;
	}
	//�����еĴ�д��ĸת���ɶ�Ӧ��Сд��ĸ
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
	
//	ɾ���������пո��ַ�
	
//	public MyString trim()                              //�㷨ʹ���ַ�����
//	{
//		char[] buffer = new char[this.value.length];
//		int n = 0;
//		for(int i = 0;i<this.value.length;i++)      //���ƴ�һ��
//			if(this.value[i]!=' ')
//				buffer[n++] = this.value[i];
//		return new MyString(buffer,0,n);
//		//��buffer�����0��ʼ��n���ַ����죬����
//	}
	
	//��s�����пո�ɾ�������ز������s��
//	public static StringBuffer trim(StringBuffer s)
//	{
//		int n = s.length();
//		for(int i =0 ; i<n;i++)
//			if(s.charAt(i)==' ')
//				s.delete(i, i+1);
//		return s;
//	}
	
	//�Ƚϴ�s1����s2�Ƿ���ȣ�����
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
	
	//�Ƚ������Ƿ���ȣ�������ĸ��Сд������
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
	//�Ƚϴ�s1��s2��С���������߲�ֵ������
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
	
	//�Ƚ�������С�����Դ�Сд������
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
//		System.out.println("�մ���\""+sbuf+"\",length()="+sbuf.length()+",capacity()="+sbuf.capacity());
//		sbuf.insert(0, "abcdef");                                  //���봮��û���õ�����ֵ
//		System.out.println("���룬\""+sbuf+"\",length()="+sbuf.length()+",capacity()="+sbuf.capacity());
//		String[] str = {"xy",null};                                //���i���ݴ��׳��쳣
//		int i = 2;       
//		for(int j = 0;j<str.length;j++)
//			System.out.println("���룬\""+sbuf+"\".insert("+i+",\""+str[j]+"\")=\""+sbuf.insert(i,str[j])+"\",length()="+sbuf.length()+",capacity()="+sbuf.capacity());
//		int[] begin = {2,4,2},end = {6,10,2};
//		for(int j = 0;j<begin.length;j++)                         //ɾ���Ӵ�
//			System.out.println("ɾ����\""+sbuf+"\".delete("+begin[j]+","+end[j]+")=\""+sbuf.delete(begin[j], end[j])+"\",length()="+sbuf.length()+",capacity()="+sbuf.capacity());
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
