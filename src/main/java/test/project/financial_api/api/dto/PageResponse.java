package test.project.financial_api.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Обёртка страницы данных с информацией о пагинации")
public record PageResponse<T>(
  
  @Schema(description = "Список элементов на текущей странице")
  List<T> content,
  
  @Schema(description = "Номер текущей страницы (начиная с 0)", example = "0")
  int number,
  
  @Schema(description = "Размер страницы (максимальное количество элементов)", example = "20")
  int size,
  
  @Schema(description = "Общее количество элементов во всех страницах", example = "100")
  long totalElements,
  
  @Schema(description = "Общее количество страниц", example = "5")
  int totalPages,
  
  @Schema(description = "Является ли текущая страница последней", example = "true")
  boolean last
) {

}
