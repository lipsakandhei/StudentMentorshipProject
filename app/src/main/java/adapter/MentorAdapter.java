package adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mentorship.student.com.studentmentorship.R;
import model.Mentor;

public class MentorAdapter extends RecyclerView.Adapter<MentorAdapter.MentorVeiwHolder>
{
    private List<Mentor> mentorList;

    @Override
    public MentorVeiwHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mentor,parent,false);
        MentorVeiwHolder mentorVeiwHolder=new MentorVeiwHolder(view);
        return mentorVeiwHolder;
    }

    @Override
    public void onBindViewHolder(MentorVeiwHolder holder, int position)
    {
        Mentor mentor= mentorList.get(position);
        holder.tvMentorQus.setText(mentor.getMentorQes());
    }

    @Override
    public int getItemCount()
    {
        return mentorList.size();
    }

    public MentorAdapter(List<Mentor> mentorList)
    {
        this.mentorList=mentorList;
    }

    public class  MentorVeiwHolder extends RecyclerView.ViewHolder
    {
        private TextView tvMentorQus;
        public MentorVeiwHolder(View itemView)
        {
            super(itemView);
            tvMentorQus=(TextView)itemView.findViewById(R.id.tvMentorQus);
        }
    }
}

