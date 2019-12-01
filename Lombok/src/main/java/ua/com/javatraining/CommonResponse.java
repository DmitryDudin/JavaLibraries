package ua.com.javatraining;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    public static final String OK_STATUS = "ok";
    public static final String ERROR_STATUS = "error";

    @Builder.Default
    private String status = OK_STATUS;
    private Long totalElements;

    private T data;

    public CommonResponse(T data) {
        this.data = data;
    }

    public CommonResponse(Long totalElements, T data) {
        this.totalElements = totalElements;
        this.data = data;
    }
}
