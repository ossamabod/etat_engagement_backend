package ma.fst.etatdengagement.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneriqueResponse<T> {
    private String message;
    private T data;
    private List<T> dataList;
    private boolean success;
    private int status;
}
