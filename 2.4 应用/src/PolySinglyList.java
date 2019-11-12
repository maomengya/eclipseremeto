import �ڶ���2.Node;
import �ڶ���2.SortedSinglyList;

//����ʽ���������࣬�̳����������࣬�ṩ��������Ķ���ʽ������㣻
//T��T��ĳ�����������ʵ��Comparable<T>�ӿڣ�Լ������Ƚϴ�С�Ĺ���Tʵ��Addible<T>����ӽӿ�
public class PolySinglyList<T extends Comparable<? super T> & Addible<T>>
        extends SortedSinglyList<T>
{
    public PolySinglyList()                                //���췽��
    {
        super();                                           //�����յ�����
    }
    public PolySinglyList(T terms[])                       //���췽������������ָ������ʽ����ֵ
    {
        super(terms);
    }
    public PolySinglyList(PolySinglyList<T> list)          //�������췽��
    {
        super(list);                                       //������������������н�㣬û�и��ƶ���
    }
       
    public void addAll(PolySinglyList<T> list)             //����ʽ��ӣ�this��=list���ܣ����ı�list
    {
        Node<T> front=this.head, p=front.next;
        Node<T> q=list.head.next;
        while (p!=null && q!=null)
            if (p.data.compareTo(q.data)==0)               //�����С��ͬ
            {
                p.data.add(q.data);                        //������ӣ�add()������Addible�ӿ�Լ��
                if (p.data.removable())                    //��Ӻ�Ԫ������ɾ������
                {                                          //removable()������Addible�ӿ�Լ��
                    front.next=p.next;                     //��Ӻ�Ԫ�ز���Ҫ�洢��ɾ��p���
                    p=front.next;
                }
                else 
                {
                    front = p;                             //front��p��ǰ�����
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
                     front.next = new Node<T>(q.data, p);  //����q��㲢���뵽front���֮��
                     q = q.next;
                 }
        while (q!=null)                                    //��list��������ʣ���㸴�Ʋ����뵽��ǰ����β
        {
            front.next = new Node<T>(q.data, null);
            front = front.next;
            q = q.next;
        }
    }
}