package �ڶ���_˳���;

public class Josephus 
{
	public Josephus(int number,int start,int distance)
	{
		System.out.print("Josephus("+number+","+start+","+distance+"),");
		SeqList<String>list = new SeqList<String>(number);
		for(int i = 0;i<number;i++)
			list.insert((char)('A'+i)+"");
		System.out.print(list.toString());
		int i = start;
		while(list.size()>1)
		{
			i = (i+distance-1)%list.size();
			System.out.print("ɾ��"+list.remove(i).toString()+",");
			System.out.println(list.toString());
		}
		System.out.println("���������"+list.get(0).toString());
	}
public static void main(String args[])
{
	new Josephus(5,0,2);
	//1213ceshi
}
}
