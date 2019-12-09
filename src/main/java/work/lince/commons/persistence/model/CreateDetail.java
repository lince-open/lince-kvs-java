package work.lince.commons.persistence.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDetail implements Detail {

    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime date;

    @NotNull
    @Column(name = "USER_CREATED", length = 50)
    private String user;

}
