package jp.co.sakuma.stopwatch5;

public class TimeCalculator {

    private int hour;
    private int min;
    private int sec;
    private int msec;

    public TimeCalculator() {
        hour = 0;
        min = 0;
        sec = 0;
        msec = 0;
    }

    public int getMsec() {
        return msec;
    }

    public void setMsec(int msec) {
        this.msec = msec;
    }

    public String time() {

        sec++;
        if (sec >= 60) {
            sec = 0;
            min++;
        }
        if (min >= 60) {
            min = 0;
            hour++;
        }
        return String.format("%02d:%02d:%02d." ,hour, min ,sec);
    }

    public String msecTime() {
        msec++;
        if (msec >= 99) {
            msec = 0;
        }
        return String.format("%02d", msec);
    }
}
