package co.za.fnb.volunteers.dto.donation;

import co.za.fnb.volunteers.dto.external.CategoryResponseDto;
import co.za.fnb.volunteers.dto.external.DropOffResponseDto;

import java.util.List;

public class ExternalServicesDto {

    private List<CategoryResponseDto> categories;
    private List<DropOffResponseDto> dropOffPoints;

    public ExternalServicesDto(List<CategoryResponseDto> categories, List<DropOffResponseDto> dropOffPoints) {
        this.categories = categories;
        this.dropOffPoints = dropOffPoints;
    }

    public List<CategoryResponseDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryResponseDto> categories) {
        this.categories = categories;
    }

    public List<DropOffResponseDto> getDropOffPoints() {
        return dropOffPoints;
    }

    public void setDropOffPoints(List<DropOffResponseDto> dropOffPoints) {
        this.dropOffPoints = dropOffPoints;
    }
}
