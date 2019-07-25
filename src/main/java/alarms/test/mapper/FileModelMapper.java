package alarms.test.mapper;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import alarms.test.model.FileModel;
import alarms.test.web.dto.FileModelDTO;

@Mapper
public interface FileModelMapper {

	FileModelMapper INSTANCE = Mappers.getMapper(FileModelMapper.class);

	FileModelDTO fileModelToFileModelDTO(FileModel fileModel);

	LinkedList<FileModelDTO> fileModelToFileModelDTO(LinkedHashSet<FileModel> fileModels);
}
