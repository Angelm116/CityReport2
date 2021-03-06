package com.example.angelmendez.cityreport;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.ArrayList;


public class ReportsFragment extends Fragment {


    private RecyclerView recyclerView;
    private ListAdapter adapter;
    ArrayList<ReportObject> reportObject;

    public ReportsFragment(ArrayList<ReportObject> reportObject) {

        this.reportObject = reportObject;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reports, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).startNewReport();
            }
        });

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new ListAdapter(reportObject, getContext(), recyclerView, (MainActivity)this.getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       // getReports();

        // Inflate the layout for this fragment
        return view;
    }

    public void updateReportsList(ArrayList<ReportObject> reports)
    {
        Log.d("Method", "on ReportsFragment: updateReportsList()");
        Log.d("Method", "on ReportsFragment: updateReportsList(), list size:" + reports.size());

        adapter.setData(reports);
        adapter.notifyDataSetChanged();

        // Moves the list to the last report
        recyclerView.post(new Runnable() {
            @Override
            public void run() {

                if (adapter.getItemCount() > 0) {
                    recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
                }
            }
        });

        Log.d("Method", "out ReportsFragment: updateReportsList()");
    }


}