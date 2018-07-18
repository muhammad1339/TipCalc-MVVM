package com.proprog.tipcalc.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.NonNull;

public abstract class ViewModel extends AndroidViewModel implements Observable {


    PropertyChangeRegistry mCallbacks;

    public ViewModel(@NonNull Application application) {
        super(application);
        mCallbacks = new PropertyChangeRegistry();
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        mCallbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        mCallbacks.remove(callback);
    }

    public void notifyChange(){
        mCallbacks.notifyChange(this,0);
    }
}
