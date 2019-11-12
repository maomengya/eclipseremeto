
public class GenList_String
{
	private static int i = 0;
	public static GenList<String> create(String gliststr)                       //������gliststr�ַ��������Ĺ����
	{
		i = 0;
		return createsub(gliststr);
	}
	//���ش�gliststr[i]��ʼ���Ӵ��������ӹ�������ַ�����ʾԭ�ӣ��ݹ��㷨
	private static GenList<String> createsub(String gliststr)
	{
		i++;                       //����������
		GenList<String>glist = new GenList<String>();                       //����չ����ֻ��ͷ���
		GenNode<String> p = glist.head;                       //ָ��ͷ���
		while(i<gliststr.length())
		{
			char ch = gliststr.charAt(i);
			switch(ch)
			{
			case',':i++;break;
			case'(':
			{
				p.next = new GenNode<String>();                       //�����ӱ���
				p = p.next;
				p.child = createsub(gliststr);                       //�����ӱ��ݹ����
				break;
			}
			case')':i++;return glist;                       //���ַ�����ʾԭ��
			default:
			{
				int j = i+1;
				ch = gliststr.charAt(j);                       //����Ӵ�
				while(ch!='('&&ch!=','&&ch!=')')
					ch = gliststr.charAt(++j);
				p.next = new GenNode<String>(gliststr.substring(i, j));                       //�������
				p = p.next;
				i=j;
			}
			}
		}
		return null;
	}
	
	public static void main(String args[])
	{
		GenList<String>glist = create("(d,(a,b),(c,(a,b)))");                       //��������G
		System.out.println("G="+glist.toString()+",size="+glist.size()+",depth="+glist.depth());
	}

}
