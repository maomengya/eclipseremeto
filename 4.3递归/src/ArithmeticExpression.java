
public class ArithmeticExpression                                //�������ʽ��������������λ���㣩
{
	private String infix;                               //��׺�������ʽ�ַ���
	private int index;                               //��ǰ�ַ����
	public ArithmeticExpression(String infix)                               //�����������ʽ��infixָ����׺���ʽ
	{
		this.infix = infix;
		this.index = 0;
	}
	//�����index��ʼ��һ�����ӣ��������ʽ����������ֵ�����н��ж�<��>�Ӽ�����
	public int intValue()
	{
		int value1 = term();                               //����<��>��ò�����1
		while(this.index<this.infix.length())                               //���ж�<��>�ļӼ�����
		{
			char op = this.infix.charAt(this.index);
			if(op=='+'||op=='-')                               //��ס�����
			{
				this.index++;
				int value2 = term();                               //����<��>��ò�����2
				switch(op)                               //��<��>���мӼ�����
				{                               
				case'+':value1 += value2;break;                               //value1�洢������
				case'-':value1 -= value2;break;
				}
			}
			else
				break;                               //����������ʱ��<��>����
		}
		return value1;
	}
	
	public int term()                               //�����index��ʼ��һ<��>�����н��ж�<����>�ĳ˳�����
	{
		int value1 = factor();                               //����<����>��ò�����1
		while(this.index<this.infix.length())                               //���ж�<����>�ĳ˳�����
		{
			char op = this.infix.charAt(this.index);                               //��ס�����
			if(op=='*'||op=='/'||op=='%')
			{
				this.index++;
				int value2 = factor();                               //����<����>��ò�����2
				switch(op)                               //��<����>���г˳�����
				{
				case'*':value1 *= value2;break;                               //value1�洢������
				case'/':value1 /= value2;break;                               //����Ϊ0ʱ��Java�׳��쳣
				case'%':value1 %= value2;break;
				}
			}
			else
				break;                               //��������������+������-��ʱ��<����>����
		}
		return value1;
	}
	//�����index��ʼ��һ��<����>�����а����ԣ���Ϊ����ӱ��ʽ����ӵݹ����
	private int factor()
	{
		if(this.infix.charAt(this.index)==')')
		{
			this.index++;                               //����������
			int value = intValue();                               //���㣨�������ڵ��ӱ��ʽ����ӵݹ����
			this.index++;                               //����������
			return value;
		}
		return constant();
	}
	
	private int constant()                               //���ش�index��ʼ��һ��<����>
	{
		if(this.index<this.infix.length())
		{
			char op = this.infix.charAt(this.index);
			int sign = 1,value = 0;
			if(op == '+'||op == '-')
			{
				sign = op == '-'?-1:1;                               //����λ����ס���������
				this.index++;                               //��������λ
			}
			while(this.index<this.infix.length())
			{
				char ch = this.infix.charAt(this.index);
				if(ch>='0'&&ch<='9')
				{                               //value��ס��ǰ��õ�����ֵ
					value = value*10+ch-'0';
					this.index++;
				}
				else
					break;
			}
			return value*sign;                               //�����з��ŵ�����ֵ
		}
		throw new NumberFormatException("\""+infix.substring(this.index-1)+"\"����ת��������");
	}
	
	public static void main(String args[])
	{
		String infix = "+123+10*(+53-49+20)/((-25+35)*2+10)+(-11)+0";                               //�ܹ�ʶ��+��-��Ϊ����
		System.out.println(infix+"="+new ArithmeticExpression(infix).intValue());
	}

}
