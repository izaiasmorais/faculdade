from PIL import Image
import numpy as np

def imagem_em_matriz_pixels(caminho):
	imagem_escala_cinza = Image.open(caminho).convert('L')
	matriz_pixels = np.array(imagem_escala_cinza)
	return matriz_pixels

def vizinhos_4(matriz_pixels):
  largura, altura = matriz_pixels.shape
  matriz_marcada = np.ones((altura, largura)) * 0

  for x in range(1, largura - 1):
    for y in range(1, altura - 1):
      if matriz_pixels[y, x] != 0:
          matriz_marcada[y, x] = 255
          matriz_marcada[y - 1, x] = 255
          matriz_marcada[y + 1, x] = 255
          matriz_marcada[y, x - 1] = 255
          matriz_marcada[y, x + 1] = 255
  return matriz_marcada

def vizinhos_8(matriz_pixels):
	largura, altura = matriz_pixels.shape
	matriz_marcada = np.ones((altura, largura)) * 0

	for x in range(1, largura - 1):
		for y in range(1, altura - 1):
			if matriz_pixels[y, x] != 0:
				matriz_marcada[y, x] = 255
				matriz_marcada[y - 1, x - 1] = 255
				matriz_marcada[y - 1, x] = 255
				matriz_marcada[y - 1, x + 1] = 255
				matriz_marcada[y, x - 1] = 255
				matriz_marcada[y, x + 1] = 255
				matriz_marcada[y + 1, x - 1] = 255
				matriz_marcada[y + 1, x] = 255
				matriz_marcada[y + 1, x + 1] = 255
	return matriz_marcada

def vizinhos_d(matriz_pixels):
	largura, altura = matriz_pixels.shape
	matriz_marcada = np.ones((altura, largura)) * 0

	for x in range(1, largura - 1):
		for y in range(1, altura - 1):
			if matriz_pixels[y, x] != 0:
				matriz_marcada[y, x] = 255
				matriz_marcada[y - 1, x - 1] = 255
				matriz_marcada[y - 1, x + 1] = 255
				matriz_marcada[y + 1, x - 1] = 255
				matriz_marcada[y + 1, x + 1] = 255
	return matriz_marcada

def salva_imagem(matriz_marcada, caminho):
  imagem = Image.fromarray(matriz_marcada.astype('uint8'))
  imagem.save(caminho)

# vizinhos 4

matriz_pixels_adjacencia_4_e_m = imagem_em_matriz_pixels('questao02/folha_adjacencia_4_e_m.png')
matriz_marcada_adjacencia_4_e_m = vizinhos_4(matriz_pixels_adjacencia_4_e_m)
salva_imagem(matriz_marcada_adjacencia_4_e_m, 'questao03/folha/vizinhos_4_adjacencia_4_e_m.png')

matriz_pixels_adjacencia_8 = imagem_em_matriz_pixels('questao02/folha_adjacencia_8.png')
matriz_marcada_adjacencia_8 = vizinhos_4(matriz_pixels_adjacencia_8)
salva_imagem(matriz_marcada_adjacencia_8, 'questao03/folha/vizinhos_4_adjacencia_8.png')

# vizinhos 8

matriz_pixels_adjacencia_4_e_m = imagem_em_matriz_pixels('questao02/folha_adjacencia_4_e_m.png')
matriz_marcada_adjacencia_4_e_m = vizinhos_8(matriz_pixels_adjacencia_4_e_m)
salva_imagem(matriz_marcada_adjacencia_4_e_m, 'questao03/folha/vizinhos_8_adjacencia_4_e_m.png')

matriz_pixels_adjacencia_8 = imagem_em_matriz_pixels('questao02/folha_adjacencia_8.png')
matriz_marcada_adjacencia_8 = vizinhos_8(matriz_pixels_adjacencia_8)
salva_imagem(matriz_marcada_adjacencia_8, 'questao03/folha/vizinhos_8_adjacencia_8.png')

# vizinhos d

matriz_pixels_adjacencia_4_e_m = imagem_em_matriz_pixels('questao02/folha_adjacencia_4_e_m.png')
matriz_marcada_adjacencia_4_e_m = vizinhos_d(matriz_pixels_adjacencia_4_e_m)
salva_imagem(matriz_marcada_adjacencia_4_e_m, 'questao03/folha/vizinhos_d_adjacencia_4_e_m.png')

matriz_pixels_adjacencia_8 = imagem_em_matriz_pixels('questao02/folha_adjacencia_8.png')
matriz_marcada_adjacencia_8 = vizinhos_d(matriz_pixels_adjacencia_8)
salva_imagem(matriz_marcada_adjacencia_8, 'questao03/folha/vizinhos_d_adjacencia_8.png')
