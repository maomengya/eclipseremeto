package �ڶ���_˳���;

//˳���б�ʵ��ADTList<T>�����ķ�����T��ʾ����Ԫ�ص�����ģ��
public class SeqList<T> extends Object
{
	protected Object[] element;                    //��������洢˳��������Ԫ�أ�������Ա
	protected int n;                               //˳���Ԫ�صĸ��������ȣ�
	public SeqList(int length)                      //��������Ϊlength�Ŀձ�
	{
		this.element = new Object[length];               //��������Ĵ洢�ռ䣬Ԫ��Ϊnull
													//��length<0����Java�׳������鳤���쳣java.lang.NegativeArraySizeException
		this.n = 0;
	}
	public SeqList()                               //����Ĭ�������Ŀձ����췽������
	{                                      
		this(64);                               //���ñ�����������ָ�������б�Ĺ��췽��
	}
	public SeqList(T[] values)                          //����˳�����values.length�Ŀձ�
	{
		this(values.length);                        //��������Ϊvalues.length�Ŀձ�
											//��values == null,���ÿն�����÷�����Java�׳�NullPointerException�ն����쳣
		for(int i = 0;i<values.length;i++)          //��������Ԫ�أ�O(n)
			this.element[i] = values[i];            //�������ø�ֵ
		this.n = element.length;
	}
	
	public SeqList(SeqList<Student> lista) {
		super();
	}
	
	public boolean isEmpty()                       //�ж�˳����Ƿ�գ���Ϊ�շ���true
	{
		return this.n == 0;
	}
	public int size()                               //����˳���Ԫ�ظ���
	{
		return this.n;
	}
	public T get(int i)                                //���ص�i��Ԫ�أ�0<=i<n.��iԽ�磬�򷵻�null
	{
		if(i>=0 && i<this.n)
			return (T)this.element[i];                  //��������Ԫ�����õĶ��󣬴��ݶ�������
		return null;
	}
	//���õ�i��Ԫ��Ϊx��0<=i<n����iԽ�磬���׳����Խ���쳣����x == null�����׳��ն����쳣
	public void set(int i,T x)
	{
		if(x == null)
			throw new NullPointerException("x == null");                    //�׳��ն����쳣
		if(i>=0&&i<this.n)
			this.element[i] = x;
		else throw new java.lang.IndexOutOfBoundsException(i+"");          //�׳����Խ���쳣
	}
	//����˳�������Ԫ�ص������ַ�������ʽΪ��(,)��������Object���toString()����
	public String toString()
	{
		String str = this.getClass().getName() + "(";          //�����ļ���
		if(this.n>0)
			str+=this.element[0].toString();
		for(int i = 1;i<this.n;i++)
			str += ","+this.element[i].toString();                //ִ��T���toString()����������ʱ��̬
		return str+")";                                             //�ձ���()
	}
//	public String toPreviousString()                          //��������Ԫ�ص������ַ���������Ӻ���ǰ����������ʡ��
//	{
//		
//	}
	
	//����x��Ϊ��i��Ԫ�أ�x��=null������x��š���Ŭ���ˣ����׳��ն����쳣��O(n)
	//�����i��ȡ�ݴ��ʩ����i<0,�����x����ǰ�棻��i>n,�����x�����
	public int insert (int i,T x)
	{
		if(x == null)
			throw new NullPointerException("x == null");                   //�׳��ն����쳣
		if(i<0)                                              //����λ��i�ݴ���������ǰ
			i = 0;
		if(i > this.n)                                      //���������
			i = this.n;
		Object[] source = this.element;                          //�������ø�ֵ��sourceҲ����element
		if(this.n == element.length)                         //����������������˳������������
		{
			this.element = new Object[source.length*2];             //��������һ���������������
			for (int j = 0;j<i;j++)                               //���Ƶ�ǰ����ǰi-1��Ԫ��
				this.element[j] = source[j];
		}
		for(int j = this.n-1;j>=i;j--)                            //��i��ʼ����β��Ԫ������ƶ�������Ӻ���ǰ
			this.element[j+1] = source[j];
		this.element[i] = x;
		this.n++;
		return i;                                              //����x���
	}
	//˳���β����xԪ�أ�����x��š���Ա�������ء���������У�this.n��1
	public int insert(T x)
	{
		return this.insert(this.n,x);
	}

	
	public T remove(int i)           //ɾ����i��Ԫ�أ�0<=i<n�����ر�ɾ��Ԫ�ء���iԽ�磬�򷵻�null
	{
		if (this.n>0 && i>=0 && i<this.n)
		{ 
			T old = (T)this.element[i];                  //old�д洢��ɾ��Ԫ��
			for(int j = i;j<this.n-1;j++)
				this.element[j] = this.element[j+1];         //Ԫ��ǰ��һ��λ��
			this.element[this.n-1] = null;                 //��������Ԫ�ض���Ϊ�գ��ͷ�ԭ����ʵ��
			this.n--;
			return old;                              //����old�ֲ��������õĶ��󣬴��ݶ�������
		}
		return null;
	}
	
	public T remove(T key)
	{
		return this.remove(this.search(key));
	}
	
	public void clear()                                 //ɾ�����Ա�����Ԫ��
	{
		this.n = 0;                                  //���ó���Ϊ0��δ�ͷ�����ռ�
	}
	//˳������״γ�����key���Ԫ�أ�����Ԫ�����i��0<=i<n�����Ҳ��ɹ�����-1
	//��key==null�����׳��ն����쳣NullPointerException
	public int search(T key)
	{
		for (int i = 0;i<this.n;i++)
			if (key.equals(this.element[i]))               //ִ��T���equals(Object)����������ʱ��̬
				return i;
		return -1;                                      //�ձ��δ�ҵ�ʱ
	}
	//�ж��Ƿ�����ؼ���ΪkeyԪ��
	public boolean contains(T key)
	{
		return this.search(key)!=-1;
	}
	
	public boolean equals(Object obj)                     //�Ƚ�����˳����Ƿ���ȣ�����
	{
		if (this==obj)                               //��this��obj����ͬһ��˳���ʵ���������
			return true;
		if (obj instanceof SeqList<?>)                     //��obj����˳���ʵ����SeqList<?>������SeqList<T>�ĸ���
		{
			SeqList<T>list = (SeqList<T>)obj;               //����listҲ����obj���õ�ʵ��
			if (this.n==list.n)                                //�Ƚ����߳����Ƿ����
			{
				for (int i = 0;i<this.n;i++)                   //�Ƚ�����˳��������Ԫ���Ƿ����
					if(!(this.get(i).equals(list.get(i))))                     //equals(Object)��������ʱ��̬
						return true;
			}
		}
		return false;
	}
	public void addAll(SeqList<? extends T>list)                        //��this֮�����list������Ԫ�أ����ϲ�
	{
		for (int i = 0;i<list.n;i++)
			this.insert(list.get(i));                             //����ʱ��̬��˳���β���룻����˳���ֵ����
	}
	//˼����2-4
//	public <T> union(SeqList<? extends T>list)
//	{
//		SeqList<T> result = new SeqList<T>(this);
//		result.addAll(list);
//		return result;
//	}

    //��˼����2-4��ϰ����
   
    //9.5.1 ����mergeAll�ϲ����鲢�����ã�������

    boolean containsAll(SeqList<? extends T> list)  //�ж��Ƿ����set������Ԫ�أ��Ƿ��Ӽ���
    {
        for (int i=0; i<list.n; i++) 
            if (!this.contains(list.get(i)))
                return false;
        return true;
    }
//����    boolean containsAll(SeqList<?> list)
    
    boolean removeAll(SeqList<? extends T> list)           //ɾ��Ҳ������set��Ԫ�أ����ϲ�
    {
        T old=null;
        for (int i=0; i<list.n; i++) 
            old=this.remove(list.get(i));
        return old!=null;
    }
    boolean retainAll(SeqList<? extends T> list)           //��������ЩҲ������set��Ԫ�أ����ϲ�
    {
        return false;
    }

    //���ش�begin��endԪ����ɵ��ӱ���Ϊ����T��ĳ������
//    SeqList<? extends T> subList(int begin, int end)   //����Ҫ���﷨ 
    SeqList<T> subList(int begin, int end)             //���ش�begin��end��ɵ��ӱ���������ͬ   
    {
        SeqList<T> list = new SeqList<T>();
        for (int i=begin; i<end; i++) 
            list.insert(this.get(i));
        return list;
    }    
   
    
    //��10�µ�����
    //10.2.1   �ṩ����������
    //1.  ˳������ṩ������
    
    public java.util.Iterator<T>  iterator()               //����Java����������
    {   return new SeqIterator();
    }
    private class SeqIterator  implements java.util.Iterator<T>//˽���ڲ��࣬ʵ�ֵ������ӿ�
    {
        int index=-1, succ=0;                              //��ǰԪ�غͺ��Ԫ�����
        public boolean hasNext()                           //���к��Ԫ�أ�����true
        {
            return this.succ<SeqList.this.n;               //SeqList.this.n���ⲿ�൱ǰʵ���ĳ�Ա����
        }
        public T next()                                    //���غ��Ԫ�أ���û�к��Ԫ�أ�����null
        {
            T value = SeqList.this.get(this.succ);         //�����ⲿ��SeqList��ǰʵ���ĳ�Ա����
            if (value!=null)
            {   this.index = this.succ++;
                return value;
            }
            throw new java.util.NoSuchElementException();  //�׳��޴�Ԫ���쳣
        }
        public void remove()                               //ɾ�������������ʾ�ļ��ϵ�ǰԪ��
        {
            if (this.index>=0 && this.index<SeqList.this.n)
            {   SeqList.this.remove(this.index);           //�����ⲿ��SeqList��ǰʵ���ĳ�Ա����
                                                           //ɾ����index��Ԫ�أ�����SeqList.this.n-1
                if (this.succ>0)
                    this.succ--;
                this.index=-1;                             //���ò�������ɾ��
            }
            else throw new java.lang.IllegalStateException(); //�׳���Ч״̬�쳣
        }
    }//SeqIterator�ڲ������


    //��˼����10-3��˳������ṩ�б��������
    public java.util.ListIterator<T> listIterator()        //����Java�б����������
    {
        return new SeqListIterator(0);
    }
    public java.util.ListIterator<T> listIterator(final int index) //����Java�б����������
    {
        if (index>=0 && index<this.n)
            return new SeqListIterator(index);
        else throw new IndexOutOfBoundsException("Index: "+index);
    }

    //˽���ڲ��࣬�̳�ʵ�ֵ������ӿڵ�SeqIterator�ڲ��࣬ʵ���б�������ӿ�
    private class SeqListIterator extends SeqIterator implements java.util.ListIterator<T>
    {
        public SeqListIterator(int index)
        {
            this.succ=index;
        }
        public boolean hasPrevious()                       //����ǰ��Ԫ�أ�����true
        {
            return this.succ!=0;
        }

        public T previous()                                //����ǰ��Ԫ��
        {
            T value = SeqList.this.get(this.succ-1);
            if (value!=null)
            {
                this.index = this.succ--;
                return value;
            }
            throw new java.util.NoSuchElementException();  //�׳��޴�Ԫ���쳣
        }
      
        public int nextIndex()                             //���غ��Ԫ�����
        {
            return this.succ;
        }
        public int previousIndex()                         //����ǰ��Ԫ�����
        {
            return this.succ-1;
        } 
      
        public void set(T x)                               //�����ϵ�ǰԪ���滻Ϊx
        {
            if (this.index>=0 && this.index<SeqList.this.n)
                SeqList.this.set(this.index, x);           //�����ⲿ�൱ǰʵ���ĳ�Ա����
            else throw new java.lang.IllegalStateException(); //�׳���Ч״̬�쳣
        }
        public void add(T x)                               //����Ԫ��x
        {
            SeqList.this.insert(this.succ, x);             //�����ⲿ�൱ǰʵ���ĳ�Ա����
            this.succ++;                                   //����Ԫ��Ϊ��ǰԪ��
        }
    }
}
