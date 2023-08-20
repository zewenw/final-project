package com.finalproject.user.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PagableRequest implements Serializable {

    {
        System.out.println("BLOCK EXECUTE");
//        List<Sort.Order> sort = new ArrayList<>();
//        Map<String, String> fieldsAndDirection = this.sortFieldsAndDirection;
//        if(MapUtil.isNotEmpty(fieldsAndDirection)){
//            Map<String, String> AscSort = fieldsAndDirection.entrySet().stream()
//                    .filter(entry-> entry.getValue().equals(Direction.ASC.toString()))
//                    .collect(Collectors.toMap(String::valueOf, String::valueOf));
//            Map<String, String> DescSort = fieldsAndDirection.entrySet().stream()
//                    .filter(entry -> entry.getValue().equals(Direction.DESC.toString()))
//                    .collect(Collectors.toMap(String::valueOf, String::valueOf));
//            for (String field : AscSort.keySet()) {
//                Sort.Order desc = Sort.Order.asc(field);
//                sort.add(desc);
//            }
//            for (String field : DescSort.keySet()) {
//                Sort.Order desc = Sort.Order.desc(field);
//                sort.add(desc);
//            }
//            this.sort = sort;
//        }
    }

    @NotNull(message = "roleCode must be present")
    private int pageNo;

    @NotNull(message = "roleCode must be present")
    private int pageSize;

    @Schema(description = "key is the field used to sort result, value is the direction, i.e., ASC or DESC")
    private Map<String, String> sortFieldsAndDirection;


    @Schema(hidden = true)
    @JsonIgnore
    private List<Sort.Order> sort;
}
