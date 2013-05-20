SELECT `lb`.`spel_id`              AS `spel_id`
       , `lb`.`beurt_id`           AS `beurt_id`
       , `b`.`account_naam`        AS `account_naam`
       , `l`.`lettertype_karakter` AS `letter`
       , `lt`.`waarde`             AS `waarde`
FROM   `letterbakjeletter` `lb`
       JOIN `letter` `l`
         ON `lb`.`spel_id` = `l`.`spel_id`
            AND `lb`.`letter_id` = `l`.`id`
       JOIN `spel` `s`
         ON `l`.`spel_id` = `s`.`id`
       RIGHT JOIN `lettertype` `lt`
               ON `lt`.`letterset_code` = `s`.`letterset_naam`
                  AND `l`.`lettertype_karakter` = `lt`.`karakter`
       JOIN (SELECT Max(`id`) AS `max`
                    , `account_naam`
             FROM   `beurt` AS `innerb`
             WHERE  `innerb`.`account_naam` = 'marijntje42'
                AND `innerb`.`spel_id` = '511'
                AND `innerb`.`aktie_type` = 'Word') AS `b`
WHERE  `lt`.`letterset_code` = `s`.`letterset_naam`
   AND `max` = `lb`.`beurt_id`
ORDER  BY `lb`.`beurt_id` ASC 
