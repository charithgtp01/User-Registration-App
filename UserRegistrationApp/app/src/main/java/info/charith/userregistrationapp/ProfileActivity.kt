package info.charith.userregistrationapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import info.charith.userregistrationapp.Utils.initiateTextViewWithIcon


class ProfileActivity : AppCompatActivity() {
    //Round Shaped Image View
    private lateinit var profileImage: ShapeableImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
    }

    //UI Initiating Method
    private fun init() {
        profileImage = findViewById(R.id.imageView)

        val bundle: Bundle? = intent.extras
        bundle.apply {
            //Parcelable Data
            val user: User? = this?.getParcelable(Constants.OBJECT)
            if (user != null) {

                initiateTextViewWithIcon(
                    findViewById(R.id.nameLayout),
                    getString(R.string.name),
                    user?.name,
                    R.mipmap.name
                ) {}

                initiateTextViewWithIcon(
                    findViewById(R.id.emailLayout),
                    getString(R.string.email),
                    user?.email,
                    R.mipmap.mail
                ) {
                    sendEmail(user?.email)
                }
                initiateTextViewWithIcon(
                    findViewById(R.id.ageLayout),
                    getString(R.string.age),
                    user?.age,
                    R.mipmap.age
                ) {}
                initiateTextViewWithIcon(
                    findViewById(R.id.contactLayout),
                    getString(R.string.phonenumber),
                    user?.phoneNumber,
                    R.mipmap.phone
                ) {
                    call(user?.phoneNumber)
                }

                profileImage.setImageURI(Uri.parse(user.profilePictureUri))
            }
        }

    }

    //Open Dialer Method with auto generated number
    private fun call(number: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)
    }

    //Email picker Method
    private fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SEND)
        val recipients = arrayOf(email)
        intent.putExtra(Intent.EXTRA_EMAIL, recipients)
        intent.putExtra(Intent.EXTRA_SUBJECT, "")
        intent.putExtra(Intent.EXTRA_TEXT, "")
        intent.putExtra(Intent.EXTRA_CC, "")
        intent.type = "text/html"
        startActivity(Intent.createChooser(intent, "Send mail"))
    }
}