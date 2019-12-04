package com.fadybassem.bittaskjava.ui.dialogues;

import android.content.DialogInterface;

public interface DialogClickInterface {

    public void onClickPositiveButton(DialogInterface pDialog, int pDialogIntefier);

    public void onClickNegativeButton(DialogInterface pDialog, int pDialogIntefier);
}