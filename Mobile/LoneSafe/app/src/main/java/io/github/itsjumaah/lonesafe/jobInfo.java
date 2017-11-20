package io.github.itsjumaah.lonesafe;

/**
 * Created by ashneelkumar on 12/06/17.
 */

public class jobInfo {

    public String startTime;
    public String endTime;
    public String workingHours;
    public String riskLevel;
    public String username;
    public String checkin1;
    public String checkin2;
    public String checkin3;
    public String checkin4;
    public String checkin5;
    public String checkin6;
    public String checkin7;
    public String checkin8;
    public String nextCheckin;

    public jobInfo() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public jobInfo (String username, String startTime, String endTime, String workingHours, String riskLevel,
                    String checkin1, String checkin2, String checkin3,String checkin4, String checkin5,
                    String checkin6, String checkin7, String checkin8, String nextcheckin) {
        this.username = username;
        this. startTime = startTime;
        this.endTime = endTime;
        this.workingHours = workingHours;
        this.riskLevel = riskLevel;

        this.checkin1 = checkin1;
        this.checkin2 = checkin2;
        this.checkin3 = checkin3;
        this.checkin4 = checkin4;
        this.checkin5 = checkin5;
        this.checkin6 = checkin6;
        this.checkin7 = checkin7;
        this.checkin8 = checkin8;
        this.nextCheckin = nextcheckin;

    }

}
