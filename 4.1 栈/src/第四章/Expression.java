package 第四章;

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
                case '+': case '-':                        //遇到＋、－运算符
                    while (!stack.isEmpty() && !stack.peek().equals("("))//与栈顶运算符比较
                        postfix.append(stack.pop());       //出栈运算符添加到后缀表达式串
                    stack.push(ch+"");                     //当前运算符入栈
                    i++;  break;
                    
                case '*': case '/':                        //遇到*、/ 运算符
                    while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/")))
                        postfix.append(stack.pop());       //栈顶优先级高的运算符出栈
                    stack.push(ch+"");
                    i++;  break;
                    
                case '(':                                  //遇到左括号，入栈
                    stack.push(ch+"");
                    i++;  break;
                    
                case ')':
                    String out = stack.pop();              //遇到右括号，出栈，若栈空返回null
                    while (out!=null && !out.equals("("))  //直到出栈运算符为左括号
                    {   postfix.append(out);
                        out = stack.pop();
                    }
                    i++;  break;
                    
                default:                                   //遇到数字，添加到后缀表达式
                    while (i<infix.length() && ch>='0' && ch<='9')
                    {   postfix.append(ch);
                        i++;
                        if (i<infix.length())
                            ch=infix.charAt(i);
                    }
                    postfix.append(" ");                   //添加空格作为数值之间的分隔符
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
