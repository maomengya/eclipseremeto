import 第二章2.Node;
import 第二章2.SortedSinglyList;

//多项式排序单链表类，继承排序单链表类，提供排序单链表的多项式相加运算；
//T或T的某个祖先类必须实现Comparable<T>接口，约定对象比较大小的规则；T实现Addible<T>可相加接口
public class PolySinglyList<T extends Comparable<? super T> & Addible<T>>
        extends SortedSinglyList<T>
{
    public PolySinglyList()                                //构造方法
    {
        super();                                           //创建空单链表
    }
    public PolySinglyList(T terms[])                       //构造方法，由项数组指定多项式各项值
    {
        super(terms);
    }
    public PolySinglyList(PolySinglyList<T> list)          //拷贝构造方法
    {
        super(list);                                       //单链表深拷贝，复制所有结点，没有复制对象
    }
       
    public void addAll(PolySinglyList<T> list)             //多项式相加，this＋=list功能，不改变list
    {
        Node<T> front=this.head, p=front.next;
        Node<T> q=list.head.next;
        while (p!=null && q!=null)
            if (p.data.compareTo(q.data)==0)               //两项大小相同
            {
                p.data.add(q.data);                        //两项相加，add()方法由Addible接口约定
                if (p.data.removable())                    //相加后元素满足删除条件
                {                                          //removable()方法由Addible接口约定
                    front.next=p.next;                     //相加后元素不需要存储，删除p结点
                    p=front.next;
                }
                else 
                {
                    front = p;                             //front是p的前驱结点
                    p = p.next;
                }
                q = q.next;
            }
            else if (p.data.compareTo(q.data)<0)
                 {
                     front = p;       
                     p = p.next;
                 }
                 else
                 {
                     front.next = new Node<T>(q.data, p);  //复制q结点并插入到front结点之后
                     q = q.next;
                 }
        while (q!=null)                                    //将list单链表中剩余结点复制并插入到当前链表尾
        {
            front.next = new Node<T>(q.data, null);
            front = front.next;
            q = q.next;
        }
    }
}