
public class UserNotes {
	
	private String description; 
	private String dueDate; 
	private String priority;
	private enum STATUS { INPROGRESS, NOTSTARTED, FINISHED };
	
	public UserNotes() { 
		this.description = "";
		this.dueDate = "";
		this.priority = ""; 
	}
	
	
	public UserNotes(String description, String dueDate, String priority) {
		this.description = description;
		this.dueDate = dueDate;
		this.priority = priority;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return "UserNotes [description=" + description + ", dueDate=" + dueDate + ", priority=" + priority + "]";
	};
	
}
