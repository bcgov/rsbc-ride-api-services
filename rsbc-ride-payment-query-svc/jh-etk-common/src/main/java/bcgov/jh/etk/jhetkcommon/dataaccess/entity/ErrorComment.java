package bcgov.jh.etk.jhetkcommon.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ErrorComment {
    private String userName;
    private String comment;
    private Date date;

    public ErrorComment(String userName, String comment) {
        this.userName = userName;
        this.comment = comment;
        this.date = new Date();
    }
}
