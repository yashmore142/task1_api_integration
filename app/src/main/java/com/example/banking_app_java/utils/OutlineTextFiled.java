package com.example.banking_app_java.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.banking_app_java.R;
import com.example.banking_app_java.databinding.OutlineTextInputLayoutBinding;
import com.google.android.material.textfield.TextInputLayout;

public class OutlineTextFiled extends TextInputLayout {

    private OutlineTextInputLayoutBinding mBinding;
    private Runnable textChangedAction;



    public OutlineTextFiled(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    private void initializeView(Context context) {
        mBinding = OutlineTextInputLayoutBinding.inflate(LayoutInflater.from(context), this, true);
        if (mBinding.tilInput.getEditText() != null) {
            mBinding.tilInput.getEditText().setTextSize(getResources().getDimension(R.dimen.size_6));
            mBinding.tilInput.getEditText().setMaxLines(1);
            mBinding.tilInput.getEditText().addTextChangedListener(getTextWatcher());
        }
    }

    public void setInputType(int inputType) {
        if (mBinding.tilInput.getEditText() != null) {
            mBinding.tilInput.getEditText().setInputType(inputType);
        }
    }

    public void setHint(String hint) {
        mBinding.tilInput.setHint(hint);
    }

    public void setEndIconModeAsPassword() {
        mBinding.tilInput.setEndIconMode(END_ICON_PASSWORD_TOGGLE);
        if (mBinding.tilInput.getEditText() != null) {
            mBinding.tilInput.getEditText().setTransformationMethod(new PasswordTransformationMethod());
        }
    }

    public void setError(String errorMessage) {
        mBinding.tilInput.setError(errorMessage);
    }

    public String getText() {
        if (mBinding.tilInput.getEditText() != null && mBinding.tilInput.getEditText().getText() != null) {
            return mBinding.tilInput.getEditText().getText().toString();
        }
        return "";
    }

    public void setText(String text) {
        if (mBinding.tilInput.getEditText() != null) {
            mBinding.tilInput.getEditText().setText(text);
        }
    }

    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no-op
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textChangedAction != null) {
                    textChangedAction.run();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    public void setTextChangedAction(Runnable textChangedAction) {
        this.textChangedAction = textChangedAction;
    }
}
