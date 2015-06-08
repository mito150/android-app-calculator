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

import android.app.Activity;
import android.os.Bundle;


public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
