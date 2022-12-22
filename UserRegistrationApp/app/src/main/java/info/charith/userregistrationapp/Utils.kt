package info.charith.userregistrationapp

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

object Utils {

    /**
     * Common method to Initiate Input Field
     * @param view Parent view element of the activity
     * @param hintString EditText Hint value
     * @param iconRes Icon Resource value
     * @return Initiated EditText
     */
    fun initiateInputTextWithIcon(view: View, hintString: String?, iconRes: Int): EditText {
        val editText: EditText = view.findViewById(R.id.content)
        val icon: ImageView = view.findViewById(R.id.icon)
        val hint: TextView = view.findViewById(R.id.hint)
        icon.setImageResource(iconRes)
        hint.text = hintString
        return editText
    }

    /**
     * Common method to Initiate Text Field
     * @param view Parent view element of the activity
     * @param hintString TextView Label value
     * @param contentString TextView Content value
     * @param iconRes Icon Resource value
     * @param listener Handler for field click event
     */
    fun initiateTextViewWithIcon(
        view: View,
        hintString: String?,
        contentString: String?,
        iconRes: Int,
        listener: View.OnClickListener
    ) {
        val textView = view.findViewById<TextView>(R.id.content)
        val icon = view.findViewById<ImageView>(R.id.icon)
        val hint = view.findViewById<TextView>(R.id.hint)
        icon.setImageResource(iconRes)
        hint.text = hintString
        textView.text = contentString
        view.setOnClickListener { listener.onClick(view) }
    }

    /**
     * Commont method to Show Alert Dialog
     * @param context Context of the activity
     * @param message Dialog Message
     */
    fun showDialog(context: Context?, message: String?) {
        AlertDialog.Builder(context)
            .setMessage(message) // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(
                android.R.string.yes
            ) { _, _ ->

            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}