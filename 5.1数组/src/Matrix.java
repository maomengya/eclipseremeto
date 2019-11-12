
public class Matrix                             //����
{
	private int rows,columns;                        //��������������
	private int[][] element;                        //��ά���飬�洢����Ԫ��
	public Matrix(int m,int n)                      //����mxn�������m��nΪ������Java�׳������鳤���쳣
	{
		this.element = new int[m][n];                 //����Ԫ�س�ֵΪ0
		this.rows = m;
		this.columns = n;
	}
	
	public Matrix(int n)               //����nxn�㷽��
	{
		this(n,n);
	}
	
	public Matrix(int m,int n,int[][] value)               //����mxn������value[][]�ṩԪ��
	{
		this(m,n);
		for(int i = 0;i<value.length&&i<m;i++)           //valueԪ�ز���ʱ��0�����Զ���Ԫ��
			for(int j = 0;j<value[i].length&&j<n;j++)
				this.element[i][j] = value[i][j];
	}
	
	//����mxn���󣬸���mat����Ԫ��
	public Matrix(int m,int n,Matrix mat)
	{
		this(m,n);
		for(int i = 0;i<mat.rows&&i<m;i++)           //valueԪ�ز���ʱ��0�����Զ���Ԫ��
			for(int j = 0;j<mat.columns&&j<n;j++)
				this.element[i][j] = mat.element[i][j];
	}
	
	//����mxn����Ԫ��Ϊx
	public Matrix(int m,int n,int x)
	{
		this(m,n);
		for(int i = 0;i<m;i++)           //valueԪ�ز���ʱ��0�����Զ���Ԫ��
			for(int j = 0;j<n;j++)
				this.element[i][j] = x;
	}
	
	//���쿽�����������
	public Matrix(Matrix mat)
	{
		this(mat.rows,mat.columns);
		for(int i = 0;i<mat.rows;i++)           //valueԪ�ز���ʱ��0�����Զ���Ԫ��
			for(int j = 0;j<mat.columns;j++)
				this.element[i][j] = mat.element[i][j];
	}
	
	//�Ƚ�����ͬ�׾����Ƿ����
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
	
	//���ص�ǰ�����ת�þ���
	public Matrix transpose()
	{
		Matrix mat = new Matrix(this.columns,this.rows);                 
		for(int i = 0;i < this.columns;i++)
			for(int j = 0;j < this.rows;j++)
				mat.element[i][j] = this.element[j][i];
		return mat;
	}
	
	//�ж��Ƿ�Ϊ��/�����Ǿ���
	public boolean isTriangular()
	{
		int f = 0;
		//������
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
			//������
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
	
	//�ж��Ƿ�Ϊ�Գƾ���
	public boolean isSymmetric()
	{
		for(int i = 0;i<this.rows;i++)
			for(int j = 0;j<i;j++)
				if(this.element[i][j]!=this.element[j][i])
					return false;
		return true;
	}
	
	//this��mat��ӣ���Ԫ�ض�Ӧ���
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
			System.out.println("�������Ͳ�ͬ�����������");
	}
	
	//����this��mat��ӵľ��󣬲��ı�this
	public Matrix union(Matrix mat)
	{
		this.addAll(mat);
		return mat;
	}
	
	//����this��mat��˵ľ��󣬲��ı�this
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
	
	//���ؾ���İ���ֵ�����㣺������С����������Ԫ�أ�
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
	
	public int getRows()               //���ؾ�������
	{
		return this.rows;
	}
	
	public int getColumns()               //���ؾ�������
	{
		return this.columns;
	}
	
	public void set(int i,int j,int x)               //���ؾ����i�е�j��Ԫ��Ϊx����i��jԽ�磬�׳����Խ���쳣
	{
		if(i>=0&&i<this.rows&&j>=0&&j<this.columns)
			this.element[i][j] = x;
		else
			throw new IndexOutOfBoundsException("i="+i+",j="+j);
	}
	
	public String toString()               //���ؾ���Ԫ�������ַ��������������
	{
		String str = "����"+this.getClass().getName()+"("+this.rows+"x"+this.columns+"):\n";
		for(int i = 0;i<this.rows;i++)
		{
			for(int j = 0;j<this.columns;j++)
				str+=String.format("%6d", this.element[i][j]);               //��%6d����ʽ��ʾʮ��������ռ6��
			str+="\n";
		}
		return str;
	}
	//���þ���Ϊm��n�С�������ָ���������ϴ��򽫾������ݣ�������ԭ����Ԫ��
	//����7.2.1��ͼ���ڽӾ���洢�ṹ
	public void setRowsColumns(int m,int n)
	{
		if(m>0&&n>0)
		{
			if(m>this.element.length||n>this.element[0].length)
			{//����ָ���������������������ϴ�ʱ�������ά��������
				int[][] source = this.element;
				this.element = new int[m][n];               //��ѯ�����ά����ռ䣬Ԫ�س�ֵΪ0
				for(int i = 0 ;i<this.columns;i++)               //����ԭ��ά����Ԫ��
					for(int j = 0;j<this.rows;j++)
						this.element[i][j] = source[i][j];
			}
			this.rows = m;
			this.columns = n;
		}
		else
			throw new IllegalArgumentException("��������������<=0,m"+m+",n="+n);
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
