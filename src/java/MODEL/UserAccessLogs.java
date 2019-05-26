package MODEL;

import java.io.Serializable;

public class UserAccessLogs implements Serializable {

    private String ID;
    private String userID;
    private String accessType;
    private String timeStamp;
    

    public UserAccessLogs(String ID, String userID, String accessType, String timeStamp) {

        this.ID = ID;
        this.userID = userID;
        this.accessType = accessType;
        this.timeStamp = timeStamp;
        
    }

    public UserAccessLogs() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

  

   
}
