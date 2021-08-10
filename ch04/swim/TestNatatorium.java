package juc.swim;

public class TestNatatorium {
	public static void main(String args[]) {
		try {
			Natatorium natatorium = new Natatorium();
			Thread nataThread = new Thread(natatorium);
			nataThread.start();
			natatorium.addSwimmer("zs", 30);
			natatorium.addSwimmer("ls", 45);
			natatorium.addSwimmer("ww", 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
