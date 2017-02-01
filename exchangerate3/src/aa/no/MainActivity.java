package aa.no;

import android.app.Activity;
import android.widget.AdapterView;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.GridView;
import android.view.View;
import android.view.View.OnClickListener;



public class MainActivity extends Activity 
implements AdapterView.OnItemSelectedListener {
/* This part set up a LinearLayout with a TextView and a GridView.
 * Code is used to fill the GridView with buttons.
 */
	// Land, tjenester og kvalitet på tjenestene fra spinner.
	private static final String[] items={"Norge","Frankrike", "Sverige"};
	private static final String[] services={"Matservering", "Taxi", "Bagasjehjelp"};
	private static final String[] quality={"Normal", "Meget bra", "Eksepsjonell"};


	GridView mKeypadGrid;
	TextView userInputText;
	String numbers = "0";

	
	// THIS HAS TO DO WITH HANDLING SPINNER EVENTS
    Spinner spinner;
    Spinner spinner2;
    Spinner spinner3;
	int p = 0;

	KeypadAdapter mKeypadAdapter;

	public void onItemSelected(AdapterView<?> parent,
			View v, int position, long id){p = position;}
	
	public void onNothingSelected(AdapterView<?> parent){p=0;}

	public double tipCalculator(double value, String items, String services, String quality)
	{

        //Setter verdi på alle land, tjenester og kvalitet på tjenestene.
		int countryTip = 0;
		int servicesTip = 0;
		int qualityTip = 0;
		int tip;


		if(items.equals("Norge"))
		{
			countryTip = 5;
		}
		else if(items.equals("Frankrike")) {
            countryTip = 7;
        }

        else if (items.equals("Sverige"))
        {
            countryTip = 4;
        }

        if(services.equals("Matservering"))
		{
			servicesTip = 10;
		}
		else if(services.equals("Taxi"))
        {
            servicesTip = 12;
        }

        else if (services.equals("Bagasjehjelp"))
        {
            servicesTip = 5;
        }

		if(quality.equals("Normal"))
		{
			qualityTip = 0;
		}
		else if(quality.equals("Meget bra"))
        {
            qualityTip = 4;
        }
        else if (quality.equals("Eksepsjonell"))
        {
            qualityTip = 6;
        }
		// gir tip verdi basert på land + tjeneste + kvalitet
        tip = countryTip + servicesTip + qualityTip;
		return tip;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);



		spinner2 = (Spinner)findViewById(R.id.spinner2);
		spinner2.setOnItemSelectedListener(this);
		ArrayAdapter<String> aa2=new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,services);
		aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(aa2);

		spinner3 = (Spinner)findViewById(R.id.spinner3);
		spinner3.setOnItemSelectedListener(this);
		ArrayAdapter<String> aa3=new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,quality);
		aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner3.setAdapter(aa3);

		spinner = (Spinner)findViewById(R.id.spinner);
		spinner.setOnItemSelectedListener(this);
		ArrayAdapter<String> aa=new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,items);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(aa);

		// End of code to fill spinner
		// Get reference to the GridView and TextView
		mKeypadGrid = (GridView) findViewById(R.id.grdButtons);
		userInputText = (TextView) findViewById(R.id.txtInput);
		// Create Keypad Adapter
		mKeypadAdapter = new KeypadAdapter(this);
		// Set adapter of the keypad grid
		mKeypadGrid.setAdapter(mKeypadAdapter);
		// Set button click listener of the keypad adapter
		// Process button clicks
		// Only "veksle" needs special treatment
		mKeypadAdapter.setOnButtonClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button btn = (Button) v;
				// Get the KeypadButton value which is used to identify the
				// keypad button from the Button's tag
				KeypadButton keypadButton = (KeypadButton) btn.getTag();
				// Process keypad button
				if (keypadButton.getText().toString() == "<-") {
					numbers = numbers.substring(0,numbers.length()-1);
					if (numbers.length()==0) {numbers = "0";};
					userInputText.setText(numbers);
					
				}
				else
				if (keypadButton.getText().toString() == "C"){ 
				numbers = "0"; 
				userInputText.setText(numbers);}
				else
			     if (keypadButton.getText().toString() != "Tips"){
					//userInputText.append(keypadButton.getText().toString());
					if (numbers != "0"){
					numbers = numbers + keypadButton.getText().toString();
					} else {
						numbers = keypadButton.getText().toString();
					}
				    userInputText.setText(numbers);} 
				else {

                     String selectedCountry = spinner.getSelectedItem().toString();
                     String selectedService = spinner2.getSelectedItem().toString();
                     String selectedQuality = spinner3.getSelectedItem().toString();
                     double enteredAmount = Double.parseDouble(numbers);
                     double output = tipCalculator(enteredAmount, selectedCountry, selectedService, selectedQuality);
                     userInputText.setText("Du burde gi: " + String.format("%.2f",output));
				    }}
		}
		);
	}

}

