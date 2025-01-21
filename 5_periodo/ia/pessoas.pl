# swipl

pessoa("Ana", "Fem", 23, 1.55, 56.0).
pessoa("Bia", "Fem", 19, 1.71, 61.3).
pessoa("Ivo", "Masc", 22, 1.80, 70.5).
pessoa("Lia", "Fem", 17, 1.85, 57.3).
pessoa("Eva", "Fem", 28, 1.75, 68.7).
pessoa("Ary", "Masc", 25, 1.72, 68.9).

mulheres(X) :- pessoa(X, "Fem", I, _, _), I > 20.
altura(X) :- pessoa(X, _, _, A, P), A >= 1.70, P < 65.0.
casais(X, Y) :- pessoa(X, "Masc", _, A, _), pessoa(Y, "Fem", _, B, _), A > B.
