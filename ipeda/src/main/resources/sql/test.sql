-- récupérer le volume d'heures d'un intervenant
select round(sum(m.duree_ffp)/3600000000000) from module m
inner join intervenant i on i.id = m.intervenant_id
and i.nom = 'deliessche'
