## Krijgt de hoogste score per spel
CREATE VIEW `highest_game` AS 
SELECT `spel_id`          AS `Spel_ID`,
       Max(`totaalscore`) AS `highest_game`
FROM   `score`
GROUP  BY `spel_id` 

## Indien een speler de zelfde score heeft als de hoogste score van het spel dan is hij dus winnaar.
## Een speler krijgt 1 punt per gewonnen spel en 0 voor een verloren spel.
## P.S. indien er een gelijk spel is dan `winnen` voor het gemak beide spelers
CREATE VIEW `wins` AS
SELECT `s`.`account_naam`,
       CASE
         WHEN `highest_game` = `totaalscore` THEN 1
         ELSE 0
       end AS 'wins'
FROM   `score` AS `s`
       JOIN `highest_game` AS `h`
         ON `s`.`spel_id` = `h`.`spel_id` 

## This is where the magic happens.
## Geneert de nodige gemiddelden van het aantal games, aantal gespeelde games, gemiddeld aantal wins per speler
CREATE VIEW `bayesian` AS
SELECT `account_naam`,
       (SELECT Count(`account_naam`)				 ## Geen zin om de subselects buiten te halen,	
        FROM   `wins`) / (SELECT Count(DISTINCT `account_naam`)  ##  te veel moeite aangezien performance niet mega belangrijk is.
                          FROM   `wins`) AS `avg_num_games`,
       (SELECT Avg(`wins`)
        FROM   `wins`)                   AS `avg_wins`,
       Count(`account_naam`)             AS `this_num_games`,
       Avg(`wins`)                       AS `this_wins`
FROM   `wins`
GROUP  BY `account_naam` 

## Past het ranking algorithme toe.
## P.S. hierin is nog niet de ranking per competitie gemaakt aangezien ik te weinig sample data heb.
CREATE VIEW `ranking` AS
SELECT `account_naam`,
       ( ( `avg_num_games` * `avg_wins` ) + ( `this_num_games` * `this_wins` ) )
       / (
       `avg_num_games` + `this_num_games` ) AS `bayesian_rating`
FROM   `bayesian`
ORDER  BY `bayesian_rating` DESC,
          `account_naam` DESC 
