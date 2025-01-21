# swipl

filme('StarWars', 'Scifi', 1977, 121).
filme('Forrest Gump', 'Drama', 1994, 122).
filme('De Volta para o Futuro', 'Scifi', 1985, 118).
filme('Diário de Uma Paixão', 'Romance', 2004, 122).
filme('It', 'Terror', 2017, 135).
filme('Um Príncipe em Nova York 2', 'Comédia', 2021, 110).

busca(Nome) :- filme(Nome, _, A, D), A < 1990, D > 120.
