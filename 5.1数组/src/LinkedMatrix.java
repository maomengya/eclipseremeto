import �ڶ���2.Node;
import �ڶ���2.SortedSinglyList;
import �ڶ���_˳���.SeqList;

public class LinkedMatrix                //�еĵ�����洢�ľ�����
{
	private int rows,columns;               //��������������
	SeqList<PolySinglyList<Triple>> rowlist;               //��ָ��˳���Ԫ���Ƕ���ʽ��������
	public LinkedMatrix(int m,int n)    //����mxn�����������������<=0���׳���Ч�����쳣
	{
		if(m>0&&n>0)
		{
			this.rows = m;
			this.columns = n;
			this.rowlist = new SeqList<PolySinglyList<Triple>>();     //����˳�����ֵΪnull
			for(int i=0;i<m;i++)               //˳�������m���յ�����
				this.rowlist.insert(new PolySinglyList<Triple>());//˳���β���룬�Զ�����
		}
		else
			throw new IllegalArgumentException("��������������<=0,m="+m+",n="+n);
	}
	
	public LinkedMatrix(int m)               //����mxn�����
	{
		this(m,m);
	}
	
	public LinkedMatrix(int m,int n,Triple[] tris)//����mxn��������Ԫ������tris�ṩ����ֵ
	{
		this(m,n);
		for(int i = 0;i<tris.length;i++)
			this.set(tris[i]);               //�����������һ��Ԫ�ص���Ԫ��
	}

	public int getRows()                                   //���ؾ���������������ʡ��
    {
        return this.rows;        
    }
    public int getColumns()                                //���ؾ���������������ʡ��
    {
        return this.columns;        
    }
    
    public void set(int i,int j,int x)
    {
    	this.set(new Triple(i,j,x));
    }
    
    public void set(Triple tri)
    {
    	int i = tri.row,j = tri.column;
    	if(i>=0&&i<this.rows&&j>=0&&j<this.columns)
    	{
    		SortedSinglyList<Triple> link = this.rowlist.get(i);
    		if(tri.value == 0)
    			link.remove(tri);
    		else
    		{
    			Node<Triple> find = link.search(tri);
    			if(find!=null)
    				find.data.value = tri.value;
    			else
    				link.insert(tri);
    		}
    	}
    	else
    		throw new IndexOutOfBoundsException("i="+i+",j"+j);
    }
       
    //��2�� ���ؾ���Ԫ��
    //���ؾ����i�е�j��Ԫ�ء���i��jԽ�磬���׳����Խ���쳣�������㷨�Ƚ���Ԫ���С
    public int get(int i, int j)
    {
        if (i>=0 && i<this.rows && j>=0 && j<this.columns) 
        {
            //�ڵ�i������������˳�������Ԫ��(i,j,0)���Ƚ���Ԫ���С
            Node<Triple> find=this.rowlist.get(i).search(new Triple(i,j,0));
            return (find!=null) ? find.data.value : 0;     //�����ҳɹ�������Ԫ��ֵ�����򷵻�0
        }
        throw new IndexOutOfBoundsException("i="+i+"��j="+j);
    }
    
    //��3�� ���þ���Ԫ��
    //���þ����i�е�j��Ԫ��Ϊx����i��jԽ�磬���׳����Խ���쳣��
    //���ҡ����롢ɾ���㷨���Ƚ���Ԫ���С
//    public void set(int i, int j, int x)
//    {
//        if (i>=0 && i<this.rows && j>=0 && j<this.columns) 
//        {
//            SortedSinglyList<Triple> link = this.rowlist.get(i);//��õ�i����������
//            if (x==0) 
//                link.remove(new Triple(i,j,0));  //�����ҳɹ���ɾ��(i,j,?)���
//            else
//            {   //���²����ٲ�����滻Ԫ�ز���������link�����������
//                Triple tri = new Triple(i,j,x);
//                Node<Triple> find=link.search(tri); //˳�����tri������Ԫ��>tri������Ҳ��ɹ�
//                if (find!=null)
//                    find.data.value = x;                   //���ҳɹ����޸��޸�find���ö���ĳ�Ա����ֵ
//                else link.insert(tri);                     //���Ҳ��ɹ�����������link��(i,j)���������Ԫ��tri
//
///*                //Ҳ�ɣ�һ�α���
//                Node<Triple> find = link.insertDifferent(new Triple(i,j,x));//���벻�ظ�Ԫ�أ����ز��ҵ�������� 
//                if (find.data.value!=x)          //���������ز��ҵ���㣬�޸ĸý��Ԫ��
//                    find.data.value = x;
//*/
//            }
//        }
//        else throw new IndexOutOfBoundsException("i="+i+"��j="+j);//�׳����Խ���쳣
//    }
//    
//    public void set(Triple tri)                  //����Ԫ��tri���þ���Ԫ��
//    {
//        this.set(tri.row, tri.column, tri.value);
//    }

    //��4�� �������
    public String toString()                               //����ϡ�������Ԫ���еĵ����������ַ���
    {
        String str="";
        for (int i=0; i<this.rowlist.size(); i++)         //ѭ������Ϊ��ָ��˳�����
            str += i+" -> "+this.rowlist.get(i).toString()+"\n";//��õ�i����������������ַ���
        return str;
    }
        
    public void printMatrix()                               //�������
    {
        System.out.println("����"+this.getClass().getName()+"��"+rows+"��"+columns+"����");
        for (int i=0; i<this.rowlist.size(); i++)
//        for (int i=0; i<this.rows; i++)
        {
            Node<Triple> p = this.rowlist.get(i).head.next;//������i����������
            for (int j=0; j<this.columns; j++)
               if (p!=null && j==p.data.column)            //��i==p.data.row 
               {
                   System.out.print(String.format("%4d", p.data.value));
                   p = p.next;
               }               
               else 
            	   System.out.print(String.format("%4d", 0));
            System.out.println();
        }   
    }
    
    //���У�Ч�ʵͣ����ڲ���get(i,j)�����Ƿ���ȷ
//    public void printMatrix2()                             //�������
//    {
//        System.out.println("����"+this.getClass().getName()+"��"+rows+"��"+columns+"����");
//        for (int i=0; i<this.rows; i++)
//        {
//            for (int j=0; j<this.columns; j++)
//                System.out.print(String.format("%4d", this.get(i,j)));
//            System.out.println();
//        }
//    } 

    //��5�� �ȽϾ����Ƿ����
    public boolean equals(Object obj)                      //�Ƚ����������Ƿ���ȣ��㷨ͬSeqSparseMatrix��
    {
        if (this==obj)
            return true;
        if (!(obj instanceof LinkedMatrix))
            return false;
        LinkedMatrix mat=(LinkedMatrix)obj;        
        return this.rows==mat.rows && this.columns==mat.columns && this.rowlist.equals(mat.rowlist);
                                                           //�Ƚ�������Ԫ��˳����Ƿ����
    }
    
    //��6�� ���þ���������
    //���þ���Ϊm��n�С���mָ�������ϴ�����ָ��˳������ݣ�ʹ��ԭ���е�����
    //����7.2.2��ͼ���ڽӱ�洢�ṹ��
    public void setRowsColumns(int m, int n)
    {
        if (m>0 && n>0)
        {
            if (m > this.rows)                             //��m����ָ�������ϴ�
                for (int i=this.rows; i<m; i++)
//                    this.rowlist.insert(new SortedSinglyList<Triple>());//˳���β���룬�Զ�����
                  this.rowlist.insert(new PolySinglyList<Triple>());//˳���β���룬�Զ�����
            this.rows = m;
            this.columns = n;
        }
        else throw new IllegalArgumentException("�������������ܡ�0��m="+m+"��n="+n);
    }

    //��7�� �������
    public void addAll(LinkedMatrix mat)
    {
        if (this.rows==mat.rows && this.columns==mat.columns)
            for (int i=0; i<this.rows; i++)
                this.rowlist.get(i).addAll(mat.rowlist.get(i));        //���ö���ʽ����������㷨
        else throw new IllegalArgumentException("�������������ͬ���������");
    }
    
    public static void main(String args[])
    {
    	Triple[] elemsa = {new Triple(0,2,11),new Triple(0,4,17),new Triple(1,1,20),new Triple(3,0,19),new Triple(3,2,36),new Triple(4,2,50)};
    	LinkedMatrix mata = new LinkedMatrix(5,6,elemsa);
    	mata.printMatrix();
    	Triple[] elemsb = {new Triple(0,2,-11),new Triple(0,4,-17),new Triple(2,3,51),new Triple(3,0,10),new Triple(4,1,99)};
    	LinkedMatrix matb = new LinkedMatrix(5,6,elemsb);
    	System.out.print("\nB ������Ԫ���еĵ�����:\n"+matb.toString());
    	matb.printMatrix();
    	mata.addAll(matb);
    	System.out.print("\nA+=B ������Ԫ���еĵ�����:\n"+mata.toString());
    	mata.printMatrix();
    }
    
    
    //����5.2.2��
    
    //���¡�ʵ����5-3��
    //�������
    //�������췽������ȿ���������˳�������˳��������е������������н���Ԫ�ض���
//    public LinkedMatrix(LinkedMatrix mat)
//    {
//        this(mat.rows, mat.columns);             //����rows��columns���������ָ��˳�����rows���յ�����
//        for (int i=0; i<this.rows; i++)   
//        {
//            SortedSinglyList<Triple> link=new SortedSinglyList<Triple>(mat.rowlist.get(i));
////            PolySinglyList<Triple> link = new PolySinglyList<Triple>(mat.rowlist.get(i));
//                                       //����SortedSinglyList(SortedSinglyList<T> list)��O(n) 
//                                       //��������������������н�㣬û�и���Ԫ�ض���                
//            for (Node<Triple> p=link.head.next;  p!=null;  p=p.next) 
//                p.data = new Triple(p.data);     //����һ���������и�������õ�Ԫ�ض���
//            this.rowlist.set(i, (PolySinglyList<Triple>) link);           //�����ƺ�ĵ���������Ϊ˳����i��Ԫ��
//        }
//    }
//    
//    public LinkedMatrix union(LinkedMatrix mat)  //����this+mat��ӵľ��󣬲��ı�this��mat
//    {
//        LinkedMatrix matc=new LinkedMatrix(this);//��ȿ���
//        matc.addAll(mat);
//        return matc;                             //���ض�������
//    }
//    
//    public LinkedMatrix transpose()                        //����this��ת�þ���
//    {
//        LinkedMatrix trans=new LinkedMatrix(columns, rows);//����columns��rows�������
//        //����ÿ����������������Ԫ��ĶԳ�Ԫ�أ����뵽trans����
//        for (int i=0; i<this.rows; i++)
//            for (Node<Triple> p=this.rowlist.get(i).head.next; p!=null; p=p.next)
//                trans.set(p.data.toSymmetry());
//        return trans;
//    } 
//    
//    
//    public class CrossNode                                   //����ʮ����������
//    {
//    	Triple data;                                          //�������ʾ��Ԫ��
//    	CrossNode down,next;                                  //down��next�ֱ�ָ���к�̺��к�̽��
//    }
//    
//    public class CrossLinkedMatrix                             //ʮ�������ʾ�ľ�����
//    {
//    	private int rows,column;                               //��������������
//    	private CrossNode rowheads[],columnheads[];            //��ָ���������ָ������
//    }
//    
//    
//    public class GenNode<T>
//    {
//    	public T data;
//    	public GenList<T> child;
//    	public GenNode<T> next;
//    	public GenNode
//    }
}
