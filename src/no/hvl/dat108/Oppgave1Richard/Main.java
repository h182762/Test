package no.hvl.dat108.Oppgave1Richard;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		Input input = new Input();

		Thread in = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					input.skrivInn();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread out = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					input.skrivUt();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		in.start();
		out.start();

	}

}
