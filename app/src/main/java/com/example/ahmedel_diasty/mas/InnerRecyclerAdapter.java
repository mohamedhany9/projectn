package com.example.ahmedel_diasty.mas;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ahmedel_diasty.mas.Model.Schedule;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed El-Diasty on 24/03/2018.
 */

    public class InnerRecyclerAdapter extends RecyclerView.Adapter<InnerRecyclerAdapter.MyViewHolder> {
    Schedule schedule;
    Context context;
    String day;
    public InnerRecyclerAdapter(Context context,Schedule schedule) {
        this.context = context;
        this.schedule = schedule;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new InnerRecyclerAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

final String subjectName = schedule.getDataSchedules().get(position).getSubjectName();
            final String type = schedule.getDataSchedules().get(position).getType();
            final String startTime = schedule.getDataSchedules().get(position).getStartTime();
            final String endTime = schedule.getDataSchedules().get(position).getEndTime();
            final String instructorName = schedule.getDataSchedules().get(position).getInstructorName();
            final String fullMarks = schedule.getDataSchedules().get(position).getTotalMark();
            final String attendancePlace = schedule.getDataSchedules().get(position).getLocation();

//            int start_time = Integer.parseInt(startTime);
//            int end_time = Integer.parseInt(endTime);
//            final int duration_time;
//            if (end_time<start_time)
//                 duration_time = end_time+12-start_time;
//            else
//                duration_time = end_time-start_time;



            holder.rowLectureName.setText(subjectName);
            holder.rowType.setText(type);
            holder.rowStartTime.setText(startTime);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.subject_information);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    TextView lectureName = dialog.findViewById(R.id.lectureName);
                    lectureName.setText(subjectName);
                    TextView instructor_name = dialog.findViewById(R.id.instructorName);
                    instructor_name.setText(instructorName);
                    TextView total_marks = dialog.findViewById(R.id.marksSubject);
                    total_marks.setText(fullMarks+" Marks");
                    TextView start_time = dialog.findViewById(R.id.startTime);
                    start_time.setText(startTime);
//                    TextView time = dialog.findViewById(R.id.timeToLeft);
//                    time.setText(duration_time+" Hours");
                    TextView attendance_place = dialog.findViewById(R.id.attendacePlace);
                    attendance_place.setText(attendancePlace);

                    Button ok = dialog.findViewById(R.id.okDialog);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        dialog.dismiss();
                        }
                    });

                    dialog.show();
                    notifyDataSetChanged();
                }
            });
    }



    @Override
    public int getItemCount() {
        return schedule.getDataSchedules().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView lectureName,instructorName,startTime,time,marks,attendancePlace;

        TextView rowLectureName,rowStartTime,rowType;




        public MyViewHolder(View itemView) {
            super(itemView);
            lectureName = itemView.findViewById(R.id.lectureName);
            instructorName = itemView.findViewById(R.id.instructorName);
            startTime = itemView.findViewById(R.id.startTime);
            time = itemView.findViewById(R.id.timeToLeft);
            attendancePlace = itemView.findViewById(R.id.attendacePlace);
            marks = itemView.findViewById(R.id.marksSubject);

            rowLectureName = itemView.findViewById(R.id.rowLectureName);
            rowStartTime = itemView.findViewById(R.id.rowStartTime);
            rowType = itemView.findViewById(R.id.rowType);
        }

    }
}
