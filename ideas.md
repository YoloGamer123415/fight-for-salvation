# Wapens
Wapens zijn opgedeeld in twee typen, `short range` en `long range`. Short range houd in dat ze een bereik van _1 tile_ hebben. Long range heeft een bereik van _oneindig/veel tiles_. Er wordt aangevallen met een linker muisklik. De speler "hit" dan in de richting van de muis. Een paar voorbeelden van short range wapens:

- Mes
- Katana
- Vuist

Een aantal voorbeelden van long range wapens:

- Pijl en boog
- Pistool
- Magic wand (als we de magie kant opgaan tenminste)

# Abilities
Abilities zijn aanvallen/wapens met een cooldown. Denk bijvoorbeeld aan het bevriezen van enemies in de buurt. Er zijn twee soorten abilities, namelijk `throwables` en `powerups`[^1]. Bij throwables kan je denken aan het volgende:

- Potions. Bijvoorbeeld een poison potion of een soort moletov.
- thunderbolt. Doet schade aan alle enemies in de lijn vanaf de speler richting de muis en verder.

En een aantal powerups:

- Strength. Doet meer damage voor x seconden.
- Dash. Schiet x tile in de richting van de muis.

Een throwable kan worden gegooid door op de rechter muisknop te klikken[^2]. De throwable wordt uitgevoerd in de richting van de muis. Een powerup kan worden geactiveerd door op de spatiebalk te drukken.

[^1]: Er kan nog gewerkt worden aan deze naam.
[^2]: Is dit mogelijk in processing?

#  UI
Rechtsonder in zie je alle wapens en abilities die je hebt geequiped. Er staan ook kleine icoontjes bij van welke knop ze activeert.

#  Levels

Ieder level speelt zich af op een eiland waar je tegen mobs vecht. In ieder level komen er meer en moeilijkere mobs.

##  Map generation
Iedere map wordt opgeslagen in een bestand genaamd `level-{{lvl_nmr}}.txt`. Er staan bepaalde characters in die corresponderen met bepaalde objecten, zoals b.v. een boom. Hier een paar ideeën:

- `#`: het uiteinde van de map.
- ` `: een beloopbare tile.
- `B`: een boom waar niet langsheen gekeken kan worden en waar ook geen arrows e.d. doorheen kunnen.
- `b`: een liggende boom. Hier kan niet overheen worden gelopen, maar wel overheen geschoten (met b.v. arrows).

# Hebben we misschien nodig
`Ray`s. Bijvoorbeeld voor de pathfinding van de mobs, om te kijken of ze de speler kunnen zien of dat er een boom o.i.d. in de weg staat.
