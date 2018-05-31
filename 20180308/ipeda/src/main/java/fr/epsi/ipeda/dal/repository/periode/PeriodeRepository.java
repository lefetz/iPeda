package fr.epsi.ipeda.dal.repository.periode;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dal.entity.periode.Periode;

@Repository
public interface PeriodeRepository extends CrudRepository<Periode, Long> {

}
