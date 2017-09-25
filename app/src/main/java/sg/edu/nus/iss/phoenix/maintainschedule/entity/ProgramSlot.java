package sg.edu.nus.iss.phoenix.maintainschedule.entity;


/**
 * Created by Gaurav on 10-09-2017.
 */

public class ProgramSlot {

    private String radioProgramName;
    private String presenter;
    private String producer;
    private String assignedBy;
    private String duration;
    private String startTime;
    private String dateOfProgram;

    public ProgramSlot(String radioProgramName, String presenter, String producer, String assignedBy, String duration, String startTime, String dateOfProgram) {
        this.radioProgramName = radioProgramName;
        this.presenter = presenter;
        this.producer = producer;
        this.assignedBy = assignedBy;
        this.duration = duration;
        this.startTime = startTime;
        this.dateOfProgram = dateOfProgram;
    }

    public String getRadioProgramName() {
        return radioProgramName;
    }

    public void setRadioProgramName(String radioProgramName) {
        this.radioProgramName = radioProgramName;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDateOfProgram() {
        return dateOfProgram;
    }

    public void setDateOfProgram(String dateOfProgram) {
        this.dateOfProgram = dateOfProgram;
    }

}


