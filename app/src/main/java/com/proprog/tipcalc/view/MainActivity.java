package com.proprog.tipcalc.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.proprog.tipcalc.R;
import com.proprog.tipcalc.databinding.MainActivityBinding;
import com.proprog.tipcalc.model.RestaurantCalculator;
import com.proprog.tipcalc.viewmodel.CalcViewModel;
import com.proprog.tipcalc.viewmodel.ViewModel;

public class MainActivity extends AppCompatActivity implements SaveDialogFragment.DialogCallback, TipSummaryAdapter.ItemClickListener {
    MainActivityBinding mMainActivityBinding;
    LoadDialogFragment saveDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        mMainActivityBinding.setVm(ViewModelProviders.of(this).get(CalcViewModel.class));

        setSupportActionBar(mMainActivityBinding.toolBar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_save:
                showSaveDialog();
                break;
            case R.id.menu_load:
                showLoadDialog();
                break;
            default:
                break;
        }
        return true;
    }

    private void showSaveDialog() {
        SaveDialogFragment saveDialogFragment = new SaveDialogFragment();
        saveDialogFragment.show(getSupportFragmentManager(), "SaveDialogFragment");
    }

    private void showLoadDialog() {
        saveDialogFragment = new LoadDialogFragment();
        saveDialogFragment.show(getSupportFragmentManager(), "LoadDialogFragment");
    }

    @Override
    public void onSave(String locName) {
        mMainActivityBinding.getVm().saveTip(locName);
        Snackbar.make(mMainActivityBinding.getRoot(), locName, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(String str) {
        mMainActivityBinding.getVm().loadTip(str);
        saveDialogFragment.dismiss();
    }
}
