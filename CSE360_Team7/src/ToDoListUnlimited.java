import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import java.awt.Panel;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

//import Buttons.ButtonListener;

import javax.swing.JFormattedTextField;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.DropMode;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

public class ToDoListUnlimited {

	// CREATE GETTERS & SETTERS TO ENCAPSULATE THIS CLASS
	public static JFrame frame;
	public static int index;
	public static JTable table;
	public JButton btnAddItem;
	public static Object[][] info = new Object[50][4];
	private static int iteration = 3;
	
	//ICONS
	ImageIcon addIcon = new ImageIcon("add.png");
	ImageIcon editIcon = new ImageIcon("edit.png");
	ImageIcon deleteIcon = new ImageIcon("delete.png");
	ImageIcon printIcon = new ImageIcon("print.png");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToDoListUnlimited window = new ToDoListUnlimited();
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
	public ToDoListUnlimited() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("To-Do List Unlimited");
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setBounds(150, 150, 675, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTodoListUnlimited = new JLabel("To-Do List Unlimited 2019");
		lblTodoListUnlimited.setBounds(0, 17, 675, 37);
		lblTodoListUnlimited.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		lblTodoListUnlimited.setHorizontalAlignment(SwingConstants.CENTER);
		lblTodoListUnlimited.setToolTipText("Displays application name");
		frame.getContentPane().add(lblTodoListUnlimited);
		
		btnAddItem = new JButton(addIcon);
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNote newNote = new AddNote();
				newNote.NewAddScreen();
			}
		});
		
		btnAddItem.setBounds(20, 88, 29, 29);
		frame.getContentPane().add(btnAddItem);
		
		JButton btnEditItem = new JButton(editIcon);
		btnEditItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNote newNote = new AddNote();
				newNote.NewAddScreen();
			}
		});
		
		btnEditItem.setBounds(60, 88, 29, 29);
		frame.getContentPane().add(btnEditItem);
		
		JLabel lblSortBy = new JLabel("Sort By:");
		lblSortBy.setBounds(425, 93, 47, 16);
		frame.getContentPane().add(lblSortBy);
		
		JButton btnPrint = new JButton(printIcon);
		btnPrint.setBounds(625, 88, 32, 32);
		frame.getContentPane().add(btnPrint);
		
		JButton btnClearList = new JButton(deleteIcon); 
		btnClearList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Needs functionality
			}
		});
		
		btnClearList.setBounds(100, 88, 29, 29);
		frame.getContentPane().add(btnClearList);
		
		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setBounds(20, 130, 100, 16);
		frame.getContentPane().add(lblDescription);
		
		JLabel lblDueDate = new JLabel("DUE DATE");
		lblDueDate.setBounds(180, 130, 84, 16);
		frame.getContentPane().add(lblDueDate);
		
		JLabel lblStatus = new JLabel("STATUS");
		lblStatus.setBounds(340, 130, 61, 16);
		frame.getContentPane().add(lblStatus);
		
		JLabel lblAction = new JLabel("PRIORITY");
		lblAction.setBounds(500, 130, 61, 16);
		frame.getContentPane().add(lblAction);
		
		String[] options = {"Priority", "Due Date", "Name"};
		
		JComboBox<?> sortBox = new JComboBox<Object>(options);
		sortBox.setSelectedIndex(0);
		sortBox.setBounds(480, 89, 126, 27);
		frame.getContentPane().add(sortBox);
		
		/*********************************************************************************/
		// THE TABLE
		/*********************************************************************************/
		
		String[] columnNames = {"Descrption", "Due Date", "Status", "Action"};
		table = new JTable(info, columnNames);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		table.setBounds(20, 158, 638, 251);
		frame.getContentPane().add(table);
		
	}
}

