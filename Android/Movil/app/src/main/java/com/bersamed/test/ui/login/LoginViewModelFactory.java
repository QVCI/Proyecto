package com.bersamed.test.ui.login;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.bersamed.test.data.LoginDataSource;
import com.bersamed.test.data.LoginRepository;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private final Context context;

    public LoginViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {

            return (T) new LoginViewModel(
                    LoginRepository.getInstance(new LoginDataSource(context), context)
            );
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}

