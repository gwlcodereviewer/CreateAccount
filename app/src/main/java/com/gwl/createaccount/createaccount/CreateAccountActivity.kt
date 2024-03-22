package com.gwl.createaccount.createaccount

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.gwl.createaccount.BaseActivity
import com.gwl.createaccount.R
import com.gwl.createaccount.dashboard.DashboardActivity
import com.gwl.createaccount.databinding.ActivityCreateAccountBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CreateAccountActivity :
    BaseActivity<ActivityCreateAccountBinding>(R.layout.activity_create_account) {
    private val viewModel: CreateAccountVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
        binding.viewModel = viewModel
    }

    private fun initObserver() {
        viewModel.navigateToLogin.observe(this) {
//            startActivity(Intent(this@CreateAccountActivity,LoginActivity::class.java))
        }
        viewModel.wrongEmail.observe(this) {
            Toast.makeText(this, "Please enter valid email.", Toast.LENGTH_SHORT).show()

        }
        viewModel.wrongPassword.observe(this) {
            Toast.makeText(this, "Please enter password minimum 6 characters.", Toast.LENGTH_SHORT)
                .show()

        }
        viewModel.navigateToNext.observe(this) {
            if (it) {
                if (!binding.checkbox.isChecked) {
                    Toast.makeText(this, "Please select checkbox.", Toast.LENGTH_SHORT).show()
                } else {

                    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                    builder.setCancelable(false) // if you want user to wait for some process to finish,

                    builder.setView(R.layout.layout_loading_dialog)
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                    GlobalScope.launch(Dispatchers.IO) {
                        delay(5000)
                        dialog.dismiss()
                        navigateToDashboard()
                    }
                }
            } else {

                if (binding.fullname.text?.isEmpty() == true) {
                    Toast.makeText(
                        this,
                        getString(R.string.please_enter_full_name),
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (binding.email.text?.isEmpty() == true) {
                    Toast.makeText(
                        this,
                        getString(R.string.please_enter_email),
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (binding.phno.text?.isEmpty() == true) {
                    Toast.makeText(
                        this,
                        getString(R.string.please_enter_phno),
                        Toast.LENGTH_SHORT
                    ).show()
                } else
                    if (binding.etPassword.text?.isEmpty() == true) {
                        Toast.makeText(
                            this,
                            getString(R.string.please_enter_password),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }

    private fun navigateToDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
    }
}