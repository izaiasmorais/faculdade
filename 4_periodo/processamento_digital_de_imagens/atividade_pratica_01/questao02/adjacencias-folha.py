from PIL import Image
import numpy as np

def imagem_em_matriz_pixels(caminho):
	imagem_escala_cinza = Image.open(caminho).convert('L')
	matriz_pixels = np.array(imagem_escala_cinza)
	return matriz_pixels

def adjacencia_4_e_m(matriz_pixels):
    pixels_fronteira = []
    altura, largura = matriz_pixels.shape

    for y in range(1, altura - 1):
        for x in range(1, largura - 1):
            if matriz_pixels[y][x] != 0:
                N4P = [matriz_pixels[y-1][x], matriz_pixels[y+1][x],
											 matriz_pixels[y][x-1], matriz_pixels[y][x+1]]
                if any(q == 0 for q in N4P):
                  pixels_fronteira.append((x, y))
    return pixels_fronteira

def adjacencia_8(matriz_pixels):
    pixels_fronteira = []
    altura, largura = matriz_pixels.shape

    for y in range(1, altura - 1):
        for x in range(1, largura - 1):
            if matriz_pixels[y][x] != 0:
                N4P = [matriz_pixels[y-1][x], matriz_pixels[y+1][x],
											 matriz_pixels[y][x-1], matriz_pixels[y][x+1]]
                NDP = [matriz_pixels[y-1][x-1], matriz_pixels[y-1][x+1],
											 matriz_pixels[y+1][x-1], matriz_pixels[y+1][x+1]]
                if any(q == 0 for q in N4P) or any(q == 0 for q in NDP):
                  pixels_fronteira.append((x, y))
    return pixels_fronteira

def salva_imagem(matriz_pixels, pixels_fronteira, caminho):
    img = Image.new('RGB', matriz_pixels.shape, color='black')
    pixels = img.load()

    for pixel in pixels_fronteira:
        pixels[pixel[0], pixel[1]] = (255, 255, 255)

    img.save(caminho)

matriz_pixels = imagem_em_matriz_pixels('folha.png')

pixels_fronteira_adjacencia_4_e_m = adjacencia_4_e_m(matriz_pixels)
pixels_fronteira_adjacencia_8 = adjacencia_8(matriz_pixels)

salva_imagem(matriz_pixels, pixels_fronteira_adjacencia_4_e_m, 'questao02/folha_adjacencia_4_e_m.png')
salva_imagem(matriz_pixels, pixels_fronteira_adjacencia_8, 'questao02/folha_adjacencia_8.png')
