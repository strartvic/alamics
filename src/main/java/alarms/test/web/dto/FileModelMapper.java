package alarms.test.web.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import alarms.test.model.FileModel;

@Mapper(componentModel = "spring")
public interface FileModelMapper {

	FileModelMapper INSTANCE = Mappers.getMapper(FileModelMapper.class);

	@Mappings({ @Mapping(source = "guid", target = "guid"), @Mapping(source = "fileName", target = "name"),
			@Mapping(source = "type", target = "type") })
	FileModelDTO fileModelToFileModelDTO(FileModel fileModel);
}
