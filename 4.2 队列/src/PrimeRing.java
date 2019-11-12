import 第二章_顺序表.SeqList;
import 第二章_顺序表.SortedSeqList;

public class PrimeRing
{
	public PrimeRing(int max)                                    //求1~max素数环
	{
		SortedSeqList<Integer> primeset = createPrime(max);          //排序顺序表存储素数集合
		System.out.println("素数集合："+primeset.toString());
		SeqList<Integer> ring = new SeqList<Integer>(max);          //顺序表，存储素数环
		ring.insert(1);                                             //素数环添加Integer(1)
		Queue<Integer> que = new SeqQueue<Integer>(max);            //创建空队列，链式队列也可
		for(int i = 2;i<=max;i++)                                   //2~max全部入队
			que.add(i);
		System.out.println("队列："+que.toString());
		int i = 0;
		while (!que.isEmpty())
		{
			int key = que.poll();                                //出队
			if(primeset.contains(ring.get(i)+key))                   //判断素数，排序顺序表包含（查找）
			{
				i++;
				ring.insert(key);                                //素数环添加Integer(key)
			}
			else
				que.add(key);                                    //key再次入队
		}
		System.out.println("1~"+max+"素数环："+ring.toString());
	}
    
	//返回包含2~max中所有素数的排序顺序表，也可返回循环双链表
	public SortedSeqList<Integer> createPrime(int max)
	{
		if(max<=0)
			return null;
		SortedSeqList<Integer> primeset = new SortedSeqList<Integer>(max*2);          //排序顺序表
		primeset.insert(2);                                //添加已知的最小素数
		for(int key = 3;key<max*2;key+=2)                                //测试奇数，其他偶数不需要测试
		{
			int i = 0;
			while(i<primeset.size()&&key%primeset.get(i)!=0)             //用primeset中的素数测试key
				i++;
			if(i == primeset.size())                                 //key是素数
				primeset.insert(key);                                //排序顺序表尾插入最大值
		}
		return primeset;
	}
	
	public static void main(String args[])
	{
		new PrimeRing(10);
	}
}
