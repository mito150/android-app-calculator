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

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;


public class AnimateView {

    public void scaleView(View view) {
        ScaleAnimation animation = new
                ScaleAnimation(1.08f, 0.95f, 1.08f, 0.95f, view.getWidth() / 2, view.getHeight() / 2);
        animation.setDuration(150);

        view.startAnimation(animation);
    }

    public void alphaView(View view) {
        AlphaAnimation toAlpha = new AlphaAnimation(0.0f, 1.0f);
        toAlpha.setDuration(500);

        view.startAnimation(toAlpha);
    }
}