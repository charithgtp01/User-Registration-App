package info.charith.userregistrationapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Utils {

    public static EditText initiateInputTextWithIcon(View view, String hintString, int iconRes) {
        EditText editText = view.findViewById(R.id.content);
        ImageView icon = view.findViewById(R.id.icon);
        TextView hint = view.findViewById(R.id.hint);
        icon.setImageResource(iconRes);
        hint.setText(hintString);
        return editText;
    }

    public static void initiateTextViewWithIcon(View view, String hintString, String contentString, int iconRes, View.OnClickListener listener) {
        TextView textView = view.findViewById(R.id.content);
        ImageView icon = view.findViewById(R.id.icon);
        TextView hint = view.findViewById(R.id.hint);
        icon.setImageResource(iconRes);
        hint.setText(hintString);
        textView.setText(contentString);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
            }
        });
    }

    public static void showDialog(Context context, String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}
