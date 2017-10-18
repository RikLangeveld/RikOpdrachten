package com.school.langevr004.kungsleden.DatabaseTest.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.school.langevr004.kungsleden.R;

public class ConfirmDeleteDialog extends DialogFragment {

    ConfirmDeleteDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ConfirmDeleteDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ConfirmDeleteDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = getArguments().getString("message");
        String positiveButton = getArguments().getString("positiveButton");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(ConfirmDeleteDialog.this);
                    }
                })
                .setNegativeButton(R.string.dialog_travel_note_deletion_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(ConfirmDeleteDialog.this);
                    }
                });
        return builder.create();
    }

    public interface ConfirmDeleteDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);

        void onDialogNegativeClick(DialogFragment dialog);
    }
}

