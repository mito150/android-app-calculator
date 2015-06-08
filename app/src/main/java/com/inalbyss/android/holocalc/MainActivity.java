/*
    hCalc Copyright (C) 2015 Inalbyss Technologies
    Mateu S. <mito150@gmail.com>

    This file is part of hCalc.

    hCalc is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    hCalc is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with hCalc.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.inalbyss.android.holocalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // Constants for preferences
    private static final String PREFS_STATE = "current_state";
    private static final String EXPR_KEY = "expression";
    //private static final String TXTSIZE_KEY = "text_size";
    private static final String LASTOP_KEY = "lastop";
    private static final String UPDATE_LASTOP_KEY = "update_lastop";

    // The "screen" of the calc
    private TextView screen, txt_lastOp;
    private boolean update_lastOp = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (TextView) findViewById(R.id.textView);
        txt_lastOp = (TextView) findViewById(R.id.textView2);
    }


    @Override
    protected void onPause() {
        super.onPause();

        // Save the current state of the UI
        saveCurrentState();
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Read the saved state of the UI
        readCurrentState();
    }


    /**
     * Handler for button key presses
     *
     * @param view UI object
     */
    public void buttonPress(View view) {
        AnimateView animation = new AnimateView();
        String c = view.getTag().toString();

        // Add animation to view
        animation.scaleView(view);

        // Checks for the residual value of the "screen" calc
        CharSequence cs = screen.getText();

        if (cs != null) {
            // Clean the screen if it contains NaN
            if (cs.toString().contains("NaN")) screen.setText("");
                // Clean the screen if it equals 0 and we press other button than "point"
            else if (cs.toString().equals("0") && view.getId() != R.id.btn_dot) screen.setText("");
        }

        // Perform actions related to key presses
        switch (view.getId()) {
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_div:
                update_lastOp = true;
                screen.append(" " + c + " ");
                break;

            case R.id.btn_inv:
                EvalExpression invert = new EvalExpression();
                screen.setText(invert.invertSignNumber(screen.getText()));
                break;

            case R.id.btn_sqrt:
                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_equal:
                // Add expression to last calculated expression field
                if (update_lastOp) {
                    animation.alphaView(txt_lastOp);
                    txt_lastOp.setText(screen.getText());
                    update_lastOp = false;
                }
                evaluate();
                break;

            // Here we handle numbers, point, ... Views that all do the same and doesn't require specific actions.
            default:
                update_lastOp = true;
                screen.append(c);
                break;
        }
    }


    /**
     * Delete the last inserted single num or operator
     */
    private void deleteChar() {
        CharSequence exp;

        if ((exp = screen.getText()) != null) {
            // Do not delete if the screen text is nothing
            if (!exp.toString().isEmpty()) {
                EvalExpression str = new EvalExpression();
                screen.setText(str.deleteLast(exp.toString()));
            }
        }
    }


    /**
     * Produces the result of the expression
     */
    private void evaluate() {
        CharSequence exp;

        if ((exp = screen.getText()) != null) {
            String fRes;
            EvalExpression result = new EvalExpression();

            fRes = result.ParseBasicExpression(exp.toString());
            screen.setText(fRes);
        }
    }


    /**
     * Save preferences and current views states of the UI
     */
    private void saveCurrentState() {
        // Get preferences file and open editor
        SharedPreferences preferences = getSharedPreferences(PREFS_STATE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        CharSequence c;

        // Add Key/Values
        if ((c = screen.getText()) != null) {
            editor.putString(EXPR_KEY, c.toString());
            editor.putString(LASTOP_KEY, String.valueOf(txt_lastOp.getText()));
        }
        editor.putBoolean(UPDATE_LASTOP_KEY, update_lastOp);

        // Apply changes
        editor.apply();
    }


    /**
     * Read preferences and saved views states of the UI
     */
    private void readCurrentState() {
        // Get preferences file
        SharedPreferences preferences = getSharedPreferences(PREFS_STATE, MODE_PRIVATE);

        // Read and apply preferences
        screen.setText(preferences.getString(EXPR_KEY, ""));
        txt_lastOp.setText(preferences.getString(LASTOP_KEY, ""));
        update_lastOp = preferences.getBoolean(UPDATE_LASTOP_KEY, true);
    }


    /**
     * Change text size of the UI
     *
     * @param event key event
     * @return true
     */
    /* Commented code for problems with the starting text size on some devices is too big!
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // Volume up stuff
        if(event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP && txt_size < 48) {
            txt_size++;
            screen.setTextSize(TypedValue.COMPLEX_UNIT_SP, txt_size);
            // We handle the key press
            return true;
        }
        // Volume down stuff
        else if(event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN && txt_size > 12) {
            txt_size--;
            screen.setTextSize(TypedValue.COMPLEX_UNIT_SP, txt_size);
            // We handle the key press
            return true;
        }
        // If we don't handle key event spread to system
        // If we don't do that menu hardware button won't work
        else return super.dispatchKeyEvent(event);
    }*/


    /**
     * Menu inflater
     *
     * @param menu to show in the UI
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }


    /**
     * Menu click handler
     *
     * @param item menu pressed
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_back:
                deleteChar();
                break;
            case R.id.mnu_clr:
                screen.setText("");
                break;
            case R.id.mnu_about:
                launch_activity(AboutActivity.class);
                break;
        }

        return true;
    }


    /**
     * Launches given activity
     *
     * @param activity activity class
     */
    private void launch_activity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}