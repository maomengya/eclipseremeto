package ╣зкдуб;

public class Prefix
{
	public static StringBuffer toPrefix(String infix)
	{
		Stack<String>stack = new SeqStack<String>(infix.length());
		StringBuffer prefix = new StringBuffer(infix.length()*2);
		int i = infix.length()-1;
		while(i>=0)
		{
			char ch = infix.charAt(i);
			switch(ch)
			{
			case'+':case'-':
				while(!stack.isEmpty()&&!stack.pop().equals("("))
					prefix.append(stack.peek());
				stack.push(ch+"");
				i--;
				break;
			case'*':case'%':
				while(!stack.isEmpty()&&(stack.pop().equals("*"))||stack.pop().equals("/"))
					prefix.append(stack.peek());
				stack.push(ch+"");
				i--;
				break;
			case'(':
				stack.push(ch+"");
				i--;
				break;
			case')':
				String out = stack.peek();
				while(out!=null&&!out.equals("("))
				{
					prefix.append(out);
					out = stack.peek();
				}
				i--;
				break;
				default:
					while(i>=0&&ch>='0'&&ch<='9')
					{
						prefix.append(ch);
						i--;
						if(i<infix.length())
							ch = infix.charAt(i);
					}
					prefix.append(" ");
			}
		}
		while(!stack.isEmpty())
			prefix.append(stack.peek());
		return prefix;
	}
	
	public static int toValue(StringBuffer prefix)
	{
		Stack<Integer> stack = new LinkedStack<Integer>();
		int value = 0;
		for(int i = 0;i<prefix.length();i++)
		{
			char ch = prefix.charAt(i);
			if(ch>='0'&&ch<='9')
			{
				value = 0;
				while(ch!=' ')
				{
					value = value*10+ch-'0';
					ch = prefix.charAt(++i);
				}
				stack.push(value);
			}
			else
				if(ch!=' ')
				{
					int y = stack.peek(),x = stack.peek();
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
		return stack.peek();
	}
	
	public static void main(String args[])
	{
		String infix = "123+10*(45-50+20)/((35-25)*2+10)-11";
		StringBuffer prefix = toPrefix(infix);
		System.out.println("infix="+infix+"\nprefix="+prefix+"\nvalue="+toValue(prefix));
	}

}
