package 第二章_顺序表;

public class SeqList_Student 
{
	//分类统计线性表list的元素信息，分段信息存于grade数组，返回保存统计结果的数组
	public static int[] groupCount(SeqList<Student>list,int grade[])                   
	{
		int result[] = new int[grade.length];                             //result数组保存统计结果
		for(int i = 0;i<list.size();i++)
		{
			Student stu = list.get(i);                                   //获得list的第i个元素，对象引用赋值
			for(int j = 0;j<grade.length-1;j++)
				if (stu.score>=grade[j]&&stu.score<grade[j+1])            //判断stu范围
				{
					result[j]++;
					break;                                                 //退出内层循环
				}
		}
		return result;                                                      //返回result数组变量引用的数组
	}
	public static void printCount(SeqList<Student>list,String titles[],int result[])
	{
		System.out.print("学生集合："+list.toString()+"\n共"+list.size()+"人，成绩统计：");
		for(int i=0;i<titles.length;i++)
			System.out.print(titles[i]+result[i]+"人，");
		System.out.println();
	}
	public static void main(String args[]) 
	{
		Student group[] = {new Student("王红",85),new Student("张明",75),new Student("李强",90),new Student("崔小兵",80),new Student("陈新诺",60),new Student("吴宇",65)};
		SeqList<Student>lista = new SeqList<Student>(group);                          //构造顺序表，由数组提高初值
		lista.insert(new Student("崔小兵",70));                                             //尾插入
		int[] grade = {0,60,70,80,90,100};                                          //指定分段信息
		String[] titles = {"不及格","及格","中等","良好","优秀"};                                          //字符串数组指定分类名称
		int[] result = groupCount(lista,grade);                                      //分类统计，返回存放统计结果的数组
		printCount(lista,titles,result);
		String name = "崔小兵";
		Student key = new Student(name,0);                                            //key包含姓名，比较相等，按姓名查找
		System.out.println("\""+name+"\"的成绩是："+lista.get(lista.search(key)).score);
		System.out.println("删除"+lista.remove(key));
		SeqList<Student>slistb = new SortedSeqList<Student>(lista);
		result = groupCount(slistb,grade);
		printCount(slistb,titles,result);
		int score = 70;
		key = new Student("",score);
		System.out.println(""+score+""+slistb.get(slistb.search(key)).name);
	}

}
