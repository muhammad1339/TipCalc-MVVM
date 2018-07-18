package com.proprog.tipcalc.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.proprog.tipcalc.R;

public class SaveDialogFragment extends DialogFragment {
    interface DialogCallback {
        void onSave(String locName);
    }

    SaveDialogFragment.DialogCallback mCallback = null;
    static int viewID = View.generateViewId();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (DialogCallback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        EditText editText = new EditText(getContext());
        editText.setHint(R.string.edit_tip_name_hint);
        editText.setId(viewID);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(editText);
        builder.setPositiveButton(R.string.action_save,
                (DialogInterface dialog, int which) -> onSave(editText));
        builder.setNegativeButton(R.string.action_cancel,
                (DialogInterface dialog, int which) -> dismiss());
        Dialog dialog = builder.create();
        return dialog;
    }

    private void onSave(EditText editText) {
        String locName = editText.getText().toString();
        if (!TextUtils.isEmpty(locName)) {
            mCallback.onSave(locName);
        }
    }

}
