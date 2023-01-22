package info.charith.userregistrationapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import info.charith.userregistrationapp.Utils.initiateInputTextWithIcon

class RegisterInfoActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAge: EditText
    private lateinit var etTelephoneNumber: EditText
    private lateinit var profileImage: ShapeableImageView
    private lateinit var buttonEdit: Button
    private lateinit var btnSubmit: Button
    private val user: User = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_info)
        init()
    }

    //UI Initiating Method
    private fun init() {
        etName = initiateInputTextWithIcon(
            findViewById(R.id.nameLayout),
            getString(R.string.name),
            R.mipmap.name
        )

        etEmail = initiateInputTextWithIcon(
            findViewById(R.id.emailLayout),
            getString(R.string.email),
            R.mipmap.mail
        )

        etAge = initiateInputTextWithIcon(
            findViewById(R.id.ageLayout),
            getString(R.string.age),
            R.mipmap.age
        )

        etTelephoneNumber = initiateInputTextWithIcon(
            findViewById(R.id.contactLayout),
            getString(R.string.phonenumber),
            R.mipmap.phone
        )

        buttonEdit = findViewById(R.id.btnEdit)
        btnSubmit = findViewById(R.id.btnSubmit)
        profileImage = findViewById(R.id.imageView)

        buttonEdit.setOnClickListener {
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    //permission already granted
                    pickImageFromGallery()
                }
            } else {
                //system OS is < Marshmallow
                pickImageFromGallery()
            }
        }

        btnSubmit.setOnClickListener {
            submit()
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000

        //Permission code
        private val PERMISSION_CODE = 1001
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup granted
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            profileImage.setImageURI(data?.data)
            //Set Image Uri to the User object
            user.profilePictureUri = data?.data.toString()
        }
    }

    //Submit Button Click Method
    private fun submit() {
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val age = etAge.text.toString()
        val phoneNumber = etTelephoneNumber.text.toString()
        val profilePictureUri = user.profilePictureUri;

        //Validating Empty Fields
        when {
            name.isBlank() -> {
                etName.error = "Invalid Name"
            }
            email.isBlank() -> {
                etEmail.error = "Invalid Email"
            }
            age.isBlank() -> {
                etAge.error = "Invalid Age"
            }
            phoneNumber.isBlank() -> {
                etTelephoneNumber.error = "Invalid Phone Number"
            }
            profilePictureUri.isBlank() -> {
                Utils.showDialog(this, getString(R.string.profileimagenotselected))
            }
            else -> {
                user.name = name
                user.email = email
                user.age = age
                user.phoneNumber = phoneNumber
                user.profilePictureUri = profilePictureUri

                //Navigate to Profile Activity with User Object
                var intent = Intent(this, ProfileActivity::class.java);
                intent.putExtra(Constants.OBJECT, user)
                startActivity(intent)
            }
        }
    }
}