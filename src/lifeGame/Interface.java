package lifeGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Interface extends JPanel implements MouseMotionListener {

	static private Interface interFace; // ����ϸ������״̬�����
	static private JLabel lastNumber; // ʣ������ϸ������ǩ
	static private JLabel iterations; // ��ǰ����������ǩ
	static private Logic life_Game; // �߼�����
	static private boolean gameOn = false; // �ж���Ϸ�Ƿ����ڽ��еı�ʶ
	static private boolean add = false; // �ж��Ƿ�����������ϸ�����ı�ʶ
	static private boolean sub = false; // �ж��Ƿ��ڼ�������ϸ�����ı�ʶ
	private Timer tm; // ��ʱ��

	// ���ö�ʱ��
	public Interface() {
		super();
		tm = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ʼ��ģ������
				life_Game.reproduction();
				// �ػ����
				repaint();
			}
		});

	}

	public static void main(String[] args) {
		life_Game = new Logic(40, 50);
		interFace = new Interface();
		// ��������
		JFrame iFrame = new JFrame("Conway's game of life");
		iFrame.addMouseMotionListener(interFace);
		// ��Ӳ˵�
		JMenuBar menu = new JMenuBar();
		iFrame.setJMenuBar(menu);

		JMenu options = new JMenu("Options");
		menu.add(options);
		JMenu other = new JMenu("Other");
		menu.add(other);

		JMenuItem start = options.add("Start");
		start.addActionListener(interFace.new StartActionListener());
		JMenuItem random = options.add("Random");
		random.addActionListener(interFace.new RandomActionListener());

		JMenuItem stop = options.add("Clear");
		stop.addActionListener(interFace.new ClearActionListener());
		JMenuItem pause = options.add("Pause");
		pause.addActionListener(interFace.new PauseActionListener());
		JMenuItem doityourself = options.add("Add");
		doityourself.addActionListener(interFace.new AddActionListener());
		JMenuItem clean = options.add("Subtract");
		clean.addActionListener(interFace.new SubActionListener());

		JMenuItem help = other.add("Help");
		help.addActionListener(interFace.new HelpActionListener());

		JMenuItem about = other.add("About");
		about.addActionListener(interFace.new AboutActionListener());

		// ��ӱ�ǩ���
		iFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel jPanel_Label = new JPanel();
		iFrame.getContentPane().add(jPanel_Label, BorderLayout.NORTH);
		iFrame.getContentPane().add(interFace, BorderLayout.CENTER);
		jPanel_Label.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lastNumber = new JLabel("ʣ������ϸ����Ϊ: " + 0 + "               ");
		jPanel_Label.add(lastNumber);
		iterations = new JLabel("��ǰ��������Ϊ: " + 0);
		jPanel_Label.add(iterations);
		jPanel_Label.setBackground(Color.lightGray); // ǳ��ɫ
		jPanel_Label.setSize(1000, 60);

		// ���ÿ����������
		iFrame.setLocationRelativeTo(null);
		iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iFrame.setSize(1007, 919);
		iFrame.setVisible(true);
		iFrame.setResizable(false);
	}

	// ��Random�˵������Ӧ���������ϸ������״̬
	class RandomActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gameOn == false) {
				interFace.setBackground(Color.lightGray);
				add = false;
				sub = false;
				life_Game.randomInit();
				// �ػ����
				repaint();
			}
		}
	}

	// ��Start�˵������Ӧ��������ʱ����ϸ����ʼ����
	class StartActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gameOn == false) {
				interFace.setBackground(Color.lightGray);
				add = false;
				sub = false;
				tm.start();
				gameOn = true;
			}
		}
	}

	// ��Clear�˵������Ӧ��������ϸ������״̬��Ϊ����
	class ClearActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gameOn == false) {
				interFace.setBackground(Color.lightGray);
				add = false;
				sub = false;
				life_Game.setFalse();
				// �ػ����
				repaint();
			}
		}
	}

	// ��Pause�˵������Ӧ���رն�ʱ�����л���Ϸ״̬
	class PauseActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gameOn == true) {
				interFace.setBackground(Color.lightGray);
				add = false;
				sub = false;
				tm.stop();
				gameOn = false;
			}
		}
	}

	// ��Help�˵������Ӧ��ʹ�öԻ�����ʾ�й���Ϸ������
	class HelpActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "����������Ϸ!!!\n"
					+ "1�����һ��ϸ����Χ��3��ϸ��Ϊ�������ϸ��Ϊ��;\n"
					+ "2�� ���һ��ϸ����Χ��2��ϸ��Ϊ�������ϸ��������״̬���ֲ���;\n"
					+ "3�� ����������£���ϸ��Ϊ����\n" + "��Ϸ�淨��飺\n"
					+ "���add��subtract�˵��Ȼ�������������϶������ӻ��������ϸ��\n"
					+ "���start�˵��ϸ����ʼ���ܣ����pause�˵��ϸ��ֹͣ���ܣ����clear�˵������ϸ������\n"
					+ "��������ͣ״̬ʱ���ſ���ʹ��add/subtract/clear�˵���");
		}
	}

	// ��about�˵������Ӧ��ʹ�öԻ�����ʾ��Ϸ����
	class AboutActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "��Ϸ���ߣ��캽 �ĺ���");
		}
	}

	// ��subtract�˵������Ӧ��������Ϸ״̬
	class SubActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gameOn == false) {
				sub = true;
				add = false;
				interFace.setBackground(Color.pink);
			}
		}
	}

	// ��add�˵������Ӧ��������Ϸ״̬
	class AddActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gameOn == false) {
				add = true;
				sub = false;
				interFace.setBackground(Color.cyan);
			}
		}
	}

	// ���������϶���������Ϸ״̬���ӻ��������ϸ��
	public void mouseDragged(MouseEvent e) {
		if (add == true || sub == true) {
			int x = e.getX();
			int y = e.getY();
			boolean[][] data = life_Game.get();
			if (add == true)
				data[(y - 130) / 20][x / 20] = true;
			else
				data[(y - 130) / 20][x / 20] = false;
			life_Game.set(data);
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	// ���»��Ƶ�ͼ
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// ��ȡϸ������״̬
		boolean[][] data = life_Game.get();

		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[0].length; j++) {
				if (data[i][j] == true)
					// ��ϸ�����ƺ�ɫ����
					g.fillRect(j * 20, i * 20 + 50, 20, 20);
				else
					// ��ϸ�����ư�ɫ����
					g.drawRect(j * 20, i * 20 + 50, 20, 20);
			}
		// ����ʣ��ϸ�����Լ���ǰ��������
		updateInf();
	}

	// ������Ϸ��Ϣ��ǩ��ʣ��ϸ����������������
	protected void updateInf() {
		int lives = life_Game.getAlive();
		int steps = life_Game.getIter();
		String sl = "ʣ������ϸ����Ϊ: " + lives + "               ";
		lastNumber.setText(sl);
		String si = "��ǰ��������Ϊ: " + steps;
		iterations.setText(si);
	}

}
