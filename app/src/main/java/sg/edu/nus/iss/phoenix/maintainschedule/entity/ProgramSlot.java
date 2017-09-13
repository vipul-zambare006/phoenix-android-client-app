package sg.edu.nus.iss.phoenix.maintainschedule.entity;

import java.util.Date;

/**
 * Created by Gaurav on 10-09-2017.
 */

public class ProgramSlot {

    private Date scheduleDate;

    public ProgramSlot(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
}

