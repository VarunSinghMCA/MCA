// Level 2: Decision Making & Loops

// Question 1
class q1{
    public void printOneToHundred(){
        for(int i=1; i<=100; i++){
            System.out.print(i + " ");
        }
    }
}

class q2{
    public void table(int n){
        for(int i=1; i<=10; i++){
            System.out.println( n + " x " + i + " = " + (n*i) );
        }
    }
}

class q3{
    public int factorial(int n){
        if (n == 0) return 1;
        return n * factorial(n-1);
    }
}

class q4{
    public void isPrime(int n){
        int flag = 0;
        for (int i = n; i>2; i--){
            if (n % i == 0) flag = 1;
        }
        if (flag == 0) System.out.println(n + " is a Prime Number");
        else System.out.println(n + " is not a Prime Number");
    }
}

class q5{
    public void reverseDigit(int n){
        int rev = 0;
        while (n != 0){
            int digit = n % 10;
            rev = rev * 10 + digit;
            n /= 10;
        }
        System.out.println("Reversed Number: " + rev);
    }
}

class q6{
    public void sumOfDigits(int n){
        int sum = 0;
        while (n != 0){
            int digit = n % 10;
            sum += digit;
            n /= 10;
        }
        System.out.println("Sum of Digits: " + sum);
    }
}

class q7{
    public void fibonacciSeries(int n){
        System.out.println("Fibonacci Series:");
        int num = 0;
        for (int i=0; i<=n; i++){
            num = num + i;
            System.out.print(num + " ");
        }
    }
}

class q8{
    public void palindromeCheck(String str){
        String rev = "";
        for (int i = str.length() - 1; i>=0; i--){
            rev = rev + str.charAt(i);
        }
        if (str.equals(rev)) System.out.println(str + " is a Palindrome");
        else System.out.println("\n"+str + " is not a Palindrome");
    }
}

class q9{
    public void armstrongCheck(int n){
        int originalNum = n;
        int sum = 0;
        int digits = String.valueOf(n).length();
        while (n != 0){
            int digit = n % 10;
            sum += Math.pow(digit, digits);
            n /= 10;
        }
        if (sum == originalNum) System.out.println(originalNum + " is an Armstrong Number");
        else System.out.println(originalNum + " is not an Armstrong Number");
    }
}

class q10{
    public void gcdLcm(int a, int b){
        int gcd = 1;
        for (int i = 1; i <= Math.min(a, b); i++){
            if (a % i == 0 && b % i == 0) gcd = i;
        }
        int lcm = (a * b) / gcd;
        System.out.println("GCD: " + gcd);
        System.out.println("LCM: " + lcm);
    }   
}


public class lvl2 {
    public static void main(String[] args){
        q1 check1 = new q1();
        check1.printOneToHundred();

        q2 check2 = new q2();
        check2.table(7);

        q3 check3 = new q3();
        int fact = check3.factorial(5);
        System.out.println("Factorial: " + fact);

        q4 check4 = new q4();
        check4.isPrime(29);

        q5 check5 = new q5();
        check5.reverseDigit(12345);

        q6 check6 = new q6();
        check6.sumOfDigits(12345);

        q7 check7 = new q7();
        check7.fibonacciSeries(10);

        q8 check8 = new q8();
        check8.palindromeCheck("madam");

        q9 check9 = new q9();
        check9.armstrongCheck(153);

        q10 check10 = new q10();
        check10.gcdLcm(54, 24);
    }    
}
