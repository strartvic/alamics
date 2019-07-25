package alarms.test.mapper;

import alarms.test.model.FileModel;
import alarms.test.web.dto.FileModelDTO;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class FileModelMapperImpl implements FileModelMapper {

    @Override
    public FileModelDTO fileModelToFileModelDTO(FileModel fileModel) {
        if ( fileModel == null ) {
            return null;
        }

        FileModelDTO fileModelDTO = new FileModelDTO();

        fileModelDTO.setGuid( fileModel.getGuid() );
        fileModelDTO.setName( fileModel.getName() );
        fileModelDTO.setType( fileModel.getType() );
        fileModelDTO.setDate( fileModel.getDate() );

        return fileModelDTO;
    }

    @Override
    public LinkedList<FileModelDTO> fileModelToFileModelDTO(LinkedHashSet<FileModel> fileModels) {
        if ( fileModels == null ) {
            return null;
        }

        LinkedList<FileModelDTO> linkedList = new LinkedList<FileModelDTO>();
        for ( FileModel fileModel : fileModels ) {
            linkedList.add( fileModelToFileModelDTO( fileModel ) );
        }

        return linkedList;
    }
}
