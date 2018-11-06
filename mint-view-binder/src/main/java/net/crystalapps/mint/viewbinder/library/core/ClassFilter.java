package net.crystalapps.mint.viewbinder.library.core;

import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Syed Owais Ali on 8/6/2018.
 */
public interface ClassFilter {

    default Class<?> getActivityClass() {
        return AppCompatActivity.class;
    }
    default Class<?> getDialogClass() {
        return Dialog.class;
    }
    default Class<?> getViewClass() {
        return View.class;
    }
    default Class<?> getSupportFragmentClass() { return Fragment.class; }
    default Class<?> getFragmentClass() { return android.app.Fragment.class; }
    Class<?> getViewHolderClass();
}