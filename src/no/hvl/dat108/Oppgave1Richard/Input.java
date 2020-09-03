package no.hvl.dat108.Oppgave1Richard;

import javax.swing.JOptionPane;

public class Input {

	private String input;
	private boolean fortsette = true;

	public void skrivInn() throws InterruptedException {

		while (fortsette == true) {
			input = JOptionPane.showInputDialog(null, "Input, press quit to quit");
			if (input.equals("quit")) {
				fortsette = false;
			}

		}
	}

	public void skrivUt() throws InterruptedException {

		while (input == null) {
			Thread.sleep(1000);
		}
		while (fortsette) {
			if (input.equals("quit")) {
				fortsette = false;
			}
			System.out.println(input);
			Thread.sleep(3000);
		}
	}
}
