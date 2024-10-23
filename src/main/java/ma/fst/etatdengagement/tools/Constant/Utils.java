package ma.fst.etatdengagement.tools.Constant;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Utils {
    public static Pageable getPageable(boolean withPagination, int page, int limit) {
        int page_ = 0;
        int limit_ = Integer.MAX_VALUE;
        if (withPagination) {
            page_ = page;
            limit_ = limit;
            // Ensure page index is 0-based
            if (page_ > 0) page_ = page_ - 1;  // Adjust for zero-based page index
        }
        return PageRequest.of(page_, limit_);
    }

}
