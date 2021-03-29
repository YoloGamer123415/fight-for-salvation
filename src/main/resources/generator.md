# Generator File format
Deze map bevat alle tile types van het spel.
Hierbij moet een vaste format worden aangehouden: `{CHAR}_{CLASSNAME}.{EXTENSION}`

De `{CHAR}` is het karakter wat een tyle heeft in het level en de `{CLASSNAME}` is de naam van de class die bij de tyle hoort.
De `{EXTENSION}` is de extensie van de Sprite (alleen .jpg en .png zijn toegestaan!).

**Voorbeeld:** `#_BorderTyle.jpg` of `%B_HighTree.png`

**LET OP!** Als een naam begint met %, dan wordt er gekeken in de .obstacles package in plaats van in de .tyles package.