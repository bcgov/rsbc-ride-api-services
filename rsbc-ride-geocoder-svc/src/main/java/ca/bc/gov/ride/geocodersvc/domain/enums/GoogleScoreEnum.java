package ca.bc.gov.ride.geocodersvc.domain.enums;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Accessors(fluent = true)
public enum GoogleScoreEnum {

    ROOFTOP(0, "ROOFTOP", "indicates that the returned result is a precise geocode for which we have location information accurate down to street address precision."),
    RANGE_INTERPOLATED(70, "RANGE_INTERPOLATED", "indicates that the returned result reflects an approximation (usually on a road) interpolated between two precise  points (such as intersections). Interpolated results are generally returned when rooftop geocodes are unavailable for a street address."),
    GEOMETRIC_CENTER(60, "GEOMETRIC_CENTER", "indicates that the returned result is the geometric center of a result such as a polyline (for example, a street) or polygon (region)."),
    APPROXIMATE(40, "APPROXIMATE", "indicates that the returned result is approximate.");

    private int score;
    private String locationType;
    private String description;
    private static Map<String, GoogleScoreEnum> mapCode;

    GoogleScoreEnum(int score, String locationType, String description) {
        this.score = score;
        this.locationType = locationType;
        this.description = description;
    }

    static {
        mapCode = new HashMap<>();
        for (GoogleScoreEnum v : GoogleScoreEnum.values()) {
            mapCode.put(v.locationType, v);
        }
    }

    public static GoogleScoreEnum getEnumfromLocationType(String locationType) {
        return mapCode.get(locationType);
    }
}