package bcgov.jh.etk.jhetkcommon.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Document("errors")
public class ErrorEntity {
    private String errorCategoryCd;
    private String errorSeverityLevelCd;
    private String ticketNo;
    private String contraventionNo;
    private String detailsTxt;
    private String serviceNm;
    private String icbcTxt;
    private List<ErrorComment> comments;

    public ErrorEntity(String errorCategoryCd, String errorSeverityLevelCd, String ticketNo, String contraventionNo, String detailsTxt, String serviceNm, String icbcTxt) {
        this.errorCategoryCd = errorCategoryCd;
        this.errorSeverityLevelCd = errorSeverityLevelCd;
        this.ticketNo = ticketNo;
        this.contraventionNo = contraventionNo;
        this.detailsTxt = detailsTxt;
        this.serviceNm = serviceNm;
        this.icbcTxt = icbcTxt;
        comments = new ArrayList<>();
    }
}
