select to_timestamp('09 2018','WW YYYY')::date as week_start;

-- date, heure debut, heure fin, libelle module, nom intervenant, salle, groupe concerné
select
	c.dateheuredebut::date as date_debut,
    c.dateheuredebut::time as heure_debut,
    (c.dateheuredebut + (c.duree/3600000000000 || ' hour')::interval)::time as heure_fin,
    m.libelle as libelle_module,
    i.nom as nom_intervenant,
    s.libelle,
    ue.type_unite_enseignement,
    p.type_parcours
from cours c
inner join module m on m.code = c.module_code
inner join intervenant_module im on im.listemodules_code = m.code
inner join intervenant i on i.id = im.listeintervenants_id
inner join salle_cours sc on sc.listecours_id = c.id
inner join salle s on s.id = sc.listesalles_id
left join unite_enseignement ue on ue.id = m.uniteenseignement_id
left join bloc_competences bc on bc.id = m.bloc_comptences_id or bc.id = ue.bloccompetences_id
left join parcours p on p.id = ue.parcours_id or p.id = bc.parcours_id
where c.dateheuredebut >= to_timestamp('16 2018','WW YYYY')::date and c.dateheuredebut < (to_timestamp('16 2018','WW YYYY')+ interval '1 week')::date;

select * from parcours
select * from bloc_competences
select * from unite_enseignement
select * from module