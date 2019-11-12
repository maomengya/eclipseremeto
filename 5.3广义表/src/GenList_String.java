
public class GenList_String
{
	private static int i = 0;
	public static GenList<String> create(String gliststr)                       //返回以gliststr字符串创建的广义表
	{
		i = 0;
		return createsub(gliststr);
	}
	//返回从gliststr[i]开始的子串创建的子广义表，用字符串表示原子，递归算法
	private static GenList<String> createsub(String gliststr)
	{
		i++;                       //跳过‘（’
		GenList<String>glist = new GenList<String>();                       //构造空广义表，只有头结点
		GenNode<String> p = glist.head;                       //指向头结点
		while(i<gliststr.length())
		{
			char ch = gliststr.charAt(i);
			switch(ch)
			{
			case',':i++;break;
			case'(':
			{
				p.next = new GenNode<String>();                       //创建子表结点
				p = p.next;
				p.child = createsub(gliststr);                       //创建子表，递归调用
				break;
			}
			case')':i++;return glist;                       //用字符串表示原子
			default:
			{
				int j = i+1;
				ch = gliststr.charAt(j);                       //获得子串
				while(ch!='('&&ch!=','&&ch!=')')
					ch = gliststr.charAt(++j);
				p.next = new GenNode<String>(gliststr.substring(i, j));                       //创建结点
				p = p.next;
				i=j;
			}
			}
		}
		return null;
	}
	
	public static void main(String args[])
	{
		GenList<String>glist = create("(d,(a,b),(c,(a,b)))");                       //构造广义表G
		System.out.println("G="+glist.toString()+",size="+glist.size()+",depth="+glist.depth());
	}

}
