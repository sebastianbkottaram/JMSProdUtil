public class Alarm {

   String monitor_id;
    String IP;
    String Alarm_instance_number;
    String Alert_Message;
    String Alarm_name;
    String Category;
    String Severity;
    String Occurrence_time;
    String Clearance_time;
    String Affected_NE_entity;

    public String getMonitor_id() {
        return monitor_id;
    }

    public void setMonitor_id(String monitor_id) {
        this.monitor_id = monitor_id;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getAlarm_instance_number() {
        return Alarm_instance_number;
    }

    public void setAlarm_instance_number(String alarm_instance_number) {
        Alarm_instance_number = alarm_instance_number;
    }

    public String getAlert_Message() {
        return Alert_Message;
    }

    public void setAlert_Message(String alert_Message) {
        Alert_Message = alert_Message;
    }

    public String getAlarm_name() {
        return Alarm_name;
    }

    public void setAlarm_name(String alarm_name) {
        Alarm_name = alarm_name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSeverity() {
        return Severity;
    }

    public void setSeverity(String severity) {
        Severity = severity;
    }

    public String getOccurrence_time() {
        return Occurrence_time;
    }

    public void setOccurrence_time(String occurrence_time) {
        Occurrence_time = occurrence_time;
    }

    public String getClearance_time() {
        return Clearance_time;
    }

    public void setClearance_time(String clearance_time) {
        Clearance_time = clearance_time;
    }

    public String getAffected_NE_entity() {
        return Affected_NE_entity;
    }

    public void setAffected_NE_entity(String affected_NE_entity) {
        Affected_NE_entity = affected_NE_entity;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "monitor_id='" + monitor_id + '\'' +
                ", IP='" + IP + '\'' +
                ", Alarm_instance_number='" + Alarm_instance_number + '\'' +
                ", Alert_Message='" + Alert_Message + '\'' +
                ", Alarm_name='" + Alarm_name + '\'' +
                ", Category='" + Category + '\'' +
                ", Severity='" + Severity + '\'' +
                ", Occurrence_time='" + Occurrence_time + '\'' +
                ", Clearance_time='" + Clearance_time + '\'' +
                ", Affected_NE_entity='" + Affected_NE_entity + '\'' +
                '}';
    }
}
