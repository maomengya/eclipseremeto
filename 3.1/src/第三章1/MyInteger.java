package 第三章1;

public final class MyInteger implements Comparable<MyInteger>      //整数类，最终类
{
	public static final int MIN_VALUE = 0x80000000;                 //最小值常量，-2^31 = -2147483647
	public static final int MAX_VALUE = 0x7fffffff;                 //最大值常量，2^31 = 2147483647
	private final int value;                                        //私有最终变量，存储整理，赋值一次
	public MyInteger(int value)                                     //由int整数value构造整数对象
	{
		this.value = value;
	}
	//由十进制整数字符串s构造整数对象。构造方法只支持十进制，s包含正负号。
	public MyInteger(String s) throws NumberFormatException
	{
		this.value = MyInteger.parseInt(s,10);
	}
	
	public int intValue()                                            //返回整数值
	{
		return this.value;
	}
	
	public String toString()                                          //返回当前整数的十进制字符串。覆盖
	{                             
		return this.value+"";                                         //“+”自动将整数转化为十进制整数字符串
	}
	
	public boolean equals(Object obj)                                 //比较对象是否相等。覆盖
	{
		return obj instanceof Integer && this.value == ((Integer)obj).intValue();
	}
	
	public int compareTo(MyInteger iobj)                               //比较对象值大小，返回-1、0或1
	{
		return this.value<iobj.value?-1:(this.value == iobj.value?0:1);
	}
	
	public static int pareInt(String s) throws NumberFormatException            //将串s按十进制转化为整数
	{
		return MyInteger.parseInt(s,10);
	}
	//将串s按radix进制转化为整数，s指定整数的radix进制原码字符串，包含正负号
	//2<=radix<=16，默认十进制。若不能将s转换成整数，则抛出数值格式异常
	public static int parseInt(String s,int radix) throws NumberFormatException
	{
		if(s == null)
			throw new NumberFormatException("null");
		if(radix<2||radix>16)
			throw new NumberFormatException("radix+"+radix+", 进制超过2~16范围。");     
		int value = 0,i = 0;
		int sign = s.charAt(0)=='-'?-1:1;                                 //符号位，记住正负数标记
		if (s.charAt(0)=='+'||s.charAt(0)=='-')                            //跳过符号位
			if(s.length()==1)                                             //只有"+"、"—"
				throw new NumberFormatException("\""+s+"\"");
			else
				i++;                                                       //i记住当前字符序号
		while(i<s.length())                                                //获得无符号的整数绝对值
		{
			char ch = s.charAt(i++);
			if(ch>='0'&&ch-'0'<radix)                                       //当2<=radix<=10时，radix进制要识别0~radix-1数字
				value = value*radix+ch-'0';                                  //value记住当前获得的整数值
			else                          //当11<=radix<=16时，radix进制还要识别从‘a’/‘A’开始的radix-10个字母表示的整数
				if(radix>10&&radix<=16&&ch>='a'&&ch-'a'<radix-10)
					value = value*radix+ch-'A'+10;
				else 
					throw new NumberFormatException(radix+"进制整数不能识别"+ch);
		}
		return value*sign;                                                      //返回有符号的整数值
	}
	
	public static String toHexString(int value)               //返回整数value的十六进制补码字符串，正数高位补0
	{
		char[] buffer = new char[8];                          //一个int有8个十六进制位
		for(int i = buffer.length-1;i>=0;i--)                 //循环执行8次，高位补0
		{
			int bit = value&15;                               //获得十六进制的个位
			buffer[i] = (char)(bit<=9?bit+'0':bit+'a'-10);         //0~9、10~15转换为'0'~'9'、'a'~'f'
			value>>>=4;                                        //右移4位，高位填充0，即value除以16
		}
		return new String(buffer);                               //返回由字符数字构造的字符串
	}
	
	public static void main(String args[])
	{
		String[] str16 = {"-18","-1","+7f","3e8"};               //整数十六进制原码
		for(int i = 0;i<str16.length;i++)
		{
			int value = MyInteger.parseInt(str16[i], 16);
			System.out.println(value+",0x"+MyInteger.toHexString(value));
		}
	}
}

