package 第四章;

public interface Stack<T>
{
	    public abstract boolean isEmpty();           //判断栈是否空
	    public abstract void push(T x);              //元素x入栈
	    public abstract T peek();                    //返回栈顶元素，未出栈
	    public abstract T pop();                     //出栈，返回栈顶元素
}
