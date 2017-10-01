package sg.edu.nus.iss.phoenix.maintainschedule.android.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.maintainschedule.entity.ProgramSlot;

/**
 * Created by Gaurav on 24-09-2017.
 */

public class ProgramSlotAdapter extends ArrayAdapter<ProgramSlot> {


    public ProgramSlotAdapter(@NonNull Context context, ArrayList<ProgramSlot> programSlotArrayList) {
        super(context, 0, programSlotArrayList);
    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_scheduled_program, parent, false);
        }
        ProgramSlot currentPS = getItem(position);

        //TODO Complete as per design.

        EditText radioPMName = (EditText)listItemView.findViewById(R.id.radio_program_name);
        radioPMName.setText(currentPS.getRadioProgramName(), TextView.BufferType.NORMAL);
        radioPMName.setKeyListener(null); // This disables editing.

        EditText dateOfProgram = (EditText)listItemView.findViewById(R.id.date_of_program);
        dateOfProgram.setText(currentPS.getDateOfProgram(), TextView.BufferType.NORMAL);
        dateOfProgram.setKeyListener(null);

        EditText startTime = (EditText)listItemView.findViewById(R.id.start_time);
        startTime.setText(currentPS.getStartTime(), TextView.BufferType.NORMAL);
        startTime.setKeyListener(null);

        EditText duration = (EditText)listItemView.findViewById(R.id.duration);
        duration.setText(currentPS.getDuration(), TextView.BufferType.NORMAL);
        duration.setKeyListener(null);

        EditText presenter = (EditText)listItemView.findViewById(R.id.presenter);
        presenter.setText(currentPS.getPresenter(), TextView.BufferType.NORMAL);
        presenter.setKeyListener(null);

        EditText producer = (EditText)listItemView.findViewById(R.id.producer);
        producer.setText(currentPS.getProducer(), TextView.BufferType.NORMAL);
        producer.setKeyListener(null);

        return listItemView;
    }

}
