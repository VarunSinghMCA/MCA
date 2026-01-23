// Question1 
class q1{
    public void print(){
        System.out.println("Hello, World!");
    }
}

// Question2
class q2{
    public int add(int a, int b){
        return a + b;
    }
}

// Question3
class q3{
	public void swapTemp(int a,int b){
		int temp = a;
		a = b;
		b = temp;
		System.out.println("Variables Swaped! (with Temp)");
		System.out.println("New a: " + a + "\nNew b: "+ b);
	}
}

// Question4
class q4{
	public void swap(int a, int b){
        	a = a + b;
        	b = a - b;
        	a = a - b;
        	System.out.println("Variables Swaped!");
		System.out.println("New a: " + a + "\nNew b: "+ b);
	}
}

// Question5
class q5{
	public float calculateRadius(float R){
		return (3.14f * R * R);
	}
}

// Question6
class q6{
	public String isEvenOdd(int num){
		return (num % 2 == 0 ? "is Even" : "is Odd");
	}
}

// Question7
class q7{
	public void greaterNum2(int a, int b){
		if (a>b) System.out.println(a+" is greater than "+b);
		else System.out.println(b+" is greater than "+a);
	}
}

// Question8
class q8{
	public void greaterNum3(int a, int b, int c){
		if (a>b && a>c) System.out.println(a +" is greater than "+ b + " and "+ c);
		else if (b>a && b>c) System.out.println(b +" is greater than "+ a + " and "+ c);
		else System.out.println(c +" is greater than "+ a + " and "+ b);
	}
}

// Question9
class q9{
	public float CelToFar(float Cel) { return ((Cel * (9.0f/5.0f)) + 32.0f); }
	public float FarToCel(float Far) { return ((Far - 32.0f) / (5.0f/9.0f)); }
}

// question10
class q10{
	public void check(int num){
		if(num == 0) System.out.println("Number is Zero");
		else if(num> 0) System.out.println("Number is Positive");
		else System.out.println("Number is Negative");
	}
}

public class lvl1{
	public static void main(String[] args){
		q1 check1 = new q1();
		check1.print();

		q2 check2 = new q2();
		System.out.println("364 + 876 = " + check2.add(364,871));

		q3 check3 = new q3();
		int a = 5;
		int b = 11;
		check3.swapTemp(a,b);

		q4 check4 = new q4();
		check4.swap(a,b);

		q5 check5 = new q5();
		System.out.println("Radius of Circle = 2.5 | Area of Circle = " + check5.calculateRadius(2.5f));

		q6 check6 = new q6();
		System.out.println("7 is " + check6.isEvenOdd(7) + "and 6 is "+ check6.isEvenOdd(6));
		
		q7 check7 = new q7();
		check7.greaterNum2(23,54);

		q8 check8 = new q8();
		check8.greaterNum3(230,54,110);

		q9 check9 = new q9();
		System.out.println("Cel(20.8) to Far"+check9.CelToFar(20.8f));
        System.out.println("Far(69.4) to Cel"+check9.FarToCel(69.4f));

        q10 check10 = new q10();
        check10.check(-23);
		
	}
}