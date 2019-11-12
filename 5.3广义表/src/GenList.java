
public class GenList<T>                                           //˫����ʾ�Ĺ�����࣬û�б���
{
	public GenNode<T> head;                                        //ͷָ�룬ָ�����ã�ͷ���
	public GenList()                                        //����չ����
	{
		this.head = new GenNode<T>();                                        //����ͷ��㣬3����ֵ��Ϊnull
	}
	
	public String toString()                                        //���ع��������Ԫ�ص������ַ���
	{
		return this.toString("");
	}
	
	public String toString(String str)                                        //���ع��������Ԫ��ֵ��Ӧ���ַ�������ʽΪ���������������������㷨���ݹ鷽��
	{
		str+="(";
		for(GenNode<T> p = this.head.next;p!=null;p = p.next)
		{
			if(p.child == null)
				str += p.data.toString();
			else
				str += p.child.toString();                                        //�ݹ���ã������ӱ�����ӱ�����
			if(p.next!=null)
				str += ",";
		}
		return str += ")";                                        //�ձ��أ���
	}
	
	public int depth()                                        //���ع������ȣ��ݹ��㷨
	{
		int max=1;
        for (GenNode<T> p=this.head.next;  p!=null;  p=p.next)
            if (p.child!=null)
            {
                int d=p.child.depth();                     //�ݹ���ã������ӱ����
                if (max<=d)                                //��ס����ӱ����
                    max=d+1;                               //��ǰ��������Ϊ�ӱ���ȼ�1
            }
        return max;
	}
	
	public GenList(T[] atoms)                                        //����������atoms�����ṩԭ�ӳ�ֵ
	{
		this();                                            //�����չ����ֻ��ͷ���
        GenNode<T> rear=this.head;
        for (int i=0; i<atoms.length; i++)
        {
            rear.next=new GenNode<T>(atoms[i]);            //β����
            rear = rear.next; 
        }
	}
	
	public boolean isEmpty()                                        //�жϹ�����Ƿ��
	{
		return head.next==null;
	}
	
	public int size()                                        //���ع������
	{
		int i=0; 
        for (GenNode<T> p=this.head.next;  p!=null;  p=p.next)
            i++;
        return i;
	}
	
	public GenNode<T> insert(int i,T x)                                        //����ԭ��x��Ϊ��i��Ԫ�أ���i�ݴ�
	{
		if (x==null)
            throw new NullPointerException("x==null");     //���ܲ���ն����׳��ն����쳣
        GenNode<T> front=this.head;                        //frontָ��ͷ���
        for (int j=0;  front.next!=null && j<i;  j++)      //Ѱ�ҵ�i-1�������һ����㣨frontָ��
            front = front.next; 
        front.next=new GenNode<T>(x, null, front.next);    //��front֮�����ֵΪx���
        return front.next;  
	}
	
	public GenNode<T> insert(T x)                                        //�ڹ����������ԭ��x���
	{
		return insert(Integer.MAX_VALUE, x);
	}
	
	public GenNode<T> insert(int i,GenList<T> glist)                                        //�����ӱ�glist��Ϊ��i��Ԫ�أ���i�ݴ�
	{
		if (glist==null)
            throw new NullPointerException("x==null");     //���ܲ���ն����׳��ն����쳣
        GenNode<T> front=this.head;                        //frontָ��ͷ���
        for (int j=0;  front.next!=null && j<i;  j++)      //Ѱ�ҵ�i-1�������һ����㣨frontָ��
            front = front.next; 
        front.next=new GenNode<T>(null, glist, front.next);//��front֮������㣬��ֵ��childָ��glist
        return front.next;  
	}
	
	public GenNode<T> insert(GenList<T> glist)                                        //�ڹ�����������ӱ�glist
	{
		 return insert(Integer.MAX_VALUE, glist);
	}
	
	public void remove(int i)                                        //ɾ����i��Ԫ��
	{
		
	}
	
	
	public static void main(String args[])
	{
		String[] atoms = {"a","b"};
		GenList<String> glist_L = new GenList<String>(atoms);                                           //��ԭ�����鹹������L
		System.out.println("L="+glist_L.toString()+",size="+glist_L.size()+",depth="+glist_L.depth());  
		GenList<String>glist_T = new GenList<String>();                                          //����չ����G
		glist_T.insert("c");                                          //����ԭ��c
		glist_T.insert(glist_L);                                          //β�����ӱ�L
		System.out.println("T="+glist_T.toString()+",size="+glist_T.size()+",depth="+glist_T.depth());
		GenList<String>glist_G = new GenList<String>();                                          //����չ����G
		glist_G.insert("d");                                          //����ԭ��d
		glist_G.insert(glist_L);                                          //β�����ӱ�L
		glist_G.insert(glist_T);                                          //β�����ӱ�T��glist_L��Ϊ�����ӱ�
		System.out.println("G="+glist_G.toString()+",size="+glist_G.size()+",depth="+glist_G.depth());
	}
}
