SELECT lb.Spel_ID, lb.Beurt_ID, b.Account_naam,l.ID, l.LetterType_karakter
FROM LetterbakjeLetter lb
JOIN Letter l ON lb.Spel_ID = l.Spel_ID
AND lb.Letter_ID = l.ID
JOIN `beurt` `b` ON ( (
`lb`.`Beurt_ID` = `b`.`ID`
)
AND (
`lb`.`Spel_ID` = `b`.`Spel_ID`
) )

where b.Account_naam = ? and l.spel_ID = ?


