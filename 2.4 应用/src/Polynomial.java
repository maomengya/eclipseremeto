import �ڶ���2.Node;

//����ʽ�࣬ʹ�ö���ʽ������������Ϊ��Ա�������ṩ����ʽ�������
//�������ʽ��������PolySinglyList�ķ��Ͳ�����TermX����Polynomial��ʾһԪ����ʽ
//�������ʽ��������PolySinglyList�ķ��Ͳ�����TermXY����Polynomial��ʾ��Ԫ����ʽ
public class Polynomial
{
    private PolySinglyList<TermX> list;                    //����ʽ��������TermX��ʾһԪ����ʽ��һ��

    public Polynomial()                                    //���췽��
    {
        this.list = new PolySinglyList<TermX>();           //�����յ�����ִ����������Ĭ�Ϲ��췽��
    }
    public Polynomial(TermX terms[])                       //���췽������������ָ������ʽ����ֵ
    {
        this.list = new PolySinglyList<TermX>(terms);
    }
    public Polynomial(String polystr)                      //���췽��������ָ������ʽ���ʽ�ַ���  
    {
        this();
        if (polystr==null || polystr.length()==0)
            return;
        Node<TermX> rear = this.list.head;
        int start=0, end=0;                                //���start��end���Ӵ�Ϊһ��
        while (start<polystr.length() && end<polystr.length())
        {
            int i=polystr.indexOf('+',end+1);              //�����ַ�+���ַ����д�end+1��ʼ�����
            if (i==-1)                                     //δ�ҵ�ָ���ַ�
                i=polystr.length();
            int j=polystr.indexOf('-',end+1);
            if (j==-1)
                j=polystr.length();
            end=i<j? i: j;                                 //endΪ��һ��+��-�ŵ����
 //           System.out.println("start="+start+", end="+end+", "+polystr.substring(start,end));
            rear.next = new Node<TermX>(new TermX(polystr.substring(start,end)), null);
                       //β���룬�����start��end���Ӵ���Ϊһ�������㣬����Ԫ�ض���
            rear = rear.next; 
            start=end;
        }
    }
    
    public Polynomial(Polynomial poly)                     //��ȿ������췽�����������н��Ͷ���
    {
        this();                                            //�����յ�����ֻ��ͷ���         
        Node<TermX> rear = this.list.head;
        for (Node<TermX> p=poly.list.head.next;  p!=null;  p=p.next) //p����poly������
        {
            rear.next = new Node<TermX>(new TermX(p.data), null);    //���ƽ�㣬���ƶ���
            rear = rear.next; 
        }
    }
    
    public String toString()                               //���ض���ʽ�������ַ���
    {
        String str="";
        for (Node<TermX> p=this.list.head.next;  p!=null;  p=p.next)
            str+=p.data.toString();
        return str;
    }

    public void addAll(Polynomial poly)                    //����ʽ��ӣ�this��=poly
    {
        this.list.addAll(poly.list);
    }
    public Polynomial union(Polynomial poly)               //�ӷ�����C=this��poly
    {
    	Polynomial polyc=new Polynomial(this);             //��ȿ������������н��Ͷ���
        polyc.addAll(poly);                                //cpoly��=poly
        return polyc;                                      //���ض�������
    }
    
    public boolean equals(Object obj)                      //�Ƚ���������ʽ�Ƿ����
    {
        return this==obj || obj instanceof Polynomial && this.list.equals(((Polynomial)obj).list);  
                                                           //�Ƚ������������Ƿ����
    }
}