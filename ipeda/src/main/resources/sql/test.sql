-- récupérer le volume d'heures d'un intervenant
select round(sum(m.duree_ffp)/3600000000000) from module m
inner join intervenant i on i.id = m.intervenant_id
and i.nom = 'deliessche'

-- récupère la date du premier jour du numéro de semaine donné
select to_timestamp('8 2018','WW YYYY')::date as week_start;


-- date, heure debut, heure fin, libelle module, nom intervenant, salle, groupe concerné
select dateheuredebut from cours
where dateheuredebut >= to_timestamp('16 2018','WW YYYY')::date and dateheuredebut < (to_timestamp('16 2018','WW YYYY')+ interval '1 week')::date;