package fr.eseo.dis.camille.pfeandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.camille.pfeandroid.bean.Student;

/**
 * Created by camil on 20/12/2017.
 */

public class ProjectStudentsAdaptater  extends RecyclerView.Adapter<ProjectStudentsAdaptater.StudentViewHolder> {


    private final Context context;
    private List<String> students;

    public ProjectStudentsAdaptater(Context context) {
        this.context = context;
        students = new ArrayList<>();
    }

    public int getItemCount() {
        return students.size();
    }

    public StudentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View studentView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_students_card, viewGroup, false);
        return new StudentViewHolder(studentView);
    }


    public void onBindViewHolder(StudentViewHolder studentViewHolder, int i) {
        String student = students.get(i);
        studentViewHolder.studentName
                .setText(student);
    }

    public void setStudentName(List<String> students) {

        this.students = students;
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {

        private final TextView studentName;

        public StudentViewHolder(View view) {
            super(view);
            studentName = (TextView) view.findViewById(R.id.project_student);
        }
    }
}