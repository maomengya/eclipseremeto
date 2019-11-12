import �ڶ���_˳���.SeqList;
import �ڶ���_˳���.SortedSeqList;

public class PrimeRing
{
	public PrimeRing(int max)                                    //��1~max������
	{
		SortedSeqList<Integer> primeset = createPrime(max);          //����˳���洢��������
		System.out.println("�������ϣ�"+primeset.toString());
		SeqList<Integer> ring = new SeqList<Integer>(max);          //˳����洢������
		ring.insert(1);                                             //���������Integer(1)
		Queue<Integer> que = new SeqQueue<Integer>(max);            //�����ն��У���ʽ����Ҳ��
		for(int i = 2;i<=max;i++)                                   //2~maxȫ�����
			que.add(i);
		System.out.println("���У�"+que.toString());
		int i = 0;
		while (!que.isEmpty())
		{
			int key = que.poll();                                //����
			if(primeset.contains(ring.get(i)+key))                   //�ж�����������˳�����������ң�
			{
				i++;
				ring.insert(key);                                //���������Integer(key)
			}
			else
				que.add(key);                                    //key�ٴ����
		}
		System.out.println("1~"+max+"��������"+ring.toString());
	}
    
	//���ذ���2~max����������������˳���Ҳ�ɷ���ѭ��˫����
	public SortedSeqList<Integer> createPrime(int max)
	{
		if(max<=0)
			return null;
		SortedSeqList<Integer> primeset = new SortedSeqList<Integer>(max*2);          //����˳���
		primeset.insert(2);                                //�����֪����С����
		for(int key = 3;key<max*2;key+=2)                                //��������������ż������Ҫ����
		{
			int i = 0;
			while(i<primeset.size()&&key%primeset.get(i)!=0)             //��primeset�е���������key
				i++;
			if(i == primeset.size())                                 //key������
				primeset.insert(key);                                //����˳���β�������ֵ
		}
		return primeset;
	}
	
	public static void main(String args[])
	{
		new PrimeRing(10);
	}
}
