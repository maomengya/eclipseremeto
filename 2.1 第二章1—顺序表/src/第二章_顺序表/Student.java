package �ڶ���_˳���;

public class Student extends Object implements Comparable<Student>      //ѧ����
{
	String name;                                               //����
	int score;                                                   //ĳ�ſγ̳ɼ�
	public Student (String name,int score)                           //���췽��
	{
		this.name = name;
		this.score = score;
	}
	public String toString()                                      //���ض���������ַ�������ʽΪ��(,)��������
	{
		return "("+this.name+"<"+this.score+")";
	}
	public boolean equals(Object obj)                                    //�Ƚ϶����Ƿ���ȣ����Ƚ�name������
	{
		//����String���equals(Object)�������Ƚ������Ƿ����
		return this == obj||(obj instanceof Student)&&this.name.equals(((Student)obj).name);
	}
	public int compareTo(Student stu)                           //�Ƚ϶����С��ʵ��Comparable<T>�ӿ�
	{
		return this.score-stu.score;                                //���ɼ��Ƚ϶����С
	}
}
