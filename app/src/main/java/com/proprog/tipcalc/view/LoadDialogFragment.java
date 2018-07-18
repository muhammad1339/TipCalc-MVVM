package com.proprog.tipcalc.view;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.proprog.tipcalc.R;
import com.proprog.tipcalc.viewmodel.CalcViewModel;
import com.proprog.tipcalc.viewmodel.TipCalculationSummaryItem;
import com.proprog.tipcalc.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoadDialogFragment extends DialogFragment{



    TipSummaryAdapter adapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        adapter = new TipSummaryAdapter(getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(createView(getContext()));
        builder.setNegativeButton(R.string.action_cancel,
                (DialogInterface dialog, int which) -> dismiss());
        return builder.create();
    }

    private View createView(Context ctx) {
        RecyclerView recyclerView = LayoutInflater.from(ctx)
                .inflate(R.layout.recycler_layout, null)
                .findViewById(R.id.saved_recycler_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        CalcViewModel vm = ViewModelProviders.of(getActivity()).get(CalcViewModel.class);
        vm.loadSavedTips().observe(this, tipCalculationSummaryItems -> {
            if (tipCalculationSummaryItems != null) {
                adapter.updateList(tipCalculationSummaryItems);
            }
        });
        return recyclerView;
    }
}
