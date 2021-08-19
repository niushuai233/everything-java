package cc.niushuai.tools.everything;

import java.awt.EventQueue;

import javax.swing.JFrame;

import cc.niushuai.tools.everything.frame.MainFrame;
import cc.niushuai.tools.everything.util.SwingUtils;

public class EverythingApplication {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EverythingApplication window = new EverythingApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EverythingApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new MainFrame();
		frame.setBounds(300, 300, 450, 300);
		SwingUtils.setCenter(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
