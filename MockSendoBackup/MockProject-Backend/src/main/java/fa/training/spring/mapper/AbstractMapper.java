package fa.training.spring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMapper<S, D> {
	
	@Autowired
	ModelMapper modelMapper;
	
	protected D mapToDTO(S s, Class<D> d) {
		return s != null ? modelMapper.map(s, d) : null;
	}
	
	protected List<D> mapToListDTO(List<S> sList, Class<D> d) {
		return !sList.isEmpty() ? sList.stream()
				.map(e -> modelMapper.map(e, d))
				.collect(Collectors.toList()) : null;
	}
	
	protected S mapToEntity(Class<S> s, D d) {
		System.out.println(d);
		System.out.println(modelMapper.map(d, s));
		return d != null ? modelMapper.map(d, s) : null;
	}
	
	protected List<S> mapToListEntity(Class<S> s, List<D> dList) {
		return !dList.isEmpty() ? dList.stream()
				.map(e -> modelMapper.map(e, s))
				.collect(Collectors.toList()) : null;
	}
}
