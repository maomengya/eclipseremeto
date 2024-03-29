import 第二章2.Node;

//多项式类，使用多项式排序单链表类作为成员变量，提供多项式相加运算
//如果多项式排序单链表PolySinglyList的泛型参数是TermX，则Polynomial表示一元多项式
//如果多项式排序单链表PolySinglyList的泛型参数是TermXY，则Polynomial表示二元多项式
public class Polynomial
{
    private PolySinglyList<TermX> list;                    //多项式排序单链表，TermX表示一元多项式的一项

    public Polynomial()                                    //构造方法
    {
        this.list = new PolySinglyList<TermX>();           //创建空单链表，执行排序单链表默认构造方法
    }
    public Polynomial(TermX terms[])                       //构造方法，由项数组指定多项式各项值
    {
        this.list = new PolySinglyList<TermX>(terms);
    }
    public Polynomial(String polystr)                      //构造方法，参数指定多项式表达式字符串  
    {
        this();
        if (polystr==null || polystr.length()==0)
            return;
        Node<TermX> rear = this.list.head;
        int start=0, end=0;                                //序号start～end的子串为一项
        while (start<polystr.length() && end<polystr.length())
        {
            int i=polystr.indexOf('+',end+1);              //返回字符+在字符串中从end+1开始的序号
            if (i==-1)                                     //未找到指定字符
                i=polystr.length();
            int j=polystr.indexOf('-',end+1);
            if (j==-1)
                j=polystr.length();
            end=i<j? i: j;                                 //end为下一个+或-号的序号
 //           System.out.println("start="+start+", end="+end+", "+polystr.substring(start,end));
            rear.next = new Node<TermX>(new TermX(polystr.substring(start,end)), null);
                       //尾插入，以序号start～end的子串作为一项，创建结点，创建元素对象
            rear = rear.next; 
            start=end;
        }
    }
    
    public Polynomial(Polynomial poly)                     //深度拷贝构造方法，复制所有结点和对象
    {
        this();                                            //创建空单链表，只有头结点         
        Node<TermX> rear = this.list.head;
        for (Node<TermX> p=poly.list.head.next;  p!=null;  p=p.next) //p遍历poly单链表
        {
            rear.next = new Node<TermX>(new TermX(p.data), null);    //复制结点，复制对象
            rear = rear.next; 
        }
    }
    
    public String toString()                               //返回多项式的描述字符串
    {
        String str="";
        for (Node<TermX> p=this.list.head.next;  p!=null;  p=p.next)
            str+=p.data.toString();
        return str;
    }

    public void addAll(Polynomial poly)                    //多项式相加，this＋=poly
    {
        this.list.addAll(poly.list);
    }
    public Polynomial union(Polynomial poly)               //加法＋，C=this＋poly
    {
    	Polynomial polyc=new Polynomial(this);             //深度拷贝，复制所有结点和对象
        polyc.addAll(poly);                                //cpoly＋=poly
        return polyc;                                      //返回对象引用
    }
    
    public boolean equals(Object obj)                      //比较两个多项式是否相等
    {
        return this==obj || obj instanceof Polynomial && this.list.equals(((Polynomial)obj).list);  
                                                           //比较两条单链表是否相等
    }
}