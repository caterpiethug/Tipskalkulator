package aa.no;

		import android.widget.*;
		import android.content.*;
		import android.view.*;
		import android.view.View.OnClickListener;

public class KeypadAdapter extends BaseAdapter {
	// Need to implement getCount, getItem, getItemId
	private Context mContext;

	// Declare button click listener variable
	private OnClickListener mOnButtonClick;

	public KeypadAdapter(Context c) {
		mContext = c;
	}

	// Method to set button click listener variable
	public void setOnButtonClickListener(OnClickListener listener) {
		mOnButtonClick = listener;
	}

	public int getCount() {
		return mButtons.length;
	}

	public Object getItem(int position) {
		return mButtons[position];
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ButtonView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		Button btn = new Button(mContext);
		KeypadButton keypadButton = mButtons[position];
		// Set OnClickListener of the button to mOnButtonClick
		btn.setOnClickListener(mOnButtonClick);
		// Set CalculatorButton enumeration as tag of the button so that we
		// will use this information from our main view to identify what to do
		btn.setTag(keypadButton);
		btn.setText(mButtons[position].getText());
		return btn;
	}

	// Create and populate keypad buttons array with CalculatorButton enum values
	private KeypadButton[] mButtons = { KeypadButton.seven,KeypadButton.B, KeypadButton.C, KeypadButton.D,
			KeypadButton.E, KeypadButton.F,KeypadButton.G, KeypadButton.H, KeypadButton.I,
			KeypadButton.J, KeypadButton.K, KeypadButton.L, KeypadButton.M};
}
