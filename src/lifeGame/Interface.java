package lifeGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Interface extends JPanel implements MouseMotionListener {

	static private Interface interFace; // 绘制细胞生存状态的面板
	static private JLabel lastNumber; // 剩余生存细胞数标签
	static private JLabel iterations; // 当前迭代次数标签
	static private Logic life_Game; // 逻辑对象
	static private boolean gameOn = false; // 判断游戏是否正在进行的标识
	static private boolean add = false; // 判断是否在增加生存细胞数的标识
	static private boolean sub = false; // 判断是否在减少生存细胞数的标识
	private Timer tm; // 计时器

	// 设置定时器
	public Interface() {
		super();
		tm = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 初始化模型数据
				life_Game.reproduction();
				// 重绘界面
				repaint();
			}
		});

	}

	public static void main(String[] args) {
		life_Game = new Logic(40, 50);
		interFace = new Interface();
		// 建立窗口
		JFrame iFrame = new JFrame("Conway's game of life");
		iFrame.addMouseMotionListener(interFace);
		// 添加菜单
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

		// 添加标签面板
		iFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel jPanel_Label = new JPanel();
		iFrame.getContentPane().add(jPanel_Label, BorderLayout.NORTH);
		iFrame.getContentPane().add(interFace, BorderLayout.CENTER);
		jPanel_Label.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lastNumber = new JLabel("剩余生存细胞数为: " + 0 + "               ");
		jPanel_Label.add(lastNumber);
		iterations = new JLabel("当前迭代次数为: " + 0);
		jPanel_Label.add(iterations);
		jPanel_Label.setBackground(Color.lightGray); // 浅灰色
		jPanel_Label.setSize(1000, 60);

		// 设置框架容器属性
		iFrame.setLocationRelativeTo(null);
		iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iFrame.setSize(1007, 919);
		iFrame.setVisible(true);
		iFrame.setResizable(false);
	}

	// 对Random菜单项的响应，随机生成细胞生存状态
	class RandomActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gameOn == false) {
				interFace.setBackground(Color.lightGray);
				add = false;
				sub = false;
				life_Game.randomInit();
				// 重绘界面
				repaint();
			}
		}
	}

	// 对Start菜单项的响应，开启定时器，细胞开始繁衍
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

	// 对Clear菜单项的响应，将所有细胞生存状态置为死亡
	class ClearActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gameOn == false) {
				interFace.setBackground(Color.lightGray);
				add = false;
				sub = false;
				life_Game.setFalse();
				// 重绘界面
				repaint();
			}
		}
	}

	// 对Pause菜单项的响应，关闭定时器，切换游戏状态
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

	// 对Help菜单项的响应，使用对话框提示有关游戏的内容
	class HelpActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "这是生命游戏!!!\n"
					+ "1．如果一个细胞周围有3个细胞为生，则该细胞为生;\n"
					+ "2． 如果一个细胞周围有2个细胞为生，则该细胞的生死状态保持不变;\n"
					+ "3． 在其它情况下，该细胞为死。\n" + "游戏玩法简介：\n"
					+ "点击add或subtract菜单项，然后点击鼠标左键并拖动可增加或减少生存细胞\n"
					+ "点击start菜单项，细胞开始繁衍，点击pause菜单项，细胞停止繁衍，点击clear菜单项，所有细胞死亡\n"
					+ "当处于暂停状态时，才可以使用add/subtract/clear菜单项");
		}
	}

	// 对about菜单项的响应，使用对话框提示游戏作者
	class AboutActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "游戏作者：朱航 夏洪玮");
		}
	}

	// 对subtract菜单项的响应，设置游戏状态
	class SubActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gameOn == false) {
				sub = true;
				add = false;
				interFace.setBackground(Color.pink);
			}
		}
	}

	// 对add菜单项的响应，设置游戏状态
	class AddActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gameOn == false) {
				add = true;
				sub = false;
				interFace.setBackground(Color.cyan);
			}
		}
	}

	// 监视鼠标的拖动，依据游戏状态增加或减少生存细胞
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

	// 重新绘制地图
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 获取细胞生存状态
		boolean[][] data = life_Game.get();

		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[0].length; j++) {
				if (data[i][j] == true)
					// 活细胞绘制黑色方块
					g.fillRect(j * 20, i * 20 + 50, 20, 20);
				else
					// 死细胞绘制白色方块
					g.drawRect(j * 20, i * 20 + 50, 20, 20);
			}
		// 更新剩余细胞数以及当前迭代次数
		updateInf();
	}

	// 更新游戏信息标签（剩余细胞数及迭代次数）
	protected void updateInf() {
		int lives = life_Game.getAlive();
		int steps = life_Game.getIter();
		String sl = "剩余生存细胞数为: " + lives + "               ";
		lastNumber.setText(sl);
		String si = "当前迭代次数为: " + steps;
		iterations.setText(si);
	}

}
