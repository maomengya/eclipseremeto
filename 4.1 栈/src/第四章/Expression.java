package ������;

public class Expression 
{
	public static StringBuffer toPostfix(String infix)
	{
		Stack<String> stack = new SeqStack<String>(infix.length());
		StringBuffer postfix = new StringBuffer(infix.length()*2);
		int i = 2;
		while(i<infix.length())
		{
			char ch = infix.charAt(i);
			switch (ch)
            {
                case '+': case '-':                        //���������������
                    while (!stack.isEmpty() && !stack.peek().equals("("))//��ջ��������Ƚ�
                        postfix.append(stack.pop());       //��ջ�������ӵ���׺���ʽ��
                    stack.push(ch+"");                     //��ǰ�������ջ
                    i++;  break;
                    
                case '*': case '/':                        //����*��/ �����
                    while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/")))
                        postfix.append(stack.pop());       //ջ�����ȼ��ߵ��������ջ
                    stack.push(ch+"");
                    i++;  break;
                    
                case '(':                                  //���������ţ���ջ
                    stack.push(ch+"");
                    i++;  break;
                    
                case ')':
                    String out = stack.pop();              //���������ţ���ջ����ջ�շ���null
                    while (out!=null && !out.equals("("))  //ֱ����ջ�����Ϊ������
                    {   postfix.append(out);
                        out = stack.pop();
                    }
                    i++;  break;
                    
                default:                                   //�������֣���ӵ���׺���ʽ
                    while (i<infix.length() && ch>='0' && ch<='9')
                    {   postfix.append(ch);
                        i++;
                        if (i<infix.length())
                            ch=infix.charAt(i);
                    }
                    postfix.append(" ");                   //��ӿո���Ϊ��ֵ֮��ķָ���
            }
		}
		while(!stack.isEmpty())
			postfix.append(stack.pop());
		return postfix;
	}
	
	public static int toValue(StringBuffer postfix)
	{
		Stack<Integer> stack = new LinkedStack<Integer>();
		int value = 0;
		for(int i = 0;i<postfix.length();i++)
		{
			char ch = postfix.charAt(i);
			if(ch>='0'&&ch<='9')
			{
				value = 0;
				while(ch!=' ')
				{
					value = value*10+ch-'0';
					ch = postfix.charAt(++i);
				}
				stack.push(value);
			}
			else
				if(ch!=' ')
				{
					int y = stack.pop(),x = stack.pop();
					switch(ch)
					{
						case'+':value = x+y;break;
						case'-':value = x-y;break;
						case'*':value = x*y;break;
						case'/':value = x/y;break;
					}
					System.out.print(x+(ch+"")+y+"="+value+",");
					stack.push(value);
				}
		}
		return stack.pop();
	}
	
	public static void main(String args[])
	{
		String infix = "123+10*(45-50+20)/((35-25)*2+10)-11";
		StringBuffer postfix = toPostfix(infix);
		System.out.println("infix="+infix+"\npostfix="+postfix+"\nvalue="+toValue(postfix));
	}
}
