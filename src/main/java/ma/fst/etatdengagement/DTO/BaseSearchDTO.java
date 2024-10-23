package ma.fst.etatdengagement.DTO;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseSearchDTO {
    private int page;
    private int limit;
    private boolean isDeleted;
    private boolean withPagination;
}
