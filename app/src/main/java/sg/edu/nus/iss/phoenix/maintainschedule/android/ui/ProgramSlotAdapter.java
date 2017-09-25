package sg.edu.nus.iss.phoenix.maintainschedule.android.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
                    R.layout.activity_maintain_schedule, parent, false);
        }
        //    Word currentWord = getItem(position);
        ProgramSlot currentPS = getItem(position);

        //TODO Complete as per design.

        /*EditText radioPMName = (EditText)listItemView.findViewById(R.id.maintain_program_name_text_view);
        radioPMName.setText(currentRP.getRadioProgramName(), TextView.BufferType.NORMAL);
        radioPMName.setKeyListener(null); // This disables editing.

        EditText radioPMDesc = (EditText)listItemView.findViewById(R.id.maintain_program_desc_text_view);
        radioPMDesc.setText(currentRP.getRadioProgramDescription(), TextView.BufferType.NORMAL);
        radioPMDesc.setKeyListener(null);

        EditText radioPMDuration = (EditText)listItemView.findViewById(R.id.maintain_program_duration_text_view);
        radioPMDuration.setText(currentRP.getRadioProgramDuration(), TextView.BufferType.NORMAL);
        radioPMDuration.setKeyListener(null);
*/
        return listItemView;
    }

}
