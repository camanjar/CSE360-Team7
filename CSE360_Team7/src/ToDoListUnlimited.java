import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
//New Addition
public class ToDoListUnlimited { 

	
	private static File chosenFile;
	public static JFrame frame;
	public static int index;
	public static JTable table;
	public JButton btnAddItem;
	public static Object[][] info = new Object[50][4];
	private static int iteration = 3;
	private static int sortingPreference = 0;
	
	//ICONS
	//ImageIcon addIcon = new ImageIcon(getClass().getResource("icons/add.png"));
	ImageIcon editIcon = new ImageIcon("icons/edit.png");
	ImageIcon deleteIcon = new ImageIcon("icons/delete.png");
	ImageIcon printIcon = new ImageIcon("icons/print.png");

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
	 * @throws Exception 
	 */
	public ToDoListUnlimited() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		
		System.out.println("Starting application...");
		File dir = new File("data");
        dir.mkdir();
		info = deserializeList();
		index = calcIndex(info);
		
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
		
		btnAddItem = new JButton("+");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNote newNote = new AddNote();
				newNote.NewAddScreen();
			}
		});
		
		btnAddItem.setBounds(20, 88, 29, 29);
		frame.getContentPane().add(btnAddItem);
		
		
		JButton btnEditItem = new JButton("E");
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
		
		JButton btnPrint = new JButton("P");
		btnPrint.setBounds(625, 88, 32, 32);
		frame.getContentPane().add(btnPrint);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printList(info);
			} 
		});
	
		
		JButton btnDeleteList = new JButton("D"); 
		btnDeleteList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 0;
				int row = table.getSelectedRow();
				if( row >= 0 && ToDoListUnlimited.index >= 1 && row != index) { // only execute the below code if a row is selected, and atleast 1 note exists 
				String value = table.getModel().getValueAt(row, column).toString(); // returns the description of the todo
				if(!value.equals("")) {
					info[row] = info[index - 1]; 
					info[index - 1] = info[index]; 
					index = --index;
					for(int i = row; i < index; i++) {
						info[i][3] = (Integer.parseInt((String) info[row][3]) - 1) + "";
					}
					try {
						ToDoListUnlimited.serializeList(ToDoListUnlimited.info);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					setSortingPreference(ToDoListUnlimited.sortingPreference); // do a re-sort after deletion
				}
				}
			}
			
		});
		
		btnDeleteList.setBounds(100, 88, 29, 29);
		frame.getContentPane().add(btnDeleteList);
		
		JButton btnSaveList = new JButton("Save"); 
		btnSaveList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    ToDoListUnlimited.serializeList(ToDoListUnlimited.info);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
			}
		});
		
		btnSaveList.setBounds(135, 90, 80, 27);
		frame.getContentPane().add(btnSaveList);
		
		JButton btnResetList = new JButton("Start Over"); 
		btnResetList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAry(info); 
				// Save after clearing
				try {
					ToDoListUnlimited.serializeList(ToDoListUnlimited.info);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frame.repaint();
			}
		});
		
		btnResetList.setBounds(210, 90, 95, 27);
		frame.getContentPane().add(btnResetList);
		
//		JLabel lblDescription = new JLabel("DESCRIPTION");
//		lblDescription.setBounds(20, 130, 100, 16);
//		frame.getContentPane().add(lblDescription);
//		
//		JLabel lblDueDate = new JLabel("DUE DATE");
//		lblDueDate.setBounds(180, 130, 84, 16);
//		frame.getContentPane().add(lblDueDate);
//		
//		JLabel lblStatus = new JLabel("STATUS");
//		lblStatus.setBounds(340, 130, 61, 16);
//		frame.getContentPane().add(lblStatus);
//		
//
//		JLabel lblDateStartedFinished = new JLabel("DATE STARTED / FINISHED");
//		lblStatus.setBounds(340, 130, 61, 16);
//		frame.getContentPane().add(lblDateStartedFinished);
		
//		JLabel lblAction = new JLabel("PRIORITY");
//		lblAction.setBounds(500, 130, 61, 16);
//		frame.getContentPane().add(lblAction);
		
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
		table = new JTable(info, columnNames);
		table.setDefaultEditor(Object.class, null); // disable ability to edit row when double clicking
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		table.setBounds(20, 130, 638, 280);
		
		JScrollPane js = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//NEW
		js.setBounds(20, 130, 638, 280);
		js.setVisible(true);
		frame.getContentPane().add(js);
		
	}
	
	/*********************************************************************************/
	// SORTING HELPER FUNCTIONS TABLE
	/*********************************************************************************/
	
	/**
	 * General function to set sorting preference and automatically trigger using the new sort. 
	 * Sorting preference of 0 is sort by priority.
	 * Sorting preference of 1 is sort by description.
	 */
	public static void setSortingPreference(int preference) {
		sortingPreference = preference;
		sortByFeature();
		frame.repaint();
	}
	
	/**
	 * General function to sort, relies of the user specified sorting preference. 
	 * 
	 *
	 */
	public static void sortByFeature() {
		if(sortingPreference == 0 ) // Priority
			sortByPriority();
		else
			sortByDescription();
	}
		
	/**
	 * Helper function to sort by priority
	 */

	public static void sortByPriority() {
		if(index > 0) // only sorts if at least 2 item is present
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
	
	/**
	 * Helper function to sort by description
	 */
	public static void sortByDescription() {
		
		if(index > 0) // only sorts if at least 2 item is present
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
	
	/**
	 * Helper function for bubble sort. Swaps the row at the index provided
	 * and the subsequent row.
	 */
	public static void swapRows(int index)
	{
		UserNotes tempNote = new UserNotes(
				info[index][0].toString(),
				info[index][1].toString(),
				info[index][3].toString(),
				info[index][2].toString());
		
//
        info[index] = info[index+1]; 
        Object[] insert = {tempNote.getDescription(), tempNote.getDueDate(), 
				tempNote.getUserStatus(), tempNote.getPriority()};
        info[index+1] = insert;
		
	}
	/*********************************************************************************/
	// Serialization
	/*********************************************************************************/
	
	
	/*
	 * This Function Will serialize the 2d array in to seperate files depending on how many colums are in the array
	 * Simply input the 2d array with all the userData and it will serialze that data into different files in the respository
	 */
	public static void serializeList(Object[][] list) throws Exception {
		
		ArrayList<Object> dList = new ArrayList<>();
		ArrayList<Object> dateList = new ArrayList<>();
		ArrayList<Object> pList = new ArrayList<>();
		ArrayList<Object> sList = new ArrayList<>();
		
		for (int i = 0; i < 50 ;i++) {
			dList.add(list[i][0]);
		}
		
		for (int i = 0; i < 50 ;i++) {
			pList.add(list[i][3]);
		}
		
		for (int i = 0; i < 50 ;i++) {
			dateList.add(list[i][1]);
		}
		
		for (int i = 0; i < 50 ;i++) {
			sList.add(list[i][2]);
		}
		
		try {
			FileOutputStream fos = new FileOutputStream("data\\Descriptions");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dList);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try {
			FileOutputStream fos = new FileOutputStream("data\\Dates");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dateList);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try {
			FileOutputStream fos = new FileOutputStream("data\\Statuses");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(sList);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try {
			FileOutputStream fos = new FileOutputStream("data\\Priorities");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(pList);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	
	
	/*
	 * This will deserialize the TaskList File and return an Arraylist that was previously used or saved
	 * @return ArrayList
	 */
	public static Object[][] deserializeList() throws Exception{
		
		//Creates all the array lists for each Colum of the 2d array
		ArrayList<Object> dList = new ArrayList<>();
		ArrayList<Object> dateList = new ArrayList<>();
		ArrayList<Object> pList = new ArrayList<>();
		ArrayList<Object> sList = new ArrayList<>();
		Object[][] info = new Object[50][4];
		
//		info[0][0] = "Example Desc";
//		info[0][1] = "01/01/2019";
//		info[0][2] = "Done";
//		info[0][3] = "1";
		
		
		//This Deserializes the Descriptions
		try
        {
            FileInputStream fis = new FileInputStream("data\\Descriptions");
            ObjectInputStream ois = new ObjectInputStream(fis); 
            dList = (ArrayList) ois.readObject(); 
            ois.close();
            fis.close();     
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return info;
        }
		
		
		//This Deserializes the Due Dates
		try
        {
            FileInputStream fis = new FileInputStream("data\\Dates");
            ObjectInputStream ois = new ObjectInputStream(fis);
            dateList = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return info;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return info;
        }
		
		
		//This Deserializes the Status
		try
        {
            FileInputStream fis = new FileInputStream("data\\Statuses");
            ObjectInputStream ois = new ObjectInputStream(fis);
            sList = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return info;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return info;
        }
		
		
		//This Deserializes the priorities
		try
        {
            FileInputStream fis = new FileInputStream("data\\Priorities");
            ObjectInputStream ois = new ObjectInputStream(fis);
            pList = (ArrayList) ois.readObject();
            ois.close();
            fis.close(); 
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return info;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return info;
        }
		
		//This does the descriptions
		for (int i = 0; i < 50 ;i++) {
			info[i][0] = dList.get(i);
		}
		
		//This does the Priority
		for (int i = 0; i < 50 ;i++) {
			info[i][3] = pList.get(i);
		}
		
		//This does the due Dates
		for (int i = 0; i < 50 ;i++) {
			info[i][1] = dateList.get(i);
		}
		
		//This does the Status
		for (int i = 0; i < 50 ;i++) {
			info[i][2] = sList.get(i);
		}
		return info;
	}
	
	public int calcIndex(Object[][] ary) {
		int index = 0;
		int i = 0;
		while (ary[i][0] != null) {
			index++;
			i++;
		}
		return index;
	}
	
	public void clearAry(Object[][] ary) {
		
		for (int i = 0; i < ary.length; i++) {
			for (int j = 0; j < 4; j++) {
				ary[i][j] = null;
			}
		}
		this.index = 0;
		
	}
	
	public static void printList(Object[][] ary) {
		File fileToSave = null;
		PrintWriter out = null;
		try {
		JFrame choseFileFrame = new JFrame();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(choseFileFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    fileToSave = fileChooser.getSelectedFile();
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		}
		
			out = new PrintWriter(new FileWriter(fileToSave));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		out.println("========================================================================");
		out.println("                        TO DO LIST UNLIMITED                            "); 
		out.println("========================================================================");
		out.println("");
		
		int i = 0;
		while (ary[i][0] != null) {
			out.println(ary[i][0] + " [Due Date=" + ary[i][1] + ", Status=" + ary[i][2] + ", Priority=" + ary[i][3] + "]");
			out.println("");
			i++;
		}
		out.close();
	}
	
	
}

