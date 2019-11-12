
public class Matrix                             //矩阵
{
	private int rows,columns;                        //矩阵行数、列数
	private int[][] element;                        //二维数组，存储矩阵元素
	public Matrix(int m,int n)                      //构造mxn零矩阵。若m或n为负数，Java抛出负数组长度异常
	{
		this.element = new int[m][n];                 //数组元素初值为0
		this.rows = m;
		this.columns = n;
	}
	
	public Matrix(int n)               //构造nxn零方阵
	{
		this(n,n);
	}
	
	public Matrix(int m,int n,int[][] value)               //构造mxn矩阵，由value[][]提供元素
	{
		this(m,n);
		for(int i = 0;i<value.length&&i<m;i++)           //value元素不足时补0，忽略多余元素
			for(int j = 0;j<value[i].length&&j<n;j++)
				this.element[i][j] = value[i][j];
	}
	
	//构造mxn矩阵，复制mat矩阵元素
	public Matrix(int m,int n,Matrix mat)
	{
		this(m,n);
		for(int i = 0;i<mat.rows&&i<m;i++)           //value元素不足时补0，忽略多余元素
			for(int j = 0;j<mat.columns&&j<n;j++)
				this.element[i][j] = mat.element[i][j];
	}
	
	//构造mxn矩阵，元素为x
	public Matrix(int m,int n,int x)
	{
		this(m,n);
		for(int i = 0;i<m;i++)           //value元素不足时补0，忽略多余元素
			for(int j = 0;j<n;j++)
				this.element[i][j] = x;
	}
	
	//构造拷贝方法，深拷贝
	public Matrix(Matrix mat)
	{
		this(mat.rows,mat.columns);
		for(int i = 0;i<mat.rows;i++)           //value元素不足时补0，忽略多余元素
			for(int j = 0;j<mat.columns;j++)
				this.element[i][j] = mat.element[i][j];
	}
	
	//比较两个同阶矩阵是否相等
	public boolean equals(Object obj)
	{
		 if (this==obj)
	        return true;
	     if (!(obj instanceof Matrix))
	        return false;
	     Matrix mat=(Matrix)obj;   
	     if(this.rows == mat.rows&&this.columns == mat.columns)
	    	 for(int i = 0;i<this.rows;i++)
	    		 for(int j = 0; j<this.columns;j++)
	    		 {
	    			 if(this.element[i][j] != mat.element[i][j])
	    				 return false;
	    		 }
	     else
	    	 return false;
	     return true;
	}
	
	//返回当前矩阵的转置矩阵
	public Matrix transpose()
	{
		Matrix mat = new Matrix(this.columns,this.rows);                 
		for(int i = 0;i < this.columns;i++)
			for(int j = 0;j < this.rows;j++)
				mat.element[i][j] = this.element[j][i];
		return mat;
	}
	
	//判断是否为上/下三角矩阵
	public boolean isTriangular()
	{
		int f = 0;
		//上三角
		for(int i = 1;i<this.rows;i++)
			for(int j = 0;j<i;j++)
				if(this.element[i][j]!=0)
				{
					f++;	
					break;
				}
		if(f == 0)
			return true;
		else
		{
			//下三角
			f = 0;
			for(int i = 0;i<this.rows-1;i++)
				for(int j = this.columns-1;j>i;j--)
					if(this.element[i][j] != 0)
					{
						f++;
						break;
					}
			if(f == 0)
				return true;
			else
				return false;
		}
	}
	
	//判断是否为对称矩阵
	public boolean isSymmetric()
	{
		for(int i = 0;i<this.rows;i++)
			for(int j = 0;j<i;j++)
				if(this.element[i][j]!=this.element[j][i])
					return false;
		return true;
	}
	
	//this和mat相加，各元素对应相加
	public void addAll(Matrix mat)
	{
		if(this.rows==mat.rows&&this.columns==mat.columns)
		{
			for(int i = 0;i<this.rows;i++)
				for(int j = 0;j<this.columns;j++)
					mat.element[i][j]+=this.element[i][j];
//			System.out.println(mat);
		}
				
		else
			System.out.println("矩阵类型不同，不可以相加");
	}
	
	//返回this和mat相加的矩阵，不改变this
	public Matrix union(Matrix mat)
	{
		this.addAll(mat);
		return mat;
	}
	
	//返回this与mat相乘的矩阵，不改变this
	public Matrix multi(Matrix mat)
	{
		if(this.columns == mat.rows)
		{
			for(int i = 0 ; i<this.rows;i++)
				for(int j = 0 ; j<mat.columns;j++)
					for(int m = 0 ; m < this.columns;m++)
						for(int n = 0;n < mat.rows;n++)
							mat.element[i][j] += mat.element[i][m]*this.element[n][j];
			return mat;
		}
		else
			return null;
	}
	
	//返回矩阵的鞍点值（鞍点：行上最小且列上最大的元素）
	public int saddlePoint()
	{
		int m = 0;
		int c = 0;
		for(int i = 0; i<this.rows;i++)
		{
			for(int j = 0;j<this.columns;j++)
				if(this.element[i][j]<this.element[i][m])
					m = j;
			for(int k = 0;k<this.rows;k++)
				if(this.element[k][m]>this.element[i][m])
					continue;
			c = this.element[i][m];
		}
		return c;
				
	}
	
	public int getRows()               //返回矩阵行数
	{
		return this.rows;
	}
	
	public int getColumns()               //返回矩阵列数
	{
		return this.columns;
	}
	
	public void set(int i,int j,int x)               //返回矩阵第i列第j行元素为x。若i、j越界，抛出序号越界异常
	{
		if(i>=0&&i<this.rows&&j>=0&&j<this.columns)
			this.element[i][j] = x;
		else
			throw new IndexOutOfBoundsException("i="+i+",j="+j);
	}
	
	public String toString()               //返回矩阵元素描述字符串，行主序遍历
	{
		String str = "矩阵"+this.getClass().getName()+"("+this.rows+"x"+this.columns+"):\n";
		for(int i = 0;i<this.rows;i++)
		{
			for(int j = 0;j<this.columns;j++)
				str+=String.format("%6d", this.element[i][j]);               //“%6d”格式表示十进制整数占6列
			str+="\n";
		}
		return str;
	}
	//设置矩阵为m行n列。若参数指定行列数较大，则将矩阵扩容，并复制原矩阵元素
	//用于7.2.1节图的邻接矩阵存储结构
	public void setRowsColumns(int m,int n)
	{
		if(m>0&&n>0)
		{
			if(m>this.element.length||n>this.element[0].length)
			{//参数指定的行数或列数或列数较大时，扩充二维数组容量
				int[][] source = this.element;
				this.element = new int[m][n];               //查询申请二维数组空间，元素初值为0
				for(int i = 0 ;i<this.columns;i++)               //复制原二维数组元素
					for(int j = 0;j<this.rows;j++)
						this.element[i][j] = source[i][j];
			}
			this.rows = m;
			this.columns = n;
		}
		else
			throw new IllegalArgumentException("矩阵行列数不能<=0,m"+m+",n="+n);
	}
	
	public static void main(String args[])
	{
		int value1[][] = {{1,2,3},{4,5,6,7,8},{9}};
		Matrix mata = new Matrix(3,4,value1);
		mata.set(2,3,10);
		System.out.print("A"+mata.toString());
		
		int value2[][] = {{2,3,1},{3,2,5},{5,3,7}};
		int value3[][] = {{1,0,0},{2,3,0},{4,3,1}};
		Matrix matb = new Matrix(3,3,value2);
		Matrix matc = new Matrix(3,3,matb);
		Matrix matd = new Matrix(3,3,value3);
		
		System.out.println(matc);
		System.out.println("equals");
		System.out.println(matc.equals(matb));
		System.out.println("transpose");
		System.out.println(matc.transpose());
		System.out.println("isTriangular1");
		System.out.println(matc.isTriangular());
		System.out.println("isTriangular2");
		System.out.println(matd.isTriangular());
		System.out.println("union");
		System.out.println(matd.union(matc));
		System.out.println("multi");
		System.out.println(matd.multi(matc));
		System.out.println("saddlePoint");
		System.out.println(matd.saddlePoint());
		
		
	}

}
