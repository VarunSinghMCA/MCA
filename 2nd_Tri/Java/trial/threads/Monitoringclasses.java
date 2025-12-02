// class mcabclass extends Thread
// {
// private String session;

// 	mcabclass(String session)
// 	{
// 		this.session=session;
// 	}
	
// 	public void run()
// 	{
// 	System.out.println(session + " is being handled by " + Thread.currentThread().getName());
	
// 	}

// }

// class mcabclass2 implements Runnable{
// private String session;

// 	mcabclass2(String session)
// 	{
// 		this.session=session;
// 	}
	
// 	public void run()
// 	{
// 	System.out.println(session + " is being handled by " + Thread.currentThread().getName());
	
// 	}
// }

// public class Monitoringclasses
// {
// 	public static void main(String[] args)
// 	{
		
// 	//Using Thread class
// 	mcabclass t1 = new mcabclass("Java");
// 	mcabclass t2 = new mcabclass("Data Structure");
// 	mcabclass t3 = new mcabclass("OS");
	
// 	//Using Runnable interface
// 	Runnable r1 = new mcabclass2("Java 2");
// 	Runnable r2 = new mcabclass2("Data Structure 2");
// 	Runnable r3 = new mcabclass2("OS 2");

// 	//or
// 	Thread r4 = new Thread(new mcabclass2("Networking 2"));


// 	t1.start();
// 	t2.start();
// 	t3.start();
	
// 	new Thread(r1).start();
// 	new Thread(r2).start();
// 	new Thread(r3).start();

// 	r4.start();


// 	}

// }