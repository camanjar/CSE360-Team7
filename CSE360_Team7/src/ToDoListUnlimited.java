import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JFormattedTextField;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.DropMode;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.RowSorter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
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
	private static int sortingPreference = 0;
	
	//ICONS
	ImageIcon addIcon = new ImageIcon("add.png");
	ImageIcon editIcon = new ImageIcon("edit.png");
	ImageIcon deleteIcon = new ImageIcon("delete.png");
	ImageIcon printIcon = new ImageIcon("print.png");
	
	public static void sortByFeature() {
		if(sortingPreference == 0 ) // Priority
			sortByPriority();
		else
			sortByDescription();
	}
		
	public static void setSortingPreference(int preference) {
		sortingPreference = preference;
		sortByFeature();
		frame.repaint();
	}
	
	public static void sortByPriority() {
		if(index > 0) // only sorts if atleast 2 item is present
		{
				  int n = info.length;
				        for (int row = 0; row < index; row++) {
				        	 for (int nextRow = 0; nextRow < index - row - 1; nextRow++) {
				            Integer priority = Integer.parseInt(info[nextRow][3].toString());
				            Integer priority2 = Integer.parseInt(info[nextRow + 1][3].toString());
				                if (priority > priority2)
				                { 
				                    // swap the two rows
				            		swapRows(nextRow);
				                } 
				        }
				        }
		}
		
	}
	
	public static void sortByDescription() {
		
		if(index > 0) // only sorts if atleast 2 item is present
		{
				  int n = info.length;
				        for (int row = 0; row < index; row++) {
				        	 for (int nextRow = 0; nextRow < index - row - 1 ; nextRow++) {
				        		
				        		String description1 = info[nextRow][0].toString();
				        		String description2 = info[nextRow + 1][0].toString();
				        		 
				                if (description1.compareTo(description2) > 0)
				                { 
				                    // swap the two rows
				                	swapRows(nextRow);
				            	
				                } 
				    
				        }
				        }
		}
		
	}
	
	private static void swapRows(int index)
	{
		UserNotes tempNote = new UserNotes(
				info[index][0].toString(),
				info[index][1].toString(),
				info[index][2].toString(),
				info[index][3].toString());
		
//
        info[index] = info[index+1]; 
        Object[] insert = {tempNote.getDescription(), tempNote.getDueDate(), 
				tempNote.getStatus(), tempNote.getPriority()};
        info[index+1] = insert;
		
	}
	
	

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
		
		frame = new JFrame("To-Do List Unlimited 2019");
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
				EditNote editNote = new EditNote();
				editNote.NewEditScreen();
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
		
		JButton btnDeleteList = new JButton(deleteIcon); 
		btnDeleteList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Needs functionality
			}
		});
		
		btnDeleteList.setBounds(100, 88, 29, 29);
		frame.getContentPane().add(btnDeleteList);
		
		JButton btnSaveList = new JButton("Save"); 
		btnSaveList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Needs functionality
			}
		});
		
		btnSaveList.setBounds(135, 90, 80, 27);
		frame.getContentPane().add(btnSaveList);
		
		JButton btnResetList = new JButton("Start Over"); 
		btnResetList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Needs functionality
			}
		});
		
		btnResetList.setBounds(210, 90, 95, 27);
		frame.getContentPane().add(btnResetList);
		
		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setBounds(20, 130, 100, 16);
		frame.getContentPane().add(lblDescription);
		
		JLabel lblDueDate = new JLabel("DUE DATE");
		lblDueDate.setBounds(180, 130, 84, 16);
		frame.getContentPane().add(lblDueDate);
		
		JLabel lblStatus = new JLabel("STATUS");
		lblStatus.setBounds(340, 130, 61, 16);
		frame.getContentPane().add(lblStatus);
		

		JLabel lblDateStartedFinished = new JLabel("DATE STARTED / FINISHED");
		lblStatus.setBounds(340, 130, 61, 16);
		frame.getContentPane().add(lblDateStartedFinished);
		
		
		JLabel lblAction = new JLabel("PRIORITY");
		lblAction.setBounds(500, 130, 61, 16);
		frame.getContentPane().add(lblAction);
		
		String[] options = {"Priority", "Description"};
		
		JComboBox<?> sortBox = new JComboBox<Object>(options);
		sortBox.setSelectedIndex(0);
		sortBox.setBounds(480, 89, 126, 27);
		frame.getContentPane().add(sortBox);
		// On change of sort preference, do a new sort on the view
		sortBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSortingPreference(sortBox.getSelectedIndex());
		
			}
		});
		
		/*********************************************************************************/
		// THE TABLE
		/*********************************************************************************/
		
		String[] columnNames = {"Description", "Due Date", "Status", "Priority"};
//		DefaultTableModel tableModel = new DefaultTableModel(info, columnNames);
//		
//		JTable table = new JTable(tableModel);
//		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
//		table.setRowSorter(sorter);
//		
//		List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
//		sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
//		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//		sorter.setSortKeys(sortKeys);
//		
//		table.setModel(tableModel);

		table = new JTable(info, columnNames);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		table.setBounds(20, 158, 638, 251);
		frame.getContentPane().add(table);
		
	}
}

