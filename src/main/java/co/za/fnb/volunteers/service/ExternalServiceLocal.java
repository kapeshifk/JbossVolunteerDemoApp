package co.za.fnb.volunteers.service;

import co.za.fnb.volunteers.dto.external.CategoryRequestDto;
import co.za.fnb.volunteers.dto.external.CategoryResponseDto;
import co.za.fnb.volunteers.dto.external.DropOffRequestDto;
import co.za.fnb.volunteers.dto.external.DropOffResponseDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ExternalServiceLocal{

    List<CategoryResponseDto> getCategories(List<CategoryRequestDto> inputs);

    List<DropOffResponseDto> getDropOffPoints(List<DropOffRequestDto> requests);

}
