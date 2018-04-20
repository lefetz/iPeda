package fr.epsi.ipeda.dal.dto.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperManager implements IModelMapper {

	private ModelMapper modelMapper;

	@Override
	public ModelMapper getModelMapper() {
		if (modelMapper == null) {
			modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		}
		return modelMapper;
	}

}
