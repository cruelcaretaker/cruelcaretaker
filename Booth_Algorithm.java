import java.util.InputMismatchException;
import java.util.Scanner;
public class Booth_Algorithm
{ 
	/*
	 * This is a program with calculates the product of two signed or unsigned numbers.
	 * It calculates the product of the given numbers using the Booth's Algorithm.
	 * The magnitude of the multiplicand term must be greater than that of the multiplier.
	 * The result comes in the form of binary numbers.
	 */
	String negative(String s)
	{
		/*
		 * This user defined function takes a binary number in String format.
		 * It return the 2's complement of the given number in String format.
		 */
		int i,l=s.lastIndexOf("1"),n=s.length();
		String st="";
		for(i=0;i<l;i++)
		{
			if(s.charAt(i)=='0')
				st+='1';
			if(s.charAt(i)=='1')
				st+='0';
		}
		for(i=l;i<n;i++)
			st=st+s.charAt(i);
		return st;
	}
	void algorithm(String M,String Q,String Mc)
	{
		/*
		 * This function does the main part of the booth's algorithm i.e. shifting and adding.
		 */
		String A="",a="",mess="";
		char Q1='0',t='0';
		int n=Q.length();
		int i,j;
		for(i=0;i<n;i++)
			A+="0";
		System.out.printf("A\tQ\tQ-1\tOPERATION\tcount\t");
		System.out.printf("\n");
		System.out.printf("%s\t%s\t%c\t%s\t\t%d\t",A,Q,Q1,"INITIAL",n-1);
		System.out.printf("\n");
		for(i=n-1;i>=0;i--)
		{
			if (Q.charAt(n-1)> Q1)
			{
				A=add(A,Mc);
				mess="A=A-M";
				System.out.printf("%s\t%s\t%c\t%s\t\t%d\t",A,Q,Q1,mess,i);
				System.out.printf("\n");
			}
			else if (Q.charAt(n-1) < Q1)
			{
				A=add(A,M);
				mess="A=A+M";
				System.out.printf("%s\t%s\t%c\t%s\t\t%d\t",A,Q,Q1,mess,i);
				System.out.printf("\n");
			}
			Q1=Q.charAt(n-1);
			for(j=0;j<n;j++)
			{
				if(j==0 && A.charAt(j)=='0')
					a+='0';
				if(j==0 && A.charAt(j)=='1')
					a+='1';
				if(j>0)
					a=a+A.charAt(j-1);
			}
			t=A.charAt(n-1);
			A=a;
			a="";
			for(j=0;j<n;j++)
			{
				if(j==0)
					a+=t;
				else
					a+=Q.charAt(j-1);
			}
			Q=a;
			a="";
			System.out.printf("%s\t%s\t%c\t%s\t\t%d\t",A,Q,Q1,"SHIFT",i);
			System.out.printf("\n");
		}
		System.out.println("Result : "+(A+Q));
	}
	String add(String A , String M)
	{
		/*
		 * This function add the given binary number (in String format). 
		 */
		
		int i=0,j=M.length()-1,carry=0,sum[]=new int[M.length()*2];
		long a=Long.parseLong(A);
		long m=Long.parseLong(M);
		String result="";
		while(a!=0 || m!=0)
		{
			sum[i++]=(int)((a%10 + m%10 + carry)%2);
			carry=(int)((a%10 + m%10 + carry)/2);
			a/=10;
			m/=10;
		}
		if(carry!=0)
		{
			sum[i++]=carry;
		}
		i--;
		while(j>=0)
		{
			result+=sum[j];
			j--;
		}
		return result;
		
	}
	public static void main(String[] args)
	{
		try (Scanner in = new Scanner(System.in)) {
			Booth_Algorithm ob = new Booth_Algorithm();
			int i,x;
			String Mc="",Qc="";
			try
			{
			System.out.println("Enter the multiplicand : ");
			int m=in.nextInt();
			System.out.println("Enter the multiplier : ");
			int q=in.nextInt();
			String M=Integer.toBinaryString(m);
			String Q=Integer.toBinaryString(q);
			if(m>0)
			{
				M="0"+M;
				Mc=ob.negative(M);
			}
			if(m<0)
			{
				m=Math.abs(m);
				M=Integer.toBinaryString(m);
				M="0"+M;
				M=ob.negative(M);
				Mc=ob.negative(M);
			}
			x=M.length()-Q.length();
			for(i=0;i<x;i++)
				Q="0"+Q;
			if(q>=0)
			{
				Qc=ob.negative(Q);
			}	
			if(q<0) 
			{
				q=Math.abs(q);
				Q=Integer.toBinaryString(q);
				x=M.length()-Q.length();
				for(i=0;i<x;i++)
					Q="0"+Q;
				Q=ob.negative(Q);
				Qc=ob.negative(Q);
			}
			System.out.println("M : "+M+"\tQ : "+Q+"\tMc : "+Mc+"\tQc : "+Qc);
			ob.algorithm(M,Q,Mc);
			}
			catch(InputMismatchException e)
			{
				System.out.println("PLEASE ENTER VALID DATA");
			}
		}
	}
}