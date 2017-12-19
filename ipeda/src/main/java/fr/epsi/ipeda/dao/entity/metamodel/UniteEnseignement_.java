package fr.epsi.ipeda.dao.entity.metamodel;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import fr.epsi.ipeda.dao.entity.BlocCompetences;
import fr.epsi.ipeda.dao.entity.Module;
import fr.epsi.ipeda.dao.entity.Parcours;
import fr.epsi.ipeda.dao.entity.UniteEnseignement;
import fr.epsi.ipeda.dao.entity.UniteEnseignement.TypeUE;

@StaticMetamodel(UniteEnseignement.class)
public class UniteEnseignement_ {
	public static volatile SingularAttribute<UniteEnseignement, Long> id;
	public static volatile SingularAttribute<UniteEnseignement, String> libelle;
	public static volatile SingularAttribute<UniteEnseignement, Parcours> parcours;
	public static volatile SingularAttribute<UniteEnseignement, BlocCompetences> blocCompetences;
	public static volatile ListAttribute<UniteEnseignement, Module> listeModules;
	public static volatile SingularAttribute<UniteEnseignement, TypeUE> typeUE;
}
