import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditNote extends AddNote {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
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
		frame.setBounds(100, 100, 413, 283);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTypeThe = new JLabel("Type the name of the note you want to update");
		lblTypeThe.setBounds(6, 25, 301, 16);
		frame.getContentPane().add(lblTypeThe);
		
		textField = new JTextField();
		textField.setBounds(6, 41, 400, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(6, 93, 400, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		lblNewDescription = new JLabel("New Description");
		lblNewDescription.setBounds(6, 77, 118, 16);
		frame.getContentPane().add(lblNewDescription);
		
		JComboBox<?> monthsBox = new JComboBox<Object>(MONTHS);
		monthsBox.setBounds(117, 131, 84, 27);
		frame.getContentPane().add(monthsBox);
		
		JComboBox<?> daysBox = new JComboBox<Object>(DAYS);
		daysBox.setBounds(213, 131, 76, 27);
		frame.getContentPane().add(daysBox);
		
		JComboBox<?> yearBox = new JComboBox<Object>(YEARS);
		yearBox.setBounds(301, 131, 95, 27);
		frame.getContentPane().add(yearBox);
		
//
		
		JLabel lblNewDueDate = new JLabel("New Due Date");
		lblNewDueDate.setBounds(6, 135, 99, 16);
		frame.getContentPane().add(lblNewDueDate);
		
		JLabel lblNewPriority = new JLabel("New Priority");
		lblNewPriority.setBounds(6, 184, 84, 16);
		frame.getContentPane().add(lblNewPriority);
		
		textField_2 = new JTextField();
		textField_2.setBounds(102, 179, 76, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnUpdateNote = new JButton("Update Note");
		btnUpdateNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Add update note functionality
			}
		});
		btnUpdateNote.setBounds(146, 226, 117, 29);
		frame.getContentPane().add(btnUpdateNote);
	}
}
