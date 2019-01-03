package xyz.codem.randomdinner;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NamePickerFragment extends DialogFragment {
    EditText text;
    Dialog dialog;
    String caller;

    public static NamePickerFragment newInstance(String caller) {
        NamePickerFragment fragment = new NamePickerFragment();
        fragment.caller = caller;
        return fragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dailog, null);
        text = v.findViewById(R.id.editText);
        dialog =  new AlertDialog.Builder(getActivity(), R.style.popkey).setView(v).
                setTitle(caller).
                setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            sendResult(Activity.RESULT_OK, text.getText().toString());
                    }
                }).create();

        return dialog;
    }

    private void sendResult(int resultCode, String string) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("name", string);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button button = ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE);
        button.setEnabled(false);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(text.getText().toString() == null || "".equals(text.getText().toString())) {
                    ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }else {
                    ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
            }
        });
    }



}
