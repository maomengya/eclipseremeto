package ������1;
//�����ַ����࣬�����࣬ʵ�ֿɱȽϽӿں����л��ӿ�
public final class MyString implements Comparable<MyString>,java.io.Serializable
{
	private final char[] value;                             //�ַ����飬˽�����ձ�����ֻ�ܸ�ֵһ��
	public MyString()                                       //����մ�������������Ϊ0
	{
		this.value = new char[0];
	}
	public MyString(java.lang.String str)                    //���ַ����������촮
	{
		this.value = new char[str.length()];                  //�����ַ����鲢����str���������Ӵ�
		for(int i = 0; i<this.value.length;i++)
			this.value[i] = str.charAt(i);
	}
	
	//��value�����i��ʼ��n���ַ����촮��i>=0��n>=0��i+n<=value.length
	//��i��nָ�����Խ�磬���׳��ַ������Խ���쳣
	public MyString(char[] value,int i,int n)
	{
		if(i>=0&&n>=0&&i+n<=value.length)
		{
			this.value = new char[n];                          //�����ַ������鲢���������ַ�
			for(int j = 0;j<n;j++)
				this.value[j] = value[i+j];
		}
		else 
			throw new StringIndexOutOfBoundsException("i=" +i+",n="+",i+n="+(i+n));
	}
	
	public MyString(char[] value)                             //���ַ����鹹�촮
	{
		this(value,0,value.length);
	}
	
	public MyString(MyString str)                             //�������췽������ȿ����������ַ�
	{
		this(str.value);
	}
	public int length()                                       //���ش����ȣ����׳��ַ������Խ���쳣
	{
		return this.value.length;
	}
	public java.lang.String toString()
	{
		return new String(this.value);
	}
	
	//���ص�i���ַ���0<=i<length()����iԽ�磬���׳��ַ������Խ���쳣
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
	
	public MyString concat(MyString str)                            //����this��str���������ɵĴ�����srt==null������롰null��
	{
		if(str == null)
			str = new MyString("null");
		char[] buffer = new char[this.value.length+str.length()];
		int i;
		for(i = 0;i<this.value.length;i++)                         //���Ƶ�ǰ��
			buffer[i] = this.value[i];
		for(int j=0;j<str.value.length;j++)                        //����ָ����str
			buffer[i+j] = str.value[j];
		return new MyString(buffer);                                //���ַ����鹹�촮����
	}
	public int compareTo(MyString str)                              //�ʱȽ�this��str���Ĵ�С���������߲�ֵ
	{
		for(int i = 0;i<this.value.length;i++)
			if(this.value[i]!=str.value[i])
				return this.value[i] - str.value[i];                 //����������һ����ͬ�ַ��Ĳ�ֵ
		return this.value.length - str.value.length;                  //ǰ׺�Ӵ��������������ȵĲ�ֵ
	}
	
	//���ص�ǰ����Ŀ�괮�����׸���ģʽ��patternƥ����Ӵ���ţ�ƥ��ʧ��ʱ����-1
	public int indexOf(MyString pattern)
	{
		return this.indexOf(pattern,0);
	}
	//���ص�ǰ����Ŀ�괮����begin��ʼ�׸���ģʽ��patternƥ����Ӵ���ţ�ƥ��ʧ��ʱ����-1.
	//0<=begin<this.length()����begin�ݴ���begin<0����0��ʼ����begin���Խ�磬���Ҳ��ɹ�
	//��pattern==null���׳��ն����쳣
	public int indexOf(MyString pattern,int begin)
	{
		int n = this.length(),m = pattern.length();
		if(begin<0)                                //��begin�ݴ���begin<0����0��ʼ
			begin = 0;
		if(n==0||n<m||begin>=n)                      //��Ŀ�괮�գ��϶̻���beginԽ�磬����Ҫ�Ƚ�
			return -1;
		int i = begin,j = 0;                         //i��j�ֱ�ΪĿ�괮��ģʽ����ǰ�ַ��±�
		int count = 0;                                //���رȽϴ���
		while(i<n&&j<m)
		{
			count++;
			if(this.charAt(i)==pattern.charAt(j))               //����ǰ���ַ���ȣ�������ȽϺ����ַ�
			{
				i++;
				j++;
			}
			else                                            //����i��j���ݣ������´�ƥ��
			{ 
				i=i-j+1;                                  //Ŀ�괮�±�i���˻ص��¸�ƥ���Ӵ����
				j=0;                                       //ģʽ���±�j���˻ص�0
				if(i>n-m)                                 //��Ŀ�괮ʣ���Ӵ��ĳ��Ȳ��������ٱȽ�
					break;
			}
		}
		if(j==m)                                             //ƥ��ɹ�
			return i-j;                                        //����ƥ����Ӵ����
		return -1;                                             //ƥ��ʧ��ʱ����-1
	}
	
	//��target����������patternƥ����Ӵ�ȫ���滻��str�������滻���target
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
	//ɾ��target����������patternƥ����Ӵ�������ɾ�����target��
	public static StringBuffer removeAll(StringBuffer target,String pattern)
	{
		int n = target.length(),m = pattern.length();
		int empty = target.indexOf(pattern),next = empty;             //emptyΪ�׸���patternƥ���Ӵ����
		while (next!=-1)                                                //ѭ��ÿ��ɾ��һ��ƥ���Ӵ�
		{
			int move = next+m;                                          //moveΪ���ƶ��Ӵ����
			next = target.indexOf(pattern,move);                         //nextΪ��move��ʼ���¸�ƥ���Ӵ����
			while(next>0&&move<next||next<0&&move<0)                   //��move~next-1֮���Ӵ���ǰ�ƶ�
				target.setCharAt(empty++, target.charAt(move));
		}
		if(empty!=-1)
			target.setLength(empty);                                    //����target������Ϊempty
		return target;
	}
	
	public static void main(String args[])
	{
		StringBuffer target = new StringBuffer("aaaa");
		String pattern = "a",str = "ab";
		System.out.println("replaceAll(\""+target+"\",\""+pattern+"\",\""+str+"\")=\""+replaceAll(target,pattern,str)+"\"");
	}
}
