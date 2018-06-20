package fr.epsi.ipeda.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.epsi.ipeda.dal.entity.DatabaseProperty;

public interface DatabasePropertyRepository extends JpaRepository<DatabaseProperty, String> {

}
