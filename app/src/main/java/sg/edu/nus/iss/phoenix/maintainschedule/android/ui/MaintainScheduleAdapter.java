package sg.edu.nus.iss.phoenix.maintainschedule.android.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.maintainschedule.entity.ProgramSlot;


/**
 * Created by Gaurav on 24-09-2017.
 */

public class MaintainScheduleAdapter extends ArrayAdapter<ProgramSlot> {
    private List<ProgramSlot> programSlots;
    //private List<User> presenter;
    //private List<User> producer;

    public MaintainScheduleAdapter(@NonNull Context context, ArrayList<ProgramSlot> programSlots) {
        super(context, 0, programSlots);
    }

    /*public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_radio_program, parent, false);
        }

        ProgramSlot currentPS= getItem(position);

    */
}










