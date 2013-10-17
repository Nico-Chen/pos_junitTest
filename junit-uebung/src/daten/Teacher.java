package daten;

public class Teacher extends Person {

	public Teacher(String firstName, String lastName, String shortSign) {
		super(firstName, lastName);
		setShortSign(shortSign);
	}

	private String shortSign;

	public void setShortSign(String shortSign) {
		this.shortSign = shortSign;
	}

	@Override
	public String toString() {
		return "Teacher {firstName= " + super.getFirstName() + ", lastName= "
				+ super.getLastName() + ", shortSigh= " + shortSign + "}";
	}
}
