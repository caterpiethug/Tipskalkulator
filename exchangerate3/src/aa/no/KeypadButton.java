package aa.no;

public enum KeypadButton {
	seven("7"),
	B("8"),
	C("9"),
	D("4"),
	E("5"),
	F("6"),
	G("1"),
	H("2"),
	I("3"),
	J("0"),
	K("C"),
	L("<-"),
	M("Tips");
	
	

	CharSequence mText; // Display Text
	
	KeypadButton(CharSequence text) {
		mText = text;
	}

	public CharSequence getText() {
		return mText;
	}
}
