import numpy as n
import pandas as p
import matplotlib.pyplot as m


def funcao(x):
  return ((x - 1)**3 - x + 1 - n.exp(-2 * x))


def derivada(x):
  return (3 * x**2 - 6 * x + 2 + 2 * n.exp(-2 * x))


def newton(x0):
  dados = {'xk': [], '|xk - xk-1|': []}
  xk = x0
  xk_anterior = xk
  while True:
    xk_anterior = xk
    xk = xk - funcao(xk) / derivada(xk)
    diferenca = n.abs(xk - xk_anterior)
    dados['xk'].append(xk)
    dados['|xk - xk-1|'].append(diferenca)
    if diferenca <= 0.0001:
      break
  return xk, p.DataFrame(dados)


def grafico(x0, raiz):
  x = n.linspace(-2, 3, 100)
  y = funcao(x)
  fig, ax = m.subplots()
  ax.plot(x, y, label='f(x)')
  ax.plot(x0, funcao(x0), 'ro', label='Ponto de saída')
  ax.plot(raiz, funcao(raiz), 'go', label='Raiz')
  st = '  ' + str(round(raiz, 4))
  ax.axhline(0, color='black', lw=0.5)
  ax.axvline(0, color='black', lw=0.5)
  ax.legend()
  ax.annotate(st, (raiz, funcao(raiz)))
  ax.set_xlabel('x')
  ax.set_ylabel('f(x)')
  m.ylim(-1, 1)
  m.grid()
  m.show()


x0 = float(input("xk: "))
raiz, resultado = newton(x0)
print(resultado)
grafico(x0, raiz)
