import java.awt.EventQueue;
import java.lang.Object;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddNote {

	protected JFrame frame;
	protected JTextField descriptionField;
	protected String description;
	protected String duedate;
	protected String dateStarted;
	protected String priority;
	
	protected Integer[] MONTHS = {1,2,3,4,5,6,7,8,9,10,11,12};
	protected Integer[] DAYS = { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
	protected Integer[] YEARS = { 
			2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033, 2034, 2035, 2036, 2037,
			2038, 2039, 2040, 2041, 2042, 2043, 2044, 2045, 2046, 2047, 2048, 2049, 2050, 2051, 2052, 2053, 2054, 2055, 2056, 
			2057, 2058, 2059, 2060, 2061, 2062, 2063, 2064, 2065, 2066, 2067, 2068, 2069, 2070, 2071, 2072, 2073, 2074, 2075, 
			2076, 2077, 2078, 2079, 2080, 2081, 2082, 2083, 2084, 2085, 2086, 2087, 2088, 2089, 2090, 2091, 2092, 2093, 2094, 
			2095, 2096, 2097, 2098, 2099, 2100, 2101, 2102, 2103, 2104, 2105, 2106, 2107, 2108, 2109, 2110, 2111, 2112, 2113, 
			2114, 2115, 2116, 2117, 2118, };
	
	protected JTextField priorityField;

	/**
	 * Launch the application.
	 */
	public static void NewAddScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNote window = new AddNote();
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
	public AddNote() {
		initializeAdd();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeAdd() {
		frame = new JFrame("Add a new note");
		frame.setBounds(100, 100, 452, 305);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		descriptionField = new JTextField();
		descriptionField.setBounds(6, 62, 438, 34);
		frame.getContentPane().add(descriptionField);
		descriptionField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Description");
		lblNewLabel.setBounds(6, 44, 106, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblDueDate = new JLabel("Due Date");
		lblDueDate.setBounds(6, 108, 61, 16);
		frame.getContentPane().add(lblDueDate);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(6, 155, 61, 16);
		frame.getContentPane().add(lblStatus);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setBounds(6, 210, 61, 16);
		frame.getContentPane().add(lblPriority);
		
		String[] options = {"Not Started", "In Progress", "Finished"};
		
		JComboBox<?> comboBox = new JComboBox<Object>(options);
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(55, 151, 126, 27);
		frame.getContentPane().add(comboBox);
		
		/////// CALENDAR ///////
		
		JComboBox<?> monthsBox = new JComboBox<Object>(MONTHS);
		monthsBox.setBounds(79, 104, 84, 27);
		frame.getContentPane().add(monthsBox);
		
		JComboBox<?> daysBox = new JComboBox<Object>(DAYS);
		daysBox.setBounds(175, 104, 76, 27);
		frame.getContentPane().add(daysBox);
		
		JComboBox<?> yearBox = new JComboBox<Object>(YEARS);
		yearBox.setBounds(263, 104, 95, 27);
		frame.getContentPane().add(yearBox);
		
		priorityField = new JTextField();
		priorityField.setColumns(10);
		priorityField.setBounds(65, 201, 69, 34);
		frame.getContentPane().add(priorityField);
		
		JLabel lblAddANew = new JLabel("Add a new note");
		lblAddANew.setFont(new Font("Comic Sans MS", Font.PLAIN, 29));
		lblAddANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddANew.setBounds(95, 6, 263, 40);
		frame.getContentPane().add(lblAddANew);
		
		/************************************************************************/
		// The following does all the stuff to properly add all info
		/************************************************************************/
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				description = descriptionField.getText();
				duedate = monthsBox.getSelectedItem().toString() + "/" + 
						daysBox.getSelectedItem().toString() + "/" + 
						yearBox.getSelectedItem().toString();
				priority = priorityField.getText();
				
				/////////DATE CREATED//////

				DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime rightNow = LocalDateTime.now(); 
				dateStarted = date.format(rightNow);
				System.out.println(dateStarted);
				
				if(description.equals("") || priority.equals("")) { 
					JOptionPane.showMessageDialog(frame,
						    "Please fill out the entire form before continuing",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
					
				//TODO: Check uniqueness of the note!
				//} 
//				else if (isUnique(ToDoListUnlimited.info, descriptionField.getText())) { 
//					JOptionPane.showMessageDialog(frame,
//						    "Description is not unique,",
//						    "Error",
//						    JOptionPane.ERROR_MESSAGE);
				} else { 
					
					if(!isNumeric(priorityField.getText())) { 
						JOptionPane.showMessageDialog(frame,
							    "Priority should be an integer value\n Example: 1",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						
					} else {
						String noteStatus = comboBox.getSelectedItem().toString();
						
						UserNotes newNote = new UserNotes(description, duedate, priority, noteStatus);
						
						////////////// STATUS ////////////
						TableColumn status = ToDoListUnlimited.table.getColumnModel().getColumn(2);
						status.setCellEditor(new DefaultCellEditor(comboBox));
						
						DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
				        renderer.setToolTipText("Click for combo box");
				        status.setCellRenderer(renderer);
				        ///////////// STATUS ////////////////
				        
						Object[] insert = {newNote.getDescription(), newNote.getDueDate(), 
								newNote.getStatus(), newNote.getPriority()};
						ToDoListUnlimited.info[ToDoListUnlimited.index] = insert;
						ToDoListUnlimited.index++;
						ToDoListUnlimited.sortByFeature();
						ToDoListUnlimited.frame.repaint();
				
						try {
							ToDoListUnlimited.serializeList(ToDoListUnlimited.info);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						frame.dispose();
					}
				}
			}
		});
		
		btnEnter.setBounds(159, 245, 117, 29);
		frame.getContentPane().add(btnEnter);
	}
	
	private static boolean isNumeric(String str) {
		  return str.matches("-?\\d+(\\.\\d+)?");  
		}
	
	//TODO:  Attempted code to check uniqueness of description
//	private static boolean isUnique(Object[][] obj, String s) { 
//		UserNotes dummy = new UserNotes(obj[][]);
//			for (int i = 0; i < obj.length; i++) {
//				for (int j = 0; j < obj[i].length; j++) {
//					if (obj[i].equals(s)) { 
//						return true; 
//					}
//				}
//			}
//		return false;
//	}
}
	

