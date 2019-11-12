package ������;

public class Bracket 
{
	//���infix���ʽ�е�Բ�����Ƿ�ƥ�䣬��ƥ�䣬���ؿմ������򷵻ش�����Ϣ
	public static String isMatched(String infix)
	{
		Stack<String>stack = new SeqStack<String>(infix.length());
		           //�����ӿڶ���stack������ʵ��Stack<T>�ӿڵ�˳��ջ���ʵ����������ջ
		for(int i = 0;i<infix.length();i++)
		{
			char ch = infix.charAt(i);
			switch(ch) 
			{
				case '(':stack.push(ch+"");                                //��������ջ
						break;
				case ')':if(stack.isEmpty()||!stack.pop().equals("("))        //����������ʱ����ջ
					return "����(";                                           //����ջ�ַ��Ƿ�Ϊ������
			
			}
		}
		return (stack.isEmpty())?"":"����)";                                  //���ؿմ���ʾû�д���
	}
	public static void main(String args[])
	{
		String infix = "((1+2)*3+4))(";
		System.out.println(infix+",�������"+Bracket.isMatched(infix));
	}
}
