package cc.niushuai.tools.everything.frame;

import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cc.niushuai.tools.everything.util.VariableConstant;

public class MainFrame extends JFrame {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 932054782520408962L;

	private JPanel contentPane;
	private JTextField searchTextField;
	private JComboBox<String> filterComboBox;
	private JTable table_1;

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		// 创建工具栏
		createMenuBar();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		searchTextField = new JTextField();
		searchTextField.setHorizontalAlignment(SwingConstants.LEFT);
		searchTextField.setColumns(10);
				
		filterComboBox = addFilterComboBox();
		
		setTable();
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(searchTextField, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
							.addGap(20)
							.addComponent(filterComboBox, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(filterComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
		);
		
		
		scrollPane.setViewportView(table_1);
		
		
		contentPane.setLayout(gl_contentPane);
	}

	private void setTable() {
		// 表格上的title
        String[] columnNames = new String[] { "id", "name", "hp", "damage" };
        // 表格中的内容，是一个二维数组
        String[][] heros = new String[][] {
        		{ "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" },
                { "3", "奎因", "832", "200" }
        };
        table_1 = new JTable(heros, columnNames);
        
        table_1.getColumnModel();
        table_1.setAutoCreateRowSorter(true);
        table_1.setFillsViewportHeight(true);
	
	}

	private JComboBox<String> addFilterComboBox() {
		filterComboBox = new JComboBox<String>();
		VariableConstant.FILTER_COMBOX_ITEM_LIST.forEach(filterComboBox::addItem);
		return filterComboBox;
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		// 创建工具栏 - 文件
		createFileMenu(menuBar);
		// 创建工具栏 - 编辑
		createEditMenu(menuBar);
		// 创建工具栏 - 选项
		createToolMenu(menuBar);
		// 创建工具栏 - 设置
		createSettingMenu(menuBar);
		// 创建工具栏 - 关于
		createAboutMenu(menuBar);
	}

	private void createFileMenu(JMenuBar menuBar) {
		JMenu fileMenu = new JMenu("文件 (F)");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);

		JMenuItem exportMenuItem = new JMenuItem("导出 (Ctrl + E)", KeyEvent.VK_E);
		fileMenu.add(exportMenuItem);

		fileMenu.addSeparator();
		JMenuItem exitMenuItem = new JMenuItem("退出 (Ctrl + Q)", KeyEvent.VK_Q);
		fileMenu.add(exitMenuItem);
	}

	private void createEditMenu(JMenuBar menuBar) {
		JMenu editMenu = new JMenu("编辑 (E)");
		editMenu.setMnemonic(KeyEvent.VK_E);
		menuBar.add(editMenu);

		JMenuItem copyMenuItem = new JMenuItem("复制 (Ctrl + C)", KeyEvent.VK_C);
		editMenu.add(copyMenuItem);

		JMenuItem pasteMenuItem = new JMenuItem("粘贴 (Ctrl + V)", KeyEvent.VK_V);
		editMenu.add(pasteMenuItem);

		JMenuItem cutMenuItem = new JMenuItem("剪切 (Ctrl + X)", KeyEvent.VK_X);
		editMenu.add(cutMenuItem);
	}

	private void createToolMenu(JMenuBar menuBar) {
		JMenu toolMenu = new JMenu("搜索 (T)");
		toolMenu.setMnemonic(KeyEvent.VK_T);

		menuBar.add(toolMenu);

		JCheckBoxMenuItem caseSenMenuItem = new JCheckBoxMenuItem("区分大小写");
		toolMenu.add(caseSenMenuItem);

		JCheckBoxMenuItem allMatchMenuItem = new JCheckBoxMenuItem("全字匹配");
		toolMenu.add(allMatchMenuItem);
	}

	private void createSettingMenu(JMenuBar menuBar) {
		JMenu settingMenu = new JMenu("设置 (S)");
		settingMenu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(settingMenu);

		JMenuItem setMenuItem = new JMenuItem("选项");
		settingMenu.add(setMenuItem);
	}

	private void createAboutMenu(JMenuBar menuBar) {
		JMenu aboutMenu = new JMenu("关于 (A)");
		aboutMenu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(aboutMenu);

		JMenuItem aboutMenuItem = new JMenuItem("关于");
		aboutMenu.add(aboutMenuItem);
	}
}
