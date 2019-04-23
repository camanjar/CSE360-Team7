import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
//new add
public class EditNote extends AddNote {

	private JFrame frame;
	private JTextField oldDesc;
	private JTextField newDesc;
	private String date;
	private JLabel lblNewDescription;
	private JTextField textField_2;
	
	protected Integer[] MONTHS = {1,2,3,4,5,6,7,8,9,10,11,12};
	protected Integer[] DAYS = { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
	protected Integer[] YEARS = { 
			2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033, 2034, 2035, 2036, 2037,
			2038, 2039, 2040, 2041, 2042, 2043, 2044, 2045, 2046, 2047, 2048, 2049, 2050, 2051, 2052, 2053, 2054, 2055, 2056, 
			2057, 2058, 2059, 2060, 2061, 2062, 2063, 2064, 2065, 2066, 2067, 2068, 2069, 2070, 2071, 2072, 2073, 2074, 2075, 
			2076, 2077, 2078, 2079, 2080, 2081, 2082, 2083, 2084, 2085, 2086, 2087, 2088, 2089, 2090, 2091, 2092, 2093, 2094, 
			2095, 2096, 2097, 2098, 2099, 2100, 2101, 2102, 2103, 2104, 2105, 2106, 2107, 2108, 2109, 2110, 2111, 2112, 2113, 
			2114, 2115, 2116, 2117, 2118, };

	/**
	 * Launch the application.
	 */
	public static void NewEditScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditNote window = new EditNote();
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
	public EditNote() {
		initializeEdit();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeEdit() {
		frame = new JFrame("Update Note");
		frame.setBounds(100, 100, 415, 289);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTypeThe = new JLabel("Type the description of the note you want to update");
		lblTypeThe.setBounds(6, 25, 351, 21);
		frame.getContentPane().add(lblTypeThe);
		
		oldDesc = new JTextField();
		oldDesc.setBounds(6, 41, 400, 26);
		frame.getContentPane().add(oldDesc);
		oldDesc.setColumns(10);
		
		newDesc = new JTextField();
		newDesc.setBounds(6, 93, 400, 26);
		frame.getContentPane().add(newDesc);
		newDesc.setColumns(10);
		
		lblNewDescription = new JLabel("New Description");
		lblNewDescription.setBounds(6, 77, 118, 16);
		frame.getContentPane().add(lblNewDescription);
		
		JComboBox<?> months = new JComboBox<Object>(MONTHS);
		months.setBounds(117, 131, 84, 27);
		frame.getContentPane().add(months);
		
		JComboBox<?> days = new JComboBox<Object>(DAYS);
		days.setBounds(213, 131, 76, 27);
		frame.getContentPane().add(days);
		
		JComboBox<?> year = new JComboBox<Object>(YEARS);
		year.setBounds(301, 131, 95, 27);
		frame.getContentPane().add(year);
		
//
		
		JLabel lblNewDueDate = new JLabel("New Due Date");
		lblNewDueDate.setBounds(6, 135, 99, 16);
		frame.getContentPane().add(lblNewDueDate);
		
		JLabel lblNewPriority = new JLabel("New Priority");
		lblNewPriority.setBounds(6, 184, 84, 16);
		frame.getContentPane().add(lblNewPriority);
		
		textField_2 = new JTextField();
		textField_2.setBounds(89, 179, 76, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnUpdateNote = new JButton("Update Note");
		btnUpdateNote.setBounds(146, 226, 117, 29);
		frame.getContentPane().add(btnUpdateNote);
		
		JLabel lblNewStatus = new JLabel("New Status");
		lblNewStatus.setBounds(177, 184, 76, 16);
		frame.getContentPane().add(lblNewStatus);
		
		String[] options = {"Not Started", "In Progress", "Finished"};
		
		JComboBox statusBox = new JComboBox(options);
		statusBox.setBounds(252, 180, 144, 27);
		frame.getContentPane().add(statusBox);
		
		
		btnUpdateNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isNumeric(textField_2.getText())) { 
					JOptionPane.showMessageDialog(frame,
						    "Priority should be an integer value\n Example: 1",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				} else 
				if (checkMatch(ToDoListUnlimited.info, oldDesc.getText())) {
					date = months.getSelectedItem().toString() + "/" + 
							days.getSelectedItem().toString() + "/" + 
							year.getSelectedItem().toString();
					
					UserNotes newNote = new UserNotes(newDesc.getText(), date,
							textField_2.getText(), statusBox.getSelectedItem().toString());
					//////// EDIT ///////
					Object[] insert = {newNote.getDescription(), newNote.getDueDate(), 
							newNote.getUserStatus(), newNote.getPriority()};
					
					//replace(ToDoListUnlimited.info, oldDesc.getText(), insert);
					ToDoListUnlimited.info[findIndexOf(ToDoListUnlimited.info, oldDesc.getText())] = insert;
					try {
                        ToDoListUnlimited.serializeList(ToDoListUnlimited.info);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
					ToDoListUnlimited.sortByFeature();
					ToDoListUnlimited.frame.repaint();
					frame.dispose();
				} else { 
					
				JOptionPane.showMessageDialog(frame,
					    "No note found by that name, please try again",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
	
	public static boolean checkMatch(Object[][] ary, String s) {
		int i = 0;
		while (ary[i][0] != null) {
			if (ary[i][0].toString().equals(s)) {
				return true;
			} else {
				i++;
			}
		}
		return false;
	}
	
	public int findIndexOf(Object[][] ary, String s) { 
		int i = 0;
		while (ary[i][0] != null) {
			if (ary[i][0].toString().equals(s)) {
				return i;
			}
			i++;
		}
		return 0;
	}
}
