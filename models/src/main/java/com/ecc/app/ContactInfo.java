import java.util.ArrayList;

public class ContactInfo {
	ArrayList<String> landline = new ArrayList<String>();
	ArrayList<String> mobile = new ArrayList<String>();
	ArrayList<String> email = new ArrayList<String>(); 

	public ArrayList<String> getLandline() {
		return this.landline;
	}

	public ArrayList<String> getMobile() {
		return this.mobile;
	}

	public ArrayList<String> getEmail() {
		return this.email;
	}

	public void addLandline(String landline) {
		this.landline.add(landline);
	}

	public void addMobile(String mobile) {
		this.mobile.add(mobile);
	}

	public void addEmail(String email) {
		this.email.add(email);
	}
}