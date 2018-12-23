package xyz.codem.randomdinner;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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
        dialog =  new AlertDialog.Builder(getActivity()).setView(v).
                setTitle(caller).
                setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            sendResult(Activity.RESULT_OK, text.getText().toString());
                    }
                }).create();
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(text.getText().toString() == null || "".equals(text.getText().toString())) {
                    //((AlertDialog) dialog).getButton(android.R.string.ok).setEnabled(false);
                }else {
                    //((AlertDialog) dialog).getButton(android.R.string.ok).setEnabled(true);
                }
            }
        });
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
}
