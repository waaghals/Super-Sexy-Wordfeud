SELECT `c`.`omschrijving`   AS `omschrijving`, 
       `r`.`account_naam`   AS `account`, 
       `b`.`this_num_games` AS `aantal_wedstrijden`, 
       `w`. `wins`          AS `aantal_gewonnen` 
FROM   `ranking` AS `r` 
       JOIN `rank_bayesian` AS `b` 
         ON ( `r`.`competitie_id` = `b`.`competitie_id` 
              AND `r`.`account_naam` = `b`.`account_naam` ) 
       JOIN `rank_winner` AS `w` 
         ON ( `r`.`competitie_id` = `w`.`competitie_id` 
              AND `r`.`account_naam` = `w`.`account_naam` ) 
       JOIN `competitie` AS `c` 
         ON `r`.`competitie_id` = `c`.`id` 
ORDER  BY `r`.`bayesian_rating` DESC
