package fr.epsi.ipeda.dal.repository.periode;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dal.entity.periode.PeriodeType;

@Repository
public interface PeriodeTypeRepository extends CrudRepository<PeriodeType, Long> {

	PeriodeType findByLibelle(String libelle);

}
