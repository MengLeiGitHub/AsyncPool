import java.util.Vector;

public class ThreadWaitNotifyTest {
	public static void main(String args[]) {
		Vector obj = new Vector();
		Thread consumer = new Thread(new Consumer(obj));
		Thread producter = new Thread(new Producters(obj));
		consumer.start();
		producter.start();
	}
}

/* Ïû·ÑÕß */
class Consumer implements Runnable {
	private Vector obj;

	public Consumer(Vector v) {
		this.obj = v;
	}

	public void run() {
		synchronized (obj) {
			while (true) {
				try {
					if (obj.size() == 0) {
						obj.wait();
					}
					System.out.println("Consumer:goods have been taken");
					System.out.println("obj size: " + obj.size());
					obj.clear();
					obj.notify();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
	 class Producters implements Runnable {
		private Vector obj;

		public Producters(Vector v) {
			this.obj = v;
		}

		public void run() {
			synchronized (obj) {
				while (true) {
					try {
						if (obj.size() != 0) {
							System.out.println("Producter:obj are wait before");

							obj.wait();

						}

						obj.add(new String("apples"));
						obj.notify();
						System.out.println("Producter:obj are ready");
						Thread.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	
}
