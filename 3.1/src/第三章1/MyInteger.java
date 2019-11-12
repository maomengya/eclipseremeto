package ������1;

public final class MyInteger implements Comparable<MyInteger>      //�����࣬������
{
	public static final int MIN_VALUE = 0x80000000;                 //��Сֵ������-2^31 = -2147483647
	public static final int MAX_VALUE = 0x7fffffff;                 //���ֵ������2^31 = 2147483647
	private final int value;                                        //˽�����ձ������洢������ֵһ��
	public MyInteger(int value)                                     //��int����value������������
	{
		this.value = value;
	}
	//��ʮ���������ַ���s�����������󡣹��췽��ֻ֧��ʮ���ƣ�s���������š�
	public MyInteger(String s) throws NumberFormatException
	{
		this.value = MyInteger.parseInt(s,10);
	}
	
	public int intValue()                                            //��������ֵ
	{
		return this.value;
	}
	
	public String toString()                                          //���ص�ǰ������ʮ�����ַ���������
	{                             
		return this.value+"";                                         //��+���Զ�������ת��Ϊʮ���������ַ���
	}
	
	public boolean equals(Object obj)                                 //�Ƚ϶����Ƿ���ȡ�����
	{
		return obj instanceof Integer && this.value == ((Integer)obj).intValue();
	}
	
	public int compareTo(MyInteger iobj)                               //�Ƚ϶���ֵ��С������-1��0��1
	{
		return this.value<iobj.value?-1:(this.value == iobj.value?0:1);
	}
	
	public static int pareInt(String s) throws NumberFormatException            //����s��ʮ����ת��Ϊ����
	{
		return MyInteger.parseInt(s,10);
	}
	//����s��radix����ת��Ϊ������sָ��������radix����ԭ���ַ���������������
	//2<=radix<=16��Ĭ��ʮ���ơ������ܽ�sת�������������׳���ֵ��ʽ�쳣
	public static int parseInt(String s,int radix) throws NumberFormatException
	{
		if(s == null)
			throw new NumberFormatException("null");
		if(radix<2||radix>16)
			throw new NumberFormatException("radix+"+radix+", ���Ƴ���2~16��Χ��");     
		int value = 0,i = 0;
		int sign = s.charAt(0)=='-'?-1:1;                                 //����λ����ס���������
		if (s.charAt(0)=='+'||s.charAt(0)=='-')                            //��������λ
			if(s.length()==1)                                             //ֻ��"+"��"��"
				throw new NumberFormatException("\""+s+"\"");
			else
				i++;                                                       //i��ס��ǰ�ַ����
		while(i<s.length())                                                //����޷��ŵ���������ֵ
		{
			char ch = s.charAt(i++);
			if(ch>='0'&&ch-'0'<radix)                                       //��2<=radix<=10ʱ��radix����Ҫʶ��0~radix-1����
				value = value*radix+ch-'0';                                  //value��ס��ǰ��õ�����ֵ
			else                          //��11<=radix<=16ʱ��radix���ƻ�Ҫʶ��ӡ�a��/��A����ʼ��radix-10����ĸ��ʾ������
				if(radix>10&&radix<=16&&ch>='a'&&ch-'a'<radix-10)
					value = value*radix+ch-'A'+10;
				else 
					throw new NumberFormatException(radix+"������������ʶ��"+ch);
		}
		return value*sign;                                                      //�����з��ŵ�����ֵ
	}
	
	public static String toHexString(int value)               //��������value��ʮ�����Ʋ����ַ�����������λ��0
	{
		char[] buffer = new char[8];                          //һ��int��8��ʮ������λ
		for(int i = buffer.length-1;i>=0;i--)                 //ѭ��ִ��8�Σ���λ��0
		{
			int bit = value&15;                               //���ʮ�����Ƶĸ�λ
			buffer[i] = (char)(bit<=9?bit+'0':bit+'a'-10);         //0~9��10~15ת��Ϊ'0'~'9'��'a'~'f'
			value>>>=4;                                        //����4λ����λ���0����value����16
		}
		return new String(buffer);                               //�������ַ����ֹ�����ַ���
	}
	
	public static void main(String args[])
	{
		String[] str16 = {"-18","-1","+7f","3e8"};               //����ʮ������ԭ��
		for(int i = 0;i<str16.length;i++)
		{
			int value = MyInteger.parseInt(str16[i], 16);
			System.out.println(value+",0x"+MyInteger.toHexString(value));
		}
	}
}

