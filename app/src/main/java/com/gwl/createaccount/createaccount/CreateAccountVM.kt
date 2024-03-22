package com.gwl.createaccount.createaccount

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern


class CreateAccountVM : ViewModel() {

    val navigateToLogin: LiveData<Boolean> get() = _navigateToLogin
    private var _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToNext: LiveData<Boolean> get() = _navigateToNext
    private var _navigateToNext = MutableLiveData<Boolean>()
    val wrongEmail: LiveData<Boolean> get() = _wrongEmail
    private var _wrongEmail = MutableLiveData<Boolean>()
    val wrongPassword: LiveData<Boolean> get() = _wrongPassword
    private var _wrongPassword = MutableLiveData<Boolean>()
    private var email: String = ""
    private var password: String = ""
    private var name: String = ""
    private var phone: String = ""

    fun updateEmail(str: String) {
        email = str
    }

    fun updateName(str: String) {
        name = str
    }

    fun updatePassword(str: String) {
        password = str
    }

    fun updatePhone(str: String) {
        phone = str
    }

    fun clickNext() {
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            _navigateToNext.postValue(false)
            return
        }
        if (!isValidEmailId(email)) {
            _wrongEmail.postValue(true)
            return
        }
        if (password.length < 6) {
            _wrongPassword.postValue(true)
            return
        }

        Log.i(CreateAccountVM::class.java.name, "clickNext: ")
        _navigateToNext.postValue(true)
    }

    private fun isValidEmailId(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    fun clickRegisterNow() {
        _navigateToLogin.postValue(true)
    }
}